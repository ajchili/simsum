package com.kirinpatel.util;

import com.kirinpatel.Main;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

public class OCR extends Tesseract {

    public static String readImage(Image image) throws TesseractException {
        return Main.INSTANCE.doOCR(image.getImage());
    }

    public static String readImage(BufferedImage image) throws TesseractException {
        return Main.INSTANCE.doOCR(image);
    }
}
