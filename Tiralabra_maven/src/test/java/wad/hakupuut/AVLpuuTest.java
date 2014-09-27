package wad.hakupuut;


import wad.solmu.Solmu;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AVLpuuTest {
    
    AVLpuu avl;
    
    public AVLpuuTest() {
    }
    
    @Before
    public void setUp() {
        avl = new AVLpuu();
    }
    
    @Test
    public void tyhjapuu() {
        assertNull(avl.hae(""));
    }
    
    @Test
    public void yhdenLisays() {
        avl.lisaa("minut lisättiin");
        assertEquals("minut lisättiin", avl.hae("minut lisättiin").getArvo());
    }
    
    @Test
    public void yhdenKorkeus() {
        avl.lisaa("korkeus 0");
        assertEquals(0, avl.korkeus(avl.hae("korkeus 0")));
    }
    
    @Test
    public void monenLisays() {
        for(int i = 0; i<12; i++) avl.lisaa(i);
        
        assertEquals("7{3{1{0,2},5{4,6}},9{8,10{[],11}}}", avl.toString());
    }
    
    @Test
    public void lehdenPoistoIlmanKiertoa() {
        for(int i = 11; i>-1; i--) avl.lisaa(i);
        
        avl.poista(0);
        
        assertEquals("4{2{1,3},8{6{5,7},10{9,11}}}", avl.toString());
    }
    
    @Test
    public void lehdenPoistoOikeaKierto() {
        for(int i = 11; i>-1; i--) avl.lisaa(i);
        avl.poista(3);
        
        assertEquals("4{1{0,2},8{6{5,7},10{9,11}}}", avl.toString());
    }
    
    @Test
    public void kaksilapsisenPoistoOikeaKierto() {
        for(int i = 11; i>-1; i--) avl.lisaa(i);
        avl.poista(2);
        
        assertEquals("4{1{0,3},8{6{5,7},10{9,11}}}", avl.toString());
    }
    
    @Test
    public void juurenPoisto() {
        for(int i = 11; i>-1; i--) avl.lisaa(i);
        avl.poista(4);
        
        assertEquals("5{2{1{0,[]},3},8{6{[],7},10{9,11}}}", avl.toString());
    }
    
    @Test
    public void vasenKaantoKunPoistetaan() {
        for(int i=1; i<5;i++) avl.lisaa(i);
        avl.poista(1);
        assertEquals("3{2,4}",avl.toString());
    }
    
    @Test
    public void oikeaKaantoKunPoistetaan() {
        for(int i=4; i>0;i--) avl.lisaa(i);
        avl.poista(4);
        assertEquals("2{1,3}",avl.toString());
    }

    @Test
    public void maxOikein() {
        assertEquals(4, avl.max(4,1));
    }
}
