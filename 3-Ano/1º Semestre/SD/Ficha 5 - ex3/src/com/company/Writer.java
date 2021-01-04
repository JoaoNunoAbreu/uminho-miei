package com.company;

public class Writer implements Runnable{

    private RWLock rwlock;

    public Writer(RWLock rwlock){
        this.rwlock = rwlock;
    }

    public void run(){
        try {
            rwlock.writeLock();
            //System.out.println("Entrou no lock do writer");
            Thread.sleep(1000);
            rwlock.writeUnlock();
            //System.out.println("Saiu no lock do writer");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
