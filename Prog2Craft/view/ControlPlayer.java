package view;


import javax.swing.JComponent;


import enums.Direction;
import game.Prog2CraftGame;

/**
 * An ActorController that allows to move an actor in the given direction that
 * is set via setDirection. This ActorController can be used to control Pac-Man
 * by a user.
 * 
 * Additionally, it has references to a {@link PacManGame} and a
 * {@link JComponent} such that it can execute a step on the Pac-Man game and
 * update the component.
 */

public class ControlPlayer {

	/**
	 * The direction the actor should move.
	 */
	private Direction direction;

	/**
	 * The Pac-Man game.
	 */
	@SuppressWarnings("unused")
	private Prog2CraftGame game;

	/**
	 * The component to update after a step was made.
	 */
	@SuppressWarnings("unused")
	private JComponent component;
	

	public Direction getMove() {
		return this.direction;
	}

	/**
	 * Sets the direction for the next move, takes out a step in the game, and
	 * updates the component.
	 * 
	 * @param direction
	 *            the next direction for the move.
	 */
	public void setDirection(Direction direction) {
		if (direction == null) throw new IllegalArgumentException("pewpew");
		this.direction = direction;
	}

	/**
	 * Sets the game.
	 * 
	 * @param game
	 *            the game to set.
	 */
	public void setGame(Prog2CraftGame game) {
		if (game == null) throw new IllegalArgumentException("pewpew2");
		this.game = game;
	}

	/**
	 * Sets the component.
	 * 
	 * @param component
	 *            the component to set.
	 */
	public void setComponent(JComponent component) {
		if (component == null) throw new IllegalArgumentException("pewpew3");
		this.component = component;
	}

}
