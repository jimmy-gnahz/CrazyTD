package crazytd.map;

import org.robobrain.test.TDspriteGame;

public class WasteLand extends Block {

	public WasteLand(int x, int y) {
		super(x, y);
		bindTextureIndex(TDspriteGame.SPRITE_WASTELAND);
	}
	public WasteLand(float x, float y) {
		super(x, y);
		bindTextureIndex(TDspriteGame.SPRITE_WASTELAND);
	}
}
