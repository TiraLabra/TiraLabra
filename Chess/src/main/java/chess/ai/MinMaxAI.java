package chess.ai;

import chess.domain.GameState;
import chess.domain.Move;
import chess.game.Player;
import chess.util.Logger;

/**
 * Minmaxiin ja alfa-beta-karsintaan perustuva tekoäly.
 */
public class MinMaxAI implements Player
{
	/**
	 * Oletusaikaraja (sekunteina), jos konstruktorissa ei ole annettu aikarajaa.
	 */
	private static final double DEFAULT_TIME_LIMIT = 2.0;

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
	 * Oletusarvo maksimihakusyvyydelle.
	 */
	private static final int DEFAULT_SEARCH_DEPTH = 100;

	/**
	 * Maksimi hakusyvyys. Pitää olla vähintään 2, jottei tekoäly suorita siirtoja jotka jättävät
	 * kuninkaan uhatuksi.
	 */
	private final int searchDepth;

	/**
	 * Poikkeus, joka heitetään aikarajan kuluessa umpeen.
	 */
	private static class TimeLimitException extends Exception
	{
	}

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
	private boolean loggingEnabled;

	/**
	 * Siirtojen määrä analysoitavan pelipuun juuresta.
	 */
	private int ply;

	/**
	 * Pistemäärä juurisolmussa.
	 */
	private int rootScore;

	/**
	 * Pino, johon hakujen tulokset kullakin pelipuun tasolla tallennetaan väliaikaisesti.
	 */
	private StateInfo[] results;

	/**
	 * Tallentaa eksplisiittisen pelipuun.
	 */
	private final TreeGenerator treeGenerator;

	/**
	 * Viimeisimmän iteraation aikana tallennettu puu.
	 */
	private Node tree;

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
	private final MoveList[] moveLists;

	/**
	 * Aloitusajankohta, josta aikaraja lasketaan.
	 */
	private long startTime;

	/**
	 * Evaluaattori pelitilanteen pisteyttämiseen.
	 */
	private final Evaluator evaluator;

	/**
	 * Luo uuden tekoälyobjektin käyttäen oletusaikarajaa. Hakusyvyys on rajoitettu ainoastaan
	 * aikarajan puitteissa.
	 *
	 * @param logger loggeri debug-viestejä varten
	 */
	public MinMaxAI(Logger logger)
	{
		this(logger, DEFAULT_SEARCH_DEPTH, DEFAULT_TIME_LIMIT, DEFAULT_TREE_GENERATION_DEPTH);
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
		this.results = new StateInfo[searchDepth + 1];
		this.moveLists = new MoveList[searchDepth + 1];
		this.ply = 0;
		this.loggingEnabled = false;
		this.evaluator = new Evaluator(searchDepth);
	}

	/**
	 * Laskee ja suorittaa siirron annettuun pelitilanteeseen. Suorittaa minmax-algoritmia
	 * iterative-deepening -menetelmää käyttäen. Hakusyvyyttä kasvatetaan joka iteraatiolla, kunnes
	 * aikaraja tulee vastaan.
	 *
	 * Transpositiotaulua ei resetoida iteraatioiden välillä, vaan aikaisemman iteraation tuloksia
	 * käytetään hakupuun läpikäyntijärjestyksen optimoinnissa.
	 *
	 * Lopullinen siirto sekä kaikki haun ilmoittamat debug-arvot annetaan viimeisestä onnistuneesta
	 * iteraatiosta.
	 *
	 * @param state pelitilanne
	 */
	@Override
	public int getMove(GameState state) throws InterruptedException
	{
		evaluator.reset(state);
		rootScore = evaluator.getScore();
		setEarlierStates(state);
		startTime = System.nanoTime();
		trposTable.clear();
		int bestMove = 0;
		GameState stateCopy = state.clone();
		int lastIterNodeCount = 0, lastIterTrPosTblHitCount = 0, lastIterTrposTblSize = 0;
		double lastIterBranchingFactor = 0.0;

		for (int depth = 2; depth <= searchDepth; ++depth) {
			if (!findMove(stateCopy, depth))
				break;

			lastIterNodeCount = nodeCount;
			lastIterTrPosTblHitCount = trposTblHitCount;
			lastIterTrposTblSize = trposTable.size();
			lastIterBranchingFactor = Math.pow(nodeCount, 1.0 / depth);
			bestMove = trposTable.get(state.getId()).bestMove;
		}

		log("nodeCount=" + lastIterNodeCount);
		log("trposTblHitCount=" + lastIterTrPosTblHitCount);
		log("trposTblSize=" + lastIterTrposTblSize);
		log(String.format("t=%.3fms", (System.nanoTime() - startTime) * 1e-6));
		log(String.format("branchingFactor=%.3g", lastIterBranchingFactor));

		return bestMove;
	}

	/**
	 * Suorittaa yhden iteraation iterative-deepening -hausta, eli käynnistää minmax-haun annettuun
	 * syvyyteen asti.
	 *
	 * @param state kopio alkuperäisestä pelitilasta
	 * @param depth hakusyvyys
	 * @return true, jos haku suoritettiin loppuun, false jos aikaraja tuli vastaan
	 */
	private boolean findMove(GameState state, int depth) throws InterruptedException
	{
		log("depth=" + depth);

		nodeCount = 0;
		trposTblHitCount = 0;
		ply = 0;
		treeGenerator.clear();
		evaluator.reset(state);

		try {
			createNodeAndSearch(depth, Scores.MIN, Scores.MAX, state, 0);
		} catch (TimeLimitException e) {
			return false;
		}

		tree = treeGenerator.getTree();

		return true;
	}

	/**
	 * Apufunktio, joka tallentaa hakupuun solmun tiedot ja kutsuu varsinaista hakufunktiota.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @param move hakupuun solmua edeltävä siirto (0 jos juurisolmu tai nollasiirto)
	 * @return palauttaa parhaan löydetyn pistemäärän
	 * @throws chess.ai.MinMaxAI.TimeLimitException
	 */
	private int createNodeAndSearch(int depth, int alpha, int beta, GameState state,
			int move) throws TimeLimitException, InterruptedException
	{
		treeGenerator.startNode(alpha, beta, state.getNextMovingPlayer(), move);

		int score = search(depth, alpha, beta, state);

		int nodeType = results[ply] != null ? results[ply].nodeType : -1;
		treeGenerator.endNode(score, nodeType);

		return score;
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
	 * @throws TimeLimitException kun aikaraja tulee täyteen
	 */
	private int search(int depth, int alpha, int beta, GameState state)
			throws TimeLimitException, InterruptedException
	{
		++nodeCount;

		checkTimeLimit();

		// Jos aikaisempaan pelitilanteeseen saavutaan uudestaan, pattitilanteiden välttämiseksi
		// (tai saavuttamiseksi) näille annetaan tasapeliä vastaava pistearvo.
		if (earlierStates.get(state.getId()) != null && ply > 0)
			return Scores.DRAW;

		if (depth == 0 || !state.areBothKingsAlive())
			return evaluator.getScore();

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
		results[ply].score = Scores.MIN;
		earlierStates.put(results[ply]);

		depth = applyNullMoveReduction(depth, beta, state);
		searchAllMoves(depth, alpha, beta, state, info != null ? info.bestMove : 0);

		earlierStates.remove(state.getId());

		results[ply].score = applyScoreDepthAdjustment(results[ply].score, state);

		// Tietue lisätään transpositiotauluun vasta jälkikäteen, koska on mahdollista
		// että haun aikana tullaan samaan pelitilanteeseen uudestaan.
		addTranspositionTableEntry(depth, results[ply]);

		return results[ply].score;
	}

	/**
	 * Haku, jossa hakuikkuna on rajattu pienimpään mahdolliseen, eli [beta - 1, beta]. Mahdollistaa
	 * nopeamman haun silloin, kun tarvitsee ainoastaan todistaa, että haun lopputulos on suurempi
	 * tai yhtä suuri kuin beeta.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param beta beeta-arvo
	 * @param state pelitila
	 * @return palauttaa parhaan löydetyn pistemäärän
	 */
	private int zeroWindowSearch(int depth, int beta, GameState state, int move)
			throws TimeLimitException, InterruptedException
	{
		return createNodeAndSearch(depth, beta - 1, beta, state, move);
	}

	/**
	 * Käy läpi kaikki mahdolliset siirrot.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @param tptblMove transpositiotaulusta löydetty aiempi paras siirto tai 0, jos ei löytynyt
	 */
	private void searchAllMoves(int depth, int alpha, int beta, GameState state, int tpTblMove)
			throws TimeLimitException, InterruptedException
	{
		// Jos hakutauluun on tallennettu paras siirto, kokeillaan sitä ensimmäisenä.
		if (tpTblMove != 0) {
			alpha = searchMove(depth, alpha, beta, state, tpTblMove);
			if (alpha >= beta)
				return;
		}

		// Muodostetaan priorisoitu siirtolista.
		if (moveLists[ply] == null)
			moveLists[ply] = new MoveList();
		moveLists[ply].populate(state);

		for (int i = 0; i < MoveList.PRIORITIES; ++i) {
			int count = moveLists[ply].getCount(i);
			for (int j = 0; j < count; ++j) {
				int move = moveLists[ply].getMove(i, j);
				if (move == tpTblMove) // Ei etsitä tätä uudestaan!
					continue;
				alpha = searchMove(depth, alpha, beta, state, move);
				if (alpha >= beta)
					return;
			}
		}

		// Pattitilanteiden tunnistus.
		if (results[ply].score < -Scores.CHECK_MATE_THRESHOLD && state.isStaleMate())
			results[ply].score = 0;
	}

	/**
	 * Suorittaa pelitilanteeseen yksittäisen siirron, ja jatkaa hakua rekursiivisesti. Haun jälkeen
	 * pelitila palautetaan alkuperäiseen tilaan.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @param move siirto
	 * @return uusi alfa-arvo
	 */
	private int searchMove(int depth, int alpha, int beta, GameState state, int move)
			throws TimeLimitException, InterruptedException
	{
		++ply;
		state.move(move);
		evaluator.makeMove(move);
		int score;
		if (results[ply - 1].nodeType == StateInfo.NODE_TYPE_UPPER_BOUND) {
			// Etsitään normaalisti niin kauan kunnes löydetään arvo välillä ]alfa,beta[
			score = -createNodeAndSearch(depth - 1, -beta, -alpha, state, move);
		} else {
			// Lopuille solmuille tarkistetaan vain, että pistemäärä on korkeintaan alfa (tai
			// aiheuttaa beta-cutoffin). Jos ei, niin suoritetaan normaali haku.
			score = -zeroWindowSearch(depth - 1, -alpha, state, move);
			if (score > alpha && score < beta)
				score = -createNodeAndSearch(depth - 1, -beta, -alpha, state, move);
		}
		evaluator.undoMove();
		state.undoMove(move);
		--ply;

		if (score > results[ply].score) {
			results[ply].score = score;
			results[ply].bestMove = move;
			if (score > alpha) {
				if (ply == 0 && loggingEnabled)
					log("  " + Move.toString(move) + " " + (score - rootScore));
				if (score >= beta)
					results[ply].nodeType = StateInfo.NODE_TYPE_LOWER_BOUND;
				else
					results[ply].nodeType = StateInfo.NODE_TYPE_EXACT;
				alpha = score;
			}
		}

		return alpha;
	}

	/**
	 * Asettaa loki-informaation tuottamisen päälle tai pois päältä.
	 */
	public void setLoggingEnabled(boolean enabled)
	{
		loggingEnabled = enabled;
	}

	/**
	 * Nollasiirtoredusointi. Skippaa yhden vuoron, ja tarkistaa aiheuttaako uusi tilanne
	 * beeta-leikkauksen. Jos aiheuttaa, niin pienennetään varsinaista hakusyvyyttä.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitilanne
	 * @return uusi syvyysarvo
	 */
	private int applyNullMoveReduction(int depth, int beta, GameState state)
			throws TimeLimitException, InterruptedException
	{
		if (depth >= NULL_MOVE_REDUCTION1 + 1) {
			state.nullMove();
			evaluator.makeNullMove();
			++ply;
			int score = -zeroWindowSearch(depth - NULL_MOVE_REDUCTION1 - 1, 1 - beta, state, 0);
			--ply;
			evaluator.undoMove();
			state.nullMove();
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
		return tree;
	}

	/**
	 * Lisää tietueen tranpositiotauluun, jos sen koko ei ylitä maksimikokoa.
	 *
	 * @param depth analysoitu syvyys
	 * @param result hakua vastaava tietue
	 */
	private void addTranspositionTableEntry(int depth, StateInfo result)
	{
		if (trposTable.size() < MAX_TRANSPOSITION_TABLE_SIZE) {
			result.depth = depth;
			trposTable.put(result);
		}
	}

	/**
	 * Tarkistaa tietyin väliajoin onko aikaraja kulunut umpeen, ja jos on niin heittää poikkeuksen.
	 *
	 * @throws chess.ai.MinMaxAI.TimeLimitException
	 * @throws InterruptedException
	 */
	private void checkTimeLimit() throws TimeLimitException, InterruptedException
	{
		if ((nodeCount & 0xfff) == 0 && timeLimit != 0) {
			if (Thread.interrupted())
				throw new InterruptedException();
			double t = (System.nanoTime() - startTime) * 1e-9;
			if (t > timeLimit) {
				log(String.format("  time limit (%.1fms)", t * 1e3));
				throw new TimeLimitException();
			}
		}
	}
}
