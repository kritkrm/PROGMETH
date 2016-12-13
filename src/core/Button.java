package core;

import util.Constants;

public abstract class Button implements ScreenObject , MouseActionable {
		
	protected int x , y , step ; 
	protected boolean isVisible ;
	
	public Button( int x , int y ) {
		this.x = x ;
		this.y = y ;
		this.step = 0 ;
	}
	
	public int getX() {
		return this.x ;
	}
	
	public int getY() {
		return this.y ;
	}
	
	public void setX( int x ) {
		this.x = x ;
		return ;
	}
	
	public void setY( int y ) {
		this.y = y ;
		return ;
	}

	public int getZ() {
		// TODO Auto-generated method stub
		return Constants.DEFAULT_Z_BUTTON;
	}

	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible ;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible ;
		if( !isVisible ) step = 0 ;
	}
	
}
