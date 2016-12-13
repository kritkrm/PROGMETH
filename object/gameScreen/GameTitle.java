package gameScreen;

import core.Button;
import javafx.scene.canvas.GraphicsContext;
import util.Resources;

public class GameTitle extends Button {
	
	private boolean isVisible;
	public GameTitle(int x, int y){
		super( x , y );
		this.isVisible = false ;
		//this.count=0;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible ;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE ;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible ;
	}

	@Override
	public void clickAction(int x , int y) {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public boolean isInside(int posX , int posY ) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(Resources.getInstance().LongStory, x, y , 480 , 72);
	}
}
