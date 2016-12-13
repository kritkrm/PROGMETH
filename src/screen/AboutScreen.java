package screen;

import button.LittleHomeButton;
import core.Screen;
import core.ScreenObject;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.AboutLogic;
import objectHolder.AboutScreenObjectHolder;
import util.Constants;
import util.Resources;

public class AboutScreen extends Screen {

	private AboutLogic aboutLogic ; 

	public AboutScreen( Canvas canvas ) {
		super( canvas ) ;
		AboutScreenObjectHolder.getInstance().getEntities().clear();
		aboutLogic = new AboutLogic( this ) ; 
		LittleHomeButton homeButton = new LittleHomeButton( 705 , 520 );
		AboutScreenObjectHolder.getInstance().add( homeButton );
	}
	
	public AboutLogic getAboutLogic() {
		return aboutLogic;
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
		drawComponenet();
		aboutLogic.updateLogic(); 
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

