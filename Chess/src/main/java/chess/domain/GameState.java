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
	 * Satunnaisnumerot ohestalyöntiruuduille (näistä ainoastaan 16 on käytössä).
	 */
	private static final long[] ZOBRIST_RND_EN_PASSANT = new long[64];

	/**
	 * Satunnaisnumerot tornitusoikeuksille (näistä ainoastaan 4 on käytössä).
	 */
	private static final long[] ZOBRIST_RND_CASTLINGRIGHTS = new long[64];

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
		for (int i = 0; i < ZOBRIST_RND_EN_PASSANT.length; ++i)
			ZOBRIST_RND_EN_PASSANT[i] = rnd.nextLong();
		for (int i = 0; i < ZOBRIST_RND_CASTLINGRIGHTS.length; ++i)
			ZOBRIST_RND_CASTLINGRIGHTS[i] = rnd.nextLong();
	}

	/**
	 * Taulukoiden aloituskapasiteetti.
	 */
	static final private int START_CAPACITY = 8;

	/**
	 * Laudan tilanteen bittimaskiesitys.
	 */
	private final BitBoard bitboard;

	/**
	 * Seuraavana vuorossa oleva pelaaja. (0-1).
	 */
	private int nextMovingPlayer;

	/**
	 * Tehtyjen puolisiirtojen lukumäärä.
	 */
	private int ply = 0;

	/**
	 * Pino aiempien pelitilanteiden Zobrist-tunnisteille. Tämänhetkinen tunniste on
	 * zobristCodes[ply].
	 */
	private long[] zobristCodes;

	/**
	 * Pino aiempien pelitilanteiden ohestalyöntiruuduille. Tämänhetkinen ohestalyöntiruutu on
	 * enPassantSquares[ply].
	 */
	private int[] enPassantSquares;

	/**
	 * Pino aiempien pelitilanteiden tornitusoikeuksista.
	 */
	private long[] castlingRights;

	/**
	 * Pitää kirjaa kuinka monta siirtoa on kulunut edellisestä lyönnistä tai sotilaan siirrosta.
	 */
	private int[] halfMoveClocks;

	/**
	 * Luo uuden pelitilanteen käyttäen standardia shakin aloitusmuodostelmaa.
	 */
	public GameState()
	{
		this(new BitBoard(
				"Ra1 Nb1 Bc1 Qd1 Ke1 Bf1 Ng1 Rh1 a2 b2 c2 d2 e2 f2 g2 h2",
				"Ra8 Nb8 Bc8 Qd8 Ke8 Bf8 Ng8 Rh8 a7 b7 c7 d7 e7 f7 g7 h7"),
				Players.WHITE);
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
		this(new BitBoard(whitePieces, blackPieces), startingPlayer);
	}

	/**
	 * Luo uuden pelitilanteen käyttäen annettua laudan tilaa ja vuorossa olevaa pelaajaa.
	 * Pelitilanne sallii tornitukset vain jos kuninkaiden ja tornien sijainnit ovat normaalin
	 * alkutilanteen mukaiset.
	 *
	 * @param board pelilaudan sisältö
	 * @param startingPlayer ensimmäisenä vuorossa oleva pelaaja
	 */
	public GameState(BitBoard board, int startingPlayer)
	{
		this(board, new long[START_CAPACITY], new int[START_CAPACITY], new long[START_CAPACITY],
				new int[START_CAPACITY], 0, startingPlayer);

		this.zobristCodes[0] = ZOBRIST_RND_EMPTY;
		this.enPassantSquares[0] = -1;
		if (startingPlayer == Players.BLACK)
			this.zobristCodes[0] ^= ZOBRIST_RND_PLAYER;

		// Asetetaan tornitusoikeudet vain jos kuninkaat/tornit oikeissa kohdissa.
		if (board.hasPiece(Players.WHITE, Pieces.KING, 60)
				&& board.hasPiece(Players.WHITE, Pieces.ROOK, 56)
				&& board.hasPiece(Players.WHITE, Pieces.ROOK, 63)
				&& board.hasPiece(Players.BLACK, Pieces.KING, 4)
				&& board.hasPiece(Players.BLACK, Pieces.ROOK, 0)
				&& board.hasPiece(Players.BLACK, Pieces.ROOK, 7)) {
			this.castlingRights[0] = Movemasks.INITIAL_CASTLING_RIGHTS;
			this.zobristCodes[0] ^= ZOBRIST_RND_CASTLINGRIGHTS[0];
			this.zobristCodes[0] ^= ZOBRIST_RND_CASTLINGRIGHTS[7];
			this.zobristCodes[0] ^= ZOBRIST_RND_CASTLINGRIGHTS[56];
			this.zobristCodes[0] ^= ZOBRIST_RND_CASTLINGRIGHTS[63];
		}

		// Päivitetään Zobrist-tunniste laudalla jo olevien nappuloiden mukaisesti.
		for (int player = 0; player < 2; ++player) {
			for (int sqr = 0; sqr < 64; ++sqr) {
				int piece = board.getPieceType(player, sqr);
				if (piece != -1)
					zobristCodes[0] ^= ZOBRIST_RND[player * Pieces.COUNT * 64 + piece * 64 + sqr];
			}
		}
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
	 * Palauttaa laudan sisällön.
	 *
	 * @return
	 */
	public BitBoard getBoard()
	{
		return bitboard;
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
	 * Palauttaa tehtyjen siirtojen määrän.
	 *
	 * @return
	 */
	public int getPly()
	{
		return ply;
	}

	/**
	 * Palauttaa ruudun johon ohestalyönti on sallittu seuraavalla siirrolla.
	 *
	 * @return ruutu tai -1 jos ohestalyönti ei mahdollinen
	 */
	public int getEnPassantSquare()
	{
		return enPassantSquares[ply];
	}

	/**
	 * Palauttaa tornitusoikeudet bittimaskina. (Ykkösbitti asetettu vastaavan tornin kohdalla.)
	 *
	 * @return
	 */
	public long getCastlingRights()
	{
		return castlingRights[ply];
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
	public void makeMove(int move)
	{
		growArrays();
		zobristCodes[ply + 1] = zobristCodes[ply];
		++ply;
		updateHalfMoveClock(move);
		if (Move.getCapturedType(move) != -1)
			removeCapturedPiece(move);
		handleCastlingMove(move);
		updateEnPassantSquare(move);
		updateCastlingRights(move);
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
		changeNextMovingPlayer();
		removePiece(nextMovingPlayer, Move.getNewType(move), Move.getToSqr(move));
		addPiece(nextMovingPlayer, Move.getPieceType(move), Move.getFromSqr(move));
		undoCastlingMove(move);
		if (Move.getCapturedType(move) != -1)
			restoreCapturedPiece(move);
		--ply;
	}

	/**
	 * Tekee "nollasiirron". Ainoastaan vaihtaa vuorossa olevan pelaajan pelaajan ja päivittää
	 * Zobrist-hajautuskoodin. Lisäksi resetoi ohestalyöntiruudun.
	 */
	public void makeNullMove()
	{
		growArrays();
		zobristCodes[ply + 1] = zobristCodes[ply];
		++ply;
		enPassantSquares[ply] = -1;
		changeNextMovingPlayer();
	}

	/**
	 * Kumoaa aikaisemman nollasiirron tekemät muutokset pelitilanteeseen.
	 */
	public void undoNullMove()
	{
		changeNextMovingPlayer();
		--ply;
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
			if (toSqr == enPassantSquares[ply] && pieceType == Pieces.PAWN)
				capturedType = Pieces.PAWN;
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
		long moves = 0;

		if (pieceType != Pieces.PAWN) {
			moves = getThreatenedSquares(player, pieceType, fromSqr) & ~getPieces(player);
			if (pieceType == Pieces.KING)
				moves |= getCastlingMoves(player);
			return moves;
		}

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
			long enemySqrs = bitboard.getPieces(1 - player);
			if (enPassantSquares[ply] != -1)
				enemySqrs |= 1L << enPassantSquares[ply];
			if (col > 0 && (enemySqrs & 1L << nextRow * 8 + col - 1) != 0)
				moves |= 1L << nextRow * 8 + col - 1;
			if (col < 7 && (enemySqrs & 1L << nextRow * 8 + col + 1) != 0)
				moves |= 1L << nextRow * 8 + col + 1;
		}

		return moves;
	}

	/**
	 * Palauttaa nappulan uhkaamat ruudut (ruudut, joihin lyönti on mahdollinen, jos ruudussa olisi
	 * vastustajan nappula) .
	 *
	 * @param player pelaaja
	 * @param piece nappulaTyyppi
	 * @param fromSqr siirrettävän nappulan sijainti
	 * @return lyöntisiirrot bittimaskina
	 */
	public long getThreatenedSquares(int player, int piece, int fromSqr)
	{
		long moves = 0;

		switch (piece) {
			case Pieces.KING:
				moves |= Movemasks.KING_MOVES[fromSqr];
				break;
			case Pieces.QUEEN:
				moves |= Movemasks.getQueenMoves(fromSqr, bitboard.getPieces());
				break;
			case Pieces.ROOK:
				moves |= Movemasks.getRookMoves(fromSqr, bitboard.getPieces());
				break;
			case Pieces.BISHOP:
				moves |= Movemasks.getBishopMoves(fromSqr, bitboard.getPieces());
				break;
			case Pieces.KNIGHT:
				moves |= Movemasks.KNIGHT_MOVES[fromSqr];
				break;
			case Pieces.PAWN:
				int row = fromSqr / 8;
				int col = fromSqr % 8;
				int nextRow = row - 1 + 2 * player;
				if ((nextRow & ~7) == 0) {
					if (col > 0)
						moves |= 1L << nextRow * 8 + col - 1;
					if (col < 7)
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
	 * laillisia siirtoja, ja kuningas EI OLE uhattuna. Lisäksi tilanne on patti, jos se on
	 * toistunut aikaisemmin tai on kulunut 50 siirtoa ilman lyöntejä tai sotilaiden siirtoja.
	 *
	 * @return true jos patti
	 */
	public boolean isStaleMate()
	{
		if (isRepeatedState() || halfMoveClocks[ply] >= 50)
			return true;
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
	 * Tarkistaa, onko kunigas uhattuna.
	 *
	 * @param defendingPlayer pelaaja, jonka kuninkaasta on kyse
	 * @return true, jos on uhattu
	 */
	public boolean isKingChecked(int defendingPlayer)
	{
		long kingMask = bitboard.getPieces(defendingPlayer, Pieces.KING);
		return isSquareThreatened(defendingPlayer, kingMask);
	}

	/**
	 * Tarkistaa, onko vastustajalla mahdollisia hyökkäyssiirtoja, jotka kohdistuvat johonkin
	 * annettuista ruuduista.
	 *
	 * @param defendingPLayer puolustava pelaaja
	 * @param sqr
	 * @return true jos jokin ruutu on uhattuna
	 */
	public boolean isSquareThreatened(int defendingPlayer, long sqrs)
	{
		int attackingPlayer = 1 - defendingPlayer;
		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			long pieces = bitboard.getPieces(attackingPlayer, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int attackingSqr = Long.numberOfTrailingZeros(pieces);
				long attackMoves = getThreatenedSquares(attackingPlayer, pieceType, attackingSqr);
				if ((sqrs & attackMoves) != 0)
					return true;
			}
		}
		return false;
	}

	@Override
	public GameState clone()
	{
		return new GameState(bitboard.clone(), zobristCodes.clone(), enPassantSquares.clone(),
				castlingRights.clone(), halfMoveClocks.clone(), ply, nextMovingPlayer);
	}

	/**
	 * Palauttaa pelitilannetta vastaavan 64-bittisen Zobrist-arvon.
	 *
	 * @return
	 */
	public long getId()
	{
		return zobristCodes[ply];
	}

	@Override
	public boolean equals(Object obj)
	{
		GameState state2 = (GameState) obj;
		boolean result = bitboard.equals(state2.bitboard)
				&& nextMovingPlayer == state2.nextMovingPlayer
				&& enPassantSquares[ply] == state2.enPassantSquares[state2.ply]
				&& castlingRights[ply] == state2.castlingRights[state2.ply];
		assert !result || zobristCodes[ply] == state2.zobristCodes[state2.ply];
		return result;
	}

	/**
	 * Palauttaa taulukossa kaikkien aikaisempien pelitilanteiden tunnisteet.
	 *
	 * @return
	 */
	public long[] getEarlierStates()
	{
		return Arrays.copyOf(zobristCodes, ply);
	}

	/**
	 * Luo pelitilanteen kopioimalla sen toisesta pelitilanteesta.
	 */
	private GameState(BitBoard board, long[] zobristCodes, int[] enPassantSquares,
			long[] castlingRights, int[] halfMoveClocks, int ply, int nextMovingPlayer)
	{
		this.bitboard = board;
		this.zobristCodes = zobristCodes;
		this.enPassantSquares = enPassantSquares;
		this.castlingRights = castlingRights;
		this.ply = ply;
		this.nextMovingPlayer = nextMovingPlayer;
		this.halfMoveClocks = halfMoveClocks;
	}

	/**
	 * Vaihtaa vuorossa olevan pelaajaan ja päivittää Zobrist-koodin.
	 */
	private void changeNextMovingPlayer()
	{
		nextMovingPlayer = 1 - nextMovingPlayer;
		zobristCodes[ply] ^= ZOBRIST_RND_PLAYER;
	}

	/**
	 * Lisää nappulan ja päivittää Zobrist-koodin.
	 */
	private void addPiece(int player, int piece, int sqr)
	{
		bitboard.addPiece(player, piece, sqr);
		zobristCodes[ply] ^= ZOBRIST_RND[player * Pieces.COUNT * 64 + piece * 64 + sqr];
	}

	/**
	 * Poistaa nappulan ja päivittää Zobrist-koodin.
	 */
	private void removePiece(int player, int piece, int sqr)
	{
		bitboard.removePiece(player, piece, sqr);
		zobristCodes[ply] ^= ZOBRIST_RND[player * Pieces.COUNT * 64 + piece * 64 + sqr];
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
		makeMove(move);
		boolean legal = !isKingChecked(player);
		undoMove(move);
		return legal;
	}

	/**
	 * Poistaa laudalta lyödyn nappulan. Käsittelee ohestalyönnit erikoistapauksena.
	 *
	 * @param move siirto
	 */
	private void removeCapturedPiece(int move)
	{
		assert Move.getCapturedType(move) != -1;
		int toSqr = Move.getToSqr(move);
		if (Move.getPieceType(move) == Pieces.PAWN && toSqr == enPassantSquares[ply - 1])
			removePiece(1 - nextMovingPlayer, Pieces.PAWN, toSqr + 8 - 16 * nextMovingPlayer);
		else
			removePiece(1 - nextMovingPlayer, Move.getCapturedType(move), Move.getToSqr(move));
	}

	/**
	 * Palauttaa laudalle edellisessä siirrossa lyödyn nappulan. Käsittelee ohestalyönnit
	 * erikoistapauksena.
	 *
	 * @param move siirto
	 */
	private void restoreCapturedPiece(int move)
	{
		assert Move.getCapturedType(move) != -1;
		int toSqr = Move.getToSqr(move);
		if (Move.getPieceType(move) == Pieces.PAWN && toSqr == enPassantSquares[ply - 1])
			addPiece(1 - nextMovingPlayer, Pieces.PAWN, toSqr + 8 - 16 * nextMovingPlayer);
		else
			addPiece(1 - nextMovingPlayer, Move.getCapturedType(move), Move.getToSqr(move));
	}

	/**
	 * Päivittää ohestalyöntiruudun sekä zobrist-koodin sen mukaisesti. Jos siirto on sotilaan
	 * kahden ruudun mittainen avaussiirto, väliin jäänyt siirto asetetaan ohestalyöntiruuduksi.
	 * Muussa tapauksessa sille annetaan arvo -1.
	 *
	 * @param move siirto
	 */
	private void updateEnPassantSquare(int move)
	{
		if (enPassantSquares[ply - 1] != -1)
			zobristCodes[ply] ^= ZOBRIST_RND_EN_PASSANT[enPassantSquares[ply - 1]];
		if (Move.getPieceType(move) == Pieces.PAWN
				&& Move.getFromSqr(move) >>> 3 == 6 - 5 * nextMovingPlayer
				&& Move.getToSqr(move) >>> 3 == 4 - nextMovingPlayer) {
			enPassantSquares[ply] = Move.getFromSqr(move) - 8 + 16 * nextMovingPlayer;
			zobristCodes[ply] ^= ZOBRIST_RND_EN_PASSANT[enPassantSquares[ply]];
		} else
			enPassantSquares[ply] = -1;
	}

	/**
	 * Jos siirretään kuningasta tai tornia, poistetaan vastaavat tornitusmahdollisuudet.
	 */
	private void updateCastlingRights(int move)
	{
		castlingRights[ply] = castlingRights[ply - 1];
		if (Move.getPieceType(move) == Pieces.KING) {
			removeCastlingRight(56 * (1 - nextMovingPlayer));
			removeCastlingRight(56 * (1 - nextMovingPlayer) + 7);
		} else if (Move.getPieceType(move) == Pieces.ROOK)
			removeCastlingRight(Move.getFromSqr(move));
	}

	/**
	 * Poistaa annettua torniruutua vastaavan tornitusoikeuden.
	 */
	private void removeCastlingRight(int rookSqr)
	{
		long sqrBit = 1L << rookSqr;
		if ((castlingRights[ply] & sqrBit) != 0) {
			castlingRights[ply] &= ~sqrBit;
			zobristCodes[ply] ^= ZOBRIST_RND_CASTLINGRIGHTS[rookSqr];
		}
	}

	/**
	 * Jos siirto on tornitus, siirtää tornin oikeaan kohtaan.
	 */
	private void handleCastlingMove(int move)
	{
		int toSqr = Move.getToSqr(move);
		if (Move.getPieceType(move) == Pieces.KING && ((Move.getFromSqr(move) - toSqr) & 3) == 2) {
			int row = toSqr >>> 3;
			int col = toSqr & 7;
			int rookFromSqr, rookToSqr;
			if (col == 2) {
				rookFromSqr = 8 * row + 0;
				rookToSqr = 8 * row + 3;
			} else {
				rookFromSqr = 8 * row + 7;
				rookToSqr = 8 * row + 5;
			}
			removePiece(nextMovingPlayer, Pieces.ROOK, rookFromSqr);
			addPiece(nextMovingPlayer, Pieces.ROOK, rookToSqr);
		}
	}

	/**
	 * Peruu aikaisemman tornituksen, eli siirtää tornin takaisin aikaisempaan ruutuun.
	 */
	private void undoCastlingMove(int move)
	{
		int toSqr = Move.getToSqr(move);
		if (Move.getPieceType(move) == Pieces.KING && ((Move.getFromSqr(move) - toSqr) & 3) == 2) {
			int row = toSqr >>> 3;
			int col = toSqr & 7;
			int rookFromSqr, rookToSqr;
			if (col == 2) {
				rookFromSqr = 8 * row + 0;
				rookToSqr = 8 * row + 3;
			} else {
				rookFromSqr = 8 * row + 7;
				rookToSqr = 8 * row + 5;
			}
			removePiece(nextMovingPlayer, Pieces.ROOK, rookToSqr);
			addPiece(nextMovingPlayer, Pieces.ROOK, rookFromSqr);
		}
	}

	/**
	 * Palauttaa sallitut tornitussiirrot annetulle pelaajalle. Vaatimukset tornitukselle:
	 * - kyseinen tornitusoikeus on vielä voimassa (tornia tai kuningasta ei siirretty)
	 * - ruudut kuninkaan ja tornin välillä ovat tyhjiä
	 * - kuningas ei saa olla uhattuna missään ruudussa, jonka kautta se kulkee
	 *
	 * @param player
	 * @return tornitussiirrot bittimaskina (kuninkaan kohderuudut)
	 */
	private long getCastlingMoves(int player)
	{
		int rowOffset = 56 * (1 - player);
		long moves = 0;
		if ((castlingRights[ply] & (1L << rowOffset)) != 0) {
			long betweenSquares = (1L << 1 | 1L << 2 | 1L << 3) << rowOffset;
			if ((betweenSquares & bitboard.getPieces()) == 0) {
				long kingSqrs = (1L << 2 | 1L << 3 | 1L << 4) << rowOffset;
				if (!isSquareThreatened(player, kingSqrs))
					moves |= 1L << 2;
			}
		}
		if ((castlingRights[ply] & (1L << (rowOffset + 7))) != 0) {
			long betweenSquares = (1L << 5 | 1L << 6) << rowOffset;
			if ((betweenSquares & bitboard.getPieces()) == 0) {
				long kingSqrs = (1L << 4 | 1L << 5 | 1L << 6) << rowOffset;
				if (!isSquareThreatened(player, kingSqrs))
					moves |= 1L << 6;
			}
		}
		moves <<= rowOffset;
		return moves;
	}

	/**
	 * Kasvattaa sisäisten taulukoiden kokoa.
	 */
	private void growArrays()
	{
		if (ply + 1 >= zobristCodes.length) {
			int newSize = 2 * zobristCodes.length;
			zobristCodes = Arrays.copyOf(zobristCodes, newSize);
			castlingRights = Arrays.copyOf(castlingRights, newSize);
			enPassantSquares = Arrays.copyOf(enPassantSquares, newSize);
			halfMoveClocks = Arrays.copyOf(halfMoveClocks, newSize);
		}
	}

	/**
	 * Tarkistaa onko pelitilanne toistunut aikaisemmin.
	 */
	private boolean isRepeatedState()
	{
		for (int i = ply - halfMoveClocks[ply]; i < ply; ++i) {
			if (zobristCodes[i] == zobristCodes[ply])
				return true;
		}
		return false;
	}

	/**
	 * Päivittää laskurin 50 siirron säännön toteuttamiseksi.
	 */
	private void updateHalfMoveClock(int move)
	{
		if (Move.getCapturedType(move) != -1 || Move.getPieceType(move) == Pieces.PAWN)
			halfMoveClocks[ply] = 0;
		else
			halfMoveClocks[ply] = halfMoveClocks[ply - 1] + 1;
	}
}
