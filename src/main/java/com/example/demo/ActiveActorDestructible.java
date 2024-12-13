package com.example.demo;

/**
 * The {@code ActiveActorDestructible} class represents a destructible actor in the game.
 * It extends {@code ActiveActor} and implements the {@code Destructible} interface,
 * providing functionality for objects that can take damage and be destroyed.
 *
 * <p>
 * Subclasses of {@code ActiveActorDestructible} must implement the abstract methods
 * {@code updatePosition}, {@code updateActor}, and {@code takeDamage} to define
 * specific behaviors for movement, updates, and damage handling.
 * </p>
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/** Indicates whether the actor is destroyed. */
	private boolean isDestroyed;

	/**
	 * Constructs an {@code ActiveActorDestructible} with the specified image, size, and initial position.
	 *
	 * @param imageName the name of the image file (located in the {@code IMAGE_LOCATION} directory)
	 * @param imageHeight the height of the image, with the width adjusted to preserve the aspect ratio
	 * @param initialXPos the initial X position of the actor
	 * @param initialYPos the initial Y position of the actor
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor. Subclasses must provide an implementation
	 * for how the position changes over time.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the state of the actor. Subclasses must provide an implementation
	 * to define the actor's behavior during updates.
	 */
	public abstract void updateActor();

	/**
	 * Applies damage to the actor. Subclasses must implement this method to
	 * handle damage logic.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor, marking it as destroyed and triggering any relevant cleanup logic.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed state of the actor.
	 *
	 * @param isDestroyed {@code true} if the actor is destroyed, {@code false} otherwise
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Returns whether the actor is destroyed.
	 *
	 * @return {@code true} if the actor is destroyed, {@code false} otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}

