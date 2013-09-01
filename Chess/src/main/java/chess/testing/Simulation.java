package chess.testing;

import chess.ai.BalancedGameGenerator;
import chess.domain.GameState;
import chess.game.Game;
import chess.game.Player;
import chess.util.InterruptableRunnable;
import chess.util.Logger;
import java.util.Random;

/**
 * Simulaattoriluokka useiden pelien peluuttamiseksi kahden (tekoäly)pelaajan välillä.
 */
public class Simulation extends InterruptableRunnable
{
	/**
	 * Loggeri tulostetta varten.
	 */
	private Logger logger;

	/**
	 * Simulaation pituus.
	 */
	private double length;

	/**
	 * Pelaajaobjektit.
	 */
	private final Player[] players = new Player[2];

	/**
	 * Konstruktori.
	 *
	 * @param logger loki
	 * @param length simulaation pituus sekunteina, tai 0 jos aikarajaa ei ole Dou
	 * @param player1
	 * @param player2
	 */
	public Simulation(Logger logger, double length, Player player1, Player player2)
	{
		this.logger = logger;
		this.length = length;
		this.players[0] = player1;
		this.players[1] = player2;
	}

	/**
	 * Simuloi sarjan pelejä, kunnes aikaraja tulee vastaan tai simulointi keskeytään. Jokainen
	 * pelitilanne pelataan kahdesti, vaihtaen pelaajien paikkaa.
	 */
	@Override
	public void runImpl() throws InterruptedException
	{
		logger.logMessage("Running simulation...");
		logger.logMessage("[Score (AvgScore\u00b1Error)]");

		Random rnd = new Random();
		double sum = 0; // Pisteiden summa
		double sqrSum = 0; // Pisteiden neliöiden summa
		int count = 0;
		long start = System.nanoTime();

		do {
			GameState state = BalancedGameGenerator.createGame(rnd.nextLong(), 1.0);
			int score = runGame(state.clone(), players[0], players[1]);
			score -= runGame(state.clone(), players[1], players[0]);

			++count;
			sum += score;
			sqrSum += score * score;

			printStatistics(score, count, sum, sqrSum);
		} while (length == 0.0 || (System.nanoTime() - start) * 1e-9 < length);

		logger.logMessage("Simulation done.");
	}

	/**
	 * Simuloi yhden pelin.
	 *
	 * @param state pelin alkutilanne
	 * @param white valkoinen pelaaja
	 * @param black musta pelaaja
	 * @return valkoisen pelaajan pistemäärä (1=voitto, 0=tasapeli, -1=tappio)
	 * @throws InterruptedException
	 */
	private int runGame(GameState state, Player white, Player black) throws InterruptedException
	{
		Game currentGame = new Game(state, white, black, null);

		currentGame.run();
		if (Thread.interrupted())
			throw new InterruptedException();

		int result = currentGame.getResult();
		if (result == 0)
			return 1;
		else if (result == 1)
			return -1;
		else
			return 0;
	}

	/**
	 * Tulostaa peliparin pistemäärän, kaikkien pelien keskipistemäärän sekä 95% luottamusvälin
	 * (noin 2*keskihajonta/sqrt(n)).
	 */
	private void printStatistics(int score, int count, double sum, double sqrSum)
	{
		double avg = sum / count;
		double stdev = Math.sqrt((sqrSum - sum * sum / count) / (count - 1));
		double error = 2 * stdev / Math.sqrt(count);
		logger.logMessage(String.format("#%d: %+d (%.3f\u00b1%.3f)", count, score, avg, error));
	}
}
