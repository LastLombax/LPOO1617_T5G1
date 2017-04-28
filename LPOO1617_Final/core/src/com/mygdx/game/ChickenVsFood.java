package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;

//implements the game loop
public class ChickenVsFood extends Game {
	public static final int V_WIDTH = 1900;
	public static final int V_HEIGHT = 900;
	public SpriteBatch batch;
	private AssetManager assetManager;

	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
}
