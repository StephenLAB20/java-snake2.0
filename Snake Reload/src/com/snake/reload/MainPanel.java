package com.snake.reload;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	private Image img;

	public MainPanel() {

		try {
			this.setLayout(null);
			img = ImageIO.read(getClass().getResource("/snakeIntro.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if (img != null) {
			g.drawImage(img, 0, 0, null);
		}
	}
}
