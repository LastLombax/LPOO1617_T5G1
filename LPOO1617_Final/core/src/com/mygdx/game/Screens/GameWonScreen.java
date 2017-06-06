package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private Label GameOver;
    private Music music;
    private Texture background;

    /**
     * Constructor for the GameOverScreen
     * @param game ChickenVsFood instance
     */
    public GameWonScreen(final ChickenVsFood game, final int level){
        this.game = game;
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());

        background = new Texture(Gdx.files.internal("GameOverScreen.png"));

        stage = new Stage(viewport, game.getBatch());
        GameOver = new Label("YOU WON", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        GameOver.setFontScale(3);
        Table t = new Table();
        t.center();
        t.top();
        t.setFillParent(true);
        t.add(GameOver).padTop(180);
        stage.addActor(t);

        Texture tex = new Texture(Gdx.files.internal("butter.png"));
        ButtonImg NextLvL = new ButtonImg(tex,tex,tex);
        NextLvL.setWidth(Gdx.graphics.getWidth()/3);
        NextLvL.setPosition(600, game.getvHeight()/2 );
        NextLvL.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if(level < 3) {
                    game.setScreen(new PlayScreen(game, level + 1));
                    dispose();
                }
                else{
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                }

            }
        });
        stage.addActor(NextLvL);

        Texture tex1 = new Texture(Gdx.files.internal("Fence.png"));
        ButtonImg Exit = new ButtonImg(tex1,tex1,tex1);
        Exit.setWidth(Gdx.graphics.getWidth()/3);
        Exit.setPosition(900, game.getvHeight()/2 );
        Exit.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(Exit);

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

       /* game.getBatch().begin();
        game.getBatch().draw(background, 0,0, game.getvWidth(), game.getvHeight());
        game.getBatch().end();*/

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
