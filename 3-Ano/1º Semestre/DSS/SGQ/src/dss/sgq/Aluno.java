/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dss.sgq;

/**
 *
 * @author joaonunoabreu
 */
public class Aluno {
    private int numero;
    private String nome;
    private String curso;
    private int ano;
    private String morada;
    
    public Aluno(int n, String nome, String curso, int ano, String morada){
        this.numero = n;
        this.nome = nome;
        this.curso = curso;
        this.ano = ano;
        this.morada = morada;
    }
    
    public int getNumero(){
        return this.numero;
    }
}
