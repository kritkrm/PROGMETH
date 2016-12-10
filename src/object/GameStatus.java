package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import screen.GameScreen;
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
		GameScreenObjectHolder.getInstance().add( this );
	}

	public int getScore() {
		return score;
	}
	
	public void clearScore() {
		this.score = 0;
	}
	
	public synchronized void decreaseRemainingTime( int decreaseRemainingTime ) {
		this.remainingTime -= decreaseRemainingTime ;
		if( this.remainingTime < 0 ) this.remainingTime = 0 ;
		return ;
	}
	
	public void setRemainingTime( int remainingTime ) {
		this.remainingTime = remainingTime ;
		if( this.remainingTime < 0 ) this.remainingTime = 0 ; 
		if( this.remainingTime > Constants.MAX_REMAINING_TIME ) this.remainingTime = Constants.MAX_REMAINING_TIME ;
		return ; 
	}
	
	public synchronized void decreaseCombo( int decreaseCombo ) {
		this.combo -= decreaseCombo ;
		if( this.combo < 0 ) this.combo = 0 ;
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

	public synchronized int getCombo() {
		return combo;
	}

	public synchronized void clearCombo() {
		this.combo = 0;
	}
	
	public synchronized void increaseCombo( int increaseCombo ) {
		this.combo += increaseCombo ; 
		if( this.combo > Constants.MAX_COMBO ) this.combo = Constants.MAX_COMBO ; 
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
	
}