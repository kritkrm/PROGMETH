package core;

import com.sun.prism.impl.BaseMesh.FaceMembers;

import gameScreen.GameScreen;
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
import util.Constants;

public class Main extends Application {
	
	private GameScreen gameScreen ;
	
	@Override
	public void start(Stage primaryStage) {
		GameScreen gameScreen = new GameScreen() ;
		
		Scene scene = new Scene( gameScreen );
		ScreenManager.getInstance().setNextScreen( gameScreen );
		
		primaryStage.setScene( scene );
		primaryStage.setTitle( Constants.GAME_NAME );
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
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
//			 gameScreen.update();		
			}
		}.start();
		
		primaryStage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
}
