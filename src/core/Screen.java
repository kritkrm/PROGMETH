package core;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import util.Constants;
import util.InputUtility;

public abstract class Screen extends Scene {

	protected Canvas canvas ;
	private StackPane stackPane ;
	protected boolean isActive ;
	protected int step ;
	
	
	public Screen( StackPane stackPane ) {
		super( stackPane ) ;
		this.canvas = new Canvas( Constants.DEFAULT_SCREEN_SIZE.getWidth() , Constants.DEFAULT_SCREEN_SIZE.getHeight() ) ;
 		this.stackPane = stackPane ;
		this.stackPane.getChildren().add( canvas ) ;
		addListener();
		this.isActive = false ;
		step = 0 ;
		
	}
	
	public abstract void update() ;
	
	public boolean isActive() {
		return this.isActive ; 
	}
	
	public abstract void active() ;
	
	public abstract void inactive() ;
	
	public Canvas getCanvas() {
		return canvas ; 
	}
	
	public abstract Object getObjectAtPos( int x , int y ) ;
	
	public abstract void drawComponenet() ; 
	
	public void drawIntro( double level ) {
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.setFill(Color.BLACK);
		gc.setGlobalAlpha( level );
		gc.fillRect ( 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setGlobalAlpha( 1 );
		
	}
	
	private void addListener() {
		
		this.canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("MouseReleased : " + event.getButton().toString());
				if (event.getButton() == MouseButton.PRIMARY)
					InputUtility.setMouseLeftDown(false);
				if (event.getButton() == MouseButton.SECONDARY)
					InputUtility.setMouseRightDown(false);
			}
		});
		
		this.canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("MousePressed : " + event.getButton().toString());
				System.out.println(InputUtility.isMouseClickedTriggered());
				if (event.getButton() == MouseButton.PRIMARY) {
										
					InputUtility.setMouseLeftDown(true);
					InputUtility.setMouseLeftLastDown(true);
					InputUtility.setMouseClickedTriggered(true); 
				}
				if (event.getButton() == MouseButton.SECONDARY) {
					InputUtility.setMouseRightDown(true);
					InputUtility.setMouseRightLastDown(true);
					InputUtility.setMouseClickedTriggered(true);
				}

			}
		});

		this.canvas.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(false);
			}
		});

		this.canvas.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(true);
			}
		});

		this.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}
		});

		this.canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}
		});
				
	}
}
