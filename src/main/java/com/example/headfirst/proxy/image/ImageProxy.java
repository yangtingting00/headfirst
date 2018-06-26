package com.example.headfirst.proxy.image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.net.URL;

public class ImageProxy implements Icon {

    ImageIcon imageIcon;
    URL imageURL;
    Thread retrievalThread;
    boolean retrieving = false;

    public ImageProxy(URL imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public int getIconWidth() {
        if (imageIcon != null){
            return imageIcon.getIconWidth();
        } else {
            return 800;
        }
    }

    @Override
    public int getIconHeight() {
        if (imageIcon != null){
            return imageIcon.getIconHeight();
        } else {
            return 600;
        }
    }

    @Override
    public void paintIcon(final Component c, Graphics g, int x, int y) {
        if (imageIcon != null){
            imageIcon.paintIcon(c,g,x,y);
        } else {
            g.drawString("Loading CD cover, please wait ...", x+300,y+190);
            if (!retrieving){
                retrieving = true;
                retrievalThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        imageIcon = new ImageIcon(imageURL,"CD Cover");
                        c.repaint();
                    }
                });
                retrievalThread.start();
            }
        }
    }
}
