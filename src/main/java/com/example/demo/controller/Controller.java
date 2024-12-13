package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

/**
 * The {@code Controller} class is responsible for managing the game stages and transitioning
 * between levels in the game. It observes changes in the game's state and handles level transitions
 * dynamically using reflection.
 */
public class Controller implements Observer {

	/** The fully qualified class name for Level One. */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";

	/** The main {@code Stage} of the application. */
	private final Stage stage;

	/**
	 * Constructs a new {@code Controller} with the specified stage.
	 *
	 * @param stage the primary stage of the application
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by displaying the main stage and transitioning to the first level.
	 *
	 * @throws ClassNotFoundException if the level class cannot be found
	 * @throws NoSuchMethodException if the level class does not have the required constructor
	 * @throws SecurityException if there is a security violation during reflection
	 * @throws InstantiationException if the level class cannot be instantiated
	 * @throws IllegalAccessException if the constructor is inaccessible
	 * @throws IllegalArgumentException if invalid arguments are passed to the constructor
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Transitions to a specified game level dynamically using reflection.
	 *
	 * @param className the fully qualified class name of the level to transition to
	 * @throws ClassNotFoundException if the specified level class cannot be found
	 * @throws NoSuchMethodException if the specified level class does not have the required constructor
	 * @throws SecurityException if there is a security violation during reflection
	 * @throws InstantiationException if the specified level class cannot be instantiated
	 * @throws IllegalAccessException if the constructor is inaccessible
	 * @throws IllegalArgumentException if invalid arguments are passed to the constructor
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
		myLevel.addObserver(this);
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();
	}

	/**
	 * Handles updates from observable objects, transitioning to the next level
	 * if a level change is triggered.
	 *
	 * @param observable the observable object notifying the observer
	 * @param arg an argument passed by the observable object, typically the class name of the next level
	 */
	@Override
	public void update(Observable observable, Object arg) {
		try {
			goToLevel((String) arg);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}
}

