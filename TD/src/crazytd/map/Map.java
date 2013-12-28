package crazytd.map;

import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Vector;

import crazytd.sprites.Monster;

public class Map {
	int maxX, maxY;
	Block[][] theMap;
	Monster monster;
	
	//default visibility to hide the mess, only MapParser allow to access this constructor
	Map(int maxX, int maxY){
		this.maxX=maxX;
		this.maxY=maxY;
		theMap =new Block[maxY][maxX];
	}
	
	public Monster getMonster(){
		return monster;
	}
	
	public void setMonster(Monster monster){
		this.monster = monster;
	}
	
	public Block getMonsterDen() throws Exception{
		for(int i=0;i<maxY;i++){
			for(int j=0;j<maxX;j++){
				if (theMap[i][j] instanceof MonsterDen) {
					return theMap[i][j];
				}
			}
		}
		throw new Exception("MonsterDen Not Found");
	}
	
	public Block getBlock(int x, int y){
		return theMap[y][x];
	}
	
	public Block getBlockByCoordinate(float x, float y){
		int xCoord = (int)(x/Block.tileSize);
		int yCoord = (int)(y/Block.tileSize);
		return theMap[yCoord][xCoord];
	}
	
	public Block getBlockByCoordinate(Vector coordinate){
		return theMap[(int)(coordinate.y/Block.tileSize)][(int)(coordinate.x/Block.tileSize)];
	}
	
	public void addMapToWorld(World world){
		
		for(int y=0;y<theMap.length;y++){
			for(int x=0;x<theMap[0].length;x++){
				world.addEntity(theMap[y][x].getMapElement());
			}
		}
	}
//	We will never show the map in 2d array format, it is confusing!!!
//  (don't delete this, this tomb is a warning
//
//	public Block[][] getMap(){
//		return theMap;
//	}
}
