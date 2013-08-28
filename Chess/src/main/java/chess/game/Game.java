package chess.game;

import chess.domain.GameState;
import chess.domain.Players;
import chess.util.CustomArrayList;
import java.util.List;

/**
 * Peliluokka. Ottaa parametrinaan kaksi pelaajaobjekti, ja suorittaa niiden siirtoja kunnes
 * peli päättyy. Pelitilanteesta annetaan tietoa Observer-rajapinnan kautta.
 */
public class Game implements Runnable
{
	/**
	 * Pelitilanne.
	 */
	private final GameState state;

	/**
	 * Pelaajat.
	 */
	private final Player[] players = new Player[2];

	/**
	 * Pelin voittaja (-1 jos kesken tai patti).
	 */
	private int result = -1;

	/**
	 * Observer-objekti, jolle annetaan tietoa pelin etenemisestä.
	 */
	private final Observer observer;

	/**
	 * Lista kaikista siirroista.
	 */
	private final List<Integer> moves = new CustomArrayList<Integer>();

	/**
	 * Luo uuden pelin käyttäen annettua aloituspelitilannetta.
	 *
	 * @param state pelitilanne
	 * @param whitePlayer valkoinen pelaaja
	 * @param blackPlayer musta pelaaja
	 * @param observer tarkkailijaobjekti
	 */
	public Game(GameState state, Player whitePlayer, Player blackPlayer, Observer observer)
	{
		this.state = state;
		this.players[Players.WHITE] = whitePlayer;
		this.players[Players.BLACK] = blackPlayer;
		this.observer = observer;
	}

	/**
	 * Käynnistää pelin (mahdollisesti omassa säikeessä). Kutsuu vuorotellen pelaajien getMove()-
	 * metodia ja päivittää pelitilannetta kunnes päädytään mattiin/pattiin.
	 */
	@Override
	public void run()
	{
		int currentPlayer = Players.WHITE;

		while (!state.isCheckMate() && !state.isStaleMate()) {
			try {
				getAndProcessMove(currentPlayer);
			} catch (InterruptedException e) {
				return;
			}
			currentPlayer = 1 ^ currentPlayer;
		}

		if (state.isCheckMate())
			result = 1 ^ state.getNextMovingPlayer();

		if (observer != null)
			observer.notifyEnd(state, result);
	}

	/**
	 * Palauttaa pelitilanteen.
	 *
	 * @return
	 */
	public GameState getState()
	{
		return state;
	}

	/**
	 * Palauttaa pelin lopputuloksen.
	 *
	 * @return voittanut pelaaja (0-1) tai -1 jos patti
	 */
	public int getResult()
	{
		return result;
	}

	/**
	 * Palauttaa listan kaikista siirroista.
	 *
	 * @return
	 */
	public List<Integer> getMoves()
	{
		return moves;
	}

	/**
	 * Pyytää pelaajalta siirron ja käsittelee sen.
	 *
	 * @param player pelaaja (0-1)
	 * @throws InterruptedException
	 */
	private void getAndProcessMove(int player) throws InterruptedException
	{
		int move = players[player].getMove(state);
		state.makeMove(move);
		moves.add(move);
		if (observer != null)
			observer.notifyMove(state, moves.size() - 1, players[player], move);
	}
}
