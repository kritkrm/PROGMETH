package core;

import javafx.scene.canvas.GraphicsContext;

public interface ScreenObejct {

	public int getZ() ; 
	public void draw( GraphicsContext gc ) ; 
	public boolean isVisible() ; 
	
}
