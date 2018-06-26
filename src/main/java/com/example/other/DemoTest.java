package com.example.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DemoTest extends Demo {
    public static void display(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Date end = format.parse("2018-06-09");
            System.out.println(end.after(now));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        try {
            /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Date end = format.parse("2018-06-09 00:00:00");
            System.out.println(!end.before(now));*/
            Random random = new Random(System.currentTimeMillis());
            System.out.println(random.nextInt(10));
        /*} catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
}
