package chess.gui;

import chess.ai.MinMaxAI;
import chess.game.Player;
import chess.util.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * Valikko joka mahdollistaa tekoälypelaajan asetusten muuttamisen.
 */
class PlayerConfigMenu extends JMenu implements ActionListener
{
	/**
	 * Valittavat aikarajavaihtoehdot (millisekunteja).
	 */
	private final static int[] TIME_LIMIT_OPTIONS = new int[]{
		10, 20, 30, 50, 100, 200, 300, 500, 1000, 2000, 3000, 5000, 10000, 0
	};

	/**
	 * Valittavat maksimisyvyysvaihtoehdot (millisekunteja).
	 */
	private final static int[] DEPTH_OPTIONS = new int[]{
		2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 20, 30, 100
	};

	/**
	 * Loggeri luotua tekoälyobjektia varten.
	 */
	private Logger logger;

	/**
	 * Ihmispelaaja.
	 */
	private Player humanPlayer;

	/**
	 * Alivalikot.
	 */
	private JMenu depthMenu, timeLimitMenu;

	/**
	 * Checkbox quiescence-haun enablointia varten.
	 */
	private JMenuItem quiescenceItem;

	/**
	 * Checkbox tekoälyn enablointia varten.
	 */
	private JCheckBoxMenuItem aiItem;

	/**
	 * Luo uuden configuraatiovalikon.
	 *
	 * @param caption otsikko
	 * @param humanPlayer ihmispelaaja
	 * @param logger loggeri tekoälyobjekteja varten
	 * @param aiSelected true jos alussa valittuna on tekoäly, false jos valittuna ihmispelaaja
	 */
	PlayerConfigMenu(String caption, Player humanPlayer, Logger logger, boolean aiSelected)
	{
		super(caption);

		this.humanPlayer = humanPlayer;
		this.logger = logger;

		aiItem = createCheckBoxMenuItem(this, "AI player");
		createTimeLimitMenu();
		createDepthMenu();
		quiescenceItem = createCheckBoxMenuItem(this, "Quiescence search");

		timeLimitMenu.getItem(8).setSelected(true);
		depthMenu.getItem(DEPTH_OPTIONS.length - 1).setSelected(true);
		quiescenceItem.setSelected(true);
		aiItem.setSelected(aiSelected);

		updateEnabledStatus();
	}

	/**
	 * Palauttaa asetusten mukaisen pelaajaobjektin.
	 *
	 * @return pelaaja
	 */
	Player getPlayer()
	{
		if (!aiItem.isSelected())
			return humanPlayer;
		double timeLimit = 0.001 * TIME_LIMIT_OPTIONS[getSelectedIdx(timeLimitMenu)];
		int maxDepth = DEPTH_OPTIONS[getSelectedIdx(depthMenu)];
		int quiescenceSearchDepth = quiescenceItem.isSelected() ? 30 : 0;
		return new MinMaxAI(logger, maxDepth, quiescenceSearchDepth, timeLimit, 3);
	}

	/**
	 * Luo alivalikon aikarajavaihtoehtoja varten.
	 */
	private void createTimeLimitMenu()
	{
		timeLimitMenu = new JMenu("Time limit");
		add(timeLimitMenu);

		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < TIME_LIMIT_OPTIONS.length; ++i) {
			String txt = TIME_LIMIT_OPTIONS[i] > 0 ? "" + TIME_LIMIT_OPTIONS[i]
					+ " ms" : "Unlimited";
			JMenuItem item = new JRadioButtonMenuItem(txt);
			timeLimitMenu.add(item);
			group.add(item);
		}
	}

	/**
	 * Luo alivalikon syvyysvaihtoehtoja varten.
	 */
	private void createDepthMenu()
	{
		depthMenu = new JMenu("Max search depth");
		add(depthMenu);

		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < DEPTH_OPTIONS.length; ++i) {
			JMenuItem item = new JRadioButtonMenuItem("" + DEPTH_OPTIONS[i]);
			depthMenu.add(item);
			group.add(item);
		}
	}

	/**
	 * Luo yksittäisen checkbox-valikkoelementin.
	 */
	private JCheckBoxMenuItem createCheckBoxMenuItem(JMenu menu, String caption)
	{
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(caption);
		item.addActionListener(this);
		menu.add(item);
		return item;
	}

	/**
	 * Palauttaa valitun elementin indeksin.
	 */
	private static int getSelectedIdx(JMenu menu)
	{
		for (int i = 0; i < menu.getItemCount(); ++i)
			if (menu.getItem(i).isSelected())
				return i;
		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == aiItem)
			updateEnabledStatus();
	}

	/**
	 * Disabloi tekoälyasetukset jos valittuna on ihmispelaaja.
	 */
	private void updateEnabledStatus()
	{
		depthMenu.setEnabled(aiItem.isSelected());
		timeLimitMenu.setEnabled(aiItem.isSelected());
		quiescenceItem.setEnabled(aiItem.isSelected());
	}
}
