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

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);


        fdef.shape = shape;
        fdef.filter.categoryBits = ChickenVsFood.CHICKEN_BIT;
        fdef.filter.maskBits = ChickenVsFood.BUTTER_BIT | ChickenVsFood.FOOD_BIT |ChickenVsFood.MAP_BIT;

        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * Updates the chicken, it's animations and position
     * @param dt
     */
    public abstract void update(float dt);

    /**
     * Returns the velocity
     */
    public abstract float getVelocity();
    /**
     * Draws the chicken
     */
    public abstract void draw(SpriteBatch batch);
    /**
     * Returns the health
     */
    public abstract int getHealth();

    /**
     * Sets the health field to the value health
     * @param health new value of health field
     */
    public abstract void setHealth(int health);

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
