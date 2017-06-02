package com.mygdx.game.Sprites.Chickens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 29/05/2017.
 */

public class MadChicken extends Chicken {
    private float VELOCITY = 2.5f;
    private int HEALTH = 5;
    private int DMG = 1;
    private World world;
    private Body b2body;
    private ChickenVsFood game;
    private TextureRegion ChickenTexture;

    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;
    public MadChicken(World world, ChickenVsFood game, int xInicial, int yInicial, PlayScreen screen) {
        super(world, game, screen);
        ChickenTexture = new TextureRegion(screen.getMadChicken().findRegion("MadChicken"), 0, 0, SIZE_PIXEL, SIZE_PIXEL);

    }
    @Override
    public void defineChicken(int i, int i1) {

    }

    @Override
    public void update(float v) {

    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public float getVelocity() {
        return 0;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public void setHealth(int health) {
        this.HEALTH = health;
    }

    @Override
    public int getDmg() {
        return DMG;
    }



}
