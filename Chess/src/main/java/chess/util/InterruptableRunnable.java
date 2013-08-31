package chess.util;

/**
 * Wrapperi yksinkertaistamaan keskeytettävien Runnable-luokkien toteutusta.
 */
public abstract class InterruptableRunnable implements Runnable
{
	@Override
	public void run()
	{
		try {
			runImpl();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Varsinainen toteutus run-funktiolle.
	 */
	public abstract void runImpl() throws InterruptedException;
}
