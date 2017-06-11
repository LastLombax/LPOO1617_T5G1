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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ButtonImg;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by vitor on 06/04/2017.
 */

public class Hud implements Disposable{
    private Stage stage;
    private Viewport viewport;
    private static Integer cornCounter;

    private Integer selectedFood;
    private boolean isSelected;

    private ChickenVsFood game;

    private static int FOOD_1 = 10;
    private static int FOOD_2 = 5;
    private static int FOOD_3 = 20;
    private static int FOOD_4 = 15;

    private static int cost[] = {FOOD_1,FOOD_2, FOOD_3, FOOD_4};
    private int INITIAL_CORN = 5;

    private static Label cornLabel;
    Label leveLabel;
    Texture tex,tex1,tex2, tex3, exit;


    /**
     * Constructor for the Heads Up Display(HUD)
     * @param sb game spritebatch
     * @param game ChickenVsoFood game
     */
    public Hud(SpriteBatch sb, final ChickenVsFood game){

        setSelectedFood(0);
        setSelected(false);
        this.game = game;
        cornCounter = INITIAL_CORN;
        viewport = new FitViewport(game.getvWidth(),game.getvHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Gdx.input.setInputProcessor(stage);

        defineTopTable();
        defineTextures();
        defineFoodButtonsTable();
        defineExitButtonTable();
        defineBottomTable();
    }

    /**
     * Defines the Top Table of the Hud
     */
    public void  defineTopTable(){
        Table tableT = new Table();
        tableT.top();
        tableT.left();
        tableT.setFillParent(true);

        cornLabel = new Label(String.format("%04d", cornCounter), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        cornLabel.setFontScale(3);
        tableT.add(cornLabel).padLeft(310);
        tableT.add(cornLabel).padTop(20);

        stage.addActor(tableT);
    }

    /**
     * Defines the Food Buttons Table of the Hud
     */
    private void defineFoodButtonsTable() {
        ButtonImg SeedShooterButton = new ButtonImg(tex,tex,tex);

        SeedShooterButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (!canSelect(1))
                    System.out.println("You need more corn!");
            }
        });


        ButtonImg UnicornButton = new ButtonImg(tex1,tex1,tex1);

        UnicornButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (!canSelect(2))
                    System.out.println("You need more corn!");
            }
        });


        ButtonImg ExplosiveBarryButton = new ButtonImg(tex2,tex2,tex2);

        ExplosiveBarryButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (!canSelect(3))
                    System.out.println("You need more corn!");
            }
        });

        ButtonImg CoolNappleButton = new ButtonImg(tex3,tex3,tex3);

        CoolNappleButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (!canSelect(4))
                    System.out.println("You need more corn!");
            }
        });

        Table tableC = new Table();
        tableC.center();
        tableC.left();
        tableC.top();
        tableC.setFillParent(true);
        tableC.row();
        tableC.add(SeedShooterButton);
        tableC.add(UnicornButton).padLeft(10);
        tableC.row();

        if (PlayScreen.getLevel() > 1)
            tableC.add(ExplosiveBarryButton).padLeft(10);

        if (PlayScreen.getLevel() > 2)
            tableC.add(CoolNappleButton).padLeft(10);

        stage.addActor(tableC);
    }

    /**
     * Defines the Exit Button Table of the Hud
     */
    private void defineExitButtonTable() {
        ButtonImg ExitButton = new ButtonImg(exit,exit,exit);

        ExitButton.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                getGame().getScreen().dispose();
                getGame().setScreen(new MainMenuScreen(getGame()));
                dispose();
            }
        });

        Table tableE = new Table();
        tableE.bottom();
        tableE.left();
        tableE.setFillParent(true);
        tableE.add(ExitButton).padLeft(80);

        stage.addActor(tableE);
    }

    /**
     * Defines the Bottom Table of the Hud
     */
    private void defineBottomTable() {
        int level = PlayScreen.getLevel();
        Table tableD = new Table();
        tableD.bottom();
        tableD.right();
        tableD.setFillParent(true);
        if (level <= 3)
             leveLabel = new Label("Level 1-" + PlayScreen.getLevel(), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        else
            leveLabel = new Label("Survival Mode", new Label.LabelStyle(new BitmapFont(), Color.GOLD));

        leveLabel.setFontScale(3);
        tableD.add(leveLabel).padRight(50);

        stage.addActor(tableD);
    }

    /**
     * Defines the textures used on the Hud
     */
    private void defineTextures() {
        tex = new Texture(Gdx.files.internal("SeedShooterCard.png"));
        tex1 = new Texture(Gdx.files.internal("UnicornCard.png"));
        tex2 = new Texture(Gdx.files.internal("ExplosiveBarryCard.png"));
        tex3 = new Texture(Gdx.files.internal("CoolNappleCard.png"));
        exit = new Texture(Gdx.files.internal("Exit.png"));
    }

    /**
     * @return Returns the instance of the game
     */
    private ChickenVsFood getGame(){
        return this.game;
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
            return true;
        }
        return false;
    }

    /**
     * @return Returns the cost array of each food
     */
    public static int[] getCost(){
        return cost;
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
