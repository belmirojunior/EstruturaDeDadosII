package br.ufms.arvore.controller;

import br.ufms.arvore.app.model.TreeFileModel;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author rafael
 */
public final class TreeController {

    public final TreeFileModel root;

    public TreeController(String diretorio) throws IOException {
        root = new TreeFileModel(diretorio);
        AbastercerArvore(root);

    }

    public void AbastercerArvore(TreeFileModel raiz) {
        if (raiz.getFilethis().isDirectory()) {
            raiz.setTamanho(raiz.getFilethis().length());
            raiz.setTamanho(getDirectorySize(raiz.getFilethis()));
            File arrayFiles[] = raiz.getFilethis().listFiles();
            raiz.setTreeDict(arrayFiles.length);
            for (int i = 0; i < raiz.getTreeDict().length; i++) {
                TreeFileModel temp = new TreeFileModel(arrayFiles[i].getAbsolutePath());
                AbastercerArvore(temp);
                raiz.setTreeDict(i, temp);
            }
        }
    }

    public static long getDirectorySize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                length += file.length();
            } else {
                length += getDirectorySize(file);
            }
        }
        return length;
    }

    public void imprimirTreePreOrdem(TreeFileModel treefile, String concatenar) throws IOException {
        if (treefile.getFilethis().isDirectory()) {
            System.out.println("↳" + treefile.getNome() + " ➣ " + treefile.getTamanho() + " kbytes ");
            TreeFileModel trees[] = treefile.getTreeDict();
            concatenar += "   ";
            for (TreeFileModel tree : trees) {
                if (!(tree.getFilethis().isHidden())) {
                    System.out.print(concatenar);
                    imprimirTreePreOrdem(tree, concatenar);
                }
            }

        } else {
            System.out.println("➜" + treefile.getNome() + " ➣ " + treefile.getTamanho() + " kbytes ");
        }
    }

    public void imprimirTreePosOrdem(TreeFileModel treefile, String concatenar) throws IOException {
        if (treefile.getFilethis().isDirectory()) {
            concatenar += "   ";
            for (TreeFileModel tree : treefile.getTreeDict()) {
                if (!(tree.getFilethis().isHidden())) {
                    imprimirTreePosOrdem(tree, concatenar);
                    System.out.print(concatenar);
                }
            }
            System.out.println("↳" + treefile.getNome() + " ➣ " + treefile.getTamanho() + " kbytes ");
        } else {
            System.out.println("➜" + treefile.getNome() + " ➣ " + treefile.getTamanho() + " kbytes ");
        }

    }

    public void imprimirTreeOrdemSimetrica(TreeFileModel treefile, String concatenar) throws IOException {
        if (treefile.getFilethis().isDirectory()) {
            concatenar += "   ";
            for (TreeFileModel tree : treefile.getTreeDict()) {
                if (!(tree.getFilethis().isHidden())) {
                    imprimirTreePosOrdem(tree, concatenar);
                    System.out.print(concatenar);
                }
            }
            System.out.println("↳" + treefile.getNome() + " ➣ " + treefile.getTamanho() + " kbytes ");
        } else {
            System.out.println("➜" + treefile.getNome() + " ➣ " + treefile.getTamanho() + " kbytes ");
        }

    }
}
