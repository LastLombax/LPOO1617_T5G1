package com.mygdx.game.Sprites.Foods;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 29/05/2017.
 */

public class ExplosiveBarry extends Food {
    public enum State{NORMAL, EXPLOSION}
    private State currState;
    private State prevState;
    private World world;
    private TextureRegion FoodTexture;
    private int x;
    private int y;
    private int HEALTH = 2;
    private float stateTimer;
    private PlayScreen screen;

    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;
    private Animation<TextureRegion> foodNormal;
    private Animation<TextureRegion> foodExploding;

    /**
     * Constructor for an ExplosiveBarry
     * @param world game world
     * @param x x coordinate
     * @param y y coordinate
     * @param screen game screen
     */
    public ExplosiveBarry(World world, int x,int y, PlayScreen screen) {
        super(world, screen);
        this.x = x;
        this.y = y;
        this.world = world;
        this.stateTimer = 0;
        this.screen = screen;
        this.currState = State.NORMAL;
        this.prevState = State.NORMAL;
        super.defineFood(x,y);
        FoodTexture = new TextureRegion(screen.getExplosiveBarry().findRegion("ExplosiveBarry"),0,0,SIZE_PIXEL,SIZE_PIXEL);
        setBounds(0,0,WORLD_SIZE,WORLD_SIZE);
        setRegion(FoodTexture);
        setAnimations();
    }
    /**
     * Sets the animations for an ExplosiveBarry
     */
    private void setAnimations() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 1; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        foodNormal = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 1; i < 4; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        foodExploding = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

    }

    @Override
    public void update(float v) {
        setPosition(super.getBody().getPosition().x - getWidth() / 2, super.getBody().getPosition().y - getWidth() / 2);
        setRegion(getFrame(v));

        if (currState == State.EXPLOSION) {
            this.x = (int) super.getBody().getPosition().x;
            this.y = (int) super.getBody().getPosition().y;
            this.screen.getFoods().add(new InvisibleSeed(this.world, this.x, this.y, this.screen, true));
            this.screen.getFoods().add(new InvisibleSeed(this.world, this.x, this.y, this.screen, false));
            this.setHealth(0);
        }
    }

    private TextureRegion getFrame(float dt) {
        TextureRegion region = foodNormal.getKeyFrame(stateTimer, true);
        currState = getState();
        if (currState == State.NORMAL)
            region = foodNormal.getKeyFrame(stateTimer);
        else if (currState == State.EXPLOSION)
            region = foodExploding.getKeyFrame(stateTimer);

        stateTimer = currState == prevState ? stateTimer + dt : 0;
        prevState = currState;

        return region;
    }


    public State getState() {
        if (super.getHit())
            return State.EXPLOSION;
        else
            return State.NORMAL;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        this.draw( (Batch) spriteBatch);
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
