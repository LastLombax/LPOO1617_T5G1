package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.mygdx.game.Sprites.Chickens.NormalChicken;
import com.mygdx.game.Sprites.Chickens.MadChicken;
import com.mygdx.game.Sprites.Chickens.StrongChicken;
import com.mygdx.game.Sprites.Chickens.SmallChickenEgg;
import com.mygdx.game.Sprites.Chickens.EggSplosion;
import com.mygdx.game.Sprites.Foods.CoolNapple;
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

    private static boolean gameOver;
    private boolean gameWon;
    private TextureAtlas NormalChicken;
    private TextureAtlas StrongChicken;
    private TextureAtlas SmallChicken;
    private TextureAtlas EggSplosion;
    private TextureAtlas MadChicken;

    private TextureAtlas Unicorn;
    private TextureAtlas SeedShooter;
    private TextureAtlas Seed;
    private TextureAtlas ExplosiveBarry;
    private TextureAtlas CoolNapple;

    private Music music;

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

    private int MAX_CHICKEN_LVL_1 = 10;
    private int MAX_CHICKEN_LVL_2 = 15;
    private int MAX_CHICKEN_LVL_3 = 20;

    private int INI_NOR_CHICKEN = 3;
    private int counter = 0;
    private int CHICKEN_GEN = 500;
    private int INITIAL_CHICKEN_X = 2000;
    private int INITIAL_TIMER = 2000;
    private int timer = 0;
    private int nChickenGEN = 0;
    private boolean generate = false;
    private static int nChickenKilled = 0;
    private static int level;

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
    public PlayScreen(ChickenVsFood game, int level){
        this.game = game;
        this.gameOver = false;
        this.gameWon = false;
        this.level = level;
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
        setMusic();
    }

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

        b2dr.setDrawBodies(false);
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
        //draw butters
        for (int i = 0; i < this.butters.size(); i++) {
            this.butters.get(i).draw(game.getBatch());
        }
        game.getBatch().end();

        hud.getStage().draw();

        if (gameOver){
            game.setScreen(new GameOverScreen(game, getLevel()));
            dispose();
        }
        if (gameWon){
            game.setScreen(new GameWonScreen(game, getLevel()));
            dispose();
        }
    }

    /**
     * Updates the screen
     * @param dt time interval of the update
     */
    public void update(float dt) {
        handleInput();

        //takes 1 step in the physics simulation (60 times per second)
        float frameTime = Math.min(dt, 0.25f);
        accumulator += frameTime;
        while (accumulator >= FPS) {
            world.step(FPS, 6, 2);
            accumulator -= FPS;
        }
        if(timer%INITIAL_TIMER == 0)
            generate = true;


        if (getLevel() == 1) {
            if (chicken.isEmpty() && MAX_CHICKEN_LVL_1 == nChickenGEN)
                gameWon = true;

            else if (chicken.size() < MAX_CHICKEN_LVL_1 && generate)
                GenerateChickens(3);
        }
        else if (getLevel() == 2) {
            if (chicken.isEmpty() && MAX_CHICKEN_LVL_2 == nChickenGEN)
                gameWon = true;
            else if (chicken.size() < MAX_CHICKEN_LVL_2 && generate)
                GenerateChickens(4);
        }
        else if (getLevel() == 3) {
            if (chicken.isEmpty() && MAX_CHICKEN_LVL_3 == nChickenGEN)
                gameWon = true;
            else if (chicken.size() < MAX_CHICKEN_LVL_3 && generate)
                GenerateChickens(5);
        }
        else if (getLevel() == 4)
            GenerateChickens(5);

        updateCharacters(dt);

        gameCam.update();
        renderer.setView(gameCam);

    }

    /**
     * Handles all input from the player
     */
    public void handleInput() {

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
                    if (isOccupied(x,y))
                        System.out.println("You can't put it there");
                    else {
                        switch (hud.getSelectedFood()) {
                            case 1:
                                foods.add(new SeedShooter(getWorld(), x, y, this));
                                break;
                            case 2:
                                foods.add(new Unicorn(getWorld(), x, y, this));
                                break;
                            case 3:
                                foods.add(new ExplosiveBarry(getWorld(), x, y, this));
                                break;
                            case 4:
                                foods.add(new CoolNapple(getWorld(), x, y, this));
                                break;
                        }
                        Hud.removeCorn(Hud.getCost()[hud.getSelectedFood() - 1]);
                        hud.setSelectedFood(0);
                        hud.setSelected(false);
                    }
                }
                else
                    System.out.println("You can't put it there");
            }
    }

    /**
     * Verifies if a food can be placed in the selected spot
     * @param x x coordinate
     * @param y y coordinate
     * @return true if can be placed, false otherwise
     */
    public boolean isOccupied(int x, int y){
        for (int i = 0; i < chicken.size(); i++)
            if (chicken.get(i).getBody().getPosition().x > (x - tileSize/2) && chicken.get(i).getBody().getPosition().x < (x + tileSize/2) && chicken.get(i).getBody().getPosition().y > (y - tileSize/2) && chicken.get(i).getBody().getPosition().y < (y + tileSize/2) )
                return true;
        for (int i = 0; i < foods.size(); i++)
            if (foods.get(i).getBody().getPosition().x > (x - tileSize/2) && foods.get(i).getBody().getPosition().x < (x + tileSize/2)  && foods.get(i).getBody().getPosition().y > (y - tileSize/2) && foods.get(i).getBody().getPosition().y < (y + tileSize/2))
                return true;
        return false;
    }

    /**
     * Verifies if a selected food is within bounds
     * @param px x coordinate
     * @param py y coordinate
     * @return true if can be placed, false otherwise
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
            if (chicken.get(i).isDead()) {
                nChickenKilled++;
                chicken.remove(i);
                i--;
            }
            else
                chicken.get(i).update(dt);
        }

        for (int i = 0; i < this.foods.size(); i++) {
            if (foods.get(i).isDead()) {
                foods.remove(i);
                i--;
            }
            else
                foods.get(i).update(dt);
        }

        for (int i = 0; i < this.butters.size(); i++) {
            if (butters.get(i).isDead()) {
                butters.remove(i);
                i--;
            }
            else
                butters.get(i).update();
        }
    }

    /**
     * Randomly generates chickens for each lane every CHICKEN_GEN seconds
     */
    private void GenerateChickens(int possible_chickens) {
        timer++;
        if(timer%CHICKEN_GEN == 0){
            Chicken c = null;
            Random rn = new Random();
            int value = rn.nextInt(Integer.SIZE -1)%5;
            int y = diffY[value];
            if (counter < INI_NOR_CHICKEN) {
                c = new NormalChicken(getWorld(), INITIAL_CHICKEN_X, y, this);
                c.getBody().applyLinearImpulse(new Vector2(-c.getVelocity(), 0), c.getBody().getWorldCenter(), true);
                chicken.add(c);
                counter++;
                return;
            }

            Random chi = new Random();
            int a = chi.nextInt(Integer.SIZE -1)%possible_chickens;

            switch(a){
                case 0:
                    c = new NormalChicken(getWorld(),INITIAL_CHICKEN_X, y, this);
                    break;
                case 1:
                    c = new StrongChicken(getWorld(), INITIAL_CHICKEN_X, y, this);
                    break;
                case 2:
                    c = new MadChicken(getWorld(), INITIAL_CHICKEN_X, y, this);
                    break;
                case 3:
                    c = new EggSplosion(getWorld(), INITIAL_CHICKEN_X, y, this);
                    break;
                case 4:
                    c = new SmallChickenEgg(getWorld(), INITIAL_CHICKEN_X, y, this);
                    break;
            }

            c.getBody().applyLinearImpulse(new Vector2(-c.getVelocity(), 0), c.getBody().getWorldCenter(), true);
            chicken.add(c);
            nChickenGEN++;
        }
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
        CoolNapple = new TextureAtlas("CoolNapple.pack");
    }

    /**
     * Creates the butters, used for a second chance in the game
     */
    public void createButters(){
        for (int i = 0; i < diffY.length; i++){
            Butter b = new Butter(getWorld(), BUTTER_X , diffY[i]);
            butters.add(b);
        }
    }

    /**
     * Sets the music for the Screen
     */
    private void setMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("chocobo.mp3"));
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();
    }

    /**
     * @return Returns the chicken vector
     */
    public Vector<Chicken> getChickens(){
        return chicken;
    }

    /**
     * @return Returns the foods vector
     */
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
    /**
     * @return Returns the Seed atlas
     */
    public TextureAtlas getSeed() {
        return Seed;
    }
    /**
     * @return Returns the ExplosiveBarry atlas
     */
    public TextureAtlas getExplosiveBarry() { return ExplosiveBarry;}

    /**
     * @return Returns the CoolNapple atlas
     */
    public TextureAtlas getCoolNapple() { return CoolNapple;}

    /**
     * @return Returns the world
     */
    public World getWorld(){
        return world;
    }

    /**
     * Sets the world
     */
    public void setWorld(){world = new World(new Vector2(0,0),true);}

    /**
     * @return Returns the current level
     */
    public static int getLevel(){
        return level;
    }

    /**
     * Game is Over
     */
    public static void setGameOver(){
        gameOver = true;
    }

    /**
     * @return Returns the number of chickens killed
     */
    public static int getChickensKilled(){
        return nChickenKilled;
    }
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
        music.dispose();
    }

}
