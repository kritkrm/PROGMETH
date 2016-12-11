package mainScreen;

import core.ScreenObjectHolder;

public class MainScreenObjectHolder extends ScreenObjectHolder {
	
	private static final MainScreenObjectHolder instance = new MainScreenObjectHolder();
	
	public MainScreenObjectHolder() {
		super() ;
	}
	public static MainScreenObjectHolder getInstance() {
		return instance;
	}

}
