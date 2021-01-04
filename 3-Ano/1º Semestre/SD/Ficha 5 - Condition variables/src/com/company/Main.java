package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int NUM_THREADS = 2;
        Thread[] threads = new Thread[NUM_THREADS];

        Warehouse wh = new Warehouse();
        threads[0] = new Thread(new Producer(wh));
        threads[1] = new Thread(new Consumer(wh));

        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].start();
        }
        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].join();
        }

        System.out.println(wh.toString());
    }
}
