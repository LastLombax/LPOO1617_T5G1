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
    
    public Chicken(World world, ChickenVsFood game, PlayScreen screen){
        super(screen.getEnemiesAtlas().findRegion("chocobo"));
        setFoodHit(false);
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
        fdef.filter.maskBits = ChickenVsFood.BUTTER_BIT | ChickenVsFood.FOOD_BIT |ChickenVsFood.MAP_BIT;

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

    public  void setHit(boolean b){
        this.hit = b;
    }

    public boolean getHit(){
        return this.hit;
    }
    public void setFoodHit(boolean b) {
        this.foodHit = b;
    }

    public boolean getFoodHit(){
        return foodHit;
    }

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
