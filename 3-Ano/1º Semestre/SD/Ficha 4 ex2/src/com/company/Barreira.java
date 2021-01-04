package com.company;

public class Barreira{

    private int count;
    private int limit;
    private int ronda;

    public Barreira(int n){
        this.count = 0;
        this.limit = n;
        this.ronda = 0;
    }

    private synchronized void esperar() throws InterruptedException {

        this.count++;
        int id = count;
        int myround = this.ronda;
        while(this.count < this.limit && this.ronda == myround){
            this.wait();
        }
        if(id == limit){
            this.notifyAll();
            this.ronda++;
            this.count = 0;
        }

    }
}
