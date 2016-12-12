package gridObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Screen.GameScreen;
import core.Cell;
import core.MouseActionable;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;
import util.InputUtility;
import util.Constants.CellColor;

public class GridCell implements ScreenObject , MouseActionable {
	
	private Cell grid[][] ; 
	private int maxCol , maxRow ;
	private boolean isVisible ; 
	private GameScreen gameScreen ;

	private List<Cell> extraAddCell ;
	
	public GridCell( GameScreen gameScreen ) {
		this.gameScreen = gameScreen ;
		maxCol = Constants.CELL_PER_ROW ;
		maxRow = Constants.CELL_PER_COL ; 
		grid = new Cell[ maxRow+1 ][ maxCol+1 ] ;
		isVisible = true ;
		extraAddCell = Collections.synchronizedList(new ArrayList<Cell>());
		generateGrid();
	}
	
	public void addExtraAddCell( Cell cell ) {
		extraAddCell.add( cell ) ;
		return ;
	}
	
	public void shuffle() {
		for( int r=1 ; r<=maxRow ; r++ ) {
			for( int c=1 ; c<=maxCol ; c++ ) {
				if ( grid[r][c] != null && grid[r][c] instanceof ColorCell ) {
					grid[r][c].destroy();
				}
			}
		}
		generateGrid(); 
		return ;
	}
	
	public int countItemCell() {
		int itemCount = 0 ;
		for( int r=1 ; r<=maxRow ; r++ ) {
			for( int c=1 ; c<=maxCol ; c++ ) {
				if( grid[r][c] != null && !grid[r][c].isDestroyed() && ! (grid[r][c] instanceof ColorCell) ) 
					itemCount++ ;
			}
		}
		return itemCount ;
	}
	
	public ArrayList<Cell> getCellInRow( int row ) {
		
		ArrayList<Cell> CellInRow = new ArrayList<Cell>() ;
		if( row >= 1 && row <= maxRow ){
			for( int i=1; i<=maxCol; i++ ) {
				CellInRow.add( grid[row][i] ) ;
			}
		}
		return CellInRow ; 
	}
	
	public ArrayList<Cell> getCellInCol( int col ) {
		
		ArrayList<Cell> CellInCol = new ArrayList<Cell>() ;
		if( col >= 1 && col <= maxCol ){
			for( int i=1; i<=maxRow; i++ ) {
				CellInCol.add( grid[i][col] ) ;
			}
		}
		return CellInCol ; 
	}
	
	public Cell getCellAtPos( int x , int y ) {
		
		for( int r=1 ; r<=maxRow ; r++ ) {
			for( int c=1 ; c<=maxCol ; c++ ) {
				if( grid[r][c].isInside( x , y ) ) {
					return grid[r][c] ; 
				}
			}
		}
		return null ; 
	}
	
	public void generateGrid() {
		for( int r=1 ; r<=maxRow ; r++ ) {
			for( int c=1 ; c<=maxCol ; c++ ) {
				if( grid[r][c] == null || grid[r][c].isDestroyed() )
					grid[r][c] = new ColorCell( r , c , CellColor.getRandom() , this ) ;
			}
		}
		return ;
	}
	
	public int countClickCell() {
		int clickCell = 0 ;
		for( int r=1 ; r<=maxRow ; r++ ) {
			for( int c=1 ; c<=maxCol ; c++ ) {
				if( grid[r][c] instanceof ColorCell ) {
					if( getNeighborOf( (ColorCell)grid[r][c] ).size() >= 3 )
						clickCell += 1;
				} else {
					clickCell += (1<<2) ; 
				}
			}
		}
		return clickCell ;
	}

	public ArrayList<ColorCell> getNeighborOf( ColorCell cell ) {
		
		ArrayList<ColorCell> neighbor = new ArrayList<ColorCell>() ;
		ArrayList<Cell> queue = new ArrayList<Cell>();
		 		 
		boolean[][] isVisited = new boolean[ Constants.CELL_PER_COL + 1 ][ Constants.CELL_PER_ROW + 1 ] ;  
		 
		for( int r=1; r<=maxRow ; r++ ) {
			for( int c=1; c<=maxCol ; c++ ) {
				isVisited[r][c] = false ;
			}
		 }
		 
		 if( !cell.isDestroyed() ) {
			 queue.add( cell ) ; 
			 isVisited[ cell.getRow() ][ cell.getCol() ] = true ;
		 }
		 
		 while( !queue.isEmpty() ) {
			 int cellC = queue.get( 0 ).getCol() ; 
			 int cellR = queue.get( 0 ).getRow() ; 
			 neighbor.add( (ColorCell) queue.get( 0 ) ) ;
			 queue.remove( 0 ) ; 
			 
			 if( cellC-1 >= 1 && !isVisited[cellR][cellC-1] ) {
				 if( !grid[cellR][cellC-1].isDestroyed() && grid[cellR][cellC].equals(grid[cellR][cellC-1] ) ) {
					 queue.add( grid[cellR][cellC-1] ) ; 
					 isVisited[cellR][cellC-1] = true ;
				 }
			 }
			 
			 if( cellR-1 >= 1 && !isVisited[cellR-1][cellC] ) {
				 if( !grid[cellR-1][cellC].isDestroyed() && grid[cellR][cellC].equals(grid[cellR-1][cellC] ) ) {
					 queue.add( grid[cellR-1][cellC] ) ; 
					 isVisited[cellR-1][cellC] = true ;
				 }
			 }
			 
			 if( cellC+1 <= maxCol && !isVisited[cellR][cellC+1] ) {
				 if( !grid[cellR][cellC+1].isDestroyed() && grid[cellR][cellC].equals(grid[cellR][cellC+1] ) ) {
					 queue.add( grid[cellR][cellC+1] ) ; 
					 isVisited[cellR][cellC+1] = true ;
				 }
			 }
			 
			 if( cellR+1 <= maxRow && !isVisited[cellR+1][cellC] ) {
				 if( !grid[cellR+1][cellC].isDestroyed() && grid[cellR][cellC].equals(grid[cellR+1][cellC] ) ) {
					 queue.add( grid[cellR+1][cellC] ) ; 
					 isVisited[cellR+1][cellC] = true ;
				 }
			 }
		 }

		 return neighbor ;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Constants.DEFAULT_Z_GRID;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for( int i=1 ; i<=maxRow ; i++ ) {
			for( int j=1 ; j<=maxCol ; j++ ) {
				if( !grid[i][j].isDestroyed() && grid[i][j].isVisible() ) {
					grid[i][j].draw( gc );
					if( grid[i][j].isInside( InputUtility.getMouseX() , InputUtility.getMouseY() ) ) {
						gc.setGlobalAlpha( 0.7 );
						gc.setFill( Color.WHEAT );
						gc.fillRect( Constants.GRID_CELL_MARGIN.getWidth() + (j-1) * Constants.CELL_SIZE + j , Constants.GRID_CELL_MARGIN.getHeight() + (i-1) * Constants.CELL_SIZE + i , Constants.CELL_SIZE , Constants.CELL_SIZE );		
						gc.setGlobalAlpha( 1 );
					}
				}
			}
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	public void updateIndex() {
		for( int r=1 ; r<=maxRow ; r++ ) {
			for( int c=1; c<=maxCol ; c++ ) {
				grid[r][c].setCol( c );
				grid[r][c].setRow( r );
			}
		}	
	}
	
	public void update() {
		for( int c=1 ; c<=maxCol ; c++ ) {
			for( int r=maxRow; r>=1 ; r-- ) {
				if( grid[r][c].isDestroyed() ) {
					grid[0][c] = grid[r][c] ; 
					for( int i=r; i>=1 ; i-- ) {
						grid[i][c] = grid[i-1][c] ; 
					}
				}
			}
		}
		updateIndex();
		generateGrid(); 
		
		synchronized( extraAddCell ){
			Iterator<Cell> iterator = extraAddCell.iterator();
			while (iterator.hasNext()) {
				Cell cell = iterator.next() ; 
				grid[cell.getRow()][cell.getCol()] = cell ;
			}
		}
		extraAddCell.clear();
		
	}

	@Override
	public boolean isInside(int x, int y) {
		// TODO Auto-generated method stub
		if( x < Constants.GRID_CELL_MARGIN.getWidth() + 1 ) return false ;
		if( x > Constants.GRID_CELL_MARGIN.getWidth() + (maxCol) * Constants.CELL_SIZE + maxCol ) return false ; 
	
		if( y < Constants.GRID_CELL_MARGIN.getHeight() + 1 ) return false ; 
		if( y > Constants.GRID_CELL_MARGIN.getHeight() + (maxRow) * Constants.CELL_SIZE + maxCol ) return false ;
		return true;
	}

	@Override
	public void clickAction(int x, int y) {
		// TODO Auto-generated method stub
		Cell cell = getCellAtPos( x , y ) ;
		if( cell != null && !cell.isDestroyed() )
			cell.clickAction( x , y );
	}
	
	public GameScreen getGameScreen() {
		return this.gameScreen ;
	}
	
	public Cell getCellAtIndex( int r , int c ) {
		return grid[r][c] ; 
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		isVisible = visible ;
	}
	
}
