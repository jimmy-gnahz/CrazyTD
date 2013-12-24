package crazytd.map;

import crazytd.map.Block.Direction;

public class MapParser {
	
	public static String testmap1 =   
			"ww,bb,bb,bb,bb,bb,bb;" +
			"de,we,ws,bb,se,ws,bb;" +
			"ww,bb,ns,bb,sn,ns,bb;" +
			"ww,bb,ne,we,wn,ns,bb;" +
			"ww,bb,bb,bb,bb,ns,bb;" +
			"ww,ww,ww,ww,ww,cc,ww"; 

	public static Map parse(String map){
		String[][] elements = toStringList(map);
		Map result = new Map(elements[0].length,elements.length);
		for (int i=0;i<elements.length;i++){
			for (int j=0;j<elements[i].length;j++){
				try {
					Block blk = stringToBlock(elements[i][j],i,j);
					result.theMap[i][j] = blk;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static Block stringToBlock(String str,int x,int y) throws Exception{
		Block blk = null;
		if (str.equals("ww")){
			blk = new WasteLand(x,y);
		}
		
		else if (str.equals("bb")){
			blk = new Buildable(x,y);
		}
		
		else if (str.equals("cc")){
			blk = new Castle(x,y);
		}
		
		else if (str.startsWith("d")){
			Direction dir = stringToDir(str.substring(1, 2));			
			blk = new MonsterDen(x,y,dir);
		}
		
		else {
			Direction inDir = stringToDir(str.substring(0,1));
			Direction outDir = stringToDir(str.substring(1,2));
			blk = new Road(x,y,inDir,outDir);
		}
		return blk;
	}
	
	private static Direction stringToDir(String str) throws Exception{
		Direction dir = null;
		if (str.equals("w")) dir = Block.Direction.WEST;
		else if (str.equals("e")) dir = Block.Direction.EAST;
		else if (str.equals("n")) dir = Block.Direction.NORTH;
		else if (str.equals("s")) dir = Block.Direction.SOUTH;
		else throw new Exception("Monster Den requires valid direction");
		return dir;
	}
	
	private static String[][] toStringList(String map){
		String[] rows = map.split(";");
		int verticalLength = rows.length;
		int horizontalLength = rows[0].split(",").length;
		
		String[][] elements = new String[verticalLength][horizontalLength];
		for (int i=0;i<rows.length;i++){
			elements[i] = rows[i].split(",");
		}
		return elements;
	} 
	
	
}
