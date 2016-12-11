package endScreen;

import aboutScreen.HomeButton;
import core.Screen;
import core.ScreenObject;
import gameScreen.GameScreenObjectHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import util.Resources;

public class EndScreen extends Screen {

	private EndLogic endLogic ;
	private HomeButton homeButton ;
	private RetryButton retryButton ;
	
	public EndScreen() {
		super( new StackPane() ) ;
		EndScreenObjectHolder.getInstance().getEntities().clear();
		endLogic = new EndLogic(this) ;
		this.homeButton = new HomeButton(190, 440);
		this.retryButton = new RetryButton(480 ,440);

		EndScreenObjectHolder.getInstance().add( homeButton );
		EndScreenObjectHolder.getInstance().add( retryButton );
		// TODO Auto-generated constructor stub
	}

	public HomeButton getHomeButton() {
		return homeButton;
	}


	public void setHomeButton(HomeButton homeButton) {
		this.homeButton = homeButton;
	}


	public RetryButton getRetryButton() {
		return retryButton;
	}


	public void setRetryButton(RetryButton retryButton) {
		this.retryButton = retryButton;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		endLogic.updateLogic();
	}

	@Override
	public void drawComponenet() {
		// TODO Auto-generated method stub
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.restore();
		gc.drawImage(Resources.getInstance().endScreen,0,0);
		for(ScreenObject renderable : EndScreenObjectHolder.getInstance().getEntities() ) {
			renderable.draw(gc);
		}
	}

	public Object getObjectAtPos( int x , int y ) {
		int currentObjectZ = -1 ; 
		Object currentObject = null ;
		for(ScreenObject renderable : EndScreenObjectHolder.getInstance().getEntities() ) {
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
