package com.mygdx.game.Screens;

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
 * Created by vitor on 09/06/2017.
 */

public class AlmanacScreen implements Screen{
    private Stage stage;
    private ChickenVsFood game;
    private Viewport viewport;
    private Texture background;
    private Music music;
    private Texture cards[]  = new Texture[4];
    private int currCard = 0;
    private int BUTTON_Y = 100;

    public AlmanacScreen(final ChickenVsFood game){
        this.game = game;
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        background = new Texture(Gdx.files.internal("GameOverScreen.png"));

        stage = new Stage(viewport, game.getBatch());
        loadCards();
        addPreviousButton();
        addExitButton();
        addNextButton();
        setMusic();
    }

    /**
     * Loads the Food Almanac Cards
     */
    public void loadCards(){
        cards[0] = new Texture(Gdx.files.internal("UnicornAlmanac.png"));
        cards[1] = new Texture(Gdx.files.internal("SeedShooterAlmanac.png"));
        cards[2] = new Texture(Gdx.files.internal("ExplosiveBarryAlmanac.png"));
        cards[3] = new Texture(Gdx.files.internal("CoolNappleAlmanac.png"));
    }

    /**
     * Adds the Previous Button
     */
    public void addPreviousButton(){
        Texture tex2 = new Texture(Gdx.files.internal("darkGrass.png"));
        ButtonImg PrevButton = new ButtonImg(tex2,tex2,tex2);
        PrevButton.setWidth(Gdx.graphics.getWidth() / 3);
        PrevButton.setPosition(200, BUTTON_Y);
        PrevButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (currCard == 0)
                    currCard = 3;
                else
                    currCard--;
            }
        });
        stage.addActor(PrevButton);
    }

    /**
     * Adds the Exit Button
     */
    public void addExitButton(){
        Texture tex2 = new Texture(Gdx.files.internal("Butter.png"));
        ButtonImg ExitButton = new ButtonImg(tex2,tex2,tex2);
        ExitButton.setWidth(Gdx.graphics.getWidth() / 3);
        ExitButton.setPosition(700,BUTTON_Y);
        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(ExitButton);
    }
    /**
     * Adds the Next Button
     */
    public void addNextButton(){
        Texture tex2 = new Texture(Gdx.files.internal("Tree1.png"));
        ButtonImg NextButton = new ButtonImg(tex2,tex2,tex2);
        NextButton.setWidth(Gdx.graphics.getWidth() / 3);
        NextButton.setPosition(1200, BUTTON_Y);
        NextButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (currCard == 3)
                    currCard = 0;
                else
                    currCard++;
            }
        });
        stage.addActor(NextButton);
    }

    /**
     * Sets the music for the Screen
     */
    private void setMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("Almanac.mp3"));
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(background, 0,0, game.getvWidth(), game.getvHeight());
        game.getBatch().draw(cards[currCard],game.getvWidth()/2-250, 300);
        game.getBatch().end();

        stage.act();
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
        music.dispose();
        stage.dispose();
    }
}

