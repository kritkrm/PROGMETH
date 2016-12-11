package endScreen;

import aboutScreen.AboutLogic;
import aboutScreen.AboutScreenObjectHolder;
import aboutScreen.HomeButton;
import core.Screen;
import javafx.scene.layout.StackPane;

public class EndScreen extends Screen {

	private EndLogic endLogic ;
	
	public EndScreen() {
		super( new StackPane() ) ;
		EndScreenObjectHolder.getInstance().getEntities().clear();
		endLogic = new EndLogic(this) ;

//		EndScreenObjectHolder.getInstance().add( homeButton );
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		endLogic.updateLogic();
	}

	@Override
	public Object getObjectAtPos(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void drawComponenet() {
		// TODO Auto-generated method stub
		
	}

}
