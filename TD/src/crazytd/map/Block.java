package crazytd.map;

import org.robobrain.sdk.graphics.Vector;


public class Block {
	public enum blockType{
		BUILDABLE , ROAD, WASTELAND, OBSTACLES		
	}
	
	private int x;		// location
	private int y;		// location
	private blockType type;//block type
	public Block(int x, int y, blockType type){
		this.x = x;
		this.y = y;
		this.type=type;
	}
	public Block(float x, float y, blockType type){
		this.x= Math.round(x);
		this.y= Math.round(y);
		this.type = type;
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
	public blockType getBlockType(){
		return type;
	}
}
