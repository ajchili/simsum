package com.kirinpatel.simsum.gui;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class FileSelector {

    public ArrayList<File> getFiles() {
        JFileChooser mediaSelector = new JFileChooser("");
        mediaSelector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        mediaSelector.showOpenDialog(null);

        ArrayList<File> files = new ArrayList<>();

        File selectedFile = mediaSelector.getSelectedFile();
        if (selectedFile == null) {
            return null;
        } else {
            if (selectedFile.isDirectory()) {
                for (File file : selectedFile.listFiles()) {
                    if (!file.isDirectory()
                            && (file.getName().toLowerCase().contains(".png")
                            || file.getName().toLowerCase().contains(".jpg"))) {
                        files.add(file);
                    }
                }
                return files;
            } else {
                files.add(selectedFile);
                return files;
            }
        }
    }

}
