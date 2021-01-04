package com.company;

public class Cliente1 implements java.lang.Runnable{

    private Banco banco;

    public Cliente1(Banco b){
        this.banco = b;
    }

    public void run(){
        banco.transferir(1000,0,1);
    }

}
