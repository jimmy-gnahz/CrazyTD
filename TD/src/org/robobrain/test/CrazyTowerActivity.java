package org.robobrain.test;

import org.robobrain.sdk.GameActivity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Color;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;


public class CrazyTowerActivity extends GameActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initRenderer();
		setBackgroundColor(Color.BLUE);
		registerGame(new CrazyTowerGame());
	}

}
