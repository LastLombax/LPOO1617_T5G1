package com.mygdx.game.Sprites.Chickens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 29/05/2017.
 */

public class SmallChicken extends Chicken {
    public enum State{WALKING, EXPLODED, CHICKS_WALKING,CHICKS_EATING};
    private State currState;
    private State prevState;
    private float VELOCITY = 2.5f;
    private int HEALTH = 5;
    private int DMG= 1;
    private World world;
    private Body b2body;
    private ChickenVsFood game;
    private TextureRegion ChickenTexture;
    private int SIZE_PIXEL = 30;
    private int WORLD_SIZE = 90;
    private float stateTimer = 0;
    private Animation<TextureRegion> chickenWalking;
    private Animation<TextureRegion> chickenEating;

    public SmallChicken(World world, ChickenVsFood game, int xInicial, int yInicial, PlayScreen screen) {
        super(world, game, screen);
        this.game = game;
        super.setFoodHit(false);
        super.setHit(false);
        currState = State.WALKING;
        prevState = State.WALKING;
        super.defineChicken(xInicial, yInicial);
        ChickenTexture = new TextureRegion(screen.getSmallChicken().findRegion("SmallChicken"), 0, 0, SIZE_PIXEL, SIZE_PIXEL);
        setBounds(0, 0, WORLD_SIZE, WORLD_SIZE);
        setRegion(ChickenTexture);
        setAnimations();
    }

    private void setAnimations() {
    }

    @Override
    public void defineChicken(int i, int i1) {

    }

    @Override
    public void update(float v) {

    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public float getVelocity() {
        return 0;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

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
