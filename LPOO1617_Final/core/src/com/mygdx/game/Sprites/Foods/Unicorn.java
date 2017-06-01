package com.mygdx.game.Sprites.Foods;

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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
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
    private Sprite corn;
    private Vector2 cornMovDir;
    private int x;
    private int y;

    public Unicorn(World world, ChickenVsFood game, int x, int y){
        super(world,game);
        setCornInc(false);
        this.timer = 0;
        this.game = game;
        this.x = x;
        this.y = y;
        super.defineFood(x,y);
        ChickenTexture = new TextureRegion(getTexture(),0,0,28,40);
        setBounds(0,0,28,40);
        setRegion(ChickenTexture);
        corn = new Sprite(new Texture("corn.png"),0,0,20,20);
        cornMovDir = new Vector2(Hud.getCornLabel().getX() - super.getBody().getPosition().x,Hud.getCornLabel().getY() - super.getBody().getPosition().y);
        cornMovDir.nor();

    }

    @Override
    public void defineFood(int i, int i1) {
       /* BodyDef bdef = new BodyDef();
        bdef.position.set(i,i1);
<<<<<<< HEAD
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = super.getWorld().createBody(bdef);
        //b2body.setUerData("Food");
=======s
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);
        b2body.setUserData("Unicorn");
>>>>>>> origin/master

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);

        fdef.shape = shape;
        fdef.filter.categoryBits = ChickenVsFood.FOOD_BIT;
        fdef.filter.maskBits = ChickenVsFood.CHICKEN_BIT | ChickenVsFood.FOOD_BIT;

        /*fdef.density = 0.5f;
        fdef.friction = 0.4f;
        fdef.restitution = 0.5f;*/
/*
        b2body.createFixture(fdef);*/
    }

    @Override
    public void update(float v) {
        setPosition(super.getBody().getPosition().x-getWidth()/2,super.getBody().getPosition().y-getWidth()/2);
        timer++;
        //Unicorn's special ability
        if (animateC)
            animateCorn(v);
        if(timer%500 == 0) { // every 5 seconds
            timer = 0;
            System.out.println("New Corn");
            animateC = true;
            corn.setPosition(this.x, this.y);
            animateCorn(v);
        }

        if(this.hiting){
            super.getBody().setLinearVelocity(new Vector2(0,0));
            if(timer%500 == 0){
                decreaseHealth();
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (animateC)
            corn.draw((Batch) batch);

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


    public void hit(){this.hiting = true;}

    public void Nothit(){this.hiting = false;}


    public boolean getCornInc(){
        return cornInc;
    }

    public void setCornInc(boolean b){
        this.cornInc = b;
    }

    public void animateCorn(float dt){
        if (corn.getX() > Hud.getCornLabel().getX() && corn.getY() < Hud.getCornLabel().getY()){
            corn.setPosition(corn.getX() + 5*cornMovDir.x, corn.getY() + 5*cornMovDir.y);
            System.out.println(corn.getX());
            System.out.println(corn.getY());

            game.getBatch().begin();
            corn.draw(game.getBatch());
            game.getBatch().end();
        }
        else {
            System.out.println("chegou");
            Hud.addCorn(50);
            animateC = false;
            corn.setPosition(this.x,this.y);
        }
    }

}
