package gameScreen;

import core.MouseActionable;
import core.ScreenObject;
import util.Resources;

public abstract class Cell implements ScreenObject , MouseActionable {

	protected int row , col ; 
	private boolean isDestroyed ;
	protected boolean isVisible ;
	protected GridCell gridCell ; 
	
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
	
	public void setRow( int row ) {
		this.row = row ;
		return ;
	}
	
	public int getCol() {
		return col ; 
	}
	
	public void setCol( int col ) {
		this.col = col ; 
		return ; 
	}
	
	public void setAlive() {
		isDestroyed = true ; 
	}
	
	public boolean isDestroyed() {
		return isDestroyed ; 
	}
	
	public void destroy() {
		Resources.getInstance().boom.play();
		isDestroyed = true ; 
		return ;
	}
	
	public abstract int getZ() ; 
	public abstract boolean isVisible();
	
}
