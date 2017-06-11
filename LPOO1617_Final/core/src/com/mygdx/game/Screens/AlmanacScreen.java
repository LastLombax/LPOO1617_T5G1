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
    private Texture cards[];
    private int currCard = 0;
    private int BUTTON_Y = 50;

    /**
     * Constructor for the AlmanacScreen
     * @param game ChickenVsFood instance
     * @param food integer to identify almanac
     */
    public AlmanacScreen(final ChickenVsFood game, int food){
        this.game = game;
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        background = new Texture(Gdx.files.internal("Almanac.png"));
        stage = new Stage(viewport, game.getBatch());
        if (food == 0) {
            cards = new Texture[4];
            loadFoodCards();
        }
        else {
            cards = new Texture[5];
            loadChickenCards();
        }
        addPreviousButton();
        addExitButton();
        addNextButton();
        setMusic();
    }

    /**
     * Loads the Food Almanac Cards
     */
    public void loadFoodCards(){
        cards[0] = new Texture(Gdx.files.internal("UnicornAlmanac.png"));
        cards[1] = new Texture(Gdx.files.internal("SeedShooterAlmanac.png"));
        cards[2] = new Texture(Gdx.files.internal("ExplosiveBarryAlmanac.png"));
        cards[3] = new Texture(Gdx.files.internal("CoolNappleAlmanac.png"));
    }
    /**
     * Loads the Chicken Almanac Cards
     */
    public void loadChickenCards(){
        cards[0] = new Texture(Gdx.files.internal("NormalChickenAlmanac.png"));
        cards[1] = new Texture(Gdx.files.internal("MadChickenAlmanac.png"));
        cards[2] = new Texture(Gdx.files.internal("StrongChickenAlmanac.png"));
        cards[3] = new Texture(Gdx.files.internal("SmallChickenEggAlmanac.png"));
        cards[4] = new Texture(Gdx.files.internal("EggSplosionAlmanac.png"));
    }

    /**
     * Adds the Previous Button
     */
    public void addPreviousButton(){
        Texture tex2 = new Texture(Gdx.files.internal("PreviousButton.png"));
        ButtonImg PrevButton = new ButtonImg(tex2,tex2,tex2);
        PrevButton.setPosition(625, BUTTON_Y);
        PrevButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (currCard == 0)
                    currCard = cards.length -1;
                else
                    currCard--;
            }
        });
        stage.addActor(PrevButton);
    }
    /**
     * Adds the Next Button
     */
    public void addNextButton(){
        Texture tex2 = new Texture(Gdx.files.internal("NextButton.png"));
        ButtonImg NextButton = new ButtonImg(tex2,tex2,tex2);
        NextButton.setPosition(950, BUTTON_Y);
        NextButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (currCard == cards.length-1)
                    currCard = 0;
                else
                    currCard++;
            }
        });
        stage.addActor(NextButton);
    }


    /**
     * Adds the Exit Button
     */
    public void addExitButton(){
        Texture tex2 = new Texture(Gdx.files.internal("ExitButton.png"));
        ButtonImg ExitButton = new ButtonImg(tex2,tex2,tex2);
        ExitButton.setPosition(1550, BUTTON_Y);
        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(ExitButton);
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
        game.getBatch().draw(cards[currCard],game.getvWidth()/2-325, 200);
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

