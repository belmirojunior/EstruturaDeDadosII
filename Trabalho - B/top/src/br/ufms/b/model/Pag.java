package br.ufms.b.model;

import java.io.*;

public class Pag implements Item {

    private int chave;

    public Pag(int chave) {
        this.chave = chave;
    }

    @Override
    public int compara(Item it) {
        Pag item = (Pag) it;
        if (this.chave < item.chave) {
            return -1;
        } else if (this.chave > item.chave) {
            return 1;
        }
        return 0;
    }

    @Override
    public void alteraChave(Object chave) {
        Integer ch = (Integer) chave;
        this.chave = ch.intValue();
    }

    @Override
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
