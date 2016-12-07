package core;

import javafx.scene.canvas.GraphicsContext;

public abstract class Stage {

	public abstract void draw( GraphicsContext gc , int stageWidth , int stageHeight );
	public abstract void update() ;
	
}
