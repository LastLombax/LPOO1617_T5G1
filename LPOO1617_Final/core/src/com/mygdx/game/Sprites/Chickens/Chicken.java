package com.mygdx.game.Sprites.Chickens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by vitor on 29/05/2017.
 */

public interface Chicken{

    void defineChicken(int x, int y);

    void update(float dt);

    Body getBody();

    float getVelocity();

    void draw(SpriteBatch batch);
}
