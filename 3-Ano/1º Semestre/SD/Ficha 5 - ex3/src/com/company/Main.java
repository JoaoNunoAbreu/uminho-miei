package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int NUM_THREADS = 30;
        Thread[] threads = new Thread[NUM_THREADS];

        RWLock rwlock = new RWLock();

        for(int i = 0; i < 15; i++){
            threads[i] = new Thread(new Reader(rwlock));
        }
        for(int i = 0; i < 15; i++){
            threads[i+15] = new Thread(new Writer(rwlock));
        }
        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].start();
        }
        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].join();
        }
    }
}
