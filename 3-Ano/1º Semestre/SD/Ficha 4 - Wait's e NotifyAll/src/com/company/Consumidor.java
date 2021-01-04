package com.company;

public class Consumidor implements java.lang.Runnable{

    private BoundedBuffer buff;
    private int numThreads;
    private int tc;
    private int total_ops;

    public Consumidor(BoundedBuffer b, int C,int tc, int total_ops){
        this.buff = b;
        this.numThreads = C;
        this.tc = tc;
        this.total_ops = total_ops;
    }

    public void run(){
        for(int i = 0; i < this.total_ops/this.numThreads; i++) {
            try {
                buff.get();
                //Thread.sleep(tc);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}