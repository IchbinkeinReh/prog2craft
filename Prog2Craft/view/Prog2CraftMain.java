package view;

import static prog2.project5.enums.Direction.DOWN;
import static prog2.project5.enums.Direction.LEFT;
import static prog2.project5.enums.Direction.RIGHT;
import static prog2.project5.enums.Direction.UP;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


import prog2.project5.autoplay.ActorController;
import prog2.project5.autoplay.ControllerFactory;
import prog2.project5.autoplay.GhostAutoPlayer;
import prog2.project5.autoplay.PacManAutoPlayer;
import prog2.project5.game.AePlayWave;
import prog2.project5.game.Board;
import prog2.project5.game.GameInfo;
import prog2.project5.game.GameObserverAdpater;
import prog2.project5.game.GhostInfo;
import prog2.project5.game.PacManGame;
import view.ControlPlayer;
import view.Prog2CraftComponent;

/**
 * BoardView provides a basic view for the pacMan game, and buttons to
 * manipulate the game.
 */

public class Prog2CraftMain {

	private static final String[] DEFAULT_BOARD = {
			"############################",
			"#------------##------------#",
			"#X####-#####-##-#####-####X#",
			"#--------------------------#",
			"#-####-##-########-##-####-#",
			"#------##----##----##------#",
			"######-#####-##-#####-######",
			"######-##----------##-######",
			"######-##-####-###-##-######",
			"----------##GGGG##----------",
			"######-##-########-##-######",
			"######-##----------##-######",
			"######-##-########-##-######",
			"#------------##------------#",
			"#-####-#####-##-#####-####-#",
			"#X--##--------P-------##--X#",
			"###-##-##-########-##-##-###",
			"#------##----##----##------#",
			"#-##########-##-##########-#",
			"#--------------------------#",
			"############################" };

	@SuppressWarnings("unused")
	private static final String[] DEBUG_BOARD = { "#######",
												  "#----G#", 
												  "#-#####",
												  "#----##",
												  "####-##",
												  "#----##",
												  "#-#####",
												  "#P#####",
												  "#######"};
	
	@SuppressWarnings("unused")
	private static final String[] DEBUG2_BOARD =  {"#####",
												   "#--G#", 
												   "#-#-#",
												   "#P--#",
												   "#####"};
	
	@SuppressWarnings("unused")
	private static final String[] DEBUG3_BOARD =  {"-----",
												   "-----", 
												   "-----",
												   "-----",
												   "----P"};
	
	public static AePlayWave soundLoop = new AePlayWave("Data/PacManLoop.wav", true);
	public static AePlayWave soundLoop2 = new AePlayWave("Data/PacManLoop2.wav", true);

	/**
	 * Starts a new Pac-Man game. If parameter -a is given the game is started
	 * in auto player mode.
	 * 
	 * @param args
	 *            the command line arguments.
	 */
	public static void main(String[] args) {
		boolean autoPlay = false;
		if (args.length > 0) {
			String arg = args[0];
			if (arg.startsWith("-a")) {
				autoPlay = true;
			}
		}
		createBoardView(autoPlay);
	}
	
	/**
	 * Creates a view for the board.
	 * 
	 * @param autoPlay
	 * 
	 * @param game
	 *            the game to create the view for
	 */
	public static void createBoardView(boolean autoPlay) {
		Board board = Board.parse(DEFAULT_BOARD);
		//Board board = Board.parse(DEBUG_BOARD);

		final ControlPlayer controlPlayer = new ControlPlayer();
		ControllerFactory cf = getControllerFactory(controlPlayer, autoPlay);

		PacManGame game = new PacManGame(board, cf);
		final JFrame frame = new JFrame("P R O G 2 | B O A R D   V I E W");
		Container cp = frame.getContentPane();
		cp.setLayout(new BorderLayout());
		Prog2CraftComponent pacManComponent = new Prog2CraftComponent(game
				.getGameInfo());
		controlPlayer.setGame(game);
		controlPlayer.setComponent(pacManComponent);

		JPanel rightPanel = createRightPanel(game, controlPlayer);
		
		final Timer timer = new Timer();
		TimerTask tt = new PacManTask(game, timer, pacManComponent);
		

		cp.add(pacManComponent, BorderLayout.CENTER);
		cp.add(rightPanel, BorderLayout.LINE_END);
		
		if (!autoPlay) {
			MyKeyListener keyListener = new MyKeyListener(controlPlayer);
			pacManComponent.setFocusable(true);
			addKeyListenerToAllComponents(keyListener, cp);
		}
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		boolean sound = true;
	
		if (sound)
		{
		PacManGame.sound = true;
		soundLoop.start();
		soundLoop2.start();
		new AePlayWave("Data/PacManStart.wav", false).start();
		try {
			Thread.sleep(4400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		soundLoop.playloop();
		soundLoop2.quiet();
		}
		
		timer.schedule(tt, 0, 100);
	}

	/**
	 * TimerTask that triggers the step method of the Pac-Man game
	 * and repaints the given component after each step until the game is over.
	 */
	private static class PacManTask extends TimerTask {

		private PacManGame game;
		private Timer timer;
		private Component comp;

		public PacManTask(PacManGame game, Timer timer, Component comp) {
			super();
			this.game = game;
			this.timer = timer;
			this.comp = comp;
		}

		@Override
		public void run() {
			synchronized (game) {
				if (game.isGameOver()) {
					timer.cancel();
				}
				game.step();
				comp.repaint();
			}		
		}

	}

	/**
	 * Returns a suitable {@link ControllerFactory}
	 * 
	 * @param controlPlayer
	 *            a ControlPlayer instance to control Pac-Man.
	 * @param autoPlayMode
	 *            indicates whether game is in autoplayer mode.
	 * @return a suitable {@link ControllerFactory}
	 */
	private static ControllerFactory getControllerFactory(
			final ControlPlayer controlPlayer, boolean autoPlayMode) {
		ControllerFactory cf;
		if (autoPlayMode) {
			cf = new ControllerFactory() {
				@Override
				public ActorController getGhostController(GameInfo info,
						GhostInfo ghost) {
					return new GhostAutoPlayer(info, ghost);
				}

				@Override
				public ActorController getPacManController(GameInfo info) {
					return new PacManAutoPlayer(info);
				}
			};
		} else {
			cf = new ControllerFactory() {
				@Override
				public ActorController getGhostController(GameInfo info,
						GhostInfo ghost) {
					return new GhostAutoPlayer(info, ghost);
				}

				@Override
				public ActorController getPacManController(GameInfo info) {
					return controlPlayer;
				}
			};
		}
		return cf;
	}
	/**
	 * Create a panel with direction buttons and info about the game.
	 * 
	 * @param game
	 *            the game to create the buttons for.
	 * 
	 */
	private static JPanel createRightPanel(final PacManGame game,
			ControlPlayer controlPlayer) {

		JPanel rightPanel = new JPanel();
		BoxLayout layout = new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS);
		rightPanel.setLayout(layout);

		JPanel allButtonPanel = new JPanel();
		// allButtonPanel.setLayout(new GridLayout(4, 1));

		JButton rightButton = getRightButton(controlPlayer);
		JButton leftButton = getLeftButton(controlPlayer);
		JButton downButton = getDownButton(controlPlayer);
		JButton upButton = getUpButton(controlPlayer);

		JPanel directionButtonPanel = new JPanel();
		directionButtonPanel.setLayout(new GridLayout(3, 3));
		directionButtonPanel.add(new JLabel(""));
		directionButtonPanel.add(upButton);
		directionButtonPanel.add(new JLabel(""));
		directionButtonPanel.add(leftButton);
		directionButtonPanel.add(new JLabel(""));
		directionButtonPanel.add(rightButton);
		directionButtonPanel.add(new JLabel(""));
		directionButtonPanel.add(downButton);
		directionButtonPanel.add(new JLabel(""));

		allButtonPanel.add(directionButtonPanel);
		rightPanel.add(allButtonPanel);
		
		JLabel pointsLabel = new JLabel();
		pointsLabel.setText("Punkte: " + game.getScore());
		new PointsListener(pointsLabel, game);
		rightPanel.add(pointsLabel);
		
		JLabel livesLabel = new JLabel();
		livesLabel.setText("Leben: " + game.getLives());
		new LivesListener(livesLabel, game);
		rightPanel.add(livesLabel);
		
		JLabel PPTLabel = new JLabel();
		PPTLabel.setText("");
		new PPTListener(PPTLabel, game);
		rightPanel.add(PPTLabel);

		return rightPanel;
	}

	
	private static class PointsListener extends GameObserverAdpater {

		private final JLabel pointsLabel;
		private final PacManGame game;

		public PointsListener(JLabel pointsLabel, PacManGame game) {
			this.pointsLabel = pointsLabel;
			this.game = game;
			game.addObserver(this);
		}

		@Override
		public void stepDone() {
			pointsLabel.setText("Punkte: " + game.getScore());
		}
	}

	private static class PPTListener extends GameObserverAdpater {

		private final JLabel PPTLabel;
		private final PacManGame game;

		public PPTListener(JLabel PPTLabel, PacManGame game) {
			this.PPTLabel = PPTLabel;
			this.game = game;
			game.addObserver(this);
		}

		@Override
		public void stepDone() {
			if (game.isPowerPelletMode()) 
				PPTLabel.setText("Kraftpillenzeit: " + game.getPPT()/1000);
			else
				PPTLabel.setText("");
		}

	}
	
	private static class LivesListener extends GameObserverAdpater {

		private final JLabel livesLabel;
		private final PacManGame game;

		public LivesListener(JLabel livesLabel, PacManGame game) {
			this.livesLabel = livesLabel;
			this.game = game;
			game.addObserver(this);
		}

		@Override
		public void stepDone() {
			livesLabel.setText("Leben: " + game.getLives());
		}

	}
	
	
	private static JButton getRightButton(final ControlPlayer player) {
		JButton button = new JButton("right");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				player.setDirection(RIGHT);
			}

		});
		return button;
	}

	private static JButton getLeftButton(final ControlPlayer player) {
		JButton button = new JButton("left");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				player.setDirection(LEFT);
			}

		});
		return button;
	}

	private static JButton getDownButton(final ControlPlayer player) {
		JButton button = new JButton("down");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				player.setDirection(DOWN);
			}

		});
		return button;
	}

	private static JButton getUpButton(final ControlPlayer player) {
		JButton button = new JButton("up");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.setDirection(UP);
			}

		});
		return button;
	}
	

	/**
	 * Adds KeyListener to all sub components of the given Container.
	 * 
	 * @param listener
	 *            the listener to add.
	 * @param parent
	 *            the parent container.
	 */
	private static void addKeyListenerToAllComponents(KeyListener listener,
			Container parent) {
		for (Component cmp : parent.getComponents()) {
			if (cmp instanceof Container)
				addKeyListenerToAllComponents(listener, (Container) cmp);
			cmp.addKeyListener(listener);
		}
	}

}
