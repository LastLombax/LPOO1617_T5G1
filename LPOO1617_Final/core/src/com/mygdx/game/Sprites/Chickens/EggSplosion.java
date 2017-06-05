package com.mygdx.game.Sprites.Chickens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 29/05/2017.
 */

public class EggSplosion extends Chicken {
    public enum State{WALKING, EXPLOSION};
    private State currState;
    private State prevState;
    private float VELOCITY = 2.5f;
    private int HEALTH = 5;
    private int timer = 0;
    private ChickenVsFood game;
    private TextureRegion ChickenTexture;
    private float stateTimer = 0;
    private Animation<TextureRegion> chickenWalking;
    private Animation<TextureRegion> chickenExplosion;

    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;

    /**
     * Constructor for the EggSplosion
     * @param world game world
     * @param game ChickenVsFood instance
     * @param xInicial x coordinate
     * @param yInicial y coordinate
     * @param screen game screen
     */
    public EggSplosion(World world, ChickenVsFood game, int xInicial, int yInicial, PlayScreen screen) {
        super(world, game, screen);
        this.game = game;
        super.setFoodHit(false);
        currState = State.WALKING;
        prevState = State.WALKING;
        super.defineChicken(xInicial, yInicial);
        ChickenTexture = new TextureRegion(screen.getEggSplosion().findRegion("EggSplosion"), 0, 0, SIZE_PIXEL, SIZE_PIXEL);
        setBounds(0, 0, WORLD_SIZE, WORLD_SIZE);
        setRegion(ChickenTexture);
        setAnimations();
    }

    /**
     * Sets the animations for an EggSplosion
     */
    public void setAnimations(){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 6; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        chickenWalking = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 7; i < 8; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));

        chickenExplosion = new Animation<TextureRegion>(2f, frames);
        frames.clear();
    }

    @Override
    public void update(float v) {
        setPosition(super.getBody().getPosition().x - getWidth() / 2, super.getBody().getPosition().y - getWidth() / 2);

        setRegion(getFrame(v));

        super.getBody().applyLinearImpulse(new Vector2(-this.getVelocity(), 0), super.getBody().getWorldCenter(), true);

        if (currState == State.EXPLOSION) {
            timer++;
            if (timer % 100 == 0)
                this.setHealth(0);
        }
    }
    /**
     * Returns the current frame/animation of the EggSplosion
     */
    private TextureRegion getFrame(float dt) {

        TextureRegion region = chickenWalking.getKeyFrame(stateTimer, true);
        currState = getState();
        if (currState == State.EXPLOSION)
            region = chickenExplosion.getKeyFrame(stateTimer, true);
        else if (currState == State.WALKING) {
            region = chickenWalking.getKeyFrame(stateTimer, true);

        }

        stateTimer = currState == prevState ? stateTimer +dt : 0;
        prevState = currState;

        return region;

    }
    /**
     * Returns the current state
     */
    private State getState() {
        if (super.getFoodHit())
            return State.EXPLOSION;
        else
            return State.WALKING;

    }

    @Override
    public float getVelocity() {
        return VELOCITY;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        this.draw((Batch) spriteBatch);
    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public void setHealth(int health) {
        this.HEALTH = health;
    }


}
