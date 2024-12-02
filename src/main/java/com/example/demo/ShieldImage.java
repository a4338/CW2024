package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {
<<<<<<< HEAD

	//o,age
=======
	
>>>>>>> 93bd4342adfab2e121d8d38b36adbfceae3fed89
	private static final String IMAGE_NAME = "/images/shield.png";
	private static final int SHIELD_SIZE = 200;
	
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		//this.setImage(new Image(IMAGE_NAME));
<<<<<<< HEAD
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.png").toExternalForm()));
=======
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.jpg").toExternalForm()));
>>>>>>> 93bd4342adfab2e121d8d38b36adbfceae3fed89
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	public void showShield() {
		this.setVisible(true);
	}
	
	public void hideShield() {
		this.setVisible(false);
	}

}
