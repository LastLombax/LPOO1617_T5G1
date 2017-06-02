package com.mygdx.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Sprites.Butter;
import com.mygdx.game.Sprites.Chickens.Chicken;
import com.mygdx.game.Sprites.Chickens.NormalChicken;
import com.mygdx.game.Sprites.Foods.Food;
import com.mygdx.game.Sprites.Foods.Peashooter;
import com.mygdx.game.Sprites.PeaBullet;

import java.awt.event.ContainerListener;


public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        System.out.println("begin contact");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if ((fixA.getUserData() instanceof Butter )&& (fixB.getUserData() instanceof Chicken)){
            //butter and chicken
            ((Chicken) fixB.getUserData()).setHealth(0);
            ((Butter) fixA.getUserData()).hit();
        }
        else if((fixB.getUserData() instanceof Butter )&& (fixA.getUserData() instanceof Chicken)){
            //butter and chicken
            ((Chicken) fixB.getUserData()).setHealth(0);
        }
        else if (fixA.getUserData() instanceof Chicken){
            //chicken and food
            if (fixB.getUserData() instanceof Food){
                ((Chicken)fixA.getUserData()).setHit(true);
                ((Chicken)fixA.getUserData()).setFoodHit(true);
                ((Food)fixB.getUserData()).setHit(true);
                ((Food)fixB.getUserData()).decreaseHealth();
            }
            //chicken and pea
            else if (fixB.getUserData() instanceof PeaBullet)
                ((Chicken) fixA.getUserData()).decreaseHealth();
        }
        else if (fixB.getUserData() instanceof Chicken){
            //chicken and pea
            if (fixA.getUserData() instanceof Food) {
                ((Chicken)fixB.getUserData()).setHit(true);
                ((Chicken)fixB.getUserData()).setFoodHit(true);
                ((Food)fixA.getUserData()).setHit(true);
                ((Food)fixA.getUserData()).decreaseHealth();
            }
            else if (fixA.getUserData() instanceof PeaBullet)
                ((Chicken) fixB.getUserData()).decreaseHealth();
        }
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("end contact");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof Chicken){
            //chicken and food
            if (fixB.getUserData() instanceof Food) {
                ((Chicken) fixA.getUserData()).setFoodHit(false);
                ((Food) fixB.getUserData()).setHit(false);
            }
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
