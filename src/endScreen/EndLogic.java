package endScreen;

import aboutScreen.AboutScreen;
import core.MouseActionable;
import util.InputUtility;

public class EndLogic {
	
	private EndScreen endScreen ; 
	
	public EndLogic( EndScreen endScreen ) {
		this.endScreen = endScreen ;
	}
	
	public void updateLogic() {
		
    	if( InputUtility.isMouseClickedTriggered() ) {
			Object object = endScreen.getObjectAtPos(InputUtility.getMouseX(), InputUtility.getMouseY()) ; 
			if( object != null ) {
				if( object instanceof MouseActionable ) {
					((MouseActionable) object).clickAction(InputUtility.getMouseX(), InputUtility.getMouseY()); 
				}
			}
		}	
	}
}
