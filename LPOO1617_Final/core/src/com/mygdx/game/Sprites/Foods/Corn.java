package com.mygdx.game.Sprites.Foods;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Scenes.Hud;

/**
 * Created by vitor on 01/06/2017.
 */

public class Corn extends Sprite {

    private Vector2 cornMovDir;
    private int MULTI = 5;

    public Corn(int x, int y, Vector2 dir){
        super(new Texture("corn.png"));
        this.setBounds(0,0,50,50);
        this.setPosition(x,y);
        cornMovDir = dir;
        cornMovDir.nor();
    }

    public boolean update(float dt){
        if (this.getX() > Hud.getCornLabel().getX() && this.getY() < Hud.getCornLabel().getY()){
            this.setPosition(this.getX() + MULTI*cornMovDir.x, this.getY() + MULTI*cornMovDir.y);
            return false;
        }
        return true;

    }

}
