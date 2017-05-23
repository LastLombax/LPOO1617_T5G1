package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ButtonImg;
import com.mygdx.game.ChickenVsFood;
/**
 * Created by vitor on 02/05/2017.
 */

public class SelectLevelScreen implements Screen {
    private Stage stage;
    private ChickenVsFood game;
    private Viewport viewport;
    Texture lvlSelector;

    public SelectLevelScreen(final ChickenVsFood game){
        this.game = game;
        viewport = new FitViewport(ChickenVsFood.V_WIDTH,ChickenVsFood.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        lvlSelector = new Texture(Gdx.files.internal("LevelScreen.png"));
        Texture tex = new Texture(Gdx.files.internal("butter.png"));
        ButtonImg PlayLevel1 = new ButtonImg(tex,tex,tex);
        PlayLevel1.setWidth(Gdx.graphics.getWidth()/3);
        PlayLevel1.setPosition(200,ChickenVsFood.V_HEIGHT/2 );
        PlayLevel1.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        });
        stage.addActor(PlayLevel1);

        Texture tex1 = new Texture(Gdx.files.internal("Fence.png"));
        ButtonImg PlayLevel2 = new ButtonImg(tex1,tex1,tex1);
        PlayLevel2.setWidth(Gdx.graphics.getWidth()/3);
        PlayLevel2.setPosition(400, ChickenVsFood.V_HEIGHT/2 );
        PlayLevel2.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {

            }
        });
        stage.addActor(PlayLevel2);

        Texture tex2 = new Texture(Gdx.files.internal("Relva1.png"));
        ButtonImg PlayLevel3 = new ButtonImg(tex2,tex2,tex2);
        PlayLevel3.setWidth(Gdx.graphics.getWidth()/3);
        PlayLevel3.setPosition(600, ChickenVsFood.V_HEIGHT/2 );
        PlayLevel3.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {

            }
        });
        stage.addActor(PlayLevel3);

        Texture tex3 = new Texture(Gdx.files.internal("Tree1.png"));
        ButtonImg ExitButton = new ButtonImg(tex3,tex3,tex3);
        ExitButton.setWidth(Gdx.graphics.getWidth()/3);
        ExitButton.setPosition(800,ChickenVsFood.V_HEIGHT/2 );
        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(ExitButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(lvlSelector, 0,0, ChickenVsFood.V_WIDTH, ChickenVsFood.V_HEIGHT);
        game.batch.end();

       // stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
