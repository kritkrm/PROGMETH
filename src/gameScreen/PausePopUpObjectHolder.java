package gameScreen;

import aboutScreen.AboutScreenObjectHolder;
import core.ScreenObjectHolder;

public class PausePopUpObjectHolder extends ScreenObjectHolder {
	
	private static final PausePopUpObjectHolder instance = new PausePopUpObjectHolder();

	public PausePopUpObjectHolder() {
		super() ;
	}
	public static PausePopUpObjectHolder getInstance() {
		return instance;
	}
}
