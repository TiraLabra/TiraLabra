package chess.ai;

import chess.domain.GameState;
import chess.util.Logger;
import java.util.Random;

/**
 * Suorituskykytesti minmax-tekoälylle.
 */
public class PerformanceTest implements Runnable
{
	/**
	 * Loggeri tulostetta varten.
	 */
	private Logger logger;

	private int startDepth;

	private double length;

	/**
	 * Konstruktori.
	 *
	 * @param logger loki
	 */
	public PerformanceTest(Logger logger, int startDepth, double length)
	{
		this.logger = logger;
		this.startDepth = startDepth;
		this.length = length;
	}

	/**
	 * Ajaa sarjan suorituskykytestejä tekoälylle eri hakusyvyyden arvoille. Kullakin hakusyvyydellä
	 * arvotaan satunnaisia pelitilanteita ja lasketaan niihin paras siirto, kunnes aikaa on
	 * käytetty n. 5 sekuntia. Hakusyvyyden kasvatus lopetetaan, jos annetussa ajassa ehdittiin
	 * analysoida vähemmän kuin 10 tilannetta.
	 *
	 * Jokaisella testikerralla käytetään samaa random-seediä, jotta testit olisivat paremmin
	 * vertailukelpoisia.
	 */
	@Override
	public void run()
	{
		logger.logMessage("Running test...");

		int depth = startDepth;
		int n;
		do {
			Random rnd = new Random(123456);

			double totalTime = 0;

			n = 0;
			while (totalTime < length) {
				totalTime += runSingleTest(depth, rnd);
				++n;
			}

			double avgTime = totalTime * 1e3 / n;
			logger.logMessage(String.format("d=%d n=%d t=%.3fms", depth, n, avgTime));

			++depth;
		} while (n > 10);

		logger.logMessage("Test done.");
	}

	/**
	 * Arpoo satunnaisen pelitlanteen ja laskee siihen parhaan siirron MinMaxAI:n avulla.
	 *
	 * @param depth käytettävä hakusyvyys
	 * @param rnd Random-objekti satunnaisen pelitilanteen generoimiseksi
	 * @return palauttaa käytetyn ajan, poislukien pelitilanteen arpomiseen kulunut aika
	 */
	private double runSingleTest(int depth, Random rnd)
	{
		GameState state = new GameState(rnd);
		AI ai = new MinMaxAI(logger, depth, 0.0);

		long start = System.nanoTime();
		ai.move(state);
		return (System.nanoTime() - start) * 1e-9;
	}
}
