package br.ufms.b.model;

import java.io.*;
//cria uma escrutura que representa uma pagina
public class Bpagina  {

    private int chave; //valor 

    public Bpagina(int chave) {
        this.chave = chave; // capsula
    }
    public int equals(Bpagina it) {//metodo que comparar Bpagina
     
        if (this.chave < it.chave) {//se chave this menor que chave it
            return -1;//return return -1, menor
        } else if (this.chave > it.chave) {//se chave this maior que chave it
            return 1;// return 1 maior
        }
        return 0;//return 0 se for igual
    }

     //Metodo para trocar o valor da chave
    public void alteraChave(Object chave) {
        Integer ch = (Integer) chave;
        this.chave = ch.intValue();
    }
     //Metodo para dar um get na chave
    public Object recuperaChave() {
        return new Integer(this.chave);
    }
    //imprimi oque é uma chave
    @Override
    public String toString() {
        return "" + this.chave;
    }
    //
    public void gravaArq(RandomAccessFile arq) throws IOException {
        arq.writeInt(this.chave);
    }

    public void leArq(RandomAccessFile arq) throws IOException {
        this.chave = arq.readInt();
    }
    // tamanho estatico maximo igual 4
    public static int tamanho() {
        return 4;
    }
}
