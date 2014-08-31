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
public class HajautustauluLinkitetyllaListalla implements Hajautustaulu {

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
	 * Täyttosuhde, jonka ylittyessä tehdään uudelleenhajautus (suurennus)
	 * 
	 */
	private Double maksimiTayttosuhde;
	/**
	 * Täyttosuhde, jonka alittuessa tehdään uudelleenhajautus (pienennys)
	 * 
	 */
	private Double minimiTayttosuhde;

	/**
	 * Konstruktori luo uuden linkitettyä listaa käyttävän hajautustaulun ja
	 * asettaa sen merkintöjen määräksi nollan, alustavaksi kooksi 16,
	 * maksimitäyttösuhteeksi 5 ja minimitäyttösuhteeksi 1.
	 * 
	 */
	public HajautustauluLinkitetyllaListalla() {
		this.merkintoja = 0;
		this.taulukonKoko = 16;
		this.taulukko = new KahteenSuuntaanLinkitettyLista[taulukonKoko];
		this.maksimiTayttosuhde = 5.0;
		this.minimiTayttosuhde = 1.0;
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
	public TaulunMerkinta lisaaMerkinta(String avain, String arvo) {
		if (!tarkistaAvain(avain)) {
			return null;
		}
		// Maksimitäyttösuhteen sattuessa uudelleenhajautus
		if (this.merkintoja == (int) (this.taulukonKoko * this.maksimiTayttosuhde)) {
			uudelleenHajautus(true);
		}
		int taulukonIndeksiAvaimelle = hajautusFunktio(avain);
		if (taulukko[taulukonIndeksiAvaimelle] == null) {
			merkintoja = merkintoja + 1;
			taulukko[taulukonIndeksiAvaimelle] = new KahteenSuuntaanLinkitettyLista();
			return taulukko[taulukonIndeksiAvaimelle].lisaaSolmu(avain, arvo).getMerkinta();

		} else {
			Solmu avaimenSolmu = taulukko[taulukonIndeksiAvaimelle]
					.etsiAvainListalta(avain);
			if (avaimenSolmu != null) {
				avaimenSolmu.getMerkinta().setArvo(arvo);
				return avaimenSolmu.getMerkinta();
			} else {
				merkintoja = merkintoja + 1;
				return taulukko[taulukonIndeksiAvaimelle].lisaaSolmu(avain,
						arvo).getMerkinta();
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
	 * @return true jos poisto onnistui, muuten false
	 */
	public Boolean poistaMerkinta(Solmu poistettavaSolmu) {
		if (poistettavaSolmu != null
				&& tarkistaAvain(poistettavaSolmu.getMerkinta().getAvain())) {
			// Minimitäyttösuhteen sattuessa uudelleenhajautus
			if (this.merkintoja == (int) (this.taulukonKoko * this.minimiTayttosuhde)
					&& this.taulukonKoko > 16) {
				uudelleenHajautus(false);
			}
			int taulukonIndeksiAvaimelle = hajautusFunktio(poistettavaSolmu
					.getMerkinta().getAvain());
			if (taulukko[taulukonIndeksiAvaimelle] != null) {
				taulukko[taulukonIndeksiAvaimelle]
						.poistaSolmu(poistettavaSolmu);
				merkintoja = merkintoja - 1;
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodi käyttää etsiMerkinta-metodia avaimen sisältävän solmun löytämiseen
	 * ja sitten välittää sen solmun parametrina ottavalle toiselle
	 * poistometodille.Avaimen sisältävä solmu poistetaan hajautustaulusta.
	 * 
	 * @param avain
	 *            hajautustaulusta poistettavan solmun avain
	 * @return true jos poisto onnistui, muuten false
	 * @see com.mycompany.tiralabra_maven.HajautustauluLinkitetyllaListalla#etsiMerkinta(java.lang.String)
	 * @see com.mycompany.tiralabra_maven.HajautustauluLinkitetyllaListalla#poistaMerkinta(com.mycompany.tiralabra_maven.Solmu)
	 */
	public Boolean poistaMerkinta(String avain) {
		return poistaMerkinta(etsiMerkinnanSolmu(avain));

	}

	/**
	 * Metodi etsii avaimen hajautustaulusta.
	 * 
	 * @param avain
	 *            hajautustaulusta etsittävä avain
	 * @return Merkinta josta avain löytyy
	 */
	public TaulunMerkinta etsiMerkinta(String avain) {
		if (!tarkistaAvain(avain)) {
			return null;
		}
		int taulukonIndeksiAvaimelle = hajautusFunktio(avain);
		if (taulukko[taulukonIndeksiAvaimelle] != null) {
			Solmu loydetty = taulukko[taulukonIndeksiAvaimelle].etsiAvainListalta(avain);
			if(loydetty!=null && loydetty.getMerkinta()!=null) {
			return loydetty.getMerkinta(); }
		}
		return null;

	}
	/**
	 * Metodi etsii avaimen hajautustaulusta.
	 * 
	 * @param avain
	 *            hajautustaulusta etsittävä avain
	 * @return Solmu josta avain löytyy
	 */
	public Solmu etsiMerkinnanSolmu(String avain) {
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

	/**
	 * Kun hajautustaulussa saavutetaan lisäysmetodissa tarkastettava määritelty
	 * maksimitäyttösuhde tai poistometodissa määritelty minimitäyttösuhde,
	 * suoritetaan tämä metodi. Metodi käy läpi hajautustaulun merkinnät ja
	 * jakaa ne uusiin indekseihin suurempaan tai pienempään taulukkoon, joka
	 * sitten asetetaan hajautustaulun käyttöön.
	 * 
	 * @param suurennusVaiPienennys
	 *            true jos taulukkoa suurennetaan, false jos pienennetään
	 * @see HajautustauluLinkitetyllaListalla#lisaaMerkinta(String, String)
	 * @see HajautustauluLinkitetyllaListalla#poistaMerkinta(String)
	 */
	private void uudelleenHajautus(Boolean suurennusVaiPienennys) {
		this.merkintoja = 0; // nollataan merkintöjen määrä
		KahteenSuuntaanLinkitettyLista[] vanhaTaulukko = this.taulukko.clone();
		if (suurennusVaiPienennys) {
			this.taulukonKoko = this.taulukonKoko * 2;
		} else {
			this.taulukonKoko = this.taulukonKoko / 2;
		}
		this.taulukko = new KahteenSuuntaanLinkitettyLista[this.taulukonKoko];

		for (KahteenSuuntaanLinkitettyLista merkinta : vanhaTaulukko) {
			if (merkinta != null) {
				Solmu lapikaytava = merkinta.getEnsimmainenSolmu();
				while (lapikaytava != null) {
					lisaaMerkinta(lapikaytava.getMerkinta().getAvain(), lapikaytava.getMerkinta().getArvo());
					lapikaytava = lapikaytava.getSeuraava();
				}
			}
		}
	}

	// GETTERIT JA SETTERIT
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

	public double getTayttosuhde() {
		return (double) merkintoja / taulukonKoko;
	}
}
