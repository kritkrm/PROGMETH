package Screen;

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
import objectHolder.PausePopUpObjectHolder;
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

		aboutButton = new AboutButton(50,380);
		playButton = new PlayButton(300,420);
		exitButton = new ExitButton(550,380);
		gameTitle = new GameTitle( 400-240 ,50);

		MainScreenObjectHolder.getInstance().add( playButton );
		MainScreenObjectHolder.getInstance().add( exitButton );
		MainScreenObjectHolder.getInstance().add( aboutButton );
		MainScreenObjectHolder.getInstance().add( gameTitle );
		
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
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.drawImage(Resources.getInstance().mainScreen, 0 , 0 , Constants.DEFAULT_SCREEN_SIZE.getWidth() , Constants.DEFAULT_SCREEN_SIZE.getHeight() );
		
		for(ScreenObject renderable : MainScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isVisible() ) 
				renderable.draw(gc);
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
		
		if( step > 100 ) {
			mainLogic.updateLogic();				
			exitButton.setVisible(true);
		} else {
			step += 1 ;
			if( step > 50 ) 
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

