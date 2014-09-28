
import wad.hakupuut.AVLpuu;
import wad.hakupuut.BinaarinenHakupuu;
import wad.hakupuut.SplayPuu;
import wad.solmu.Solmu;

//käsitestailua
public class Main {
    public static void main(String[] args) {
        // BST käsitestausta
        /*
        BinaarinenHakupuu bst = new BinaarinenHakupuu();
        bst.lisaa(4);
        bst.lisaa(3);
        bst.lisaa(2);
        bst.lisaa(5);
        bst.lisaa(12);
        bst.lisaa(13);
        bst.lisaa(10);
        bst.lisaa(6);
        System.out.println(bst);
        bst.poista(4);
        //System.out.println(bst);
        bst.poista(5);
        //System.out.println(bst); */
    
        //AVL käsitestausta
        /*
        AVLpuu avl = new AVLpuu();
        for(int i = 11; i>-1; i--) avl.lisaa(i);
        System.out.println(avl);
        avl.poista(4);
        System.out.println(avl.toString()); 
        avl.lisaa(2);
        System.out.println(avl);
        avl.lisaa(1);
        System.out.println(avl);
        avl.lisaa(3);
        System.out.println(avl);
        avl.lisaa(4);
        System.out.println(avl);
        avl.poista(2);
        System.out.println(avl);
        avl.lisaa(7);
        System.out.println(avl);
        avl.lisaa(10);
        System.out.println(avl);
        avl.lisaa(12);
        System.out.println(avl);
        avl.lisaa(1);
        System.out.println(avl);
        avl.lisaa(4);
        System.out.println(avl);
        avl.lisaa(6);
        System.out.println(avl);
        avl.lisaa(9);
        System.out.println(avl);
        avl.lisaa(0);
        System.out.println(avl);
        avl.lisaa(2);
        System.out.println(avl);
        avl.poista(12);
        System.out.println(avl);*/

        //Splay-puu käsitestausta
        /* ONPA OMITUINEN */
        SplayPuu splay = new SplayPuu();
        for(int i = 1; i<7; i++) splay.lisaa(i);
        //splay.hae(1);
        //splay.poista(1);
        splay.poista(4);
        System.out.println(splay);
        
    }        
}
