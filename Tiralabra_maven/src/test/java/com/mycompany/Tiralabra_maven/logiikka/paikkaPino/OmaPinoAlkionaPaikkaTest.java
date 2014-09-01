package com.mycompany.Tiralabra_maven.logiikka.paikkaPino;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hannu
 */
public class OmaPinoAlkionaPaikkaTest {

    StackPinoAlkionaPaikka javaPino;
    OmaPinoAlkionaPaikka omaPinoP;
    Paikka paikka;
    Paikka paikka1;
    Paikka paikka2;
    Paikka paikka3;
    Paikka paikka4;
    Paikka paikka5;
    Paikka paikka6;
    Paikka paikka7;
    Paikka paikka8;
    Paikka paikka9;

    public OmaPinoAlkionaPaikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        javaPino = new StackPinoAlkionaPaikka();
        omaPinoP = new OmaPinoAlkionaPaikka();

        paikka1 = new Paikka();
        paikka2 = new Paikka();
        paikka3 = new Paikka();
        paikka4 = new Paikka();
        paikka5 = new Paikka();
        paikka6 = new Paikka();
        paikka7 = new Paikka();
        paikka8 = new Paikka();

//        Paikka paikka1 = new Paikka(1, 1, 1);
//        Paikka paikka2 = new Paikka(2, 2, 2);
//        Paikka paikka3 = new Paikka(3, 3, 3);
//        Paikka paikka4 = new Paikka(4, 4, 4);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void tyhjaPinoOnTyhja() {
        assertEquals(true, omaPinoP.stackIsEmpty());
    }

    @Test
    public void manyPushAndOnePop() {
        omaPinoP.stackPush(paikka1);
        omaPinoP.stackPush(paikka2);
        omaPinoP.stackPush(paikka3);
        omaPinoP.stackPush(paikka4);

        assertEquals(paikka4, omaPinoP.stackPop());
    }

    @Test
    public void manyPushAndManyPop() {
        omaPinoP.stackPush(paikka1);
        omaPinoP.stackPush(paikka2);
        omaPinoP.stackPush(paikka3);
        paikka = omaPinoP.stackPop();
        omaPinoP.stackPush(paikka4);
        omaPinoP.stackPush(paikka4);
        omaPinoP.stackPush(paikka6);
        omaPinoP.stackPush(paikka9);
        omaPinoP.stackPush(paikka1);
        omaPinoP.stackPush(paikka5);
        omaPinoP.stackPush(paikka6);
        omaPinoP.stackPush(paikka6);
        paikka = omaPinoP.stackPop();
        paikka = omaPinoP.stackPop();
        paikka = omaPinoP.stackPop();
        paikka = omaPinoP.stackPop();

        assertEquals(paikka9, omaPinoP.stackPop());
    }

    @Test
    public void omaPinoJaJavaPino() {
        omaPinoP.stackPush(paikka4);
        javaPino.stackPush(paikka4);

        omaPinoP.stackPush(paikka1);
        javaPino.stackPush(paikka1);

        omaPinoP.stackPush(paikka2);
        javaPino.stackPush(paikka2);

        omaPinoP.stackPush(paikka7);
        javaPino.stackPush(paikka7);

        omaPinoP.stackPush(paikka8);
        javaPino.stackPush(paikka8);

        paikka = omaPinoP.stackPop();
        paikka = javaPino.stackPop();

        paikka = omaPinoP.stackPop();
        paikka = javaPino.stackPop();

        omaPinoP.stackPush(paikka8);
        javaPino.stackPush(paikka8);

        omaPinoP.stackPush(paikka3);
        javaPino.stackPush(paikka3);

        omaPinoP.stackPush(paikka2);
        javaPino.stackPush(paikka2);

        omaPinoP.stackPush(paikka7);
        javaPino.stackPush(paikka7);

        omaPinoP.stackPush(paikka8);
        javaPino.stackPush(paikka8);

        omaPinoP.stackPush(paikka9);
        javaPino.stackPush(paikka9);

        omaPinoP.stackPush(paikka1);
        javaPino.stackPush(paikka1);

        paikka = omaPinoP.stackPop();
        paikka = javaPino.stackPop();

        paikka = omaPinoP.stackPop();
        paikka = javaPino.stackPop();

        paikka = omaPinoP.stackPop();
        paikka = javaPino.stackPop();

        assertEquals(javaPino.stackPop(), omaPinoP.stackPop());
    }

    @Test
    public void omaPinoJaJavaPinoIsEmpty() {
        omaPinoP.stackPush(paikka4);
        javaPino.stackPush(paikka4);

        omaPinoP.stackPush(paikka1);
        javaPino.stackPush(paikka1);

        omaPinoP.stackPush(paikka2);
        javaPino.stackPush(paikka2);

        omaPinoP.stackPush(paikka7);
        javaPino.stackPush(paikka7);

        omaPinoP.stackPush(paikka8);
        javaPino.stackPush(paikka8);

        paikka = omaPinoP.stackPop();
        paikka = javaPino.stackPop();

        paikka = omaPinoP.stackPop();
        paikka = javaPino.stackPop();

        omaPinoP.stackPush(paikka8);
        javaPino.stackPush(paikka8);

        omaPinoP.stackPush(paikka3);
        javaPino.stackPush(paikka3);

        omaPinoP.stackPush(paikka2);
        javaPino.stackPush(paikka2);

        omaPinoP.stackPush(paikka7);
        javaPino.stackPush(paikka7);

        omaPinoP.stackPush(paikka8);
        javaPino.stackPush(paikka8);

        omaPinoP.stackPush(paikka9);
        javaPino.stackPush(paikka9);

        omaPinoP.stackPush(paikka1);
        javaPino.stackPush(paikka1);

        while (!javaPino.stackIsEmpty()) {
            paikka = omaPinoP.stackPop();
            paikka = javaPino.stackPop();
        }

        assertEquals(true, omaPinoP.stackIsEmpty());
    }
}
