package gameScreen;

import java.awt.Container;

import core.Screen;
import core.ScreenManager;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import util.Constants;
import util.Resources;

public class GameScreen extends Screen {
	
	private GridCell gridCell ; 
	private GameStatus gameStatus ; 
	private GameLogic gameLogic ; 
	private int frameCount ;
	
	public GameScreen() {
		super( new StackPane() ) ; 
		GameScreenObjectHolder.getInstance().getEntities().clear();
		this.gridCell = new GridCell( this ) ;
		this.gameStatus = new GameStatus( this ) ;
		this.gameLogic = new GameLogic( this ) ; 
		GameScreenObjectHolder.getInstance().add( gridCell );		
		GameScreenObjectHolder.getInstance().add( gameStatus );		
		frameCount = 0 ;
	}
	
	@Override
	public void drawComponenet(){
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.restore(); 
		gc.drawImage(Resources.getInstance().gameScreen,0,0);
		for(ScreenObject renderable : GameScreenObjectHolder.getInstance().getEntities() ) {
			renderable.draw(gc);
		}
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		frameCount++;
		if( frameCount == Constants.MAX_FRAME_PER_SECOND ) {
			frameCount = 0 ; 
			gameStatus.decreaseRemainingTime( 1 );
			gameStatus.decreaseCombo( 1 );
		}
		if( gameStatus.getRemainingTime() > 0 ) {
			gameLogic.updateLogic(); 
			drawComponenet();
		} else {
			ScreenManager.getInstance().setNextScreen( ScreenManager.getInstance().getEndScreen() ) ;		
		}
		
	}
	
	public Object getObjectAtPos( int x , int y ) {
		int currentObjectZ = -1 ; 
		Object currentObject = null ;
		for(ScreenObject renderable : GameScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isInside(x, y) ) {
				if( currentObjectZ < renderable.getZ() ) {
					currentObject = renderable ; 
				}				
			}
		}
		return currentObject;
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
	public void active() {
		// TODO Auto-generated method stub
		isActive = true ;
		if( !Resources.getInstance().soundGameScreen.isPlaying() )
			Resources.getInstance().soundGameScreen.play();
	}

	@Override
	public void inactive() {
		// TODO Auto-generated method stub
		isActive = false ;
		if( Resources.getInstance().soundGameScreen.isPlaying() )
			Resources.getInstance().soundGameScreen.stop();
	}
	
	
}

