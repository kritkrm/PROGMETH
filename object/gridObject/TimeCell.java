package gridObject;

import core.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;
import util.Resources;

public class TimeCell extends Cell {

	public TimeCell(int row, int col, GridCell gridCell) {
		super(row, col, gridCell);		
		isVisible = true ;
		// TODO Auto-generated constructor stub
	}

	public boolean equals( Object object ) {
		
		if( object instanceof TimeCell ) return true ;
		return false ;
		
	}
	
	@Override
	public void clickAction( int x , int y ) {
		// TODO Auto-generated method stub
		gridCell.getGameScreen().getGameStatus().increaseRemainingTime( 2 ); 
		this.destroy(); 
		gridCell.update();
	}

	@Override
	public boolean isInside(int x, int y) {
		// TODO Auto-generated method stub
		if( x < Constants.GRID_CELL_MARGIN.getWidth() + (col-1) * Constants.CELL_SIZE + col ) return false ;
		if( x > Constants.GRID_CELL_MARGIN.getWidth() + (col-1) * Constants.CELL_SIZE + col + Constants.CELL_SIZE ) return false ; 
	
		if( y < Constants.GRID_CELL_MARGIN.getHeight() + (row-1) * Constants.CELL_SIZE + row ) return false ; 
		if( y > Constants.GRID_CELL_MARGIN.getHeight() + (row-1) * Constants.CELL_SIZE + row + Constants.CELL_SIZE ) return false ;
		
		return true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage( Resources.getInstance().timeCell , Constants.GRID_CELL_MARGIN.getWidth() + (col-1) * Constants.CELL_SIZE + col, Constants.GRID_CELL_MARGIN.getHeight() + (row-1) * Constants.CELL_SIZE + row , Constants.CELL_SIZE ,Constants.CELL_SIZE  );
		return ;
		
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		isVisible = visible;
	}

}
