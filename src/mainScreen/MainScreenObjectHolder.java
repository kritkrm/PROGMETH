package mainScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import object.ScreenObject;

public class MainScreenObjectHolder {
	
	private static final MainScreenObjectHolder instance = new MainScreenObjectHolder();
	private List<ScreenObject> entities;
	
	public MainScreenObjectHolder() {
		entities = new ArrayList<ScreenObject>();
	}
	public synchronized static MainScreenObjectHolder getInstance() {
		return instance;
	}
	public synchronized void add(ScreenObject entity) {
		entities.add(entity);
	}
	public synchronized List<ScreenObject> getEntities() {
		return entities;
	}
	
}
