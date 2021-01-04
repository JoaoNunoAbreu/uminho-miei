package com.company;

import java.lang.Thread;

public class ex1 implements java.lang.Runnable{

    private int n;

    public ex1(int n){
        this.n = n;
    }

    public void run(){
        for(int i = 0; i < n; i++)
            System.out.println(i);
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 2;
        int I = 20;
        Thread[] threads = new Thread[N];

        for(int i = 0; i < N; i++)
            threads[i] = new Thread(new ex1(I));

        for(int i = 0; i < N; i++){
            threads[i].start();
        }
        for(int i = 0; i < N; i++){
            threads[i].join();
        }
    }
}
