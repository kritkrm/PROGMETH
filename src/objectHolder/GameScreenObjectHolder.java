package objectHolder;

import core.ScreenObjectHolder;

public class GameScreenObjectHolder extends ScreenObjectHolder{
	
	private static final GameScreenObjectHolder instance = new GameScreenObjectHolder();

	public GameScreenObjectHolder() {
		super();
	}
	
	public static GameScreenObjectHolder getInstance() {
		return instance;
	}
}
