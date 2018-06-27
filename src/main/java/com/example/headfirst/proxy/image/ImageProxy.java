package com.example.headfirst.proxy.image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.net.URL;

public class ImageProxy implements Icon {

    ImageIcon imageIcon;
    URL imageURL;

    State notLoaded;
    State loaded;
    Thread retrievalThread;
    boolean retrieving = false;

    State state = notLoaded;




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
        state.paintIcon(c,g,x,y);
        /*if (imageIcon != null){
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
        }*/
    }

    public ImageProxy(URL imageURL) {
        this.imageURL = imageURL;
        notLoaded = new ImageNotLoaded(this);
        loaded = new ImageLoaded(this);
        state = notLoaded;
    }

    public State getNotLoaded() {
        return notLoaded;
    }

    public State getLoaded() {
        return loaded;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public Thread getRetrievalThread() {
        return retrievalThread;
    }

    public void setRetrievalThread(Thread retrievalThread) {
        this.retrievalThread = retrievalThread;
    }

    public boolean isRetrieving() {
        return retrieving;
    }

    public void setRetrieving(boolean retrieving) {
        this.retrieving = retrieving;
    }
}
