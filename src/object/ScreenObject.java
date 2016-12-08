package object;

import javafx.scene.canvas.GraphicsContext;

public interface ScreenObject {

	public int getZ() ; 
	public void draw( GraphicsContext gc ) ; 
	public boolean isVisible() ; 
	
}
