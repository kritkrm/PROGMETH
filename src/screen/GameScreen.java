package screen;

import java.util.List;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import button.LittleHomeButton;
import button.LittlePauseButton;
import button.LittlePlayButton;
import button.LittleRetryButton;
import core.Screen;
import core.ScreenObject;
import gameScreen.GameStatus;
import gridObject.GridCell;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.AboutLogic;
import logic.GameLogic;
import objectHolder.AboutScreenObjectHolder;
import objectHolder.EndPopUpObjectHolder;
import objectHolder.GameScreenObjectHolder;
import objectHolder.PausePopUpObjectHolder;
import util.Constants;
import util.InputUtility;
import util.Resources;

public class GameScreen extends Screen {
	
	private GridCell gridCell ; 
	private GameStatus gameStatus ; 
	private GameLogic gameLogic ; 
	private int frameCount ;
	private int pauseStep ; 
	private int endScore ;
	private LittlePauseButton littlePauseButton ;

	public GameScreen( Canvas canvas ) {
		super( canvas ) ; 
		GameScreenObjectHolder.getInstance().getEntities().clear();

//		littlePauseButton = new LittlePauseButton( 730 , 30 );		
//		GameScreenObjectHolder.getInstance().add( littlePauseButton );
//		littlePauseButton = new LittlePauseButton( 670 , 30 );		
//		GameScreenObjectHolder.getInstance().add( littlePauseButton );

		littlePauseButton = new LittlePauseButton( 610 , 30 );
		GameScreenObjectHolder.getInstance().add( littlePauseButton );
		
		this.gridCell = new GridCell( this );
		this.gameStatus = new GameStatus( this );
		this.gameLogic = new GameLogic( this ); 
		createPausePopUp() ;
		createEndPopUp() ;
		GameScreenObjectHolder.getInstance().add( gridCell );		
		GameScreenObjectHolder.getInstance().add( gameStatus );		
		frameCount = 0 ;
		pauseStep = 0 ; 
		endScore = 0 ;
		gameStatus.pause();
		
	}
	
	public LittlePauseButton getPauseButton() {
		return littlePauseButton ;
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
		gc.drawImage(Resources.getInstance().gameScreen , 0 , 0 , Constants.DEFAULT_SCREEN_SIZE.getWidth() , Constants.DEFAULT_SCREEN_SIZE.getHeight() );
		gc.setGlobalAlpha( 0.5 );
		gc.setFill(Color.WHITE);
		gc.fillRoundRect ( Constants.GRID_CELL_MARGIN.getWidth()-2 , Constants.GRID_CELL_MARGIN.getHeight()-2 , Constants.DEFAULT_GRID_SIZE.getWidth()+4 ,  Constants.DEFAULT_GRID_SIZE.getHeight()+4 , 10 , 10 );
		gc.setGlobalAlpha( 1 );
		for(ScreenObject renderable : GameScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isVisible() ) 
				renderable.draw(gc);
		}
		
	}
	
	public void drawPopUpBG( int step ) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill( Color.LIGHTGOLDENRODYELLOW );
		gc.setGlobalAlpha( 0.9 );
		gc.fillRoundRect( Constants.DEFAULT_SCREEN_SIZE.getWidth()/2 - 200 , Constants.DEFAULT_SCREEN_SIZE.getHeight()/2 - 4*step , 400 , 8*step , 10 , 10 );
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
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_width = fontLoader.computeStringWidth("GAME IS PAUSE", gc.getFont());
		
		gc.fillText( "GAME IS PAUSE", (int)(Constants.DEFAULT_SCREEN_SIZE.getWidth()-font_width)/2, 260);
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
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_width = fontLoader.computeStringWidth("GAME OVER", gc.getFont());
		gc.fillText( "GAME OVER", (int)(Constants.DEFAULT_SCREEN_SIZE.getWidth()-font_width)/2, 260);
		
		
		gc.setFont( Resources.getInstance().scoreFont );
		gc.setFill( Color.CADETBLUE );
		font_width = fontLoader.computeStringWidth("SCORE : " + Integer.toString( gameStatus.getScore() ), gc.getFont());
		gc.fillText( "SCORE : " + Integer.toString( endScore ), (int)(Constants.DEFAULT_SCREEN_SIZE.getWidth()-font_width)/2, 310);
		gc.setFont( Font.getDefault() );
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();
		if( gameStatus.isPause() ) { 
			drawPopUpBG( pauseStep );
			if( pauseStep < 25 ) {
				pauseStep += 1 ;
				// when we doesn't update logic we must clear mouse trigger from method Inputtility.postUpdate() 
				InputUtility.postUpdate();
			} else {
				gameLogic.updateLogic(); 
				if( gameStatus.getRemainingTime() == 0 ) { 
					
					endScore += ((int)(gameStatus.getScore()-endScore)/30)+1 ;
					if( endScore > gameStatus.getScore() ) endScore = gameStatus.getScore() ;
					drawEndPopUP() ;
				} else {
					drawPausePopUP() ;
				}
			}
		} else {
			if( pauseStep > 0 ) { 
				pauseStep -= 1 ; 
				// when we doesn't update logic we must clear mouse trigger from method Inputtility.postUpdate() 
				InputUtility.postUpdate();
				drawPopUpBG(pauseStep);
			} else {
				frameCount++; 
				// count frame 
				if( frameCount == Constants.MAX_FRAME_PER_SECOND ) {
					// if frameCount reach to approximate FPS value use it for 1 sec timing
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
		gameStatus.unpause();
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

