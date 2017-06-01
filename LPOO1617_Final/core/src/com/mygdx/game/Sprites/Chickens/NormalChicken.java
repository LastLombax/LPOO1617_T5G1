package com.mygdx.game.Sprites.Chickens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;


public class NormalChicken extends Chicken {
    private float VELOCITY = 10f;
    private int HEALTH = 5;
    private int DMG = 1;
    private ChickenVsFood game;
    private TextureRegion ChickenTexture;
    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;
    private boolean hiting = false;
    private float stateTimer = 0;
    private Animation<TextureRegion> chickenWalking;
    private Animation<TextureRegion> chickenEating;

    public NormalChicken(World world, ChickenVsFood game, int xInicial, int yInicial, PlayScreen screen) {
        super(world,game, screen);
        this.game = game;
        setFoodHit(false);
        super.defineChicken(xInicial, yInicial);
        ChickenTexture = new TextureRegion(super.getTexture(), 0, 0, SIZE_PIXEL, SIZE_PIXEL);
        setBounds(0, 0, WORLD_SIZE, WORLD_SIZE);
        setRegion(ChickenTexture);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 3; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        chickenWalking = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 6; i < 8; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        chickenEating = new Animation<TextureRegion>(0.5f, frames);
        frames.clear();

    }

    public void defineChicken(int x, int y) {
        /*BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
<<<<<<< HEAD
        //b2body.setUserData("Chicken");
=======
        b2body.setUserData("Normal Chicken");
>>>>>>> origin/master

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);


        fdef.shape = shape;
        fdef.filter.categoryBits = ChickenVsFood.CHICKEN_BIT;
        fdef.filter.maskBits = ChickenVsFood.CHICKEN_BIT | ChickenVsFood.BUTTER_BIT | ChickenVsFood.FOOD_BIT;

        /*fdef.density = 0.5f;
        fdef.friction = 0.4f;
        fdef.restitution = 0.5f;*/

       /* b2body.createFixture(fdef).setUserData(this);*/
    }

    public void update(float dt) {
        setPosition(super.getBody().getPosition().x - getWidth() / 2, super.getBody().getPosition().y - getWidth() / 2);
        setRegion(getFrame(dt));

        //movement
        super.getBody().applyLinearImpulse(new Vector2(-this.getVelocity(), 0), super.getBody().getWorldCenter(), true);


        if(this.hiting){
            super.getBody().setLinearVelocity(new Vector2(0,0));
            this.VELOCITY = 0f;
        }
    }

   private TextureRegion getFrame(float dt) {
       TextureRegion region;
        if (super.getFoodHit())
            region = chickenEating.getKeyFrame(stateTimer, true);
       else
            region = chickenWalking.getKeyFrame(stateTimer, true);

       stateTimer+=dt;
       return region;

   }

    public float getVelocity() {
        return VELOCITY;
    }



    public void hit(){this.hiting = true;}
    public void Nothit(){this.hiting = false; this.setFoodHit(false);}

    @Override
    public void draw(SpriteBatch batch) {
        this.draw((Batch) batch);
    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public void setHealth(int health) {
        this.HEALTH = health;
    }

    @Override
    public int getDmg() {
        return DMG;
    }
}
