package screen;

import button.AboutButton;
import button.ExitButton;
import button.PlayButton;
import core.Screen;
import core.ScreenObject;
import gameScreen.GameTitle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.MainLogic;
import objectHolder.MainScreenObjectHolder;
import util.Constants;
import util.Resources;

public class MainScreen extends Screen {
	
	private PlayButton playButton ;
	private ExitButton exitButton ;
	private AboutButton aboutButton ;
	private GameTitle gameTitle ;
	private MainLogic mainLogic;
	private int step;
	
	public MainScreen( Canvas canvas ) {
		
		super( canvas ) ;
		MainScreenObjectHolder.getInstance().getEntities().clear();
		mainLogic = new MainLogic(this);

		aboutButton = new AboutButton( 60 , 420 );
		playButton  = new PlayButton( 310 , 440 );
		exitButton  = new ExitButton( 560 , 420 );
		gameTitle   = new GameTitle( 170 , 50 );

		MainScreenObjectHolder.getInstance().add( gameTitle );
		MainScreenObjectHolder.getInstance().add( playButton );
		MainScreenObjectHolder.getInstance().add( exitButton );
		MainScreenObjectHolder.getInstance().add( aboutButton );
		// set default center diamond picture
		MainScreenObjectHolder.getInstance().setCenterDiamond( 0 );
		
	}

	public MainLogic getMainLogic() {
		return mainLogic;
	}

	public void setMainLogic(MainLogic mainLogic) {
		this.mainLogic = mainLogic;
	}

	@Override
	public void drawComponenet() {
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.setFill(Color.WHITE);
		gc.clearRect( 0 , 0 , canvas.getWidth() , canvas.getHeight() );
		gc.fillRect ( 0 , 0 , canvas.getWidth() , canvas.getHeight() );
		
		gc.drawImage(Resources.getInstance().mainScreen, 0 , 0 , Constants.DEFAULT_SCREEN_SIZE.getWidth() , Constants.DEFAULT_SCREEN_SIZE.getHeight() );
		
		for(ScreenObject renderable : MainScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isVisible() ) 
				renderable.draw(gc);
		}	
		
	}
	
	
	public void drawCenterDiamond() {
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		if( MainScreenObjectHolder.getInstance().getCenterDiamond() == 0 ) {
			gc.drawImage( Resources.getInstance().playCell, 280, 170);
		} else if( MainScreenObjectHolder.getInstance().getCenterDiamond() == 1 ){
			gc.drawImage( Resources.getInstance().aboutCell, 270, 150);
		} else {
			gc.drawImage( Resources.getInstance().exitCell, 265, 155);
		}
		
	}
	
	public Object getObjectAtPos( int x , int y ) {
		int currentObjectZ = -1 ; 
		Object currentObject = null ;
		for(ScreenObject renderable : MainScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isInside(x, y) && renderable.isVisible() ) {
				if( currentObjectZ < renderable.getZ() ) {
					currentObject = renderable ; 
				}				
			}
		}
		return currentObject;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		drawComponenet();		
		mainLogic.updateLogic();	
		if( step > 60 ) {	
			drawCenterDiamond();
			exitButton.setVisible(true);
		} else {
			step += 1 ;
			if( step > 40 ) 
				aboutButton.setVisible(true);
			else if( step > 20 )
				playButton.setVisible(true);
			else 
				gameTitle.setVisible(true);
		}

	}

	@Override
	public void active() {
		// TODO Auto-generated method stub
		isActive = true ;
		step = 0 ;	
		if( !Resources.getInstance().soundMainScreen.isPlaying() )
			Resources.getInstance().soundMainScreen.play();
	}

	@Override
	public void inactive() {
		// TODO Auto-generated method stub
		isActive = false ;
		for(ScreenObject renderable : MainScreenObjectHolder.getInstance().getEntities() ) {
			renderable.setVisible(false); 
		}	
		if( Resources.getInstance().soundMainScreen.isPlaying() )
			Resources.getInstance().soundMainScreen.stop(); 
	}
	
}

