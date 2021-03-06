package crazytd.map;

import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Vector;

import android.util.Log;

import crazytd.sprites.Monster;

public class Map {
	int maxX, maxY;
	Block[][] theMap;
	
	/**
	 * The type of monster the current map/level is going to create
	 */
	private Monster monster;
	
	//default visibility to hide the mess, only MapParser allow to access this constructor
	Map(int maxX, int maxY){
		this.maxX=maxX;
		this.maxY=maxY;
		theMap =new Block[maxY][maxX];
	}
	
	public Monster getMonster(){
		return monster;
	}
	
	/**
	 * Sets the type of monster that will come out of the monsterDen
	 * @param monster
	 */
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
	
	public Castle getCastle() throws Exception {
		for(int i=0;i<maxY;i++){
			for(int j=0;j<maxX;j++){
				if (theMap[i][j] instanceof Castle) {
					return (Castle) theMap[i][j];
				}
			}
		}
		throw new Exception("Castle not found");
	}
	
	public Block getBlock(int x, int y){
		return theMap[y][x];
	}
	/**
	 * find a block in map given its coordinate
	 * @param x
	 * @param y
	 * @return the block with the coordinate, null if there is no block
	 */
	public Block getBlockByCoordinate(float x, float y){
		int xCoord = Math.round((x-Block.tileSize/2)/Block.tileSize);
		int yCoord = Math.round((y-Block.tileSize/2)/Block.tileSize);
		
		if (xCoord < maxX && yCoord < maxY){
			return theMap[yCoord][xCoord];
		}
		return null;
	}
	
	
	public void addMapToWorld(World world){
		
		for(int y=0;y<theMap.length;y++){
			for(int x=0;x<theMap[0].length;x++){
				world.addEntity(theMap[y][x]);
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
