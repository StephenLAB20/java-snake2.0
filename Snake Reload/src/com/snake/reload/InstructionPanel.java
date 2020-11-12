package com.snake.reload;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class InstructionPanel extends JPanel {

	private Image img;

	public InstructionPanel() {

		try {
			this.setLayout(null);
			img = ImageIO.read(getClass().getResource("/instruction.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if (img != null) {
			g.drawImage(img, 0, 0, null);
		}
	}
}
