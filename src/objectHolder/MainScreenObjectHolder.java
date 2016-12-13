package objectHolder;

import core.ScreenObjectHolder;

public class MainScreenObjectHolder extends ScreenObjectHolder {
	
	private static final MainScreenObjectHolder instance = new MainScreenObjectHolder();
	private int centerDiamond ; // id of diamond that show on center of main screen

	public MainScreenObjectHolder() {
		super() ; 
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
