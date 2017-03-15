package br.ufms.b.model;

import java.io.*;

public class Ppagina  {

    private int chave;

    public Ppagina(int chave) {
        this.chave = chave;
    }

    
    public int equals(Ppagina it) {
        Ppagina item = it;
        if (this.chave < item.chave) {
            return -1;
        } else if (this.chave > item.chave) {
            return 1;
        }
        return 0;
    }


    public void alteraChave(Object chave) {
        Integer ch = (Integer) chave;
        this.chave = ch.intValue();
    }

    public Object recuperaChave() {
        return new Integer(this.chave);
    }

    @Override
    public String toString() {
        return "" + this.chave;
    }

    public void gravaArq(RandomAccessFile arq) throws IOException {
        arq.writeInt(this.chave);
    }

    public void leArq(RandomAccessFile arq) throws IOException {
        this.chave = arq.readInt();
    }

    public static int tamanho() {
        return 4;
    }
}
