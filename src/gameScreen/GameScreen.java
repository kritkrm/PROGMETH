package gameScreen;

import java.awt.Container;
import java.util.List;

import core.Screen;
import core.ScreenManager;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.Constants;
import util.Resources;

public class GameScreen extends Screen {
	
	private GridCell gridCell ; 
	private GameStatus gameStatus ; 
	private GameLogic gameLogic ; 
	private int frameCount ;
	private int pauseStep ; 
	private int endScore ;

	public GameScreen() {
		super( new StackPane() ) ; 
		GameScreenObjectHolder.getInstance().getEntities().clear();
		this.gridCell = new GridCell( this ) ;
		this.gameStatus = new GameStatus( this ) ;
		this.gameLogic = new GameLogic( this ) ; 
		createPausePopUp() ;
		createEndPopUp() ;
		GameScreenObjectHolder.getInstance().add( gridCell );		
		GameScreenObjectHolder.getInstance().add( gameStatus );		
		frameCount = 0 ;
		pauseStep = 0 ; 
		endScore = 0 ;
		gameStatus.unpause();
//		gameStatus.increaseScore( 1850 );
		
	}
	
	public void createPausePopUp() {
		
		PausePopUpObjectHolder.getInstance().getEntities().clear();
		LittleHomeButton littleHomeButton = new LittleHomeButton( (int)((Constants.DEFAULT_SCREEN_SIZE.getWidth()/2)+100-Constants.DEFAULT_LITTLE_BUTTON_SIZE.getWidth()) , 300 ) ;
		LittleRetryButton littleRetryButton = new LittleRetryButton( (int)(Constants.DEFAULT_SCREEN_SIZE.getWidth()/2)-100, 300 ) ;
		LittlePlayButton littlePlayButton = new LittlePlayButton( (int)(Constants.DEFAULT_SCREEN_SIZE.getWidth()-Constants.DEFAULT_MEDIUM_BUTTON_SIZE.getWidth())/2 , 300 ) ;
		PausePopUpObjectHolder.getInstance().add( littleHomeButton );
		PausePopUpObjectHolder.getInstance().add( littleRetryButton );
		PausePopUpObjectHolder.getInstance().add( littlePlayButton );

	}
	
	public void createEndPopUp() {
		EndPopUpObjectHolder.getInstance().getEntities().clear();
		LittleHomeButton littleHomeButton = new LittleHomeButton( (int)((Constants.DEFAULT_SCREEN_SIZE.getWidth()/2)+50-Constants.DEFAULT_LITTLE_BUTTON_SIZE.getWidth()) , 330 ) ;
		LittleRetryButton littleRetryButton = new LittleRetryButton( (int)(Constants.DEFAULT_SCREEN_SIZE.getWidth()/2)-50, 330 ) ;
		EndPopUpObjectHolder.getInstance().add( littleHomeButton );
		EndPopUpObjectHolder.getInstance().add( littleRetryButton );
	}
	
	@Override
	public void drawComponenet(){
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.restore(); 
		gc.drawImage(Resources.getInstance().gameScreen,0,0 , Constants.DEFAULT_SCREEN_SIZE.getWidth() , Constants.DEFAULT_SCREEN_SIZE.getHeight() );
		gc.setGlobalAlpha( 0.5 );
		gc.setFill(Color.WHITE);
		gc.fillRect ( Constants.GRID_CELL_MARGIN.getWidth()-2, Constants.GRID_CELL_MARGIN.getHeight()-2 , Constants.DEFAULT_GRID_SIZE.getWidth()+4 ,  Constants.DEFAULT_GRID_SIZE.getHeight()+4 );
		gc.setGlobalAlpha( 1 );
		for(ScreenObject renderable : GameScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isVisible() ) 
				renderable.draw(gc);
		}
		
	}
	
	public void drawPopUpBG( int step ) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill( Color.LIGHTGOLDENRODYELLOW );
//		gc.fillRoundRect(x, y, w, h, arcWidth, arcHeight);
		gc.setGlobalAlpha( 0.9 );
		gc.fillRoundRect( Constants.DEFAULT_SCREEN_SIZE.getWidth()/2 - 200 , Constants.DEFAULT_SCREEN_SIZE.getHeight()/2 - 2*step , 400 , 4*step , 10 , 10 );
		gc.setGlobalAlpha( 1 );
	}
	
	public void drawPausePopUP() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for(ScreenObject renderable : PausePopUpObjectHolder.getInstance().getEntities() ) {
			if( renderable.isVisible() ) {
				renderable.draw(gc);
			}
		}
		gc.setFill(Color.BLACK);
		gc.setFont( Resources.getInstance().pauseFont );
		gc.fillText( " GAME IS PAUSE ", 240, 260);
		gc.setFont( Font.getDefault() );
	}
	
	public void drawEndPopUP() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for(ScreenObject renderable : EndPopUpObjectHolder.getInstance().getEntities() ) {
			if( renderable.isVisible() ) {
				renderable.draw(gc);
			}
		}
		gc.setFill(Color.BLACK);
		gc.setFont( Resources.getInstance().pauseFont );
		gc.fillText( " GAME OVER ", 280, 260);
		gc.setFont( Resources.getInstance().scoreFont );
		gc.setFill( Color.CADETBLUE );
		gc.fillText( "SCORE : ", 300, 310);
		gc.fillText( Integer.toString( endScore ), 420, 310);
		gc.setFont( Font.getDefault() );
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		if( gameStatus.isPause() ) { 
			drawPopUpBG( pauseStep );
			if( pauseStep < 50 ) {
				pauseStep += 1 ;
			} else {
				gameLogic.updateLogic(); 
				if( gameStatus.getRemainingTime() == 0 ) { 
					
					endScore += ((int)(gameStatus.getScore()-endScore)/20)+1 ;
					if( endScore > gameStatus.getScore() ) endScore = gameStatus.getScore() ;
					drawEndPopUP() ;
				} else {
					drawPausePopUP() ;
				}
			}
		} else {
			if( pauseStep > 0 ) { 
				pauseStep -= 1 ;
				drawPopUpBG(pauseStep);
			} else {
				frameCount++;
				if( frameCount == Constants.MAX_FRAME_PER_SECOND ) {
					frameCount = 0 ; 
					gameStatus.decreaseRemainingTime( 1 );
					gameStatus.decreaseCombo( (gameStatus.getCombo()/6)+1);
				}
				if( gameStatus.getRemainingTime() > 0 ) {
					gameLogic.updateLogic(); 
				} else {
					gameStatus.pause();
				}
			}
		}
		
	}
	
	public Object getObjectAtPos( int x , int y ) {
		
		int currentObjectZ = -1 ; 
		Object currentObject = null ;
		
		List<ScreenObject> entitiesList ;
		
		if( gameStatus.isPause() ) {
			if( gameStatus.getRemainingTime() > 0 ) 
				entitiesList = PausePopUpObjectHolder.getInstance().getEntities() ;
			else 
				entitiesList = EndPopUpObjectHolder.getInstance().getEntities() ;
		} else {
			entitiesList = GameScreenObjectHolder.getInstance().getEntities() ;
		}
		for(ScreenObject renderable : entitiesList ) {
			if( renderable.isInside(x, y) && renderable.isVisible() ) {
				if( currentObjectZ < renderable.getZ() ) {
					currentObject = renderable ; 
				}				
			}
		}
		
		return currentObject;
	}
	
	public GridCell getGridCell() {
		return gridCell ;
	}
	
	public GameStatus getGameStatus() {
		return gameStatus ; 
	}
	
	public GameLogic getGameLogic() {
		return gameLogic ; 
	}

	@Override
	public void active() {
		// TODO Auto-generated method stub
		isActive = true ;
		if( !Resources.getInstance().soundGameScreen.isPlaying() )
			Resources.getInstance().soundGameScreen.play();
	}

	@Override
	public void inactive() {
		// TODO Auto-generated method stub
		isActive = false ;
		if( Resources.getInstance().soundGameScreen.isPlaying() )
			Resources.getInstance().soundGameScreen.stop();
	}
	
	
}

