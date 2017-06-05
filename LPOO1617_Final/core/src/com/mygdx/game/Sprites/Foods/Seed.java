package com.mygdx.game.Sprites.Foods;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 30/05/2017.
 */

public class Seed extends Food{
    private World world;
    private Body b2body;
    private TextureRegion FoodTexture;
    private float VELOCITY = 10f;
    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;
    private int HEALTH = 1;
    /**
     * Constructor for a Seed
     * @param w game world
     * @param g ChickenVsFood instance
     * @param x x coordinate
     * @param y y coordinate
     */
    public Seed(World w, ChickenVsFood g, int x, int y, PlayScreen screen){
        super(w,g, screen);
        super.setHit(false);
        this.world = w;
        defineFood(x+80,y);
        FoodTexture = new TextureRegion(screen.getSeed().findRegion("Seed"),0,0,SIZE_PIXEL,SIZE_PIXEL);
        setBounds(0,0,WORLD_SIZE,WORLD_SIZE);
        setRegion(FoodTexture);
    }

    /**
     * Defines a food's body
     * @param x x coordinate
     * @param y y coordinate
     */
    public void defineFood(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        this.b2body = this.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);

        fdef.shape = shape;
        fdef.filter.categoryBits = ChickenVsFood.FOOD_BIT;
        fdef.filter.maskBits = ChickenVsFood.CHICKEN_BIT ;

        this.b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void update(float dt) {
        setPosition(this.getBody().getPosition().x - getWidth() / 2, this.getBody().getPosition().y - getWidth() / 2);
        this.getBody().applyLinearImpulse(new Vector2(this.VELOCITY, 0), this.getBody().getWorldCenter(), true);
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.draw( (Batch) batch);
    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public void setHealth(int health) {
        this.HEALTH = health;
    }

    public Body getBody(){
        return b2body;
    }

    /**
     * Verifies if Seed is dead
     * @return true if dead, false if not
     */
    public boolean isDead() {
        if (getHealth() == 0){
            this.world.destroyBody(this.b2body);
            return true;
        }
        return false;
    }
}
