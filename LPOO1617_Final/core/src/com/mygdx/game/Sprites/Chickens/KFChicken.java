package com.mygdx.game.Sprites.Chickens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ChickenVsFood;

/**
 * Created by vitor on 29/05/2017.
 */

public class KFChicken implements Chicken {
    public static final float VELOCITY = 2.5f;
    public World world;
    public Body b2body;
    public ChickenVsFood game;
    private TextureRegion ChickenTexture;
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
}
