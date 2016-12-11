package aboutScreen;


import java.util.ArrayList;
import java.util.List;

import core.Screen;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import mainScreen.MainScreenObjectHolder;
import util.Resources;

public class AboutScreen extends Screen {

	private HomeButton homeButton ;
	private AboutLogic aboutLogic ; 
	
	public AboutScreen() {
		super( new StackPane() ) ;
		AboutScreenObjectHolder.getInstance().getEntities().clear();
		homeButton = new HomeButton(650, 480);
		aboutLogic = new AboutLogic(this) ;
		AboutScreenObjectHolder.getInstance().add( homeButton );
	}
	
	public HomeButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(HomeButton homeButton) {
		this.homeButton = homeButton;
	}

	@Override
	public void drawComponenet(){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.restore();
		gc.drawImage(Resources.getInstance().aboutScreen,0,0);
		for(ScreenObject renderable : AboutScreenObjectHolder.getInstance().getEntities() ) {
			renderable.draw(gc);
		}
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		aboutLogic.updateLogic();
	}

	@Override
	public Object getObjectAtPos( int x , int y ) {
		int currentObjectZ = -1 ; 
		Object currentObject = null ;
		for(ScreenObject renderable : AboutScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isInside(x, y) ) {
				if( currentObjectZ < renderable.getZ() ) {
					currentObject = renderable ; 
				}				
			}
		}
		return currentObject;
	}
	
}

