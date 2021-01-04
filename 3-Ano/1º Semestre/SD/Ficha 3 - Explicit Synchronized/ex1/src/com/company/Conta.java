package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class Conta{

    private int id;
    private double valor;

    private ReentrantLock contaLock = new ReentrantLock();

    public Conta(int id, double valor){
        this.id = id;
        this.valor = valor;
    }

    public void depositar(double valor){
        this.valor += valor;
    }

    public void levantar(double valor){
        this.valor -= valor;
    }

    public double consultar(){
        return this.valor;
    }

    public void lock(){
        this.contaLock.lock();
    }
    public void unlock(){
        this.contaLock.unlock();
    }
}
