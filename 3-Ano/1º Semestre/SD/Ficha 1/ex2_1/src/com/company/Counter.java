package com.company;

public class Counter {

    private int n;

    public Counter(){
        this.n = 0;
    }

    public void increment(){
        this.n++;
    }

    public int getNum(){
        return this.n;
    }
}
