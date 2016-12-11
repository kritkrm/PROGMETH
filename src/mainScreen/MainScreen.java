package mainScreen;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import object.Button;
import object.ScreenObject;
import screen.Screen;
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
		this.mainLogic = new MainLogic(this);
		this.playButton = new PlayButton(120,240);
		this.exitButton = new ExitButton(145,360);
		this.aboutButton = new AboutButton(600, 500);
		this.titleGame = new TitleGame(0,70);
		time=0;
		
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
	public void drawComponenet(){
		if(!Resources.getInstance().soundMainScreen.isPlaying())	Resources.getInstance().soundMainScreen.play();
		if( isActive ) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.WHITE);
			gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.restore();
			gc.drawImage(Resources.getInstance().mainScreen,0,0);
			for(ScreenObject renderable : MainScreenObjectHolder.getInstance().getEntities() ) {
				renderable.draw(gc);
			}
		}
	}
	
	public Object getObjectAtPos( int x , int y ) {
		int currentObjectZ = -1 ; 
		Object currentObject = null ;
		for(ScreenObject renderable : MainScreenObjectHolder.getInstance().getEntities() ) {
			if( renderable.isInside(x, y) ) {
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
		time+=1;
		mainLogic.updateLogic( time );
		drawComponenet();
		
	}
	
}

