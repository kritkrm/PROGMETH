package endScreen;

import core.ScreenObjectHolder;

public class EndScreenObjectHolder extends ScreenObjectHolder {
	
	private static final EndScreenObjectHolder instance = new EndScreenObjectHolder();

	public EndScreenObjectHolder() {
		super() ;
	}
	public static EndScreenObjectHolder getInstance() {
		return instance;
	}
}
