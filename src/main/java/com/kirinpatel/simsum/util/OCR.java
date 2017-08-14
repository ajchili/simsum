package com.kirinpatel.simsum.util;

import com.kirinpatel.simsum.Simsum;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

public class OCR extends Tesseract {

    public static String readImage(Image image) throws TesseractException {
        return Simsum.INSTANCE.doOCR(image.getImage());
    }

    public static String readImage(BufferedImage image) throws TesseractException {
        return Simsum.INSTANCE.doOCR(image);
    }
}
