package gameScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import core.ScreenObject;
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
