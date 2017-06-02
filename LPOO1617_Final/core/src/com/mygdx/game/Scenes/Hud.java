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

    private static Label cornLabel;
    Label leveLabel;
    Texture tex,tex1,tex2;

    /**
     * Constructor for the Heads Up Display(HUD)
     * @param sb game spritebatch
     * @param game ChickenVsoFood game
     */
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

        ButtonImg food1 = new ButtonImg(tex,tex1,tex2);

        food1.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.out.println("coiso1");
               if (!canSelect(1))
                   System.out.println("Get more coin");

            }
        });

        tex1 = new Texture(Gdx.files.internal("UmbrellaCopr.png"));

        ButtonImg food2 = new ButtonImg(tex1,tex1,tex1);

        food2.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.out.println("coiso2");
                if (!canSelect(2))
                    System.out.println("Get more coin");
            }
        });

        tex2 = new Texture(Gdx.files.internal("Tree1.png"));

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

    /**
     * @return returns the stage
     */
    public Stage getStage(){
        return stage;
    }

    /**
     * @return the selected food
     */
    public Integer getSelectedFood(){
        return selectedFood;
    }

    /**
     * sets the selectedFood field with the value food
     * @param food new selected food
     */
    public void setSelectedFood(int food){
        selectedFood = food;
    }

    /**
     * @return Returns the value of the field isSelected
     */
    public boolean isSelected(){
        return isSelected;
    }

    /**
     * sets the isSelected field with the value b
     * @param b boolean value for isSelected
     */
    public void setSelected(boolean b){
        isSelected = b;
    }

    /**
     * adds the value counter to the cornCounter
     * @param counter value to be added
     */
    public static void addCorn(int counter){
        cornCounter+=counter;
        setCornText();
    }

    /**
     * removes the value counter from the cornCounter
     * @param counter value to be removed
     */
    public static void removeCorn(int counter){
        cornCounter-=counter;
        setCornText();
    }

    /**
     * sets the cornLabel text with the value of the cornCounter
     */
    public static void setCornText(){
        cornLabel.setText(String.format("%04d", cornCounter));
    }

    /**
     * Verifies if food can be selected
     * @param food index
     * @return true if can be selected, false if not
     */
    public boolean canSelect(int food){
        if (cornCounter >= cost[food-1]){
            setSelectedFood(food);
            setSelected(true);
            removeCorn(cost[food-1]);
            return true;
        }
        return false;

    }

    /**
     * @return Returns the cornLabel
     */
    public static Label getCornLabel(){
        return cornLabel;
    }

    /**
     * Disposes the stage
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
