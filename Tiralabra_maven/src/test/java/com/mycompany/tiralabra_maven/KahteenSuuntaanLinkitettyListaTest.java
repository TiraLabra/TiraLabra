package com.mycompany.tiralabra_maven;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KahteenSuuntaanLinkitettyListaTest {
	public KahteenSuuntaanLinkitettyListaTest() {
	}
	KahteenSuuntaanLinkitettyLista lista;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	@Before
	public void setUp() {
	this.lista=new KahteenSuuntaanLinkitettyLista();
	}
	@After
	public void tearDown() {
	}
	
	//TESTIT
	
	 @Test
	 public void testaaListaTyhjaAluksi() {
	 assertEquals(null,lista.getEnsimmainenSolmu());
	 }
	 
	 @Test
	 public void testaaLisaysAvain()  {
		 lista.lisaaSolmu("123", "");
		 assertEquals("123",lista.getEnsimmainenSolmu().getMerkinta().getAvain());
	 }
	 @Test
	 public void testaaLisaysArvo()  {
		 lista.lisaaSolmu("123", "321");
		 assertEquals("321",lista.getEnsimmainenSolmu().getMerkinta().getArvo());
	 }
	 @Test
	 public void testaaMonenLisays() {
		 lista.lisaaSolmu("123", "");
		 lista.lisaaSolmu("231", "");
		 lista.lisaaSolmu("312", "");
		 assertEquals("312",lista.getEnsimmainenSolmu().getMerkinta().getAvain());
		 assertEquals("231",lista.getEnsimmainenSolmu().getSeuraava().getMerkinta().getAvain());
		 assertEquals("123",lista.getEnsimmainenSolmu().getSeuraava().getSeuraava().getMerkinta().getAvain());
	 }
	 @Test
	 public void testaaPoisto() {
		 Solmu a = lista.lisaaSolmu("123","");
		 lista.poistaSolmu(a);
		 assertEquals(null,lista.getEnsimmainenSolmu());	 
	 }
	 @Test
	 public void testaaHaku() {
		 Solmu a = lista.lisaaSolmu("123", "");
		 lista.lisaaSolmu("231", "");
		 lista.lisaaSolmu("312", "");
		 assertEquals(a,lista.etsiAvainListalta("123"));
	 }
	
	
}
