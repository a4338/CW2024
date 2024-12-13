package com.example.demo;

/**
 * The {@code FighterPlane} class represents a destructible fighter plane in the game.
 * It extends {@code ActiveActorDestructible} and provides additional functionality
 * for firing projectiles and managing health.
 *
 * <p>
 * Subclasses must define the specific behavior for firing projectiles by
 * implementing the {@code fireProjectile} method.
 * </p>
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	/** The health of the fighter plane. */
	private int health;

	/**
	 * Constructs a {@code FighterPlane} with the specified image, size, initial position, and health.
	 *
	 * @param imageName the name of the image file for the plane (located in the {@code IMAGE_LOCATION} directory)
	 * @param imageHeight the height of the plane's image, with the width adjusted to preserve the aspect ratio
	 * @param initialXPos the initial X position of the plane
	 * @param initialYPos the initial Y position of the plane
	 * @param health the initial health of the plane
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane.
	 *
	 * <p>
	 * Subclasses must implement this method to define the specific behavior and type of projectile fired.
	 * </p>
	 *
	 * @return an {@code ActiveActorDestructible} representing the projectile
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Applies damage to the fighter plane, reducing its health by 1.
	 *
	 * <p>
	 * If the plane's health reaches zero, it is destroyed.
	 * </p>
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X position for a projectile relative to the fighter plane's position.
	 *
	 * @param xPositionOffset the offset to add to the plane's current X position
	 * @return the X position for the projectile
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y position for a projectile relative to the fighter plane's position.
	 *
	 * @param yPositionOffset the offset to add to the plane's current Y position
	 * @return the Y position for the projectile
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the fighter plane's health is zero.
	 *
	 * @return {@code true} if the health is zero, {@code false} otherwise
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Returns the current health of the fighter plane.
	 *
	 * @return the health of the plane
	 */
	public int getHealth() {
		return health;
	}
}
