package core;

import java.io.ObjectInputStream.GetField;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import util.Constants;

public class GameScreen extends Screen {
	
	private GridCell gridCell ; 
	private GameStatus gameStatus ; 
	private GameLogic gameLogic ; 
	
	private static final GameScreen instance = new GameScreen();
	
	public static GameScreen getInstance() { return instance ; } 
	
	public GameScreen() {
		super() ; 
		this.gridCell = new GridCell() ;
		this.gameStatus = new GameStatus() ;
		this.gameLogic = new GameLogic() ; 
	}
	
	public GridCell getGridCell() {
		return gridCell ;
	}
	
	public GameStatus getGameStatus() {
		return gameStatus ; 
	}
	
	public GameLogic getGameLogic() {
		return gameLogic ; 
	}
	
	@Override
	public void drawComponenet(){
		if( isActive ) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.WHITE);
			gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.restore(); 
			for(ScreenObejct renderable : GameScreenObejctHoloder.getInstance().getEntities() ) {
				renderable.draw(gc);
			}
		}
	}
	
}

