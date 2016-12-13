package util;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class Resources {
	public Image mainScreen , aboutScreen , gameScreen ;
	
	public Image aboutbutton , aboutbutton2 ;
	public Image exitbutton , exitbutton2 ;
	public Image playbutton , playbutton2;
	public Image LongStory;
	public AudioClip soundMainScreen, soundGameScreen , clickButton, clickBox , boom ;
	public Image yellowCell , blueCell , greenCell , purpleCell , redCell ;
	public Image diamondCell , timeCell , bottleCell ;
	public Image littleHomeButton , littleRetryButton , littlePlayButton , littlePauseButton;
	public Font pauseFont , scoreFont ;
	public Image playCell , aboutCell , exitCell ;
	private static Resources instance = new Resources() ; 
	
	public static Resources getInstance() {
		return instance ;
	}
	
	private ClassLoader getClassLoader() {
		return this.getClass().getClassLoader();
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
		pauseFont = Font.loadFont(getClass().getResourceAsStream("/fonts/ClearSans-Bold.ttf"), 40);
		scoreFont = Font.loadFont(getClass().getResourceAsStream("/fonts/ClearSans-Bold.ttf"), 30);
		return ; 

	}
	
	private void loadSound() {
		soundGameScreen = new AudioClip((ClassLoader.getSystemResource("sound/soundGameScreen.mp3")).toString());
		soundMainScreen = new AudioClip((ClassLoader.getSystemResource("sound/soundMainScreen.mp3")).toString());
		clickBox 		= new AudioClip((ClassLoader.getSystemResource("sound/clickBox.mp3")).toString());
		clickButton 	= new AudioClip((ClassLoader.getSystemResource("sound/clickButton.mp3")).toString());
		boom 			= new AudioClip((ClassLoader.getSystemResource("sound/boom.mp3")).toString());
		return ;
	}
	
	private void loadImage() {
		
		playCell 	= new Image(ClassLoader.getSystemResource("cell/1.png").toString());
		exitCell 	= new Image(ClassLoader.getSystemResource("cell/3.png").toString());
		aboutCell 	= new Image(ClassLoader.getSystemResource("cell/2.png").toString());

		
		yellowCell 	= new Image(ClassLoader.getSystemResource("cell/cheeseCell.png").toString());
		redCell 	= new Image(ClassLoader.getSystemResource("cell/blackCell.png").toString());
		blueCell 	= new Image(ClassLoader.getSystemResource("cell/blueCell.png").toString());
		greenCell 	= new Image(ClassLoader.getSystemResource("cell/purpleCell2.png").toString());
		purpleCell 	= new Image(ClassLoader.getSystemResource("cell/purpleCell.png").toString());
		
		diamondCell = new Image(ClassLoader.getSystemResource("cell/diamondCell.png").toString());
//		diamondCell = new Image(ClassLoader.getSystemResource("cell/newDiamondCell.png").toString());

		bottleCell 	= new Image(ClassLoader.getSystemResource("cell/bottleCell.png").toString());
		timeCell 	= new Image(ClassLoader.getSystemResource("cell/timeCell.png").toString());
			
		littleHomeButton 	= new Image(ClassLoader.getSystemResource("button/littleHomeButton.png").toString());
		littleRetryButton 	= new Image(ClassLoader.getSystemResource("button/littleRetryButton.png").toString());
		littlePlayButton 	= new Image(ClassLoader.getSystemResource("button/littlePlayButton.png").toString());
		littlePauseButton 	= new Image(ClassLoader.getSystemResource("button/littlePauseButton.png").toString());

		aboutScreen = new Image(ClassLoader.getSystemResource("background/aboutScreen.png").toString());
		gameScreen 	= new Image(ClassLoader.getSystemResource("background/gameScreen.png").toString());
		mainScreen 	= new Image(ClassLoader.getSystemResource("background/mainScreen.png").toString());
		
		aboutbutton 	= new Image(ClassLoader.getSystemResource("button/aboutbutton.png").toString());
		aboutbutton2 	= new Image(ClassLoader.getSystemResource("button/aboutbutton2.png").toString());
		
		exitbutton 		= new Image(ClassLoader.getSystemResource("button/exitbutton.png").toString());
		exitbutton2 	= new Image(ClassLoader.getSystemResource("button/exitbutton2.png").toString());
		
		playbutton 	= new Image(ClassLoader.getSystemResource("button/playbutton.png").toString());
		playbutton2 = new Image(ClassLoader.getSystemResource("button/playbutton2.png").toString());
		
		LongStory = new Image(ClassLoader.getSystemResource("pic/LongStory.png").toString());
		
		return ; 

	}
	
}
