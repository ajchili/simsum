package com.kirinpatel.gui;

import com.kirinpatel.util.Image;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ImageView extends Canvas {

    private Image image;

    ImageView() {
        ImageListener imageListener = new ImageListener();
        addMouseListener(imageListener);
        addMouseMotionListener(imageListener);
        addMouseWheelListener(imageListener);
    }

    void setImage(Image image) {
        this.image = image;
        repaint();
    }

    void clearImage() {
        this.image = null;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            float scale = image.getScale();
            int x = getWidth() + getWidth() * scale < getWidth() ? getWidth() : (int) Math.abs(getWidth() + getWidth() * scale);
            int y = getHeight() + getHeight() * scale < getHeight() ? getHeight() : (int) Math.abs(getHeight() + getHeight() * scale);
            int xOffset = image.getOffset()[0];
            int yOffset = image.getOffset()[1];

            g.drawImage(image.getImage(), xOffset, yOffset, x, y, this);
        }
    }

    public Image getImage() {
        return image;
    }

    public BufferedImage getView() {
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        paint(g);
        return bufferedImage;
    }

    class ImageListener implements MouseListener, MouseMotionListener, MouseWheelListener {

        private long lastClick;
        private int startingX, startingY;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (lastClick < System.currentTimeMillis() + 200 && image != null) {
                int[] offset = { 0, 0 };
                image.setOffset(offset);
                image.resetScale();
                repaint();
            }

            lastClick = System.currentTimeMillis();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (image != null) {
                startingX = e.getX();
                startingY = e.getY();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int[] offset = { image.getOffset()[0] + (e.getX() - startingX), image.getOffset()[1] + (e.getY() - startingY) };

            int width = getWidth() + (int) (getWidth() * image.getScale());
            int height = getHeight() + (int) (getHeight() * image.getScale());

            if (offset[0] > width) offset[0] = width;
            else if (offset[0] < width * -1) offset[0] = width * -1;
            if (offset[1] > height) offset[1] = height;
            else if (offset[1] < height * -1) offset[1] = height * -1;

            if (image != null) image.setOffset(offset);
            startingX = e.getX();
            startingY = e.getY();
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (image != null) image.setScale((float) (e.getPreciseWheelRotation() / 100));
            repaint();
        }
    }
}
