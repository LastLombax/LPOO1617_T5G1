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
 * Created by vitor on 06/06/2017.
 */

public class GameWonScreen implements Screen {
    private Stage stage;
    private ChickenVsFood game;
    private Viewport viewport;
    private Music music;
    private Texture background;
    private int level;

    /**
     * Constructor for the GameOverScreen
     * @param game ChickenVsFood instance
     */
    public GameWonScreen(final ChickenVsFood game, final int level){
        this.game = game;
        this.level = level;
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        if (level < 3)
            background = new Texture(Gdx.files.internal("Map.png"));
        else if (level == 3)
            background = new Texture(Gdx.files.internal("GameWon.png"));
        stage = new Stage(viewport, game.getBatch());

        if(Gdx.app.getType() == Application.ApplicationType.Android && level == 3)
            addFbButton();
        if (level < 3)
            addNextLvlButton();
        addExitButton();
        setMusic();
    }

    /**
     * Adds the Facebook Button if on Android Device
     */
    private void addFbButton() {
        Texture tex2 = new Texture(Gdx.files.internal("fbButton.png"));
        ButtonImg FBButton = new ButtonImg(tex2, tex2, tex2);
        //FBButton.setWidth(Gdx.graphics.getWidth() / 3);
        FBButton.setPosition(300, 100);
        FBButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (!Gdx.net.openURI("fb://page/<page_id>"))
                    Gdx.net.openURI("https://www.facebook.com/Chicken-Vs-Food-1929917687231434/");
            }
        });
        stage.addActor(FBButton);
    }

    /**
     * Adds the Next Level Button
     */
    private void addNextLvlButton(){
        Texture tex = new Texture(Gdx.files.internal("NextLvLButton.png"));
        ButtonImg NextLvL = new ButtonImg(tex,tex,tex);
        //NextLvL.setWidth(Gdx.graphics.getWidth()/3);
        NextLvL.setPosition(600, 10 );
        NextLvL.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new PlayScreen(game, level + 1));
                dispose();
            }
        });
        stage.addActor(NextLvL);
    }
    /**
     * Adds the Exit Button
     */
    public void addExitButton(){
        Texture tex1 = new Texture(Gdx.files.internal("ExitButton.png"));
        ButtonImg Exit = new ButtonImg(tex1,tex1,tex1);
        //Exit.setWidth(Gdx.graphics.getWidth()/3);
        if (level == 3)
            Exit.setPosition(1550,50);
        else
            Exit.setPosition(870, 10);

        Exit.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(Exit);
    }

    /**
     * Sets the music for the Screen
     */
    private void setMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("Won.mp3"));
        music.setVolume(0.3f);
        music.setLooping(false);
        music.play();
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
        music.dispose();
    }

}
