
package core;

import java.util.Random;

import gameScreen.BottleCell;
import gameScreen.DiamondCell;
import gameScreen.GameScreen;
import gameScreen.TimeCell;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.Constants;
import util.Resources;

public class Main extends Application {
	
	private Thread eventMakerThread ; 
	
	@Override
	public void start(Stage primaryStage) {
		

		ScreenManager.getInstance().setStage( primaryStage );
		Resources.getInstance().initialize() ;
		
		eventMakerThread = new Thread( () -> {
			
			Random random = new Random();
			long countCombo = 0 ;
			GameScreen gameScreen ;
			while ( !Thread.currentThread().isInterrupted() ) {
			    try {
			    	gameScreen = ScreenManager.getInstance().getGameScreen();
			    	int sleepTime = Constants.EVENT_MAKER_SLEEP_TIME ;
			    	int countCell = gameScreen.getGridCell().countClickCell() ; 
			    
			    	if( countCell <= Constants.GRID_SHUFFLE_THRESHOLD ) {
			    		if( !gameScreen.getGameLogic().getShuffle() )
			    			gameScreen.getGameLogic().setShuffle( true );
			    	}
			    	System.out.println("Combo : " + countCombo + "/" + Constants.COMBO_THRESHOLD );
			    	System.out.println("Shuffle : " + countCell + "/" + Constants.GRID_SHUFFLE_THRESHOLD );
			    	if( !gameScreen.getGameStatus().isPause() ) {
			    		countCombo += ( 1 << (gameScreen.getGameStatus().getCombo()>>3) ) ;
			    		if( gameScreen.getGridCell().countItemCell() >= Constants.MAX_ITEM_IN_GRID ) {
			    			countCombo = 0l ;
			    		}
						if( countCombo >= Constants.COMBO_THRESHOLD ) {
							countCombo = 0l ;
							int randomRow = random.nextInt( Constants.CELL_PER_COL ) + 1 ;
							int randomCol = random.nextInt( Constants.CELL_PER_ROW ) + 1 ;
							int randomType =  random.nextInt( 10000 )%3 ; 
							
							if( randomType == 0 ) {
								gameScreen.getGridCell().addExtraAddCell( new DiamondCell( randomRow, randomCol, gameScreen.getGridCell()) );
							} else if ( randomType == 1 ) {
								gameScreen.getGridCell().addExtraAddCell( new TimeCell( randomRow, randomCol, gameScreen.getGridCell()) );
							} else {
								gameScreen.getGridCell().addExtraAddCell( new BottleCell( randomRow, randomCol, gameScreen.getGridCell()) );
							}							
						}
			    	}
			        Thread.sleep(sleepTime);
			    
			    } catch (InterruptedException ex) {
			        Thread.currentThread().interrupt();
			    }
			}
			
		});
		
		eventMakerThread.start();

		primaryStage.setTitle( Constants.GAME_NAME );
		primaryStage.setResizable( false );
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
				updateTime = currentTime ;
				if ( updateTime < maximumWaitTime ) {
					try {
						Thread.sleep( (maximumWaitTime - updateTime) / 1000000l );
					} catch (InterruptedException e) {
						// TODO: handle exception
						Thread.interrupted() ; 
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		primaryStage.show();
	}

	public void stop(){
		
		if( eventMakerThread.isAlive() ) {
			eventMakerThread.interrupt() ;
			System.out.println("EventMaker is stopped.");
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
