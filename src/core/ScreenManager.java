package core;

public class ScreenManager {
	
	private static final ScreenManager instance = new ScreenManager();
	
	public static ScreenManager getInstance() {
		return instance;
	}
	
	private Screen nextScreen ;
	private Screen currentScreen ;
	
	public void setNextScreen( Screen screen ) {
		this.nextScreen = screen ;
	}
	
	public ScreenManager() {
		
	}
	
	public void update() {
		
		if (this.nextScreen != null) {
			if( currentScreen != null ) {
				currentScreen.inactive(); 
			}
			this.currentScreen = nextScreen;
			this.nextScreen = null;
		}

		if (this.currentScreen != null) {
			currentScreen.active(); 
			this.currentScreen.update();
		}

	}
	
}
