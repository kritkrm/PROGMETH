package pauseScreen;


import java.util.ArrayList;
import java.util.List;

import core.Screen;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import mainScreen.MainScreenObjectHolder;
import util.Resources;

public class PauseScreen extends Screen {
	private List<ScreenObject> pauseBox;
	private NoButton noButton;
	private ResumeButton resumeButton;
	public PauseScreen() {
		super( new StackPane() ) ;
		pauseBox = new ArrayList<ScreenObject>();
		this.noButton = new NoButton(480, 390);
		this.resumeButton = new ResumeButton(190 ,390);
		pauseBox.add(this.noButton);
		pauseBox.add(this.resumeButton);
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
		for(ScreenObject renderable :  pauseBox ) {
			renderable.draw(gc);
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		
	}

	@Override
	public Object getObjectAtPos( int x , int y ) {
		int currentObjectZ = -1 ; 
		Object currentObject = null ;
		for(ScreenObject renderable : MainScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isInside(x, y) ) {
				if( currentObjectZ < renderable.getZ() ) {
					currentObject = renderable ; 
				}				
			}
		}
		return currentObject;
	}
}

