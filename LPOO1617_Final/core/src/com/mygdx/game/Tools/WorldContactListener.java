package com.mygdx.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Sprites.Chickens.Chicken;
import com.mygdx.game.Sprites.Chickens.NormalChicken;
import com.mygdx.game.Sprites.Foods.Food;
import com.mygdx.game.Sprites.Foods.Peashooter;

import java.awt.event.ContainerListener;

/**
 * Created by vicen on 30/05/2017.
 */

public class WorldContactListener implements ContactListener{

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case ChickenVsFood.CHICKEN_BIT | ChickenVsFood.FOOD_BIT:
                if(fixA.getFilterData().categoryBits == ChickenVsFood.CHICKEN_BIT){
                    ((Chicken)fixA.getUserData()).hit();
                    ((Food)fixB.getUserData()).hit();
                }else if(fixB.getFilterData().categoryBits == ChickenVsFood.CHICKEN_BIT){
                    ((Chicken)fixB.getUserData()).hit();
                    ((Food)fixA.getUserData()).hit();
                }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
