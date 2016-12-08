package mainScreen;

import core.MouseActionable;
import javafx.scene.canvas.GraphicsContext;
import object.ScreenObject;
import util.InputUtility;
import util.Resources;

public class ScoreButton implements ScreenObject, MouseActionable{
	private int x,y;
	public ScoreButton(int x, int y){
		this.x=x;
		this.y=y;
		MainScreenObjectHolder.getInstance().add( this );
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void clickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInside(int xx, int yy) {
		if( xx < x) return false ;
		if( xx > x+184) return false ; 
	
		if( yy < y) return false ; 
		if( yy > y+71) return false ;
		
		return true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(x>=375){
//			gc.setFill(Color.ALICEBLUE);
//			gc.fillRect(x, y, 200, 80);
			gc.drawImage(Resources.getInstance().Score, x, y);
			x-=1;
		}
		if(isInside(InputUtility.getMouseX(),InputUtility.getMouseY())){
			//draw something
		}
	}
}
