package pauseScreen;

import aboutScreen.AboutScreenObjectHolder;
import core.ScreenObjectHolder;

public class PauseScreenObjectHolder extends ScreenObjectHolder {
	
	private static final PauseScreenObjectHolder instance = new PauseScreenObjectHolder();

	public PauseScreenObjectHolder() {
		super() ;
	}
	public static PauseScreenObjectHolder getInstance() {
		return instance;
	}
}
