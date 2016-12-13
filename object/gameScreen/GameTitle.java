package gameScreen;

import core.Button;
import javafx.scene.canvas.GraphicsContext;
import util.Constants;
import util.Resources;

public class GameTitle extends Button {
	
	public GameTitle(int x, int y){
		super( x , y );
		setVisible(false);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(Resources.getInstance().LongStory, x, y , 480 , 72);
	}

	@Override
	public boolean isInside(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clickAction(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
}
