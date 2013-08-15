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
	 * Asettaa loki-informaation tuottamisen päälle tai pois päältä.
	 */
	void setLoggingEnabled(boolean enabled);
}
