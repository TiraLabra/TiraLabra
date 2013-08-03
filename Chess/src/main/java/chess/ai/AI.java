package chess.ai;

import chess.domain.GameState;

public interface AI
{
	void move(GameState state, int player);
}
