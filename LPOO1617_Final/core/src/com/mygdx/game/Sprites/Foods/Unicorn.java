package com.mygdx.game.Sprites.Foods;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Scenes.Hud;

/**
 * Created by vitor on 29/05/2017.
 */

public class Unicorn extends Food {
    private ChickenVsFood game;
    private TextureRegion ChickenTexture;
    private int HEALTH = 5;
    private int timer;
    private boolean cornInc;
    private boolean hiting = false;
    private boolean animateC = false;
    private Corn corn;
    private Vector2 cornMovDir;
    private int x;
    private int y;

    /**
     * Constructor for the Unicorn
     * @param world game world
     * @param game ChickenVsFood instance
     * @param x x coordinate
     * @param y y coordinate
     */
    public Unicorn(World world, ChickenVsFood game, int x, int y){
        super(world,game);
        setCornInc(false);
        this.timer = 0;
        this.game = game;
        this.x = x;
        this.y = y;
        super.setHit(false);
        super.defineFood(x,y);
        ChickenTexture = new TextureRegion(getTexture(),0,0,28,40);
        setBounds(0,0,28,40);
        setRegion(ChickenTexture);
        cornMovDir = new Vector2(Hud.getCornLabel().getX() - super.getBody().getPosition().x,Hud.getCornLabel().getY() - super.getBody().getPosition().y);
    }

    @Override
    public void update(float v) {
        setPosition(super.getBody().getPosition().x-getWidth()/2,super.getBody().getPosition().y-getWidth()/2);
        timer++;
        //Unicorn's special ability
        if (animateC)
            if (corn.update(v)) {//ended
                corn = null;
                animateC = false;
                Hud.addCorn(50);
            }

        if(timer%500 == 0) { // every 5 seconds
            System.out.println("New Corn");
            timer = 0;
            corn = new Corn(this.x,this.y, cornMovDir);
            animateC = true;
        }

        if(super.getHit()){
            super.getBody().setLinearVelocity(new Vector2(0,0));
            if(timer%100 == 0){
                decreaseHealth();
            }
        }
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

    public boolean getCornInc(){
        return cornInc;
    }

    public void setCornInc(boolean b){
        this.cornInc = b;
    }


}
