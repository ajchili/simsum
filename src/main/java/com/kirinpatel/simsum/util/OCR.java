package com.kirinpatel.simsum.util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

public class OCR extends Tesseract {

    private static final Tesseract TESSERACT = new Tesseract();

    public String readImage(Image image) throws TesseractException {
        return TESSERACT.doOCR(image.getImage());
    }

    public String readImage(BufferedImage image) throws TesseractException {
        return TESSERACT.doOCR(image);
    }
}
