package chess.ai;

import chess.domain.GameState;
import chess.util.Logger;
import java.util.Random;

public class PerformanceTest implements Runnable
{
	private Logger logger;

	public PerformanceTest(Logger logger)
	{
		this.logger = logger;
	}

	@Override
	public void run()
	{
		logger.logMessage("Running test...");

		int depth = 2;
		int n;
		do {
			Random rnd = new Random(123456);

			double totalTime = 0;

			n = 0;
			while (totalTime < 5.0) {
				totalTime += runSingleTest(depth, rnd);
				++n;
			}

			double avgTime = totalTime * 1e3 / n;
			logger.logMessage(String.format("d=%d n=%d t=%.3fms", depth, n, avgTime));

			++depth;
		} while (n > 10);

		logger.logMessage("Test done.");
	}

	private double runSingleTest(int depth, Random rnd)
	{
		GameState state = new GameState(rnd);
		AI ai = new MinMaxAI(logger, depth, 0.0);

		long start = System.nanoTime();
		ai.move(state);
		return (System.nanoTime() - start) * 1e-9;
	}
}
