package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import java.util.Arrays;

/**
 * Ylläpitää sallituista siirroista listaa, joka on jaettu useampaan eri prioriteettiluokkaan.
 * Tarkoituksena on siirtojen tehokas järjestäminen alfa-beta-karsintaa varten.
 */
public final class MoveList
{
	/**
	 * Eri prioriteettiluokkien kokonaismäärä.
	 */
	public static final int PRIORITIES = 12;

	/**
	 * Siirrot jaettuna useaan listaan siten, että jokaiselle prioriteetille on oma listansa.
	 */
	private final int[][] moves = new int[PRIORITIES][512];

	/**
	 * Siirtojen lukumäärä kussakin listassa.
	 */
	private final int[] moveCounts = new int[PRIORITIES];

	/**
	 * Täyttää siirtolistan sisällön annetusta pelitilanteesta.
	 *
	 * @param state pelitilanne
	 */
	public void populate(GameState state)
	{
		int player = state.getNextMovingPlayer();
		clear();

		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			long pieces = state.getPieces(player, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int fromSqr = Long.numberOfTrailingZeros(pieces);
				long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);
				for (int capturedType = 0; capturedType < Pieces.COUNT; ++capturedType) {
					long captureMoves = moves & state.getPieces(1 - player, capturedType);
					for (; captureMoves != 0; captureMoves -= Long.lowestOneBit(captureMoves)) {
						int toSqr = Long.numberOfTrailingZeros(captureMoves);
						add(pieceType, fromSqr, toSqr, capturedType, -1);
					}
				}

				long quietMoves = moves & ~state.getPieces(1 - player);
				for (; quietMoves != 0; quietMoves -= Long.lowestOneBit(quietMoves)) {
					int toSqr = Long.numberOfTrailingZeros(quietMoves);
					add(pieceType, fromSqr, toSqr, -1, -1);
				}
			}
		}
	}

	/**
	 * Palauttaa siirtojen lukumäärän annetussa prioriteettiluokassa.
	 *
	 * @param priority prioriteetti
	 * @return siirtojen lukumäärä
	 */
	public int getCount(int priority)
	{
		return moveCounts[priority];
	}

	/**
	 * Palauttaa siirtojen lukumäärän annetussa prioriteettiluokassa.
	 *
	 * @param priority prioriteetti
	 * @return siirtojen lukumäärä
	 */
	public int getMove(int priority, int idx)
	{
		return moves[priority][idx];
	}

	/**
	 * Tyhjentää siirtolistan.
	 */
	private void clear()
	{
		Arrays.fill(moveCounts, 0);
	}

	/**
	 * Lisää uuden siirron listaan. Prioriteettiluokat on jaettu siten, että ensimmäisenä (0-10)
	 * ovat lyönnit järjestettynä materiaalierotuksen mukaan, ja viimeisenä (11) tavalliset siirrot.
	 */
	private void add(int pieceType, int fromSqr, int toSqr, int capturedType, int promotedType)
	{
		int priority = 11;
		if (capturedType != -1)
			priority = capturedType - pieceType + 5;
		int idx = moveCounts[priority]++;
		moves[priority][idx] = Move.pack(fromSqr, toSqr, pieceType, capturedType, promotedType);
	}
}
