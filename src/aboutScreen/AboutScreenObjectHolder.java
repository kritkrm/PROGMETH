package aboutScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import core.ScreenObject;

public class AboutScreenObjectHolder {
	
	private static final AboutScreenObjectHolder instance = new AboutScreenObjectHolder();
	private List<ScreenObject> entities;
	private Comparator<ScreenObject> comparator;

	public AboutScreenObjectHolder() {
		entities = new ArrayList<ScreenObject>();
		comparator = ( ScreenObject A , ScreenObject B ) -> {
			if ( A.getZ() > B.getZ() )
				return 1;
			return -1;
		};
	}
	public static AboutScreenObjectHolder getInstance() {
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