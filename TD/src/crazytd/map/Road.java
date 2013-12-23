package crazytd.map;

import org.robobrain.sdk.graphics.Vector;


public class Road extends Block{
	public enum Direction{
		NORTH, SOUTH, EAST, WEST
	}
	private Direction inDirection, outDirection;
	private Vector n=new Vector(0,-1), s= new Vector(0,1),e=new Vector(1,0),w=new Vector(-1,0), o=new Vector(0,0);
	
	
	public Road(float x, float y, String inDir, String outDir) {
		super(x, y);
		setDir(true, inDir);
		setDir(false,outDir);
	}
	public Road(int col, int row, String inDir, String outDir) {
		super(col, row);
		setDir(true, inDir);
		setDir(false,outDir);
	}
	
	private void setDir(boolean isInDir, String direction){
		Direction d =Direction.valueOf(""+direction);
		if(isInDir)			//is incoming direction
			inDirection = d;
		else outDirection = d;
	}
	//get the direction (unit vector) of the monster is heading given the coordinate
	//(0,0) flags for error
	public Vector getDirection(double x, double y){
		double xInTile=x-this.x;
		double yInTile=y-this.y;
		if (xInTile > tileSize/2 || xInTile<tileSize/2 || yInTile > tileSize/2 ||yInTile<tileSize/2) return o; //out of boundary
		switch(inDirection){
		case NORTH:
			switch(outDirection){
			case SOUTH:
				return s;//heading south
			case EAST:	//either heading south or east
				if (y>=this.y)	//over the half point on the way south, should turn now
					return e;
				else return s;
			case WEST:	//either heading south or west
				if (y>=this.y)	//over the half point on the way south, should turn now
					return w;
				else return s;
			default: return o;	// oops, something is wrong
			}
		case SOUTH:
			switch(outDirection){
			case NORTH:
				return n;//heading north
			case EAST:	//either heading north or east
				if (y<=this.y) //over the half point on the way north, should turn now
					return e;
				else return n;
			case WEST:
				if (y<=this.y) //over the half point on the way north, should turn now
					return w;
				else return n;
			default: return o;	// oops, something is wrong
			}
		case EAST:
			switch(outDirection){
			case WEST:
				return w;//heading west
			case NORTH:	//either heading east or north
				if (y>=this.y) //over the half point on the way east, should turn now
					return n;
				else return w;
			case SOUTH:
				if (y>=this.y) //over the half point on the way east, should turn now
					return s;
				else return w;
			default: return o;	// oops, something is wrong
			}
		case WEST:
			switch(outDirection){
			case EAST:
				return e;//heading east
			case NORTH:	//either heading west or north
				if (y<=this.y) //over the half point on the way east, should turn now
					return n;
				else return e;
			case SOUTH:
				if (y<=this.y) //over the half point on the way east, should turn now
					return s;
				else return e;
			default: return o;	// oops, something is wrong
			}
		}
		return o; //oops, somthing is wrong
	}
}
