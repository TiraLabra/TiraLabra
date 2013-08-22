
import suunnistajat.SuunnistajaAStar;
import verkko.Labyrintti;
import verkko.Solmu;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import fi.helsinki.cs.tmc.edutestutils.Reflex.ClassRef;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodAndReturnType;
import fi.helsinki.cs.tmc.edutestutils.Reflex.MethodRef1;
import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SuunnistajaAStarTest {

    private Labyrintti laby;
    private Solmu solmuSeina;
    private Solmu solmuLahto;
    private Solmu solmuMaali1;

    public SuunnistajaAStarTest() {
    }

    @Before
    public void setUp() {
        laby = new Labyrintti("labyTest5x5");
        solmuLahto = laby.verkko[0][0];
        solmuMaali1 = laby.verkko[2][0];

    }

    @Test
    public void test() throws NoSuchMethodException, Throwable {
        ClassRef<SuunnistajaAStar> suunnistajaASarClass = Reflex.reflect(SuunnistajaAStar.class);
        SuunnistajaAStar ast = suunnistajaASarClass.constructor().taking(Solmu.class, Solmu.class, Labyrintti.class).invoke(solmuLahto, solmuMaali1, laby);
        MethodRef1<SuunnistajaAStar, Integer, Solmu> heuristiikkaMethod = suunnistajaASarClass.method(ast, "heuristiikka").returning(int.class).taking(Solmu.class);
        int tulos =  heuristiikkaMethod.invokeOn(ast, laby.verkko[1][0]);
        assertEquals(1, tulos);
        tulos =  heuristiikkaMethod.invokeOn(ast, laby.verkko[0][1]);
        assertEquals(1000003, tulos);
        
        tulos =  heuristiikkaMethod.invokeOn(ast, laby.verkko[2][4]);
        assertEquals(4, tulos);
        
        tulos =  heuristiikkaMethod.invokeOn(ast, laby.verkko[0][4]);
        assertEquals(6, tulos);
        
        tulos =  heuristiikkaMethod.invokeOn(ast, laby.verkko[4][4]);
        assertEquals(6, tulos);        
    }
}
