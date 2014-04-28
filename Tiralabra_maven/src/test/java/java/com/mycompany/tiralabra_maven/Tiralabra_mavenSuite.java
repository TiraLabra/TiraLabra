/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java.com.mycompany.tiralabra_maven;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Tiina
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.mycompany.tiralabra_maven.WallTest.class, com.mycompany.tiralabra_maven.BoardTest.class, com.mycompany.tiralabra_maven.AstarTest.class, com.mycompany.tiralabra_maven.NodeTest.class, com.mycompany.tiralabra_maven.HiirestysTest.class, com.mycompany.tiralabra_maven.HiiriTest.class, com.mycompany.tiralabra_maven.ListaTest.class})
public class Tiralabra_mavenSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
