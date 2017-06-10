package com.mygdx.game.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
    private int BUTTON_X = 780;
    private Texture background;

    private Music music;

    /**
     * Constructor for the MainMenu Screen
     * @param game ChickenVsFood instance
     */
    public MainMenuScreen(final ChickenVsFood game){
        this.game = game.getGame();
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());

        background = new Texture(Gdx.files.internal("Chocobo.png"));
        addPlayButton();
        addOptionsButton();
        addAlmanacButton();
      //  if(Gdx.app.getType() == Application.ApplicationType.Android)
            addFbButton();
        addExitButton();
        setMusic();
    }

    /**
     * Adds the Play Button
     */
    private void addPlayButton() {
        Texture tex = new Texture("Butter.png");
        ButtonImg PlayGameButton = new ButtonImg(tex,tex,tex);
        PlayGameButton.setWidth(Gdx.graphics.getWidth()/3);
        PlayGameButton.setPosition(BUTTON_X,2*game.getvHeight()/3 +40 );
        PlayGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new SelectLevelScreen(game));
                dispose();
            }
        });
        stage.addActor(PlayGameButton);
    }
    /**
     * Adds the Options Button
     */
    private void addOptionsButton() {
        Texture tex1 = new Texture(Gdx.files.internal("Fence.png"));
        ButtonImg OptionsButton = new ButtonImg(tex1,tex1,tex1);
        OptionsButton.setWidth(Gdx.graphics.getWidth()/3);
        OptionsButton.setPosition(BUTTON_X, game.getvHeight()/2 + 30);
        OptionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new OptionsScreen(game));
                dispose();
            }
        });
        stage.addActor(OptionsButton);
    }

    /**
     * Adds the Almanac Button
     */
    private void addAlmanacButton(){
        Texture tex2 = new Texture(Gdx.files.internal("darkGrass.png"));
        ButtonImg ExitButton = new ButtonImg(tex2,tex2,tex2);
        ExitButton.setWidth(Gdx.graphics.getWidth()/3);
        ExitButton.setPosition(BUTTON_X,game.getvHeight()/3);
        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new AlmanacScreen(game));
                dispose();
            }
        });
        stage.addActor(ExitButton);
    }

    /**
     * Adds the Facebook Button if on Android Device
     */
    private void addFbButton(){
        Texture tex2 = new Texture(Gdx.files.internal("Butter.png"));
        ButtonImg FBButton = new ButtonImg(tex2,tex2,tex2);
        FBButton.setWidth(Gdx.graphics.getWidth()/3);
        FBButton.setPosition(BUTTON_X + 850,game.getvHeight()/3);
        FBButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (!Gdx.net.openURI("fb://page/<page_id>"))
                    Gdx.net.openURI("https://www.facebook.com/Chicken-Vs-Food-1929917687231434/");
            }
        });
        stage.addActor(FBButton);
    }
    /**
     * Adds the Exit Button
     */
    private void addExitButton() {
        Texture tex2 = new Texture(Gdx.files.internal("Tree1.png"));
        ButtonImg ExitButton = new ButtonImg(tex2,tex2,tex2);
        ExitButton.setWidth(Gdx.graphics.getWidth()/3);
        ExitButton.setPosition(BUTTON_X,game.getvHeight()/4 - 80);
        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.exit(0);
            }
        });
        stage.addActor(ExitButton);
    }

    /**
     * Sets the music for the Screen
     */
    private void setMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("MainMenu.mp3"));
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();
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
        music.dispose();
        stage.dispose();
    }
}
