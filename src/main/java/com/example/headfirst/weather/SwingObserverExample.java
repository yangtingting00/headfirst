package com.example.headfirst.weather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingObserverExample {
    JFrame frame;

    public static void main(String[] args) {
        SwingObserverExample swingObserverExample = new SwingObserverExample();
        swingObserverExample.go();
    }

    public void go(){
        frame = new JFrame();
        JButton button = new JButton("Should I do it?");
        button.addActionListener(new AngelListener());
        button.addActionListener(new DevilListener());
        frame.getContentPane().add(BorderLayout.CENTER,button);
    }

    class AngelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Don't do it,you might regret it!");
        }
    }
    class DevilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Come on,do it!");
        }
    }
}
