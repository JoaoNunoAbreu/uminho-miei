package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Banco implements Bank{

    private Map<Integer,Conta> contas;
    private ReentrantLock lockBanco = new ReentrantLock();

    private int lastID = 0;

    public Banco(int numContas){
        this.contas = new HashMap<>(numContas);
    }

    public double consulta(int n){
        return this.contas.get(n).consultar();
    }

    public void credito(double quantia, int n){
        this.contas.get(n).depositar(quantia);
    }

    public void debito(double quantia, int n){
        this.contas.get(n).levantar(quantia);
    }

    public void transferir(double quantia, int n1, int n2) throws InvalidAccount, NotEnoughFunds{
        int conta_menor_id = Math.min(n1,n2);
        int conta_maior_id = Math.max(n1,n2);
        synchronized (contas.get(conta_menor_id)){
            synchronized (contas.get(conta_maior_id)){
                this.debito(quantia,n1);
                this.credito(quantia,n2);
            }
        }
    }

    public int createAccount(double saldo){
        this.lockBanco.lock();
        int id = lastID++;
        Conta c = new Conta(id,saldo);
        this.contas.put(id,c);
        this.lockBanco.unlock();

        return id;
    }

    public double closeAccount(int id) throws InvalidAccount{
        try{
            this.lockBanco.lock();
            if(this.contas.containsKey(id)){
                Conta c = this.contas.get(id);
                c.lock();
                double saldo = c.consultar();
                this.contas.remove(id);
                c.unlock();
                this.lockBanco.unlock();
                return saldo;
            }
            else {
                this.lockBanco.unlock();
                throw new InvalidAccount("Conta " + id + " inválida.");
            }
        }
        catch(Exception e){
            throw new InvalidAccount("Conta Inválida!");
        }
    }

    public double totalBalance(int[] accounts){
        double saldoTotal = 0;
        ArrayList<Integer> contasLocked = new ArrayList<>(accounts.length);
        this.lockBanco.lock();
        for(int i =0 ; i < accounts.length; i++){
            int id = accounts[i];
            if(this.contas.containsKey(id)){
                this.contas.get(id).lock();
                contasLocked.add(id);
            }
            else{
                this.lockBanco.unlock();
            }
        }
        return saldoTotal;
    }
}