
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PakkaajaTest {
    Pakkaaja pakkaaja;
    
    @Before
    public void setUp() {
        pakkaaja = new Pakkaaja();
    }
    
    @Test
    public void pakkausOnnistuu() {
        assertEquals("100000011111110101011", pakkaaja.pakkaa("Addddööö 3"));
    }
    
    @Test
    public void tyhjaaTiedostoaEiPakata() {
        assertNull(pakkaaja.pakkaa(""));
    }
}
