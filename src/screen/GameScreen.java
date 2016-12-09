package screen;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import object.GameLogic;
import object.GameScreenObjectHolder;
import object.GameStatus;
import object.GridCell;
import object.ScreenObject;

public class GameScreen extends Screen {
	
	private GridCell gridCell ; 
	private GameStatus gameStatus ; 
	private GameLogic gameLogic ; 
	
	public GameScreen() {
		super( new StackPane() ) ; 
		this.gridCell = new GridCell( this ) ;
		this.gameStatus = new GameStatus( this ) ;
		this.gameLogic = new GameLogic( this ) ; 
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
			for(ScreenObject renderable : GameScreenObjectHolder.getInstance().getEntities() ) {
				renderable.draw(gc);
			}
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		gameLogic.updateLogic(); 
		gridCell.update();
		drawComponenet();
		
	}
	
}

