package objectHolder;

import core.ScreenObjectHolder;
import javafx.scene.image.Image;
import util.Resources;

public class MainScreenObjectHolder extends ScreenObjectHolder {
	
	private static final MainScreenObjectHolder instance = new MainScreenObjectHolder();
	private int centerDiamond ;

	public MainScreenObjectHolder() {
		super() ;
		setCenterDiamond( 0 ) ;
	}
	public static MainScreenObjectHolder getInstance() {
		return instance;
	}
	public int getCenterDiamond() {
		return centerDiamond;
	}
	public void setCenterDiamond(int centerDiamond) {
		this.centerDiamond = centerDiamond;
	}

}
