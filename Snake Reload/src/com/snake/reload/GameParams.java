package com.snake.reload;

import java.awt.Color;

public final class GameParams {
	
	// SIZE
	public static final int SCREEN_WIDTH = 600;
	public static final int GAME_FIELD_HEIGTH = 600;
	public static final int TOP_PANEL_HEIGTH = 80;
	public static final int DOT_SIZE = 20;
	
	// COLOR
	public static final Color GREEN = new Color(154, 203, 69);
	public static final Color MIDDLE_GREEN = new Color(127, 180, 34);
	public static final Color MIDDLE_OP_GREEN = new Color(127, 180, 34, 180);
	public static final Color LIGHT_GREEN = new Color(240, 253, 156);
	public static final Color DARK_GREEN = new Color(63, 136, 39);
	public static final Color RED = new Color(180, 34, 34);
	public static final Color BLUE = new Color(28, 173, 227);

	// SOUND
	public static final String BUTTON_SOUND = "/button.wav";
	public static final String MAIN_SOUND = "/snakeIntroSound.wav";
	public static final String PAUSE_SOUND = "/pauseSound.wav";
	public static final String GAMEOVER_SOUND = "/gameOver.wav";
	public static final String FOOD_SOUND = "/eaten.wav";
}
