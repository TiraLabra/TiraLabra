package chess.ai;

import chess.domain.GameGenerator;
import chess.domain.GameState;
import chess.domain.Pieces;
import java.util.Random;

/**
 * Luo satunnaisen pelitilanteen, joka on mahdollisimman tasapuolinen. Tilanteen arvioimiseen
 * käytetään MinMaxAI:ta.
 */
public final class BalancedGameGenerator
{
	/**
	 * Luo uuden tasapuolisen pelitilanteen.
	 *
	 * @param seed satunnaislukusiemen
	 * @param maxImbalance suurin sallittu epätasapaino pisteissä (yksikkönä yksi sotilas)
	 * @return pelitilanne
	 */
	public static GameState createGame(long seed, double maxImbalance)
	{
		Random rnd = new Random();
		for (;;) {
			GameState gameState = GameGenerator.createGame(rnd.nextLong());
			MinMaxAI testAI = new MinMaxAI(null, 5, 30, 0.05, 0);
			try {
				testAI.getMove(gameState);
			} catch (InterruptedException e) {
			}
			int score = testAI.getGameTree().score;
			if (Math.abs(score) < maxImbalance * Scores.PIECE_VALUES[Pieces.PAWN])
				return gameState;
		}
	}
}
