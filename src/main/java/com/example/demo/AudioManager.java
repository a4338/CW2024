package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
    private static MediaPlayer backgroundMusicPlayer;
    private static boolean isMuted = false;

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

    public static void playSoundEffect(String audioFilePath) {
        try {
            Media media = new Media(AudioManager.class.getResource(audioFilePath).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading sound effect: " + e.getMessage());
        }
    }

    // Overloaded method with a callback
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

    public static void mute() {
        isMuted = true;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(true);
        }
    }

    public static void unmute() {
        isMuted = false;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(false);
        }
    }

    public static void pauseBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.pause();
        }
    }

    public static void resumeBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.play();
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
            backgroundMusicPlayer = null;
        }
    }
}




/* public class AudioManager {
    private static MediaPlayer backgroundMusicPlayer;
    private static boolean isMuted = false;

    // Start the background music (only once)
    public static void startBackgroundMusic(String audioFilePath) {
        backgroundMusicPlayer = new MediaPlayer(new Media(AudioManager.class.getResource(audioFilePath).toExternalForm()));
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop background music
        if (isMuted) {
            backgroundMusicPlayer.setMute(true); // Respect mute state
        }
        backgroundMusicPlayer.play();
    }

    public static void mute() {
        isMuted = true;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(true);
        }
    }

    public static void unmute() {
        isMuted = false;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setMute(false);
        }
    }
    // Pause the background music without resetting its position
    public static void pauseBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.pause();
        }
    }

    // Resume the background music from where it was paused
    public static void resumeBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.play();
        }
    }

    // Stop the background music completely
    public static void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
            backgroundMusicPlayer = null; // Reset the player
        }
    }

    // Play sound effects (win/lose/game over sounds)
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

    // Overloaded method without Runnable
    public static void playSoundEffect(String audioFilePath) {
        playSoundEffect(audioFilePath, null);
    }
}
*/

/* package com.example.demo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
    private static MediaPlayer backgroundMusicPlayer;

    public static void playSequentialAudio(String firstAudioPath, String secondAudioPath, Runnable onComplete) {
        MediaPlayer firstPlayer = new MediaPlayer(new Media(AudioManager.class.getResource(firstAudioPath).toExternalForm()));
        MediaPlayer secondPlayer = new MediaPlayer(new Media(AudioManager.class.getResource(secondAudioPath).toExternalForm()));

        firstPlayer.setOnEndOfMedia(() -> {
            secondPlayer.play();
            secondPlayer.setOnEndOfMedia(() -> {
                if (onComplete != null) {
                    onComplete.run();
                }
            });
        });

        firstPlayer.play();
    }

    public static void resumeBackgroundMusic(String backgroundMusicPath) {
        backgroundMusicPlayer = new MediaPlayer(new Media(AudioManager.class.getResource(backgroundMusicPath).toExternalForm()));
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop background music
        backgroundMusicPlayer.play();
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    public static void playSoundEffect(String audioFilePath) {
        try {
            Media media = new Media(AudioManager.class.getResource(audioFilePath).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading sound effect: " + e.getMessage());
        }
    }

}

*/