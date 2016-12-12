package core;

import Screen.AboutScreen;
import Screen.GameScreen;
import Screen.MainScreen;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Constants;
import util.InputUtility;

public class ScreenManager {
	
	private static final ScreenManager instance = new ScreenManager();	
	
	public static ScreenManager getInstance() {
		return instance;
	}
	
	private Screen nextScreen ;
	private Screen currentScreen ;
	
	private GameScreen gameScreen ;
	private MainScreen mainScreen ;
	private AboutScreen aboutScreen ;	
	private Canvas canvas ; 
	
	public ScreenManager() {
		
		canvas = new Canvas( Constants.DEFAULT_SCREEN_SIZE.getWidth() , Constants.DEFAULT_SCREEN_SIZE.getHeight() ) ; 
		mainScreen = new MainScreen( canvas ) ;
		gameScreen = new GameScreen( canvas ) ;
		aboutScreen = new AboutScreen( canvas ) ;
		setNextScreen( mainScreen );
	}
	
	public Canvas getCanvas() {
		return this.canvas ;
	}
	
	
	public GameScreen getGameScreen() {
		return this.gameScreen ;
	}
	
	public void newGameScreen() {
		gameScreen = new GameScreen( canvas ) ; 
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
			currentScreen.active(); 
			InputUtility.postUpdate();
			
		}

		if (this.currentScreen != null) {
			this.currentScreen.update();
		}

	}
	
}
