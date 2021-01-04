package com.company;

public class Counter {

    private int n;

    public Counter(){
        this.n = 0;
    }

    public synchronized void increment(){
        this.n++;
    }

    // Ou

    public void increment2(){
        synchronized (this){
            this.n++;
            Thread.currentThread().getName();
        }
    }

    public synchronized int getNum(){
        return this.n;
    }
}
