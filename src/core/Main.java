package core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import util.Constants;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		GameScreen root = new GameScreen( new GridCell() );
		Scene scene = new Scene( root );
		
		primaryStage.setScene( scene );
		primaryStage.setTitle( Constants.GAME_NAME );
			
		new AnimationTimer() {
			long updateTime ;
			final long maximumWaitTime = 1000000000l / Constants.MAX_FRAME_PER_SECOND;
			@Override
			public void handle( long currentTime ) {
				// TODO Auto-generated method stub
				
//				updateTime = currentTime ; 
//				StageManager.getInstance().update() ; 
//				updateTime = currentTime ;
//				
//				if ( updateTime < maximumWaitTime ) {
//					try {
//						Thread.sleep( (maximumWaitTime - updateTime) / 1000000l );
//					} catch (InterruptedException e) {
//						// TODO: handle exception
//						Thread.interrupted() ; 
//						e.printStackTrace();
//					}
//				}
				root.drawComponenet(); 
				
			}
		}.start();
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
