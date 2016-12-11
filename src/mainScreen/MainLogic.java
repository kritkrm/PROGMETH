package mainScreen;

import core.MouseActionable;
import object.Cell;
import screen.GameScreen;
import util.InputUtility;
import util.Resources;

public class MainLogic {
	
	private MainScreen mainScreen ; 
	
	public MainLogic( MainScreen mainScreen ) {
		this.mainScreen = mainScreen ;
	}
	
	public void updateLogic(int time) {
	    if(time >160){
	    	mainScreen.getAboutButton().setVisible(true);
	    }
		else if(time > 110){
			mainScreen.getExitButton().setVisible(true);
			mainScreen.getPlayButton().setVisible(true);
			if(mainScreen.getPlayButton().getY()>220){
				mainScreen.getPlayButton().setY(mainScreen.getPlayButton().getY()-1);
				mainScreen.getExitButton().setY(mainScreen.getExitButton().getY()-1);
			}
		}
		else if(time > 40 ){
			mainScreen.getTitleGame().setVisible(true);
			if(mainScreen.getTitleGame().getX()>=60 && time >70)   mainScreen.getTitleGame().setX(mainScreen.getTitleGame().getX()-2);
			else if(mainScreen.getTitleGame().getX()<=80 && time <70)   mainScreen.getTitleGame().setX(mainScreen.getTitleGame().getX()+3);   	
		}
	    
	    if( mainScreen.isActive() ) {
			if( InputUtility.isMouseClickedTriggered() ) {
				Object object = mainScreen.getObjectAtPos(InputUtility.getMouseX(), InputUtility.getMouseY()) ; 
				if( object != null ) {
					if( object instanceof MouseActionable ) {
						((MouseActionable) object).clickAction(InputUtility.getMouseX(), InputUtility.getMouseY()); 
					}
				}
			}	
		}
	    
	}
	
}