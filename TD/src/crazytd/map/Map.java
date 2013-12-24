package crazytd.map;

public class Map {
	int maxX, maxY;
	Block[][] theMap;
	
	public Map(int maxX, int maxY){
		this.maxX=maxX;
		this.maxY=maxY;
		theMap =new Block[maxY][maxX];
	}
	
	public Block getBlock(int x, int y){
		return theMap[x][y];
	}
	
	public Block[][] getMap(){
		return theMap;
	}
}
