package com.company;

public class Cliente2 implements java.lang.Runnable{

    private Banco banco;

    public Cliente2(Banco b){
        this.banco = b;
    }

    public void run(){
        banco.transferir(1000,1,0);
    }

}
