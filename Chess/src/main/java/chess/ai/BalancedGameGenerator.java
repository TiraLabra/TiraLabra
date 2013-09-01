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
	public static GameState createGame(long seed, double maxImbalance) throws InterruptedException
	{
		Random rnd = new Random();
		for (;;) {
			GameState gameState = GameGenerator.createGame(rnd.nextLong());
			MinMaxAI testAI = new MinMaxAI(null, 5, 30, 0.0, 0);
			testAI.getMove(gameState);
			int score = testAI.getSearchTree().score;
			if (Math.abs(score) < maxImbalance * Scores.PIECE_VALUES[Pieces.PAWN])
				return gameState;
		}
	}
}
