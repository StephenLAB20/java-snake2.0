package com.snake.reload;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class Food extends JPanel{

	private int foodX;
	private int foodY;
	private String foodType;
	private Random random;

	public void createFood(int foodX, int foodY) {

		this.foodX = foodX;
		this.foodY = foodY;
		
		int someRand = new Random().nextInt(10);
		if (someRand < 9) {
			foodType = "Apple";
			System.out.println("New Apple x: " + foodX + " y: " + foodY);
		} else {
			foodType = "Elixir";
			System.out.println("New Elixir x: " + foodX + " y: " + foodY);
		}
	}

	public void drawFood(Graphics g) {
		
		switch (foodType) {
		case "Apple":
			g.setColor(GameParams.RED);
			g.fillRect(foodX, foodY, GameParams.DOT_SIZE, GameParams.DOT_SIZE);
			break;
			
		case "Elixir":
			g.setColor(GameParams.BLUE);
			g.fillRect(foodX, foodY, GameParams.DOT_SIZE, GameParams.DOT_SIZE);
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		drawFood(g);
	}

	public String getFoodType() {
		return foodType;
	}
}
