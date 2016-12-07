package util;

import java.io.InputStream;

public class Resources {

	private static Resources instance = new Resources() ; 
	
	public static Resources getInstance() {
		return instance ;
	}
	
	private ClassLoader getClassLoader() {
		return this.getClass().getClassLoader();
	}
	
	private InputStream getResourceAsStream(String resourcePath) {
		return getClassLoader().getResourceAsStream("res/" + resourcePath);
	}

	public boolean initialize() {
		try {
			loadFonts();
			loadSound();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	private void loadFonts() {
		
		return ; 

	}
	
	private void loadSound() {
		return ;
	}
	
}
