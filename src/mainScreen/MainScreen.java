package mainScreen;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import object.ScreenObject;
import screen.Screen;

public class MainScreen extends Screen {
	int x,y;
	private PlayButton playButton ; 
	private ScoreButton scoreButton ; 
	private Menu menu ; 
	
	public MainScreen() {
		super( new StackPane() ) ; 
		this.menu = new Menu(450, 100);
		this.playButton = new PlayButton(505, 190);
		this.scoreButton = new ScoreButton(505, 300);
		
	}
	
	public PlayButton getPlayButton() {
		return playButton ;
	}
	
	public ScoreButton getScoreButton() {
		return scoreButton ; 
	}
	
	
	@Override
	public void drawComponenet(){
		if( isActive ) {
			System.out.println("1");
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.WHITE);
			gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
			gc.restore(); 
			for(ScreenObject renderable : MainScreenObjectHolder.getInstance().getEntities() ) {
				System.out.println("2");
				renderable.draw(gc);
			}
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//gameLogic.updateLogic(); 
		drawComponenet();
		
	}
	
}

