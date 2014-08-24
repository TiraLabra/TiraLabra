package com.mycompany.tiralabra_maven;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HajautustauluAvoimellaHajautuksellaTest {
	public HajautustauluAvoimellaHajautuksellaTest() {
	}
	HajautustauluAvoimellaHajautuksella hajis;
	HajautustauluAvoimellaHajautuksella nelioHajis;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	@Before
	public void setUp() {
	this.hajis=new HajautustauluAvoimellaHajautuksella(16,true);
	this.nelioHajis=new HajautustauluAvoimellaHajautuksella(16,false);
	}
	@After
	public void tearDown() {
	}
	
	//TESTIT LINEAARISTA KOKEILUJONOA KÄYTTÄVÄLLE VERSIOLLE
	
	
	 @Test
	 public void testaaTauluTyhjaAluksi() {
	 assertEquals(0,hajis.getMerkintoja());
	 }
	 
	@Test
	public void testaaMerkinnanLisays() {
		TaulunMerkinta lisatty = hajis.lisaaMerkinta("1", "2");
		assertEquals(lisatty,hajis.etsiMerkinta("1"));
		assertEquals(1,hajis.getMerkintoja());
	}
	@Test
	public void testaaUseanMerkinnanLisays() {
		TaulunMerkinta lisatty = hajis.lisaaMerkinta("1", "2");
		TaulunMerkinta lisattymyos = hajis.lisaaMerkinta("23","32");
		TaulunMerkinta lisattymyos2 = hajis.lisaaMerkinta("3","32");
		
		assertEquals(lisatty,hajis.etsiMerkinta("1"));
		assertEquals(lisattymyos,hajis.etsiMerkinta("23"));
		assertEquals(lisattymyos2,hajis.etsiMerkinta("3"));
		assertEquals(3,hajis.getMerkintoja());
	}
	@Test
	public void testaaMerkinnanPoisto() {
		TaulunMerkinta lisatty = hajis.lisaaMerkinta("1", "2");
		hajis.poistaMerkinta(lisatty);
		assertEquals(0,hajis.getMerkintoja());
	}
	
	 @Test
	 public void testaaMerkinnanHaku() {
     TaulunMerkinta lisatty = hajis.lisaaMerkinta("1", "2");
     TaulunMerkinta lisatty2 = hajis.lisaaMerkinta("2", "4");
     TaulunMerkinta lisatty3 = hajis.lisaaMerkinta("4", "2");
     assertEquals(lisatty,hajis.etsiMerkinta("1"));
	 assertEquals(lisatty2,hajis.etsiMerkinta("2"));
	 assertEquals(lisatty3,hajis.etsiMerkinta("4"));
	 }

	//TESTIT NELIÖISTÄ KOKEILUJONOA KÄYTTÄVÄLLE VERSIOLLE
	 
		
	 @Test
	 public void nelioHajisTyhjaAluksi() {
	 assertEquals(0,nelioHajis.getMerkintoja());
	 }
	 
	@Test
	public void nelioHajisMerkinnanLisays() {
		TaulunMerkinta lisatty = nelioHajis.lisaaMerkinta("1", "2");
		assertEquals(lisatty,nelioHajis.etsiMerkinta("1"));
		assertEquals(1,nelioHajis.getMerkintoja());
	}
	@Test
	public void nelioHajisUseanMerkinnanLisays() {
		TaulunMerkinta lisatty = nelioHajis.lisaaMerkinta("1", "2");
		TaulunMerkinta lisattymyos = nelioHajis.lisaaMerkinta("23","32");
		TaulunMerkinta lisattymyos2 = nelioHajis.lisaaMerkinta("3","32");
		
		assertEquals(lisatty,nelioHajis.etsiMerkinta("1"));
		assertEquals(lisattymyos,nelioHajis.etsiMerkinta("23"));
		assertEquals(lisattymyos2,nelioHajis.etsiMerkinta("3"));
		assertEquals(3,nelioHajis.getMerkintoja());
	}
	@Test
	public void nelioHajisMerkinnanPoisto() {
		TaulunMerkinta lisatty = nelioHajis.lisaaMerkinta("1", "2");
		nelioHajis.poistaMerkinta(lisatty);
		assertEquals(0,nelioHajis.getMerkintoja());
	}
	
	 @Test
	 public void nelioHajisMerkinnanHaku() {
     TaulunMerkinta lisatty = nelioHajis.lisaaMerkinta("1", "2");
     TaulunMerkinta lisatty2 = nelioHajis.lisaaMerkinta("2", "4");
     TaulunMerkinta lisatty3 = nelioHajis.lisaaMerkinta("4", "2");
     assertEquals(lisatty,nelioHajis.etsiMerkinta("1"));
	 assertEquals(lisatty2,nelioHajis.etsiMerkinta("2"));
	 assertEquals(lisatty3,nelioHajis.etsiMerkinta("4"));
	 }
}
