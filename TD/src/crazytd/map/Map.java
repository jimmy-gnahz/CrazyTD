package crazytd.map;

public class Map {
	int maxX, maxY;
	Block[][] theMap;
	
	public Map(int maxX, int maxY){
		this.maxX=maxX;
		this.maxY=maxY;
		theMap =new Block[maxY][maxX];
	}
	
	private void dummy(){
		theMap[0][0]=new Buildable(0,0);
		theMap[1][0]=new Buildable(1,0);
		theMap[2][0]=new Buildable(2,0);
		theMap[3][0]=new Buildable(3,0);
		
		theMap[0][1]=new MonsterDen(0,1,MonsterDen.Direction.EAST);
		theMap[1][1]=new Road(1,1,Road.Direction.WEST,Road.Direction.EAST);
		theMap[2][1]=new Road(2,1,Road.Direction.WEST,Road.Direction.EAST);
		theMap[3][1]=new Castle(3,1,100);
		
		theMap[0][2]=new Buildable(0,2);
		theMap[1][2]=new Buildable(1,2);
		theMap[2][2]=new Buildable(2,2);		
		theMap[3][2]=new Buildable(3,2);	
		
		theMap[0][3]=new WasteLand(0,3);	
		theMap[1][3]=new WasteLand(1,3);
		theMap[2][3]=new WasteLand(2,3);
		theMap[3][3]=new WasteLand(3,3);
	}
	
//	private void dummy(){
//		theMap[0][0]=new WasteLand(0,0);
//		theMap[1][0]=new Buildable(1,0);
//		theMap[2][0]=new Buildable(2,0);
//		theMap[3][0]=new Buildable(3,0);
//		theMap[4][0]=new Buildable(4,0);
//		theMap[5][0]=new Buildable(5,0);
//		theMap[6][0]=new Buildable(6,0);
//		
//		theMap[0][1]=new MonsterDen(0,1,MonsterDen.Direction.EAST);
//		theMap[1][1]=new Road(1,1,Road.Direction.WEST,Road.Direction.EAST);
//		theMap[2][1]=new Road(2,1,Road.Direction.WEST,Road.Direction.EAST);
//		theMap[3][1]=new Buildable(3,1);
//		theMap[4][1]=new Road(4,1,Road.Direction.SOUTH,Road.Direction.EAST);
//		theMap[5][1]=new Road(5,1,Road.Direction.WEST,Road.Direction.SOUTH);
//		theMap[6][1]=new Buildable(6,1);
//		
//		theMap[0][2]=new WasteLand(0,2);
//		theMap[1][2]=new Buildable(1,2);
//		theMap[2][2]=new Road(2,2,Road.Direction.NORTH,Road.Direction.SOUTH);
//		theMap[3][2]=new Buildable(3,2);
//		theMap[4][2]=new Road(4,2,Road.Direction.SOUTH,Road.Direction.NORTH);
//		theMap[5][2]=new Road(5,2,Road.Direction.NORTH,Road.Direction.SOUTH);
//		theMap[6][2]=new Buildable(6,2);
//		
//		theMap[0][3]=new WasteLand(0,3);
//		theMap[1][3]=new Buildable(1,3);
//		theMap[2][3]=new Road(2,3,Road.Direction.NORTH,Road.Direction.EAST);
//		theMap[3][3]=new Road(3,3,Road.Direction.WEST,Road.Direction.EAST);
//		theMap[4][3]=new Road(4,3,Road.Direction.WEST,Road.Direction.NORTH);
//		theMap[5][3]=new Road(5,3,Road.Direction.NORTH,Road.Direction.SOUTH);
//		theMap[6][3]=new Buildable(6,3);
//		
//		theMap[0][4]=new WasteLand(0,4);
//		theMap[1][4]=new Buildable(1,4);
//		theMap[2][4]=new Buildable(2,4);
//		theMap[3][4]=new Buildable(3,4);
//		theMap[4][4]=new Buildable(4,4);
//		theMap[5][4]=new Road(5,4,Road.Direction.NORTH,Road.Direction.SOUTH);
//		theMap[6][4]=new Buildable(6,4);
//		
//		theMap[0][5]=new WasteLand(0,5);
//		theMap[1][5]=new WasteLand(1,5);
//		theMap[2][5]=new WasteLand(2,5);
//		theMap[3][5]=new WasteLand(3,5);
//		theMap[4][5]=new WasteLand(4,5);
//		theMap[5][5]=new Castle(5,5,100);
//		theMap[6][5]=new WasteLand(6,5);
//	}
	
	public Block getBlock(int x, int y){
		return theMap[x][y];
	}
	
	public Block[][] getMap(){
		return theMap;
	}
}
