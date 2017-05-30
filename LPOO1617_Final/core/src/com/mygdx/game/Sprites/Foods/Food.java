package com.mygdx.game.Sprites.Foods;

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

public abstract class Food extends Sprite {

    private Body b2body;
    private World world;

    public Food(World world, ChickenVsFood game){
        super(game.getAssetManager().get("Chicken.png",Texture.class));
        this.world = world;
    }

     public  void  defineFood(int x, int y){
         BodyDef bdef = new BodyDef();
         bdef.position.set(x,y);
         bdef.type = BodyDef.BodyType.DynamicBody;
         this.b2body = world.createBody(bdef);
         //this.b2body.setUserData("Food");

         FixtureDef fdef = new FixtureDef();
         CircleShape shape = new CircleShape();
         shape.setRadius(35);

         fdef.shape = shape;
         fdef.filter.categoryBits = ChickenVsFood.FOOD_BIT;
         fdef.filter.maskBits = ChickenVsFood.CHICKEN_BIT ;

        /*fdef.density = 0.5f;
        fdef.friction = 0.4f;
        fdef.restitution = 0.5f;*/

         this.b2body.createFixture(fdef).setUserData(this);
     };

    public abstract void update(float dt);

    public abstract void draw(SpriteBatch batch);

    public abstract int getHealth();

    public abstract void hit();

    public World getWorld(){return this.world;}

    public Body getBody(){return this.b2body;}

}
