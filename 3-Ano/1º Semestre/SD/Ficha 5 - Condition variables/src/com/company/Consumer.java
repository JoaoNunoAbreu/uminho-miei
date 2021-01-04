package com.company;

public class Consumer implements Runnable{

    private Warehouse wh;

    public Consumer(Warehouse wh){
        this.wh = wh;
    }

    public void run(){
        try {
            this.wh.consume(new String []{"item1", "item2", "item3"});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
