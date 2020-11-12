package com.snake.reload;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLab {

	private Clip clip;
	private String path;
	private boolean loop;
	private long pos;

	SoundLab(String path, boolean loop) {

		this.path = path;
		this.loop = loop;
	}

	public void playSound() {

		pos = 0;
		
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		
			if (loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopSound() {
		pos = clip.getMicrosecondPosition();
		clip.stop();
	}

	public void resumeSound() {
		clip.setMicrosecondPosition(pos);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}


