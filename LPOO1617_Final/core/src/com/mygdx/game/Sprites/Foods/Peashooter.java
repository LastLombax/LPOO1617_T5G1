package com.mygdx.game.Sprites.Foods;


import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.game.Sprites.PeaBullet;


public class Peashooter extends Food {
    private float VELOCITY = 0f;
    private ChickenVsFood game;
    private TextureRegion ChickenTexture;
    private int HEALTH = 5;
    private int timer;
    private boolean hiting = false;
    private int x;
    private int y;

    public Peashooter(World world,ChickenVsFood game,int xInicial,int yInicial){
        super(world,game);
        this.timer = 0;
        this.game = game;
        this.x = xInicial;
        this.y = yInicial;
        super.setHit(false);
        super.defineFood(xInicial,yInicial);
        ChickenTexture = new TextureRegion(getTexture(),0,0,28,40);
        setBounds(0,0,28,40);
        setRegion(ChickenTexture);
    }

    public void defineFood(int x, int y){
        /*BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);


        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);

        fdef.shape = shape;
        fdef.filter.categoryBits = ChickenVsFood.FOOD_BIT;
        fdef.filter.maskBits = ChickenVsFood.CHICKEN_BIT | ChickenVsFood.FOOD_BIT;

        /*fdef.density = 0.5f;
        fdef.friction = 0.4f;
        fdef.restitution = 0.5f;*/

        /*b2body.createFixture(fdef).setUserData(this);*/
    }

    public void update(float dt){
        setPosition(super.getBody().getPosition().x-getWidth()/2,super.getBody().getPosition().y-getWidth()/2);

        timer++;
        //implement a thread for each unicorn to send corns
        if(timer%100 == 0) { // every second
           // new PeaBullet(this.world,this.game,this.x,this.y);
            System.out.println("bala incoming");

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
        this.draw( (Batch) batch);
    }

    public void hit(){this.hiting = true;}
    public void Nothit(){this.hiting = false;}

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public void setHealth(int health) {
        this.HEALTH = health;
    }
}
