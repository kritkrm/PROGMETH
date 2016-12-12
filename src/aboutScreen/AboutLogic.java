package aboutScreen;

import core.MouseActionable;
import core.ScreenObject;
import util.InputUtility;

public class AboutLogic {
	
	private AboutScreen aboutScreen ; 
	
	public AboutLogic( AboutScreen aboutScreen ) {
		this.aboutScreen = aboutScreen ;
	}
	
	public void updateLogic() {
		
    	if( InputUtility.isMouseClickedTriggered() ) {
			Object object = aboutScreen.getObjectAtPos(InputUtility.getMouseX(), InputUtility.getMouseY()) ; 
			if( object != null ) {
				if( object instanceof MouseActionable ) {
					((MouseActionable) object).clickAction(InputUtility.getMouseX(), InputUtility.getMouseY()); 
				}
			}
		}	
	}
}
