package br.ufms.arvore.controller;

import br.ufms.arvore.app.model.TreeFileModel;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author rafael
 */
public final class TreeController {
    private final TreeFileModel root;
    public TreeController(String diretorio) throws IOException {
        root = new TreeFileModel(diretorio);
        AbastercerArvore(root);
        imprimirTree(root, "");
    }

    public void AbastercerArvore(TreeFileModel raiz) {
        if (raiz.getFilethis().isDirectory()) {
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

    private void imprimirTree(TreeFileModel treefile, String concatenar) throws IOException {
        if (treefile.getTreeDict() == null) {
            System.out.println("➜" + treefile.getNome() + " ➣ " + treefile.getTamanho() + " kbytes ");
        } else {
            System.out.println("↳" + treefile.getNome() + " ➣ " + treefile.getTamanho() + " kbytes ");
            TreeFileModel trees[] = treefile.getTreeDict();
            concatenar += "   ";
            for (TreeFileModel tree : trees) {
                if (!(tree.getFilethis().isHidden())) {
                    System.out.print(concatenar);
                    imprimirTree(tree, concatenar);
                }
            }
        }

    }

}
