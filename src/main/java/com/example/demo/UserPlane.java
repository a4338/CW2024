package com.example.demo;
/**
 * The {@code UserPlane} class represents the player's plane in the game.
 * It extends {@code FighterPlane} and includes functionality for movement, firing projectiles,
 * and tracking the number of enemy kills.
 *
 * <p>
 * The player can move the plane both vertically and horizontally within predefined boundaries
 * and fire projectiles to destroy enemies.
 * </p>
 */
public class UserPlane extends FighterPlane {

	/** The file name of the user's plane image. */
	private static final String IMAGE_NAME = "userplane.png";

	/** The upper boundary for vertical movement. */
	private static final double Y_UPPER_BOUND = -40;

	/** The lower boundary for vertical movement. */
	private static final double Y_LOWER_BOUND = 600.0;

	/** The initial X position of the plane. */
	private static final double INITIAL_X_POSITION = 5.0;

	/** The initial Y position of the plane. */
	private static final double INITIAL_Y_POSITION = 300.0;

	/** The height of the plane's image, with the width adjusted to preserve the aspect ratio. */
	private static final int IMAGE_HEIGHT = 200;

	/** The vertical velocity of the plane during movement. */
	private static final int VERTICAL_VELOCITY = 8;

	/** The horizontal velocity of the plane during movement. */
	private static final int HORIZONTAL_VELOCITY = 8;

	/** The X position offset for firing projectiles. */
	private static final int PROJECTILE_X_POSITION = 110;

	/** The Y position offset for firing projectiles. */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

	/** The left boundary for horizontal movement. */
	private static final double X_LEFT_BOUND = -40.0;

	/** The right boundary for horizontal movement. */
	private static final double X_RIGHT_BOUND = 600.0;

	/** Multiplier for vertical movement velocity, based on user input. */
	private int verticalVelocityMultiplier;

	/** Multiplier for horizontal movement velocity, based on user input. */
	private int horizontalVelocityMultiplier;

	/** Tracks the number of kills made by the user. */
	private int numberOfKills;

	/**
	 * Constructs a {@code UserPlane} with the specified initial health.
	 *
	 * @param initialHealth the initial health of the user's plane
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the position of the plane based on the velocity multipliers
	 * for vertical and horizontal movement. Ensures the plane stays within the defined boundaries.
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			double initialTranslateX = getTranslateX();

			moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);

			double newVerticalPosition = getLayoutY() + getTranslateY();
			double newHorizontalPosition = getLayoutX() + getTranslateX();

			if (newVerticalPosition < Y_UPPER_BOUND || newVerticalPosition > Y_LOWER_BOUND) {
				setTranslateY(initialTranslateY);
			}

			if (newHorizontalPosition < X_LEFT_BOUND || newHorizontalPosition > X_RIGHT_BOUND) {
				setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Updates the state of the user plane, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user's plane.
	 *
	 * @return a {@code UserProjectile} object representing the fired projectile
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		AudioManager.playSoundEffect("/com/example/demo/audio/userShoot.wav"); // Play shooting sound
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the plane is currently moving.
	 *
	 * @return {@code true} if the plane is moving, {@code false} otherwise
	 */
	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}

	/**
	 * Moves the plane upwards by setting the vertical velocity multiplier to -1.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the plane downwards by setting the vertical velocity multiplier to 1.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the plane forward (to the right) by setting the horizontal velocity multiplier to 1.
	 */
	public void moveForward() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Moves the plane backward (to the left) by setting the horizontal velocity multiplier to -1.
	 */
	public void moveBackward() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Stops the plane's movement by resetting both velocity multipliers to 0.
	 */
	public void stop() {
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Returns the number of kills made by the user.
	 *
	 * @return the number of kills
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the user's kill count by 1.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
}


