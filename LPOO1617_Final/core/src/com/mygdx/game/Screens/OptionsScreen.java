package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
 * Created by vitor on 02/05/2017.
 */

public class OptionsScreen implements Screen {
    private Stage stage;
    private ChickenVsFood game;
    private Viewport viewport;
    private Texture background;
    /**
     * Constructor for the Options Screen
     * @param game ChickenVsFood instance
     */
    public OptionsScreen(final ChickenVsFood game){
        this.game = game;
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        background = new Texture(Gdx.files.internal("Almanac.png"));
        addCenterTable();
        addBottomTable();
        addExitButton();
    }

    /**
     * Adds the Center Table
     */
    public void addCenterTable(){
        Label credits1 = new Label("Chicken Vs Food is a game developed for the subject of Object Oriented Programming Laboratory from FEUP", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label credits2 = new Label("The game was developed by Vicente Espinha and Vitor Magalhaes", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        credits1.setFontScale(2);
        credits2.setFontScale(2);

        Table t = new Table();
        t.center();
        t.setFillParent(true);
        t.add(credits1);
        t.row();
        t.add(credits2);
        stage.addActor(t);
    }

    /**
     * Adds the Exit Button
     */
    private void addExitButton() {
        Texture tex2 = new Texture(Gdx.files.internal("ExitButton.png"));
        ButtonImg ExitButton = new ButtonImg(tex2,tex2,tex2);
        ExitButton.setWidth(Gdx.graphics.getWidth()/3);
        ExitButton.setPosition(750,game.getvHeight()/4 - 80);
        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(ExitButton);
    }

    /**
     * Adds the Bottom Table
     */
    public void addBottomTable(){
        Label credits3 = new Label("No chickens/chocobos were harmed in the making of this game", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        credits3.setFontScale(2);
        Table t1 = new Table();
        t1.center();
        t1.bottom();
        t1.setFillParent(true);
        t1.add(credits3);
        stage.addActor(t1);
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
        stage.dispose();
    }
}