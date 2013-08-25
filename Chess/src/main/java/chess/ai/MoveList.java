package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Movemasks;
import chess.domain.Pieces;
import java.util.Arrays;

/**
 * Ylläpitää sallituista siirroista listaa, joka on jaettu useampaan eri prioriteettiluokkaan.
 * Tarkoituksena on siirtojen tehokas järjestäminen alfa-beta-karsintaa varten.
 */
final class MoveList
{
	/**
	 * Eri prioriteettiluokkien kokonaismäärä.
	 */
	static final int PRIORITIES = 12;

	/**
	 * Eri lyöntien prioriteetit (CAPTURE_PRIORITIES[piece][capturedType]).
	 */
	static final int[][] CAPTURE_PRIORITIES = new int[][]{
		{0, 8, 8, 9, 9, 9},
		{0, 4, 5, 6, 6, 7},
		{0, 3, 4, 5, 5, 6},
		{0, 2, 3, 4, 4, 5},
		{0, 2, 3, 4, 4, 5},
		{0, 1, 2, 3, 3, 4}
	};

	/**
	 * Korotusten prioriteetit. (Muuksi kuin kuningattareksi korottaminen kannattaa vain harvoin,
	 * joten ne analysoidaan viimeisenä.)
	 */
	static final int[] PROMOTION_PRIORITIES = new int[]{-1, 3, 11, 11, 11, -1};

	/**
	 * Siirrot jaettuna useaan listaan siten, että jokaiselle prioriteetille on oma listansa.
	 * Maksimi siirtojen määrä missään positiossa on 218, joten 256 on riittävä taulukon koko.
	 */
	private final int[][] moves = new int[PRIORITIES][256];

	/**
	 * Siirtojen lukumäärä kussakin listassa.
	 */
	private final int[] moveCounts = new int[PRIORITIES];

	/**
	 * Täyttää siirtolistan sisällön annetusta pelitilanteesta.
	 *
	 * @param state pelitilanne
	 * @param excludeQuietMoves ainoastaan lyönnit
	 */
	void populate(GameState state, boolean excludeQuietMoves)
	{
		int player = state.getNextMovingPlayer();
		clear();

		// Muut kuin sotilaat.
		for (int pieceType = 0; pieceType < Pieces.COUNT - 1; ++pieceType) {
			long pieces = state.getPieces(player, pieceType);
			addMoves(state, pieceType, pieces, -1, excludeQuietMoves);
		}

		// Korotettavat sotilaat.
		long pieces = state.getPieces(player, Pieces.PAWN) & Movemasks.PROMOTABLE[player];
		if (pieces != 0) {
			for (int promotedType = Pieces.QUEEN; promotedType <= Pieces.KNIGHT; ++promotedType)
				addMoves(state, Pieces.PAWN, pieces, promotedType, excludeQuietMoves);
		}

		// Ei-korotettavat sotilaat.
		pieces = state.getPieces(player, Pieces.PAWN) & ~Movemasks.PROMOTABLE[player];
		addMoves(state, Pieces.PAWN, pieces, -1, excludeQuietMoves);
	}

	/**
	 * Lisää siirrot kaikille annetun tyyppisille nappuloille.
	 *
	 * @param state pelitilanne
	 * @param pieceType nappulatyyppi
	 * @param pieces nappuloiden sijainnit bittimaskina
	 * @param promotedType korotuksen tyyppi
	 * @param excludeQuietMoves ainostaan lyönnit
	 */
	private void addMoves(GameState state, int pieceType, long pieces, int promotedType,
			boolean excludeQuietMoves)
	{
		int player = state.getNextMovingPlayer();

		for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
			int fromSqr = Long.numberOfTrailingZeros(pieces);
			long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);

			if ((moves & state.getPieces(1 - player)) != 0) {
				for (int capturedType = 0; capturedType < Pieces.COUNT; ++capturedType) {
					long captureMoves = moves & state.getPieces(1 - player, capturedType);
					for (; captureMoves != 0; captureMoves -= Long.lowestOneBit(captureMoves)) {
						int toSqr = Long.numberOfTrailingZeros(captureMoves);
						add(pieceType, fromSqr, toSqr, capturedType, promotedType);
					}
				}
			}

			if (!excludeQuietMoves) {
				long quietMoves = moves & ~state.getPieces(1 - player);
				for (; quietMoves != 0; quietMoves -= Long.lowestOneBit(quietMoves)) {
					int toSqr = Long.numberOfTrailingZeros(quietMoves);
					add(pieceType, fromSqr, toSqr, -1, promotedType);
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
	int getCount(int priority)
	{
		return moveCounts[priority];
	}

	/**
	 * Palauttaa siirtojen lukumäärän annetussa prioriteettiluokassa.
	 *
	 * @param priority prioriteetti
	 * @return siirtojen lukumäärä
	 */
	int getMove(int priority, int idx)
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
	 * Lisää uuden siirron listaan. Prioriteetit asetetaan seruvaavasti:
	 * Korotukset kuningattareksi: 3
	 * Lyönnit: 0-9 (0 on PxK ja 9 on KxP)
	 * Normaalit siirrot: 10
	 * Korotukset torniksi/lähetiksi/ratsuksi: 11
	 */
	private void add(int pieceType, int fromSqr, int toSqr, int capturedType, int promotedType)
	{
		int priority = 10;
		if (capturedType != -1)
			priority = CAPTURE_PRIORITIES[pieceType][capturedType];
		else if (promotedType != -1)
			priority = PROMOTION_PRIORITIES[promotedType];
		int idx = moveCounts[priority]++;
		moves[priority][idx] = Move.pack(fromSqr, toSqr, pieceType, capturedType, promotedType);
	}
}
