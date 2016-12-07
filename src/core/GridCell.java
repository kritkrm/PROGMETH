package core;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;
import util.Constants.CellColor;

public class GridCell implements IRenderable {
	
	private Cell grid[][] ; 
	private int maxCol , maxRow , z ;
	private boolean isVisible ; 
	
	public GridCell() {
		
		maxCol = Constants.CELL_PER_COL ;
		maxRow = Constants.CELL_PER_ROW ; 
		grid = new Cell[ maxCol+1 ][ maxRow+1 ] ;
		isVisible = true ;
		generateGrid();
			
	}
	
	public Cell getCellAtIndex( int x , int y ) {
		return grid[x][y] ; 
	}
	
	public Cell getCellAtPos( int x , int y ) {
		
		for( int i=1 ; i<=maxCol ; i++ ) {
			for( int j=1 ; j<=maxRow ; j++ ) {
				if( grid[i][j].isInside( x , y ) ) {
					return grid[i][j] ; 
				}
				
			}
		}
		
		return null ; 
		
	}
	
	public void generateGrid() {
		for( int i=1 ; i<=maxCol ; i++ ) {
			for( int j=1 ; j<=maxRow ; j++ ) {
				grid[i][j] = new ColorCell( i , j , CellColor.getRandom() , this ) ;
			}
		}
	}
	
	public boolean isSameType( ColorCell a , ColorCell b ) {
		return a.getCellColor() == b.getCellColor() ;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for( int i=1 ; i<=maxCol ; i++ ) {
			for( int j=1 ; j<=maxRow ; j++ ) {
				grid[i][j].draw( gc );
				if( grid[i][j].isInside( InputUtility.getMouseX() , InputUtility.getMouseY() ) ) {
					gc.setGlobalAlpha( 0.7 );
					gc.setFill( Color.WHITE );
					gc.fillRect( (j-1) * Constants.CELL_SIZE + j , (i-1) * Constants.CELL_SIZE + i , Constants.CELL_SIZE , Constants.CELL_SIZE );		
					gc.setGlobalAlpha( 1 );
				}
			}
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

}
