package core;

import aboutScreen.AboutScreen;
import gameScreen.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mainScreen.MainScreen;
import util.Constants;
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
	
	private int step ;
	

	public ScreenManager() {
		mainScreen = new MainScreen() ;
		gameScreen = new GameScreen() ;
		aboutScreen = new AboutScreen() ;
		setNextScreen( mainScreen );
		step = 0 ;
	}
	
	public GameScreen getGameScreen() {
		return this.gameScreen ;
	}
	
	public void newGameScreen() {
		gameScreen = new GameScreen() ; 
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
			stage.setScene( currentScreen );
			currentScreen.active(); 
			InputUtility.postUpdate();
			
		}

		if (this.currentScreen != null) {
			this.currentScreen.update();
		}

	}
	
}
