package gameScreen;

import core.NegativeValueException;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Constants;

public class GameStatus implements ScreenObject {

	private int remainingTime ;
	private int score ; 
	private boolean isPause ;
	private int combo ; 
	private GameScreen gameScreen ;
	
	public GameStatus( GameScreen gameScreen ) {
		this.setGameScreen(gameScreen) ;
		this.remainingTime = Constants.MAX_REMAINING_TIME ; 
		this.isPause = false ;
		clearCombo();  
		clearScore(); 
	}

	public int getScore() {
		return score;
	}
	
	public void clearScore() {
		this.score = 0;
	}
	
	public synchronized void addRemainingTime( int addRemainingTime ) throws NegativeValueException {
		this.remainingTime += addRemainingTime ;
		if( this.remainingTime < 0 ) this.remainingTime = 0 ;
		if( this.remainingTime > Constants.MAX_REMAINING_TIME ) this.remainingTime = Constants.MAX_REMAINING_TIME ;
		
		if( this.remainingTime < 0 ) throw new NegativeValueException( "addRemainingTime" ) ;  
		return ;
	}
	
	public void increaseRemainingTime( int increaseRemainingTime ) throws NegativeValueException  {
		try {
			addRemainingTime(increaseRemainingTime); 
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "increaseRemainingTime" , negEx ) ;
		}
		return ;
	}
	
	public void decreaseRemainingTime( int decreaseRemainingTime ) throws NegativeValueException {
		try {
			addRemainingTime(-decreaseRemainingTime); 
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "decreaseRemainingTime" , negEx ) ;
		}
		return ;
	}
	
	public void setRemainingTime( int remainingTime ) throws NegativeValueException {
		try {
			addRemainingTime( -getRemainingTime() + remainingTime );		
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "setRemainingTime" , negEx ) ;
		}
		return ; 
	}
	
	public void decreaseCombo( int decreaseCombo ) throws NegativeValueException {
		try {
			addCombo(-decreaseCombo);	
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "decreaseCombo" , negEx ) ;
		}
		return ;
	}
	
	public void increaseCombo( int increaseCombo ) throws NegativeValueException {
		try {
			addCombo(increaseCombo);	
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "decreaseCombo" , negEx ) ;
		}
		return ;
	}
	
	public void clearCombo() throws NegativeValueException {
		try {
			addCombo(-getCombo());	
		} catch ( NegativeValueException negEx ) {
			throw new NegativeValueException( "clearCombo" , negEx ) ;
		}
		return ;
	}
	
	public synchronized void addCombo( int addCombo ) throws NegativeValueException {
		this.combo += addCombo ; 
		if( this.combo > Constants.MAX_COMBO ) this.combo = Constants.MAX_COMBO ; 
		if( this.combo < 0 ) this.combo = 0 ;		
		if( this.remainingTime < 0 ) throw new NegativeValueException( "addCombo" ) ;  
		return ;
	}
	
	public void increaseScore( int increaseScore ) {
		this.score += increaseScore ;
	}
	
	public int getRemainingTime() {
		return remainingTime;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill( Color.BLUEVIOLET);
		gc.fillText( "Score : " + Integer.toString( this.score ) , 30, 30);
		gc.fillText( "Combo : " + Integer.toString( this.combo ) , 120, 30);
		gc.fillText( "Time  : " + Integer.toString( this.remainingTime ) , 200, 30);

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPause() {
		return isPause;
	}

	public void togglePause() {
		this.isPause = !this.isPause;
	}

	public int getCombo() {
		return combo;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	@Override
	public boolean isInside(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
