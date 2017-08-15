package com.kirinpatel.simsum.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private BufferedImage image;
    private int[] offset = new int[2];
    private float scale = 0f;
    private String OCRText = "";

    public Image(File file) {
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOffset(int[] offset) {
        this.offset = offset;
    }

    public void resetScale() {
        scale = 0f;
    }

    public void setScale(float scale) {
        this.scale += scale;
        if (this.scale < 0) this.scale = 0;
        else if (this.scale > 10) this.scale = 10;
    }

    public void setOCRText(String OCRText) {
        this.OCRText = OCRText;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getOffset() {
        return offset;
    }

    public float getScale() {
        return scale;
    }

    public String getOCRText() {
        return OCRText;
    }
}
