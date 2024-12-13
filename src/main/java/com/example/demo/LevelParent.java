package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 40;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private LevelView levelView;
	private boolean isPaused = false;
	private boolean isMuted = false;
	private ImageView pauseOverlay;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		initializePauseOverlay();
		friendlyUnits.add(user);
	}
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	// Pause and Mute Methods
	private void togglePause(){
		if (isPaused) {
			resumeGame();
		} else {
			pauseGame();
		}
	}


	private void pauseGame() {
		isPaused = true;
		timeline.pause(); // Stop the game loop
		AudioManager.pauseBackgroundMusic(); // Pause background music
		pauseOverlay.setVisible(true);
		System.out.println("Pause overlay made visible");
	}

	private void resumeGame() {
		isPaused = false;
		timeline.play(); // Resume the game loop
		AudioManager.resumeBackgroundMusic(); // Resume background music
		pauseOverlay.setVisible(false); // Hide pause overlay
		System.out.println("Pause overlay hidden");
	}
	private void initializePauseOverlay() {
		pauseOverlay = new ImageView(new Image(getClass().getResource("/com/example/demo/images/pauseOverlay.png").toExternalForm()));
		pauseOverlay.setFitWidth(300); // Set the width of the overlay image
		pauseOverlay.setFitHeight(300); // Set the height of the overlay image
		pauseOverlay.setVisible(false); // Initially invisible
		pauseOverlay.setLayoutX((screenWidth - pauseOverlay.getFitWidth()) / 2); // Center horizontally
		pauseOverlay.setLayoutY((screenHeight - pauseOverlay.getFitHeight()) / 2); // Center vertically
		root.getChildren().add(pauseOverlay); // Add it to the scene graph
	}
	private void toggleMute() {
		if (isMuted) {
			AudioManager.unmute();
			isMuted = false;
			System.out.println("Sound unmuted");
		} else {
			AudioManager.mute();
			isMuted = true;
			System.out.println("Sound muted");
		}
	}


	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();


	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}


	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.P) { // Press "P" to toggle pause
					togglePause();
				} else if (!isPaused) { // Allow other inputs only if not paused
					if (kc == KeyCode.UP) user.moveUp();
					if (kc == KeyCode.DOWN) user.moveDown();
					if (kc == KeyCode.RIGHT) user.moveForward();
					if (kc == KeyCode.LEFT) user.moveBackward();
					if (kc == KeyCode.SPACE) fireProjectile();
					if (kc == KeyCode.M) toggleMute();  // Mute key
			}
		}});

		background.setOnKeyReleased(e -> {
			if (!isPaused) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
				if (kc == KeyCode.RIGHT || kc == KeyCode.LEFT) user.stop();
			}
		});

		root.getChildren().add(background);
	}

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}


	private void handleCollisions(List<ActiveActorDestructible> actors1,
								  List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();

				}
			}
		}
	}


	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
				System.out.println("User took damage. Remaining health: " + user.getHealth());
			}
		}
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}


	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		if(enemy instanceof EnemyProjectile){
			return enemy.getX() <= 0;
		}
		return false;
	}


	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
		AudioManager.pauseBackgroundMusic(); // Pause background music

		// Play win sound, then game over sound, then resume background music
		AudioManager.playSoundEffect("/com/example/demo/audio/userWin.wav", () -> {
			AudioManager.playSoundEffect("/com/example/demo/audio/gameOver.wav", () -> {
				AudioManager.resumeBackgroundMusic(); // Resume background music
			});
		});

		// Add a 3-second delay before transitioning to the end screen
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(event -> goToEndScreen(true)); // Transition to end screen after delay
		pause.play();
	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
		AudioManager.pauseBackgroundMusic(); // Pause background music

		// Play lose sound, then game over sound, then resume background music
		AudioManager.playSoundEffect("/com/example/demo/audio/userDie.wav", () -> {
			AudioManager.playSoundEffect("/com/example/demo/audio/gameOver.wav", () -> {
				AudioManager.resumeBackgroundMusic(); // Resume background music
			});
		});
		goToEndScreen(false); // Pass false to indicate a loss
	}
	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}
	private void goToEndScreen(boolean playerWon) {
		try {
			// Load the end screen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/view/endScreen.fxml"));
			Pane endScreenRoot = loader.load();

			// Optionally, customize the screen for win/loss
			if (playerWon) {
				System.out.println("Player won!");
			} else {
				System.out.println("Player lost!");
			}

			// Get the current stage and set the new scene
			Stage stage = (Stage) root.getScene().getWindow();
			Scene endScreenScene = new Scene(endScreenRoot, screenWidth, screenHeight);
			stage.setScene(endScreenScene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}