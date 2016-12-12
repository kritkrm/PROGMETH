package gameScreen;

import core.MouseActionable;
import util.InputUtility;

public class GameLogic {
	
	private GameScreen gameScreen ; 	
	private boolean shuffle ;
	
	public GameLogic( GameScreen gameScreen ) {
		this.gameScreen = gameScreen ;
		this.shuffle = false ;
	}
	
	public void updateLogic() {
		
		if( InputUtility.isMouseClickedTriggered() ) {
			Object object = gameScreen.getObjectAtPos(InputUtility.getMouseX(), InputUtility.getMouseY()) ; 
			if( object != null ) {
				if( object instanceof MouseActionable ) {
					((MouseActionable) object).clickAction(InputUtility.getMouseX(), InputUtility.getMouseY()); 
				}
			}
		}	
		if( shuffle ) {
			gameScreen.getGridCell().shuffle() ;
			setShuffle( false );
		}
		gameScreen.getGridCell().update();
	}
	
	public boolean getShuffle() {
		return this.shuffle ; 
	}
	
	public synchronized void setShuffle( boolean shuffle ) {
		this.shuffle = shuffle ;
	}
	
}
