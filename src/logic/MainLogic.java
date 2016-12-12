package logic;

import Screen.MainScreen;
import core.MouseActionable;
import util.InputUtility;

public class MainLogic {
	
	private MainScreen mainScreen ; 
	
	public MainLogic( MainScreen mainScreen ) {
		this.mainScreen = mainScreen ;
	}
	
	public void updateLogic( int step ) {
		
		if( step > 120 ) {
			mainScreen.getExitButton().setVisible(true);
	    	if( InputUtility.isMouseClickedTriggered() ) {
				Object object = mainScreen.getObjectAtPos(InputUtility.getMouseX(), InputUtility.getMouseY()) ; 
				if( object != null ) {
					if( object instanceof MouseActionable ) {
						((MouseActionable) object).clickAction(InputUtility.getMouseX(), InputUtility.getMouseY()); 
					}
				}
			}
		} else if( step > 80 ) {
			mainScreen.getAboutButton().setVisible(true);
		} else if( step > 40 ) {
			mainScreen.getPlayButton().setVisible(true);
		} else {
			mainScreen.getTitleGame().setVisible(true);
		}
	}
	
}