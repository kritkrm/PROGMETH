package gameScreen;


import aboutScreen.AboutScreenObjectHolder;
import core.Screen;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import mainScreen.MainScreenObjectHolder;

public class GameScreen extends Screen {
	
	private GridCell gridCell ; 
	private GameStatus gameStatus ; 
	private GameLogic gameLogic ; 
	
	public GameScreen() {
		super( new StackPane() ) ; 
		GameScreenObjectHolder.getInstance().getEntities().clear();
		this.gridCell = new GridCell( this ) ;
		this.gameStatus = new GameStatus( this ) ;
		this.gameLogic = new GameLogic( this ) ; 
		GameScreenObjectHolder.getInstance().add( gridCell );		
		GameScreenObjectHolder.getInstance().add( gameStatus );		
		
	}
	
	@Override
	public void drawComponenet(){
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.restore(); 
		for(ScreenObject renderable : GameScreenObjectHolder.getInstance().getEntities() ) {
			renderable.draw(gc);
		}
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		gameLogic.updateLogic(); 
		drawComponenet();
		
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
	
	
}

