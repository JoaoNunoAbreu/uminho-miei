package com.company;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        int NUM_CONTAS = 10;
        Thread[] threads = new Thread[2];
        Banco b = new Banco(NUM_CONTAS);

        b.credito(1000,0);

        threads[0] = new Thread(new Cliente1(b));
        threads[1] = new Thread(new Cliente2(b));

        for(int i = 0; i < 2; i++){
            threads[i].start();
        }
        for(int i = 0; i < 2; i++){
            threads[i].join();
        }

        System.out.println("Conta 0 = " + b.consulta(0));
        System.out.println("Conta 1 = " + b.consulta(1));
    }
}
