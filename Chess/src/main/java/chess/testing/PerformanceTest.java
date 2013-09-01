package chess.testing;

import chess.ai.MinMaxAI;
import chess.domain.GameGenerator;
import chess.domain.GameState;
import chess.util.InterruptableRunnable;
import chess.util.Logger;
import java.util.Random;

/**
 * Suorituskykytesti minmax-tekoälylle.
 */
public class PerformanceTest extends InterruptableRunnable
{
	/**
	 * Loggeri tulostetta varten.
	 */
	private Logger logger;

	/**
	 * Aloitussyvyys.
	 */
	private int startDepth;

	/**
	 * Kunkin iteraation pituus sekunteina.
	 */
	private double length;

	/**
	 * Iteraation aikana analysoitujen solmujen kokonäismäärä.
	 */
	private long totalNodes;

	private boolean qs;

	/**
	 * Konstruktori.
	 *
	 * @param logger loki
	 */
	public PerformanceTest(Logger logger, int startDepth, double length, boolean qs)
	{
		this.logger = logger;
		this.startDepth = startDepth;
		this.length = length;
		this.qs = qs;
	}

	/**
	 * Ajaa sarjan suorituskykytestejä tekoälylle eri hakusyvyyden arvoille. Kullakin hakusyvyydellä
	 * arvotaan satunnaisia pelitilanteita ja lasketaan niihin paras siirto, kunnes aikaa on
	 * käytetty vaadittu määrä. Hakusyvyyden kasvatus lopetetaan, jos annetussa ajassa ehdittiin
	 * analysoida vähemmän kuin 10 tilannetta.
	 *
	 * Jokaisella testikerralla käytetään samaa random-seediä, jotta testit olisivat paremmin
	 * vertailukelpoisia.
	 */
	@Override
	public void runImpl() throws InterruptedException
	{
		logger.logMessage("Running performance test...");

		int depth = startDepth;
		int n;
		do {
			Random rnd = new Random(12345);

			double totalTime = 0;
			totalNodes = 0;

			MinMaxAI ai = new MinMaxAI(logger, depth, qs ? 30 : 0, 0.0, 0);
			n = 0;
			while (totalTime < length) {
				totalTime += runSingleTest(ai, rnd.nextLong());
				++n;
			}

			double avgTime = totalTime * 1e3 / n;
			printStatistics(depth, n, avgTime);

			++depth;
		} while (n > 10);

		logger.logMessage("Test done.");
	}

	/**
	 * Arpoo satunnaisen pelitlanteen ja laskee siihen parhaan siirron MinMaxAI:n avulla.
	 *
	 * @param ai käytettävä tekoälyobjekti
	 * @param rnd Random-objekti satunnaisen pelitilanteen generoimiseksi
	 * @return palauttaa käytetyn ajan, poislukien pelitilanteen arpomiseen kulunut aika
	 */
	private double runSingleTest(MinMaxAI ai, long seed) throws InterruptedException
	{
		GameState state = GameGenerator.createGame(seed);

		long start = System.nanoTime();
		ai.getMove(state);
		totalNodes += ai.getNodeCount();
		return (System.nanoTime() - start) * 1e-9;
	}

	/**
	 * Tulostaa testin statistiikat.
	 */
	private void printStatistics(int depth, int n, double avgTime)
	{
		logger.logMessage(String.format("Depth: %d", depth));
		logger.logMessage(String.format("  Search count: %d", n));
		logger.logMessage(String.format("  Avg time: %.3fms", avgTime));
		logger.logMessage(String.format("  Avg nodes: %d", totalNodes / n));
//		logger.logMessage(String.format("d%d: %d %.3fms %.3g", depth, n, avgTime,
//				Math.pow(totalNodes / n, 1.0 / depth)));
	}
}
