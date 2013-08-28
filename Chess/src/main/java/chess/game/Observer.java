package chess.game;

import chess.domain.GameState;

/**
 * Rajapinta, jonka välityksellä peliluokka antaa tietoa siirroista ja pelitilanteesta.
 */
public interface Observer
{
	/**
	 * Kutsutaan kun jompikumpi pelaaja on tehnyt siirron.
	 *
	 * @param state pelitilanne siirron jälkeen
	 * @param ply puolisiirtojen lukumäärä pelin alusta
	 * @param player siirron tehnyt pelaaja
	 * @param move siirto (ks. Move)
	 */
	void notifyMove(GameState state, int ply, Player player, int move) throws InterruptedException;

	/**
	 * Kutsutaan kun peli on päättynyt.
	 *
	 * @param state pelitilanne viimeisen siirron jälkeen
	 * @param result voittanut pelaaja (0-1) tai -1 jos pattitilanne
	 */
	void notifyEnd(GameState state, int result);
}
