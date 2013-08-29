package chess.ai;

/**
 * Transpositiotaulu, eli hajautustaulu johon tallennetaan jo analysoidut pelitilanteet, ja haun
 * tulokset.
 *
 * Avaimena käytetään pelitilanteesta laskettua 64-bittistä Zobrist-tunnistetta, josta edelleen
 * lasketaan hajautusarvo vähiten merkitsevistä biteistä. Teoriassa on mahdollista, että kahdella
 * pelitilanteella on sama 64-bittinen tunniste, mutta riski on riittävän pieni verrattuna
 * saavutettuun hyötyyn nopeudessa ja muistinkulutuksessa.
 *
 * Hajautustaulu käyttää avointa hajautusta (open addressing) ja neliöllistä kokeilujonoa
 * ((h + i/2 + i^2/2) % n). Kun taulun koko on kahden potenssi, kokeilujono käy läpi kaikki taulun
 * alkiot kunnes vapaa alkio löytyy.
 *
 * Poisto tapahtuu merkitsemällä alkiot asettamalla viite erityiseen REMOVED-objektiin, jotta
 * kokeilujonot pysyvät yhtenäisenä. Kun varattujen elementtien (käytössä olevat + poistetut)
 * yhteismäärä ylittää puolet kapasiteetista, suoritetaan uudelleenhajautus.
 */
final class TranspositionTable
{
	/**
	 * Hajautustaulun oletuskoko alussa.
	 */
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	/**
	 * Kerroin, jolla taulun kokoa kasvatetaan, kun uudelleenhajautuskynnys ylitetään.
	 */
	private static final int GROWTH_FACTOR = 2;

	/**
	 * Tietue poistettujen alkioiden merkitsemiseksi, jotta kokeilujonot pysyvät yhtenäisenä.
	 */
	private static final StateInfo REMOVED = new StateInfo(0);

	/**
	 * Käytössä olevat + poistetuksi merkityt alkiot.
	 */
	private int reservedCount = 0;

	/**
	 * Poistettujen tietueiden lukumäärä.
	 */
	private int removedCount = 0;

	/**
	 * Taulun koko, kahden potenssi.
	 */
	private int capacity;

	/**
	 * Bittimaski, jolla saadaan tehtyä modulo-operaatio nopeasti (x % capacity == x & mask).
	 */
	private int mask;

	/**
	 * Hajautustaulu.
	 */
	private StateInfo[] entries;

	/**
	 * Luo uuden transpositiotaulun.
	 */
	public TranspositionTable()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * Luo uuden transpositiotaulun käyttäen annettua alkukapasiteettia.
	 *
	 * @param initialCapacity kapasiteetti alussa
	 */
	public TranspositionTable(int initialCapacity)
	{
		clear(initialCapacity);
	}

	/**
	 * LIsää tauluun uuden tietueen. Avaimena käytetään tietueen state-kenttää, joka on vastaavan
	 * pelitilanteen Zobrist-arvo.
	 *
	 * @param info lisättävä tietue
	 */
	public void put(StateInfo info)
	{
		if (reservedCount >= capacity >> 1)
			rehash();

		int h = (int) info.state & mask;
		int d = 1;
		while (entries[h] != null && entries[h] != REMOVED && entries[h].state != info.state)
			h = (h + d++) & mask;
		if (entries[h] == null || entries[h] == REMOVED)
			++reservedCount;

		entries[h] = info;
	}

	/**
	 * Hakee taulusta pelitilannetta vastaavan tietueen.
	 *
	 * @param state pelitilanteen Zobrist-koodi
	 * @return vastaava StateInfo tietue tai null jos tietuetta ei löytynyt
	 */
	public StateInfo get(long state)
	{
		int h = (int) state & mask;
		int d = 1;
		while (entries[h] != null) {
			if (entries[h] != REMOVED && state == entries[h].state)
				return entries[h];
			h = (h + d++) & mask;
		}

		return null;
	}

	/**
	 * Tyhjentää hajautustaulun sisällön.
	 */
	public void clear()
	{
		clear(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * Tyhjentää hajautustaulun sisällön ja varaa uuden taulun annetulla kapasiteetilla.
	 *
	 * @param initialCapacity alkukapasiteetti
	 */
	public void clear(int initialCapacity)
	{
		capacity = initialCapacity;
		if (capacity < 8)
			throw new IllegalArgumentException("Initial capacity too small.");
		mask = capacity - 1;
		entries = new StateInfo[capacity];
		reservedCount = 0;
		removedCount = 0;
	}

	/**
	 * Palauttaa tietueiden lukumäärän.
	 *
	 * @return
	 */
	public int size()
	{
		return reservedCount - removedCount;
	}

	/**
	 * Poistaa pelitilanteen taulusta.
	 *
	 * @param state pelitilanteen tunniste
	 */
	public void remove(long state)
	{
		int h = (int) state & mask;
		int d = 1;
		while (entries[h] != null) {
			if (state == entries[h].state) {
				entries[h] = REMOVED;
				++removedCount;
				return;
			}
			h = (h + d++) & mask;
		}
	}

	/**
	 * Suorittaa uudelleenhajautuksen.
	 */
	private void rehash()
	{
		int newCapacity = capacity;
		if (reservedCount - removedCount >= capacity >> 1)
			newCapacity *= GROWTH_FACTOR;
		int newMask = newCapacity - 1;
		StateInfo[] newEntries = new StateInfo[newCapacity];

		copyEntries(newEntries, newMask);

		capacity = newCapacity;
		mask = newMask;
		entries = newEntries;
		reservedCount -= removedCount;
		removedCount = 0;
	}

	/**
	 * Kopioi tietueet uuteen taulukkoon hajautusarvojen mukaisesti.
	 */
	private void copyEntries(StateInfo[] newEntries, int newMask)
	{
		for (int i = 0; i < capacity; ++i) {
			if (entries[i] != null && entries[i] != REMOVED) {
				int h = (int) entries[i].state & newMask;
				int d = 1;
				while (newEntries[h] != null)
					h = (h + d++) & newMask;
				newEntries[h] = entries[i];
			}
		}
	}
}
