package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The {@code AudioManager} class manages audio playback in the game.
 * It provides methods to play background music, sound effects, and control
 * playback options such as muting, pausing, and resuming.
 *
 * <p>
 * This class uses static methods and properties to manage a single background
 * music player instance and multiple sound effect players.
 * </p>
 */
public class AudioManager {

    /** The {@code MediaPlayer} instance for background music playback. */
    private static MediaPlayer backgroundMusicPlayer;

    /** Indicates whether audio is muted. */
    private static boolean isMuted = false;

    /**
     * Starts playing the background music from the specified audio file.
     *
     * <p>
     * The background music loops indefinitely until stopped or paused.
     * </p>
     *
     * @param audioFilePath the file path of the background music
     */
    public static void startBackgroundMusic(String audioFilePath) {
        if (backgroundMusicPlayer == null) {
            backgroundMusicPlayer = new MediaPlayer(new Media(AudioManager.class.getResource(audioFilePath).toExternalForm()));
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            if (isMuted) {
                backgroundMusicPlayer.setMute(true);
            }
            backgroundMusicPlayer.play();
        }
    }

    /**
     * Plays a sound effect from the specified audio file.
     *
     * @param audioFilePath the file path of the sound effect
     */
    public static void playSoundEffect(String audioFilePath) {
        try {
            Media media = new Media(AudioManager.class.getResource(audioFilePath).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading sound effect: " + e.getMessage());
        }
    }

    /**
     * Plays a sound effect from the specified audio file with a callback
     * that runs when the sound effect ends.
     *
     * @param audioFilePath the file path of the sound effect
     * @param onEnd a {@code Runnable} to execute when the sound effect ends
     */
    public static void playSoundEffect(String audioFilePath, Runnable onEnd) {
        try {
            Media media = new Media(AudioManager.class.getResource(audioFilePath).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(() -> {
                if (onEnd != null) {
                    onEnd.run();
                }
            });
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading sound effect: " + e.getMessage());
        }
    }

    /**
     * Mutes all audio, including background music and sound effects.
     */
    public static void mute() {
        isMuted = true;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(true);
        }
    }

    /**
     * Unmutes all audio, including background music and sound effects.
     */
    public static void unmute() {
        isMuted = false;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(false);
        }
    }

    /**
     * Pauses the background music playback.
     */
    public static void pauseBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.pause();
        }
    }

    /**
     * Resumes the background music playback.
     */
    public static void resumeBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.play();
        }
    }

    /**
     * Stops the background music playback and releases the {@code MediaPlayer} instance.
     */
    public static void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
            backgroundMusicPlayer = null;
        }
    }
}