package pauseScreen;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import object.ScreenObject;
import screen.Screen;
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
		if( isActive ) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.WHITE);
			gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.restore();
			gc.drawImage(Resources.getInstance().pauseScreen,0,0);
			for(ScreenObject renderable :  pauseBox) {
				renderable.draw(gc);
			}
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		
	}
	
}

