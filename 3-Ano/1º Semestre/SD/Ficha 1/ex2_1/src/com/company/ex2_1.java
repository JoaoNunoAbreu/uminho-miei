package com.company;

import java.lang.Thread;

public class ex2_1 implements java.lang.Runnable{

    private int n;
    private Counter counter;

    public ex2_1(int n, Counter c){
        this.n = n;
        this.counter = c;
    }

    public void run(){
        for(int i = 0; i < n; i++)
            this.counter.increment();
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        int I = 200;
        Thread[] threads = new Thread[N];
        Counter c = new Counter();

        for(int i = 0; i < N; i++)
            threads[i] = new Thread(new ex2_1(I,c));

        for(int i = 0; i < N; i++){
            threads[i].start();
        }
        for(int i = 0; i < N; i++){
            threads[i].join();
        }

        System.out.println(c.getNum());
    }
}
