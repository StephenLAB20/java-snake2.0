package com.snake.reload;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameMessage extends JPanel {
	
	private String boldStr;
	private String smallStr;
	private Font boldFont;
	private Font smallFont;
	FontMetrics boldMetrics;
	FontMetrics smallMetrics;
	private int targetWidth;
	
	GameMessage(String boldStr, String smallStr) {

		this.boldStr = boldStr;
		this.smallStr = smallStr;
		
		boldFont = (new Font("Arial", Font.BOLD, 50));
		boldMetrics = getFontMetrics(boldFont);

		smallFont = new Font("Arial", Font.BOLD, 14);
		smallMetrics = getFontMetrics(smallFont);
		
		findWidth(boldMetrics, smallMetrics);
	}
	
	public void findWidth(FontMetrics boldMetrics, FontMetrics smallMetrics) {
		
		if (boldMetrics.stringWidth(boldStr) > smallMetrics.stringWidth(smallStr)) {
			targetWidth = boldMetrics.stringWidth(boldStr);
		} else {
			targetWidth = smallMetrics.stringWidth(smallStr);
		}
	}
	
	public void drawMessage(Graphics g) {

		// draw rect
		g.setColor(GameParams.MIDDLE_OP_GREEN);
		g.fillRect(300 - (targetWidth + 40) / 2, 300 - boldMetrics.getHeight(), targetWidth + 40,
				boldMetrics.getHeight() * 2);
		// draw str1
		g.setFont(boldFont);
		g.setColor(GameParams.LIGHT_GREEN);
		g.drawString(boldStr, 300 - boldMetrics.stringWidth(boldStr) / 2, 300);
		// draw str2
		g.setColor(GameParams.DARK_GREEN);
		g.setFont(smallFont);
		g.drawString(smallStr, 300 - smallMetrics.stringWidth(smallStr) / 2, 310 + smallMetrics.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		drawMessage(g);
	}
}