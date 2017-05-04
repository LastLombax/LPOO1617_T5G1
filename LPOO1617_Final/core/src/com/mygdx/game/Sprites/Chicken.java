package com.mygdx.game.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.ChickenVsFood;


public class Chicken extends Sprite{
    public World world;
    public Body b2body;
    public ChickenVsFood game;
    private TextureRegion ChickenTexture;

    public Chicken(World world,ChickenVsFood game){
        super(game.getAssetManager().get("Chicken.png",Texture.class));
        this.world = world;
        this.game = game;
        defineChicken();
        ChickenTexture = new TextureRegion(getTexture(),0,0,28,40);
        setBounds(0,0,28/100,40/100);//PPM
        setRegion(ChickenTexture);
    }

    public void defineChicken(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(2000/ChickenVsFood.PPM,570/ChickenVsFood.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35/ChickenVsFood.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getWidth()/2);
    }
}
