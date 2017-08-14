package com.kirinpatel.simsum;

import com.kirinpatel.simsum.gui.Window;
import net.sourceforge.tess4j.Tesseract;

public class Simsum {

    public static final Tesseract INSTANCE = new Tesseract();

    public static void main(String[] args) {
        new Window();
    }
}
