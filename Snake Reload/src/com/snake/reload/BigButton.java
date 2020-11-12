package com.snake.reload;

import java.awt.Font;

import javax.swing.JLabel;

public class BigButton extends JLabel {
	
	private SoundLab buttonSound;
	
	BigButton(String text) {
		
		this.setText(text);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font("Arial", Font.BOLD, 32));
		this.setOpaque(true);
		
		this.buttonSound = new SoundLab(GameParams.BUTTON_SOUND, false);
	}
	
	public void setStatic() {
		
		this.setBackground(GameParams.MIDDLE_GREEN);
		this.setForeground(GameParams.LIGHT_GREEN);
	}
	
	public void setHover() {
		
		this.setBackground(GameParams.LIGHT_GREEN);
		this.setForeground(GameParams.MIDDLE_GREEN);
		this.buttonSound.playSound();
	}
}