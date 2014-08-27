package com.mycompany.tiralabra_maven;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HajautustauluLinkitetyllaListallaTest {
	public HajautustauluLinkitetyllaListallaTest() {
	}
	HajautustauluLinkitetyllaListalla hajis;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	@Before
	public void setUp() {
	this.hajis=new HajautustauluLinkitetyllaListalla();
	}
	@After
	public void tearDown() {
	}
	
	//TESTIT
	
	 @Test
	 public void testaaTauluTyhjaAluksi() {
	 assertEquals(0,hajis.getMerkintoja());
	 }
	 
	@Test
	public void testaaMerkinnanLisays() {
		Solmu lisatty = hajis.lisaaMerkinta("1", "2");
		assertEquals(lisatty,hajis.etsiMerkinta("1"));
		assertEquals(1,hajis.getMerkintoja());
	}
	@Test
	public void testaaUseanMerkinnanLisays() {
		Solmu lisatty = hajis.lisaaMerkinta("1", "2");
		Solmu lisattymyos = hajis.lisaaMerkinta("23","32");
		Solmu lisattymyos2 = hajis.lisaaMerkinta("3","32");
		
		assertEquals(lisatty,hajis.etsiMerkinta("1"));
		assertEquals(lisattymyos,hajis.etsiMerkinta("23"));
		assertEquals(lisattymyos2,hajis.etsiMerkinta("3"));
		assertEquals(3,hajis.getMerkintoja());
	}
	@Test
	public void testaaMerkinnanPoisto() {
		Solmu lisatty = hajis.lisaaMerkinta("1", "2");
		hajis.poistaMerkinta(lisatty);
		assertEquals(0,hajis.getMerkintoja());
	}
	
	 @Test
	 public void testaaMerkinnanHaku() {
     Solmu lisatty = hajis.lisaaMerkinta("1", "2");
     Solmu lisatty2 = hajis.lisaaMerkinta("2", "4");
     Solmu lisatty3 = hajis.lisaaMerkinta("4", "2");
     assertEquals(lisatty,hajis.etsiMerkinta("1"));
	 assertEquals(lisatty2,hajis.etsiMerkinta("2"));
	 assertEquals(lisatty3,hajis.etsiMerkinta("4"));
	 }
}
