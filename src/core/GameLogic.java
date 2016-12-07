package core;

public class GameLogic {
	
	private GameScreen gameScreen ; 
	
	public GameLogic( GameScreen gameScreen ) {
		this.gameScreen = gameScreen ; 
	}
	
	public void updateLogic() {
		if( InputUtility.isMouseLeftClicked() ) {
			Cell cell = gameScreen.getGridCell().getCellAtPos( InputUtility.getMouseX() , InputUtility.getMouseY() ) ;
			cell.clickAction(); 
//			System.out.println( cell.isDestroyed() );
		}	
	}
	
	public void updateScreen() {
		gameScreen.drawComponenet();
	}
}
