package com.snake.reload;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel{
	
	private String strApple;
	private String strElixir;
	private String strTimer;
	private final Dimension panelSize = new Dimension(GameParams.SCREEN_WIDTH, GameParams.TOP_PANEL_HEIGTH); //600x80
	
	JLabel labelLogo;
	JLabel labelApples;
	JLabel labelElixirs;
	JLabel labelTimer;
	ImageIcon logoImg;
	
	TopPanel() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		this.setPreferredSize(panelSize);
		this.setBackground(GameParams.DARK_GREEN);
		
		labelLogo = new JLabel();
		labelLogo.setPreferredSize(new Dimension(62, 80));
		ImageIcon logoImg = new ImageIcon(getClass().getResource("/smallLogo.png"));
		
		labelLogo.setIcon(logoImg);
	    this.add(labelLogo);
		
		
		labelApples = new JLabel();
		labelApples.setPreferredSize(new Dimension(110, 80));
	    labelApples.setFont(new Font("Arial", Font.BOLD, 18));
	    labelApples.setForeground(GameParams.LIGHT_GREEN);
	    labelApples.setHorizontalAlignment(JLabel.CENTER);
	    this.add(labelApples);
	    
	    labelElixirs = new JLabel();
	    labelElixirs.setPreferredSize(new Dimension(110, 80));
	    labelElixirs.setFont(new Font("Arial", Font.BOLD, 18));
	    labelElixirs.setForeground(GameParams.LIGHT_GREEN);
	    labelElixirs.setHorizontalAlignment(JLabel.CENTER);
	    this.add(labelElixirs);
	    
	    labelTimer = new JLabel();
	    labelTimer.setPreferredSize(new Dimension(218, 80));
	    labelTimer.setFont(new Font("Arial", Font.BOLD, 18));
	    labelTimer.setForeground(GameParams.LIGHT_GREEN);
	    labelTimer.setHorizontalAlignment(JLabel.CENTER);
	    this.add(labelTimer);
	}

	public void setStrApple(String strApple) {
		this.strApple = strApple;
		labelApples.setText("Apples: " + this.strApple);
	}

	public void setStrElixir(String strElixir) {
		this.strElixir = strElixir;
		labelElixirs.setText("Elixirs: " + this.strElixir);
	}
	
	public void setStrTimer(String strTimer) {
		this.strTimer = strTimer;
		labelTimer.setText("Time: " + this.strTimer);
	}
}



	

