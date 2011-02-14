package view;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import enums.Direction;

/**
 * Key listener that allows to move Pac-Man.
 */

public class MyKeyListener extends KeyAdapter {

	/**
	 * The control player to delegate the moves.
	 */
	ControlPlayer c;

	/**
	 * Creates a new MyKeyListener with given control player.
	 * 
	 * @param c
	 *            the control player to delegate the moves.
	 */
	public MyKeyListener(ControlPlayer c) {
		if (c == null) throw new IllegalArgumentException("pewpew4");
		this.c = c;
	}

	/**
	 * Sets the direction for the control player when a corresponding key was
	 * pressed (VK_UP, VK_LEFT ...).
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			c.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			c.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			c.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			c.setDirection(Direction.RIGHT);
			break;	
		}
	}


}
