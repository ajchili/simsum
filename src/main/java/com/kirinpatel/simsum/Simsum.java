package com.kirinpatel.simsum;

import com.kirinpatel.simsum.gui.FileSelector;
import com.kirinpatel.simsum.util.OCR;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Simsum {

    public static final Tesseract INSTANCE = new Tesseract();
    private static final ApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("SpringConfig.xml");
    public static final FileSelector FILE_SELECTOR = (FileSelector) Simsum.CONTEXT.getBean("fileSelectorBean");
    public static final OCR OCR = (OCR) Simsum.CONTEXT.getBean("ocrBean");

    public static void main(String[] args) {
       CONTEXT.getBean("windowBean");
    }
}
