package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class ButtonImg extends ImageButton{
    /**
     * Construct for a Button with a Texture
     * @param texture_up texture when button not pressed
     * @param texture_down texture when button pressed
     * @param background background
     */
    public ButtonImg(Texture texture_up, Texture texture_down, Texture background){
        super(new SpriteDrawable(new Sprite(texture_up)),
                new SpriteDrawable(new Sprite(texture_down)));
        this.setBackground(new SpriteDrawable(new Sprite(background)));

    }
}