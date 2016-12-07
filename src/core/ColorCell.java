package core;

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
		gc.fillRect( (col-1) * Constants.CELL_SIZE + col , (row-1) * Constants.CELL_SIZE + row , Constants.CELL_SIZE , Constants.CELL_SIZE );		
		gc.restore();
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
		
	}
	
	@Override
	public void howerAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInside(int x, int y) {
		// TODO Auto-generated method stub
		if( x < (col-1) * Constants.CELL_SIZE + col ) return false ;
		if( x > (col-1) * Constants.CELL_SIZE + col + Constants.CELL_SIZE ) ; 
	
		if( y < (row-1) * Constants.CELL_SIZE + row ) return false ; 
		if( y > (row-1) * Constants.CELL_SIZE + row + Constants.CELL_SIZE ) return false ;
		
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
