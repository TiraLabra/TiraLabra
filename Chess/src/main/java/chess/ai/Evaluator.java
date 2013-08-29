package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;

/**
 * Ylläpitää pelitilanteen pistemäärää inkrementaalisesti (päivitetään ilmoittamalla tehdyt
 * siirrot). Pistemäärä lasketaan nappuloiden arvon ja sijainnin perusteella. Pisteytys on
 * symmetrinen, eli toisen pelaajan pistemäärä on toisen vastaluku.
 */
final class Evaluator
{
	/**
	 * Puolisiirtojen määrä aloitustilanteesta.
	 */
	private int ply;

	/**
	 * Pino pelitilanteiden pistemääristä.
	 */
	private int[] scores;

	/**
	 * Vuorossa oleva pelaaja.
	 */
	private int player;

	/**
	 * Luo uuden evaluaattorin.
	 *
	 * @param maxDepth maksimisyvyys
	 */
	Evaluator(int maxDepth)
	{
		ply = 0;
		scores = new int[maxDepth + 1];
	}

	/**
	 * Asettaa juurisolmua vastaavan pelitilanteen ja laskee sen pistemäärän. Juurisolmun
	 * pistemäärä voisi periaatteessa olla 0, mutta debuggauksen kannalta absoluuttinen pistemäärä
	 * on hyödyllisempi.
	 *
	 * @param state juurisolmun pelitila
	 */
	void reset(GameState state)
	{
		ply = 0;
		scores[ply] = 0;
		player = state.getNextMovingPlayer();

		for (int pl = 0; pl < 2; ++pl) {
			for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
				long pieces = state.getPieces((player + pl) % 2, pieceType);
				for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
					int sqr = Long.numberOfTrailingZeros(pieces);
					int value = Scores.POSITIONAL_PIECE_VALUES[(player + pl) % 2][pieceType][sqr];
					scores[ply] += (1 - 2 * pl) * value;
				}
			}
		}
	}

	/**
	 * Palauttaa tämänhetkisen pelaajan absoluuttisen pistemäärän.
	 *
	 * @return
	 */
	int getScore()
	{
		return scores[ply];
	}

	/**
	 * Palauttaa tämänhetkisen pelaajan pistemäärän suhteessa alkutilanteeseen.
	 *
	 * @return
	 */
	int getRelativeScore()
	{
		return scores[ply] - (1 - 2 * (ply & 1)) * scores[0];
	}

	/**
	 * Päivittää pistemäärää annetun siirron mukaisesti.
	 *
	 * @param move pakattu siirto
	 */
	void makeMove(int move)
	{
		int score = scores[ply];

		int fromSqr = Move.getFromSqr(move);
		int toSqr = Move.getToSqr(move);
		int pieceType = Move.getPieceType(move);
		int capturedType = Move.getCapturedType(move);
		int newType = Move.getNewType(move);

		score -= Scores.POSITIONAL_PIECE_VALUES[player][pieceType][fromSqr];
		score += Scores.POSITIONAL_PIECE_VALUES[player][newType][toSqr];
		if (capturedType >= 0)
			score += Scores.POSITIONAL_PIECE_VALUES[1 - player][capturedType][toSqr];

		// Tornituksien ja ohestalyöntien aiheuttamat positiomuutokset tulisi periaatteessa myös
		// huomoida, mutta hyöty on pieni verrattuna tehokkuusmenetykseen.

		scores[++ply] = -score;
		player = 1 - player;
	}

	/**
	 * Tekee nollasiirron, eli pistemäärä ainoastaan vaihdetaan vastaluvuksi, ja vuoro vaihtuu
	 * toiselle pelaajalle.
	 */
	void makeNullMove()
	{
		int score = scores[ply];
		scores[++ply] = -score;
		player = 1 - player;
	}

	/**
	 * Kumoaa edellisen siirron vaikutukset pistemäärään.
	 */
	void undoMove()
	{
		--ply;
		player = 1 - player;
	}
}
