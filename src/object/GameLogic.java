package object;

import screen.GameScreen;
import util.InputUtility;

public class GameLogic {
	
	private GameScreen gameScreen ; 	
	private boolean shuffle ;
	
	public GameLogic( GameScreen gameScreen ) {
		this.gameScreen = gameScreen ;
		this.shuffle = false ;
	}
	
	public boolean getShuffle() {
		return this.shuffle ; 
	}
	
	public synchronized void setShuffle( boolean shuffle ) {
		this.shuffle = shuffle ;
	}
	
	public void updateLogic() {
		
		if( gameScreen.isActive() ) {
			if( InputUtility.isMouseClickedTriggered() ) {
				Cell cell = gameScreen.getGridCell().getCellAtPos( InputUtility.getMouseX() , InputUtility.getMouseY() ) ;
				if( cell != null && !cell.isDestroyed() )
					cell.clickAction();
			}	
			if( shuffle ) {
				gameScreen.getGridCell().shuffle() ;
				setShuffle( false );
			}
		}

		gameScreen.getGridCell().update();
	}
	
}
