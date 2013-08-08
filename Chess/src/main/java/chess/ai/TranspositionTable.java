package chess.ai;

import chess.domain.GameState;
import java.util.HashMap;

/**
 * Transpositiotaulu, eli hajautustaulu johon tallennetaan jo analysoidut pelitilanteet, ja haun
 * tulokset.
 *
 * Avaimena käytetään pelitilanteita, joille lasketaan Zobrist-hajautusarvo.
 *
 * //TODO oma toteutus joka ei tee muistiallokaatioita
 */
final class TranspositionTable extends HashMap<GameState, StateInfo>
{
}
