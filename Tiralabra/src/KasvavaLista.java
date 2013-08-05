/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albis
 */
public class KasvavaLista {
    private String[] lista;
    
    public KasvavaLista() {
        lista = new String[10];
    }
    
    public void lisaa(String lisattava) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                lista[i] = lisattava;
                return;
            }
        }
        
        String[] kopio = new String[lista.length];
        for (int i = 0; i < lista.length; i++) {
            kopio[i] = lista[i];
        }
        
        lista = new String[kopio.length+10];
        for (int i = 0; i < kopio.length; i++) {
            lista[i] = kopio[i];
        }
        
        lista[kopio.length] = lisattava;
    }
    
    public int length() {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                return i+1;
            }
        }
        return lista.length;
    }
    
    public KasvavaLista kopioi() {
        KasvavaLista kopio = new KasvavaLista();
        
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                return kopio;
            }
            
            kopio.lisaa(lista[i]);
        }
        
        return kopio;
    }
}
