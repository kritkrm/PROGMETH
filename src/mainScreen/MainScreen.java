package mainScreen;

import core.Screen;
import core.ScreenObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import util.Constants;
import util.Resources;

public class MainScreen extends Screen {
	
	private PlayButton playButton ;
	private ExitButton exitButton ;
	private AboutButton aboutButton ;
	private TitleGame titleGame ;
	private MainLogic mainLogic;
	private int time;
	
	public MainScreen() {
		
		super( new StackPane() ) ;
		MainScreenObjectHolder.getInstance().getEntities().clear();
		mainLogic = new MainLogic(this);

		this.aboutButton = new AboutButton(50,380);
		this.playButton = new PlayButton(300,420);
		this.exitButton = new ExitButton(550,380);
		this.titleGame = new TitleGame( 400-240 ,50);

		MainScreenObjectHolder.getInstance().add( playButton );
		MainScreenObjectHolder.getInstance().add( exitButton );
		MainScreenObjectHolder.getInstance().add( aboutButton );
		MainScreenObjectHolder.getInstance().add( titleGame );
		
	}
	
	public PlayButton getPlayButton() {
		return playButton ;
	}
	
	public ExitButton getExitButton() {
		return exitButton;
	}

	public AboutButton getAboutButton() {
		return aboutButton;
	}

	public TitleGame getTitleGame() {
		return titleGame;
	}

	public MainLogic getMainLogic() {
		return mainLogic;
	}

	public int getTime(){
		return time;
	}
	
	
	public void setPlayButton(PlayButton playButton) {
		this.playButton = playButton;
	}

	public void setExitButton(ExitButton exitButton) {
		this.exitButton = exitButton;
	}

	public void setAboutButton(AboutButton aboutButton) {
		this.aboutButton = aboutButton;
	}

	public void setTitleGame(TitleGame titleGame) {
		this.titleGame = titleGame;
	}

	public void setMainLogic(MainLogic mainLogic) {
		this.mainLogic = mainLogic;
	}

	public void setTime(int time) {
		this.time = time;
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
		time += 1;
		mainLogic.updateLogic( time );
		drawComponenet();
		
	}

	@Override
	public void active() {
		// TODO Auto-generated method stub
		isActive = true ;
		if( !Resources.getInstance().soundMainScreen.isPlaying() )
			Resources.getInstance().soundMainScreen.play();
	}

	@Override
	public void inactive() {
		// TODO Auto-generated method stub
		isActive = false ;
		if( Resources.getInstance().soundMainScreen.isPlaying() )
			Resources.getInstance().soundMainScreen.stop(); 
	}
	
}

