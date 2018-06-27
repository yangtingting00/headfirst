package com.example.headfirst.proxy.image;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageNotLoaded implements State {
    ImageProxy imageProxy;



    public ImageNotLoaded(ImageProxy imageProxy) {
        this.imageProxy = imageProxy;
    }
    public void paintIcon(final Component c, Graphics g, int x, int y){
        g.drawString("Loading CD cover, please wait ...", x+300,y+190);
        if (!imageProxy.isRetrieving()){
            imageProxy.setRetrieving(true);
            imageProxy.setRetrievalThread(new Thread(new Runnable() {
                @Override
                public void run() {
                    imageProxy.setImageIcon(new ImageIcon(imageProxy.getImageURL(),"CD Cover"));
                    imageProxy.setState(imageProxy.getLoaded());    //必须在这里转换状态，因为repaint调用的还是proxy.paintIcon,如果状态未切换调用的就还是未初始化的方法进入死循环
                    c.repaint();
                }
            }));
            imageProxy.getRetrievalThread().start();
        }

    }


}
