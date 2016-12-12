package aboutScreen;

import core.Screen;
import core.ScreenObject;
import gameScreen.LittleHomeButton;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import util.Constants;
import util.Resources;

public class AboutScreen extends Screen {

	private LittleHomeButton homeButton ;
	private AboutLogic aboutLogic ; 
	
	public AboutScreen() {
		super( new StackPane() ) ;
		AboutScreenObjectHolder.getInstance().getEntities().clear();
		aboutLogic = new AboutLogic( this ) ; 
		homeButton = new LittleHomeButton( 705 , 520 );
		AboutScreenObjectHolder.getInstance().add( homeButton );
	}
	
	public LittleHomeButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(LittleHomeButton homeButton) {
		this.homeButton = homeButton;
	}

	@Override
	public void drawComponenet(){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.restore();
		gc.drawImage(Resources.getInstance().aboutScreen,0,0,Constants.DEFAULT_SCREEN_SIZE.getWidth(),Constants.DEFAULT_SCREEN_SIZE.getHeight());
		for(ScreenObject renderable : AboutScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isVisible() ) 
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
			if( renderable.isInside(x, y) && renderable.isVisible() ) {
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

