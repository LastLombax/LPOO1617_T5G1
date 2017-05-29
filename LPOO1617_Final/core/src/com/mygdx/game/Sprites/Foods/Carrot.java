package com.mygdx.game.Sprites.Foods;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ChickenVsFood;

/**
 * Created by vitor on 29/05/2017.
 */

public class Carrot implements Food {
    public World world;
    public Body b2body;
    public ChickenVsFood game;
    private TextureRegion ChickenTexture;
    @Override
    public void defineFood(int i, int i1) {

    }

    @Override
    public void update(float v) {

    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

    }
}
