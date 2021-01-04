package com.company;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        int SIZE_ARRAY = 10;
        int NUM_THREADS = 2;

        Thread[] threads = new Thread[NUM_THREADS];
        BoundedBuffer b = new BoundedBuffer(SIZE_ARRAY);

        threads[0] = new Thread(new Consumidor(b));
        threads[1] = new Thread(new Produtor(b));

        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].start();
        }
        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].join();
        }

        System.out.println("Final é: " + b.toString());
    }
}
