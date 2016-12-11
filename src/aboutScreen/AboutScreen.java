package aboutScreen;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import object.ScreenObject;
import screen.Screen;
import util.Resources;

public class AboutScreen extends Screen {
	private HomeButton homeButton;
	public AboutScreen() {
		super( new StackPane() ) ;
		this.homeButton = new HomeButton(650, 480);
	}
	
	public HomeButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(HomeButton homeButton) {
		this.homeButton = homeButton;
	}

	@Override
	public void drawComponenet(){
		if( isActive ) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.WHITE);
			gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.restore();
			gc.drawImage(Resources.getInstance().aboutScreen,0,0);
//			for(ScreenObject renderable :  pauseBox) {
//				renderable.draw(gc);
//			}
			homeButton.draw(gc);
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		
	}
	
}

