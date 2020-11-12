package com.snake.reload;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Snake extends JPanel{
	
	private final int ALL_DOTS = GameParams.SCREEN_WIDTH * GameParams.GAME_FIELD_HEIGTH / GameParams.DOT_SIZE;
	private int x[] = new int[ALL_DOTS];
	private int y[] = new int[ALL_DOTS];
	private int bodyParts;
	private boolean isAction;
	private char direction = ' ';
	
	
	Snake (int bodyParts) {
		
		this.bodyParts = bodyParts;
		
		for (int i = 0; i < bodyParts; i++) {
			x[i] = (bodyParts-i) * GameParams.DOT_SIZE;
			y[i] = GameParams.DOT_SIZE;
		}
	}

	public void move(char direction, boolean isAction) {
		
		this.direction = direction;
		this.isAction = isAction;

		if (isAction) {
			for (int i = bodyParts; i > 0; i--) {
				x[i] = x[i - 1];
				y[i] = y[i - 1];
			}

			switch (direction) {
			case 'U': {
				y[0] = y[0] - GameParams.DOT_SIZE;
				break;
			}
			case 'D': {
				y[0] = y[0] + GameParams.DOT_SIZE;
				break;
			}
			case 'L': {
				x[0] = x[0] - GameParams.DOT_SIZE;
				break;
			}
			case 'R': {
				x[0] = x[0] + GameParams.DOT_SIZE;
				break;
			}
			case ' ': {
				x[0] = x[0];
				break;
			}
			}
		}
	}

	public void drawSnake(Graphics g) {
		
		for (int i = bodyParts-1; i >= 0; i--) {
			if (i > 0) {
				g.setColor(GameParams.LIGHT_GREEN);
				g.fillRect(x[i], y[i], GameParams.DOT_SIZE, GameParams.DOT_SIZE);
			} else {
				g.setColor(GameParams.DARK_GREEN);
				g.fillRect(x[i], y[i], GameParams.DOT_SIZE, GameParams.DOT_SIZE);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		drawSnake(g);
	}

	public int[] getSnakeX() {
		return x;
	}

	public int[] getSnakeY() {
		return y;
	}
	
	public void setBodyParts(int bodyParts) {
		this.bodyParts = bodyParts;
	}
}
