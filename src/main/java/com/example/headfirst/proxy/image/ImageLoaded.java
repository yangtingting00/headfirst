package com.example.headfirst.proxy.image;

import javax.swing.*;
import java.awt.*;

public class ImageLoaded implements State {
    ImageProxy imageProxy;

    public ImageLoaded(ImageProxy imageProxy) {
        this.imageProxy = imageProxy;
    }

    public void paintIcon(final Component c, Graphics g, int x, int y){
        imageProxy.getImageIcon().paintIcon(c,g,x,y);
    }
}
