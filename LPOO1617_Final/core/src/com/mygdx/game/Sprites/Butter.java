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
    private TextureRegion ButterTexture;
    private Texture Butter = new Texture("Butter.png");
    private float VELOCITY = 100f;
    private boolean hitting = false;
    private int SIZE_PIXEL = 120;
    private int WORLD_SIZE = 100;
    private int LIMIT_X = 2000;
    private boolean destroy = false;

    /**
     * Constructor for the Butter
     * @param world game world
     * @param x x coordinate
     * @param y y coordinate
     */
    public Butter(World world, int x, int y){
        this.world = world;
        defineButter(x,y);
        ButterTexture = new TextureRegion(Butter,0,0,SIZE_PIXEL,SIZE_PIXEL);
        setBounds(0,0,WORLD_SIZE,WORLD_SIZE);
        setRegion(ButterTexture);
    }

    /**
     * Defines the Butter's body
     * @param x x coordinate
     * @param y y coordinate
     */
    public void defineButter(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);
        fdef.shape = shape;
        fdef.filter.categoryBits = ChickenVsFood.BUTTER_BIT;
        fdef.filter.maskBits = ChickenVsFood.CHICKEN_BIT;

        this.b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * @return Returns the body
     */
    public Body getBody() {
        return b2body;
    }

    /**
     * Updates the butter's position
     */
    public void update() {
        setPosition(getBody().getPosition().x - getWidth() / 2, getBody().getPosition().y - getWidth() / 2);
        if(getHit())
            this.getBody().applyLinearImpulse(new Vector2(this.VELOCITY, 0),this.getBody().getWorldCenter(), true);

        if (getBody().getPosition().x > LIMIT_X)
            destroy = true;
    }

    /**
     * Sets the field hitting with the value b
     * @param b new value for the field hitting
     */
    public void setHit(boolean b){
        this.hitting = b;
    }

    /**
     * @return Returns the value hitting
     */
    public boolean getHit(){
        return this.hitting;
    }

    /**
     * Verifies if butter has passed a limit
     * @return true if to destroy, false if not
     */
    public boolean isDead() {
        if (destroy){
            this.world.destroyBody(this.b2body);
            return true;
        }
        return false;
    }

}



