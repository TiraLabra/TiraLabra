package com.mycompany.tiralabra_maven;
/**
 * Luokka toteuttaa avointa hajautusta käyttävän hajautustaulun.
 * 
 * @author sampox
 */
public class HajautustauluAvoimellaHajautuksella {
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
	 * Määrittelee käytetäänkö hajautustaulussa neliöistä vai lineaarista
	 * kokeilua. False jos neliöinen, true jos lineaarinen.
	 */
	private Boolean lineaarinenKokeilu;

	/**
	 * Konstruktori luo uuden avointa hajautusta käyttävän hajautustaulun ja
	 * asettaa sen taulukon alkuperäiseksi kooksi parametrinä annetun arvon,
	 * merkintöjen määräksi nollan ja ottaa hajautusfunktiossa käyttöön joko
	 * lineaarisen tai neliöisen kokeilun.
	 * 
	 * @param koko
	 *            hajautustaulun alkuperäinen koko
	 * @param lineaarinenKokeilu
	 *            määrittää onko käytössä lineaarinen kokeilu (true) vai
	 *            neliöinen kokeilu (false)
	 */
	public HajautustauluAvoimellaHajautuksella(int taulukonKoko,
			Boolean lineaarinenKokeilu) {
		this.taulukko = new TaulunMerkinta[taulukonKoko];
		this.lineaarinenKokeilu = lineaarinenKokeilu;
		this.merkintoja = 0;
		this.taulukonKoko = taulukonKoko;
	}

	/**
	 * Metodi lisää uuden merkinnän hajautustauluun ja asettaa sille syötteenä
	 * saadun avaimen+arvon kun avain ei ole tyhjä. Jos avain löytyy
	 * hajautustaulusta päivitetään sen arvo. Merkintöjen määrää
	 * hajautustaulussa päivitetään tarpeen mukaan. Metodissa myös kutsutaan
	 * uudelleenhajautus metodia jos kriittinen täyttösuhde on saavutettu
	 * 
	 * 
	 * @param avain
	 *            Uuden merkinnän avain
	 * @param arvo
	 *            Uuden merkinnän arvo
	 * 
	 * @return Lisätty/päivitetty taulunMerkintä-olio
	 * 
	 * @see HajautustauluAvoimellaHajautuksella#hajautusFunktio(String)
	 * @see HajautustauluAvoimellaHajautuksella#uudelleenHajautus()
	 */
	public TaulunMerkinta lisaaMerkinta(String avain, String arvo) {
		if (!tarkistaAvain(avain)) {
			return null;
		}
		if (this.merkintoja == (int) (taulukonKoko * 0.8)) { // Kun taulukko on
																// 80% täysi
			uudelleenHajautus();
		}
		int taulukonIndeksiAvaimelle = hajautusFunktio(avain);
		if (taulukko[taulukonIndeksiAvaimelle] == null) {
			merkintoja = merkintoja + 1;
			return taulukko[taulukonIndeksiAvaimelle] = new TaulunMerkinta(
					avain, arvo);
		} else {
			taulukko[taulukonIndeksiAvaimelle].setArvo(arvo);
			return taulukko[taulukonIndeksiAvaimelle];
		}
	}

	/**
	 * Metodi poistaa merkinnän hajautustaulusta, jos se ylipäätänsä
	 * hajautustaulusta löytyy. Poistamisen onnistuessa merkintöjen määrää
	 * hajautustaulussa vähennetään.
	 * 
	 * @param poistettavaMerkinta
	 *            hajautustaulusta poistettava merkintä
	 */
	public void poistaMerkinta(TaulunMerkinta poistettavaMerkinta) {
		if (poistettavaMerkinta != null
				&& tarkistaAvain(poistettavaMerkinta.getAvain())) {
			int taulukonIndeksiAvaimelle = hajautusFunktio(poistettavaMerkinta
					.getAvain());
			if (taulukko[taulukonIndeksiAvaimelle] != null) {
				taulukko[taulukonIndeksiAvaimelle] = null;
				merkintoja = merkintoja - 1;
			}
		}
	}

	/**
	 * Metodi etsii avaimen hajautustaulusta.
	 * 
	 * @param avain
	 *            hajautustaulusta etsittävä avain
	 * @return Merkintä josta avain löytyy
	 */
	public TaulunMerkinta etsiMerkinta(String avain) {
		if (!tarkistaAvain(avain)) {
			return null;
		}
		int taulukonIndeksiAvaimelle = hajautusFunktio(avain);
		if (taulukko[taulukonIndeksiAvaimelle] != null) {
			return taulukko[taulukonIndeksiAvaimelle];
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
	 * @return käytössä olevan hajautusfunktion avaimelle laskema indeksi
	 *         taulukosta
	 * 
	 * @see HajautustauluAvoimellaHajautuksella#LineaarinenHajautusFunktio(String)
	 * @see HajautustauluAvoimellaHajautuksella#NelioinenHajautusFunktio(String)
	 */
	private int hajautusFunktio(String avain) {
		if (this.lineaarinenKokeilu) {
			return LineaarinenHajautusFunktio(avain);
		}
		return NelioinenHajautusFunktio(avain);
	}

	/**
	 * Metodi laskee jakojäännösmenetelmää hyväksikäyttäen avaimelle paikan
	 * taulukosta. Jakajana käytetään hajautustaulun kokoa. Jos paikassa on jo
	 * toinen avain, etsitään uusi paikka lisäämällä edellisellä kerralla
	 * saatuun arvoon laskuri-muuttujan määrä hajautusfunktiossa. Käytössä on
	 * siis lineaarinen kokeilujono.
	 * 
	 * @param avain
	 *            merkkijono jolle etsitään paikka taulukosta
	 * @return avaimelle laskettu indeksi taulukosta
	 * @see HajautustauluAvoimellaHajautuksella#merkkijonoLuvuksi(String)
	 */
	private int LineaarinenHajautusFunktio(String avain) {
		int taulukonIndeksi = merkkijonoLuvuksi(avain) % taulukonKoko;
		int laskuri = 1; // kokeilukertojen määrän laskuri
		while (taulukko[taulukonIndeksi] != null
				&& !taulukko[taulukonIndeksi].getAvain().equals(avain)) {
			taulukonIndeksi = (taulukonIndeksi + laskuri) % taulukonKoko;
			laskuri = laskuri + 1;
		}
		return taulukonIndeksi;
	}

	/**
	 * Metodi laskee jakojäännösmenetelmää hyväksikäyttäen avaimelle paikan
	 * taulukosta. Jakajana käytetään hajautustaulun kokoa. Jos paikassaa on jo
	 * toinen avain, etsitään uusi paikka siis lisäämällä edellisellä kerralla
	 * saatuun arvoon laskuri-muuttujalla kerrottu vakio ja laskuri-muuttujan
	 * neliöllä kerrottu vakio hajautusfunktiossa. Käytössä on siis neliöinen
	 * kokeilujono
	 * 
	 * @param avain
	 *            merkkijono jolle etsitään paikka taulukosta
	 * @return avaimelle laskettu indeksi taulukosta
	 * @see HajautustauluAvoimellaHajautuksella#merkkijonoLuvuksi(String)
	 */
	private int NelioinenHajautusFunktio(String avain) {
		int taulukonIndeksi = merkkijonoLuvuksi(avain) % taulukonKoko;
		int laskuri = 1; // kokeilukertojen määrän laskuri
		Double vakio = 0.5;
		Double toinenVakio = 0.5;
		while (taulukko[taulukonIndeksi] != null
				&& !taulukko[taulukonIndeksi].getAvain().equals(avain)) {
			taulukonIndeksi = (int) ((taulukonIndeksi + vakio * laskuri + toinenVakio
					* Math.pow(laskuri, 2.0)) % taulukonKoko);
			laskuri = laskuri + 1;
		}
		return taulukonIndeksi;
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
	 * maksimitäyttösuhde, suoritetaan tämä metodi. Metodi käy läpi
	 * hajautustaulun merkinnät ja jakaa ne uusiin indekseihin suurempaan
	 * taulukkoon, joka sitten asetetaan hajautustaulun käyttöön.
	 * 
	 * @see HajautustauluAvoimellaHajautuksella#lisaaMerkinta(String, String)
	 */
	private void uudelleenHajautus() {
		TaulunMerkinta[] vanhaTaulukko = this.taulukko.clone();
		this.taulukonKoko = this.taulukonKoko * 2;
		this.taulukko= new TaulunMerkinta[this.taulukonKoko];
		
		for (TaulunMerkinta merkinta : vanhaTaulukko) {
			if (merkinta != null)
				this.taulukko[hajautusFunktio(merkinta.getAvain())] = new TaulunMerkinta(
						merkinta.getAvain(), merkinta.getArvo());
		}
	}

	//GETTERIT JA SETTERIT
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
	
	
}