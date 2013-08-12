package chess.domain;

/**
 * Apufunktiot siirtojen pakkaamiseksi 32-bittiseen kokonaislukuun, pakatun siirron purkamiseksi,
 * sekä siirron muuttamiseksi merkkijonomuotoon.
 */
public final class Move
{
	/**
	 * Pakkaa siirron yhteen kokonaislukumuuttujaan.
	 *
	 * @param fromSqr lähtöruutu
	 * @param toSqr kohderuutu
	 * @param pieceType siirrettävän nappulan tyyppi
	 * @param capturedType lyödyn nappulan tyyppi tai -1 jos kohderuutu on tyhjä
	 * @param promotedType nappula, joho
	 * @return pakattu siirto
	 */
	public static int pack(int fromSqr, int toSqr, int pieceType, int capturedType,
			int promotedType)
	{
		++capturedType;
		++promotedType;
		return fromSqr | toSqr << 8 | pieceType << 16 | capturedType << 20 | promotedType << 24;
	}

	/**
	 * Palauttaa siirron lähtöruudun.
	 *
	 * @param move pakattu siirto
	 * @return
	 */
	public static int getFromSqr(int move)
	{
		return move & 0xff;
	}

	/**
	 * Palauttaa siirron kohderuudun.
	 *
	 * @param move pakattu siirto
	 * @return
	 */
	public static int getToSqr(int move)
	{
		return move >> 8 & 0xff;
	}

	/**
	 * Palauttaa siirrettävän nappulan tyypin.
	 *
	 * @param move pakattu siirto
	 * @return
	 */
	public static int getPieceType(int move)
	{
		return move >> 16 & 0x7;
	}

	/**
	 * Palauttaa lyödyn nappulan tyypin tai -1 jos sitä ei ole.
	 *
	 * @param move pakattu siirto
	 * @return
	 */
	public static int getCapturedType(int move)
	{
		return (move >> 20 & 0x7) - 1;
	}

	/**
	 * Palauttaa korotetun nappulan tyypin tai -1 jos sitä ei ole.
	 *
	 * @param move pakattu siirto
	 * @return
	 */
	public static int getPromotedType(int move)
	{
		return (move >> 24 & 0x7) - 1;
	}

	/**
	 * Muuntaa siirron merkkijonoksi käyttäen pitkää algebrallista notaatiota (esim "Qb3xc4").
	 *
	 * @param move pakattu siirto
	 * @return
	 */
	public static String toString(int move)
	{
		String ret = Pieces.symbols[getPieceType(move)] + sqrToStr(getFromSqr(move));
		ret += (getCapturedType(move) >= 0 ? "x" + Pieces.symbols[getCapturedType(move)] : "-");
		return ret + sqrToStr(getToSqr(move));
	}

	/**
	 * Muuntaa ruudun merkkijonoksi (a1-h8).
	 */
	private static String sqrToStr(int sqr)
	{
		return "" + (char) ('a' + sqr % 8) + (char) ('8' - sqr / 8);
	}

	private Move()
	{
	}
}
