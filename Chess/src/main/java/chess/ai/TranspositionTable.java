package chess.ai;

import chess.domain.GameState;
import java.util.HashMap;
import java.util.Map;

final class TranspositionTable
{
	private final Map<GameState, StateInfo> positions = new HashMap<GameState, StateInfo>();

	TranspositionTable()
	{
	}

	StateInfo get(GameState state)
	{
		return positions.get(state);
	}

	void put(GameState state, StateInfo info)
	{
		positions.put(state, info);
	}

	void clear()
	{
		positions.clear();
	}

	int size()
	{
		return positions.size();
	}
}
