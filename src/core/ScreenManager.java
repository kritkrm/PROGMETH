package core;

import Screen.AboutScreen;
import Screen.GameScreen;
import Screen.MainScreen;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
		addListener();

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
		if(InputUtility.isMouseLeftClicked() ) System.out.println("12321");
		if (this.nextScreen != null) {
			
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
