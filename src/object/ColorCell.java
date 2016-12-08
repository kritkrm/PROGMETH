package object;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;
import util.Constants.CellColor;

public class ColorCell extends Cell {

	private CellColor cellColor ;
	
	public ColorCell(int row, int col, CellColor cellColor  , GridCell gridCell) {
		super(row, col , gridCell );
		// TODO Auto-generated constructor stub
		this.cellColor = cellColor ;
	}

	CellColor getCellColor() {
		return cellColor ;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		Color color = getCellColor( this ) ;		
		gc.setFill( color );
		gc.fillRect( Constants.GRID_CELL_MARGIN.getWidth() + (col-1) * Constants.CELL_SIZE + col , Constants.GRID_CELL_MARGIN.getHeight() + (row-1) * Constants.CELL_SIZE + row , Constants.CELL_SIZE , Constants.CELL_SIZE );		
		gc.restore();
			
		return ;
		
	}

	public void setCellColor( CellColor cellColor ) {
		this.cellColor = cellColor ; 
		return ; 
	}
	
	public Color getCellColor( ColorCell colorCell ) {
		switch ( colorCell.getCellColor() ) {
			case RED     : return Color.RED    ;
			case BLUE    : return Color.BLUE   ; 
			case GREEN   : return Color.GREEN  ; 
			case PURPLE  : return Color.PURPLE ; 
			case YELLOW  : return Color.YELLOW ;
		}
		return null;
	}

	@Override
	public void clickAction() {
		// TODO Auto-generated method stub
		ArrayList<ColorCell> neighborCell = gridCell.getNeighborOf(this) ;
		if( neighborCell.size() >= 3 ) {
			for( ColorCell i : neighborCell ) {
				i.destroy(); 
				gridCell.update();
			}
			gridCell.getGameScreen().getGameStatus().increaseScore( neighborCell.size() );
			gridCell.getGameScreen().getGameStatus().increaseCombo( 2 );
		
		} else {
			gridCell.getGameScreen().getGameStatus().clearCombo();
			
		} 
	
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
