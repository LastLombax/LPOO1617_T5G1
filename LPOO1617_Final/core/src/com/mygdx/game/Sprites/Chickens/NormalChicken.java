package com.mygdx.game.Sprites.Chickens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ChickenVsFood;


public class NormalChicken extends Sprite implements Chicken {
    private static final float VELOCITY = 0.2f;
    private int HEALTH = 5;
    private int DMG = 1;
    private World world;
    private Body b2body;
    private ChickenVsFood game;
    private TextureRegion ChickenTexture;

    public NormalChicken(World world, ChickenVsFood game, int xInicial, int yInicial) {
        super(game.getAssetManager().get("Chicken.png", Texture.class));
        this.world = world;
        this.game = game;
        defineChicken(xInicial, yInicial);
        ChickenTexture = new TextureRegion(getTexture(), 0, 0, 28, 40);
        setBounds(0, 0, 28, 40);
        setRegion(ChickenTexture);
    }

    public void defineChicken(int x, int y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setUserData("Normal Chicken");

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);

        fdef.shape = shape;

        /*fdef.density = 0.5f;
        fdef.friction = 0.4f;
        fdef.restitution = 0.5f;*/

        b2body.createFixture(fdef);
    }

    public void update(float dt) {
        //movement
        this.getBody().applyLinearImpulse(new Vector2(-this.getVelocity(), 0), this.getBody().getWorldCenter(), true);

        //position
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getWidth() / 2);
    }

    public Body getBody() {
        return b2body;
    }

    public float getVelocity() {
        return VELOCITY;
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.draw((Batch) batch);
    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public int getDmg() {
        return DMG;
    }

    @Override
    public boolean isDead() {
        if (getHealth() == 0)
            return true;
        return false;
    }

    @Override
    public void decreaseHealth() {
        this.HEALTH--;
        System.out.println("decrementou");
    }
}
