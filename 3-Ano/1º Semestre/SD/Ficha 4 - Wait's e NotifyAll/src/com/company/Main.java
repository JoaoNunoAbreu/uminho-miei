package com.company;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        int SIZE_ARRAY = 10;
        int tc = 500;
        int tp = 1000;
        int NUM_THREADS = 10;
        int P = 5;
        int C = NUM_THREADS - P;
        int total_ops = 100;
        double maxDebito = 0;
        int maxProd = 0;

        Thread[] threads = new Thread[NUM_THREADS];
        BoundedBuffer b = new BoundedBuffer(SIZE_ARRAY);

        long startTime = System.currentTimeMillis()/1000;

        int filled = 0;
        for(; filled < C; filled++){
            threads[filled] = new Thread(new Consumidor(b,C,tc,total_ops));
        }

        for(int j = filled;j < C + P; j++){
            threads[j] = new Thread(new Produtor(b,P,tp,total_ops));
        }

        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].start();
        }
        for(int i = 0; i < NUM_THREADS; i++){
            threads[i].join();
        }

        long endTime = System.currentTimeMillis()/1000;

        System.out.println("Final é: " + b.toString());
    }
}
