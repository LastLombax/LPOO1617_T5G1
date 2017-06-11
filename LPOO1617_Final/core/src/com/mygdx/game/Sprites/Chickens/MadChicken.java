package com.mygdx.game.Sprites.Chickens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 29/05/2017.
 */

public class MadChicken extends Chicken {
    public enum State{WALKING, EATING}

    private State currState;
    private State prevState;
    private float VELOCITY = 15f;
    private int HEALTH = 5;
    private TextureRegion ChickenTexture;
    private float stateTimer = 0;
    private Animation<TextureRegion> chickenWalking;
    private Animation<TextureRegion> chickenEating;

    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;
    /**
     * Constructor for the MadChicken
     * @param world game world
     * @param xInicial x coordinate
     * @param yInicial y coordinate
     * @param screen game screen
     */
    public MadChicken(World world,  int xInicial, int yInicial, PlayScreen screen) {
        super(world, screen);
        super.setFoodHit(false);
        currState = State.WALKING;
        prevState = State.WALKING;
        super.defineChicken(xInicial, yInicial);
        ChickenTexture = new TextureRegion(screen.getMadChicken().findRegion("MadChicken"), 0, 0, SIZE_PIXEL, SIZE_PIXEL);
        setBounds(0, 0, WORLD_SIZE, WORLD_SIZE);
        setRegion(ChickenTexture);
        setAnimations();
    }

    /**
     * Sets the animations for a MadChicken
     */
    public void setAnimations(){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 4; i < 7; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        chickenWalking = new Animation<TextureRegion>(0.5f, frames);
        frames.clear();

        for (int i = 0; i < 3; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        chickenEating = new Animation<TextureRegion>(0.5f, frames);
        frames.clear();
    }

    @Override
    public void update(float v) {
        setPosition(super.getBody().getPosition().x - getWidth() / 2, super.getBody().getPosition().y - getWidth() / 2);

        setRegion(getFrame(v));

        super.getBody().applyLinearImpulse(new Vector2(-this.getVelocity(), 0), super.getBody().getWorldCenter(), true);
    }
    /**
     * Returns the current frame/animation of the MadChicken
     */
    private TextureRegion getFrame(float dt) {

        TextureRegion region = chickenWalking.getKeyFrame(stateTimer, true);
        currState = getState();
        if (currState == State.EATING && super.getFoodHit())
            region = chickenEating.getKeyFrame(stateTimer, true);
        else if (currState == State.WALKING)
            region = chickenWalking.getKeyFrame(stateTimer, true);

        stateTimer = currState == prevState ? stateTimer +dt : 0;
        prevState = currState;

        return region;

    }
    /**
     * Returns the current state
     */
    private State getState() {
        if (super.getFoodHit())
            return State.EATING;
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
