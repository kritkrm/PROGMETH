package gridObject;

import java.awt.PageAttributes.ColorType;
import java.util.ArrayList;

import core.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import util.Constants;
import util.Resources;
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
	
	public boolean equals( Object object ) {
		if( object instanceof ColorCell ) {
			if( cellColor == ((ColorCell)object).getCellColor() )
				return true ;
			return false ;
		}
		return false;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		Image image = getCellImage( cellColor ) ;
		gc.drawImage( image , Constants.GRID_CELL_MARGIN.getWidth() + (col-1) * Constants.CELL_SIZE + col, Constants.GRID_CELL_MARGIN.getHeight() + (row-1) * Constants.CELL_SIZE + row , Constants.CELL_SIZE ,Constants.CELL_SIZE  );
		return ;
	}

	public void setCellColor( CellColor cellColor ) {
		this.cellColor = cellColor ; 
		return ; 
	}
	
	public Image getCellImage( CellColor cellColor  ) {
		switch ( cellColor ) {
			case RED     : return Resources.getInstance().redCell    ;
			case BLUE    : return Resources.getInstance().blueCell   ; 
			case GREEN   : return Resources.getInstance().greenCell  ; 
			case PURPLE  : return Resources.getInstance().purpleCell ; 
			case YELLOW  : return Resources.getInstance().yellowCell ;
		}
		return null;
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
	public void clickAction( int x , int y ) {
		// TODO Auto-generated method stub
		ArrayList<ColorCell> neighborCell = gridCell.getNeighborOf(this) ;
		if( neighborCell.size() >= 3 ) {
			for( ColorCell i : neighborCell ) {
				i.destroy(); 
				gridCell.update();
			}
			gridCell.getGameScreen().getGameStatus().increaseScore( neighborCell.size()*((gridCell.getGameScreen().getGameStatus().getCombo()>>4)|1));
			gridCell.getGameScreen().getGameStatus().increaseCombo( ( (gridCell.getGameScreen().getGameStatus().getCombo()>>3)+2)*(neighborCell.size()>>1) );
		
		} else {
			Resources.getInstance().clickBox.play();
			gridCell.getGameScreen().getGameStatus().clearCombo();
			gridCell.getGameScreen().getGameStatus().decreaseRemainingTime(4);
			
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

}
