package pauseScreen;

import core.MouseActionable;
import util.InputUtility;

public class PauseLogic {
	
	private PauseScreen pauseScreen ; 
	
	public PauseLogic( PauseScreen pauseScreen ) {
		this.pauseScreen = pauseScreen ;
	}
	public void updateLogic() {
    	if( InputUtility.isMouseClickedTriggered() ) {
			Object object = pauseScreen.getObjectAtPos(InputUtility.getMouseX(), InputUtility.getMouseY()) ; 
			if( object != null ) {
				if( object instanceof MouseActionable ) {
					((MouseActionable) object).clickAction(InputUtility.getMouseX(), InputUtility.getMouseY()); 
				}
			}
		}	
	}
}
