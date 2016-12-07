package core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;

public abstract class Cell implements IRenderable , MouseActionable {

	protected int row , col ; 
	private boolean isDestroyed ;
	private boolean isVisible ;
	private GridCell gridCell ; 
	
	public Cell ( int row , int col , GridCell gridCell ) {
		this.row = row ; 
		this.col = col ; 
		this.isDestroyed = false ;
		this.isVisible = true ;
		this.gridCell = gridCell ;
	}
	
	public GridCell getGridCell() {
		return gridCell ;
	}

	public int getRow() {
		return row ;
	}
	
	public int getCol() {
		return col ; 
	}

	public boolean isDestroyed() {
		return isDestroyed ; 
	}
	
	public void destroy() {
		isDestroyed = true ; 
		return ;
	}
	
	public abstract int getZ() ; 
	public abstract boolean isVisible();
	
}
