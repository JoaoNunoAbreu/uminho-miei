package com.company;

public class Consumidor implements java.lang.Runnable{

    private BoundedBuffer buff;

    public Consumidor(BoundedBuffer b){
        this.buff = b;
    }

    public void run(){
        for(int i = 0; i < 20; i++) {
            try {
                buff.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}