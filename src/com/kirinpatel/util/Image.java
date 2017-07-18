package com.kirinpatel.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private File file;
    private BufferedImage image;
    private int[] offset = new int[2];
    private float scale = 0f;

    public Image(File file) {
        this.file = file;
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public File getFile() {
        return file;
    }

    public int[] getOffset() {
        return offset;
    }

    public float getScale() {
        return scale;
    }
}
