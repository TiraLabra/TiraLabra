/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

/**
 * Luokka kuvaa kahteen suuntaan linkitettyä listaa. Listaan voi lisätä solmuja
 * ja siitä voi poistaa solmuja. Listalta voi myös etsiä tietyn avaimen
 * sisältävää solmua.
 * 
 * @author sampox
 */

public class KahteenSuuntaanLinkitettyLista {
	/**
	 * 
	 * Linkitetyn listan ensimmäinen solmu
	 */
	private Solmu ensimmainenSolmu;

	/**
	 * Konstruktori luo uuden kahteen suuntaan linkitetyn listan ja asettaa
	 * ensimmäisen solmun tyhjäksi.
	 * 
	 */
	public KahteenSuuntaanLinkitettyLista() {
		this.ensimmainenSolmu = null;
	}

	/**
	 * Metodi lisää uuden solmun linkitettyyn listaan, asettaa sille syötteenä
	 * saadun avaimen+arvon ja siirtää solmun listan ensimmäiseksi
	 * 
	 * 
	 * @param avain
	 *            Uuden solmun avain
	 * @param arvo
	 *            Uuden solmun arvo
	 * 
	 * @return lisätty solmu
	 */
	public Solmu lisaaSolmu(String avain, String arvo) {
		Solmu uusiSolmu = new Solmu(avain, arvo, this.ensimmainenSolmu, null);
		if (this.ensimmainenSolmu != null) {
			Solmu seuraava = uusiSolmu.getSeuraava();
			seuraava.setEdellinen(uusiSolmu);
		}
		this.ensimmainenSolmu = uusiSolmu;
		return uusiSolmu;
	}

	/**
	 * Metodi poistaa linkitetyltä listalta yhden solmun ja korjaa rikki menevät
	 * linkitykset listalla.
	 * 
	 * @param poistettavaSolmu
	 *            Listalta poistettava solmu
	 * 
	 */
	public void poistaSolmu(Solmu poistettavaSolmu) {
		if (poistettavaSolmu == null) {
			System.err.println("Ei voi poistaa tyhjää solmua");
			return;
		}
		Solmu poistettavaaEdeltava = poistettavaSolmu.getEdellinen();
		Solmu poistettavaaSeuraava = poistettavaSolmu.getSeuraava();
		if (poistettavaaEdeltava != null) {
			poistettavaaEdeltava.setSeuraava(poistettavaaSeuraava);
		} else {
			this.ensimmainenSolmu = poistettavaaSeuraava;
		}
		if (poistettavaaSeuraava != null) {
			poistettavaaSeuraava.setEdellinen(poistettavaaEdeltava);
		}

	}

	/**
	 * Metodi etsii linkitetyltä listalta solmun jonka avaimena on parametrinä
	 * annettu avain.
	 * 
	 * @param avain
	 *            Linkitetystä listasta etsittävä avain
	 * 
	 * @return Solmu jossa etsittävä avain on.
	 */
	public Solmu etsiAvainListalta(String avain) {
		if (avain == null || avain.isEmpty()) {
			System.err.println("Avain ei voi olla tyhjä!");
			return null;
		}
		Solmu EtsittavanAvaimenSolmu = this.ensimmainenSolmu;
		while (EtsittavanAvaimenSolmu != null
				&& !avain.equals(EtsittavanAvaimenSolmu.getAvain())) {
			EtsittavanAvaimenSolmu = EtsittavanAvaimenSolmu.getSeuraava();
		}
		return EtsittavanAvaimenSolmu;
	}

	public Solmu getEnsimmainenSolmu() {
		return ensimmainenSolmu;
	}

	public void setEnsimmainenSolmu(Solmu ensimmainenSolmu) {
		this.ensimmainenSolmu = ensimmainenSolmu;
	}
}
