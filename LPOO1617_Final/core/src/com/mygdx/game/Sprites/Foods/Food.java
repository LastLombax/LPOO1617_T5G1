package com.mygdx.game.Sprites.Foods;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by vitor on 29/05/2017.
 */

public interface Food {

    void defineFood(int x, int y);

    void update(float dt);

    Body getBody();

    void draw(SpriteBatch batch);

    int getHealth();


}
