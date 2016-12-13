package objectHolder;

import core.ScreenObjectHolder;

public class MainScreenObjectHolder extends ScreenObjectHolder {
	
	private static final MainScreenObjectHolder instance = new MainScreenObjectHolder();
	private int centerDiamond ;

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
