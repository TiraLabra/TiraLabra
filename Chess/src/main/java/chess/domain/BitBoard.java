package chess.domain;

import java.util.Arrays;

/**
 * Tietorakenne nappuloiden sijaintien tallentamiseen 64-bittisinä maskeina. Maskit tallennetaan
 * row-major formaatissa, niin että bitti 0 vastaa laudan vasenta yläkulmaan (a8) ja bitti 63
 * oikeaa alakulmaa (h1).
 *
 * Tallentaa 8 maskia: 6 eri nappulatyypeille ja 2 pelaajille. Tietyn pelaajan tietyn tyyppiset
 * nappulat saadaa AND-operaatiolla nappulatyypin maskin ja pelaajan maskin välillä.
 */
public final class BitBoard
{
	/**
	 * Maskit kullekin nappulatyypille, ottaen huomioon pelaajan värin. Valkoiset nappulat 0-5,
	 * ja mustat 6-11.
	 */
	private final long[] pieces = new long[Pieces.COUNT];

	/**
	 * Maskit kummankin pelaajan kaikille nappuloille. Valkoinen 0, musta 1.
	 */
	private final long[] playerPieces = new long[Players.COUNT];

	/**
	 * Luo tyhjän laudan.
	 */
	public BitBoard()
	{
	}

	/**
	 * Luo uuden laudan, missä nappuloiden sijainnit on annettu merkkijonoina (esim
	 * "Ka8 Bc7").
	 *
	 * @param whitePieces valkoiset nappulat
	 * @param blackPieces mustat nappulat
	 */
	public BitBoard(String whitePieces, String blackPieces)
	{
		addPieces(Players.WHITE, whitePieces);
		addPieces(Players.BLACK, blackPieces);
	}

	/**
	 * Tyhjentää laudan sisällön.
	 */
	public void clear()
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece)
			pieces[piece] = 0;
		playerPieces[Players.WHITE] = 0;
		playerPieces[Players.BLACK] = 0;
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
		pieces[piece] |= sqrBit;
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
		pieces[piece] &= ~sqrBit;
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
		return playerPieces[player] & pieces[piece];
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
		return (playerPieces[player] & pieces[piece] & (1L << sqr)) != 0;
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
	 * Palauttaa nappulan tyypin ruudussa.
	 *
	 * @param player pelaaja (0-1)
	 * @param sqr ruutu (0-63)
	 * @return nappulatyyppi (0-5) tai -1 jos ruutu tyhjä
	 */
	public int getPieceType(int player, int sqr)
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			if (hasPiece(player, piece, sqr))
				return piece;
		}
		return -1;
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

		for (int player = 0; player < Players.COUNT; ++player) {
			for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
				for (int sqr = 0; sqr < 64; ++sqr) {
					if (hasPiece(player, pieceType, sqr))
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
		for (int piece = 0; piece < Pieces.COUNT; ++piece)
			pieces[piece] = source.pieces[piece];
		playerPieces[Players.WHITE] = source.playerPieces[Players.WHITE];
		playerPieces[Players.BLACK] = source.playerPieces[Players.BLACK];
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
		if (playerPieces[Players.WHITE] != bb2.playerPieces[Players.WHITE]
				|| playerPieces[Players.BLACK] != bb2.playerPieces[Players.BLACK])
			return false;
		for (int i = Pieces.COUNT - 1; i >= 0; --i) {
			if (pieces[i] != bb2.pieces[i])
				return false;
		}

		return true;
	}

	/**
	 * Lisää pelaajan nappulat merkkijonosta.
	 *
	 * @param player pelaaja
	 * @param pieces nappulat merkkijonona
	 */
	private void addPieces(int player, String pieces)
	{
		int i = 0;
		while (i < pieces.length()) {
			int pieceType = Pieces.PAWN;
			if (Character.isUpperCase(pieces.charAt(i)))
				pieceType = Pieces.fromString(Character.toString(pieces.charAt(i++)));
			int sqr = pieces.charAt(i++) - 'a';
			sqr += ('8' - pieces.charAt(i++)) * 8;
			addPiece(player, pieceType, sqr);
			++i;
		}
	}
}
