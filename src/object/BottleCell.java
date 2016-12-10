package object;

import java.awt.Container;
import java.awt.PageAttributes.ColorType;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;
import util.Constants.CellColor;

public class BottleCell extends Cell {

	public BottleCell(int row, int col, GridCell gridCell) {
		super(row, col, gridCell);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		Color color = Color.TURQUOISE ;		
		gc.setFill( color );
		gc.fillRect( Constants.GRID_CELL_MARGIN.getWidth() + (col-1) * Constants.CELL_SIZE + col , Constants.GRID_CELL_MARGIN.getHeight() + (row-1) * Constants.CELL_SIZE + row , Constants.CELL_SIZE , Constants.CELL_SIZE );		
		gc.restore();
	}

	@Override
	public void clickAction() {
		// TODO Auto-generated method stub
		
		Random random = new Random(); 
		int maxRow = Constants.CELL_PER_COL ;
		int maxCol = Constants.CELL_PER_ROW ;
		int randomRow = random.nextInt( maxRow-2 ) + 2 ;
		int randomCol = random.nextInt( maxCol-2 ) + 2 ;
		CellColor cellColor = CellColor.getRandom() ;
		
		for( int r=randomRow-2; r<=randomRow+2; r++ ) {
			for( int c=randomCol-2; c<=randomCol+2; c++ ) {
				if( r >=1 && r <= maxRow && c >= 1 && c <=maxCol ) {
					if( ! (( r == randomRow-2 && c == randomCol-2 ) || ( r == randomRow-2 && c == randomCol+2 ) || 
						   ( r == randomRow+2 && c == randomCol-2 ) || ( r == randomRow-2 && c == randomCol+2 )) ) {
						gridCell.addExtraAddCell( new ColorCell( r , c , cellColor , gridCell ) );
					}
				}
			}
		}
		this.destroy();
		
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
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}
