package com.kirinpatel.util;

import java.awt.image.BufferedImage;

public class Image {

    private BufferedImage image;
    private int[] offset = new int[2];
    private float scale = 0f;

    public Image(BufferedImage image) {
        this.image = image;
    }

    public void setOffset(int[] offset) {
        this.offset = offset;
    }

    public void setScale(float scale) {
        this.scale += scale;
        if (this.scale < 0) this.scale = 0;
        else if (this.scale > 10) this.scale = 10;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getOffset(int offest) {
        return this.offset[offest];
    }

    public int[] getOffset() {
        return offset;
    }

    public float getScale() {
        return scale;
    }
}
