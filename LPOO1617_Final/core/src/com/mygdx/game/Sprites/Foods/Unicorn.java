package com.mygdx.game.Sprites.Foods;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 29/05/2017.
 */

public class Unicorn extends Food {
    public enum State{NORMAL, DYING, DYING2, DYING3}
    private State currState;
    private State prevState;
    private ChickenVsFood game;
    private TextureRegion FoodTexture;
    private int ADD_CORN = 5;
    private int HEALTH = 5;
    private int timer;
    private int DMG_SECONDS = 100;
    private int SPAWN_CORN = 500;
    private boolean cornInc;
    private boolean animateC = false;
    private Corn corn;
    private Vector2 cornMovDir;
    private int x;
    private int y;
    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;
    private float stateTimer = 0;
    private Animation<TextureRegion> foodNormal;
    private Animation<TextureRegion> foodDying;
    private Animation<TextureRegion> foodDying2;
    private Animation<TextureRegion> foodDying3;

    /**
     * Constructor for the Unicorn
     * @param world game world
     * @param game ChickenVsFood instance
     * @param x x coordinate
     * @param y y coordinate
     * @param screen game screen
     */
    public Unicorn(World world, ChickenVsFood game, int x, int y, PlayScreen screen){
        super(world,game, screen);
        setCornInc(false);
        this.timer = 0;
        this.game = game;
        this.x = x;
        this.y = y;
        this.currState = State.NORMAL;
        this.prevState = State.NORMAL;
        super.defineFood(x,y);
        FoodTexture = new TextureRegion(screen.getUnicorn().findRegion("Unicorn"),0,0,SIZE_PIXEL,SIZE_PIXEL);
        setBounds(0,0,WORLD_SIZE,WORLD_SIZE);
        setRegion(FoodTexture);
        cornMovDir = new Vector2(Hud.getCornLabel().getX() - super.getBody().getPosition().x,Hud.getCornLabel().getY() - super.getBody().getPosition().y);
        setAnimations();
    }
    /**
     * Sets the animations for a Unicorn
     */
    private void setAnimations() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 1; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        foodNormal = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 1; i < 2; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        foodDying = new Animation<TextureRegion>(0.5f, frames);
        frames.clear();

        for (int i = 2; i < 3; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        foodDying2 = new Animation<TextureRegion>(0.5f, frames);
        frames.clear();

        for (int i = 3; i < 4; i++)
            frames.add(new TextureRegion(super.getTexture(), i*SIZE_PIXEL, 0, SIZE_PIXEL, SIZE_PIXEL));
        foodDying3 = new Animation<TextureRegion>(0.5f, frames);
        frames.clear();
    }

    @Override
    public void update(float v) {
        setPosition(super.getBody().getPosition().x-getWidth()/2,super.getBody().getPosition().y-getWidth()/2);
        setRegion(getFrame(v));
        timer++;

        if (animateC)
            if (corn.update(v)) {
                corn = null;
                animateC = false;
                Hud.addCorn(ADD_CORN);
            }

        if(timer%SPAWN_CORN == 0) {
            timer = 0;
            corn = new Corn(this.x,this.y, cornMovDir);
            animateC = true;
        }

        if(super.getHit())
            if(timer%DMG_SECONDS == 0)
                decreaseHealth();
    }

    /**
     * Returns the current frame/animation of the Unicorn
     */
    private TextureRegion getFrame(float dt) {

        TextureRegion region = foodNormal.getKeyFrame(stateTimer, true);
        currState = getState();
        if (currState == State.NORMAL)
            region = foodNormal.getKeyFrame(stateTimer, true);
        else if (currState == State.DYING)
            region = foodDying.getKeyFrame(stateTimer, true);
        else if (currState == State.DYING2)
            region = foodDying2.getKeyFrame(stateTimer, true);
        else if (currState == State.DYING3)
            region = foodDying3.getKeyFrame(stateTimer, true);

        stateTimer = currState == prevState ? stateTimer +dt : 0;
        prevState = currState;

        return region;

    }
    /**
     * Returns the current state
     */
    private State getState() {
        if (getHealth() == 5)
            return State.NORMAL;
        else if (getHealth() == 4)
            return State.DYING;
        else if (getHealth() == 2 || getHealth() == 3)
            return State.DYING2;
        else
            return State.DYING3;

    }

    @Override
    public void draw(SpriteBatch batch) {
        this.draw( (Batch) batch);
        if (corn != null)
            corn.draw(batch);
    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public void setHealth(int health) {
        this.HEALTH = health;
    }


    public void setCornInc(boolean b){
        this.cornInc = b;
    }

}
