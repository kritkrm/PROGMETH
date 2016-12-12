package core;

import javafx.scene.canvas.GraphicsContext;

public interface ScreenObject {

	public int getZ() ; 
	public boolean isInside( int x , int y );
	public void draw( GraphicsContext gc ) ; 
	public boolean isVisible() ;
	public void setVisible( boolean visible ) ;
	
}
