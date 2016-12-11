package gameScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import core.ScreenObject;

public class GameScreenObjectHolder {
	
	private static final GameScreenObjectHolder instance = new GameScreenObjectHolder();

	private List<ScreenObject> entities;
	private Comparator<ScreenObject> comparator;
	
	public GameScreenObjectHolder() {
		entities = new ArrayList<ScreenObject>();
		comparator = ( ScreenObject A , ScreenObject B ) -> {
			if ( A.getZ() > B.getZ() )
				return 1;
			return -1;
		};
	}
	
	public static GameScreenObjectHolder getInstance() {
		return instance;
	}
	
	public void add(ScreenObject entity) {
		entities.add(entity);
		sort();
	}
	
	public void sort(){
		Collections.sort(entities, comparator);
	}
	
	public List<ScreenObject> getEntities() {
		return entities;
	}
	
}
