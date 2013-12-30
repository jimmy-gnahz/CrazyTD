package crazytd.map;

import org.robobrain.sdk.graphics.Vector;
import org.robobrain.test.TDspriteGame;


public class Road extends Block{

	private Direction inDirection, outDirection;
	
	/**
	 * Direction vectors for north, south, east, and west.
	 * o : zero vector 
	 */
	private Vector n = new Vector(0,-1), 
			s = new Vector(0,1),
			e = new Vector(1,0),
			w = new Vector(-1,0), 
			o = new Vector(0,0);
	
	/**
	 * @param x 
	 * @param y
	 * @param inDir inwards direction into the block
	 * @param outDir direction going out of the block
	 */
	public Road(float x, float y, Direction inDir, Direction outDir) {
		super(x, y);
		inDirection = inDir;
		outDirection = outDir;
		bindTextureIndex(TDspriteGame.SPRITE_ROAD);
	}
	
	/**
	 * @param col  
	 * @param row
	 * @param inDir
	 * @param outDir
	 */
	public Road(int col, int row, Direction inDir, Direction outDir) {
		super(col, row);
		inDirection = inDir;
		outDirection = outDir;
		bindTextureIndex(TDspriteGame.SPRITE_ROAD);
	}
	
	/**
	 * get the direction (unit vector) in which the monster is heading,
	 * given the current coordinate of the monster
	 * Precondition: Monster is inside the tile.
	 * @param x x-coordinate of the monster
	 * @param y y-coordinate of the monster
	 * @return direction, one of {(1,0),(0,1),(-1,0),(0,-1)}. returns (0,0) in case of error
	 */
	//
	//(0,0) flags for error
	public Vector getDirection(float x, float y){
		float xInTile = x - this.x;
		float yInTile = y - this.y;
		if (xInTile > tileSize/2 || xInTile<-tileSize/2 || yInTile > tileSize/2 ||yInTile<-tileSize/2) {
			return o; 
		}//out of boundary
		switch(inDirection){
		case NORTH:
			switch(outDirection){
			case SOUTH:
				return s;//heading south
			case EAST:	//either heading south or east
				if (y >= this.y)	//over the half point on the way south, should turn now
					return e;
				else return s;
			case WEST:	//either heading south or west
				if (y >= this.y)	//over the half point on the way south, should turn now
					return w;
				else return s;
			default: return o;	// oops, something is wrong
			}
		case SOUTH:
			switch(outDirection){
			case NORTH:
				return n;//heading north
			case EAST:	//either heading north or east
				if (y <= this.y) //over the half point on the way north, should turn now
					return e;
				else return n;
			case WEST:
				if (y <= this.y) //over the half point on the way north, should turn now
					return w;
				else return n;
			default: return o;	// oops, something is wrong
			}
		case EAST:
			switch(outDirection){
			case WEST:
				return w;//heading west
			case NORTH:	//either heading east or north
				if (x <= this.x) //over the half point on the way east, should turn now
					return n;
				else return w;
			case SOUTH:
				if (x <= this.x) //over the half point on the way east, should turn now
					return s;
				else return w;
			default: return o;	// oops, something is wrong
			}
		case WEST:
			switch(outDirection){
			case EAST:
				return e;//heading east
			case NORTH:	//either heading west or north
				if (x >= this.x) //over the half point on the way east, should turn now
					return n;
				else return e;
			case SOUTH:
				if (x >= this.x) //over the half point on the way east, should turn now
					return s;
				else return e;
			default: return o;	// oops, something is wrong
			}
		}
		return o; //oops, something is wrong
	}
}
