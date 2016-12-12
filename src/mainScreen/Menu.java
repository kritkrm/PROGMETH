package mainScreen;

import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;

public class Menu implements ScreenObject {

	private int x , y ;
	
	public Menu( int x, int y ){
		this.x = x;
		this.y = y;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE-1;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(x>=320){
//			gc.setFill(Color.ALICEBLUE);
//			gc.fillRect(x, y, 300, 350);
//			gc.drawImage(Resources.getInstance().me, x, y);
			x-=1;
		}
	}

	@Override
	public boolean isInside(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}
}
