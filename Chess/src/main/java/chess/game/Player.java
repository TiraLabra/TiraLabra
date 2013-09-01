package chess.game;

import chess.domain.GameState;

/**
 * Rajapinta, jonka eri pelaajatyypit toteuttavat (tekoälyt + ihmispelaajan toteutus
 * käyttöliittymässä).
 */
public interface Player
{
	/**
	 * Palauttaa pelaajan siirron annettuun pelitilanteeseen.
	 *
	 * @param state pelitilanne
	 * @return siirto (ks. Move)
	 */
	int getMove(GameState state) throws InterruptedException;
}
