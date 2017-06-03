package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ChickenVsFood;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Butter;
import com.mygdx.game.Sprites.Chickens.Chicken;
import com.mygdx.game.Sprites.Chickens.EggSplosion;
import com.mygdx.game.Sprites.Chickens.MadChicken;
import com.mygdx.game.Sprites.Chickens.StrongChicken;
import com.mygdx.game.Sprites.Chickens.NormalChicken;
import com.mygdx.game.Sprites.Chickens.SmallChickenEgg;

import com.mygdx.game.Sprites.Foods.ExplosiveBarry;
import com.mygdx.game.Sprites.Foods.Food;
import com.mygdx.game.Sprites.Foods.SeedShooter;
import com.mygdx.game.Sprites.Foods.Unicorn;
import com.mygdx.game.Tools.B2WorldCreator;
import com.mygdx.game.Tools.WorldContactListener;

import java.util.Random;
import java.util.Vector;


/**
 * Created by vitor on 06/04/2017.
 */

public class PlayScreen implements Screen{
    private ChickenVsFood game;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private float accumulator;
    private float FPS = 1/60f;

    private TextureAtlas NormalChicken;
    private TextureAtlas StrongChicken;
    private TextureAtlas SmallChicken;
    private TextureAtlas EggSplosion;
    private TextureAtlas MadChicken;

    private TextureAtlas Unicorn;
    private TextureAtlas SeedShooter;
    private TextureAtlas Seed;
    private TextureAtlas ExplosiveBarry;


    //placement variables
    private int MIN_WORLD_X = 512;
    private int MAX_WORLD_X = 1790;
    private int MIN_WORLD_Y = 180;
    private int MAX_WORLD_Y = 695;
    private int ORIGINAL_X_MID = 575;
    private int FINAL_X_MID = 1855;
    private int ORIGINAL_Y_MID = 185;
    private int FINAL_Y_MID = 793;
    private int GAP = 60;
    private int tileSize = 128;

    private int MAX_CHICKEN = 20;
    private int INITIAL_CHICKEN_X = 2000;
    private int timer = 0;

    private int LANE_1_Y = 700;
    private int LANE_2_Y = 570;
    private int LANE_3_Y = 440;
    private int LANE_4_Y = 310;
    private int LANE_5_Y = 185;
    private int BUTTER_X = 450;

    private int diffY[] = {LANE_1_Y,LANE_2_Y,LANE_3_Y,LANE_4_Y,LANE_5_Y}; //array with different initial Y values for each lane

    private Vector<Chicken> chicken = new Vector<Chicken>();
    private Vector<Food> foods = new Vector<Food>();
    private Vector<Butter> butters = new Vector<Butter>();


    /**
     * Creates the screen of the game
     * @param game ChickenVsFood instance
     */
    public PlayScreen(ChickenVsFood game){
        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(game.getvWidth(),game.getvHeight(),gameCam);
        hud = new Hud(game.getBatch(), game);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1);
        loadAssets();
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        setWorld();
        b2dr = new Box2DDebugRenderer();
        b2dr.SHAPE_STATIC.set(1,0,0,1);

        new B2WorldCreator(world, map);
        world.setContactListener(new WorldContactListener());

        createButters();

    }

    /**
     * Loads the several textures and stores them into specific atlas
     */
    public void loadAssets(){
        NormalChicken = new TextureAtlas("NormalChicken.pack");
        EggSplosion = new TextureAtlas("EggSplosion.pack");
        MadChicken = new TextureAtlas("MadChicken.pack");
        StrongChicken =  new TextureAtlas("StrongChicken.pack");
        SmallChicken = new TextureAtlas("SmallChicken.pack");

        SeedShooter = new TextureAtlas("SeedShooter.pack");
        Seed = new TextureAtlas("Seed.pack");
        Unicorn = new TextureAtlas("Unicorn.pack");
        ExplosiveBarry = new TextureAtlas("ExplosiveBarry.pack");

        game.getAssetManager().load("Chicken.png", Texture.class);
        game.getAssetManager().finishLoading();
    }

    /**
     * Creates the butters, used for a second chance in the game
     */
    public void createButters(){
        for (int i = 0; i < diffY.length; i++){
            Butter b = new Butter(getWorld(), game, BUTTER_X , diffY[i]);
            butters.add(b);
        }
    }

    public Vector<Chicken> getChickens(){
        return chicken;
    }

    public Vector<Food> getFoods() { return foods;}

    /**
     * @return Returns the NormalChicken atlas
     */
    public TextureAtlas getNormalChicken(){
        return NormalChicken;
    }
    /**
     * @return Returns the SmallChicken atlas
     */
    public TextureAtlas getSmallChicken(){ return SmallChicken;}
    /**
     * @return Returns the StrongChicken atlas
     */
    public TextureAtlas getStrongChicken(){ return StrongChicken;}
    /**
     * @return Returns the MadChicken atlas
     */
    public TextureAtlas getMadChicken(){ return MadChicken;}
    /**
     * @return Returns the EggSplotion atlas
     */
    public TextureAtlas getEggSplosion(){ return EggSplosion;}
    /**
     * @return Returns the Unicorn atlas
     */
    public TextureAtlas getUnicorn() { return Unicorn;}
    /**
     * @return Returns the SeedShooter atlas
     */
    public TextureAtlas getSeedShooter() { return SeedShooter;}

    public TextureAtlas getSeed() {
        return Seed;
    }

    public TextureAtlas getExplosiveBarry() { return ExplosiveBarry;}

    /**
     * Renders the game screen
     * @param delta time interval of each render
     */
    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gameCam.combined);

        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        game.getBatch().begin();

        //draw chickens
        for (int i = 0; i < this.chicken.size(); i++){
            chicken.get(i).draw(game.getBatch());
        }
        //draw foods
        for (int i = 0; i < this.foods.size(); i++){
            foods.get(i).draw(game.getBatch());
        }
        game.getBatch().end();

        //draw hud
        hud.getStage().draw();

    }

    /**
     * Updates the screen
     * @param dt time interval of the update
     */
    public void update(float dt) {
        handleInput(dt);

        //takes 1 step in the physics simulation (60 times per second)
        float frameTime = Math.min(dt, 0.25f);
        accumulator += frameTime;
        while (accumulator >= FPS) {
            world.step(FPS, 6, 2);
            accumulator -= FPS;
        }

        if(chicken.size() < MAX_CHICKEN)
            GenerateChickens();

        updateCharacters(dt);

        gameCam.update();
        renderer.setView(gameCam);
    }

    /**
     * Handles all input from the player
     * @param dt time interval
     */
    public void handleInput(float dt) {

        if (hud.isSelected()) //puts food in selected location
            if (Gdx.input.isTouched()) {

                Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                v = gameCam.unproject(v);

                int x = (int) v.x;
                int y = (int) v.y - 20;

                if (checkPlacingBounds(x , y)) {

                    for (int m = ORIGINAL_X_MID; m <FINAL_X_MID; m+=tileSize){
                        if ( x-m < GAP) { //found
                            x = m;
                            break;
                        }
                    }
                    for (int m = ORIGINAL_Y_MID; m <FINAL_Y_MID; m+=tileSize){
                        if ( y-m < GAP-10) { //found
                            y = m;
                            break;
                        }
                    }

                    switch (hud.getSelectedFood()) {
                        case 1:
                            foods.add(new SeedShooter(getWorld(), game, x, y, this));
                            break;
                        case 2:
                            foods.add(new Unicorn(getWorld(), game, x, y, this));
                            break;
                        case 3:
                            foods.add(new ExplosiveBarry(getWorld(), game, x, y, this));
                    }
                    hud.setSelectedFood(0);
                    hud.setSelected(false);
                } else
                    System.out.println("You can't put it there");
            }
    }

    /**
     * Verifies if a food can be placed in the selected spot
     * @param px x coordinate
     * @param py y coordinate
     * @return true if can be placed
     */
    public boolean checkPlacingBounds(double px, double py){
        return (px >= MIN_WORLD_X && px <= MAX_WORLD_X && py >= MIN_WORLD_Y && py <=MAX_WORLD_Y);
    }

    /**
     * Updates all characters(Foods and Chickens)
     * @param dt time interval for the update
     */
    public void updateCharacters(float dt){
        for (int i = 0; i < this.chicken.size(); i++) {
            //Dead Chicken
            if (chicken.get(i).isDead()) {
                chicken.remove(i);
                i--;
            }
            else{
                chicken.get(i).update(dt);
            }
        }

        for (int i = 0; i < this.foods.size(); i++) {
            if (foods.get(i).isDead()) {
                foods.remove(i);
                i--;
            }
            else{
                //collision detection
                foods.get(i).update(dt);
            }
        }

        for (int i = 0; i < this.butters.size(); i++) {
            this.butters.get(i).update(dt);
        }
    }

    /**
     * Randomly generates chickens for each lane every 5 seconds
     */
    public void GenerateChickens(){
        timer++;
        if(timer%500 == 0){ // every 5 seconds
            Random rn = new Random();
            int value = rn.nextInt(Integer.SIZE -1)%5;
            int y = diffY[value];
            Chicken c = new NormalChicken(getWorld(), game, INITIAL_CHICKEN_X, y, this);
            //Chicken c = new EggSplosion(getWorld(), game, INITIAL_CHICKEN_X, y, this);
            //Chicken c = new StrongChicken(getWorld(), game, INITIAL_CHICKEN_X, y, this);
            //Chicken c = new MadChicken(getWorld(), game, INITIAL_CHICKEN_X, y, this);
            //Chicken c = new SmallChickenEgg(getWorld(), game, INITIAL_CHICKEN_X, y, this);

            c.getBody().applyLinearImpulse(new Vector2(-c.getVelocity(), 0), c.getBody().getWorldCenter(), true);
            chicken.add(c);
        }
    }

    /**
     * @return retuns the world
     */
    public World getWorld(){
        return world;
    }

    /**
     * Sets the world
     */
    public void setWorld(){world = new World(new Vector2(0,0),true);}

    /**
     * Resizes the gameport
     * @param width new width
     * @param height new height
     */
    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void show() {
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

    /**
     * Disposes of all used objects
     */
    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

}
