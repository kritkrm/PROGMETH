package button;

import core.Button;
import core.ScreenManager;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import util.Constants;
import util.InputUtility;
import util.Resources;

public class ExitButton extends Button {
	private boolean isVisible;
	private int step ;
	
	public ExitButton(int x, int y) {
		super( x , y ) ;
		this.isVisible = false;
		step = 0 ;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		if( !isVisible ) step = 0 ;
	}
	@Override
	public int getZ() {
		return 4;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public boolean isInside(int posX , int posY ) {
		// TODO Auto-generated method stub
		if( posX < this.x ) return false ;
		if( posX > this.x+Constants.DEFAULT_BUTTON_SIZE.getWidth()  ) return false ; 
	
		if( posY < this.y ) return false ; 
		if( posY > this.y+Constants.DEFAULT_BUTTON_SIZE.getHeight()  ) return false ;
		
		return true;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if( isVisible ) {
			gc.setGlobalAlpha((double)step / 15.0 );
			if( step < 15 ) step += 1 ;
			if( isInside( InputUtility.getMouseX(),InputUtility.getMouseY() ) ) {
				gc.drawImage( Resources.getInstance().exitbutton2 , 
							  x - Constants.DEFAULT_BUTTON_EXPAND.getWidth() , 
							  y - Constants.DEFAULT_BUTTON_EXPAND.getHeight() ,
							  Constants.DEFAULT_BUTTON_SIZE.getWidth() + ( Constants.DEFAULT_BUTTON_EXPAND.getWidth()*2 ) , 
							  Constants.DEFAULT_BUTTON_SIZE.getHeight() + (Constants.DEFAULT_BUTTON_EXPAND.getHeight()*2) ) ;
			} else{
				gc.drawImage(Resources.getInstance().exitbutton, x, y, Constants.DEFAULT_BUTTON_SIZE.getWidth() , Constants.DEFAULT_BUTTON_SIZE.getHeight() );
			}
			gc.setGlobalAlpha(1);
		}
	}
	@Override
	public void clickAction(int x, int y) {
		// TODO Auto-generated method stubclickButton
		Platform.exit();
	}
}
