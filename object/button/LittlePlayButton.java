package button;

import core.Button;
import core.ScreenManager;
import javafx.scene.canvas.GraphicsContext;
import util.Constants;
import util.InputUtility;
import util.Resources;

public class LittlePlayButton extends Button {
	
	public LittlePlayButton(int x, int y) {
		super( x , y );
		setVisible(true);
	}

	@Override
	public boolean isInside(int posX , int posY ) {
		// TODO Auto-generated method stub
		if( posX < this.x ) return false ;
		if( posX > this.x+Constants.DEFAULT_MEDIUM_BUTTON_SIZE.getWidth()  ) return false ; 
	
		if( posY < this.y ) return false ; 
		if( posY > this.y+Constants.DEFAULT_MEDIUM_BUTTON_SIZE.getHeight()  ) return false ;
		
		return true;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
			
		gc.setGlobalAlpha((double)step / 20.0 );
		if( step < 20 ) step += 1 ;
		if( isInside( InputUtility.getMouseX(),InputUtility.getMouseY() ) ) {
			gc.drawImage( Resources.getInstance().littlePlayButton , 
						  x - Constants.DEFAULT_MEDIUM_BUTTON_EXPAND.getWidth() , 
						  y - Constants.DEFAULT_MEDIUM_BUTTON_EXPAND.getHeight() ,
						  Constants.DEFAULT_MEDIUM_BUTTON_SIZE.getWidth() + ( Constants.DEFAULT_MEDIUM_BUTTON_EXPAND.getWidth()*2 ) , 
						  Constants.DEFAULT_MEDIUM_BUTTON_SIZE.getHeight() + (Constants.DEFAULT_MEDIUM_BUTTON_EXPAND.getHeight()*2) ) ;
		} else{
			gc.drawImage(Resources.getInstance().littlePlayButton, x, y, Constants.DEFAULT_MEDIUM_BUTTON_SIZE.getWidth() , Constants.DEFAULT_MEDIUM_BUTTON_SIZE.getHeight() );
		}
		gc.setGlobalAlpha(1);
		
	}
	
	@Override
	public void clickAction(int x, int y) {
		// TODO Auto-generated method stub
		Resources.getInstance().clickButton.play();
		ScreenManager.getInstance().getGameScreen().getGameStatus().unpause(); 
	}
}
