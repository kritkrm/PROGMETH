package core;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import screen.AboutScreen;
import screen.GameScreen;
import screen.MainScreen;
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
	private int changeStep ;
	
	public ScreenManager() {
		
		canvas = new Canvas( Constants.DEFAULT_SCREEN_SIZE.getWidth() , Constants.DEFAULT_SCREEN_SIZE.getHeight() ) ; 
		mainScreen = new MainScreen( canvas ) ;
		gameScreen = new GameScreen( canvas ) ;
		aboutScreen = new AboutScreen( canvas ) ;
		setNextScreen( mainScreen );
		addListener();
		changeStep = 0 ;
		// set step of changing screen of the game to be zero 
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
			changeStep = 60 ;
			if( currentScreen != null ) {
				currentScreen.inactive(); 
			} 
			this.currentScreen = nextScreen;
			this.nextScreen = null;
			currentScreen.active(); 			
			
		}
		if (this.currentScreen != null) {
			this.currentScreen.update();
		}
		if( changeStep > 0 ){ 
			// make a smooth fade in effect 
			changeStep -= 1 ;
			GraphicsContext gc = this.canvas.getGraphicsContext2D() ;
			gc.setFill(Color.BLACK);
			gc.setGlobalAlpha(((double)changeStep/60));
			gc.fillRect(0, 0, Constants.DEFAULT_SCREEN_SIZE.getWidth(), Constants.DEFAULT_SCREEN_SIZE.getHeight());
			gc.setGlobalAlpha(1);
		} 
	}
	
	private void addListener() {
		this.canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("MouseReleased : " + event.getButton().toString());
				if (event.getButton() == MouseButton.PRIMARY)
					InputUtility.setMouseLeftDown(false);
				if (event.getButton() == MouseButton.SECONDARY)
					InputUtility.setMouseRightDown(false);

			}
		});
		this.canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("MousePressed : " + event.getButton().toString());
				if (event.getButton() == MouseButton.PRIMARY) {
					InputUtility.setMouseLeftDown(true);
					InputUtility.setMouseLeftLastDown(true);
				}
				if (event.getButton() == MouseButton.SECONDARY) {
					InputUtility.setMouseRightDown(true);
					InputUtility.setMouseRightLastDown(true);
				}

			}
		});

		this.canvas.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(false);
			}
		});

		this.canvas.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(true);
			}
		});

		this.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}
		});

		this.canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}
		});
		
				
	}
	
}
