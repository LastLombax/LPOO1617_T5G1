package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ButtonImg;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.MainMenuScreen;

/**
 * Created by vitor on 06/04/2017.
 */

public class Hud implements Disposable{
    private Stage stage;
    private Viewport viewport;
    private static Integer cornCounter;

    private Integer selectedFood;
    private boolean isSelected;

    private static int FOOD_1 = 100;
    private static int FOOD_2 = 50;
    private static int cost[] = {FOOD_1,FOOD_2};
    private int INITIAL_CORN = 50;

    // private Texture tex = new Texture("butter.png");
    //progress
    //textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
   /* ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(tex);
    ProgressBar lvlProgress = new ProgressBar(0,100, 5,false,barStyle);
    bar.setPosition(10, 10);
    bar.setSize(290, bar.getPrefHeight());
    bar.setAnimateDuration(2);
    stage.addActor(bar);
*/
    private static Label cornLabel;
    Label progressLevelLabel;
    Label leveLabel;
    Texture tex,tex1,tex2;

    public Hud(SpriteBatch sb, final ChickenVsFood game){

        setSelectedFood(0);
        setSelected(false);
        cornCounter = INITIAL_CORN;
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());

        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        //upper hud
        Table tableT = new Table();
        tableT.top();
        tableT.left();
        tableT.setFillParent(true);

        tex = new Texture(Gdx.files.internal("butter.png"));
        tex1 = new Texture(Gdx.files.internal("UmbrellaCopr.png"));
        tex2 = new Texture(Gdx.files.internal("Tree1.png"));

        ButtonImg food1 = new ButtonImg(tex,tex1,tex2);

        //  food1.setZIndex();

        food1.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.out.println("coiso1");
               if (!canSelect(1))
                   System.out.println("Get more coin");

            }
        });

        ButtonImg food2 = new ButtonImg(tex1,tex1,tex1);

        food2.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.out.println("coiso2");
                if (!canSelect(2))
                    System.out.println("Get more coin");
            }
        });

        ButtonImg food3 = new ButtonImg(tex2,tex2,tex2);

        food3.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        cornLabel = new Label(String.format("%04d", cornCounter), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        cornLabel.setFontScale(3); //change size
        tableT.add(cornLabel).padLeft(150);
        tableT.add(food1).padLeft(375);
        tableT.add(food2).padLeft(10);
        tableT.add(food3).padLeft(10);
        stage.addActor(tableT);


        //lower hud
        Table tableD = new Table();
        tableD.bottom();
        tableD.right();
        tableD.setFillParent(true);

        leveLabel = new Label("Level 1-1", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        leveLabel.setFontScale(3); //change size
        tableD.add(leveLabel).padRight(50);
        stage.addActor(tableD);
    }

    public Stage getStage(){
        return stage;
    }

    public Integer getSelectedFood(){
        return selectedFood;
    }

    public void setSelectedFood(int food){
        selectedFood = food;
    }

    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected(boolean b){
        isSelected = b;
    }

    public static void addCorn(int counter){
        cornCounter+=counter;
        setCornText();
    }

    public static void removeCorn(int counter){
        cornCounter-=counter;
        setCornText();
    }

    public static void setCornText(){
        cornLabel.setText(String.format("%04d", cornCounter));
    }

    public boolean canSelect(int food){
        if (cornCounter >= cost[food-1]){
            setSelectedFood(food);
            setSelected(true);
            removeCorn(cost[food-1]);
            return true;
        }
        return false;

    }

    public static Label getCornLabel(){
        return cornLabel;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
