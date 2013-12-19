package crazytd.map;

import org.robobrain.sdk.graphics.Vector;


public class Block {
	public enum blockType{
		BUILDABLE , ROAD, WASTELAND, OBSTACLES
	}
	
	private int x;		// location
	private int y;		// location
	private blockType type;//block type
	
	public Block(int x, int y, String type){
		this.x = x;
		this.y = y;
		this.type=blockType.valueOf(type);
	}

	public Block(double x, double y, String type){
		this.x=(int)Math.round(x);
		this.y=(int)Math.round(y);
		this.type=blockType.valueOf(type);
	}
	public Vector getCondinate(){
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
