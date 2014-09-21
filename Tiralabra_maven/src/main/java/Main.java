
import wad.hakupuut.BinaarinenHakupuu;
import wad.solmu.Solmu;

//käsitestailua
public class Main {
    public static void main(String[] args) {
        BinaarinenHakupuu bst = new BinaarinenHakupuu();
        bst.lisaa(4);
        bst.lisaa(3);
        bst.lisaa(2);
        bst.lisaa(5);
        /*bst.lisaa(12);
        bst.lisaa(13);
        bst.lisaa(10);
        bst.lisaa(6);*/
        System.out.println(bst);
        bst.poista(15);
        System.out.println(bst);
        bst.poista(15);
        System.out.println(bst);
    }        
}
