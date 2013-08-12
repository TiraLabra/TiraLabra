package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import chess.util.Logger;

/**
 * Minmaxiin ja alfa-beta-karsintaan perustuva tekoäly.
 */
public class MinMaxAI implements AI
{
	/**
	 * Oletusaikaraja (sekunteina), jos konstruktorissa ei ole annettu aikarajaa.
	 */
	private static final double DEFAULT_TIME_LIMIT = 2.0;

	/**
	 * Estimoitu haarautumiskerroin, jota käytetään suoritusajaan arviointiin.
	 */
	private static final double ESTIMATED_BRANCHING_FACTOR = 5;

	/**
	 * Kuinka monta tasoa hakupuuta pienennetään nollasiirron kanssa. Oletuksena on, että siirron
	 * skippaaminen heikentää asemaa enemmän kuin hakusyvyyden pienentäminen tämän verran.
	 */
	private static final int NULL_MOVE_REDUCTION1 = 2;

	/**
	 * Jos haku nollasiirron kanssa palauttaa betaa suuremman pistemäärän (liian hyvä siirto),
	 * varsinaista hakusyvyyttä pienennetään tämän verran. Hakusyvyyttä ainoastaan pienennetään
	 * sen sijaan että se skipattaisiin kokonaan mahdollisten zugzwang-positioiden takia.
	 */
	private static final int NULL_MOVE_REDUCTION2 = 4;

	/**
	 * Transpositiotaulun maksimikoko. Rajoituksena tälle on transpositiotaulun viemän muistin
	 * määrä.
	 */
	private static final int MAX_TRANSPOSITION_TABLE_SIZE = 1024 * 1024;

	/**
	 * Oletussyvyys puun tallentamiseksi käyttöliittymää varten.
	 */
	private static final int DEFAULT_TREE_GENERATION_DEPTH = 3;

	/**
	 * Maksimi hakusyvyys. Pitää olla vähintään 2, jottei tekoäly suorita siirtoja jotka jättävät
	 * kuninkaan uhatuksi.
	 */
	private final int searchDepth;

	/**
	 * Transpositiotaulu johon tallennetaan jo analysoidut tilanteet ja parhaat siirrot niissä.
	 */
	private TranspositionTable trposTable = new TranspositionTable();

	/**
	 * Hakutaulu aikaisempia pelitilanteita varten.
	 */
	private TranspositionTable earlierStates = new TranspositionTable(512);

	/**
	 * Aikaraja (sekunteina) parhaan siirron etsimiselle tai 0 jos aikarajaa ei ole. Hakuajan
	 * rajoitus perustuu seuraavan iteraation keston arviointiin, joten tämä ei välttämättä ole
	 * tarkka rajoitus.
	 */
	private double timeLimit;

	/**
	 * Logger-objekti debug-viestejä varten.
	 */
	private final Logger logger;

	/**
	 * Kirjoitetaanko debuggausinformaatiota lokiin vai ei.
	 */
	private boolean loggingEnabled = false;

	/**
	 * Siirtojen määrä analysoitavan pelipuun juuresta.
	 */
	private int ply = 0;

	/**
	 * Pistemäärä juurisolmussa.
	 */
	private int rootScore;

	/**
	 * Pino, johon hakujen tulokset kullakin pelipuun tasolla tallennetaan väliaikaisesti.
	 */
	private StateInfo[] results = new StateInfo[1024];

	/**
	 * Tallentaa eksplisiittisen pelipuun.
	 */
	private TreeGenerator treeGenerator;

	/**
	 * Analysoitujen pelipuun solmujen määrä.
	 */
	private int nodeCount;

	/**
	 * Transpositiotaulusta löydettyjen positioiden lukumäärä.
	 */
	private int trposTblHitCount;

	/**
	 * Pino siirtolistoista (välttää uudelleenallokoinnin jokaisessa solmussa).
	 */
	private MoveList[] moveLists = new MoveList[100];

	/**
	 * Luo uuden tekoälyobjektin käyttäen oletusaikarajaa. Hakusyvyys on rajoitettu ainoastaan
	 * aikarajan puitteissa.
	 *
	 * @param logger loggeri debug-viestejä varten
	 */
	public MinMaxAI(Logger logger)
	{
		this(logger, Integer.MAX_VALUE, DEFAULT_TIME_LIMIT, DEFAULT_TREE_GENERATION_DEPTH);
	}

	/**
	 * Luo uuden tekoälyobjektin käyttäen annettua aikarajaa ja maksimihakusyvyyttä.
	 *
	 * @param logger loggeri debug-viestejä varten
	 * @param searchDepth maksimi hakusyvyys
	 * @param timeLimit aikaraja yhden parhaan siirron laskemiselle
	 * @param treeGenerationDepth syvyys, johon asti pelipuu tallennetaan
	 */
	public MinMaxAI(Logger logger, int searchDepth, double timeLimit, int treeGenerationDepth)
	{
		if (searchDepth < 2)
			throw new IllegalArgumentException("Search depth too small.");
		this.logger = logger;
		this.searchDepth = searchDepth;
		this.timeLimit = timeLimit;
		this.treeGenerator = new TreeGenerator(treeGenerationDepth);
	}

	/**
	 * Laskee ja suorittaa siirron annettuun pelitilanteeseen. Suorittaa minmax-algoritmia
	 * iterative-deepening -menetelmää käyttäen. Hakusyvyyttä kasvatetaan joka iteraatiolla, kunnes
	 * arvioidaan, että seuraava iteraatio ylittäisi aikarajan.
	 *
	 * Transpositiotaulua ei resetoida iteraatioiden välillä, vaan aikaisemman iteraation tuloksia
	 * käytetään hakupuun läpikäyntijärjestyksen optimoinnissa.
	 *
	 * @param state pelitilanne
	 */
	@Override
	public void move(GameState state)
	{
		rootScore = getScore(state);
		setEarlierStates(state);

		long start = System.nanoTime();
		trposTable.clear();
		int depth;
		StateInfo info = null;
		for (depth = 2; depth <= searchDepth; ++depth) {
			log("depth=" + depth);

			nodeCount = 0;
			trposTblHitCount = 0;
			search(depth, Scores.MIN, Scores.MAX, state);
			info = trposTable.get(state.getId());
			treeGenerator.endNode(0, info.score, 0);

			double elapsedTime = (System.nanoTime() - start) * 1e-9;
			if (timeLimit > 0 && elapsedTime * ESTIMATED_BRANCHING_FACTOR > timeLimit)
				break;
		}

		log("nodeCount=" + nodeCount);
		log("trposTblHitCount=" + trposTblHitCount);
		log("trposTblSize=" + trposTable.size());
		log(String.format("t=%.3fms", (System.nanoTime() - start) * 1e-6));
		log(String.format("branchingFactor=%.3g", Math.pow(nodeCount, 1.0 / depth)));

		state.move(info.bestMove);
	}

	/**
	 * Minmax-haun pääfunktio. Toteutettu negamax-hakuna, eli samaa funktiota käytetään molemmille
	 * pelaajille, ja tarvitsee ainostaan negatoida pistemäärät ja vaihtaa alfa/beta-parametrit
	 * keskenään.
	 *
	 * Lisäksi käytetään alfa-beta karsintaa. Alfa vastaa parhaan tähän mennessä löydetyn siirron
	 * pistemäärää, ja beta vastaa parasta vastustajan siirtoa (beta >= alfa). Jos löydetään
	 * betaa suurempi pistearvo, voidaan ko. alipuun etsintä lopettaa, koska vastustaja olisi
	 * voinut estää tilanteen jo aikaisemmin (ts. tilanne on liian hyvä).
	 *
	 * Alfa-beta-karsinnan tehokkaan toiminnan vuoksi on tärkeää, että kussakin tilanteessa
	 * parhaat siirrot käydään läpi mahdollisimman aikaisessa vaiheessa.
	 *
	 * Funktio tarkistaa ensin transpositiotaulusta onko annettu pelitilanne jo analysoitu vaadituun
	 * syvyyteen asti. Jos on analysoitu, ja tallennetun solmun tyyppi on sopiva, voidaan
	 * tallennettu pistemäärä palauttaa.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @return palauttaa parhaan löydetyn pistemäärän
	 */
	private int search(int depth, int alpha, int beta, GameState state)
	{
		++nodeCount;

		treeGenerator.startNode(alpha, beta, state.getNextMovingPlayer());

		// Jos aikaisempaan pelitilanteeseen saavutaan uudestaan, pattitilanteiden välttämiseksi
		// (tai saavuttamiseksi) näille annetaan tasapeliä vastaava pistearvo.
		if (earlierStates.get(state.getId()) != null)
			return Scores.DRAW;

		if (depth == 0 || !state.areBothKingsAlive())
			return getScore(state);

		StateInfo info = trposTable.get(state.getId());
		if (info != null && info.depth >= depth) {
			++trposTblHitCount;
			if (info.nodeType == StateInfo.NODE_TYPE_EXACT
					|| info.nodeType == StateInfo.NODE_TYPE_LOWER_BOUND && info.score >= beta
					|| info.nodeType == StateInfo.NODE_TYPE_UPPER_BOUND && info.score <= alpha)
				return info.score;
		}

		results[ply] = new StateInfo(state.getId());
		results[ply].nodeType = StateInfo.NODE_TYPE_UPPER_BOUND;
		earlierStates.put(results[ply]);

		depth = applyNullMoveReduction(depth, alpha, beta, state);
		int score = searchAllMoves(depth, alpha, beta, state, info);

		earlierStates.remove(state.getId());

		score = applyScoreDepthAdjustment(score, state);

		// Tietue lisätään transpositiotauluun vasta jälkikäteen, koska on mahdollista
		// että haun aikana tullaan samaan pelitilanteeseen uudestaan.
		addTranspositionTableEntry(depth, score, results[ply]);

		return score;
	}

	/**
	 * Käy läpi kaikki mahdolliset siirrot.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @param info aiemman haun tulos tai null, jos positiota ei löytynyt transpositiotaulusta
	 * @return palauttaa parhaan löydetyn pistemäärän
	 */
	private int searchAllMoves(int depth, int alpha, int beta, GameState state, StateInfo info)
	{
		int player = state.getNextMovingPlayer();

		// Jos hakutauluun on tallennettu paras siirto, kokeillaan sitä ensimmäisenä.
		int tptblMove = 0;
		if (info != null && info.bestMove != 0) {
			tptblMove = info.bestMove;
			alpha = searchMove(depth, alpha, beta, state, info.bestMove);
			if (alpha >= beta) {
				results[ply].nodeType = StateInfo.NODE_TYPE_LOWER_BOUND;
				return alpha;
			}
		}

		if (moveLists[ply] == null)
			moveLists[ply] = new MoveList();
		moveLists[ply].populate(state);

		for (int i = 0; i < MoveList.PRIORITIES; ++i) {
			int count = moveLists[ply].getCount(i);
			for (int j = 0; j < count; ++j) {
				int move = moveLists[ply].getMove(i, j);
				if (move == tptblMove) // Ei etsitä tätä uudestaan!
					continue;
				alpha = searchMove(depth, alpha, beta, state, move);
				if (alpha >= beta) {
					results[ply].nodeType = StateInfo.NODE_TYPE_LOWER_BOUND;
					return alpha;
				}
			}
		}

		if (alpha < -Scores.CHECK_MATE_THRESHOLD && state.isStaleMate())
			return 0;

		return alpha;
	}

	/**
	 * Suorittaa pelitilanteeseen yksittäisen siirron, ja jatkaa hakua rekursiivisesti. Haun jälkeen
	 * pelitila palautetaan alkuperäiseen tilaan.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @param pieceType siirrettävän nappulan tyyppi
	 * @param fromSqr siirrettävän nappulan vanha sijainti
	 * @param toSqr sirrettävän nappulan uusi sijainti
	 * @return uusi alfa-arvo
	 */
	private int searchMove(int depth, int alpha, int beta, GameState state, int move)
	{
		++ply;
		state.move(move);
		int score = -search(depth - 1, -beta, -alpha, state);
		state.undoMove(move);
		--ply;

		int nodeType = results[ply + 1] != null ? results[ply + 1].nodeType : -1;
		treeGenerator.endNode(move, -score, nodeType);

		if (score > alpha) {
			if (ply == 0 && loggingEnabled) {
				log("" + Move.getFromSqr(move) + " " + Move.getToSqr(move) + " "
						+ (score - rootScore));
			}
			results[ply].bestMove = move;
			results[ply].nodeType = StateInfo.NODE_TYPE_EXACT;
			alpha = score;
		}

		return alpha;
	}

	/**
	 * Laskee pistemäärän pelitilanteelle, perustuen pelaajien nappuloiden yhteisarvojen erotukseen.
	 * Lisäksi tasapisteissä painotetaan hieman tilannetta, jossa nappuloiden kokonaismäärä
	 * laudalle on pienempi. Tällä saadaan tekoälystä aggressiivisempi; ilman avauskirjastoa tms.
	 * alkupeli on muutoin liian passiivinen.
	 *
	 * @param state pelitila
	 * @return pistemäärä
	 */
	int getScore(GameState state)
	{
		int player = state.getNextMovingPlayer();
		int score = 0;
		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			int pieceValue = Scores.PIECE_VALUES[pieceType];
			score += Long.bitCount(state.getPieces(player, pieceType)) * pieceValue;
			score -= Long.bitCount(state.getPieces(1 - player, pieceType)) * pieceValue;
		}
		score -= Long.bitCount(state.getPieces(player) | state.getPieces(1 - player));
		return score;
	}

	@Override
	public void setLoggingEnabled(boolean enabled)
	{
		loggingEnabled = enabled;
	}

	/**
	 * Nollasiirtoredusointi.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitilanne
	 * @return uusi syvyysarvo
	 */
	private int applyNullMoveReduction(int depth, int alpha, int beta, GameState state)
	{
		if (depth >= NULL_MOVE_REDUCTION1 + 1) {
			state.nullMove();
			++ply;
			int score = -search(depth - NULL_MOVE_REDUCTION1 - 1, -beta, -alpha, state);
			--ply;
			state.nullMove();
			treeGenerator.endNode(0, -score, 0);
			if (score >= beta)
				depth = Math.max(depth - NULL_MOVE_REDUCTION2, 1);
		}
		return depth;
	}

	/**
	 * Lisää uuden viestin lokiin.
	 *
	 * @param msg viesti
	 */
	private void log(String msg)
	{
		if (loggingEnabled)
			logger.logMessage(msg);
	}

	/**
	 * Palauttaa analysoitujen pelipuun solmujen määrän edellisessä move()-kutsussa.
	 *
	 * @return
	 */
	public int getNodeCount()
	{
		return nodeCount;
	}

	/**
	 * Päivittää taulun, joka sisältää aikaisemmat pelitilanteet.
	 *
	 * @param state pelitilanne
	 */
	private void setEarlierStates(GameState state)
	{
		earlierStates.clear();
		long[] states = state.getEarlierStates();
		for (int i = 0; i < states.length; ++i)
			earlierStates.put(new StateInfo(states[i]));
	}

	/**
	 * Pienentää siirron pistemäärää jokaista siirtoa kohden niin, että jos usealla siirrolla
	 * päästään samanarvoiseeen lopputilanteeseen, valitaan se, jonka vaatima siirtomäärä on pienin.
	 * (Ja vastustaja pyrkii viivyttämään sitä.) Erityisesti mattiin joutumista tulee viivyttää niin
	 * pitkään kuin mahdollista; muutoin voi aiheutua laiton siirto tilanteessa, jossa matti on
	 * välttämätön.
	 *
	 * @param score parhaan siirron pistemäärä (alfa)
	 * @param state pistemäärä ennen siirtoa
	 */
	private int applyScoreDepthAdjustment(int score, GameState state)
	{
		if (score > Scores.CHECK_MATE_THRESHOLD)
			score -= Scores.CHECK_MATE_DEPTH_ADJUSTMENT;
//		else {
//			int currentScore = getScore(state);
//			if (score > currentScore)
//				score -= SCORE_DEPTH_ADJUSTMENT;
//		}
		return score;
	}

	/**
	 * Palauttaa tallennetun pelipuun.
	 *
	 * @return
	 */
	public Node getGameTree()
	{
		return treeGenerator.getTree();
	}

	/**
	 * Lisää tietueen tranpositiotauluun, jos sen koko ei ylitä maksimikokoa.
	 *
	 * @param depth analysoitu syvyys
	 * @param score haun palauttama pistemäärä
	 * @param result hakua vastaava tietue
	 */
	private void addTranspositionTableEntry(int depth, int score, StateInfo result)
	{
		if (trposTable.size() < MAX_TRANSPOSITION_TABLE_SIZE) {
			result.depth = depth;
			result.score = score;
			trposTable.put(result);
		}
	}
}
