package com.kirinpatel.util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR extends Tesseract {

    public static String readImage(Image image) throws TesseractException {
        Tesseract instance = new Tesseract();
        return instance.doOCR(image.getImage());
    }
}
