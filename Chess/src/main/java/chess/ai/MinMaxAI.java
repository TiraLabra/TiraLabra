package chess.ai;

import chess.domain.GameState;
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
	private static final double DEFAULT_TIME_LIMIT = 3.0;

	/**
	 * Estimoitu haarautumiskerroin, jota käytetään suoritusajaan arviointiin.
	 */
	private static final double ESTIMATED_BRANCHING_FACTOR = 3.5;

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
	 * Maksimi hakusyvyys. Pitää olla vähintään 2, jottei tekoäly suorita siirtoja jotka jättävät
	 * kuninkaan uhatuksi.
	 */
	private final int searchDepth;

	/**
	 * Hetkellinen hakusyvyys, jota kasvatetaan vähitellen (iterative deepening search).
	 */
	private int currentSearchDepth;

	/**
	 * Transpositiotaulu johon tallennetaan jo analysoidut tilanteet ja parhaat siirrot niissä.
	 */
	private TranspositionTable trposTable = new TranspositionTable();

	/**
	 * Syvyys, johon asti transpositiotaulu tallennetaan. Rajoituksena tälle on transpositiotaulun
	 * viemän muistin määrä.
	 */
	private final int trposDepth = 6;

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
	 * Evaluoitujen positioiden määrä (getScore()-kutsut) debuggausta varten.
	 */
	private int count;

	/**
	 * Transpositiotaulusta löydettyjen positioiden lukumäärä.
	 */
	private int trposTblHitCount;

	/**
	 * Luo uuden tekoälyobjektin käyttäen oletusaikarajaa. Hakusyvyys on rajoitettu ainoastaan
	 * aikarajan puitteissa.
	 *
	 * @param logger loggeri debug-viestejä varten
	 */
	public MinMaxAI(Logger logger)
	{
		this(logger, Integer.MAX_VALUE, DEFAULT_TIME_LIMIT);
	}

	/**
	 * Luo uuden tekoälyobjektin käyttäen annettua aikarajaa ja maksimihakusyvyyttä.
	 *
	 * @param logger loggeri debug-viestejä varten
	 * @param searchDepth maksimi hakusyvyys
	 * @param timeLimit aikaraja yhden parhaan siirron laskemiselle
	 */
	public MinMaxAI(Logger logger, int searchDepth, double timeLimit)
	{
		if (searchDepth < 2)
			throw new IllegalArgumentException("Search depth too small.");
		this.logger = logger;
		this.searchDepth = searchDepth;
		this.timeLimit = timeLimit;
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
		long start = System.nanoTime();
		trposTable.clear();
		for (int d = 2; d <= searchDepth; ++d) {
			count = 0;
			trposTblHitCount = 0;
			currentSearchDepth = d;
			searchWithTranspositionLookup(d, -Integer.MAX_VALUE, Integer.MAX_VALUE, state);

			//StateInfo info = transposTable.get(state);
			//log("d=" + d + " best=" + info.bestMoveFrom + "->" + info.bestMoveTo);
			log("d=" + d);

			double elapsedTime = (System.nanoTime() - start) * 1e-9;
			if (timeLimit > 0 && elapsedTime * ESTIMATED_BRANCHING_FACTOR > timeLimit)
				break;
		}

		log("count=" + count);
		log("trposTblHitCount=" + trposTblHitCount);
		log("trposTblSize=" + trposTable.size());
		log(String.format("t=%.3fms", (System.nanoTime() - start) * 1e-6));

		StateInfo info = trposTable.get(state.getId());
		state.move(info.bestMoveFrom, info.bestMoveTo);
	}

	/**
	 * Tarkistaa transpositiotaulusta onko annettu pelitilanne jo analysoitu vaadituun syvyyteen
	 * asti. Jos ei ole analysoitu, tai syvyys hakutaulussa on liian pieni, analysoidaan tilanne
	 * kutsumalla varsinaista minmax-hakufunktiota (search()).
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @return palauttaa parhaan löydetyn pistemäärän
	 */
	private int searchWithTranspositionLookup(int depth, int alpha, int beta, GameState state)
	{
		boolean add = false;
		StateInfo info = trposTable.get(state.getId());
		if (info != null) {
			if (info.depth >= depth) {
				++trposTblHitCount;
				return info.score;
			}
		} else if (depth >= currentSearchDepth - trposDepth) {
			info = new StateInfo(state.getId());
			add = true;
		}

		int score = search(depth, alpha, beta, state, info);

		if (info != null) {
			info.depth = depth;
			info.score = score;
			// Tietue lisätään transpositiotauluun vasta jälkikäteen, koska on mahdollista
			// että haun aikana tullaan samaan pelitilanteeseen uudestaan.
			if (add)
				trposTable.put(info);
		}

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
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @param info transpositiotaulun tietue johon paras siirto/pistemäärä tallennetaan (null, jos
	 * kyseistä positiota ei tallenneta transpositiotauluun)
	 * @return palauttaa parhaan löydetyn pistemäärän
	 */
	private int search(int depth, int alpha, int beta, GameState state, StateInfo info)
	{
		if (depth == 0 || !state.areBothKingsAlive())
			return getScore(state, depth);

		int player = state.getNextMovingPlayer();

		// Nollasiirtoredusointi.
		if (depth >= NULL_MOVE_REDUCTION1 + 1) {
			state.nullMove();
			int score = -searchWithTranspositionLookup(depth - NULL_MOVE_REDUCTION1 - 1, -beta,
					-alpha, state);
			state.nullMove();
			if (score >= beta)
				depth = Math.max(depth - NULL_MOVE_REDUCTION2, 1);
		}

		// Jos hakutauluun on tallennettu paras siirto, kokeillaan sitä ensimmäisenä.
		if (info != null && info.bestMoveFrom != -1) {
			alpha = searchMove(depth, alpha, beta, state, info, info.bestMovePieceType,
					info.bestMoveFrom, info.bestMoveTo);
			if (alpha >= beta)
				return beta;
		}

		// Etsitään ensimmäisenä kaikki lyönnit.
		for (int pieceType = Pieces.COUNT - 1; pieceType >= 0; --pieceType) {
			long pieces = state.getPieces(player, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int fromSqr = Long.numberOfTrailingZeros(pieces);
				long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);

				for (int capturedPiece = 0; capturedPiece < Pieces.COUNT; ++capturedPiece) {
					long captureMoves = moves & state.getPieces(1 - player, capturedPiece);
					alpha = iterateMoves(depth, alpha, beta, state, info, pieceType, fromSqr,
							captureMoves);
					if (alpha >= beta)
						return beta;
				}
			}
		}

		// Viimeisenä siirrot, joissa ei tapahdu materiaalimuutoksia.
		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			long pieces = state.getPieces(player, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int fromSqr = Long.numberOfTrailingZeros(pieces);
				long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);
				moves &= ~state.getPieces(1 - player);
				alpha = iterateMoves(depth, alpha, beta, state, info, pieceType, fromSqr, moves);
				if (alpha >= beta)
					return beta;
			}
		}

		return alpha;
	}

	/**
	 * Käy läpi kaikki bittimaskissa annetut siirrot tietylle nappulalle.
	 *
	 * @param depth vaadittava jäljellä oleva hakusyvyys
	 * @param alpha alfa-beta-karsinnan alfa-arvo
	 * @param beta alfa-beta-karsinnan beta-arvo
	 * @param state pelitila
	 * @param info transpositiotaulun tietue johon paras siirto/pistemäärä tallennetaan (null, jos
	 * kyseistä positiota ei tallenneta transpositiotauluun)
	 * @param pieceType siirrettävän nappulan tyyppi
	 * @param fromSqr siirrettävän nappulan sijainti
	 * @param moves siirrot 64-bittisenä bittimaskina
	 * @return uusi alfa-arvo
	 */
	private int iterateMoves(int depth, int alpha, int beta, GameState state, StateInfo info,
			int pieceType, int fromSqr, long moves)
	{
		for (; moves != 0; moves -= Long.lowestOneBit(moves)) {
			int toSqr = Long.numberOfTrailingZeros(moves);
			alpha = searchMove(depth, alpha, beta, state, info, pieceType, fromSqr, toSqr);
			if (alpha >= beta)
				return beta;
		}

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
	 * @param info transpositiotaulun tietue johon paras siirto/pistemäärä tallennetaan (null, jos
	 * kyseistä positiota ei tallenneta transpositiotauluun)
	 * @param pieceType siirrettävän nappulan tyyppi
	 * @param fromSqr siirrettävän nappulan vanha sijainti
	 * @param toSqr sirrettävän nappulan uusi sijainti
	 * @return uusi alfa-arvo
	 */
	private int searchMove(int depth, int alpha, int beta, GameState state, StateInfo info,
			int pieceType, int fromSqr, int toSqr)
	{
		int capturedPiece = state.move(fromSqr, toSqr, pieceType);
		int score = -searchWithTranspositionLookup(depth - 1, -beta, -alpha, state);
		state.undoMove(fromSqr, toSqr, pieceType, capturedPiece);

		if (score > alpha) {
			if (info != null) {
				if (depth == currentSearchDepth && loggingEnabled)
					log("" + fromSqr + " " + toSqr + " " + (score - getScore(state, 0)));
				info.bestMoveFrom = fromSqr;
				info.bestMoveTo = toSqr;
				info.bestMovePieceType = pieceType;
			}
			alpha = score;
		}

		return alpha;
	}

	/**
	 * Laskee pistemäärän pelitilanteelle, perustuen pelaajien nappuloiden yhteisarvojen erotukseen.
	 * Lisäksi tasapisteissä painotetaan hieman tilannetta, jossa nappuloiden kokonaismäärä
	 * laudalle on pienempi. Tällä saadaan tekoälystä aggressiivisempi; ilman avauskirjastoa tms.
	 * alkupeli on muutoin liian passiivinen. Lisäksi syvyys hakupuussa vaikuttaa pistemäärään;
	 * mitä nopeammin paras tilanne saavutetaan, sitä parempi.
	 *
	 * @param state pelitila
	 * @param depth
	 * @return
	 */
	int getScore(GameState state, int depth)
	{
		++count;
		int player = state.getNextMovingPlayer();
		int score = -depth;
		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			int pieceValue = Pieces.values[pieceType];
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
	 * Lisää uuden viestin lokiin.
	 *
	 * @param msg viesti
	 */
	private void log(String msg)
	{
		if (loggingEnabled)
			logger.logMessage(msg);
	}
}
