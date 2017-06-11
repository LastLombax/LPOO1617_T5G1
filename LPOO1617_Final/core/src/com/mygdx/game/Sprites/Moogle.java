package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.GameOverScreen;

/**
 * Created by vitor on 10/06/2017.
 */

public class Moogle extends Sprite {
    public enum State{STANDING, WALKING}
    private State currState;
    private State prevState;
    private int timer;
    private World world;
    private Body b2body;
    private float VELOCITY = 5f;
    private TextureRegion ChickenTexture;
    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 150;
    private float stateTimer = 0;
    private Animation<TextureRegion> moogleStanding;
    private Animation<TextureRegion> moogleWalking;
    private boolean animate = false;

    public Moogle(World world, int xInicial, int yInicial, GameOverScreen screen) {
        this.world = world;
        currState = State.STANDING;
        prevState = State.STANDING;
        defineMoogle(xInicial, yInicial);
        ChickenTexture = new TextureRegion(screen.getMoogle().findRegion("Moogle"), 0, 0, SIZE_PIXEL, SIZE_PIXEL);
        setBounds(0, 0, WORLD_SIZE, WORLD_SIZE);
        setRegion(ChickenTexture);
        setAnimations();
    }

    public void defineMoogle(int x, int y){
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
     * Sets the animations for a NormalChicken
     */
    public void setAnimations(){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 5; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        moogleStanding = new Animation<TextureRegion>(0.6f, frames);
        frames.clear();

        for (int i = 6; i < 8; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        moogleWalking = new Animation<TextureRegion>(0.7f, frames);
        frames.clear();
    }


    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getWidth() / 2);

        timer++;
        if(animate)
            setRegion(getFrame(dt));

        if (currState == State.WALKING)
            b2body.applyLinearImpulse(new Vector2(this.getVelocity(), 0), b2body.getWorldCenter(), true);

        if (timer%50 == 0)
            animate = true;


        if (animate && timer%200 == 0){
            currState = State.WALKING;
            prevState = State.STANDING;
        }
    }

    /**
     * Returns the current frame/animation of the NormalChicken
     */
    private TextureRegion getFrame(float dt) {

        TextureRegion region = moogleStanding.getKeyFrame(stateTimer, true);
        if (currState == State.STANDING)
            region = moogleStanding.getKeyFrame(stateTimer);
        else if (currState == State.WALKING)
            region = moogleWalking.getKeyFrame(stateTimer, true);

        stateTimer = currState == prevState ? stateTimer +dt : 0;
        prevState = currState;

        return region;

    }

    public float getVelocity() {
        return VELOCITY;
    }


}
