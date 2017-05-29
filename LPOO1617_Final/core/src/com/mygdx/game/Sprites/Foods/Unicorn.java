package com.mygdx.game.Sprites.Foods;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

public class Unicorn extends Sprite implements Food {
    private World world;
    private Body b2body;
    private ChickenVsFood game;
    private TextureRegion ChickenTexture;
    private int HEALTH = 5;
    private int timer;
    private boolean cornInc;

    public Unicorn(World world, ChickenVsFood game, int x, int y) {
        super(game.getAssetManager().get("Chicken.png",Texture.class));
        setCornInc(false);
        this.timer = 0;
        this.world = world;
        this.game = game;
        defineFood(x,y);
        ChickenTexture = new TextureRegion(getTexture(),0,0,28,40);
        setBounds(0,0,28,40);
        setRegion(ChickenTexture);
    }

    @Override
    public void defineFood(int i, int i1) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(i,i1);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setUserData("Food");

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);

        fdef.shape = shape;

        /*fdef.density = 0.5f;
        fdef.friction = 0.4f;
        fdef.restitution = 0.5f;*/

        b2body.createFixture(fdef);
    }

    @Override
    public void update(float v) {
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getWidth()/2);
        timer++;
        //implement a thread for each unicorn to send corns
        if(timer%500 == 0) { // every 5 seconds
            System.out.println("merdices");
            Hud.addCorn(1);
            timer = 0;
        }
    }

    @Override
    public Body getBody() {
        return b2body;
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.draw( (Batch) batch);
    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    public boolean getCornInc(){
        return cornInc;
    }

    public void setCornInc(boolean b){
        this.cornInc = b;
    }

}
