package com.snake.reload;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

	private final Dimension fieldSize = new Dimension(GameParams.SCREEN_WIDTH, GameParams.GAME_FIELD_HEIGTH); // 600x600
	private final int ALL_DOTS = GameParams.SCREEN_WIDTH * GameParams.GAME_FIELD_HEIGTH / GameParams.DOT_SIZE;
	private int x[] = new int[ALL_DOTS];
	private int y[] = new int[ALL_DOTS];
	private int DELAY;
	private int bodyParts;
	private int apples;
	private int elixirs;
	private int foodX;
	private int foodY;
	private boolean isUnique;
	private boolean isEaten;
	private int randCount = 0; // to delete
	private String foodType;
	private char direction;
	private boolean inGame;
	private boolean isPause;
	private boolean isAction; // The snake auto starts with it. Remove it everywhere if it's no needed.
	private Timer timer;
	private Random random;
	long startTime;
	long endTime;
	long timeElapsed;
	long startPause;
	long pauseTime;

	GameMessage message;
	TopPanel topPanel;
	Snake snake;
	Food food;
	SoundLab mainSound;
	SoundLab foodSound;
	SoundLab pauseSound;
	SoundLab gameOverSound;

	GamePanel(TopPanel topPanel) {

		this.topPanel = topPanel;
		this.setPreferredSize(fieldSize);
		this.setBackground(GameParams.GREEN);
		this.setBorder(BorderFactory.createLineBorder(GameParams.MIDDLE_GREEN, GameParams.DOT_SIZE));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter2());
		
		mainSound = new SoundLab(GameParams.MAIN_SOUND, true);
		pauseSound = new SoundLab(GameParams.PAUSE_SOUND, false);
		foodSound = new SoundLab(GameParams.FOOD_SOUND, false);
		gameOverSound = new SoundLab(GameParams.GAMEOVER_SOUND, false);
		
		startGame();
	}

	public void startGame() {
		
		mainSound.playSound();
		apples = 0;
		elixirs = 0;
		bodyParts = 3;
		DELAY = 150;
		direction = 'R';
		isEaten = false;
		isUnique = false;
		inGame = true;
		isPause = false;
		isAction = false;
		pauseTime = 0;

		random = new Random();
		startTime = System.currentTimeMillis();

		timer = new Timer(DELAY, this);
		timer.start();

		snake = new Snake(bodyParts);
		food = new Food();

		createFood();
	}

	public void updateSnake() {
		
		x = snake.getSnakeX();
		y = snake.getSnakeY();

		snake.move(direction, isAction);
	}

	public void createFood() {
		
		checkUnique();
		System.out.println("sent to food create x " + foodX + " y " + foodY);
		food.createFood(foodX, foodY);
		foodType = food.getFoodType();
		isEaten = false;
	}

	public void updateStats(String foodType) {

		switch (foodType) {
		case "Apple":
			bodyParts++;
			snake.setBodyParts(bodyParts);
			apples++;

			if (DELAY > 50) {
				DELAY -= 5;
				timer.setDelay(DELAY);
				timer.getDelay();
				System.out.println("delay " + timer.getDelay());
			}
			break;

		case "Elixir":
			elixirs++;
			break;
		}
	}

	public void checkFood() {
		
		if (!isEaten) {

			switch (foodType) {
			case "Apple":
				if (x[0] == foodX & y[0] == foodY) {
					isUnique = false;
					isEaten = true;
					foodSound.playSound();
					System.out.println("Apple eaten x: " + foodX + " y: " + foodY);
					updateStats(foodType);
				}
				break;

			case "Elixir":
				if (x[0] == foodX & y[0] == foodY) {
					isUnique = false;
					isEaten = true;
					foodSound.playSound();
					System.out.println("Elixir eaten x: " + foodX + " y: " + foodY);
					updateStats(foodType);
				}
				break;
			}
		} else {
			createFood();
		}
	}

	public void checkCollisions() {

		// check if head collides with body
		if (elixirs >= 1) {

			for (int i = bodyParts; i > 0; i--) {
				if (x[0] == x[i] && y[0] == y[i]) {
					elixirs--;
					break;
				}
			}
		} else {
			for (int i = bodyParts; i > 0; i--) {
				if (x[0] == x[i] && y[0] == y[i]) {
					inGame = false;
				}
			}
		}

		// check if head collides with borders
		if (elixirs <= 0) {
			if (x[0] < GameParams.DOT_SIZE || x[0] > GameParams.SCREEN_WIDTH - GameParams.DOT_SIZE * 2
					|| y[0] < GameParams.DOT_SIZE || y[0] > GameParams.SCREEN_WIDTH - GameParams.DOT_SIZE * 2) {
				inGame = false;
			}
		} else {
			if (x[0] < GameParams.DOT_SIZE) {
				x[0] = GameParams.SCREEN_WIDTH - GameParams.DOT_SIZE * 2;
				elixirs--;
			} else if (x[0] > GameParams.SCREEN_WIDTH - GameParams.DOT_SIZE * 2) {
				x[0] = GameParams.DOT_SIZE;
				elixirs--;
			} else if (y[0] < GameParams.DOT_SIZE) {
				y[0] = GameParams.SCREEN_WIDTH - GameParams.DOT_SIZE * 2;
				elixirs--;
			} else if (y[0] > GameParams.SCREEN_WIDTH - GameParams.DOT_SIZE * 2) {
				y[0] = GameParams.DOT_SIZE;
				elixirs--;
			}
		}

		if (!inGame) {
			mainSound.stopSound();
			gameOverSound.playSound();
			timer.stop();
		}
	}

	public void checkUnique() {

		while (!isUnique) {
			foodX = random.nextInt((int) ((GameParams.SCREEN_WIDTH - GameParams.DOT_SIZE * 2) / GameParams.DOT_SIZE))
					* GameParams.DOT_SIZE + GameParams.DOT_SIZE;
			foodY = random.nextInt((int) ((GameParams.SCREEN_WIDTH - GameParams.DOT_SIZE * 2) / GameParams.DOT_SIZE))
					* GameParams.DOT_SIZE + GameParams.DOT_SIZE;
			System.out.println("Sent to snake x: " + foodX + " y: " + foodY);

			for (int i = bodyParts - 1; i >= 0; i--) {
				if (x[i] == foodX && y[i] == foodY) {
					isUnique = false;
					randCount++;
					break;
				} else {
					isUnique = true;
				}
			}

			System.out.println("Checked x: " + foodX + " Checked y: " + foodY);
			System.out.println("New Random Count: " + randCount);
		}
	}

	public void timeConvert(long milsec) {

		DecimalFormat df = new DecimalFormat("00");
		int time = (int) milsec / 1000;
		int ss = time % 60;
		int hh = time / 3600;
		int mm = hh % 60;
		String str = df.format(hh) + ":" + df.format(mm) + ":" + df.format(ss);
		topPanel.setStrTimer(str);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (inGame && !isPause) {
			updateSnake();
			checkCollisions();
			checkFood();

			topPanel.setStrApple("" + apples);
			topPanel.setStrElixir("" + elixirs);

			endTime = System.currentTimeMillis();
			timeElapsed = endTime - startTime - pauseTime;
			timeConvert(timeElapsed);
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		draw(g);
	}

	public void draw(Graphics g) {

		if (inGame && !isPause) {
			food.drawFood(g);
			snake.drawSnake(g);
		} else if (inGame && isPause) {
			food.drawFood(g);
			snake.drawSnake(g);

			message = new GameMessage("Pause", "Press space to resume the game");
			message.drawMessage(g);
		} else if (!inGame) {
			message = new GameMessage("Game Over", "Press space to start new game");
			message.drawMessage(g);
		}
	}

	public class MyKeyAdapter2 extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);

			int key = e.getKeyCode();

			switch (key) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					isAction = true;
					direction = 'L';
				}
				break;

			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					isAction = true;
					direction = 'R';
				}
				break;

			case KeyEvent.VK_UP:
				if (direction != 'D') {
					isAction = true;
					direction = 'U';
				}
				break;

			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					isAction = true;
					direction = 'D';
				}
				break;

			case KeyEvent.VK_CONTROL:
				if (elixirs > 0) {
					elixirs -= 1;
					DELAY += 50;
					timer.setDelay(DELAY);
				}
				break;

			case KeyEvent.VK_SPACE:
				
				
				if (inGame && !isPause) {
					mainSound.stopSound();
					pauseSound.playSound();
					isPause = true;
					startPause = System.currentTimeMillis();
				} else if (inGame && isPause) {
					isPause = false;
					mainSound.resumeSound();
					long tempDiff = System.currentTimeMillis() - startPause;
					pauseTime += tempDiff;
				} else if (!inGame) {
					startGame();
				}
				break;
			}
		}
	}
}