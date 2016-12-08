package gameScreen;

import core.ScreenObejct;
import javafx.scene.canvas.GraphicsContext;
import util.Constants;

public class GameStatus implements ScreenObejct {

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
		GameScreenObejctHoloder.getInstance().add( this );
	}

	public int getScore() {
		return score;
	}
	
	public synchronized void clearScore() {
		this.score = 0;
	}
	
	public synchronized void increaseScore( int increaseScore ) {
		this.score += increaseScore ;
	}
	
	public synchronized void setRemainingTime( int remainingTime ) {
		this.remainingTime = remainingTime ;  
		return ; 
	}
	
	public int getRemainingTime() {
		return remainingTime;
	}
	
	public synchronized void decreaseRemainingTime(int amount) {
		remainingTime -= amount ;
		if( remainingTime < 0 ) remainingTime = 0 ; 
	}

	public synchronized void increaseRemainingTime(int amount) {
		remainingTime += amount ;
		if( remainingTime > Constants.MAX_REMAINING_TIME ) remainingTime = Constants.MAX_REMAINING_TIME ; 
	}
	
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.fillText( "Score : " + Integer.toString( this.score ) , 30, 30);
		gc.fillText( "Combo : " + Integer.toString( this.combo ) , 120, 30);
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPause() {
		return isPause;
	}

	public synchronized void togglePause() {
		this.isPause = !this.isPause;
	}

	public int getCombo() {
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
