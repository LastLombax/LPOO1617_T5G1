package com.mygdx.game.Sprites.Foods;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;

public class SeedShooter extends Food {
    public enum State{NORMAL, DYING, DYING2, DYING3}
    private State currState;
    private State prevState;
    private TextureRegion FoodTexture;
    private int HEALTH = 5;
    private int timer;
    private int seedTimer;
    private int SEED_SECONDS = 200;
    private int DMG_SECONDS = 100;
    private int x;
    private int y;
    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;
    private float stateTimer = 0;
    private World world;
    private PlayScreen screen;
    private Animation<TextureRegion> foodNormal;
    private Animation<TextureRegion> foodDying;
    private Animation<TextureRegion> foodDying2;
    private Animation<TextureRegion> foodDying3;

    /**
     * Constructor for a SeedShooter
     * @param world game world
     * @param xInicial x coordinate
     * @param yInicial y coordinate
     */
    public SeedShooter(World world, int xInicial,int yInicial, PlayScreen screen) {
        super(world,screen);
        this.timer = 0;
        this.seedTimer = 0;
        this.world = world;
        this.screen = screen;
        this.x = xInicial;
        this.y = yInicial;
        super.setHit(false);
        super.defineFood(xInicial, yInicial);
        FoodTexture = new TextureRegion(screen.getSeedShooter().findRegion("SeedShooter"),0,0,SIZE_PIXEL,SIZE_PIXEL);
        setBounds(0, 0, WORLD_SIZE, WORLD_SIZE);
        setRegion(FoodTexture);
        setAnimations();
    }

    /**
     * Sets the animations for the SeedShooter
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
    public void update(float dt){
        setPosition(super.getBody().getPosition().x-getWidth()/2,super.getBody().getPosition().y-getWidth()/2);
        setRegion(getFrame(dt));
        timer++;
        seedTimer++;
       if(seedTimer%SEED_SECONDS == 0)
           this.screen.getFoods().add(new Seed(this.world, this.x,this.y, this.screen));

        if(super.getHit())
            if(timer%DMG_SECONDS == 0)
                decreaseHealth();
    }

    /**
     * Returns the current frame/animation of the SeedShooter
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
    public void draw(SpriteBatch batch) { this.draw( (Batch) batch); }


    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public void setHealth(int health) {
        this.HEALTH = health;
    }
}
