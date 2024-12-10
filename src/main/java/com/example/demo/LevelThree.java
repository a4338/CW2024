/* package com.example.demo;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final double REFLECTIVE_SHIELD_ACTIVATION_PROBABILITY = 0.1; // 10% chance
    // private boolean reflectiveShieldActive = false;

    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

  /*  @Override
    protected void updateGame() {
        super.updateGame();

        // Randomly activate reflective shield
        if (Math.random() < REFLECTIVE_SHIELD_ACTIVATION_PROBABILITY) {
            activateReflectiveShield();
        } else {
            deactivateReflectiveShield();
        }
    }

    private void activateReflectiveShield() {
        reflectiveShieldActive = true;
        System.out.println("Reflective shield activated!");
        getUser().setReflectiveShieldActive(true); // Enable the shield for the UserPlane
    }

    private void deactivateReflectiveShield() {
        reflectiveShieldActive = false;
        getUser().setReflectiveShieldActive(false); // Disable the shield for the UserPlane
    }
*/ /*
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (allEnemiesDefeated()) {
            goToNextLevel("com.example.demo.LevelFour");
        }
    }

    private boolean allEnemiesDefeated() {
        return getCurrentNumberOfEnemies() == 0; // Or your logic to check if level objectives are met
    }
}

*/


package com.example.demo;

public class LevelThree extends LevelParent{
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    // private static final String NEXT_LEVEL = "com.example.demo.LevelFour";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;

    //private LevelViewLevelThree levelView;
    private boolean levelTransitionInProgress = false; // Flag to ensure single execution

    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget() && !levelTransitionInProgress) {
            levelTransitionInProgress = true; // Set the flag to true to prevent reentry
            winGame();
        }
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }
    }
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }


}