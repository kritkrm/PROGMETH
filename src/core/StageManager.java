package core;

import java.awt.Graphics2D;

public class StageManager {
	
	private static StageManager instance = new StageManager() ; 
	
	public static StageManager getInstance() {
		return instance;
	}

	private Stage nextStage;
	private Stage currentStage;

	public void setNextStage(Stage stage) {
		this.nextStage = stage;
	}
	
	public void update() {

		if ( this.nextStage != null ) {
			this.currentStage = nextStage;
			this.nextStage = null;
		}

	
	}
	
}
