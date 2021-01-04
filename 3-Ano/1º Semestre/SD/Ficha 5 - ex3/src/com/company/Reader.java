package com.company;

public class Reader implements Runnable{

    private RWLock rwlock;

    public Reader(RWLock rwlock){
        this.rwlock = rwlock;
    }

    public void run(){
        try {
            rwlock.readLock();
            //System.out.println("Entrou no lock do reader");
            Thread.sleep(1000);
            rwlock.readUnlock();
            //System.out.println("Saiu no lock do reader");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
