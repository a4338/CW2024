package com.example.demo;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 10;
	private static final int HORIZONTAL_VELOCITY = 10;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

	// New constants for horizontal movement
	private static final double X_LEFT_BOUND = -40.0;
	private static final double X_RIGHT_BOUND = 600.0;
	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}
	
	@Override
	public void updatePosition() {
		if (isMoving()) {
			// Store the initial translation values
			double initialTranslateY = getTranslateY();
			double initialTranslateX = getTranslateX();

			// Move vertically and horizontally
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);

			// Calculate new positions
			double newVerticalPosition = getLayoutY() + getTranslateY();
			double newHorizontalPosition = getLayoutX() + getTranslateX();

			// Check boundaries for vertical movement
			if (newVerticalPosition < Y_UPPER_BOUND || newVerticalPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}

			// Check boundaries for horizontal movement
			if (newHorizontalPosition < X_LEFT_BOUND || newHorizontalPosition > X_RIGHT_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}


	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	public void moveForward() {
		horizontalVelocityMultiplier = 1;
	}

	public void moveBackward() {
		horizontalVelocityMultiplier = -1;
	}

	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

}
