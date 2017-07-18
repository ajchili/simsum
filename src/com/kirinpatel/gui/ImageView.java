package com.kirinpatel.gui;

import com.kirinpatel.util.Image;

import java.awt.*;
import java.awt.event.*;

public class ImageView extends Canvas {

    private Image image;

    public ImageView() {
        ImageListener imageListener = new ImageListener();
        addMouseListener(imageListener);
        addMouseMotionListener(imageListener);
        addMouseWheelListener(imageListener);
    }

    public ImageView(Image image) {
        this();
        setImage(image);
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            float scale = image.getScale();
            int x = getWidth() + getWidth() * scale < getWidth() ? getWidth() : (int) Math.abs(getWidth() + getWidth() * scale);
            int y = getHeight() + getHeight() * scale < getHeight() ? getHeight() : (int) Math.abs(getHeight() + getHeight() * scale);
            int xOffset = image.getOffset(0);
            int yOffset = image.getOffset(1);

            g.drawImage(image.getImage(), xOffset, yOffset, x, y, this);
        }
    }

    class ImageListener implements MouseListener, MouseMotionListener, MouseWheelListener {

        private long lastClick;
        private int startingX, startingY;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (lastClick < System.currentTimeMillis() + 200 && image != null) {
                int[] offset = { 0, 0 };
                image.setOffset(offset);
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
            int[] offset = { image.getOffset(0) + (e.getX() - startingX), image.getOffset(1) + (e.getY() - startingY) };

            if (offset[0] > getWidth()) offset[0] = getWidth();
            else if (offset[0] < getWidth() * -1) offset[0] = getWidth() * -1;
            if (offset[1] > getHeight()) offset[1] = getHeight();
            else if (offset[1] < getHeight() * -1) offset[1] = getHeight() * -1;

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