package button;

import core.Button;
import core.ScreenManager;
import javafx.scene.canvas.GraphicsContext;
import objectHolder.MainScreenObjectHolder;
import util.Constants;
import util.InputUtility;
import util.Resources;

public class PlayButton extends Button {
	
	public PlayButton(int x, int y){
		super( x , y );
		setVisible( false );
	}
	
	@Override
	public void clickAction( int x , int y ) {
		// TODO Auto-generated method stub
		Resources.getInstance().clickButton.play();
		ScreenManager.getInstance().newGameScreen();
		ScreenManager.getInstance().setNextScreen( ScreenManager.getInstance().getGameScreen() ) ; 

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
			
		if( isVisible ){ 
			gc.setGlobalAlpha( (double)step / 15 );
			if( step < 15 ) step += 1 ;
			if( isInside( InputUtility.getMouseX(),InputUtility.getMouseY() ) ) {
				MainScreenObjectHolder.getInstance().setCenterDiamond(0);
				gc.drawImage( Resources.getInstance().playbutton2 , 
							  x - Constants.DEFAULT_BUTTON_EXPAND.getWidth() , 
							  y - Constants.DEFAULT_BUTTON_EXPAND.getHeight() ,
							  Constants.DEFAULT_BUTTON_SIZE.getWidth() + ( Constants.DEFAULT_BUTTON_EXPAND.getWidth()*2 ) , 
							  Constants.DEFAULT_BUTTON_SIZE.getHeight() + (Constants.DEFAULT_BUTTON_EXPAND.getHeight()*2) );
			} else {
				gc.drawImage(Resources.getInstance().playbutton, x, y, Constants.DEFAULT_BUTTON_SIZE.getWidth() , Constants.DEFAULT_BUTTON_SIZE.getHeight() );
			}
			gc.setGlobalAlpha(1);
		}
	}
}
