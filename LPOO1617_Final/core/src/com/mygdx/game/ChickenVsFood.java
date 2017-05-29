package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MainMenuScreen;

//implements the game loop
public class ChickenVsFood extends Game {
	private static final int V_WIDTH = 1900;
	private static final int V_HEIGHT = 900;
	public static final float ASPECT_RATIO = (float) V_WIDTH/(float) V_HEIGHT;
	//public static final float PPM = 100;
	private SpriteBatch batch;
	private AssetManager assetManager;

	public int getvWidth(){
		return V_WIDTH;
	}

	public int getvHeight(){
		return V_HEIGHT;
	}

	public SpriteBatch getBatch(){
		return batch;
	}

	public ChickenVsFood getGame(){
		return this;
	}

	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
}
