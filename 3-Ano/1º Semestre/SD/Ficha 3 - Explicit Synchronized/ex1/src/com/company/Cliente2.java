package com.company;

public class Cliente2 implements java.lang.Runnable{

    private Banco banco;

    public Cliente2(Banco b){
        this.banco = b;
    }

    public void run(){
        try{
            System.out.println("Conta com " + banco.closeAccount(0) + " fechada");
        }
        catch(InvalidAccount e){
            System.out.println(e);
        }
    }

}
