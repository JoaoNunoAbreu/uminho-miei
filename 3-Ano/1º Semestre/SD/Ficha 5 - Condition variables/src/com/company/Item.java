package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Item {

    private ReentrantLock lock = new ReentrantLock();
    private Condition isEmpty = lock.newCondition();
    private int quantity;

    public Item(int quantity){
        this.quantity = quantity;
    }

    public void supply(int quantity){
        this.lock.lock();
        this.quantity += quantity;
        this.isEmpty.signalAll(); // podia ser signal() se a quantidade adicionada fosse = 1
        this.lock.unlock();
    }

    public void consume() throws InterruptedException {
        this.lock.lock();
        while(this.quantity == 0)
            this.isEmpty.await();

        this.quantity--;
        this.lock.unlock();
    }

    public String toString(){
        return "" + this.quantity;
    }
}
