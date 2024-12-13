package com.example.demo;

/**
 * The {@code Projectile} class represents a base class for all projectiles in the game.
 * It extends {@code ActiveActorDestructible} and provides a foundation for defining
 * the behavior of projectiles, including their initialization and collision logic.
 *
 * <p>
 * Subclasses must implement the {@code updatePosition()} method to define the specific
 * movement behavior of the projectile.
 * </p>
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a {@code Projectile} instance with the specified properties.
	 *
	 * @param imageName the file name of the projectile's image
	 * @param imageHeight the height of the projectile's image, with the width adjusted
	 *                    to preserve the aspect ratio
	 * @param initialXPos the initial X position of the projectile
	 * @param initialYPos the initial Y position of the projectile
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles the projectile's reaction to taking damage, which destroys the projectile.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile. This method must be implemented
	 * by subclasses to define the specific movement behavior of the projectile.
	 */
	@Override
	public abstract void updatePosition();
}
