package util;

import javafx.geometry.Dimension2D;

public final class Constants {

	public static final String GAME_NAME = "Long Story" ; 
		
	public static int MAX_FRAME_PER_SECOND = 60 ;
	
	public static int CELL_SIZE = 40 ;
	
	public static int CELL_PER_ROW = 13 ;
	public static int CELL_PER_COL = 10 ;
	
	public static int COMBO_THRESHOLD = 1000 ;
	
	public static int MAX_REMAINING_TIME = 60 ;
	public static int MAX_COMBO = 60 ;
	public static int MAX_ITEM_IN_GRID = 7 ;
	
	public static int EVENT_MAKER_SLEEP_TIME = 150 ;
	public static int GRID_SHUFFLE_THRESHOLD = 20 ;
	
	public static final Dimension2D DEFAULT_SCREEN_SIZE = new Dimension2D( 800 , 600  ) ;
	
	public static final Dimension2D GRID_CELL_MARGIN = new Dimension2D( 40 , 140  ) ;
	public static final Dimension2D RESUME_BUTTON_MARGIN = new Dimension2D( 100 , 140  ) ;

	public static final Dimension2D DEFAULT_GRID_SIZE = new Dimension2D( CELL_PER_ROW * ( CELL_SIZE + 1 ) + 1 , CELL_PER_COL * ( CELL_SIZE + 1 ) + 1 ) ; 
	public static final Dimension2D DEFAULT_POPUP_SIZE = new Dimension2D( 500 , 400 ) ; 

	public static final Dimension2D DEFAULT_BUTTON_SIZE = new Dimension2D( 180 , 72 ) ; 
	public static final Dimension2D DEFAULT_BUTTON_EXPAND = new Dimension2D( 2 , 1 ) ;

	
//	public static final Dimension2D DEFAULT_BUTTON_SIZE = new Dimension2D( 200 , 80 ) ; 
//	public static final Dimension2D DEFAULT_BUTTON_EXPAND = new Dimension2D( 10 , 5 ) ;
	
	public static final Dimension2D DEFAULT_LITTLE_BUTTON_EXPAND = new Dimension2D( 2 , 2 ) ; 
	public static final Dimension2D DEFAULT_LITTLE_BUTTON_SIZE = new Dimension2D( 40 , 45 ) ; 	
	
	public static final Dimension2D DEFAULT_MEDIUM_BUTTON_EXPAND = new Dimension2D( 3 , 3 ) ; 
	public static final Dimension2D DEFAULT_MEDIUM_BUTTON_SIZE = new Dimension2D( 66 , 70 ) ; 	
	
	public static final int DEFAULT_Z_CELL = 4 ;
	public static final int DEFAULT_Z_GAME_STATUS = 3 ;
	public static final int DEFAULT_Z_GRID = 4 ;

	public enum CellColor {
		RED , BLUE , GREEN , YELLOW , PURPLE ; 
		public static CellColor getRandom() {
	        return values()[ (int)(Math.random() * values().length) ];
	    }
	}
	
	public enum CellItem {
		BOTTLE , DIAMOND , TIME ; 
		public static CellItem getRandom() {
	        return values()[ (int)(Math.random() * values().length) ];
	    }
	}

}
