
package core;

import java.util.Random;

import gridObject.BottleCell;
import gridObject.DiamondCell;
import gridObject.TimeCell;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import screen.GameScreen;
import util.Constants;
import util.Resources;

public class Main extends Application {
	
	private Thread eventMakerThread ;
	private AnimationTimer gameLoop ;
	
	@Override
	public void start(Stage primaryStage) {
		
		Resources.getInstance().initialize() ;

		StackPane mainPane = new StackPane() ;
		
		// Every part of drawing method will draw in only on canvas which on ScreenManager Class
		mainPane.getChildren().add( ScreenManager.getInstance().getCanvas() ) ;
		
		primaryStage.setScene( new Scene( mainPane ) );
        primaryStage.initStyle(StageStyle.TRANSPARENT);

		eventMakerThread = new Thread( () -> {
			Random random = new Random();
			// accumulate combo point every Constants.EVENT_MAKER_SLEEP_TIME milisec
			long countCombo = 0 ;
			GameScreen gameScreen ;
			while ( !Thread.currentThread().isInterrupted() ) {
			    try {
			    	gameScreen = ScreenManager.getInstance().getGameScreen();
			    	int sleepTime = Constants.EVENT_MAKER_SLEEP_TIME ;
			    	// count number of cell group that can be destroy and shuffle when value is less than or equal Constants.GRID_SHUFFLE_THRESHOLD

			    	int countCell = gameScreen.getGridCell().countClickCell() ; 
			    	if( countCell <= Constants.GRID_SHUFFLE_THRESHOLD ) {
			    		if( !gameScreen.getGameLogic().getShuffle() )
			    			gameScreen.getGameLogic().setShuffle( true );
			    	}
//			    	System.out.println("Combo : " + countCombo + "/" + Constants.COMBO_THRESHOLD );
//			    	System.out.println("Shuffle : " + countCell + "/" + Constants.GRID_SHUFFLE_THRESHOLD );
			    	
			    	if( !gameScreen.getGameStatus().isPause()  ) {
			    		// this is a ratio to increas combo acummulation to be expotential
			    		countCombo += ( 1 << (gameScreen.getGameStatus().getCombo()>>3) ) ;
			    		if( gameScreen.getGridCell().countItemCell() >= Constants.MAX_ITEM_IN_GRID ) {
			    			countCombo = 0l ;
			    			// to ensure that number of Item Cell in grid will not more than Constants.MAX_ITEM_IN_GRID
			    		}
						if( countCombo >= Constants.COMBO_THRESHOLD ) {
							// if combo accumulation reach to thresholdd value add some Item Cell by random and restart the count
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
			        // to control process cycle of eventMaker thread 
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
		
		gameLoop = new AnimationTimer() {
			long updateTime ;
			final long maximumWaitTime = 1000000000l / Constants.MAX_FRAME_PER_SECOND;
			@Override
			public void handle( long currentTime ) {
				// TODO Auto-generated method stub
				updateTime = currentTime ; 
				ScreenManager.getInstance().update() ; 
				updateTime = currentTime ;
				if ( updateTime < maximumWaitTime ) {
					// to contral FPS of the game 
					try {
						Thread.sleep( (maximumWaitTime - updateTime) / 1000000l );
					} catch (InterruptedException e) {
						// TODO: handle exception
						Thread.interrupted() ; 
						e.printStackTrace();
					}
				}
			}
		}; 
		
		gameLoop.start();

		primaryStage.show();
	
	}
	

	public void stop(){
		if( eventMakerThread.isAlive() ) {
			eventMakerThread.interrupt() ;
			System.out.println("EventMaker is stopped.");
			// make sure that eventMaker is stopped when exit the game 
		}
		gameLoop.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
