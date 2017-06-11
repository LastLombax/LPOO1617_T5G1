package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ButtonImg;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Sprites.Moogle;

/**
 * Created by vitor on 06/06/2017.
 */

public class GameOverScreen implements Screen {
    private Stage stage;
    private ChickenVsFood game;
    private Viewport viewport;
    private FitViewport gamePort;
    private Label scoreLabel;
    private Texture background;
    private int level;
    private Music music;
    private TextureAtlas moogleAtlas;
    private Moogle moogle;
    private World world;
    private OrthographicCamera gameCam;
    private float accumulator;
    private float FPS = 1/120f;

    /**
     * Constructor for the GameOverScreen
     * @param game ChickenVsFood instance
     */
    public GameOverScreen(final ChickenVsFood game, final int level){
        this.game = game;
        this.level = level;

        this.world = new World(new Vector2(0,0),true);
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(game.getvWidth(), game.getvHeight(), gameCam);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        if (level != 4)
            background = new Texture(Gdx.files.internal("GameOverScreen.png"));
        else
            background = new Texture(Gdx.files.internal("SurvivalGameOver.png"));

        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());

        if (level == 4)
            addScoreLabel();
        addTryAgainButton();

        addExitButton();
        setMusic();
        moogleAtlas = new TextureAtlas("Moogle.pack");
        moogle = new Moogle(this.world, 180, 100, this);
    }

    /**
     * Adds the Score Label
     */
    private void addScoreLabel(){
        scoreLabel = new Label(PlayScreen.getChickensKilled() + "", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabel.setFontScale(3);
        scoreLabel.setPosition(1452, 413);
        stage.addActor(scoreLabel);
    }

    /**
     * Adds the Try Again Button
     */
    private void addTryAgainButton(){
        Texture tex = new Texture(Gdx.files.internal("TryAgainButton.png"));
        ButtonImg TryAgain = new ButtonImg(tex, tex, tex);
        TryAgain.setPosition(600, 215);
        TryAgain.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new PlayScreen(game, level));
                dispose();
            }
        });
        stage.addActor(TryAgain);
    }
    /**
     * Adds the Exit Button
     */
    private void addExitButton(){
        Texture tex1 = new Texture(Gdx.files.internal("ExitButton.png"));
        ButtonImg Exit = new ButtonImg(tex1,tex1,tex1);
        Exit.setPosition(1100, 215);
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
        music = Gdx.audio.newMusic(Gdx.files.internal("GameOverMusic.mp3"));
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();
    }

    /**
     * Returns the Moogle Atlas
     */
    public TextureAtlas getMoogle(){
        return moogleAtlas;
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
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(background, 0,0, game.getvWidth(), game.getvHeight());

        moogle.draw(game.getBatch());
        game.getBatch().end();

        stage.draw();
    }

    private void update(float delta) {
        moogle.update(delta);

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= FPS) {
            world.step(FPS, 6, 2);
            accumulator -= FPS;
        }
        gameCam.update();
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
