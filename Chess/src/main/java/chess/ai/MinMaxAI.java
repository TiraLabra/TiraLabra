package chess.ai;

import chess.domain.GameState;
import chess.domain.Pieces;
import chess.util.Logger;

public class MinMaxAI implements AI
{
	private static final int DEFAULT_SEARCH_DEPTH = 8;

	private final int searchDepth; // Pitää olla vähintään 2!

	private int currentSearchDepth;

	private final int transpDepth = 6;

	private final Logger logger;

	private boolean loggingEnabled = false;

	private int count;

	private int transpCount;

	private TranspositionTable transposTable = new TranspositionTable();

	public MinMaxAI(Logger logger)
	{
		this(logger, DEFAULT_SEARCH_DEPTH);
	}

	public MinMaxAI(Logger logger, int searchDepth)
	{
		this.logger = logger;
		this.searchDepth = searchDepth;
	}

	public void move(GameState state)
	{
		transposTable.clear();
		for (int d = 2; d <= searchDepth; ++d) {
			count = 0;
			transpCount = 0;
			currentSearchDepth = d;
			searchWithTranspositionLookup(d, -Integer.MAX_VALUE, Integer.MAX_VALUE, state);
		}
		log("count=" + count);
		log("transpCount=" + transpCount);
		log("transpTableSize=" + transposTable.size());
		StateInfo info = transposTable.get(state);
		state.move(info.bestMoveFrom, info.bestMoveTo);
	}

	private int searchWithTranspositionLookup(int depth, int alpha, int beta, GameState state)
	{
		boolean add = false;
		StateInfo info = transposTable.get(state);
		if (info != null) {
			if (info.depth >= depth) {
				++transpCount;
				return info.score;
			}
		} else if (depth >= currentSearchDepth - transpDepth) {
			info = new StateInfo();
			add = true;
		}

		int score = search(depth, alpha, beta, state, info);

		if (info != null) {
			info.depth = depth;
			info.score = score;
			if (add)
				transposTable.put(state.clone(), info);
		}

		return score;
	}

	private int search(int depth, int alpha, int beta, GameState state, StateInfo info)
	{
		if (depth == 0 || !state.areBothKingsAlive())
			return getScore(state, depth);

		int player = state.getNextMovingPlayer();

		if (info != null && info.bestMoveFrom != -1) {
			alpha = searchMove(depth, alpha, beta, state, info.bestMovePieceType, info.bestMoveFrom,
					info.bestMoveTo, info);
			if (alpha >= beta)
				return alpha;
		}

		for (int pieceType = Pieces.COUNT - 1; pieceType >= 0; --pieceType) {
			long pieces = state.getPieces(player, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int fromSqr = Long.numberOfTrailingZeros(pieces);
				long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);

				for (int capturedPiece = 0; capturedPiece < Pieces.COUNT; ++capturedPiece) {
					long captureMoves = moves & state.getPieces(1 - player, capturedPiece);
					alpha = iterateMoves(depth, alpha, beta, state, pieceType, fromSqr,
							captureMoves, info);
					if (alpha >= beta)
						return alpha;
				}
			}
		}

		for (int pieceType = 0; pieceType < Pieces.COUNT; ++pieceType) {
			long pieces = state.getPieces(player, pieceType);
			for (; pieces != 0; pieces -= Long.lowestOneBit(pieces)) {
				int fromSqr = Long.numberOfTrailingZeros(pieces);
				long moves = state.getPseudoLegalMoves(player, pieceType, fromSqr);
				moves &= ~state.getPieces(1 - player);
				alpha = iterateMoves(depth, alpha, beta, state, pieceType, fromSqr, moves, info);
				if (alpha >= beta)
					return alpha;
			}
		}

		return alpha;
	}

	private int iterateMoves(int depth, int alpha, int beta, GameState state, int pieceType,
			int fromSqr, long moves, StateInfo info)
	{
		for (; moves != 0; moves -= Long.lowestOneBit(moves)) {
			int toSqr = Long.numberOfTrailingZeros(moves);
			alpha = searchMove(depth, alpha, beta, state, pieceType, fromSqr, toSqr, info);
			if (alpha >= beta)
				return alpha;
		}

		return alpha;
	}

	private int searchMove(int depth, int alpha, int beta, GameState state, int pieceType,
			int fromSqr, int toSqr, StateInfo info)
	{
		if (info != null && (info.depth == -1 && fromSqr == info.bestMoveFrom
				&& toSqr == info.bestMoveTo))
			return alpha;

		int capturedPiece = state.move(fromSqr, toSqr, pieceType);
		int score = -searchWithTranspositionLookup(depth - 1, -beta, -alpha, state);
		state.undoMove(fromSqr, toSqr, pieceType, capturedPiece);

		if (score > alpha) {
			if (info != null) {
				if (depth == searchDepth && loggingEnabled)
					log("" + fromSqr + " " + toSqr + " " + (score - getScore(state, 0)));
				info.bestMoveFrom = fromSqr;
				info.bestMoveTo = toSqr;
				info.bestMovePieceType = pieceType;
			}
			alpha = score;
		}

		return alpha;
	}

	private int getScore(GameState state, int depth)
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

	public void setLoggingEnabled(boolean enabled)
	{
		loggingEnabled = enabled;
	}

	private void log(String msg)
	{
		if (loggingEnabled)
			logger.logMessage(msg);
	}
}
