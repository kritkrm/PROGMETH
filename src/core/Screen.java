package core;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import util.Constants;
import util.InputUtility;

public abstract class Screen {

	protected Canvas canvas ;
	protected boolean isActive ;
	protected int step ;
	
	public Screen( Canvas canvas ) {
		this.canvas = canvas ;
		this.isActive = false ;
		step = 0 ;
		// set a step for make animation to be zero 
	}
	
	public abstract void update() ;
	
	public boolean isActive() {
		return this.isActive ; 
	}
	
	public abstract void active() ;
	
	public abstract void inactive() ;
	
	public Canvas getCanvas() {
		return canvas ; 
	}
	
	public abstract Object getObjectAtPos( int x , int y ) ;
	
	public abstract void drawComponenet() ; 

}
