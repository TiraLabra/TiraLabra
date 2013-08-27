package chess.domain;

import java.util.Arrays;
import java.util.Random;

/**
 * Tallentaa nappuloiden sijainnit ja muut pelitilanteen tiedot. (Sallitut tornitukset,
 * ohestalyönnit.) Mahdollistaa sallittujen siirtojen laskemisen kullekin nappulalle,
 * mattitilanteiden tarkastuksen yms. perusoperaatiot.
 *
 * Pelitilanteesta pidetään jatkuvasti yllä Zobrist-hajautuskoodia transpositiotaulua varten.
 */
public final class GameState
{
	/**
	 * Lista satunnaisnumeroista Zobrist-hajautuskoodin laskemiseksi. Jokaiselle
	 * pelaaja-nappula-ruutu-kombinaatiolle on oma satunnaisnumeronsa.
	 */
	private static final long[] ZOBRIST_RND = new long[Players.COUNT * Pieces.COUNT * 64];

	/**
	 * Satunnaisnumero, jolla zobrist-koodi xorrataan kun musta on vuorossa.
	 */
	private static final long ZOBRIST_RND_PLAYER;

	/**
	 * Tyhjää lautaa vastaava satunnaisnumero.
	 */
	private static final long ZOBRIST_RND_EMPTY;

	/**
	 * Zobrist-satunnaisnumeroiden alustus.
	 */
	static {
		Random rnd = new Random(999);
		for (int i = 0; i < ZOBRIST_RND.length; ++i)
			ZOBRIST_RND[i] = rnd.nextLong();
		ZOBRIST_RND_PLAYER = rnd.nextLong();
		ZOBRIST_RND_EMPTY = rnd.nextLong();
	}

	/**
	 * Laudan tilanteen bittimaskiesitys.
	 */
	private final BitBoard bitboard = new BitBoard();

	/**
	 * Seuraavana vuorossa oleva pelaaja. (0-1).
	 */
	private int nextMovingPlayer = Players.WHITE;

	/**
	 * Zobrist-hajautuskoodi
	 */
	private long zobristCode = ZOBRIST_RND_EMPTY;

	/**
	 * Aikaisempien pelitilanteiden Zobrist-tunnisteet.
	 */
	private long[] earlierStates = new long[1024];

	/**
	 * Tehtyjen puolisiirtojen lukumäärä.
	 */
	private int ply = 0;

	/**
	 * Luo uuden pelitilanteen käyttäen standardia shakin aloitusmuodostelmaa.
	 */
	public GameState()
	{
		setupInitialPosition();
	}

	/**
	 * Luo satunnaisen (mutta sallitun) pelitilanteen.
	 *
	 * @param rnd käytettävä satunnaislukugeneraattori
	 */
	public GameState(Random rnd)
	{
		randomize(rnd);
	}

	/**
	 * Luo uuden pelitilanteen käyttäen annettua laudan tilaa ja vuorossa olevaa pelaajaa.
	 *
	 * @param board pelilaudan sisältö
	 * @param startingPlayer ensimmäisenä vuorossa oleva pelaaja
	 */
	public GameState(BitBoard board, int startingPlayer)
	{
		this.bitboard.copyFrom(board);
		this.nextMovingPlayer = startingPlayer;
	}

	/**
	 * Luo uuden pelitilanteen, missä nappuloiden sijainnit on annettu merkkijonoina (esim
	 * "Ka8 Bc7").
	 *
	 * @param whitePieces valkoiset nappulat
	 * @param blackPieces mustat nappulat
	 * @param startingPlayer ensimmäisenä vuorossa oleva pelaaja
	 */
	public GameState(String whitePieces, String blackPieces, int startingPlayer)
	{
		this.bitboard.copyFrom(new BitBoard(whitePieces, blackPieces));
		this.nextMovingPlayer = startingPlayer;
	}

	/**
	 * Palauttaa seuraavana vuorossa olevan pelaajan.
	 *
	 * @return 0-1
	 */
	public int getNextMovingPlayer()
	{
		return nextMovingPlayer;
	}

	/**
	 * Palauttaa laudan sisällön taulukkona.
	 *
	 * @return 64-alkioinen taulukko
	 */
	public int[] getBoard()
	{
		return bitboard.toArray();
	}

	/**
	 * Palauttaa kaikki pelaajan tietyntyyppiset nappulat bittimaskina.
	 *
	 * @param player pelaaja (0-1)
	 * @param piece nappulatyyppi (0-5)
	 */
	public long getPieces(int player, int piece)
	{
		return bitboard.getPieces(player, piece);
	}

	/**
	 * Palauttaa kaikki pelaajan nappulat bittimaskina.
	 *
	 * @param player pelaaja (0-1)
	 */
	public long getPieces(int player)
	{
		return bitboard.getPieces(player);
	}

	/**
	 * Tekee pelitilanteeseen annetun siirron.
	 *
	 * @param move siirto pakattuna int-muuttujaan (ks. Move)
	 */
	public void move(int move)
	{
		earlierStates[ply++] = zobristCode;
		if (Move.getCapturedType(move) != -1)
			removePiece(1 - nextMovingPlayer, Move.getCapturedType(move), Move.getToSqr(move));
		removePiece(nextMovingPlayer, Move.getPieceType(move), Move.getFromSqr(move));
		addPiece(nextMovingPlayer, Move.getNewType(move), Move.getToSqr(move));
		changeNextMovingPlayer();
	}

	/**
	 * Kumoaa aikaisemman siirron tekemät muutokset pelitilanteeseen.
	 *
	 * @param move siirto pakattuna int-muuttujaan (ks. Move)
	 */
	public void undoMove(int move)
	{
		--ply;
		changeNextMovingPlayer();
		removePiece(nextMovingPlayer, Move.getNewType(move), Move.getToSqr(move));
		addPiece(nextMovingPlayer, Move.getPieceType(move), Move.getFromSqr(move));
		if (Move.getCapturedType(move) != -1)
			addPiece(1 - nextMovingPlayer, Move.getCapturedType(move), Move.getToSqr(move));
	}

	/**
	 * Tekee "nollasiirron". Ainoastaan vaihtaa aktiivisen pelaajan ja päivittää
	 * Zobrist-hajautuskoodin. Nollasiirto voidaan kumota kutsumalla funktiota uudestaan.
	 */
	public void nullMove()
	{
		changeNextMovingPlayer();
	}

	/**
	 * Palauttaa kaikki lailliset siirrot siirtovuorossa olevalle pelaajalle.
	 *
	 * @return siirrot taulukkona
	 */
	public int[] getLegalMoves()
	{
		int count = 0;
		int[] moves = new int[256];
		long pieces = bitboard.getPieces(nextMovingPlayer);
		for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
			int fromSqr = Long.numberOfTrailingZeros(pieces);
			int[] sqrMoves = getLegalMoves(fromSqr);
			for (int i = 0; i < sqrMoves.length; ++i)
				moves[count++] = sqrMoves[i];
		}
		return Arrays.copyOf(moves, count);
	}

	/**
	 * Palauttaa kaikki lailliset siirrot yhdestä ruudusta.
	 *
	 * @param fromSqr ruutu (0-63)
	 * @return siirrot taulukkona
	 */
	public int[] getLegalMoves(int fromSqr)
	{
		int count = 0;
		int[] moves = new int[27]; // Maksimi siirtomäärä yhdestä ruudusta.

		long movesMask = getPseudoLegalMoves(nextMovingPlayer, fromSqr);

		for (; movesMask != 0; movesMask -= Long.lowestOneBit(movesMask)) {
			int toSqr = Long.numberOfTrailingZeros(movesMask);
			int pieceType = bitboard.getPieceType(nextMovingPlayer, fromSqr);
			int capturedType = bitboard.getPieceType(1 - nextMovingPlayer, toSqr);
			int move = Move.pack(fromSqr, toSqr, pieceType, capturedType, pieceType);
			if (!isLegalMove(move))
				continue;
			if (pieceType == Pieces.PAWN && (toSqr / 8) == nextMovingPlayer * 7) {
				for (int promoType = Pieces.QUEEN; promoType <= Pieces.KNIGHT; ++promoType)
					moves[count++] = Move.pack(fromSqr, toSqr, pieceType, capturedType, promoType);
			} else
				moves[count++] = move;
		}

		return Arrays.copyOf(moves, count);
	}

	/**
	 * Palauttaa pelaajan "pseudolailliset" siirrot annetusta ruudusta. Siirrot, jotka ovat muuten
	 * sääntöjen mukaisia, mutta voivat jättää kuninkaan uhatuksi. Huomattavasti kevyempi laskea,
	 * kuin aidosti lailliset siirrot, minkä takia MinMaxAI perustuu pseudolaillisiin siirtoihin,
	 * ja mattitilanteet vältetään antamalla kuninkaan lyönnille hyvin suuri/pieni pistearvo.
	 *
	 * @param player pelaaja (0-1)
	 * @param fromSqr ruutu (0-63)
	 * @return siirrot bittimaskina
	 */
	public long getPseudoLegalMoves(int player, int fromSqr)
	{
		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			if (bitboard.hasPiece(nextMovingPlayer, pieceType, fromSqr))
				return getPseudoLegalMoves(player, pieceType, fromSqr);
		}

		return 0;
	}

	/**
	 * Palauttaa pseudolailliset siirrot, kun nappulan tyyppi tiedetään.
	 *
	 * @param player pelaaja
	 * @param pieceType nappulan tyyppi
	 * @param fromSqr nappulan sijainti
	 * @return siirrot bittimaskina
	 */
	public long getPseudoLegalMoves(int player, int pieceType, int fromSqr)
	{
		if (pieceType != Pieces.PAWN)
			return getAttackMoves(player, pieceType, fromSqr);

		long moves = 0;

		int row = fromSqr / 8;
		int col = fromSqr % 8;
		int nextRow = row - 1 + 2 * player;

		if ((nextRow & ~7) == 0) {
			if (!bitboard.hasPiece(nextRow * 8 + col)) {
				moves |= 1L << nextRow * 8 + col;
				int doublePushSqr = (4 - player) * 8 + col;
				if (row == 6 - 5 * player && !bitboard.hasPiece(doublePushSqr))
					moves |= 1L << doublePushSqr;
			}
			if (col > 0 && bitboard.hasPiece(1 - player, nextRow * 8 + col - 1))
				moves |= 1L << nextRow * 8 + col - 1;
			if (col < 7 && bitboard.hasPiece(1 - player, nextRow * 8 + col + 1))
				moves |= 1L << nextRow * 8 + col + 1;
		}

		return moves;
	}

	/**
	 * Palauttaa ne pseudolailliset siirrot, jotka voivat lyödä vastustajan nappulan silloin, jos
	 * kohderuudussa on vastustajan nappula.
	 *
	 * @param player pelaaja
	 * @param piece nappulaTyyppi
	 * @param fromSqr siirrettävän nappulan sijainti
	 * @return lyöntisiirrot bittimaskina
	 */
	public long getAttackMoves(int player, int piece, int fromSqr)
	{
		long moves = 0;

		switch (piece) {
			case Pieces.KING:
				moves |= Movemasks.KING_MOVES[fromSqr] & ~bitboard.getPieces(player);
				break;
			case Pieces.QUEEN:
				long allPieces = bitboard.getPieces();
				moves |= Movemasks.getQueenMoves(fromSqr, allPieces, bitboard.getPieces(player));
				break;
			case Pieces.ROOK:
				allPieces = bitboard.getPieces();
				moves |= Movemasks.getRookMoves(fromSqr, allPieces, bitboard.getPieces(player));
				break;
			case Pieces.BISHOP:
				allPieces = bitboard.getPieces();
				moves |= Movemasks.getBishopMoves(fromSqr, allPieces, bitboard.getPieces(player));
				break;
			case Pieces.KNIGHT:
				moves |= Movemasks.KNIGHT_MOVES[fromSqr] & ~bitboard.getPieces(player);
				break;
			case Pieces.PAWN:
				int row = fromSqr / 8;
				int col = fromSqr % 8;
				int nextRow = row - 1 + 2 * player;
				if ((nextRow & ~7) == 0) {
					if (col > 0 && bitboard.hasPiece(1 - player, nextRow * 8 + col - 1))
						moves |= 1L << nextRow * 8 + col - 1;
					if (col < 7 && bitboard.hasPiece(1 - player, nextRow * 8 + col + 1))
						moves |= 1L << nextRow * 8 + col + 1;
				}
				break;
		}

		return moves;
	}

	/**
	 * Tarkistaa, onko pelitilanne shakkimatti, eli siirtovuorossa olevalla pelaajalla ei ole
	 * laillisia siirtoja, ja kuningas ON uhattuna.
	 *
	 * @return true jos matti
	 */
	public boolean isCheckMate()
	{
		for (int sqr = 0; sqr < 64; ++sqr) {
			if (getLegalMoves(sqr).length != 0)
				return false;
		}
		return isKingChecked(nextMovingPlayer);
	}

	/**
	 * Tarkistaa, onko pelitilanne patissa, eli siirtovuorossa olevalla pelaajalla ei ole
	 * laillisia siirtoja, ja kuningas EI OLE uhattuna.
	 *
	 * @return true jos patti
	 */
	public boolean isStaleMate()
	{
		for (int sqr = 0; sqr < 64; ++sqr) {
			if (getLegalMoves(sqr).length != 0)
				return false;
		}
		return !isKingChecked(nextMovingPlayer);
	}

	/**
	 * Tarkistaa, ovatko kummankin pelaajan kuninkaat laudalla.
	 *
	 * @return
	 */
	public boolean areBothKingsAlive()
	{
		return bitboard.getPieces(Players.WHITE, Pieces.KING) != 0
				&& bitboard.getPieces(Players.BLACK, Pieces.KING) != 0;
	}

	/**
	 * Palauttaa annetun pelaajan kuninkaan sijainnin.
	 *
	 * @param player pelaaja (0-1)
	 * @return sijainti (0-63), tai -1 jos laudalla ei ole kuningasta
	 */
	public int getKingSquare(int player)
	{
		long kingSqrMask = bitboard.getPieces(player, Pieces.KING);
		return kingSqrMask == 0 ? -1 : Long.numberOfTrailingZeros(kingSqrMask);
	}

	/**
	 * Tarkistaa, onko kunigas uhattuna.
	 *
	 * @param defendingPlayer pelaaja, jonka kuninkaasta on kyse
	 * @return true, jos on uhattu
	 */
	public boolean isKingChecked(int defendingPlayer)
	{
		int kingSqr = getKingSquare(defendingPlayer);
		return isSquareThreatened(defendingPlayer, kingSqr);
	}

	/**
	 * Tarkistaa, onko vastustajalla mahdollisia hyökkäyssiirtoja, jotka kohdistuvat annettuun
	 * ruutuun.
	 *
	 * @param defendingPLayer puolustava pelaaja
	 * @param sqr
	 * @return
	 */
	public boolean isSquareThreatened(int defendingPLayer, int sqr)
	{
		int attackingPlayer = 1 - defendingPLayer;
		long sqrBit = 1L << sqr;
		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			long pieces = bitboard.getPieces(attackingPlayer, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int attackingSqr = Long.numberOfTrailingZeros(pieces);
				long attackMoves = getAttackMoves(attackingPlayer, pieceType, attackingSqr);
				if ((sqrBit & attackMoves) != 0)
					return true;
			}
		}
		return false;
	}

	@Override
	public GameState clone()
	{
		return new GameState(this);
	}

	/**
	 * Palauttaa pelitilannetta vastaavan 64-bittisen Zobrist-arvon.
	 *
	 * @return
	 */
	public long getId()
	{
		return zobristCode;
	}

	@Override
	public boolean equals(Object obj)
	{
		GameState state2 = (GameState) obj;
		return bitboard.equals(state2.bitboard) && nextMovingPlayer == state2.nextMovingPlayer;
	}

	/**
	 * Palauttaa taulukossa kaikkien aikaisempien pelitilanteiden tunnisteet.
	 *
	 * @return
	 */
	public long[] getEarlierStates()
	{
		return Arrays.copyOf(earlierStates, ply);
	}

	/**
	 * Luo pelitilanteen kopioimalla sen toisesta pelitilanteesta.
	 */
	private GameState(GameState copyFrom)
	{
		nextMovingPlayer = copyFrom.nextMovingPlayer;
		bitboard.copyFrom(copyFrom.bitboard);
		zobristCode = copyFrom.zobristCode;
		earlierStates = copyFrom.earlierStates.clone();
	}

	/**
	 * Luo satunnaisen pelitilanteen. Jos pelitilanne ei ole laillinen, arvotaan uusi niin kauan
	 * kunnes laillinen tilanne löytyy.
	 */
	private void randomize(Random rnd)
	{
		do {
			bitboard.clear();
			addRandomizedPieces(1, 1, Pieces.KING, rnd);
			addRandomizedPieces(0, 1, Pieces.QUEEN, rnd);
			addRandomizedPieces(0, 2, Pieces.ROOK, rnd);
			addRandomizedPieces(0, 2, Pieces.BISHOP, rnd);
			addRandomizedPieces(0, 2, Pieces.KNIGHT, rnd);
			addRandomizedPieces(0, 8, Pieces.PAWN, rnd);
		} while (isCheckMate() || isStaleMate() || isKingChecked(Players.BLACK));
	}

	/**
	 * Lisää satunnaisen määrän tietyn tyyppisiä nappuloita kummallekin pelaajalle.
	 *
	 * @param min minimimäärä
	 * @param max maksimimäärä
	 * @param pieceType tyyppi
	 * @param rnd satunnaisgeneraattori
	 */
	private void addRandomizedPieces(int min, int max, int pieceType, Random rnd)
	{
		for (int player = 0; player < 2; ++player) {
			int n = min + rnd.nextInt(1 + max - min);
			for (int i = 0; i < n; ++i) {
				int sqr;
				do {
					sqr = pieceType != Pieces.PAWN ? rnd.nextInt(64) : 8 + rnd.nextInt(48);
				} while (bitboard.hasPiece(sqr));
				addPiece(player, pieceType, sqr);
			}
		}
	}

	/**
	 * Vaihtaa vuorossa olevan pelaajaan ja päivittää Zobrist-koodin.
	 */
	private void changeNextMovingPlayer()
	{
		nextMovingPlayer = 1 - nextMovingPlayer;
		zobristCode ^= ZOBRIST_RND_PLAYER;
	}

	/**
	 * Lisää nappulan ja päivittää Zobrist-koodin.
	 */
	private void addPiece(int player, int piece, int sqr)
	{
		bitboard.addPiece(player, piece, sqr);
		zobristCode ^= ZOBRIST_RND[player * Pieces.COUNT * 64 + piece * 64 + sqr];
	}

	/**
	 * Poistaa nappulan ja päivittää Zobrist-koodin.
	 */
	private void removePiece(int player, int piece, int sqr)
	{
		bitboard.removePiece(player, piece, sqr);
		zobristCode ^= ZOBRIST_RND[player * Pieces.COUNT * 64 + piece * 64 + sqr];
	}

	/**
	 * Lisää nappulat normaalin aloitusmuodostelman mukaisesti.
	 */
	private void setupInitialPosition()
	{
		addInitialPiece(0, 0, Pieces.ROOK);
		addInitialPiece(0, 1, Pieces.KNIGHT);
		addInitialPiece(0, 2, Pieces.BISHOP);
		addInitialPiece(0, 3, Pieces.QUEEN);
		addInitialPiece(0, 4, Pieces.KING);
		addInitialPiece(0, 5, Pieces.BISHOP);
		addInitialPiece(0, 6, Pieces.KNIGHT);
		addInitialPiece(0, 7, Pieces.ROOK);
		for (int i = 0; i < 8; ++i)
			addInitialPiece(1, i, Pieces.PAWN);
	}

	/**
	 * Lisää nappulan samaan kohtaan kummallekin pelaajalle (x-akselin suhteen peilattuna)
	 */
	private void addInitialPiece(int row, int col, int piece)
	{
		int sqr = row * 8 + col;
		addPiece(Players.BLACK, piece, sqr);
		int sqr2 = (7 - row) * 8 + col;
		addPiece(Players.WHITE, piece, sqr2);
	}

	/**
	 * Tarkistaa, onko "pseudolaillinen" siirto laillinen, eli ei jätä kunigasta uhatuksi.
	 *
	 * @param move siirto
	 * @return true jos laillinen, false jos laiton siirto
	 */
	private boolean isLegalMove(int move)
	{
		int player = nextMovingPlayer;
		move(move);
		boolean legal = !isKingChecked(player);
		undoMove(move);
		return legal;
	}
}
