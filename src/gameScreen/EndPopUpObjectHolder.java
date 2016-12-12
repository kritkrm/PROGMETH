package gameScreen;

import core.ScreenObjectHolder;

public class EndPopUpObjectHolder extends ScreenObjectHolder {
	
	private static final EndPopUpObjectHolder instance = new EndPopUpObjectHolder();

	public EndPopUpObjectHolder() {
		super() ;
	}
	public static EndPopUpObjectHolder getInstance() {
		return instance;
	}
}
