package com.kirinpatel.simsum.gui;

import com.kirinpatel.simsum.Simsum;
import com.kirinpatel.simsum.util.Image;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Window extends JFrame {

    private ImageView image;
    private ArrayList<Image> images = new ArrayList<>();
    private JButton add;
    private JButton remove;
    private JButton clear;
    private JButton mode;
    private JButton lastFile;
    private JButton readFile;
    private JButton nextFile;
    private JTextArea OCRTextArea;
    private int ocrMode = 0;

    public Window() {
        super("simsum");
        setUILook();

        setMinimumSize(new Dimension(600, 400));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel controls = new JPanel(new GridLayout(5, 1));
        JButton select = new JButton("Select file/folder");
        select.setSize(new Dimension(200, select.getHeight()));
        select.addActionListener(e -> {
            ArrayList<File> files = Simsum.FILE_SELECTOR.getFiles();
            if (image != null && files != null) {
                images.clear();
                for (File file : files) {
                    images.add(new Image(file));
                }
                image.setImage(images.get(0));

                lastFile.setEnabled(images.size() > 1);
                readFile.setEnabled(true);
                nextFile.setEnabled(images.size() > 1);
                add.setEnabled(true);
                remove.setEnabled(true);
                clear.setEnabled(true);
                mode.setEnabled(true);
                if (images.size() > 1) {
                    setTitle("simsum (" + (images.indexOf(image.getImage()) + 1) + "/" + images.size() + ")");
                } else {
                    setTitle("simsum");
                }
            }
        });
        controls.add(select);
        add = new JButton("Add file/folder");
        add.setSize(new Dimension(200, add.getHeight()));
        add.setEnabled(false);
        add.addActionListener(e -> {
            ArrayList<File> files = Simsum.FILE_SELECTOR.getFiles();
            if (image != null && files != null) {
                for (File file : files) {
                    images.add(new Image(file));
                }
                image.setImage(images.get(images.size() - 1));

                lastFile.setEnabled(true);
                nextFile.setEnabled(true);
                setTitle("simsum (" + (images.indexOf(image.getImage()) + 1) + "/" + images.size() + ")");
            }
        });
        controls.add(add);
        remove = new JButton("Remove file");
        remove.setSize(new Dimension(200, remove.getHeight()));
        remove.setEnabled(false);
        remove.addActionListener(e -> {
            int index = images.indexOf(image.getImage());
            images.remove(index);
            if (images.size() == 0) {
                image.clearImage();

                lastFile.setEnabled(false);
                readFile.setEnabled(false);
                nextFile.setEnabled(false);
                setTitle("simsum");

                add.setEnabled(false);
                clear.setEnabled(false);
                mode.setEnabled(false);
                OCRTextArea.setText("");
                ocrMode = 0;
            } else {
                if (index < images.size()) {
                    image.setImage(images.get(index));
                } else {
                    image.setImage(images.get(index - 1));
                }
                setTitle("simsum (" + (images.indexOf(image.getImage()) + 1) + "/" + images.size() + ")");
                OCRTextArea.setText(images.get(index).getOCRText());
            }
        });
        controls.add(remove);
        clear = new JButton("Clear file(s)");
        clear.setSize(new Dimension(200, clear.getHeight()));
        clear.setEnabled(false);
        clear.addActionListener(e -> {
            images.clear();
            image.clearImage();

            lastFile.setEnabled(false);
            readFile.setEnabled(false);
            nextFile.setEnabled(false);
            setTitle("simsum");

            add.setEnabled(false);
            clear.setEnabled(false);
            mode.setEnabled(false);
            OCRTextArea.setText("");
            ocrMode = 0;
        });
        controls.add(clear);
        mode = new JButton("Mode (scale)");
        mode.setSize(new Dimension(200, mode.getHeight()));
        mode.setEnabled(false);
        mode.addActionListener(e -> {
            ocrMode = (ocrMode + 1) % 2;
            if (ocrMode == 0)  {
                mode.setText("Mode (scale)");
            } else {
                mode.setText("Mode (section)");
            }
        });
        controls.add(mode);
        add(controls, BorderLayout.WEST);

        JPanel view = new JPanel(new BorderLayout());
        image = new ImageView();
        view.add(image, BorderLayout.CENTER);
        image.repaint();
        add(view, BorderLayout.CENTER);
        JPanel viewControls = new JPanel(new GridLayout(1, 5));
        viewControls.add(new JPanel());
        lastFile = new JButton("<");
        lastFile.setEnabled(false);
        lastFile.addActionListener(e -> {
            int index = images.indexOf(image.getImage());
            if (index == 0) {
                image.setImage(images.get(images.size() - 1));
            } else {
                image.setImage(images.get(index - 1));
            }
            setTitle("simsum (" + (images.indexOf(image.getImage()) + 1) + "/" + images.size() + ")");
            OCRTextArea.setText(image.getImage().getOCRText());
        });
        viewControls.add(lastFile);
        readFile = new JButton("Read text");
        readFile.setEnabled(false);
        readFile.addActionListener(e -> new Thread(() -> {
            int index = images.indexOf(image.getImage());
            try {
                if (images.get(index).getScale() == 0f) {
                    images.get(index).setOCRText(Simsum.OCR.readImage(images.get(index)));
                } else {
                    images.get(index).setOCRText(Simsum.OCR.readImage(image.getView()));
                }
                OCRTextArea.setText(images.get(index).getOCRText());
            } catch (TesseractException e1) {
                e1.printStackTrace();
            }
        }).start());
        viewControls.add(readFile);
        nextFile = new JButton(">");
        nextFile.setEnabled(false);
        nextFile.addActionListener(e -> {
            int index = images.indexOf(image.getImage());
            if (index == images.size() - 1) {
                image.setImage(images.get(0));
            } else {
                image.setImage(images.get(index + 1));
            }
            setTitle("simsum (" + (images.indexOf(image.getImage()) + 1) + "/" + images.size() + ")");
            OCRTextArea.setText(images.get(index).getOCRText());
        });
        viewControls.add(nextFile);
        viewControls.add(new JPanel());
        view.add(viewControls, BorderLayout.SOUTH);

        OCRTextArea = new JTextArea();
        OCRTextArea.setRows(4);
        OCRTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(OCRTextArea);
        add(scrollPane, BorderLayout.SOUTH);

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
