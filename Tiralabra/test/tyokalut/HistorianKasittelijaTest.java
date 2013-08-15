//package tyokalut;
//
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import osat.Laatikko;
//import osat.Lava;
//
///**
// * 
// *
// * @author albis
// */
//public class HistorianKasittelijaTest {
//    private HistorianKasittelija historia;
//    private AVLsolmu juuri;
//    
//    public HistorianKasittelijaTest() {
//    }
//
//    @Before
//    public void setUp() {
//        historia = new HistorianKasittelija();
//        
//        juuri = null;
//        
//        historia.AVLlisays(juuri, new Laatikko(20, 20, 20, 111111111111L),
//                new KasvavaLista(), new Lava(80, 120, 120));
//
//        historia.AVLlisays(juuri, new Laatikko(40, 40, 40, 222222222222L),
//                new KasvavaLista(), new Lava(100, 120, 120));
//        
//        historia.AVLlisays(juuri, new Laatikko(60, 60, 60, 333333333333L),
//                new KasvavaLista(), new Lava(80, 120, 200));
//        
//        historia.AVLlisays(juuri, new Laatikko(15, 15, 15, 100000000000L),
//                new KasvavaLista(), new Lava(100, 120, 200));
//    }
//    
//    @Test
//    public void asettuvatOikeaanJarjestykseen() {
//        
//        assertEquals(juuri.getVasen().getKey(), 100000000000L);
//        assertEquals(juuri.getKey(), 111111111111L);
//    }
//
//    @Test
//    public void hakeeKorkeudenOikein() {
//        
//    }
//}
