package com.company;

import java.util.HashMap;

public class Warehouse {

    private HashMap<String,Item> stock;

    public Warehouse() {
        this.stock = new HashMap<>();
        this.stock.put("item1",new Item(0));
        this.stock.put("item2",new Item(0));
        this.stock.put("item3",new Item(0));
    }

    public void supply(String item, int quantity){
        this.stock.get(item).supply(quantity);
    }

    public void consume(String [] items) throws InterruptedException {
        for(String str: items)
            this.stock.get(str).consume();
    }

    public String toString(){
        return this.stock.toString();
    }
}
