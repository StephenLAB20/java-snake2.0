package com.snake.reload;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	private Dimension contentSize = new Dimension(GameParams.SCREEN_WIDTH, GameParams.GAME_FIELD_HEIGTH + GameParams.TOP_PANEL_HEIGTH);
	ImageIcon logoImg = new ImageIcon(getClass().getResource("/frameLogo.png"));

	TopPanel top;
	GamePanel field;
	MainPanel intro;
	InstructionPanel rules;

	JPanel container;
	CardLayout card;

	BigButton bigButton;

	BigButton playButton;
	BigButton instructionsButton;
	BigButton backButton;

	public GameFrame() {

		intro = new MainPanel();

		// BUTTONS
		playButton = new BigButton("PLAY");
		playButton.setBounds(150, 420, 300, 70);
		playButton.setStatic();

		instructionsButton = new BigButton("INSTRUCTIONS");
		instructionsButton.setBounds(150, 510, 300, 70);
		instructionsButton.setStatic();

		backButton = new BigButton("BACK");
		backButton.setBounds(150, 560, 300, 70);
		backButton.setStatic();

		intro.add(playButton);
		intro.add(instructionsButton);

		container = new JPanel();
		card = new CardLayout();

		container.setLayout(card);
		container.add(intro, "Intro");
		card.show(container, "Intro");

		this.getContentPane().setPreferredSize(contentSize);
		this.add(container);
		this.setTitle("Snake");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(logoImg.getImage());
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);

		playButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setStatic();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setHover();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				top = new TopPanel();
				field = new GamePanel(top);
				JPanel page = new JPanel();
				page.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

				page.add(top);
				page.add(field);
				container.add(page, "Page");
				card.show(container, "Page");
				field.requestFocus();
			}
		});

		instructionsButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				rules = new InstructionPanel();
				rules.add(backButton);

				container.add(rules, "Rules");
				card.show(container, "Rules");
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				instructionsButton.setHover();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				instructionsButton.setStatic();
			}
		});

		backButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setStatic();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setHover();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(container, "Intro");
			}
		});
	}
}
