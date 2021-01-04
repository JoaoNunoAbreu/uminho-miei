package com.company;

public class Producer implements Runnable{

    private Warehouse wh;

    public Producer(Warehouse wh){
        this.wh = wh;
    }

    public void run() {
        try{
            this.wh.supply("item1",1);
            Thread.sleep(3000);
            this.wh.supply("item2",1);
            Thread.sleep(3000);
            this.wh.supply("item3",1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
