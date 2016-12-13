package gameScreen;

import core.NegativeValueException;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import screen.GameScreen;
import util.Constants;
import util.Resources;

public class GameStatus implements ScreenObject {

	private int remainingTime , score , combo ;
	private boolean isPause , isVisible ;
	private GameScreen gameScreen ;
	
	public GameStatus( GameScreen gameScreen ) {
		this.setGameScreen(gameScreen);
		this.remainingTime = Constants.MAX_REMAINING_TIME ; 
		this.isPause = false ;
		this.isVisible = true ;
		clearCombo();  
		clearScore(); 
	}

	public int getScore() {
		return score ;
	}
	
	public void clearScore() {
		this.score = 0 ;
	}
	// to control upper and lower bound of time easy to implement other method about time
	// thread safe 
	public synchronized void addRemainingTime( int addRemainingTime ) throws NegativeValueException {
		this.remainingTime += addRemainingTime ;
		if( this.remainingTime < 0 ) this.remainingTime = 0 ;
		if( this.remainingTime > Constants.MAX_REMAINING_TIME ) this.remainingTime = Constants.MAX_REMAINING_TIME ;
		
		if( this.remainingTime < 0 ) throw new NegativeValueException( "addRemainingTime" );  
		return ;
	}
	
	public void increaseRemainingTime( int increaseRemainingTime ) throws NegativeValueException  {
		try {
			addRemainingTime(increaseRemainingTime); 
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "increaseRemainingTime" , negEx );
		}
		return ;
	}
	
	public void decreaseRemainingTime( int decreaseRemainingTime ) throws NegativeValueException {
		try {
			addRemainingTime(-decreaseRemainingTime); 
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "decreaseRemainingTime" , negEx );
		}
		return ;
	}
	
	public void setRemainingTime( int remainingTime ) throws NegativeValueException {
		try {
			addRemainingTime( -getRemainingTime() + remainingTime );		
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "setRemainingTime" , negEx );
		}
		return ; 
	}
	
	public void decreaseCombo( int decreaseCombo ) throws NegativeValueException {
		try {
			addCombo(-decreaseCombo);	
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "decreaseCombo" , negEx );
		}
		return ;
	}
	
	public void increaseCombo( int increaseCombo ) throws NegativeValueException {
		try {
			addCombo(increaseCombo);	
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "decreaseCombo" , negEx );
		}
		return ;
	}
	
	public void clearCombo() throws NegativeValueException {
		try {
			addCombo(-getCombo());	
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "clearCombo" , negEx );
		}
		return ;
	}
	// to control upper and lower bound of combo  easy to implement other method about combo
	// thread safe 
	public synchronized void addCombo( int addCombo ) throws NegativeValueException {
		this.combo += addCombo ; 
		if( this.combo > Constants.MAX_COMBO ) this.combo = Constants.MAX_COMBO ; 
		if( this.combo < 0 ) this.combo = 0 ;		
		if( this.remainingTime < 0 ) throw new NegativeValueException( "addCombo" );  
		return ;
	}
	
	public void increaseScore( int increaseScore ) {
		this.score += increaseScore ;
	}
	
	public int getRemainingTime() {
		return remainingTime ;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Constants.DEFAULT_Z_GAME_STATUS ;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill( Color.BLACK ); 
		gc.setGlobalAlpha( 0.5 );
		gc.fillRoundRect( Constants.GRID_CELL_MARGIN.getWidth()+10 ,  80 , 60*(Constants.DEFAULT_GRID_SIZE.getWidth()-22)/60 , 20 , 10 , 10 );
		gc.fillRoundRect( Constants.GRID_CELL_MARGIN.getWidth()+10 , 110 , 60*(Constants.DEFAULT_GRID_SIZE.getWidth()-22)/60 , 10 , 10 , 10 );

		gc.setGlobalAlpha( 0.2 );
		gc.fillRoundRect(Constants.GRID_CELL_MARGIN.getWidth()-2 , 25 , Constants.DEFAULT_GRID_SIZE.getWidth()+4 , 110 , 10 , 10 );
		gc.setGlobalAlpha( 1 );
		gc.setFill( Color.BEIGE );
		gc.setFont( Resources.getInstance().scoreFont );
		gc.fillText( "Score : " + Integer.toString( score ) , Constants.GRID_CELL_MARGIN.getWidth()+10 , 60 );
		gc.setFont( Font.getDefault() );
		gc.setFill( Color.CRIMSON );	
		gc.fillRoundRect( Constants.GRID_CELL_MARGIN.getWidth()+10 , 80 , getRemainingTime()*(Constants.DEFAULT_GRID_SIZE.getWidth()-22)/60 , 20 , 10 , 10);
		gc.setGlobalAlpha(1);
		gc.setFill( Color.HONEYDEW );
		gc.fillRoundRect( Constants.GRID_CELL_MARGIN.getWidth()+10 , 110 , getCombo()*(Constants.DEFAULT_GRID_SIZE.getWidth()-22)/60 , 10 , 10 , 10 );
//		gc.fillText( "Combo : " + Integer.toString( this.combo ) , 120, 30);
//		gc.fillText( "Time  : " + Integer.toString( this.remainingTime ) , 200, 30);

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible ;
	}

	public boolean isPause() {
		return isPause ;
	}

	public void pause() {
//		InputUtility.postUpdate();
		gameScreen.getPauseButton().setVisible( false );
		this.isPause = true ;
	}
	
	public void unpause() {
//		InputUtility.postUpdate();
		gameScreen.getPauseButton().setVisible( true );
		this.isPause = false ;
	}

	public int getCombo() {
		return combo ;
	}

	public GameScreen getGameScreen() {
		return gameScreen ;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen ;
	}

	@Override
	public boolean isInside(int x, int y) {
		// TODO Auto-generated method stub
		return false ;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		this.isVisible = visible ;
		return ;
	}
	
}
