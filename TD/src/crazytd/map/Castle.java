package crazytd.map;

import org.robobrain.sdk.graphics.Vector;
import org.robobrain.test.TDspriteGame;
public class Castle extends Block {

	private int hp, maxHp;
	
	public Castle(int x, int y){
		super(x,y);
		bindTextureIndex(TDspriteGame.SPRITE_CASTLE);
	}
	
	public Castle(int x, int y,int maxHp) {
		super(x,y);
		this.maxHp = maxHp;
		bindTextureIndex(TDspriteGame.SPRITE_CASTLE);
	}
	
	public Castle(float x, float y,int maxHp) {
		super(x,y);
		this.maxHp = maxHp;
		bindTextureIndex(TDspriteGame.SPRITE_CASTLE);
	}
	
	public Vector getDirection(float x, float y){
		float xInTile = x - this.x;
		float yInTile = y - this.y;
		if (xInTile > tileSize/2 || xInTile<-tileSize/2 || yInTile > tileSize/2 ||yInTile<-tileSize/2) {
			return new Vector(0,0); //out of boundary
		}
		return new Vector(-xInTile,-yInTile).normalize();
	}
	
	public void setHP(int hp){
		this.hp = hp;
	}
	
	public int getHP(){
		return hp;
	}
	
	public int getMaxHp(){
		return maxHp;
	}
	
	public void damage(int damageAmount){
		hp -= damageAmount;
		if (hp < 0) hp = 0;
	}
}
