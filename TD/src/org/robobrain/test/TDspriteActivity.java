package org.robobrain.test;

import org.robobrain.sdk.GameActivity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Color;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;


public class TDspriteActivity extends GameActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initRenderer(800,480);
		setBackgroundColor(Color.YELLOW);
		registerGame(new TDspriteGame());
	}

}
