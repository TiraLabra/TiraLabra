/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

/**
 * Luokka toteuttaa hajautustaulun käyttäen apuna linkitettyä listaa.
 * 
 * @author sampox
 */
public class HajautustauluLinkitetyllaListalla {

	/**
	 * Taulukko johon alkiot/alkiolistat tallennetaan
	 * 
	 */
	private KahteenSuuntaanLinkitettyLista[] taulukko;

	/**
	 * Hajautustaulukon koko
	 * 
	 */
	private int taulukonKoko;
	/**
	 * Merkintöjen määrä hajautustaulussa;
	 * 
	 */
	private int merkintoja;

	/**
	 * Konstruktori luo uuden linkitettyä listaa käyttävän hajautustaulun ja
	 * asettaa sen taulukon alkuperäiseksi kooksi parametrinä annetun arvon ja
	 * merkintöjen määräksi nollan.
	 * 
	 * @param koko
	 *            hajautustaulun alkuperäinen koko
	 */
	public HajautustauluLinkitetyllaListalla(int koko) {
		this.merkintoja = 0;
		this.taulukonKoko = koko;
		this.taulukko = new KahteenSuuntaanLinkitettyLista[taulukonKoko];
	}

	/**
	 * Metodi lisää uuden merkinnän hajautustauluun ja asettaa sille syötteenä
	 * saadun avaimen+arvon kun avain ei ole tyhjä. Jos avain löytyy
	 * hajautustaulusta päivitetään sen arvo. Merkintöjen määrää
	 * hajautustaulussa päivitetään tarpeen mukaan.
	 * 
	 * 
	 * @param avain
	 *            Uuden merkinnän avain
	 * @param arvo
	 *            Uuden merkinnän arvo
	 * 
	 * @return Linkitetyn listan solmu johon avain/arvo lisättiin
	 * 
	 * @see HajautustauluLinkitetyllaListalla#hajautusFunktio(String)
	 */
	public Solmu lisaaMerkinta(String avain, String arvo) {
		if (!tarkistaAvain(avain)) {
			return null;
		}
		int taulukonIndeksiAvaimelle = hajautusFunktio(avain);
		if (taulukko[taulukonIndeksiAvaimelle] == null) {
			merkintoja = merkintoja + 1;
			taulukko[taulukonIndeksiAvaimelle] = new KahteenSuuntaanLinkitettyLista();
			return taulukko[taulukonIndeksiAvaimelle].lisaaSolmu(avain, arvo);

		} else {
			Solmu avaimenSolmu = taulukko[taulukonIndeksiAvaimelle]
					.etsiAvainListalta(avain);
			if (avaimenSolmu != null) {
				avaimenSolmu.setArvo(arvo);
				return avaimenSolmu;
			} else {
				merkintoja = merkintoja + 1;
				return taulukko[taulukonIndeksiAvaimelle].lisaaSolmu(avain,
						arvo);
			}
		}
	}

	/**
	 * Metodi poistaa merkinnän sisältävän solmun hajautustaulusta, jos solmu ei
	 * ole tyhjä ja se ylipäätänsä hajautustaulusta löytyy. Solmun poistamisen
	 * onnistuessa merkintöjen määrää hajautustaulussa vähennetään.
	 * 
	 * @param poistettavaSolmu
	 *            hajautustaulusta poistettavan merkinnän solmu
	 */
	public void poistaMerkinta(Solmu poistettavaSolmu) {
		if (poistettavaSolmu != null
				&& tarkistaAvain(poistettavaSolmu.getAvain())) {
			int taulukonIndeksiAvaimelle = hajautusFunktio(poistettavaSolmu
					.getAvain());
			if (taulukko[taulukonIndeksiAvaimelle] != null) {
				taulukko[taulukonIndeksiAvaimelle]
						.poistaSolmu(poistettavaSolmu);
				merkintoja = merkintoja - 1;
			}
		}
	}

	/**
	 * Metodi käyttää etsiMerkinta-metodia avaimen sisältävän solmun löytämiseen
	 * ja sitten välittää sen solmun parametrina ottavalle toiselle
	 * poistometodille.Avaimen sisältävä solmu poistetaan hajautustaulusta.
	 * 
	 * @param avain
	 *            hajautustaulusta poistettavan solmun avain
	 * @see com.mycompany.tiralabra_maven.HajautustauluLinkitetyllaListalla#etsiMerkinta(java.lang.String)
	 * @see com.mycompany.tiralabra_maven.HajautustauluLinkitetyllaListalla#poistaMerkinta(com.mycompany.tiralabra_maven.Solmu)
	 */
	public void poistaMerkinta(String avain) {
		if (!tarkistaAvain(avain)) {
			return;
		}
		poistaMerkinta(etsiMerkinta(avain));

	}

	/**
	 * Metodi etsii avaimen hajautustaulusta.
	 * 
	 * @param avain
	 *            hajautustaulusta etsittävä avain
	 * @return Solmu josta avain löytyy
	 */
	public Solmu etsiMerkinta(String avain) {
		if (!tarkistaAvain(avain)) {
			return null;
		}
		int taulukonIndeksiAvaimelle = hajautusFunktio(avain);
		if (taulukko[taulukonIndeksiAvaimelle] != null) {
			return taulukko[taulukonIndeksiAvaimelle].etsiAvainListalta(avain);
		}
		return null;

	}

	/**
	 * Metodi muuntaa merkkijonon (avain) luvuksi, niin että sen indeksi
	 * taulukossa voidaan laskea hajautusfunktiossa. Merkkijonon merkkejä
	 * kerrotaan alkuluvulla 41 potenssiin i, jotta samat merkit sisältävät
	 * erilaiset merkkijonot saisivat eri tulokset.
	 * 
	 * @param avain
	 *            merkkijono joka muunnetaan luvuksi
	 * @return avain muunnettuna luvuksi
	 * @see HajautustauluLinkitetyllaListalla#hajautusFunktio(String)
	 */
	private int merkkijonoLuvuksi(String avain) {
		int merkkijonoLukuna = (int) avain.charAt(0);
		for (int i = 1; i < avain.length(); i++) {
			merkkijonoLukuna += (int) avain.charAt(i) * 41 ^ i;
		}
		return merkkijonoLukuna;
	}

	/**
	 * Metodi laskee jakojäännösmenetelmää hyväksikäyttäen avaimelle paikan
	 * taulukosta. Jakajana käytetään hajautustaulun kokoa.
	 * 
	 * @param avain
	 *            merkkijono jolle etsitään paikka taulukosta
	 * @return avaimelle laskettu indeksi taulukosta
	 * @see HajautustauluLinkitetyllaListalla#merkkijonoLuvuksi(String)
	 */
	private int hajautusFunktio(String avain) {
		return merkkijonoLuvuksi(avain) % taulukonKoko;
	}

	/**
	 * Metodi tarkistaa, ettei avain ole tyhjä
	 * 
	 * @param avain
	 *            Avaimena toimiva merkkijono
	 * @return false jos avain on tyhjä, muuten true
	 */
	private Boolean tarkistaAvain(String avain) {
		if (avain == null || avain.isEmpty()) {
			System.err.println("Avain ei voi olla tyhjä!");
			return false;
		}
		return true;
	}
	
	//GETTERIT JA SETTERIT
	public KahteenSuuntaanLinkitettyLista[] getTaulukko() {
		return taulukko;
	}

	public void setTaulukko(KahteenSuuntaanLinkitettyLista[] taulukko) {
		this.taulukko = taulukko;
	}

	public int getTaulukonKoko() {
		return taulukonKoko;
	}

	public void setTaulukonKoko(int taulukonKoko) {
		this.taulukonKoko = taulukonKoko;
	}

	public int getMerkintoja() {
		return merkintoja;
	}

	public void setMerkintoja(int merkintoja) {
		this.merkintoja = merkintoja;
	}

}
