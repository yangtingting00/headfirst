package com.example.headfirst.starbuzz;

import java.io.*;

public class InputTest {
    public static void main(String[] args) {
        int c;
        try {
            InputStream i = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("tt")));
            while ((c = i.read())>=0){
                System.out.println((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
