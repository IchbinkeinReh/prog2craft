package view;


import static enums.FieldType.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import enums.*;

/**
 * A swing component that displays the PacMan game.
 */
@SuppressWarnings("serial")

public class Prog2CraftComponent extends JPanel {

	private GameInfo gameInfo;

	static final int FIELDSIZE = 20;
	static final int FRAME = 4;// 5 animation per step
	
	FieldInfo[] ghostpos;
	FieldInfo pacmanpos;
	Direction[] ghostdir;
	Direction pacmandir;
	
	private int frame = 0;

	static final Map<FieldType, Color> colorMap = new HashMap<FieldType, Color>();

	static {
		colorMap.put(WALL, Color.BLUE.darker());
		colorMap.put(POWER_PELLET, Color.BLACK);
		colorMap.put(FREE, Color.BLACK);
		colorMap.put(GHOST_START, Color.MAGENTA.darker().darker().darker());
		colorMap.put(PACMAN_START, Color.BLUE.darker().darker());

	}

	/**
	 * Creates a new PacManComponent for the given game.
	 * 
	 * @param gameInfo
	 *            information about the game.
	 */
	public Prog2CraftComponent(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		BoardInfo b = gameInfo.getBoardInfo();
		setPreferredSize(new Dimension(FIELDSIZE * b.getNumberOfColumns(),
				FIELDSIZE * b.getNumberOfRows()));
		
		List<FieldInfo> ghostpostemp = this.gameInfo.getGhostPositions();
		pacmanpos = this.gameInfo.getPacManPosition();
		pacmandir = Direction.LEFT;
		ghostpos = new FieldInfo[ghostpostemp.size()];
		ghostdir = new Direction[ghostpostemp.size()];
		for (int i = 0; i < ghostpostemp.size(); i++)
			{
			ghostpos[i] = ghostpostemp.get(i);
			ghostdir[i] = Direction.LEFT;
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (gameInfo.isGameOver()) {
			paintGameOver(g, gameInfo);
		} else {
			paintBoard(g, gameInfo);
		}
	}

	/**
	 * Routine that paints the board on given graphics object.
	 * 
	 * @param g
	 *            the graphics object to use for painting.
	 * @param board
	 *            the board to paint.
	 */
	private void paintBoard(Graphics g, GameInfo gameInfo) {
		BoardInfo boardInfo = gameInfo.getBoardInfo();
		
		List<FieldInfo> ghostpostemp = this.gameInfo.getGhostPositions();
		
		for (int row = 0; row < boardInfo.getNumberOfRows(); row++) {
			for (int column = 0; column < boardInfo.getNumberOfColumns(); column++) {
				FieldInfo field = boardInfo.getFieldInfo(column, row);
				
				Toolkit kit = Toolkit.getDefaultToolkit();	
				
				switch (field.getType())
				{
				case WALL:
					g.drawImage(kit.getImage("Data/Mauer.jpg"), column * FIELDSIZE, row * FIELDSIZE, column * FIELDSIZE + FIELDSIZE, row * FIELDSIZE + FIELDSIZE, 0, 0, FIELDSIZE, FIELDSIZE, this);
				break;
				default:
					g.setColor(colorMap.get(field.getType()));
				}
				
				if (field.getActorType() == null && field.getType() != FieldType.WALL)
				{
					g.fillRect(column * FIELDSIZE, row * FIELDSIZE, FIELDSIZE,
							FIELDSIZE);
					
					g.setColor(Color.WHITE);
					if (field.hasPowerPellet()) {
						drawDot(g, column, row, 0.6);
					}
					if (field.hasPacDot()) {
						drawRec(g, column, row, 0.2);
					}
					if (field.getExtraItem() != null)
						g.drawImage(kit.getImage("Data/"+field.getExtraItem().name()+".jpg"), column * FIELDSIZE, row * FIELDSIZE, column * FIELDSIZE + FIELDSIZE, row * FIELDSIZE + FIELDSIZE, 0, 0, FIELDSIZE, FIELDSIZE, this);
				}
				
				if (field.getActorType() != null)
				{
				int c = boardInfo.getNumberOfColumns();
				int r = boardInfo.getNumberOfRows();
					switch (field.getActorType())
					{
					case PACMAN:	
							if (pacmanpos.hashCode() == boardInfo.getFieldInfo( (field.getX()-1 + c) % c, field.getY()).hashCode())
								pacmandir = Direction.RIGHT;
							else if (pacmanpos.hashCode() == boardInfo.getFieldInfo( (field.getX()+1 + c) % c, field.getY()).hashCode())
								pacmandir = Direction.LEFT;
							else if (pacmanpos.hashCode() == boardInfo.getFieldInfo( field.getX(), (field.getY()-1 + r) % r).hashCode())
								pacmandir = Direction.DOWN;
							else if (pacmanpos.hashCode() == boardInfo.getFieldInfo( field.getX(), (field.getY()+1 + r) % r).hashCode())
								pacmandir = Direction.UP;
							pacmanpos = field;

							g.drawImage(kit.getImage("Data/pacman"+pacmandir.name()+".jpg"), column * FIELDSIZE, row * FIELDSIZE, column * FIELDSIZE + FIELDSIZE, row * FIELDSIZE + FIELDSIZE, FIELDSIZE * (frame % FRAME), 0, FIELDSIZE + FIELDSIZE * (frame % FRAME), FIELDSIZE, this);
							break;
						
					case GHOST:
						int i = field.getGhostInfo().getCharacter().ordinal();
						FieldInfo fi = ghostpos[i];
						if (fi.hashCode() == boardInfo.getFieldInfo( (field.getX()-1 + c) % c, field.getY()).hashCode())
							ghostdir[i] = Direction.RIGHT;
						else if (fi.hashCode() == boardInfo.getFieldInfo( (field.getX()+1 + c) % c, field.getY()).hashCode())
							ghostdir[i] = Direction.LEFT;
						else if (fi.hashCode() == boardInfo.getFieldInfo( field.getX(), (field.getY()-1 + r) % r).hashCode())
							ghostdir[i] = Direction.DOWN;
						else if (fi.hashCode() == boardInfo.getFieldInfo( field.getX(), (field.getY()+1 + r) % r).hashCode())
							ghostdir[i] = Direction.UP;
						
						if (this.gameInfo.isPowerPelletMode())
							g.drawImage(kit.getImage("Data/BLUE " + ghostdir[i].name() + ".jpg"), column * FIELDSIZE, row * FIELDSIZE, column * FIELDSIZE + FIELDSIZE, row * FIELDSIZE + FIELDSIZE, FIELDSIZE * (frame % 2), 0, FIELDSIZE + FIELDSIZE * (frame % 2), FIELDSIZE, this);
						else
							g.drawImage(kit.getImage("Data/" + field.getGhostInfo().getCharacter().name() + " " + ghostdir[i].name() + ".jpg"), column * FIELDSIZE, row * FIELDSIZE, column * FIELDSIZE + FIELDSIZE, row * FIELDSIZE + FIELDSIZE, FIELDSIZE * (frame % 2), 0, FIELDSIZE + FIELDSIZE * (frame % 2), FIELDSIZE, this);
						break;
					}
					
					
					
				}

			}
		}
		frame++;
		for (int q = 0; q < ghostpostemp.size(); q++)
			ghostpos[q] = ghostpostemp.get(q);
		
	}

	/**
	 * Draws a dot with given scale on given position.
	 * 
	 * @param g
	 *            the graphics object to use for painting.
	 * @param column
	 *            the column of the dot.
	 * @param row
	 *            the row of the dot.
	 * @param scale
	 *            the scale of the dot in the interval [0,1]
	 */
	private void drawDot(Graphics g, int column, int row, double scale) {
		g.fillOval((int) (column * FIELDSIZE + (1 - scale) / 2 * FIELDSIZE),
				(int) (row * FIELDSIZE + (1 - scale) / 2 * FIELDSIZE),
				(int) (FIELDSIZE * scale), (int) (FIELDSIZE * scale));
	}
	
	private void drawRec(Graphics g, int column, int row, double scale) {
		g.fillRect((int) (column * FIELDSIZE + (1 - scale) / 2 * FIELDSIZE),
				(int) (row * FIELDSIZE + (1 - scale) / 2 * FIELDSIZE),
				(int) (FIELDSIZE * scale), (int) (FIELDSIZE * scale));
	}

	/**
	 * Routine that paints a game over screen on given graphics object.
	 * 
	 * @param g
	 *            the graphics object to use for painting.
	 * @param board
	 *            the board to paint the game over message for.
	 */
	private void paintGameOver(Graphics g, GameInfo gameInfo) {
		g.setColor(Color.RED);
		BoardInfo board = gameInfo.getBoardInfo();
		g.fillRect(0, 0, board.getNumberOfColumns() * FIELDSIZE, board
				.getNumberOfRows()
				* FIELDSIZE);
		g.setColor(Color.WHITE);
		int x = board.getNumberOfColumns() * FIELDSIZE / 2 - 40;
		int y = board.getNumberOfRows() * FIELDSIZE / 2;
		g.drawString("GAME OVER", x, y);
		x += FIELDSIZE;
		y += FIELDSIZE;
		g.drawString("Score: " + gameInfo.getScore(), x, y);
	}

}
