package chess.domain;

/**
 * Bittimaskit nopeaa siirtojen generointia yms varten.
 *
 * Ratsun ja kuninkaan siirrot riippuvat ainoastaan ruudusta, joten niiden tallentamiseen riittää
 * 64 erilaista bittimaskia.
 *
 * Lähetin ja tornin siirrot riippuvat myös muista nappuloista samalla rivillä/sarakkeella/
 * diagonaaleilla. Niiden sijainnesta muodostetaan ensin bittimaski (ns. relevant occupancies)
 * ja kuvataan se välille 0-4095 käyttäen minimaalista täydellistä hajautusfunktiota
 * hash(sqr, occ) := (MAGIC_MULTIPLIERS[sqr] * occ) >>> (64 - OCCUPANCY_BITS[sqr]).
 * Taikakertoimet on generoitu erillisellä ohjelmalla, joka kokeilee satunnaisia lukuja kunnes
 * löytyy sopiva (ks. http://chessprogramming.wikispaces.com/Looking+for+Magics).
 *
 * Kuningattaren siirrot lasketaan yhdistämällä lähetin ja tornin siirrot.
 */
public final class Movemasks
{
	/**
	 * Bittimaski korotettavien sotilaiden sijainneista kummallekin pelaajalle.
	 */
	public static final long[] PROMOTABLE = {0x000000000000FF00L, 0x00FF000000000000L};

	/**
	 * Bittimaskit ratsun siirroista kussakin pelilaudan ruudussa.
	 */
	public static final long[] KING_MOVES = new long[64];

	/**
	 * Bittimaskit kuninkaan siirroista kussakin pelilaudan ruudussa.
	 */
	public static final long[] KNIGHT_MOVES = new long[64];

	/**
	 * Maskit samalla rivillä/sarakkeella olevista ruuduista (poislukien reunat).
	 */
	private static final long[] ROOK_OCCUPANCY_MASKS = new long[64];

	/**
	 * Montako bittiä vaaditaan samalla rivillä/sarakkeella (poislukien päädyt) olevien nappuloiden
	 * esittämiseen.
	 */
	private static final long[] ROOK_OCCUPANCY_BITS = new long[]{
		12, 11, 11, 11, 11, 11, 11, 12,
		11, 10, 10, 10, 10, 10, 10, 11,
		11, 10, 10, 10, 10, 10, 10, 11,
		11, 10, 10, 10, 10, 10, 10, 11,
		11, 10, 10, 10, 10, 10, 10, 11,
		11, 10, 10, 10, 10, 10, 10, 11,
		11, 10, 10, 10, 10, 10, 10, 11,
		12, 11, 11, 11, 11, 11, 11, 12
	};

	/**
	 * Ruutukohtaiset kertoimet täydellistä hajautusfunktiota varten.
	 */
	private static final long[] ROOK_OCCUPANCY_MAGIC_MULTIPLIERS = new long[]{
		0X808000802A144000L, 0XA040200010004000L, 0X0880088020001000L, 0X8680040801100080L,
		0X02000490080200A0L, 0X0300010008040002L, 0X4880328001000200L, 0X0200010200284084L,
		0X0000800080204000L, 0X0081004000802100L, 0X0641801000A00084L, 0X10430010010900A0L,
		0X2009001100040801L, 0X28A0800400020080L, 0X0204001844290A10L, 0X0209002100004082L,
		0X0380004040002000L, 0X2030004020084000L, 0X0050808020001000L, 0X0421818010004800L,
		0X4901010010040800L, 0X0202808002000400L, 0XE140040008224110L, 0X0000020000810044L,
		0X0080004040002000L, 0X900A004600210080L, 0X04C1100280200080L, 0X0482100280080080L,
		0X0804000808004080L, 0X088A000200080411L, 0X0001002100042200L, 0X10140B0600308044L,
		0X0810904000800028L, 0X0040002800601000L, 0X0211004011002000L, 0X2120204012000A00L,
		0X0006800800800400L, 0X0101000401000802L, 0X8002021854001001L, 0X1000801040801100L,
		0X8940820041020025L, 0X0800402010004004L, 0X0261002000430011L, 0X0090000C21010010L,
		0X2145000408010010L, 0X0006000400808002L, 0X084100020005000CL, 0X3000484B8C120009L,
		0X0000402200810200L, 0X2008201880400080L, 0X2104420010248200L, 0X001C480081900080L,
		0X0001018800108500L, 0X005A000280040080L, 0X0220081081020400L, 0X1000010408488200L,
		0X3081020040221282L, 0X0080248700124001L, 0X0118400820001101L, 0X002020400A00460EL,
		0X4002012004108802L, 0X10810004004A0829L, 0X0220480082100104L, 0X4800008400483102L
	};

	/**
	 * Tornin siirrot jokaiselle ruudun ja muiden nappuloiden kombinaatiolle.
	 */
	private static final long[][] ROOK_MOVES = new long[64][];

	/**
	 * Maskit samoilla diagonaaleilla olevista ruuduista (poislukien reunat).
	 */
	private static final long[] BISHOP_OCCUPANCY_MASKS = new long[64];

	/**
	 * Montako bittiä vaaditaan samoilla diagonaaleilla (poislukien reunat) olevien nappuloiden
	 * esittämiseen.
	 */
	private static final long[] BISHOP_OCCUPANCY_BITS = new long[]{
		6, 5, 5, 5, 5, 5, 5, 6,
		5, 5, 5, 5, 5, 5, 5, 5,
		5, 5, 7, 7, 7, 7, 5, 5,
		5, 5, 7, 9, 9, 7, 5, 5,
		5, 5, 7, 9, 9, 7, 5, 5,
		5, 5, 7, 7, 7, 7, 5, 5,
		5, 5, 5, 5, 5, 5, 5, 5,
		6, 5, 5, 5, 5, 5, 5, 6
	};

	/**
	 * Ruutukohtaiset kertoimet täydellistä hajautusfunktiota varten.
	 */
	private static final long[] BISHOP_OCCUPANCY_MAGIC_MULTIPLIERS = new long[]{
		0X084084481A004010L, 0X012084810205A444L, 0X0110210841002002L, 0X0029040103780080L,
		0X0010882000001400L, 0X00042208C0226000L, 0X010C809888400804L, 0X0001840104901404L,
		0X8000411001410902L, 0X000604810C210A00L, 0X0800040102020440L, 0X0006044104200008L,
		0X00212405040C8090L, 0X4006008805400808L, 0X4040028221114000L, 0X0000060111011000L,
		0X000A004008014408L, 0X9042000888080098L, 0X0020800108030040L, 0X0808001882004480L,
		0X0005000820082400L, 0X081A000108022200L, 0X10A2098400A40480L, 0X5004420200420888L,
		0X1020440010100250L, 0XC808054008010820L, 0X0058140002002202L, 0X0002002002008200L,
		0X0011001001004000L, 0X000812000C410080L, 0X0A21040022008480L, 0X0A8101280A060100L,
		0X2110820801208880L, 0X0384040440025004L, 0X0001080202010400L, 0X0808400820020200L,
		0X8400408020020200L, 0X4000900080410080L, 0X0084014400005400L, 0X0400808900120102L,
		0X08040421040008C1L, 0X0040820842802000L, 0X00110040B2031006L, 0X0000202011040811L,
		0X8100400109002202L, 0X4081010102000100L, 0X0062101A02088081L, 0X0008320052000049L,
		0X2900920842404010L, 0X2024450088201800L, 0X0000610841100000L, 0X0002000020884103L,
		0X6000808420820000L, 0X0040101092182001L, 0X0420201400A48000L, 0X0020380321012204L,
		0X0800804400844000L, 0X400A010082012010L, 0X4200100090480802L, 0X04800010102A0800L,
		0X0200200020204120L, 0X9406240820488088L, 0X28A0200410408109L, 0X1C04011001020880L
	};

	/**
	 * Lähetin siirrot jokaiselle ruudun ja muiden nappuloiden kombinaatiolle.
	 */
	private static final long[][] BISHOP_MOVES = new long[64][];

	/**
	 * Generoi hakutaulukot.
	 */
	static {
		for (int sqr = 0; sqr < 64; ++sqr) {
			int row = sqr / 8;
			int col = sqr % 8;
			KING_MOVES[sqr] = generateKingMoves(row, col);
			KNIGHT_MOVES[sqr] = generateKnightMoves(row, col);
			ROOK_OCCUPANCY_MASKS[sqr] = generateRookOccupancyMask(sqr, row, col);
			ROOK_MOVES[sqr] = generateRookMoves(sqr, row, col);
			BISHOP_OCCUPANCY_MASKS[sqr] = generateBishopOccupancyMask(sqr, row, col);
			BISHOP_MOVES[sqr] = generateBishopMoves(sqr, row, col);
		}
	}

	/**
	 * Palauttaa tornin siirrot annetusta ruudusta, ottaen huomioon muiden nappuloiden sijainnit.
	 *
	 * @param fromSqr lähtöruutu
	 * @param allPieces kaikki nappulat
	 * @param friendlyPieces samanväriset nappulat
	 * @return siirot bittimaskina
	 */
	public static long getRookMoves(int fromSqr, long allPieces, long friendlyPieces)
	{
		int hash = rookOccupancyHash(fromSqr, allPieces);
		return ROOK_MOVES[fromSqr][hash] & ~friendlyPieces;
	}

	/**
	 * Palauttaa lähetin siirrot annetusta ruudusta, ottaen huomioon muiden nappuloiden sijainnit.
	 *
	 * @param fromSqr lähtöruutu
	 * @param allPieces kaikki nappulat
	 * @param friendlyPieces samanväriset nappulat
	 * @return siirot bittimaskina
	 */
	public static long getBishopMoves(int fromSqr, long allPieces, long friendlyPieces)
	{
		int hash = bishopOccupancyHash(fromSqr, allPieces);
		return BISHOP_MOVES[fromSqr][hash] & ~friendlyPieces;
	}

	/**
	 * Palauttaa kuningattaren siirrot annetusta ruudusta, ottaen huomioon muiden nappuloiden
	 * sijainnit.
	 *
	 * @param fromSqr lähtöruutu
	 * @param allPieces kaikki nappulat
	 * @param friendlyPieces samanväriset nappulat
	 * @return siirot bittimaskina
	 */
	public static long getQueenMoves(int fromSqr, long allPieces, long friendlyPieces)
	{
		int rhash = rookOccupancyHash(fromSqr, allPieces);
		int bhash = bishopOccupancyHash(fromSqr, allPieces);
		long moves = ROOK_MOVES[fromSqr][rhash] | BISHOP_MOVES[fromSqr][bhash];
		return moves & ~friendlyPieces;
	}

	/**
	 * Täydellinen hajautusfunktio samalla rivillä/sarakkeella oleville nappuloille.
	 *
	 * @param sqr ruutu
	 * @param allPieces kaikki laudalla olevat nappulat
	 * @return hajautusarvo
	 */
	private static int rookOccupancyHash(int sqr, long allPieces)
	{
		long hash = allPieces;
		hash &= ROOK_OCCUPANCY_MASKS[sqr];
		hash *= ROOK_OCCUPANCY_MAGIC_MULTIPLIERS[sqr];
		hash >>>= 64 - ROOK_OCCUPANCY_BITS[sqr];
		return (int) hash;
	}

	/**
	 * Täydellinen hajautusfunktio samalla diagonaalilla oleville nappuloille.
	 *
	 * @param sqr ruutu
	 * @param allPieces kaikki laudalla olevat nappulat
	 * @return hajautusarvo
	 */
	private static int bishopOccupancyHash(int sqr, long allPieces)
	{
		long hash = allPieces;
		hash &= BISHOP_OCCUPANCY_MASKS[sqr];
		hash *= BISHOP_OCCUPANCY_MAGIC_MULTIPLIERS[sqr];
		hash >>>= 64 - BISHOP_OCCUPANCY_BITS[sqr];
		return (int) hash;
	}

	/**
	 * Luo maskin samalla rivillä/sarakkeella olevista ruuduista.
	 */
	private static long generateRookOccupancyMask(int sqr, int row, int col)
	{
		long rowMask = 0x7EL << (row * 8); // Rivi ilman ensimmäistä/viimeistä ruutua.
		long colMask = 0x0001010101010100L << col; // Sarake ilman ensimmäistä/viimeistä ruutua.
		return (rowMask | colMask) & ~(1L << sqr); // Rivi + sarake, poislukien oma sijainti.
	}

	/**
	 * Luo maskin samalla diagonaalilla olevista ruuduista.
	 */
	private static long generateBishopOccupancyMask(int sqr, int row, int col)
	{
		long mask = 0;
		mask |= generateSlidingMoves(row, col, -1, -1, 0);
		mask |= generateSlidingMoves(row, col, -1, 1, 0);
		mask |= generateSlidingMoves(row, col, 1, -1, 0);
		mask |= generateSlidingMoves(row, col, 1, 1, 0);
		long borders = 0xFF818181818181FFL;
		return mask & ~borders & ~(1L << sqr); // Poistetaan reunat ja oma sijainti.
	}

	/**
	 * Generoi tornin siirrot annetussa ruudussa jokaiselle muiden nappuloiden variaatiolle.
	 */
	private static long[] generateRookMoves(int sqr, int row, int col)
	{
		int[] bitPositions = getBitPositions(ROOK_OCCUPANCY_MASKS[sqr]);
		int variationCount = 1 << Long.bitCount(ROOK_OCCUPANCY_MASKS[sqr]);
		long[] moves = new long[variationCount];
		boolean[] used = new boolean[variationCount];
		for (int i = 0; i < variationCount; ++i) {
			long occupancy = getOccupancyVariation(bitPositions, i);
			int hash = rookOccupancyHash(sqr, occupancy);
			if (used[hash])
				throw new RuntimeException("Rook occupancy hash collision.");
			used[hash] = true;
			moves[hash] |= generateSlidingMoves(row, col, -1, 0, occupancy);
			moves[hash] |= generateSlidingMoves(row, col, 0, -1, occupancy);
			moves[hash] |= generateSlidingMoves(row, col, 0, 1, occupancy);
			moves[hash] |= generateSlidingMoves(row, col, 1, 0, occupancy);
		}
		return moves;
	}

	/**
	 * Generoi lähetin siirrot annetussa ruudussa jokaiselle muiden nappuloiden variaatiolle.
	 */
	private static long[] generateBishopMoves(int sqr, int row, int col)
	{
		int[] bitPositions = getBitPositions(BISHOP_OCCUPANCY_MASKS[sqr]);
		int variationCount = 1 << Long.bitCount(BISHOP_OCCUPANCY_MASKS[sqr]);
		long[] moves = new long[variationCount];
		boolean[] used = new boolean[variationCount];
		for (int i = 0; i < variationCount; ++i) {
			long occupancy = getOccupancyVariation(bitPositions, i);
			int hash = bishopOccupancyHash(sqr, occupancy);
			if (used[hash])
				throw new RuntimeException("Bishop occupancy hash collision.");
			used[hash] = true;
			moves[hash] |= generateSlidingMoves(row, col, -1, -1, occupancy);
			moves[hash] |= generateSlidingMoves(row, col, -1, 1, occupancy);
			moves[hash] |= generateSlidingMoves(row, col, 1, -1, occupancy);
			moves[hash] |= generateSlidingMoves(row, col, 1, 1, occupancy);
		}
		return moves;
	}

	/**
	 * Muodostaa bittimaskin lähetin/tornin/kuningattaren siirroista yhteen suuntaan. Lisätään
	 * kaikki ruudut ko. suuntaan, kunnes tullaan laudan reunaan tai vastaan tulee toinen nappula.
	 * (Tässä vaiheessa ko. siirto lasketaan mukaan riippumatta kohdatun nappulan väristä.)
	 *
	 * @param row siirrettävän nappulan rivi
	 * @param col siirrettävän nappulan sarake
	 * @param dr suunnan rivikomponentti (-1,0,1)
	 * @param dc suunnan sarakekomponentti (-1,0,1)
	 * @param occupancy bittimaski kaikista nappuloista
	 * @return bittimaski siirroista
	 */
	private static long generateSlidingMoves(int row, int col, int dr, int dc, long occupancy)
	{
		long moves = 0;
		for (;;) {
			row += dr;
			col += dc;

			long move = getMove(row, col);
			if (move == 0)
				break;
			moves |= move;
			if ((occupancy & move) != 0)
				break;
		}
		return moves;
	}

	/**
	 * Muodostaa uuden luvun siirtämällä vähiten merkisevät bitit uuteen kohtaan.
	 *
	 * @param bitPositions bittien uudet sijainnit
	 * @param variationIdx muunnettava luku
	 * @return uusi bittimaski
	 */
	private static long getOccupancyVariation(int[] bitPositions, int variationIdx)
	{
		long occupancy = 0;
		for (int i = 0; i < bitPositions.length; ++i) {
			if ((variationIdx & (1 << i)) != 0)
				occupancy |= 1L << bitPositions[i];
		}
		return occupancy;
	}

	/**
	 * Palauttaa kaikkien ykkösbittien sijainnit.
	 */
	private static int[] getBitPositions(long mask)
	{
		int[] bitPositions = new int[Long.bitCount(mask)];
		for (int i = 0; i < bitPositions.length; ++i) {
			bitPositions[i] = Long.numberOfTrailingZeros(mask);
			mask -= Long.lowestOneBit(mask);
		}
		return bitPositions;
	}

	/**
	 * Generoi bittimaskin kuninkaan siirroista yhdessä ruudussa.
	 *
	 * @param row rivi
	 * @param col sarake
	 * @return siirrot bittimaskina
	 */
	private static long generateKingMoves(int row, int col)
	{
		long moves = 0;
		moves |= getMove(row - 1, col - 1);
		moves |= getMove(row - 1, col);
		moves |= getMove(row - 1, col + 1);
		moves |= getMove(row, col - 1);
		moves |= getMove(row, col + 1);
		moves |= getMove(row + 1, col - 1);
		moves |= getMove(row + 1, col);
		moves |= getMove(row + 1, col + 1);
		return moves;
	}

	/**
	 * Generoi bittimaskin ratsun siirroista yhdessä ruudussa.
	 *
	 * @param row rivi
	 * @param col sarake
	 * @return siirrot bittimaskina
	 */
	private static long generateKnightMoves(int row, int col)
	{
		long moves = 0;
		moves |= getMove(row - 2, col - 1);
		moves |= getMove(row - 2, col + 1);
		moves |= getMove(row - 1, col - 2);
		moves |= getMove(row - 1, col + 2);
		moves |= getMove(row + 2, col - 1);
		moves |= getMove(row + 2, col + 1);
		moves |= getMove(row + 1, col - 2);
		moves |= getMove(row + 1, col + 2);
		return moves;
	}

	/**
	 * Muodostaa bittimaskin yhdestä ruudusta, jos se sijaitsee pelilaudalla.
	 *
	 * @param row rivi
	 * @param col sarake
	 * @return bittimaski (0 jos ruutu laudan ulkopuolella)
	 */
	private static long getMove(int row, int col)
	{
		if (((row | col) & ~7) != 0)
			return 0;
		return 1L << row * 8 + col;
	}
}
