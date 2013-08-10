package chess.domain;

/**
 * Nappuloita vastaavat kokonaislukuvakiot.
 */
public final class Pieces
{
	/**
	 * Kuningas.
	 */
	public static final int KING = 0;

	/**
	 * Kuningatar.
	 */
	public static final int QUEEN = 1;

	/**
	 * Torni.
	 */
	public static final int ROOK = 2;

	/**
	 * Lähetti.
	 */
	public static final int BISHOP = 3;

	/**
	 * Ratsu.
	 */
	public static final int KNIGHT = 4;

	/**
	 * Sotilas.
	 */
	public static final int PAWN = 5;

	/**
	 * Nappulatyyppien lukumäärä.
	 */
	public static final int COUNT = 6;

	/**
	 * Nappuloiden arvot, joita käytetään pelitilanteiden pistemäärien arvioimiseen. Arvot on
	 * skaalattu 1000:lla, jotta pienempiä arvoja voidaan käyttää muita pisteytyskriteerejä
	 * varten.
	 *
	 * Myös kuninkaalle on annettu pistemäärä, koska MinMaxAI perustuu pseudolaillisiin siirtoihin,
	 * jotka voivat mahdollistaa kuninkaan lyönnin.
	 */
	public static final int[] values = new int[Pieces.COUNT];

	static {
		values[Pieces.KING] = 1000 * 1000;
		values[Pieces.QUEEN] = 9 * 1000;
		values[Pieces.ROOK] = 5 * 1000;
		values[Pieces.BISHOP] = 3 * 1000;
		values[Pieces.KNIGHT] = 3 * 1000;
		values[Pieces.PAWN] = 1 * 1000;
	}

	private Pieces()
	{
	}
}
