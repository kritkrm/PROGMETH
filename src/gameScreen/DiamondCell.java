package gameScreen;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;
import util.Resources;

public class DiamondCell extends Cell {

	public DiamondCell(int row, int col, GridCell gridCell) {
		super(row, col, gridCell);
		isVisible = true ;
		// TODO Auto-generated constructor stub
	}
	
	public boolean equals( Object object ) {
		
		if( object instanceof DiamondCell ) return true ;
		return false ;
		
	}
	
	@Override
	public void clickAction( int x , int y ) {
		// TODO Auto-generated method stub
		Random random = new Random(); 
		ArrayList<Cell> cellInLine ;
		if( random.nextInt( 10000 )%2 == 0 ) {
			 cellInLine = gridCell.getCellInRow( this.getRow() ) ;
		} else {
			 cellInLine = gridCell.getCellInCol( this.getCol() ) ;
		}

		for( Cell i : cellInLine ) {
			if( i instanceof ColorCell ) {
				i.destroy(); 
				gridCell.update();
				gridCell.getGameScreen().getGameStatus().increaseScore(1);
			}
		}
		
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
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage( Resources.getInstance().diamondCell , Constants.GRID_CELL_MARGIN.getWidth() + (col-1) * Constants.CELL_SIZE + col, Constants.GRID_CELL_MARGIN.getHeight() + (row-1) * Constants.CELL_SIZE + row , Constants.CELL_SIZE ,Constants.CELL_SIZE  );
		
		return ;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		this.isVisible = visible ; 
	}

}
