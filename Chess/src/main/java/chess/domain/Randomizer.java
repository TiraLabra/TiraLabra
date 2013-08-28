package chess.domain;

import java.util.Random;

/**
 * Satunnaisen pelitilanteen luominen.
 */
public final class Randomizer
{
	/**
	 * Luo satunnaisen pelitilanteen. Jos pelitilanne ei ole laillinen, arvotaan uusi niin kauan
	 * kunnes laillinen tilanne löytyy.
	 *
	 * @param rnd satunnaisgeneraattori
	 */
	public static GameState createGame(Random rnd)
	{
		GameState state;
		do {
			BitBoard board = new BitBoard();
			addRandomizedPieces(board, 1, 1, Pieces.KING, rnd);
			addRandomizedPieces(board, 0, 1, Pieces.QUEEN, rnd);
			addRandomizedPieces(board, 0, 2, Pieces.ROOK, rnd);
			addRandomizedPieces(board, 0, 2, Pieces.BISHOP, rnd);
			addRandomizedPieces(board, 0, 2, Pieces.KNIGHT, rnd);
			addRandomizedPieces(board, 0, 8, Pieces.PAWN, rnd);
			state = new GameState(board, Players.WHITE);
		} while (state.isCheckMate() || state.isStaleMate() || state.isKingChecked(Players.BLACK));
		return state;
	}

	/**
	 * Lisää satunnaisen määrän tietyn tyyppisiä nappuloita kummallekin pelaajalle.
	 *
	 * @param board lauta
	 * @param min minimimäärä
	 * @param max maksimimäärä
	 * @param pieceType tyyppi
	 * @param rnd satunnaisgeneraattori
	 */
	private static void addRandomizedPieces(BitBoard board, int min, int max, int pieceType,
			Random rnd)
	{
		for (int player = 0; player < 2; ++player) {
			int n = min + rnd.nextInt(1 + max - min);
			for (int i = 0; i < n; ++i) {
				int sqr;
				do {
					// Ei sotilaita ensimmäiselle tai viimeiselle riville.
					sqr = pieceType != Pieces.PAWN ? rnd.nextInt(64) : 8 + rnd.nextInt(48);
				} while (board.hasPiece(sqr));
				board.addPiece(player, pieceType, sqr);
			}
		}
	}
}
