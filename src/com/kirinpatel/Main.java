package com.kirinpatel;

import com.kirinpatel.gui.Window;
import net.sourceforge.tess4j.Tesseract;

public class Main {

    public static final Tesseract INSTANCE = new Tesseract();

    public static void main(String[] args) {
        new Window();
    }
}
