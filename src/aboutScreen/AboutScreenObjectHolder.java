package aboutScreen;

import core.ScreenObjectHolder;

public class AboutScreenObjectHolder extends ScreenObjectHolder {
	
	private static final AboutScreenObjectHolder instance = new AboutScreenObjectHolder();

	public AboutScreenObjectHolder() {
		super() ;
	}
	public static AboutScreenObjectHolder getInstance() {
		return instance;
	}
	
}
