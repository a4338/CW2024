package com.example.demo;

import java.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code Boss} class represents a boss enemy in the game.
 * It extends {@code FighterPlane} and includes unique behaviors such as movement patterns,
 * firing projectiles, and activating/deactivating a shield.
 *
 * <p>
 * The boss has a predefined move pattern, randomized shield activation, and a projectile-firing mechanism.
 * The class also interacts with the level's view to update shield position and visibility.
 * </p>
 */
public class Boss extends FighterPlane {

	/** The image file name for the boss. */
	private static final String IMAGE_NAME = "bossThwomp.png";

	/** Initial X position of the boss. */
	private static final double INITIAL_X_POSITION = 1000.0;

	/** Initial Y position of the boss. */
	private static final double INITIAL_Y_POSITION = 400;

	/** Offset for the projectile's Y position. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/** The probability of the boss firing a projectile on any given frame. */
	private static final double BOSS_FIRE_RATE = 0.03;

	/** The probability of the boss activating its shield. */
	private static final double BOSS_SHIELD_PROBABILITY = 0.002;

	/** The probability of the boss deactivating its shield. */
	private static final double SHIELD_DEACTIVATION_PROBABILITY = 0.01;

	/** The height of the boss's image. */
	private static final int IMAGE_HEIGHT = 300;

	/** The vertical velocity of the boss during movement. */
	private static final int VERTICAL_VELOCITY = 8;

	/** The initial health of the boss. */
	private static final int HEALTH = 100;

	/** The number of times a move is repeated in a single cycle. */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/** A zero value used for stationary moves in the movement pattern. */
	private static final int ZERO = 0;

	/** The maximum number of frames the boss can move in the same direction. */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/** The upper bound for the boss's Y position. */
	private static final int Y_POSITION_UPPER_BOUND = -100;

	/** The lower bound for the boss's Y position. */
	private static final int Y_POSITION_LOWER_BOUND = 475;

	/** The movement pattern of the boss. */
	private final List<Integer> movePattern;

	/** Tracks consecutive moves in the same direction. */
	private int consecutiveMovesInSameDirection;

	/** The index of the current move in the movement pattern. */
	private int indexOfCurrentMove;

	/** The number of frames the shield has been active. */
	private int framesWithShieldActivated;

	/** Indicates whether the shield is currently active. */
	private boolean isShielded = false;

	/** Indicates whether the shield is on cooldown. */
	private boolean shieldCooldown = false;

	/** Tracks the cooldown timer for the shield. */
	private int shieldTimer = 0;

	/** A reference to the level's view for updating shield position and visibility. */
	private final LevelViewLevelTwo levelView;

	/**
	 * Constructs a {@code Boss} with a predefined position, health, and level view reference.
	 *
	 * @param levelView the level view associated with the boss
	 */
	public Boss(LevelViewLevelTwo levelView) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.levelView = levelView;
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		initializeMovePattern();
	}

	/**
	 * Updates the boss's state, including position and shield behavior.
	 * Also updates the shield's position in the level view.
	 */
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

	/**
	 * Updates the boss's position based on its movement pattern.
	 * Ensures the boss does not move beyond the predefined bounds.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Fires a projectile from the boss, if the current frame meets the fire probability.
	 *
	 * @return a {@code BossProjectile} instance if the boss fires, otherwise {@code null}
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Applies damage to the boss. If the shield is active, the damage is blocked.
	 * Otherwise, the boss's health is reduced.
	 */
	@Override
	public void takeDamage() {
		if (isShielded) {
			System.out.println("Damage blocked by shield.");
		} else {
			super.takeDamage();
			System.out.println("Boss took damage. Remaining health: " + getHealth());
		}
	}

	/**
	 * Initializes the movement pattern for the boss by alternating between
	 * upward, downward, and stationary moves. The pattern is shuffled to add randomness.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield's state, including activation, deactivation, and cooldown logic.
	 */
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

	/**
	 * Gets the next move in the movement pattern and updates the move counters.
	 *
	 * @return the next vertical move value
	 */
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

	/**
	 * Determines whether the boss fires a projectile in the current frame.
	 *
	 * @return {@code true} if the boss fires, {@code false} otherwise
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Calculates the initial Y position of the projectile relative to the boss.
	 *
	 * @return the Y position for the projectile
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Activates the boss's shield and updates the level view.
	 */
	private void activateShield() {
		isShielded = true;
		if (levelView != null) {
			levelView.showShield();
		}
		System.out.println("Shield activated.");
	}

	/**
	 * Deactivates the boss's shield and updates the level view.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		if (levelView != null) {
			levelView.hideShield();
		}
		System.out.println("Shield deactivated.");
	}
}
