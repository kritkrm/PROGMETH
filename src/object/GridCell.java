package object;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import screen.GameScreen;
import util.Constants;
import util.InputUtility;
import util.Constants.CellColor;

public class GridCell implements ScreenObject {
	
	private Cell grid[][] ; 
	private int maxCol , maxRow , z ;
	private boolean isVisible ; 
	private GameScreen gameScreen ;
	private ArrayList<Cell> extraAddCell ;  
	
	public GridCell( GameScreen gameScreen ) {
		this.gameScreen = gameScreen ;
		maxCol = Constants.CELL_PER_ROW ;
		maxRow = Constants.CELL_PER_COL ; 
		grid = new Cell[ maxRow+1 ][ maxCol+1 ] ;
		isVisible = true ;
		extraAddCell = new ArrayList<Cell>() ;
		generateGrid();
		GameScreenObjectHolder.getInstance().add( this );		
	}
	
	public GameScreen getGameScreen() {
		return this.gameScreen ;
	}
	
	public Cell getCellAtIndex( int r , int c ) {
		return grid[r][c] ; 
	}
	
	public void addExtraAddCell( Cell cell ) {
		extraAddCell.add( cell ) ;
		return ;
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
		
		for( int i=1 ; i<=maxRow ; i++ ) {
			for( int j=1 ; j<=maxCol ; j++ ) {
				if( grid[i][j].isInside( x , y ) ) {
					return grid[i][j] ; 
				}
				
			}
		}
		
		return null ; 
		
	}
	
	public void generateGrid() {
		for( int i=1 ; i<=maxRow ; i++ ) {
			for( int j=1 ; j<=maxCol ; j++ ) {
				if( grid[i][j] == null )
					grid[i][j] = new ColorCell( i , j , CellColor.getRandom() , this ) ;
				if( grid[i][j].isDestroyed() ) {
					grid[i][j] = new ColorCell( i , j , CellColor.getRandom() , this ) ;
				}
			}
		}
		return ;
		
	}
	
	public boolean isSameType( Cell a , Cell b ) {
		if( !( a instanceof ColorCell ) ) return false ;
		if( !( b instanceof ColorCell ) ) return false ;
 		return (((ColorCell)a).getCellColor() == ((ColorCell)b).getCellColor() );
	}

	public ArrayList<ColorCell> getNeighborOf( ColorCell cell ) {
		
		ArrayList<ColorCell> neighbor = new ArrayList<ColorCell>() ;
		ArrayList<Cell> queue = new ArrayList<Cell>();
		 		 
		int[][] isVisited = new int[ Constants.CELL_PER_COL + 1 ][ Constants.CELL_PER_ROW + 1 ] ;  
		 
		for( int r=1; r<=maxRow ; r++ ) {
			for( int c=1; c<=maxCol ; c++ ) {
				isVisited[r][c] = 0 ;
			}
		 }
		 
		 if( !cell.isDestroyed() ) {
			 queue.add( cell ) ; 
			 isVisited[ cell.getRow() ][ cell.getCol() ] = 1 ;
		 }
		 
		 while( !queue.isEmpty() ) {
			 int cellC = queue.get( 0 ).getCol() ; 
			 int cellR = queue.get( 0 ).getRow() ; 
			 neighbor.add( (ColorCell) queue.get( 0 ) ) ;
			 queue.remove( 0 ) ; 
			 
			 if( cellC-1 >= 1 && isVisited[cellR][cellC-1] == 0 ) {
				 if( !grid[cellR][cellC-1].isDestroyed() && isSameType( grid[cellR][cellC] , grid[cellR][cellC-1] ) ) {
					 queue.add( grid[cellR][cellC-1] ) ; 
					 isVisited[cellR][cellC-1] = 1 ;
				 }
			 }
			 
			 if( cellR-1 >= 1 && isVisited[cellR-1][cellC] == 0) {
				 if( !grid[cellR-1][cellC].isDestroyed() && isSameType( grid[cellR][cellC] , grid[cellR-1][cellC] ) ) {
					 queue.add( grid[cellR-1][cellC] ) ; 
					 isVisited[cellR-1][cellC] = 1 ;
				 }
			 }
			 
			 if( cellC+1 <= maxCol && isVisited[cellR][cellC+1] == 0 ) {
				 if( !grid[cellR][cellC+1].isDestroyed() && isSameType( grid[cellR][cellC] , grid[cellR][cellC+1] ) ) {
					 queue.add( grid[cellR][cellC+1] ) ; 
					 isVisited[cellR][cellC+1] = 1 ;
				 }
			 }
			 
			 if( cellR+1 <= maxRow && isVisited[cellR+1][cellC] == 0 ) {
				 if( !grid[cellR+1][cellC].isDestroyed() && isSameType( grid[cellR][cellC] , grid[cellR+1][cellC] ) ) {
					 queue.add( grid[cellR+1][cellC] ) ; 
					 isVisited[cellR+1][cellC] = 1 ;
				 }
			 }
		 }
		 
		 System.out.println( queue.size()); 
		 
		 return neighbor ;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for( int i=1 ; i<=maxRow ; i++ ) {
			for( int j=1 ; j<=maxCol ; j++ ) {
				if( !grid[i][j].isDestroyed() ) {
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
		
		for( Cell cell : extraAddCell ) {
			grid[cell.getRow()][cell.getCol()] = cell ;
		}
		
		extraAddCell.clear();
		
	}
	
}