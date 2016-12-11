package util;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Resources {
	public Image mainScreen,pauseScreen,aboutScreen,gameScreen;
	public Image BlueBox,GreenBox,PurpleBox,RedBox,YellowBox;
	public Image aboutbutton,aboutbutton2,exitbutton,exitbutton2;
	public Image HomeButton1,HomeButton2;
	public Image nobutton,nobutton2,pausebutton,pausebutton2;
	public Image playbutton,playbutton2;
	public Image resumebutton, resumebutton2;
	public Image LongStory;
	public AudioClip soundMainScreen, soundGameScreen,clickButton, clickBox,boom;
	
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
		soundGameScreen = new AudioClip((ClassLoader.getSystemResource("sound/soundGameScreen.mp3")).toString());
		soundMainScreen = new AudioClip((ClassLoader.getSystemResource("sound/soundMainScreen.mp3")).toString());
		clickBox = new AudioClip((ClassLoader.getSystemResource("sound/clickBox.mp3")).toString());
		clickButton = new AudioClip((ClassLoader.getSystemResource("sound/clickButton.mp3")).toString());
		boom = new AudioClip((ClassLoader.getSystemResource("sound/boom.mp3")).toString());
		return ;
	}
	
	private void loadImage() {
		aboutScreen = new Image(ClassLoader.getSystemResource("background/aboutScreen.png").toString());
		gameScreen = new Image(ClassLoader.getSystemResource("background/gameScreen.png").toString());
		mainScreen = new Image(ClassLoader.getSystemResource("background/mainScreen.png").toString());
		pauseScreen = new Image(ClassLoader.getSystemResource("background/pauseScreen.png").toString());
		HomeButton1 = new Image(ClassLoader.getSystemResource("button/HomeButton1.png").toString());
		HomeButton2 = new Image(ClassLoader.getSystemResource("button/HomeButton2.png").toString());
		BlueBox   = new Image(ClassLoader.getSystemResource("box/BlueBox.png").toString());
		GreenBox  = new Image(ClassLoader.getSystemResource("box/GreenBox.png").toString());
		PurpleBox = new Image(ClassLoader.getSystemResource("box/PurpleBox.png").toString());
		RedBox    = new Image(ClassLoader.getSystemResource("box/RedBox.png").toString());
		YellowBox = new Image(ClassLoader.getSystemResource("box/YellowBox.png").toString());
		aboutbutton = new Image(ClassLoader.getSystemResource("button/aboutbutton.png").toString());
		aboutbutton2 = new Image(ClassLoader.getSystemResource("button/aboutbutton2.png").toString());
		exitbutton = new Image(ClassLoader.getSystemResource("button/exitbutton.png").toString());
		exitbutton2 = new Image(ClassLoader.getSystemResource("button/exitbutton2.png").toString());
		nobutton = new Image(ClassLoader.getSystemResource("button/nobutton.png").toString());
		nobutton2 = new Image(ClassLoader.getSystemResource("button/nobutton2.png").toString());
		pausebutton = new Image(ClassLoader.getSystemResource("button/pausebutton.png").toString());
		pausebutton2 = new Image(ClassLoader.getSystemResource("button/pausebutton2.png").toString());
		playbutton = new Image(ClassLoader.getSystemResource("button/playbutton.png").toString());
		playbutton2 = new Image(ClassLoader.getSystemResource("button/playbutton2.png").toString());
		resumebutton = new Image(ClassLoader.getSystemResource("button/resumebutton.png").toString());
		resumebutton2 = new Image(ClassLoader.getSystemResource("button/resumebutton2.png").toString());
		LongStory = new Image(ClassLoader.getSystemResource("pic/LongStory.png").toString());
		return ; 

	}
	
}
