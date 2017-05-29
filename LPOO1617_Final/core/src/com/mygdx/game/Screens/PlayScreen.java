package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.game.Sprites.Chickens.Chicken;
import com.mygdx.game.Sprites.Foods.Food;
import com.mygdx.game.Sprites.Chickens.NormalChicken;
import com.mygdx.game.Sprites.Foods.Peashooter;
import com.mygdx.game.Tools.B2WorldCreator;

import java.util.Vector;


/**
 * Created by vitor on 06/04/2017.
 */

public class PlayScreen implements Screen{
    private ChickenVsFood game;
    private Vector<Chicken> chicken = new Vector<Chicken>();

    private Vector<Food> foods = new Vector<Food>();
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    protected float accumulator;

    //placement variables
    int ORIGINAL_X_MID = 575;
    int FINAL_X_MID = 1855;
    int ORIGINAL_Y_MID = 185;
    int FINAL_Y_MID = 793;
    int GAP = 60;
    int tileSize = 128;

    public PlayScreen(ChickenVsFood game){
        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(ChickenVsFood.V_WIDTH,ChickenVsFood.V_HEIGHT,gameCam);
        hud = new Hud(game.batch, game);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1);
        loadAssets();
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        setWorld();
        b2dr = new Box2DDebugRenderer();
        b2dr.SHAPE_STATIC.set(1,0,0,1);

        chicken.add(new NormalChicken(getWorld(),game,1800,690));
        chicken.add(new NormalChicken(getWorld(),game,1800,565));
        chicken.add(new NormalChicken(getWorld(),game,1800,440));
        chicken.add(new NormalChicken(getWorld(),game,1800,310));
        chicken.add(new NormalChicken(getWorld(),game,1800,185));

        new B2WorldCreator(world, map);
    }

    public World getWorld(){
        return world;
    }

    public void setWorld(){
        world = new World(new Vector2(0,0),true);
    }

    public void loadAssets(){
        game.getAssetManager().load("Chicken.png", Texture.class);
        game.getAssetManager().finishLoading();
    }

    public void handleInput(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            for (int i = 0; i < this.chicken.size(); i++) {
                chicken.get(i).getBody().applyLinearImpulse(new Vector2(-chicken.get(i).getVelocity(), 0), chicken.get(i).getBody().getWorldCenter(), true);
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.S) /*&& chicken.b2body.getLinearVelocity().x <= 2*/) {
            for (int i = 0; i < this.chicken.size(); i++) {
                chicken.get(i).getBody().applyLinearImpulse(new Vector2(0, -chicken.get(i).getVelocity()), chicken.get(i).getBody().getWorldCenter(), true);
            }

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            System.out.println("devia sair");
            new MainMenuScreen(game);
            dispose();

        } else if (hud.isSelected) //puts food in selected location
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

                    switch (hud.selectedFood) {
                        case 1:
                            foods.add(new Peashooter(getWorld(), game, x, y));
                            break;
                        case 2:
                            foods.add(new Peashooter(getWorld(), game, x, y));
                            break;
                    }
                    hud.selectedFood = 0;
                    hud.isSelected = false;
                } else
                    System.out.println("You can't put it there");
            }
    }

    public boolean checkPlacingBounds(double px, double py){
        if (px >= 512 && px <= 1790 && py >= 180 && py <=695)
            return true;
        return false;
    }

    public void updateCharacters(float dt){
        for (int i = 0; i < this.chicken.size(); i++)
            chicken.get(i).update(dt);

        for (int i = 0; i < this.foods.size(); i++)
            foods.get(i).update(dt);
    }

    public void update(float dt) {
        handleInput(dt);

        //takes 1 step in the physics simulation (60 times per second)
        float frameTime = Math.min(dt, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

        updateCharacters(dt);

        gameCam.update();
        renderer.setView(gameCam);
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        game.batch.begin();

        for (int i = 0; i < this.chicken.size(); i++){
            chicken.get(i).draw(game.batch);
        }

        for (int i = 0; i < this.foods.size(); i++){
            foods.get(i).draw(game.batch);
        }
        //onde desenhar as cenas, cois.draw();
        game.batch.end();
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);

        gamePort.update(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
