package aboutScreen;

import core.Screen;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import util.Constants;
import util.Resources;

public class AboutScreen extends Screen {

	private HomeButton homeButton ;
	private AboutLogic aboutLogic ; 
	
	public AboutScreen() {
		super( new StackPane() ) ;
		AboutScreenObjectHolder.getInstance().getEntities().clear();
		aboutLogic = new AboutLogic( this ) ; 
		homeButton = new HomeButton(600, 500);
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
//		if( step < 1000 ) {
//			step += 1 ; 
//			drawIntro() ;
//		} else {
			drawComponenet();
			aboutLogic.updateLogic(); 
//		}
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

	@Override
	public void active() {
		// TODO Auto-generated method stub
		isActive = true ;
	}

	@Override
	public void inactive() {
		// TODO Auto-generated method stub
		isActive = false ;
	}
	
}

