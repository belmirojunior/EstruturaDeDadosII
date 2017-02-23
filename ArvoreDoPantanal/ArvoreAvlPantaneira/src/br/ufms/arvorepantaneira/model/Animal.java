package br.ufms.arvorepantaneira.model;

import br.ufms.arvorepantaneira.controller.ArmazenaComID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
public class Animal {

    private Animal esquerda = null;
    private Animal direita = null;
    private Animal pai = null;
    private int chave;
    private int balanceamento;
    private int indice;
    private int idade;
    private String sexo;
    private String cor;
    private String nome;

    public Animal(String nome, int idade, String sexo, String cor) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.cor = cor;
        this.chave = ArmazenaComID.abatece(Math.abs(nome.hashCode()));
        this.balanceamento = 0;
    }

    public Animal(Animal a) {
        this.nome = a.nome;
        this.idade = a.idade;
        this.sexo = a.sexo;
        this.cor = a.cor;
        this.chave = a.chave;
        this.balanceamento = a.balanceamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.valueOf(chave);
    }

    public boolean equals(Animal a) {
        return a.getChave() == this.getChave();
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {

        this.chave = chave;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Animal getPai() {
        return pai;
    }

    public void setPai(Animal pai) {
        this.pai = pai;
    }

    public Animal getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Animal esquerda) {
        this.esquerda = esquerda;
    }

    public Animal getDireita() {
        return direita;
    }

    public void setDireita(Animal direita) {
        this.direita = direita;
    }

    public int getBalanceamento() {
        return balanceamento;
    }

    public void setBF(int balanceamento) {
        this.balanceamento = balanceamento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
