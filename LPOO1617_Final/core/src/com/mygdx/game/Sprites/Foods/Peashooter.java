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


public class Peashooter extends Sprite implements Food {
    public World world;
    public Body b2body;
    public ChickenVsFood game;
    private TextureRegion ChickenTexture;

    public Peashooter(World world,ChickenVsFood game,int xInicial,int yInicial){
        super(game.getAssetManager().get("Chicken.png",Texture.class));
        this.world = world;
        this.game = game;
        defineFood(xInicial,yInicial);
        ChickenTexture = new TextureRegion(getTexture(),0,0,28,40);
        setBounds(0,0,28,40);
        setRegion(ChickenTexture);
    }

    public void defineFood(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
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

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getWidth()/2);
    }

    @Override
    public Body getBody() {
        return b2body;
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.draw( (Batch) batch);
    }
}