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
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 29/05/2017.
 */

public abstract class Chicken extends Sprite{

    private World world;
    private Body b2body;
    private boolean foodHit;
    private boolean hit;

    /**
     * Constructor for a Chicken
     * @param world game world
     * @param game game
     * @param screen game screen
     */
    public Chicken(World world, ChickenVsFood game, PlayScreen screen){

        setFoodHit(false);
        this.world = world;
    }

    /**
     * Defines the body of a chicken
     * @param x initial x coordinate
     * @param y initial y coordinate
     */
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
        fdef.filter.maskBits = ChickenVsFood.BUTTER_BIT | ChickenVsFood.FOOD_BIT |ChickenVsFood.MAP_BIT;

        /*fdef.density = 0.5f;
        fdef.friction = 0.4f;
        fdef.restitution = 0.5f;*/

        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * Updates the chicken
     * @param dt
     */
    public abstract void update(float dt);

    public abstract float getVelocity();

    public abstract void draw(SpriteBatch batch);

    public abstract int getHealth();

    public abstract void setHealth(int health);

    public abstract int getDmg();

    /**
     * Sets the hit field with value b
     * @param b boolean
     */
    public  void setHit(boolean b){
        this.hit = b;
    }

    /**
     * @return the value of the hit field
     */
    public boolean getHit(){
        return this.hit;
    }

    /**
     * Sets the foodHit field with value b
     * @param b boolean
     */
    public void setFoodHit(boolean b) {
        this.foodHit = b;
    }
    /**
     * @return the value of the foodHit field
     */
    public boolean getFoodHit(){
        return foodHit;
    }

    /**
     * @return the game world
     */
    public World getWorld(){return this.world;}

    /**
     * @return the body of the chicken
     */
    public Body getBody(){return this.b2body;}

    /**
     * Verifies if the chicken is dead
     * @return true if dead, false if alive
     */
    public boolean isDead() {
        if (getHealth() == 0){
            this.world.destroyBody(this.b2body);
            return true;
        }
        return false;
    }

    /**
     * decreases the health of the chicken by 1
     */
    public void decreaseHealth() {
        setHealth(getHealth()-1);
    }



}
