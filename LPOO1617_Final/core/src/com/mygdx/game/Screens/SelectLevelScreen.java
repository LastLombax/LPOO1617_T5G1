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

public class SelectLevelScreen implements Screen {
    private Stage stage;
    private ChickenVsFood game;
    private Viewport viewport;
    private int BUTTON_X = 40;
    private int BUTTON_Y = 250;
    private int DIST = 450;
    private Texture background;

    /**
     * Constructor for the Select Level Screen
     * @param game ChickenVsFood instance
     */
    public SelectLevelScreen(final ChickenVsFood game){
        this.game = game;
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());

        background = new Texture(Gdx.files.internal("PinkScreen.png"));

        addPlayLvL1Button();
        addPlayLvL2Button();
        addPlayLvL3Button();
        addPlaySurvivalButton();
        addExitButton();

    }
    /**
     * Adds the Play Level 1 Button
     */
    private void addPlayLvL1Button() {
        Texture tex = new Texture(Gdx.files.internal("Level1.png"));
        ButtonImg PlayLevel1 = new ButtonImg(tex,tex,tex);
        PlayLevel1.setWidth(Gdx.graphics.getWidth()/3);
        PlayLevel1.setPosition(BUTTON_X, BUTTON_Y);
        PlayLevel1.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new PlayScreen(game,1));
                dispose();
            }
        });
        stage.addActor(PlayLevel1);
    }

    /**
     * Adds the Play Level 2 Button
     */
    private void addPlayLvL2Button(){
        Texture tex1 = new Texture(Gdx.files.internal("Level2.png"));
        ButtonImg PlayLevel2 = new ButtonImg(tex1,tex1,tex1);
        PlayLevel2.setWidth(Gdx.graphics.getWidth()/3);
        PlayLevel2.setPosition(BUTTON_X + DIST, BUTTON_Y);
        PlayLevel2.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new PlayScreen(game,2));
                dispose();
            }
        });
        stage.addActor(PlayLevel2);
    }

    /**
     * Adds the Play Level 3 Button
     */
    private void addPlayLvL3Button(){
        Texture tex2 = new Texture(Gdx.files.internal("Level3.png"));
        ButtonImg PlayLevel3 = new ButtonImg(tex2,tex2,tex2);
        // PlayLevel3.setWidth(Gdx.graphics.getWidth()/3);
        PlayLevel3.setPosition(BUTTON_X + 2*DIST, BUTTON_Y );
        PlayLevel3.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new PlayScreen(game,3));
                dispose();
            }
        });
        stage.addActor(PlayLevel3);
    }

    /**
     * Adds the Play Survival Button
     */
    private void addPlaySurvivalButton(){
        Texture tex3 = new Texture(Gdx.files.internal("Survival.png"));
        ButtonImg SurvivalButton = new ButtonImg(tex3,tex3,tex3);
        SurvivalButton.setWidth(Gdx.graphics.getWidth()/3);
        SurvivalButton.setPosition(BUTTON_X + 3*DIST,BUTTON_Y );
        SurvivalButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new PlayScreen(game,4));
                dispose();
            }
        });
        stage.addActor(SurvivalButton);
    }

    /**
     * Adds the Exit Button
     */
    private void addExitButton(){
        Texture tex4 = new Texture(Gdx.files.internal("ExitButton.png"));
        ButtonImg ExitButton = new ButtonImg(tex4,tex4,tex4);
        ExitButton.setWidth(Gdx.graphics.getWidth()/3);
        ExitButton.setPosition(BUTTON_X + 3*DIST + 50, 0);
        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(ExitButton);
    }
    /**
     * Sets the gdx input processor with the stage
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
        stage.dispose();
    }
}
