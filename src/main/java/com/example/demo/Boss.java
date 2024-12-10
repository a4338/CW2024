package com.example.demo;

import java.util.*;

public class Boss extends FighterPlane {

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = 0.03;
	private static final double BOSS_SHIELD_PROBABILITY = 0.002;
	private static final double SHIELD_DEACTIVATION_PROBABILITY = 0.01;
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 100;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;

	private final List<Integer> movePattern;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;
	private boolean isShielded = false;
	private boolean shieldCooldown = false;
	private int shieldTimer = 0;
	private final LevelViewLevelTwo levelView;

	public Boss(LevelViewLevelTwo levelView) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.levelView = levelView;
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		initializeMovePattern();
	}

	@Override
	public void updateActor() {
		updatePosition();
		updateShield();

		if (levelView != null) {
			double bossX = getLayoutX() + getTranslateX();
			double bossY = getLayoutY() + getTranslateY();
			levelView.updateShieldPosition(bossX, bossY);
		}
	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	@Override
	public void takeDamage() {
		if (isShielded) {
			System.out.println("Damage blocked by shield.");
		} else {
			super.takeDamage();
			System.out.println("Boss took damage. Remaining health: " + getHealth());
		}
	}

	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			if (Math.random() < SHIELD_DEACTIVATION_PROBABILITY) {
				deactivateShield();
			}
		} else if (!shieldCooldown) {
			if (Math.random() < BOSS_SHIELD_PROBABILITY) {
				activateShield();
			}
		}

		if (shieldCooldown) {
			shieldTimer++;
			if (shieldTimer >= MAX_FRAMES_WITH_SAME_MOVE) {
				shieldCooldown = false;
				shieldTimer = 0;
			}
		}
	}

	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	private void activateShield() {
		isShielded = true;
		if (levelView != null) {
			levelView.showShield();
		}
		System.out.println("Shield activated.");
	}

	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		if (levelView != null) {
			levelView.hideShield();
		}
		System.out.println("Shield deactivated.");
	}
}