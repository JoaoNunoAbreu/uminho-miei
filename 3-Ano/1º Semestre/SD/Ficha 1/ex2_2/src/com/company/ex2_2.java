package com.company;

import java.lang.Thread;

public class ex2_2 implements java.lang.Runnable {

    private int n;
    private static int contador;

    public ex2_2(int n, int c) {
        this.n = n;
        this.contador = c;
    }

    public void run() {
        for (int i = 0; i < n; i++)
            this.contador++;
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        int I = 200;
        Thread[] threads = new Thread[N];
        Counter c = new Counter();

        for (int i = 0; i < N; i++)
            threads[i] = new Thread(new ex2_2(I, 0));

        for (int i = 0; i < N; i++) {
            threads[i].start();
        }
        for (int i = 0; i < N; i++) {
            threads[i].join();
        }

        System.out.println(contador);
    }
}
