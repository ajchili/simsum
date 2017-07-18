package com.kirinpatel.gui;

import com.kirinpatel.util.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame {

    private ImageView image;
    private ArrayList<Image> images = new ArrayList<>();

    public Window() {
        super("simsum");
        setUILook();

        setMinimumSize(new Dimension(600, 400));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel controls = new JPanel(new GridLayout(5, 1));

        JButton select = new JButton("Select file/folder");
        select.setSize(new Dimension(200, select.getHeight()));
        select.addActionListener(e -> {
            try {
                images.add(new Image(ImageIO.read(FileSelector.getFiles().get(0))));
                if (image != null) image.setImage(images.get(images.size() - 1));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        controls.add(select);

        JButton add = new JButton("Add file/folder");
        add.setSize(new Dimension(200, add.getHeight()));
        add.addActionListener(e -> {
            try {
                images.add(new Image(ImageIO.read(FileSelector.getFiles().get(0))));
                if (image != null) image.setImage(images.get(images.size() - 1));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        controls.add(add);

        add(controls, BorderLayout.WEST);

        JPanel view = new JPanel(new BorderLayout());

        image = new ImageView();
        view.add(image, BorderLayout.CENTER);
        image.repaint();

        JPanel viewControls = new JPanel(new GridLayout(1, 7));

        view.add(viewControls, BorderLayout.SOUTH);

        add(view, BorderLayout.CENTER);

        setVisible(true);
    }

    private void setUILook() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
