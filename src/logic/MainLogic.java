package logic;

import core.MouseActionable;
import screen.MainScreen;
import util.InputUtility;

public class MainLogic {
	
	private MainScreen mainScreen ; 
	
	public MainLogic( MainScreen mainScreen ) {
		this.mainScreen = mainScreen ;
	}
	
	public void updateLogic() {
		if( InputUtility.isMouseLeftClicked() ) {
			InputUtility.postUpdate();
			Object object = mainScreen.getObjectAtPos(InputUtility.getMouseX(), InputUtility.getMouseY()) ; 
			if( object != null ) {
				if( object instanceof MouseActionable ) {
					((MouseActionable) object).clickAction(InputUtility.getMouseX(), InputUtility.getMouseY()); 
				}
			}
		}
		
	}
	
}