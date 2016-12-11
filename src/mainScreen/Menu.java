package mainScreen;

import javafx.scene.canvas.GraphicsContext;
import object.ScreenObject;
import util.Resources;

public class Menu implements ScreenObject {
	private int x,y;
	
	public Menu(int x, int y){
		this.x=x;
		this.y=y;
		MainScreenObjectHolder.getInstance().add( this );
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
}
