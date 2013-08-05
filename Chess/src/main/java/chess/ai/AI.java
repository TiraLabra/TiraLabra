package chess.ai;

import chess.domain.GameState;

public interface AI
{
	void move(GameState state);

	void setLoggingEnabled(boolean enabled);
}
