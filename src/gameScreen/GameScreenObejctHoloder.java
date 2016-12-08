package gameScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import core.ScreenObejct;

public class GameScreenObejctHoloder {
	
	private static final GameScreenObejctHoloder instance = new GameScreenObejctHoloder();

	private List<ScreenObejct> entities;
	private Comparator<ScreenObejct> comparator;
	
	public GameScreenObejctHoloder() {
		entities = new ArrayList<ScreenObejct>();
		comparator = ( ScreenObejct A , ScreenObejct B ) -> {
			if ( A.getZ() > B.getZ() )
				return 1;
			return -1;
		};
	}
	
	public static GameScreenObejctHoloder getInstance() {
		return instance;
	}
	
	public void addAndSort(ScreenObejct entity) {
		add(entity);
//		sort();
	}
	
	public void add(ScreenObejct entity) {
		entities.add(entity);
//		sort();
	}
	
	public void sort(){
		Collections.sort(entities, comparator);
	}
	
	
	public List<ScreenObejct> getEntities() {
		return entities;
	}
	
}
