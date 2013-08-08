package chess.ai;

import chess.domain.GameState;

/**
 * Rajapinta jonka eri tekoälyt toteuttavat.
 */
public interface AI
{
	/**
	 * Laskee ja suorittaa siirron annettuun pelitilanteeseen.
	 *
	 * @param state pelitilanne
	 */
	void move(GameState state);

	/**
	 *
	 */
	void setLoggingEnabled(boolean enabled);
}
