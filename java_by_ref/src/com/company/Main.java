package com.company;

public class Main {

    public static void main(String[] args) {
        MyNumber x = new MyNumber();
        x.x = 8;
        change(x);
        System.out.println(x.x);
    }

    static void change(MyNumber x){
        x.x = 13;
    }

    static class MyNumber{
        int x;
    }
}
