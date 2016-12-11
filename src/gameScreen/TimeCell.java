package gameScreen;

import core.MouseActionable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;

public class TimeCell extends Cell {

	public TimeCell(int row, int col, GridCell gridCell) {
		super(row, col, gridCell);
		// TODO Auto-generated constructor stub
	}

	public boolean equals( Object object ) {
		
		if( object instanceof TimeCell ) return true ;
		return false ;
		
	}
	
	@Override
	public void clickAction( int x , int y ) {
		// TODO Auto-generated method stub
		gridCell.getGameScreen().getGameStatus().setRemainingTime( -120 );
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
		Color color = Color.WHITE ;		
		gc.setFill( color );
		gc.fillRect( Constants.GRID_CELL_MARGIN.getWidth() + (col-1) * Constants.CELL_SIZE + col , Constants.GRID_CELL_MARGIN.getHeight() + (row-1) * Constants.CELL_SIZE + row , Constants.CELL_SIZE , Constants.CELL_SIZE );		
		gc.restore();
			
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
		return false;
	}

}
