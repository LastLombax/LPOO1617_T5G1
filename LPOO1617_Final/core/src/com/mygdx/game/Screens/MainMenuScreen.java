package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ButtonImg;
import com.mygdx.game.ChickenVsFood;
/**
 * Created by vitor on 02/05/2017.
 */

public class MainMenuScreen implements Screen {
    private Stage stage;
    private ChickenVsFood game;
    private Viewport viewport;
    private int BUTTON_X = 720;
    Texture background;

    /**
     * Constructor for the MainMenu Screen
     * @param game ChickenVsFood instance
     */
    public MainMenuScreen(final ChickenVsFood game){
        this.game = game.getGame();
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());

        background = new Texture(Gdx.files.internal("Chocobo.png"));

        Texture tex = new Texture("Butter.png");
        ButtonImg PlayGameButton = new ButtonImg(tex,tex,tex);
        PlayGameButton.setWidth(Gdx.graphics.getWidth()/3);
        PlayGameButton.setPosition(BUTTON_X,2*game.getvHeight()/3 + 40);
        PlayGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new SelectLevelScreen(game));
                dispose();
            }
        });
        stage.addActor(PlayGameButton);

        Texture tex1 = new Texture(Gdx.files.internal("Fence.png"));
        ButtonImg OptionsButton = new ButtonImg(tex1,tex1,tex1);
        OptionsButton.setWidth(Gdx.graphics.getWidth()/3);
        OptionsButton.setPosition(BUTTON_X, game.getvHeight()/2 );
        OptionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new OptionsScreen(game));
                dispose();
            }
        });
        stage.addActor(OptionsButton);

        Texture tex2 = new Texture(Gdx.files.internal("Tree1.png"));
        ButtonImg ExitButton = new ButtonImg(tex2,tex2,tex2);
        ExitButton.setWidth(Gdx.graphics.getWidth()/3);
        ExitButton.setPosition(BUTTON_X,game.getvHeight()/3 - 40);
        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.exit(0);
            }
        });
        stage.addActor(ExitButton);
    }

    /**
     * sets the gdx input processor with the stage
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the screen
     * @param delta time interval
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(background, 0,0, game.getvWidth(), game.getvHeight());
        game.getBatch().end();

        stage.act();
        stage.draw();
    }

    /**
     * Resizes the screen
     * @param width new witdh
     * @param height new height
     */
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
