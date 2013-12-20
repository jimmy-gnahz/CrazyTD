package crazytd.map;

import crazytd.map.Block.BlockType;

public class Buildable extends Block {
	private boolean isBuilt;
	
	public Buildable(int x, int y) {
		super(x, y);
		this.type = BlockType.valueOf("BUILDABLE");
		isBuilt = false;
	}
	public Buildable(double x, double y) {
		super(x, y);
		this.type = BlockType.valueOf("BUILDABLE");
		isBuilt = false;
	}

		
	
}
