package com.mycompany.tiralabra_maven;

/**
 * Luokka toteuttaa avointa hajautusta käyttävän hajautustaulun.
 * 
 * @author sampox
 */
public class HajautustauluAvoimellaHajautuksella implements Hajautustaulu {
	/**
	 * Taulukko johon alkiot tallennetaan
	 * 
	 */
	private TaulunMerkinta[] taulukko;
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
	 * Tayttosuhde, jonka ylittyessä tehdään uudelleenhajautus (suurennus)
	 * 
	 */
	private Double maksimiTayttosuhde;
	/**
	 * Tayttosuhde, jonka alittuessa tehdään uudelleenhajautus (pienennys)
	 * 
	 */
	private Double minimiTayttosuhde;
	/**
	 * Määrittelee käytetäänkö hajautustaulussa neliöistä vai lineaarista
	 * kokeilua. False jos neliöinen, true jos lineaarinen.
	 */
	private Boolean lineaarinenKokeilu;

	/**
	 * Konstruktori luo uuden avointa hajautusta käyttävän hajautustaulun ja
	 * asettaa sen taulukon alustavaksi kooksi 16, maksimitäyttösuhteeksi 0.8,
	 * minimitäyttösuhteeksi 0.3, merkintöjen määräksi nollan ja ottaa
	 * hajautusfunktiossa käyttöön joko lineaarisen tai neliöisen kokeilun.
	 * 
	 * @param lineaarinenKokeilu
	 *            määrittää onko käytössä lineaarinen kokeilu (true) vai
	 *            neliöinen kokeilu (false)
	 */
	public HajautustauluAvoimellaHajautuksella(Boolean lineaarinenKokeilu) {
		this.lineaarinenKokeilu = lineaarinenKokeilu;
		this.merkintoja = 0;
		this.taulukonKoko = 16;
		this.taulukko = new TaulunMerkinta[taulukonKoko];
		this.maksimiTayttosuhde = 0.8;
		this.minimiTayttosuhde = 0.3;

	}

	/**
	 * Metodi lisää uuden merkinnän hajautustauluun ja asettaa sille syötteenä
	 * saadun avaimen+arvon kun avain ei ole tyhjä. Jos avain löytyy
	 * hajautustaulusta päivitetään sen arvo, ja jos kokeilujonosta löytyy
	 * poistettu merkintä niin se korvataan tällä uudella merkinnällä.
	 * Merkintöjen määrää hajautustaulussa päivitetään tarpeen mukaan. Metodissa
	 * myös kutsutaan uudelleenhajautus metodia jos kriittinen täyttösuhde on
	 * saavutettu
	 * 
	 * 
	 * @param avain
	 *            Uuden merkinnän avain
	 * @param arvo
	 *            Uuden merkinnän arvo
	 * 
	 * @return Lisätty/päivitetty taulunMerkintä-olio tai null jos epäonnistui
	 * 
	 * @see HajautustauluAvoimellaHajautuksella#hajautusFunktio(String, int)
	 * @see HajautustauluAvoimellaHajautuksella#uudelleenHajautus(Boolean)
	 */
	public TaulunMerkinta lisaaMerkinta(String avain, String arvo) {
		if (!tarkistaAvain(avain)) {
			return null;
		}
		// Maksimitäyttösuhteen sattuessa uudelleenhajautus
		if (this.merkintoja == (int) (this.taulukonKoko * this.maksimiTayttosuhde)) {
			uudelleenHajautus(true);
		}
		int poistetunIndeksi = -1; // kuvaa mahdollista poistetun merkinnän
									// indeksiä
		int tyhjaIndeksi = -1; // kuvaa null indeksiä
		for (int i = 0; i <= this.taulukonKoko; i++) {
			int kokeiltavaIndeksiAvaimelle = hajautusFunktio(avain, i);
			if (taulukko[kokeiltavaIndeksiAvaimelle] == null) {
				tyhjaIndeksi = kokeiltavaIndeksiAvaimelle;
				break;
			} else {
				if (taulukko[kokeiltavaIndeksiAvaimelle].getAvain() == null
						&& poistetunIndeksi == -1) {
					poistetunIndeksi = kokeiltavaIndeksiAvaimelle;
				} else if (taulukko[kokeiltavaIndeksiAvaimelle].getAvain() != null && taulukko[kokeiltavaIndeksiAvaimelle].getAvain()
						.equals(avain)) {
					taulukko[kokeiltavaIndeksiAvaimelle].setArvo(arvo);
					return taulukko[kokeiltavaIndeksiAvaimelle];
				}
			}
		}
		if (poistetunIndeksi != -1) {
			tyhjaIndeksi = poistetunIndeksi;
		}
		if (tyhjaIndeksi != -1) {
			merkintoja = merkintoja + 1;
			return taulukko[tyhjaIndeksi] = new TaulunMerkinta(avain, arvo);
		}
		return null;
	}

	/**
	 * Metodi tarkastaa onko merkintä tyhjä, ja jos ei ole, välittää sen
	 * sisältämän avaimen toiselle poistometodille.
	 * 
	 * @param poistettavaMerkinta
	 *            hajautustaulusta poistettava merkintä
	 * @return true jos poisto onnistui, muuten false
	 */
	public Boolean poistaMerkinta(TaulunMerkinta poistettavaMerkinta) {
		if (poistettavaMerkinta != null) {
			return poistaMerkinta(poistettavaMerkinta.getAvain());
		}
		return false;

	}

	/**
	 * Jos avaimen sisältävä merkintä löytyy hajautustaulusta, metodi asettaa
	 * merkinnän avaimelle erikoisarvon (tyhjä avain), jolloin merkintä voidaan
	 * korvata uudella lisäyksessä. etsiMerkinta-metodia käytetään avaimen
	 * sisältävän merkinnän löytämiseen. Merkintöjen määrää päivitetään poiston
	 * mukaisesti. Metodi myös kutsuu uudelleenhajautusta jos täyttösuhde on
	 * erittäin alhainen.
	 * 
	 * @param avain
	 *            hajautustaulusta poistettava avain
	 * 
	 * @return true jos poisto onnistui, muuten false
	 * @see HajautustauluAvoimellaHajautuksella#uudelleenHajautus(Boolean)
	 */
	public Boolean poistaMerkinta(String avain) {
		if (!tarkistaAvain(avain)) {
			return false;
		}
		// Minimitäyttösuhteen sattuessa uudelleenhajautus
		if (this.merkintoja == (int) (this.taulukonKoko * this.minimiTayttosuhde)
				&& this.taulukonKoko > 16) {
			uudelleenHajautus(false);
		}
		for (int i = 0; i <= this.taulukonKoko; i++) {
			int taulukonIndeksiAvaimelle = hajautusFunktio(avain, i);
			if (taulukko[taulukonIndeksiAvaimelle] == null) {
				return false;
			}
			if (taulukko[taulukonIndeksiAvaimelle].getAvain() != null
					&& taulukko[taulukonIndeksiAvaimelle].getAvain().equals(
							avain)) {
				taulukko[taulukonIndeksiAvaimelle].setAvain(null);
				merkintoja = merkintoja - 1;
				return true;
			}
		}
		return false;

	}

	/**
	 * Metodi etsii avaimen hajautustaulusta.
	 * 
	 * @param avain
	 *            hajautustaulusta etsittävä avain
	 * @return Merkintä josta avain löytyy tai null jos avainta ei löydy
	 */
	public TaulunMerkinta etsiMerkinta(String avain) {
		if (!tarkistaAvain(avain)) {
			return null;
		}
		for (int i = 0; i <= this.taulukonKoko; i++) {
			int taulukonIndeksiAvaimelle = hajautusFunktio(avain, i);
			if (taulukko[taulukonIndeksiAvaimelle] == null) {
				return null;
			}
			if (taulukko[taulukonIndeksiAvaimelle].getAvain() != null
					&& taulukko[taulukonIndeksiAvaimelle].getAvain().equals(
							avain)) {
				return taulukko[taulukonIndeksiAvaimelle];
			}
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
	 * @see HajautustauluAvoimellaHajautukselle#hajautusFunktio(String)
	 */
	private int merkkijonoLuvuksi(String avain) {
		int merkkijonoLukuna = (int) avain.charAt(0);
		for (int i = 1; i < avain.length(); i++) {
			merkkijonoLukuna += (int) avain.charAt(i) * 41 ^ i;
		}
		return merkkijonoLukuna;
	}

	/**
	 * Kutsuu sitä hajautusfunktiota laskemaan indeksi parametrina saadulle
	 * avaimelle, joka on konstruktorissa asetettu käyttöön
	 * (lineaarinenHajautus-muuttuja).
	 * 
	 * @param avain
	 *            hajautusfunktioille välitettävä avain
	 * @param laskuri
	 *            kertoo mones etsintäkerta menossa kokeilujonolla
	 * @return käytössä olevan hajautusfunktion avaimelle laskema indeksi
	 *         taulukosta
	 * 
	 * @see HajautustauluAvoimellaHajautuksella#LineaarinenHajautusFunktio(String,
	 *      int)
	 * @see HajautustauluAvoimellaHajautuksella#NelioinenHajautusFunktio(String,
	 *      int)
	 */
	private int hajautusFunktio(String avain, int laskuri) {
		if (this.lineaarinenKokeilu) {
			return LineaarinenHajautusFunktio(avain, laskuri);
		}
		return NelioinenHajautusFunktio(avain, laskuri);
	}

	/**
	 * Metodi laskee jakojäännösmenetelmää hyväksikäyttäen avaimelle paikan
	 * taulukosta. Jakajana käytetään hajautustaulun kokoa. Jos paikassa on jo
	 * toinen avain, voidaan metodia kutsua uudestaan yhtä suuremmalla
	 * laskuri-muuttujalla. Käytössä on siis lineaarinen kokeilujono.
	 * 
	 * @param avain
	 *            merkkijono jolle etsitään paikka taulukosta
	 * @param laskuri
	 *            kuvaa monennetta kertaa paikkaa lasketaan
	 * @return avaimelle laskettu indeksi taulukosta
	 * @see HajautustauluAvoimellaHajautuksella#merkkijonoLuvuksi(String)
	 */
	private int LineaarinenHajautusFunktio(String avain, int laskuri) {
		return ((merkkijonoLuvuksi(avain) % taulukonKoko) + laskuri)
				% taulukonKoko;

	}

	/**
	 * Metodi laskee jakojäännösmenetelmää hyväksikäyttäen avaimelle paikan
	 * taulukosta. Jakajana käytetään hajautustaulun kokoa. Jos paikassa on jo
	 * toinen avain, etsitään uusi paikka siis lisäämällä edellisellä kerralla
	 * saatuun arvoon laskuri-muuttujalla kerrottu vakio ja laskuri-muuttujan
	 * neliöllä kerrottu vakio hajautusfunktiossa. Käytössä on siis neliöinen
	 * kokeilujono
	 * 
	 * @param avain
	 *            merkkijono jolle etsitään paikka taulukosta
	 * @param laskuri
	 *            kuvaa monennetta kertaa paikkaa lasketaan
	 * @return avaimelle laskettu indeksi taulukosta
	 * @see HajautustauluAvoimellaHajautuksella#merkkijonoLuvuksi(String)
	 */
	private int NelioinenHajautusFunktio(String avain, int laskuri) {
		return (int) (((merkkijonoLuvuksi(avain) % taulukonKoko) + 0.5
				* laskuri + 0.5 * Math.pow(laskuri, 2.0)) % taulukonKoko);

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
	 * @see HajautustauluAvoimellaHajautuksella#lisaaMerkinta(String, String)
	 * @see HajautustauluAvoimellaHajautuksella#poistaMerkinta(String)
	 */
	private void uudelleenHajautus(Boolean suurennusVaiPienennys) {
		this.merkintoja = 0; // nollataan merkintöjen määrä
		TaulunMerkinta[] vanhaTaulukko = this.taulukko.clone();
		if (suurennusVaiPienennys) {
			this.taulukonKoko = this.taulukonKoko * 2;
		} else {
			this.taulukonKoko = this.taulukonKoko / 2;
		}
		this.taulukko = new TaulunMerkinta[this.taulukonKoko];

		for (TaulunMerkinta merkinta : vanhaTaulukko) {
			if (merkinta != null && merkinta.getAvain() != null)
				lisaaMerkinta(merkinta.getAvain(), merkinta.getArvo());
		}
	}

	// GETTERIT JA SETTERIT
	public TaulunMerkinta[] getTaulukko() {
		return taulukko;
	}

	public void setTaulukko(TaulunMerkinta[] taulukko) {
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

	public Boolean getLineaarinenKokeilu() {
		return lineaarinenKokeilu;
	}

	public void setLineaarinenKokeilu(Boolean lineaarinenKokeilu) {
		this.lineaarinenKokeilu = lineaarinenKokeilu;
	}

	public double getTayttosuhde() {
		return (double) merkintoja / taulukonKoko;
	}
	

}