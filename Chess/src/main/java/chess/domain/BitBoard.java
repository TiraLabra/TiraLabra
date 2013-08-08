package chess.domain;

import java.util.Arrays;

/**
 * Tietorakenne nappuloiden sijaintien tallentamiseen 64-bittisinä maskeina. Maskit tallennetaan
 * row-major formaatissa, niin että bitti 0 vastaa laudan vasenta yläkulmaan (a8) ja bitti 63
 * oikeaa alakulmaa (h1).
 */
public final class BitBoard
{
	/**
	 * Maskit kullekin nappulatyypille, ottaen huomioon pelaajan värin. Valkoiset nappulat 0-5,
	 * ja mustat 6-11.
	 */
	private final long[] pieces = new long[2 * Pieces.COUNT];

	/**
	 * Maskit kummankin pelaajan kaikille nappuloille. Valkoinen 0, musta 1.
	 */
	private final long[] playerPieces = new long[2];

	/**
	 * Tyhjentää laudan sisällön.
	 */
	public void clear()
	{
		for (int player = 0; player < 2; ++player) {
			for (int piece = 0; piece < Pieces.COUNT; ++piece)
				pieces[player * Pieces.COUNT + piece] = 0;
			playerPieces[player] = 0;
		}
	}

	/**
	 * Lisää laudalle nappulan.
	 *
	 * @param player pelaaja (0-1)
	 * @param piece nappulatyyppi (0-5)
	 * @param sqr ruutu (0-63)
	 */
	public void addPiece(int player, int piece, int sqr)
	{
		long sqrBit = 1L << sqr;
		pieces[player * Pieces.COUNT + piece] |= sqrBit;
		playerPieces[player] |= sqrBit;
	}

	/**
	 * Poistaa laudalta nappulan.
	 *
	 * @param player pelaaja (0-1)
	 * @param piece nappulatyyppi (0-5)
	 * @param sqr ruutu (0-63)
	 */
	public void removePiece(int player, int piece, int sqr)
	{
		long sqrBit = 1L << sqr;
		pieces[player * Pieces.COUNT + piece] &= ~sqrBit;
		playerPieces[player] &= ~sqrBit;
	}

	/**
	 * Poistaa laudalta nappulan, kun sen tyyppiä ei tiedetä.
	 *
	 * @param player pelaaja (0-1)
	 * @param sqr ruutu (0-63)
	 * @return palauttaa poistetun nappulan tyypin, tai -1 jos ruudussa ei ollut nappulaa
	 */
	public int removePiece(int player, int sqr)
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			if (hasPiece(player, piece, sqr)) {
				removePiece(player, piece, sqr);
				return piece;
			}
		}
		return -1;
	}

	/**
	 * Palauttaa kaikki pelaajan tietyntyyppiset nappulat bittimaskina.
	 *
	 * @param player pelaaja (0-1)
	 * @param piece nappulatyyppi (0-5)
	 */
	public long getPieces(int player, int piece)
	{
		return pieces[player * Pieces.COUNT + piece];
	}

	/**
	 * Palauttaa kaikki pelaajan nappulat bittimaskina.
	 *
	 * @param player pelaaja (0-1)
	 */
	public long getPieces(int player)
	{
		return playerPieces[player];
	}

	/**
	 * Tarkistaa, onko pelaajalla tietyn tyyppinen nappula annetussa ruudussa.
	 *
	 * @param player pelaaja (0-1)
	 * @param piece nappulatyyppi (0-5)
	 * @param sqr ruutu (0-63)
	 * @return true, jos nappula löytyi
	 */
	public boolean hasPiece(int player, int piece, int sqr)
	{
		return (pieces[player * Pieces.COUNT + piece] & (1L << sqr)) != 0;
	}

	/**
	 * Tarkistaa, onko pelaajalla jokin nappula annetussa ruudussa.
	 *
	 * @param player pelaaja (0-1)
	 * @param sqr ruutu (0-63)
	 * @return true, jos nappula löytyi
	 */
	public boolean hasPiece(int player, int sqr)
	{
		return (playerPieces[player] & (1L << sqr)) != 0;
	}

	/**
	 * Tarkistaa, onko annetussa ruudussa jomman kumman pelaajan nappula.
	 *
	 * @param sqr ruutu (0-63)
	 * @return true, jos nappula löytyi
	 */
	public boolean hasPiece(int sqr)
	{
		return ((playerPieces[Players.WHITE] | playerPieces[Players.BLACK]) & (1L << sqr)) != 0;
	}

	/**
	 * Muodostaa laudasta 64-alkoisen taulukon. Kunkin alkion arvo on nappulan tyyppi ko. ruudussa
	 * (valk. 0-5, musta 6-11), tai -1 jos ruutu on tyhjä.
	 *
	 * @return laudan sisältö taulukkona
	 */
	public int[] toArray()
	{
		int[] board = new int[64];
		Arrays.fill(board, -1);

		for (int player = 0; player < 2; ++player) {
			for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
				for (int sqr = 0; sqr < 64; ++sqr) {
					if ((pieces[player * Pieces.COUNT + pieceType] & (1L << sqr)) != 0)
						board[sqr] = player * Pieces.COUNT + pieceType;
				}
			}
		}

		return board;
	}

	/**
	 * Kopoi laudan sisällön toisesta BitBoard-objektista.
	 *
	 * @param source kopioinnin lähde
	 */
	public void copyFrom(BitBoard source)
	{
		for (int player = 0; player < 2; ++player) {
			for (int piece = 0; piece < Pieces.COUNT; ++piece) {
				int p = player * Pieces.COUNT + piece;
				pieces[p] = source.pieces[p];
			}
			playerPieces[player] = source.playerPieces[player];
		}
	}

	/**
	 * Vertaa laudan sisältöä toiseen.
	 *
	 * @param obj toinen BitBoard-objekti
	 * @return palauttaa tosi jos ja vain jos jokaisen ruudun sisältö on täsmälleen sama
	 */
	@Override
	public boolean equals(Object obj)
	{
		BitBoard bb2 = (BitBoard) obj;
		for (int i = Pieces.COUNT - 1; i >= 0; --i) {
			if (pieces[i] != bb2.pieces[i])
				return false;
			if (pieces[Pieces.COUNT + i] != bb2.pieces[Pieces.COUNT + i])
				return false;
		}

		return true;
	}
}
