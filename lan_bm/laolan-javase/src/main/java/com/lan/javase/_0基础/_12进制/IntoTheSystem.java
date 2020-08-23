package com.lan.javase._0基础._12进制;

public class IntoTheSystem {

    public static void main(String[] args) {
        int i1 = 0x11;
        System.out.println("i1:" + Integer.toBinaryString(i1));

        int i2 = 021;
        System.out.println("i2:" + Integer.toBinaryString(i2));

        char c = 0xffff;
        System.out.println("c:" + Integer.toBinaryString(c));
    }


}
