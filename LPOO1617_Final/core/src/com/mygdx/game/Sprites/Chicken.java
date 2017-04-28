package com.mygdx.game.Sprites;

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


public class Chicken extends Actor{

        private final Sprite sprite;

        private final Animation<TextureRegion> animation;

        private float stateTime = 0;


        public Chicken(ChickenVsFood game) {
            Texture texture = game.getAssetManager().get("Chicken.png");

            TextureRegion[][] thrustRegion = TextureRegion.split(texture, texture.getWidth() / 11, texture.getHeight());

            TextureRegion[] frames = new TextureRegion[11];
            System.arraycopy(thrustRegion[0], 0, frames, 0, 11);

            // Creates the animation
            animation = new Animation<TextureRegion>(.25f, frames);

            sprite = new Sprite(animation.getKeyFrame(0));

            // Necessary so that inputs events are registered correctly
            setWidth(animation.getKeyFrame(0).getRegionWidth());
            setHeight(texture.getHeight());

            // Necessary so that rotations are correctly processed
            setOrigin(getWidth() / 2, getHeight() / 2);
            sprite.setOrigin(getWidth() / 2, getHeight() / 2);
        }

        @Override
        public void setPosition(float x, float y) {
            super.setPosition(x - getWidth() / 2, y - getHeight() / 2);
        }


        @Override
        protected void positionChanged() {
            super.positionChanged();
            sprite.setPosition(getX(), getY());
        }


        @Override
        public void act(float delta) {
            super.act(delta);

            stateTime += delta;
            sprite.setRegion(animation.getKeyFrame(stateTime, true));
        }


        @Override
        public void draw(Batch batch, float parentAlpha) {
            sprite.setColor(getColor());
            sprite.draw(batch);
        }

        Body createBody(World world) {

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;


            float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
            Body body = world.createBody(bodyDef);
            body.setTransform(1900 / 2, (900 * ratio) / 2, 0); // Middle of the viewport, no rotation


            CircleShape circle = new CircleShape();
            circle.setRadius(0.11f); // 22cm / 2

            // Create ball fixture
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = circle;
            fixtureDef.density = .5f;      // how heavy is the ball
            fixtureDef.friction =  .5f;    // how slippery is the ball
            fixtureDef.restitution =  .5f; // how bouncy is the ball

            // Attach fixture to body
            body.createFixture(fixtureDef);

            // Dispose of circle shape
            circle.dispose();

            return body;
        }
}
