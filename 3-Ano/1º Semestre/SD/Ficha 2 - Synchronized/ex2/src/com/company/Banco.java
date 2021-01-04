package com.company;

public class Banco{

    private Conta[] contas;

    public Banco(int numContas){
        this.contas = new Conta[numContas];
        for(int i = 0; i < numContas; i++){
            this.contas[i] = new Conta();
        }
    }

    public double consulta(int n){
        return this.contas[n].consultar();
    }

    public void credito(int quantia, int n){
        this.contas[n].depositar(quantia);
    }

    public void debito(int quantia, int n){
        this.contas[n].levantar(quantia);
    }

    public void transferir(int quantia, int n1, int n2){ // Não usar synchronized pois os métodos usados já usam
        int conta_menor_id = Math.min(n1,n2);
        int conta_maior_id = Math.max(n1,n2);
        synchronized (contas[conta_menor_id]){
            synchronized (contas[conta_maior_id]){
                this.debito(quantia,n1);
                this.credito(quantia,n2);
            }
        }
    }
}