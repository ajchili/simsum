package com.kirinpatel.gui;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class FileSelector {

    public static ArrayList<File> getFiles() {
        JFileChooser mediaSelector = new JFileChooser("tomcat/webapps/media");
        mediaSelector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        mediaSelector.showOpenDialog(null);

        ArrayList<File> files = new ArrayList<>();

        File selectedFile = mediaSelector.getSelectedFile();
        if (selectedFile == null) return null;
        else {
            if (selectedFile.isDirectory()) {
                return files;
            } else {
                files.add(selectedFile);
                return files;
            }
        }
    }

}
