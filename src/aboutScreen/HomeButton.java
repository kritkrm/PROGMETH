package aboutScreen;

import javafx.scene.canvas.GraphicsContext;
import object.Button;
import util.InputUtility;
import util.Resources;

public class HomeButton extends Button {
	private boolean isVisible;	
	public HomeButton(int x, int y){
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
	public void clickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInside(int xx, int yy) {
		// TODO Auto-generated method stub
		if( xx < x) return false ;
		if( xx > x+80) return false ; 
	
		if( yy < y) return false ; 
		if( yy > y+86) return false ;
		
		return true;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub	
		if(isVisible){
			if(isInside(InputUtility.getMouseX(),InputUtility.getMouseY())){
				gc.drawImage(Resources.getInstance().HomeButton1, x-5, y-5,90,97);
			}
			else{
				gc.drawImage(Resources.getInstance().HomeButton2, x, y,80,86);
			}
		}
	}
}
