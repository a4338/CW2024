package com.example.demo;

/**
 * The {@code EnemyPlane} class represents a basic enemy plane in the game.
 * It extends the {@code FighterPlane} class, implementing behavior for movement,
 * firing projectiles, and interacting with the game environment.
 */
public class EnemyPlane extends FighterPlane {

	/** The file name of the enemy plane's image. */
	private static final String IMAGE_NAME = "bowser.png";

	/** The height of the enemy plane's image, with the width adjusted to preserve the aspect ratio. */
	private static final int IMAGE_HEIGHT = 150;

	/** The horizontal velocity of the enemy plane, moving leftwards. */
	private static final int HORIZONTAL_VELOCITY = -6;

	/** The X offset for the projectile's initial position relative to the enemy plane. */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

	/** The Y offset for the projectile's initial position relative to the enemy plane. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

	/** The initial health of the enemy plane. */
	private static final int INITIAL_HEALTH = 1;

	/** The probability of the enemy plane firing a projectile on any given frame. */
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructs an {@code EnemyPlane} at the specified initial position.
	 *
	 * @param initialXPos the initial X position of the enemy plane
	 * @param initialYPos the initial Y position of the enemy plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally
	 * at the predefined velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane, if the current frame meets the fire probability.
	 *
	 * @return an {@code EnemyProjectile} instance if the enemy fires, otherwise {@code null}
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane. In this implementation, it only updates its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}

