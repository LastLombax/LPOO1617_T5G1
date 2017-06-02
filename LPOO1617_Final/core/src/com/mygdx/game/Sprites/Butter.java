package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ChickenVsFood;

/**
 * Created by vitor on 16/05/2017.
 */

public class Butter extends Sprite {
    private World world;
    private Body b2body;
    private ChickenVsFood game;
    private TextureRegion ButterTexture;
    private Texture Butter = new Texture("butter.png");
    private float VELOCITY = 50f;
    private boolean hitting = false;


    public Butter(World world, ChickenVsFood game, int x, int y){
        this.world = world;
        this.game = game;
        defineButter(x,y);
        ButterTexture = new TextureRegion(Butter,0,0,28,40);
        setBounds(0,0,28,40);
        setRegion(ButterTexture);
    }

    public void defineButter(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);
        fdef.shape = shape;

        this.b2body.createFixture(fdef).setUserData(this);
    }

    public Body getBody() {
        return b2body;
    }

    public void update(float dt) {
        setPosition(getBody().getPosition().x - getWidth() / 2, getBody().getPosition().y - getWidth() / 2);
        if(this.hitting){
            this.getBody().applyLinearImpulse(new Vector2(this.VELOCITY, 0),this.getBody().getWorldCenter(), true);
        }

    }

    public void setHit(boolean b){
        this.hitting = b;
    }

    public boolean getHit(){
        return this.hitting;
    }




}



