package core;

public class GameLogic {
	
	public GameLogic() {

	}
	
	public void updateLogic() {
		if( InputUtility.isMouseClickedTriggered() ) {
			Cell cell = GameScreen.getInstance().getGridCell().getCellAtPos( InputUtility.getMouseX() , InputUtility.getMouseY() ) ;
			
			if( cell != null && !cell.isDestroyed() )
				cell.clickAction();
		}	
		System.out.println( InputUtility.isMouseClickedTriggered() );
	}
	
	public void updateScreen() {
		GameScreen.getInstance().drawComponenet();
	}
}
