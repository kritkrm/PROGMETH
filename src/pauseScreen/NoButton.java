package pauseScreen;

import gameScreen.Button;
import javafx.scene.canvas.GraphicsContext;
import util.InputUtility;
import util.Resources;

public class NoButton extends Button {
	private boolean isVisible;	
	public NoButton(int x, int y){
		super(x,y);
		this.isVisible=true;
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
	public boolean isInside(int xx, int yy) {
		// TODO Auto-generated method stub
		if( xx < x) return false ;
		if( xx > x+121) return false ; 
	
		if( yy < y) return false ; 
		if( yy > y+70) return false ;
		
		return true;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub	
		if(isVisible){
			if(isInside(InputUtility.getMouseX(),InputUtility.getMouseY())){
				gc.drawImage(Resources.getInstance().nobutton2, x-10, y-10,138,80);
			}
			else{
				gc.drawImage(Resources.getInstance().nobutton, x, y,121,70);
			}
		}
	}
	@Override
	public void clickAction(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
