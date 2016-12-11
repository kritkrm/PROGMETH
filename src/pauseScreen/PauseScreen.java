package pauseScreen;


import java.util.ArrayList;
import java.util.List;

import aboutScreen.AboutLogic;
import aboutScreen.AboutScreenObjectHolder;
import core.Screen;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import mainScreen.MainScreenObjectHolder;
import util.Resources;

public class PauseScreen extends Screen {

	private NoButton noButton;
	private ResumeButton resumeButton;
	private PauseLogic pauseLogic ;
	public PauseScreen() {
		super( new StackPane() ) ;
		PauseScreenObjectHolder.getInstance().getEntities().clear();
		pauseLogic = new PauseLogic( this ) ; 
		this.noButton = new NoButton(480, 390);
		this.resumeButton = new ResumeButton(190 ,390);
		PauseScreenObjectHolder.getInstance().add(this.noButton);
		PauseScreenObjectHolder.getInstance().add(this.resumeButton);
	}
	
	public NoButton getNoButton() {
		return noButton;
	}

	public void setNoButton(NoButton noButton) {
		this.noButton = noButton;
	}

	public ResumeButton getResumeButton() {
		return resumeButton;
	}

	public void setResumeButton(ResumeButton resumeButton) {
		this.resumeButton = resumeButton;
	}

	@Override
	public void drawComponenet(){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.restore();
		gc.drawImage(Resources.getInstance().pauseScreen,0,0);
		for(ScreenObject renderable : PauseScreenObjectHolder.getInstance().getEntities() ) {
			renderable.draw(gc);
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		pauseLogic.updateLogic();
	}

	@Override
	public Object getObjectAtPos( int x , int y ) {
		int currentObjectZ = -1 ; 
		Object currentObject = null ;
		for(ScreenObject renderable : PauseScreenObjectHolder.getInstance().getEntities() ) {
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
		isActive = true;
	}

	@Override
	public void inactive() {
		// TODO Auto-generated method stub
		isActive = false;
	}
}

