/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorebinaria;

/**
 *
 * @author rafael
 * @param <T>
 */
public class Arvore<T> {

    Arbusto<T> ptr_raiz;
//    
//    procedimento Remover (Chave x, Árvore T) {
//    se T <> Nulo então
//        se x < T^.val então Remover (x, T^.Esq)
//        senão se x > T^.val então Remover (x, T^.Dir)
//        senão
//             se T^.Esq = Nulo então {
//                  tmp  T
//	   T  T^.Dir
//                  Liberar (tmp)
//             }
//             senão se T^.Dir = Nulo então {
//	   tmp  T
//	   T  T^.Esq
//                  Liberar (tmp)
//             }
//             senão T^.Val  RemoverMenorMaiores (T^.Dir)
//}
    public void remover(Integer chave,Arbusto<T> ptr){
    
    }
    public boolean buscar(Integer chave, Arbusto<T> ptr) {
    if(ptr==null) return false;
    if(chave.equals(ptr.getCogumelo())) return true;
    if(chave < ptr.getCogumelo()) return buscar(chave, ptr.getArbustoEsquerdo());
    return buscar(chave, ptr.getArbustoDireito());
    }

    public Arbusto<T> adicionar(Integer chave, Arbusto<T> ptr) {
        if (ptr == null) {
            ptr = new Arbusto<>();
            ptr.setCogumelo(chave);
        } else {
            if (chave < ptr.getCogumelo()) {
                ptr.setArbustoEsquerdo(adicionar(chave, ptr.getArbustoEsquerdo()));
            }
            if (chave > ptr.getCogumelo()) {
                ptr.setArbustoDireito(adicionar(chave, ptr.getArbustoDireito()));
            }
        }
        return ptr_raiz = ptr;
    }
}
