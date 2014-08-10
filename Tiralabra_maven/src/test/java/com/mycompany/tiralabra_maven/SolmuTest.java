package com.mycompany.tiralabra_maven;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SolmuTest {
	public SolmuTest() {
	}
	Solmu solmu;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	@Before
	public void setUp() {
	this.solmu=new Solmu(null,null,null,null);
	}
	@After
	public void tearDown() {
	}

	//TESTIT
	
	@Test
	public void testaaAvaimenLisays() {
		solmu.setAvain("123");
		assertEquals("123",solmu.getAvain());
	}
	@Test
	public void testaaArvonLisays() {
		solmu.setArvo("123");
		assertEquals("123",solmu.getArvo());
	}
	@Test
	public void testaaSeuraava() {
		Solmu b = new Solmu(null,null,null,null);
		solmu.setSeuraava(b);
		assertEquals(b,solmu.getSeuraava());
	}
	@Test
	public void testaaEdellinen() {
		Solmu b = new Solmu(null,null,null,null);
		solmu.setEdellinen(b);
		assertEquals(b,solmu.getEdellinen());
	}
}
