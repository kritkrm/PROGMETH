package mainScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import core.ScreenObject;
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
