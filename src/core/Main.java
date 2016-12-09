package core;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mainScreen.MainScreen;
import object.DiamondCell;
import object.TimeCell;
import screen.GameScreen;
import util.Constants;
import util.Resources;

public class Main extends Application {
	
	private GameScreen gameScreen ;
	private MainScreen mainScreen ;
	private Thread eventMakerThread ; 
	
	@Override
	public void start(Stage primaryStage) {
		gameScreen = new GameScreen() ;
		mainScreen = new MainScreen() ; 
		Resources.getInstance().initialize() ;
		
		eventMakerThread = new Thread( () -> {
			
			Random random = new Random();
		
			while ( !Thread.currentThread().isInterrupted() ) {
			    try {
			    	int sleepTime = 2000 / (gameScreen.getGameStatus().getCombo()+1 ); 
					
					if( gameScreen.isActive() ) {
						
						int randomRow = random.nextInt( Constants.CELL_PER_COL ) + 1 ;
						int randomCol = random.nextInt( Constants.CELL_PER_ROW ) + 1 ;
						int randomType =  random.nextInt( 2 ) ; 
						if( randomType == 0 ) {
							gameScreen.getGridCell().addExtraAddCell( new DiamondCell( randomRow, randomCol, gameScreen.getGridCell()) );
						} else {
							gameScreen.getGridCell().addExtraAddCell( new TimeCell( randomRow, randomCol, gameScreen.getGridCell()) );
						} 
						gameScreen.getGameStatus().decreaseCombo( (int)(gameScreen.getGameStatus().getCombo()*0.2)+1 );
					}
					
			        Thread.sleep(sleepTime);
			    
			    } catch (InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }
			}
			
		});
	
		// clear screenholder
		ScreenManager.getInstance().setNextScreen( gameScreen );
		ScreenManager.getInstance().update();
		
		eventMakerThread.start();
//		System.out.println(ScreenManager.getInstance().getCurrentScreen() );
		
		primaryStage.setScene( ScreenManager.getInstance().getCurrentScreen() );
		primaryStage.setTitle( Constants.GAME_NAME );
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				stop();
				System.exit(0);
			
			}
		});
		
		new AnimationTimer() {
			long updateTime ;
			final long maximumWaitTime = 1000000000l / Constants.MAX_FRAME_PER_SECOND;
			@Override
			public void handle( long currentTime ) {
				// TODO Auto-generated method stub
				
				updateTime = currentTime ; 
				ScreenManager.getInstance().update() ; 
				primaryStage.setScene( ScreenManager.getInstance().getCurrentScreen() );
				updateTime = currentTime ;
//				System.out.println(System.nanoTime());
//				System.out.println(currentTime);
//				if ( updateTime < maximumWaitTime ) {
//					try {
//						Thread.sleep( (maximumWaitTime - updateTime) / 1000000l );
//					} catch (InterruptedException e) {
//						// TODO: handle exception
//						Thread.interrupted() ; 
//						e.printStackTrace();
//					}
//				}
			}
		}.start();
		
		primaryStage.show();
	}

	public void stop(){
		
		if( eventMakerThread.isAlive() ) {
			eventMakerThread.interrupt() ;
			System.out.println("stop");
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
