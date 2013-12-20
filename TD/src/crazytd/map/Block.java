package crazytd.map;

import org.robobrain.sdk.graphics.Vector;


public class Block {
	public enum BlockType{
		BUILDABLE , ROAD, WASTELAND, OBSTACLES
	}
	
	private int tileX;		// the column number in the map
	private int tileY;		// the row number in the map
	protected double x;		// the x coordinate in the map
	protected double y;		// the y coordinate in the map
	protected BlockType type;//block type
	
	//Temporary variables
	protected double tileSize = 5;//the edge length of a tile 
	//set up a tile by column number and row number
	public Block(int col, int row){
		this.tileX = col;
		this.tileY = row;
		this.x=col*tileSize;
		this.y=row*tileSize;
		//this.type=BlockType.valueOf(type);
	}

	//set up a tile by given a coordinate, the coordinate of a tile will be its top left corner
	public Block(double x, double y){
		this.tileX=(int)(x/tileSize);			//round down
		this.tileY=(int)(y/tileSize);			//round down
		this.x=tileX*tileSize;
		this.y=tileY*tileSize;
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
	public String getBlockType(){
		return type.toString();
	}
}
