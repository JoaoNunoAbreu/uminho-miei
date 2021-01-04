package com.company;

public class Produtor implements java.lang.Runnable{

    private BoundedBuffer buff;

    public Produtor(BoundedBuffer b){
        this.buff = b;
    }

    public void run(){
        for(int i = 0; i < 20; i++) {
            try {
                buff.put(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}