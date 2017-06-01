package com.mygdx.game.Sprites.Chickens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ChickenVsFood;

/**
 * Created by vitor on 29/05/2017.
 */

public abstract class Chicken extends Sprite{

    private World world;
    private Body b2body;

    public Chicken(World world, ChickenVsFood game){
        super(game.getAssetManager().get("Chicken.png",Texture.class));
        this.world = world;
    }

    public void defineChicken(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        //b2body.setUserData("Chicken");

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);


        fdef.shape = shape;
        fdef.filter.categoryBits = ChickenVsFood.CHICKEN_BIT;
        fdef.filter.maskBits = ChickenVsFood.BUTTER_BIT | ChickenVsFood.FOOD_BIT;

        /*fdef.density = 0.5f;
        fdef.friction = 0.4f;
        fdef.restitution = 0.5f;*/

        b2body.createFixture(fdef).setUserData(this);
    }

    public abstract void update(float dt);

    public abstract float getVelocity();

    public abstract void draw(SpriteBatch batch);

    public abstract int getHealth();

    public abstract void setHealth(int health);

    public abstract int getDmg();

    public abstract void hit();

    public abstract void Nothit();

    public World getWorld(){return this.world;}

    public Body getBody(){return this.b2body;}

    public boolean isDead() {
        if (getHealth() == 0){
            this.world.destroyBody(this.b2body);
            return true;
        }
        return false;
    }


    public void decreaseHealth() {
        setHealth(getHealth()-1);
    }



}
