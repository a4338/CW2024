package com.example.demo;

/**
 * The {@code EnemyProjectile} class represents a projectile fired by an enemy plane.
 * It extends the {@code Projectile} class and defines the behavior for the projectile's movement.
 */
public class EnemyProjectile extends Projectile {

	/** The file name of the projectile's image. */
	private static final String IMAGE_NAME = "enemyFire.png";

	/** The height of the projectile's image, with the width adjusted to preserve the aspect ratio. */
	private static final int IMAGE_HEIGHT = 50;

	/** The horizontal velocity of the projectile, moving leftwards. */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an {@code EnemyProjectile} at the specified initial position.
	 *
	 * @param initialXPos the initial X position of the projectile
	 * @param initialYPos the initial Y position of the projectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally
	 * at the predefined velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the projectile. In this implementation, it only
	 * updates its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
