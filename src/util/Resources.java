package util;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Resources {
	
	public Image ExitedButton1,ExitedButton2;
	public Image HighscoreButton1,HighscoreButton2;
	public Image HomeButton1,HomeButton2;
	public Image PauseButton1,PauseButton2;
	public Image SoundButton1,SoundButton2;
	public Image TalkButton1,TalkButton2;
	public Image Easy, Medium, Hard;
	public Image Menu, Play,Score,Options;
	public AudioClip soundGame;
	
	private static Resources instance = new Resources() ; 
	
	public static Resources getInstance() {
		return instance ;
	}
	
	private ClassLoader getClassLoader() {
		return this.getClass().getClassLoader();
	}
	
	private InputStream getResourceAsStream(String resourcePath) {
		return getClassLoader().getResourceAsStream("res/" + resourcePath);
	}

	public boolean initialize() {
		try {
			loadFonts();
			loadImage();
			loadSound();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	private void loadFonts() {
		
		return ; 

	}
	
	private void loadSound() {
		soundGame = new AudioClip((ClassLoader.getSystemResource("song/111.mp3")).toString());
		return ;
	}
	
	private void loadImage() {
		ExitedButton1 = new Image(ClassLoader.getSystemResource("button/ExitedButton1.png").toString());
		ExitedButton2 = new Image(ClassLoader.getSystemResource("button/ExitedButton2.png").toString());
		HighscoreButton1 = new Image(ClassLoader.getSystemResource("button/HighscoreButton1.png").toString());
		HighscoreButton2 = new Image(ClassLoader.getSystemResource("button/HighscoreButton2.png").toString());
		HomeButton1 = new Image(ClassLoader.getSystemResource("button/HomeButton1.png").toString());
		HomeButton2 = new Image(ClassLoader.getSystemResource("button/HomeButton2.png").toString());
		PauseButton1 = new Image(ClassLoader.getSystemResource("button/PauseButton1.png").toString());
		PauseButton2 = new Image(ClassLoader.getSystemResource("button/PauseButton2.png").toString());
		SoundButton1 = new Image(ClassLoader.getSystemResource("button/SoundButton1.png").toString());
		SoundButton2 = new Image(ClassLoader.getSystemResource("button/SoundButton2.png").toString());
		TalkButton1 = new Image(ClassLoader.getSystemResource("button/TalkButton1.png").toString());
		TalkButton2 = new Image(ClassLoader.getSystemResource("button/TalkButton2.png").toString());
		Easy = new Image(ClassLoader.getSystemResource("button/easy.png").toString());
		Medium = new Image(ClassLoader.getSystemResource("button/medium.png").toString());
		Hard = new Image(ClassLoader.getSystemResource("button/hard.png").toString());
		Menu = new Image(ClassLoader.getSystemResource("button/Menu.png").toString());
		Play = new Image(ClassLoader.getSystemResource("button/play.png").toString());
		Options = new Image(ClassLoader.getSystemResource("button/option.png").toString());
		Score = new Image(ClassLoader.getSystemResource("button/score.png").toString());
		
		return ; 

	}
	
}
