package com.mygdx.game.Tools;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.Butter;
import com.mygdx.game.Sprites.Chickens.Chicken;
import com.mygdx.game.Sprites.Chickens.EggSplosion;
import com.mygdx.game.Sprites.Chickens.SmallChickenEgg;
import com.mygdx.game.Sprites.Foods.ExplosiveBarry;
import com.mygdx.game.Sprites.Foods.Food;
import com.mygdx.game.Sprites.Foods.InvisibleSeed;
import com.mygdx.game.Sprites.Foods.Seed;


public class WorldContactListener implements ContactListener {
    /**
     * Detects contact between all objects
     * @param contact Contact
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if ((fixA.getUserData() instanceof Butter )&& (fixB.getUserData() instanceof Chicken)){
            //butter and chicken
            ((Butter) fixA.getUserData()).setHit(true);
            ((Chicken) fixB.getUserData()).setHealth(0);
        }
        else if((fixB.getUserData() instanceof Butter )&& (fixA.getUserData() instanceof Chicken)){
            //butter and chicken
            ((Chicken) fixA.getUserData()).setHealth(0);
            ((Butter) fixB.getUserData()).setHit(true);
        }
        else if ((fixA.getUserData() instanceof InvisibleSeed)&& (fixB.getUserData() instanceof Chicken)){
            //butter and chicken
            ((InvisibleSeed) fixA.getUserData()).setHealth(0);
            ((Chicken) fixB.getUserData()).setHealth(0);
        }
        else if((fixB.getUserData() instanceof InvisibleSeed )&& (fixA.getUserData() instanceof Chicken)){
            //butter and chicken
            ((Chicken) fixA.getUserData()).setHealth(0);
            ((InvisibleSeed) fixB.getUserData()).setHealth(0);
        }

        else if((fixB.getUserData() instanceof B2WorldCreator)&& (fixA.getUserData() instanceof Chicken))
            PlayScreen.setGameOver();

        else if((fixA.getUserData() instanceof B2WorldCreator)&& (fixB.getUserData() instanceof Chicken))
            PlayScreen.setGameOver();

        else if (fixA.getUserData() instanceof Chicken){
            //chicken and food
            if (fixB.getUserData() instanceof Seed) {
                ((Seed) fixB.getUserData()).setHealth(0);
                ((Chicken) fixA.getUserData()).decreaseHealth();
            }
            else if ((fixB.getUserData() instanceof ExplosiveBarry)) {
                ((ExplosiveBarry) fixB.getUserData()).setHit(true);
                ((Chicken) fixA.getUserData()).setHealth(0);
            }

            else if (fixB.getUserData() instanceof Food){
                ((Chicken)fixA.getUserData()).setFoodHit(true);
                ((Food)fixB.getUserData()).setHit(true);
                ((Food)fixB.getUserData()).decreaseHealth();
            }
        }
        else if (fixB.getUserData() instanceof Chicken){

            if (fixA.getUserData() instanceof Seed) {
                ((Seed) fixA.getUserData()).setHealth(0);
                ((Chicken) fixB.getUserData()).decreaseHealth();
            }
            else if ((fixA.getUserData() instanceof ExplosiveBarry)) {
                ((ExplosiveBarry) fixA.getUserData()).setHit(true);
                ((Chicken) fixB.getUserData()).setHealth(0);
            }
            else if (fixA.getUserData() instanceof Food) {
                ((Chicken)fixB.getUserData()).setFoodHit(true);
                ((Food)fixA.getUserData()).setHit(true);
                ((Food)fixA.getUserData()).decreaseHealth();
            }

        }
    }

    /**
     * Detects when two objects stop the contact
     * @param contact Contact
     */
    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof Chicken){
            //chicken and food
            if (fixB.getUserData() instanceof Food) {
                ((Chicken) fixA.getUserData()).setFoodHit(false);
                ((Food) fixB.getUserData()).setHit(false);

                if (fixA.getUserData() instanceof EggSplosion || fixA.getUserData() instanceof SmallChickenEgg)
                    ((Food)fixB.getUserData()).setHealth(0);
            }
            if (fixB.getUserData() instanceof ExplosiveBarry)
                ((ExplosiveBarry) fixB.getUserData()).setHit(true);
        }
        else if (fixB.getUserData() instanceof Chicken){
            //chicken and food
            if (fixA.getUserData() instanceof Food) {
                ((Chicken) fixB.getUserData()).setFoodHit(false);
                ((Food) fixA.getUserData()).setHit(false);

                if (fixB.getUserData() instanceof EggSplosion || fixA.getUserData() instanceof SmallChickenEgg)
                    ((Food)fixA.getUserData()).setHealth(0);
            }
            if (fixA.getUserData() instanceof ExplosiveBarry)
                ((ExplosiveBarry) fixA.getUserData()).setHit(true);
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
