package chess.domain;

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
	private static final int[] zobristRndNumbers = new int[Players.COUNT * Pieces.COUNT * 64];

	/**
	 * Satunnaisnumero, jolla zobrist-koodi xorrataan kun musta on vuorossa.
	 */
	private static final int zobristRndPlayer;

	/**
	 * Zobrist-satunnaisnumeroiden alustus.
	 */
	static {
		Random rnd = new Random();
		for (int i = 0; i < zobristRndNumbers.length; ++i)
			zobristRndNumbers[i] = rnd.nextInt();
		zobristRndPlayer = rnd.nextInt();
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
	private int zobristCode;

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
	 * Tekee pelitilanteeseen annetun siirron. Jos kohderuudussa on nappula, se poistetaan, ja
	 * lyödyn nappulan tyyppi palautetaan. Siirron validiutta ei tehokkuussyistä tarkasteta
	 * enää tässä vaiheessa.
	 *
	 * @param fromSqr siirrettävän nappulan vanha sijainti (0-63)
	 * @param toSqr siirrettävän nappulan uusi sijainti (0-63)
	 * @return lyödyn nappulan tyyppi tai -1, jos kohderuutu tyhjä
	 */
	public int move(int fromSqr, int toSqr)
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			if (bitboard.hasPiece(nextMovingPlayer, piece, fromSqr)) {
				removePiece(nextMovingPlayer, piece, fromSqr);
				addPiece(nextMovingPlayer, piece, toSqr);
				break;
			}
		}

		int capturedPiece = removePiece(1 - nextMovingPlayer, toSqr);

		changeNextMovingPlayer();

		return capturedPiece;
	}

	/**
	 * Tekee pelitilanteeseen annetun siirron, kun siirrettävän nappulan tyyppi tiedetään
	 * (hieman nopeampi). Jos kohderuudussa on nappula, se poistetaan, ja lyödyn nappulan tyyppi
	 * palautetaan. Siirron validiutta ei tehokkuussyistä tarkasteta enää tässä vaiheessa.
	 *
	 * @param fromSqr siirrettävän nappulan vanha sijainti (0-63)
	 * @param toSqr siirrettävän nappulan uusi sijainti (0-63)
	 * @param pieceType siirrettävän nappulan tyyppi (0-5)
	 * @return lyödyn nappulan tyyppi tai -1, jos kohderuutu tyhjä
	 */
	public int move(int fromSqr, int toSqr, int pieceType)
	{
		removePiece(nextMovingPlayer, pieceType, fromSqr);
		addPiece(nextMovingPlayer, pieceType, toSqr);
		int capturedPiece = removePiece(1 - nextMovingPlayer, toSqr);
		changeNextMovingPlayer();
		return capturedPiece;
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
	 * Peruu aikaisemman tehdyn siirron.
	 *
	 * @param fromSqr siirrettyn nappulan vanha sijainti (0-63)
	 * @param toSqr siirrettyn nappulan uusi sijainti (0-63)
	 * @param movedPiece siirretyn nappulan tyyppi
	 * @param capturedPiece lyödyn nappulan tyyppi tai -1, jos kohderuutu oli tyhjä
	 */
	public void undoMove(int fromSqr, int toSqr, int movedPiece, int capturedPiece)
	{
		changeNextMovingPlayer();
		removePiece(nextMovingPlayer, movedPiece, toSqr);
		addPiece(nextMovingPlayer, movedPiece, fromSqr);
		if (capturedPiece != -1)
			addPiece(1 - nextMovingPlayer, capturedPiece, toSqr);
	}

	/**
	 * Palauttaa bittimaskina kaikki lailliset siirrot annetusta ruudusta. Huomio sen, että
	 * siirto ei saa jättää kuningasta uhatuksi.
	 *
	 * @param fromSqr ruutu (0-63)
	 * @return bittimaski sallituista siirroista
	 */
	public long getLegalMoves(int fromSqr)
	{
		long moves = getPseudoLegalMoves(nextMovingPlayer, fromSqr);

		for (int toSqr = 0; toSqr < 64; ++toSqr) {
			if ((moves & (1L << toSqr)) != 0) {
				if (!isLegalMove(fromSqr, toSqr))
					moves &= ~(1L << toSqr);
			}
		}

		return moves;
	}

	/**
	 * Palauttaa pelaajan "pseudolailliset" siirrot annetusta ruudusta. Siirrot, jotka ovat muuten
	 * sääntöjen mukaisia, mutta voivat jättää kuninkaan uhatuksi. Huomattavasti kevyempi laskea,
	 * kuin aidosti lailliset siirrot, minkä takia MinMaxAI perustuu pseudolaillisiin siirtoihin,
	 * ja mattitilanteet vältetään antamalla kuninkaan lyönnille hyvin suuri/pieni pistearvo.
	 *
	 * @param player pelaaja (0-1)
	 * @param sqr ruutu (0-63)
	 * @return siirrot bittimaskina
	 */
	public long getPseudoLegalMoves(int player, int sqr)
	{
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			if (bitboard.hasPiece(nextMovingPlayer, piece, sqr))
				return getPseudoLegalMoves(player, piece, sqr);
		}

		return 0;
	}

	/**
	 * Palauttaa pseudolailliset siirrot, kun nappulan tyyppi tiedetään.
	 *
	 * @param player pelaaja
	 * @param piece nappulan tyyppi
	 * @param fromSqr nappulan sijainti
	 * @return siirrot bittimaskina
	 */
	public long getPseudoLegalMoves(int player, int piece, int fromSqr)
	{
		long moves = 0;

		int row = fromSqr / 8;
		int col = fromSqr % 8;

		switch (piece) {
			case Pieces.KING:
			case Pieces.QUEEN:
			case Pieces.ROOK:
			case Pieces.BISHOP:
			case Pieces.KNIGHT:
				moves = getAttackMoves(player, piece, fromSqr);
				break;
			case Pieces.PAWN:
				int nextRow = row - 1 + 2 * player;
				if ((nextRow & ~7) == 0) {
					if (!bitboard.hasPiece(nextRow * 8 + col)) {
						moves |= 1L << nextRow * 8 + col;
						int nextRow2 = row - 2 + 4 * player;
						if (row == 6 - 5 * player && (nextRow2 & ~7) == 0
								&& !bitboard.hasPiece(nextRow2 * 8 + col))
							moves |= 1L << nextRow2 * 8 + col;
					}
					if (col > 0 && bitboard.hasPiece(1 - player, nextRow * 8 + col - 1))
						moves |= 1L << nextRow * 8 + col - 1;
					if (col < 7 && bitboard.hasPiece(1 - player, nextRow * 8 + col + 1))
						moves |= 1L << nextRow * 8 + col + 1;
				}
				break;
		}

		//TODO ohestalyönti, tornitus
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

		int row = fromSqr / 8;
		int col = fromSqr % 8;

		switch (piece) {
			case Pieces.KING:
				moves |= getMove(player, row - 1, col - 1);
				moves |= getMove(player, row - 1, col);
				moves |= getMove(player, row - 1, col + 1);
				moves |= getMove(player, row, col - 1);
				moves |= getMove(player, row, col + 1);
				moves |= getMove(player, row + 1, col - 1);
				moves |= getMove(player, row + 1, col);
				moves |= getMove(player, row + 1, col + 1);
				break;
			case Pieces.QUEEN:
				moves |= getLineMoves(player, row, col, -1, -1);
				moves |= getLineMoves(player, row, col, -1, 0);
				moves |= getLineMoves(player, row, col, -1, 1);
				moves |= getLineMoves(player, row, col, 0, -1);
				moves |= getLineMoves(player, row, col, 0, 1);
				moves |= getLineMoves(player, row, col, 1, -1);
				moves |= getLineMoves(player, row, col, 1, 0);
				moves |= getLineMoves(player, row, col, 1, 1);
				break;
			case Pieces.ROOK:
				moves |= getLineMoves(player, row, col, -1, 0);
				moves |= getLineMoves(player, row, col, 0, -1);
				moves |= getLineMoves(player, row, col, 0, 1);
				moves |= getLineMoves(player, row, col, 1, 0);
				break;
			case Pieces.BISHOP:
				moves |= getLineMoves(player, row, col, -1, -1);
				moves |= getLineMoves(player, row, col, -1, 1);
				moves |= getLineMoves(player, row, col, 1, -1);
				moves |= getLineMoves(player, row, col, 1, 1);
				break;
			case Pieces.KNIGHT:
				moves |= getMove(player, row - 2, col - 1);
				moves |= getMove(player, row - 2, col + 1);
				moves |= getMove(player, row - 1, col - 2);
				moves |= getMove(player, row - 1, col + 2);
				moves |= getMove(player, row + 2, col - 1);
				moves |= getMove(player, row + 2, col + 1);
				moves |= getMove(player, row + 1, col - 2);
				moves |= getMove(player, row + 1, col + 2);
				break;
			case Pieces.PAWN:
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
		for (int sqr = 0; sqr < 64; ++sqr)
			if (getLegalMoves(sqr) != 0)
				return false;
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
		for (int sqr = 0; sqr < 64; ++sqr)
			if (getLegalMoves(sqr) != 0)
				return false;
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
	 * @return sijainti (0-63)
	 */
	public int getKingSquare(int player)
	{
		int sqr = 0;
		for (; sqr < 64; ++sqr) {
			if (bitboard.hasPiece(player, Pieces.KING, sqr))
				break;
		}
		return sqr;
	}

	@Override
	public GameState clone()
	{
		return new GameState(this);
	}

	@Override
	public int hashCode()
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
	 * Muodostaa bittimaskin lähetin/kunigattaren/tornin siirroista yhteen suuntaan. Lisätään kaikki
	 * ruudut ko. suuntaan, kunnes tullaan laudan reunaan tai vastaan tulee toinen nappula. (Jos
	 * se on erivärinen, lyönti lasketaan mukaan siirtoihin.)
	 *
	 * @param player pelaaja
	 * @param row siirrettävän nappulan rivi
	 * @param col siirrettävän nappulan sarake
	 * @param dr suunnan rivikomponentti (-1,0,1)
	 * @param dc suunnan sarakekomponentti (-1,0,1)
	 * @return bittimaski siirroista
	 */
	private long getLineMoves(int player, int row, int col, int dr, int dc)
	{
		long moves = 0;
		for (;;) {
			row += dr;
			col += dc;

			long move = getMove(player, row, col);
			if (move == 0)
				break;

			moves |= move;
			if (bitboard.hasPiece(1 - player, row * 8 + col))
				break;
		}
		return moves;
	}

	/**
	 * Muodostaa bittimaskin yksittäisestä siirrosta.
	 *
	 * @param player pelaaja
	 * @param row nappulan rivi
	 * @param col nappulan sarake
	 * @return bittimaski
	 */
	private long getMove(int player, int row, int col)
	{
		if (((row | col) & ~7) != 0)
			return 0;

		int sqr = row * 8 + col;
		if (bitboard.hasPiece(player, sqr))
			return 0;

		return 1L << sqr;
	}

	/**
	 * Tarkistaa, onko kunigas uhattuna.
	 *
	 * @param defendingPlayer pelaaja, jonka kuninkaasta on kyse
	 * @return true, jos on uhattu
	 */
	private boolean isKingChecked(int defendingPlayer)
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
	private boolean isSquareThreatened(int defendingPLayer, int sqr)
	{
		int attackingPlayer = 1 - defendingPLayer;
		long sqrBit = 1L << sqr;
		for (int piece = 0; piece < Pieces.COUNT; ++piece) {
			for (int attackingSqr = 0; attackingSqr < 64; ++attackingSqr)
				if (bitboard.hasPiece(attackingPlayer, piece, attackingSqr)) {
					long attackMoves = getAttackMoves(attackingPlayer, piece, attackingSqr);
					if ((sqrBit & attackMoves) != 0)
						return true;
				}
		}
		return false;
	}

	/**
	 * Luo pelitilanteen kopioimalla sen toisesta pelitilanteesta.
	 */
	private GameState(GameState copyFrom)
	{
		nextMovingPlayer = copyFrom.nextMovingPlayer;
		bitboard.copyFrom(copyFrom.bitboard);
		zobristCode = copyFrom.zobristCode;
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
		} while (isCheckMate() || isKingChecked(Players.BLACK));
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
		zobristCode ^= zobristRndPlayer;
	}

	/**
	 * Lisää nappulan ja päivittää Zobrist-koodin.
	 */
	private void addPiece(int player, int piece, int sqr)
	{
		bitboard.addPiece(player, piece, sqr);
		zobristCode ^= zobristRndNumbers[player * Pieces.COUNT * 64 + piece * 64 + sqr];
	}

	/**
	 * Poistaa nappulan ja päivittää Zobrist-koodin.
	 */
	private void removePiece(int player, int piece, int sqr)
	{
		bitboard.removePiece(player, piece, sqr);
		zobristCode ^= zobristRndNumbers[player * Pieces.COUNT * 64 + piece * 64 + sqr];
	}

	/**
	 * Lisää nappulan ja päivittää Zobrist-koodin.
	 */
	private int removePiece(int player, int sqr)
	{
		int capturedPiece = bitboard.removePiece(player, sqr);
		if (capturedPiece != -1)
			zobristCode ^= zobristRndNumbers[player * Pieces.COUNT * 64 + capturedPiece * 64 + sqr];
		return capturedPiece;
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
	 * @param fromSqr lähtöruutu (0-63)
	 * @param toSqr kohderuutu (0-63)
	 * @return true jos laillinen, false jos laiton siirto
	 */
	private boolean isLegalMove(int fromSqr, int toSqr)
	{
		GameState copy = clone();
		copy.move(fromSqr, toSqr);
		return !copy.isKingChecked(nextMovingPlayer);
	}
}
