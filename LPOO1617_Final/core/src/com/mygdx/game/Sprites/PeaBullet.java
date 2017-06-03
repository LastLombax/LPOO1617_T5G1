package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.Foods.Peashooter;

/**
 * Created by vitor on 30/05/2017.
 */

public class PeaBullet extends Peashooter{
    private World world;
    private Body b2body;
    private ChickenVsFood game;
    private TextureRegion PeaTexture;
    private Texture Pea = new Texture("butter.png");
    private float VELOCITY = 0.1f;


    /**
     * Constructor for the PeaBullet
     * @param w game world
     * @param g ChickenVsFood instance
     * @param x x coordinate
     * @param y y coordinate
     */
    public PeaBullet(World w, ChickenVsFood g, int x, int y, PlayScreen screen){
        super(w,g,x,y, screen);
        definePea(x,y);
        PeaTexture = new TextureRegion(Pea,0,0,28,40);
        setBounds(0,0,28,40);
        setRegion(PeaTexture);
    }

    /**
     * Defines the Bullet/Pea
     * @param x x coordinate
     * @param y x coordinate
     */
   public void definePea(int x, int y){
       BodyDef bdef = new BodyDef();
       bdef.position.set(x,y);
       bdef.type = BodyDef.BodyType.StaticBody;
       b2body = world.createBody(bdef);
       b2body.setUserData("Pea");

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
    public void update(float dt) {
        super.update(dt);
        this.getBody().applyLinearImpulse(new Vector2(this.VELOCITY, 0),this.getBody().getWorldCenter(), true);

    }
}
