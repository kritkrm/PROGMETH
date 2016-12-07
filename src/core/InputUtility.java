package core;

public class InputUtility {

	private static int mouseX, mouseY;
	private static boolean mouseLeftDown, mouseRightDown, mouseOnScreen;
	private static boolean mouseLeftLastDown, mouseRightLastDown;

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX( int mouseX ) {
		InputUtility.mouseX = mouseX;
		return;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY( int mouseY ) {
		InputUtility.mouseY = mouseY;
		return ; 
	}

	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}

	public static void setMouseLeftDown( boolean mouseLeftDown ) {
		InputUtility.mouseLeftLastDown = mouseLeftDown;
	}

	public static boolean isMouseRightDown() {
		return mouseRightDown;
	}

	public static void setMouseRightDown( boolean mouseRightDown ) {
		InputUtility.mouseRightDown = mouseRightDown ; 
		return ;
	}

	public static boolean isMouseOnScreen() {
		return mouseOnScreen;
	}

	public static void setMouseOnScreen(boolean mouseOnScreen) {
		InputUtility.mouseOnScreen = mouseOnScreen ;
		return;
	}

	public static boolean isMouseLeftClicked() {
		return mouseLeftLastDown;
	}

	public static void setMouseLeftLastDown( boolean v ) {
		InputUtility.mouseLeftLastDown = v ; 
		return ;
	}

	public static boolean isMouseRightClicked() {
		return mouseRightLastDown;
	}

	public static void setMouseRightLastDown( boolean v ) {
		InputUtility.mouseRightLastDown = v;
		return ;
	}

	public static void postUpdate() {
		mouseLeftLastDown = false;
		mouseRightLastDown = false;
	}
	
}
