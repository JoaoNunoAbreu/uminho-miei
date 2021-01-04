package com.company;

public class Produtor implements java.lang.Runnable{

    private BoundedBuffer buff;
    private int numThreads;
    private int tp;
    private int total_ops;

    public Produtor(BoundedBuffer b, int P, int tp,int total_ops){
        this.buff = b;
        this.numThreads = P;
        this.tp = tp;
        this.total_ops = total_ops;
    }

    public void run(){
        for(int i = 0; i < this.total_ops/this.numThreads; i++) {
            try {
                buff.put(10);
                //Thread.sleep(this.tp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}