package button;

import core.Button;
import core.ScreenManager;
import javafx.scene.canvas.GraphicsContext;
import objectHolder.MainScreenObjectHolder;
import util.Constants;
import util.InputUtility;
import util.Resources;

public class AboutButton extends Button {
	
	public AboutButton(int x, int y) {
		super( x , y );
		setVisible( false );
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
			gc.setGlobalAlpha( (double)step / 15.0 );
			if( step < 15 ) step += 1 ;
			if( isInside( InputUtility.getMouseX(),InputUtility.getMouseY() ) ) {
				MainScreenObjectHolder.getInstance().setCenterDiamond(1);
				gc.drawImage( Resources.getInstance().aboutbutton2 , 
							  x - Constants.DEFAULT_BUTTON_EXPAND.getWidth() , 
							  y - Constants.DEFAULT_BUTTON_EXPAND.getHeight() ,
							  Constants.DEFAULT_BUTTON_SIZE.getWidth() + ( Constants.DEFAULT_BUTTON_EXPAND.getWidth()*2 ) , 
							  Constants.DEFAULT_BUTTON_SIZE.getHeight() + (Constants.DEFAULT_BUTTON_EXPAND.getHeight()*2) );
			} else{
				gc.drawImage(Resources.getInstance().aboutbutton, x, y, Constants.DEFAULT_BUTTON_SIZE.getWidth() , Constants.DEFAULT_BUTTON_SIZE.getHeight() );
			}
			gc.setGlobalAlpha(1);
		}
	}
	@Override
	public void clickAction(int x, int y) {
		// TODO Auto-generated method stubclickButton
		Resources.getInstance().clickButton.play();
		ScreenManager.getInstance().setNextScreen( ScreenManager.getInstance().getAboutScreen() ) ; 

	}


}
