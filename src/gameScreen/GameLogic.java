package gameScreen;

import util.InputUtility;

public class GameLogic {
	
	private GameScreen gameScreen ; 
	
	public GameLogic( GameScreen gameScreen ) {
		this.gameScreen = gameScreen ;
	}
	
	public void updateLogic() {
		
		if( InputUtility.isMouseClickedTriggered() ) {
			Cell cell = gameScreen.getGridCell().getCellAtPos( InputUtility.getMouseX() , InputUtility.getMouseY() ) ;
			
			if( cell != null && !cell.isDestroyed() )
				cell.clickAction();
		}	
		System.out.println( InputUtility.isMouseClickedTriggered() );
	
	}
	
}
