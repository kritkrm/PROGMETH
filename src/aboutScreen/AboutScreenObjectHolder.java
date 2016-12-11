package aboutScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import core.ScreenObject;
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
