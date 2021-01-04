package com.company;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        int NUM_CONTAS = 10;
        int NUM_THREADS = 2;
        Thread[] threads = new Thread[NUM_THREADS];
        Banco b = new Banco(NUM_CONTAS);

        int id1 = b.createAccount(1000);

        threads[0] = new Thread(new Cliente1(b));
        threads[1] = new Thread(new Cliente2(b));

        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].start();
        }
        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].join();
        }
    }
}
