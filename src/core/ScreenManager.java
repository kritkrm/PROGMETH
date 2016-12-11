package core;

import aboutScreen.AboutScreen;
import gameScreen.GameScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainScreen.MainScreen;
import pauseScreen.PauseScreen;
import util.InputUtility;

public class ScreenManager {
	
	private static final ScreenManager instance = new ScreenManager();	
	
	public static ScreenManager getInstance() {
		return instance;
	}
	
	private Screen nextScreen ;
	private Screen currentScreen ;
	private Stage stage ; 
	
	private GameScreen gameScreen ;
	private MainScreen mainScreen ;
	private AboutScreen aboutScreen ;
	private PauseScreen pauseScreen ; 

	public ScreenManager() {
		mainScreen = new MainScreen() ;
		gameScreen = new GameScreen() ;
		aboutScreen = new AboutScreen() ;
		pauseScreen = new PauseScreen() ; 
		setNextScreen( mainScreen );
	}
	
	public GameScreen getGameScreen() {
		return this.gameScreen ;
	}
	
	public MainScreen getMainScreen() {
		return this.mainScreen ;
	}
	
	public AboutScreen getAboutScreen() {
		return this.aboutScreen ;
	}
	
	public void setNextScreen( Screen screen ) {
		this.nextScreen = screen ;
	}
	
	public void setStage( Stage stage ) {
		this.stage = stage ;
	}
	
	public Screen getCurrentScreen() {
		return currentScreen ;
	}
	
	public void update() {
		
		if (this.nextScreen != null) {
			if( currentScreen != null ) {
				currentScreen.inactive(); 
			}
			this.currentScreen = nextScreen;
			this.nextScreen = null;
			stage.setScene( getCurrentScreen() );
			InputUtility.postUpdate();
		}

		if (this.currentScreen != null) {
			currentScreen.active(); 
			this.currentScreen.update();
		}

	}
	
}
