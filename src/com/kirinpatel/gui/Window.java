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
    private JButton lastFile;
    private JButton readFile;
    private JButton nextFile;

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

            lastFile.setEnabled(images.size() > 1);
            readFile.setEnabled(images.size() > 0);
            nextFile.setEnabled(images.size() > 1);
            if (images.size() > 1) setTitle("simsum (" + (images.indexOf(image.getImage()) + 1) + "/" + images.size() + ")");
            else setTitle("simsum");
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

            lastFile.setEnabled(images.size() > 1);
            readFile.setEnabled(images.size() > 0);
            nextFile.setEnabled(images.size() > 1);
        });
        controls.add(add);

        add(controls, BorderLayout.WEST);

        JPanel view = new JPanel(new BorderLayout());

        image = new ImageView();
        view.add(image, BorderLayout.CENTER);
        image.repaint();

        JPanel viewControls = new JPanel(new GridLayout(1, 5));

        viewControls.add(new JPanel());

        lastFile = new JButton("<");
        lastFile.setEnabled(false);
        lastFile.addActionListener(e -> {
            int index = images.indexOf(image.getImage());

            if (index == 0) image.setImage(images.get(images.size() - 1));
            else image.setImage(images.get(index - 1));

            setTitle("simsum (" + (images.indexOf(image.getImage()) + 1) + "/" + images.size() + ")");
        });
        viewControls.add(lastFile);

        readFile = new JButton("Read text");
        readFile.setEnabled(false);
        viewControls.add(readFile);

        nextFile = new JButton(">");
        nextFile.setEnabled(false);
        nextFile.addActionListener(e -> {
            int index = images.indexOf(image.getImage());

            if (index == images.size() - 1) image.setImage(images.get(0));
            else image.setImage(images.get(index + 1));

            setTitle("simsum (" + (images.indexOf(image.getImage()) + 1) + "/" + images.size() + ")");
        });
        viewControls.add(nextFile);

        viewControls.add(new JPanel());

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
