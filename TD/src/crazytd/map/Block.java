package crazytd.map;

import org.robobrain.sdk.graphics.Vector;


public class Block {
	public enum BlockType{
		BUILDABLE , ROAD, WASTELAND, OBSTACLES
	}
	
	private int tileX;		// the column number in the map
	private int tileY;		// the row number in the map
	protected float x;		// the x coordinate in the map
	protected float y;		// the y coordinate in the map
	//protected BlockType type;//block type
	
	//Temporary variables
	protected float tileSize = 64;//the edge length of a tile 
	//set up a tile by column number and row number
	public Block(int col, int row){
		this.tileX = col;
		this.tileY = row;
		this.x=col*tileSize+tileSize/2;
		this.y=row*tileSize+tileSize/2;
		//this.type=BlockType.valueOf(type);
	}

	//set up a tile by given a coordinate, the coordinate of the center of a tile will always smaller or equal to the given coordinate
	public Block(float x, float y){
		this.tileX=(int)(x/tileSize);			//round down
		this.tileY=(int)(y/tileSize);			//round down
		this.x=tileX*tileSize+tileSize/2;
		this.y=tileY*tileSize+tileSize/2;
		//this.type=BlockType.valueOf(type);
	}
	public Vector getCoordinate(){
		return new Vector((float)x,(float)y);
	}
	
	public float getX(){
		return (float)x;
	}
	
	public float getY(){
		return (float)y;
	}

}
