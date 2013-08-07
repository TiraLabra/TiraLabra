package chess.ai;

final class StateInfo
{
	int depth;

	int score;

	int bestMoveFrom = -1;

	int bestMoveTo;

	int bestMovePieceType;

	boolean active;
}
