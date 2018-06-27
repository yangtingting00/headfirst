package com.example.headfirst.proxy.image;

import java.awt.*;

public interface State {
    void paintIcon(final Component c, Graphics g, int x, int y);
}
