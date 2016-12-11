package mainScreen;

import gameScreen.Button;
import javafx.scene.canvas.GraphicsContext;
import util.InputUtility;
import util.Resources;

public class ExitButton extends Button {
	private boolean isVisible;
	private double count=0;
	public ExitButton(int x, int y){
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
				gc.drawImage(Resources.getInstance().exitbutton2, x-10, y-5, 220 , 90 );
			}
			else{
				gc.drawImage(Resources.getInstance().exitbutton, x, y,200,80);
			}
			gc.setGlobalAlpha(1);
		}
	}
	@Override
	public void clickAction(int x, int y) {
		// TODO Auto-generated method stub
		Resources.getInstance().clickButton.play();
	}
}
