package crazytd.map;

public class Buildable extends Block {
	private boolean isBuilt;
	
	public Buildable(int x, int y, String type) {
		super(x, y, type);
		isBuilt = false;
	}
	public Buildable(double x, double y, String type) {
		super(x, y, type);
		isBuilt = false;
	}

		
	
}
