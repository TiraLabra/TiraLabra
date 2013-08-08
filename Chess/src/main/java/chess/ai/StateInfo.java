package chess.ai;

/**
 * Transpositiotaulun tietue, joka tallentaa haun tulokset yhdessä pelitilanteessa.
 */
final class StateInfo
{
	/**
	 * Syvyys, johon asti pelitilanne on analysoitu.
	 */
	int depth;

	/**
	 * Paras löydetty pistemäärä.
	 */
	int score;

	/**
	 * Parhaan siirron lähtöruutu, tai -1 jos yhtään siirtoa ei ole vielä löydetty (esim jos
	 * tilanne aiheuttaa beta-cutoffin).
	 */
	int bestMoveFrom = -1;

	/**
	 * Parhaan löydetyn siirron kohderuutu.
	 */
	int bestMoveTo;

	/**
	 * Nappulan tyyppi parhaassa siirrossa.
	 */
	int bestMovePieceType;
}
