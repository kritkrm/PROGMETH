package core;

import logic.TargetObject;

public class GameLogic {
	
	public GameLogic( GridCell gridCell ) {
		
		private Cell getTopMostCellAt( int x , int y ) {
			Cell cell = null;
			for( Cell target : onScreenObject){
				if(target.contains(x, y)){
					if(obj != null){
						if(target.getZ() > obj.getZ()){
							obj.setPointerOver(false);
							obj = target;
							obj.setPointerOver(true);
						}
					}else{
						obj = target;
						obj.setPointerOver(true);
					}
				}else{
					target.setPointerOver(false);
				}
			}
			return obj;
		}
		
		
	}
	
}
