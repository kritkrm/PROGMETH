package mainScreen;

import core.ScreenManager;
import gameScreen.Button;
import javafx.scene.canvas.GraphicsContext;
import util.InputUtility;
import util.Resources;

public class PlayButton extends Button {
	
	private boolean isVisible;
	private double count=0;
	
	public PlayButton(int x, int y){
		super(x,y);
		this.isVisible=false;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	@Override
	public int getZ() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public void clickAction( int x , int y ) {
		// TODO Auto-generated method stub
		Resources.getInstance().clickButton.play();
		ScreenManager.getInstance().newGameScreen();
		ScreenManager.getInstance().setNextScreen( ScreenManager.getInstance().getGameScreen() ) ; 

	}

	@Override
	public boolean isInside(int xx, int yy) {
		// TODO Auto-generated method stub
		if( xx < x) return false ;
		if( xx > x+200) return false ; 
	
		if( yy < y) return false ; 
		if( yy > y+77) return false ;
		
		return true;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
			
		if(isVisible){
			gc.setGlobalAlpha(count);
			count+=0.05;
			if(isInside(InputUtility.getMouseX(),InputUtility.getMouseY())){
				gc.drawImage(Resources.getInstance().playbutton2, x-10, y-10,220,85);
			}
			else{
				gc.drawImage(Resources.getInstance().playbutton, x, y,200,77);
			}
			gc.setGlobalAlpha(1);
		}
	}
}
