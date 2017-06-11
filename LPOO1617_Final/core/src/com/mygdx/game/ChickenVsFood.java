package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Screens.MainMenuScreen;


public class ChickenVsFood extends Game {
	private static final int V_WIDTH = 1900;
	private static final int V_HEIGHT = 900;
	public static final short CHICKEN_BIT = 1;
	public static final short FOOD_BIT = 2;
	public static final short BUTTER_BIT = 4;
	public static final short MAP_BIT = 8;
	private SpriteBatch batch;

	/**
	 * @return Returns the Virtual Width V_WIDTH
	 */
	public int getvWidth() {
		return V_WIDTH;
	}

	/**
	 * @return Returns the Virtual Height V_HEIGHT
	 */
	public int getvHeight() {
		return V_HEIGHT;
	}

	/**
	 * @return Returns the game batch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}

	/**
	 * @return Returns the game batch
	 */
	public ChickenVsFood getGame() {
		return this;
	}

	/**
	 * Creates the batch and calls the MainMenu Screen
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}
	/**
	 * Renders all
	 */
	@Override
	public void render() {
		super.render();
	}

	/**
	 * Disposes the batch
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

}
