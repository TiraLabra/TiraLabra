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
	 * @param newType nappulan uusi tyyppi (sama kuin pieceType, jos siirto ei ole korotus)
	 * @return pakattu siirto
	 */
	public static int pack(int fromSqr, int toSqr, int pieceType, int capturedType, int newType)
	{
		++capturedType;
		return fromSqr | toSqr << 8 | pieceType << 16 | capturedType << 20 | newType << 24;
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
	 * Palauttaa nappulan uuden tyypin (sama kuin alkuperäinen nappula jos siirto ei ole korotus).
	 *
	 * @param move pakattu siirto
	 * @return
	 */
	public static int getNewType(int move)
	{
		return move >> 24 & 0x7;
	}

	/**
	 * Muuntaa siirron merkkijonoksi käyttäen pitkää algebrallista notaatiota (esim "Qb3xc4").
	 *
	 * @param move pakattu siirto
	 * @return
	 */
	public static String toString(int move)
	{
		String ret = Pieces.SYMBOLS[getPieceType(move)] + sqrToStr(getFromSqr(move));
		ret += getCapturedType(move) >= 0 ? "x" + Pieces.SYMBOLS[getCapturedType(move)] : "-";
		ret += sqrToStr(getToSqr(move));
		if (getNewType(move) != getPieceType(move))
			ret += Pieces.SYMBOLS[getNewType(move)];
		return ret;
	}

	/**
	 * Luo siirron sen merkkijonoesityksestä.
	 *
	 * @param s siirto merkkijonona (esim. "Ke3xNf4")
	 * @return siirto
	 */
	public static int fromString(String s)
	{
		if (!s.matches("[KQRBN]?[a-h][1-8](-|x[KQRBN]?)[a-h][1-8][QRBN]?"))
			throw new IllegalArgumentException("Invalid move format.");

		int i = 0;

		int pieceType = Pieces.PAWN;
		if (Character.isUpperCase(s.charAt(i)))
			pieceType = Pieces.fromString(Character.toString(s.charAt(i++)));

		int fromSqr = s.charAt(i++) - 'a';
		fromSqr += ('8' - s.charAt(i++)) * 8;

		int capturedType = -1;
		if (s.charAt(i++) == 'x') {
			capturedType = Pieces.PAWN;
			if (Character.isUpperCase(s.charAt(i)))
				capturedType = Pieces.fromString(Character.toString(s.charAt(i++)));
		}

		int toSqr = s.charAt(i++) - 'a';
		toSqr += ('8' - s.charAt(i++)) * 8;

		int newType = pieceType;
		if (i != s.length()) {
			if (pieceType != Pieces.PAWN)
				throw new IllegalArgumentException("Invalid move format.");
			newType = Pieces.fromString(Character.toString(s.charAt(i++)));
		}

		return pack(fromSqr, toSqr, pieceType, capturedType, newType);
	}

	/**
	 * Muuntaa ruudun merkkijonoksi (a1-h8).
	 */
	private static String sqrToStr(int sqr)
	{
		return "" + (char) ('a' + sqr % 8) + (char) ('8' - sqr / 8);
	}
}
