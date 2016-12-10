package object;

import core.MouseActionable;

public abstract class Button implements ScreenObject , MouseActionable {
	
	protected int x , y ; 

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
	
}
