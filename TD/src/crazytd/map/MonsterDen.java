package crazytd.map;

import org.robobrain.sdk.graphics.Vector;
import org.robobrain.test.TDspriteGame;
public class MonsterDen extends Block {

	protected float elapsedTime;
	
	/**
	 * Time interval between creating new monsters. 
	 * Measured in milliseconds
	 */
	protected float timeInterval;
	
	/**
	 *  The direction in which monsters are going out of the tile
	 */
	Direction outDirection;
	
	/**
	 * Direction vectors for north, south, east, and west.
	 * o : zero vector 
	 */
	private Vector n = new Vector(0,-1), 
			s = new Vector(0,1),
			e = new Vector(1,0),
			w = new Vector(-1,0), 
			o = new Vector(0,0);
	
	//outDir should be one of the the enum NORTH, SOUTH, EAST, WEST
	public MonsterDen(int x, int y,Direction outDir) {
		super(x, y);
		outDirection = outDir;
		elapsedTime = 0;
		bindTextureIndex(TDspriteGame.SPRITE_MONSTER_DEN);
	}
	
	public MonsterDen(float x, float y) {
		super(x, y);
		elapsedTime = 0;
		bindTextureIndex(TDspriteGame.SPRITE_MONSTER_DEN);
	}
	
	public Vector getDirection(float x, float y){
		float xInTile = x - this.x;
		float yInTile = y - this.y;
		if (xInTile > tileSize/2 || xInTile<-tileSize/2 || yInTile > tileSize/2 ||yInTile<-tileSize/2) {
			return o; //out of boundary
		}
		switch (outDirection){
		case NORTH:
			return n;
		case SOUTH:
			return s;
		case EAST:
			return e;
		case WEST:
			return w;
		default: return o;//oops something is wrong
		}
	}
	
	public float getTimeInterval(){
		return timeInterval;
	}
	
	public float getElapsedTime(){
		return elapsedTime;
	}
	
	public void setTimeInterval(float time){
		timeInterval = time;
	}
	
	public void setElapsedTime(float time){
		elapsedTime = time;
	}
}
