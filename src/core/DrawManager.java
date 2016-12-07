package core;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public final class DrawManager {

	private static DrawManager instance = new DrawManager() ; 
	
	public static DrawManager getInstance() {
		return instance ; 
	}
	
	private Canvas canvas ;
	private GraphicsContext gc ; 
	 
	private int canvasWidth ;
	private int canvasHeight ; 
	private boolean isCanvasSizeSet = false ; 
	
	protected void setCanvasSize( final int width , final int height ) { 
		isCanvasSizeSet = true ; 
		 
		this.canvasWidth = width ;
		this.canvasHeight = height ;
	}
	
	protected boolean isCanvasSizeSet() {
		return isCanvasSizeSet ; 
	}
	
	protected int getCanvasHeight() {
		return canvasHeight;
	}
	
	protected int getCanvasWidth() {
		return canvasWidth;
	}
	
	
}
