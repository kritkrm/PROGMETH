package core;

import javafx.scene.Scene;
import javafx.stage.Stage;
import mainScreen.MainScreen;
import screen.GameScreen;
import screen.Screen;

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
	
	public ScreenManager() {
		mainScreen = new MainScreen() ;
		gameScreen = new GameScreen() ;
		setNextScreen( mainScreen );
	}
	
	public GameScreen getGameScreen() {
		return this.gameScreen ;
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

		}

		if (this.currentScreen != null) {
			currentScreen.active(); 
			this.currentScreen.update();
		}

	}
	
}
