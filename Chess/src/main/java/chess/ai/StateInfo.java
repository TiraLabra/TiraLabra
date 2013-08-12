package chess.ai;

/**
 * Transpositiotaulun tietue, joka tallentaa haun tulokset yhdessä pelitilanteessa.
 */
final class StateInfo
{
	/**
	 * Tietueeseen tallennettu pistemäärä on eksakti. Vastaa hakupuun solmua, jonka kaikki alipuut
	 * on käyty läpi, ja niiden paras arvo osuu avoimelle välille ]alfa,beta[. (PV-node, Type 1)
	 */
	static final int NODE_TYPE_EXACT = 0;

	/**
	 * Tietueeseen tallennettu pistemäärä on alaraja. Vastaa hakupuun solmua, jonka lapsisolmuista
	 * jonkin pistemäärä on betaa suurempi. (Cut-node, Type 2)
	 */
	static final int NODE_TYPE_LOWER_BOUND = 1;

	/**
	 * Tietueeseen tallennettu pistemäärä on yläraja. Vastaa hakupuun solmua, jonka lapsisolmuista
	 * minkään pistemäärä ei ole alfaa suurempi. (All-node (Type 3))
	 */
	static final int NODE_TYPE_UPPER_BOUND = 2;

	/**
	 * Pelitilanteen Zobrist-tunniste.
	 */
	final long state;

	/**
	 * Syvyys, johon asti pelitilanne on analysoitu.
	 */
	int depth;

	/**
	 * Paras löydetty pistemäärä.
	 */
	int score;

	/**
	 * Paras löydetty siirto koodattuna 32-bittisenä lukuna (ks Move), tai 0 jos siirtoa ei ole
	 * vielä löydetty.
	 */
	int bestMove;

	/**
	 * Hakupuun solmun tyyppi. Eksakti, alaraja tai yläraja. (0-2)
	 */
	int nodeType;

	/**
	 * Konstruktori.
	 *
	 * @param state pelitilanteen Zobrist-tunniste
	 */
	StateInfo(long state)
	{
		this.state = state;
	}
}
