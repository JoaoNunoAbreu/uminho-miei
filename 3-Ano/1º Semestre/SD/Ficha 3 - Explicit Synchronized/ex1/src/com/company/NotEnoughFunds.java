package com.company;

public class NotEnoughFunds extends Exception{
    public NotEnoughFunds(String msg){
        super(msg);
    }
}
