package com.company;

public class Cliente1 implements java.lang.Runnable{

    private Banco banco;

    public Cliente1(Banco b){
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
