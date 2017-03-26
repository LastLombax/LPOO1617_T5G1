package dkeep.test;
  
import static org.junit.Assert.*;

import org.junit.Test; 


import java.util.Vector;

import org.junit.Test;
  
import dkeep.logic.*;

public class TestTest { 

	char [][] map = {{'X','X','X','X','X'},
			{'X',' ',' ',' ','X'},
			{' ',' ',' ',' ','X'},
			{' ',' ',' ',' ','X'},
			{'X','X','X','X','X'}};

	char [][] map_dungeon = new char[10][10];
	char[][] map_keep = new char [9][9];

	public void fillmapdungeon(){
		map_dungeon[1][4] = 'I';
		map_dungeon[3][4] = 'I';
		map_dungeon[3][2] = 'I';
		map_dungeon[8][2] = 'I';
		map_dungeon[8][4] = 'I';

		//added wall
		for(int i = 0; i < 10;i++){
			if(map_dungeon[i][0]!='I')
				map_dungeon[i][0]='X';			
		}	
		for(int i = 0; i < 10;i++){
			map_dungeon[i][9]='X';			
		}
		for(int j = 0; j < 10;j++){
			map_dungeon[0][j]='X';			
		}
		for(int j = 0; j < 10;j++){
			map_dungeon[9][j]='X';			
		}
		map_dungeon[1][6] =
				map_dungeon[2][1] = map_dungeon[2][2] =map_dungeon[2][4] = map_dungeon[2][5] = map_dungeon[2][6] = 
				map_dungeon[3][6] =
				map_dungeon[4][1] = map_dungeon[4][2] = map_dungeon[4][4] = map_dungeon[4][5] = map_dungeon[4][6] =
				map_dungeon[7][1] = map_dungeon[7][2] = map_dungeon[7][4] = map_dungeon[7][5] = map_dungeon[7][6] = map_dungeon[7][7] =
				map_dungeon[8][6] = 'X';
	}

	public void fillmapkeep(){
		//added wall
		for(int i = 0; i < 9;i++){
			if(map_keep[i][0]!='I')
				map_keep[i][0]='X';			
		}	
		for(int i = 0; i < 9;i++){
			map_keep[i][8]='X';			
		}
		for(int j = 0; j < 9;j++){
			map_keep[0][j]='X';			
		}
		for(int j = 0; j < 9;j++){
			map_keep[8][j]='X';			
		}
	}

	Hero H = new Hero(1,1,'H');
	Guard G = new Guard(1,3,'G',(int) Math.floor(Math.random()*3));
	Lever k = new Lever(3,1,'k');
	Ogre O = new Ogre(1,3,'O',7);
	Key K = new Key(3,1,'k');
	Vector<Exit> exits = new Vector<Exit>();
	{exits.add(new Exit(2,0,'I'));
	exits.add(new Exit(3,0,'I'));}


	@Test
	public void TestMoveHeroIntoFreeCell(){
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("d");
		game.print();
		assertEquals(1,game.getHero().getCoordenateI());
		assertEquals(2,game.getHero().getCoordenateJ());		
	}

	@Test
	public void Testcopy(){
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		char [][] map2 = game.copyMap();
		for(int i =0; i < 5; i++)
			for(int j=0; j < 5;j++)
				assertEquals(map2[i][j],game.getMap().getMap()[i][j]);	
	}

	@Test
	public void TestMoveHeroIntoWall() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("w");
		assertEquals(1,game.getHero().getCoordenateI());
		assertEquals(1,game.getHero().getCoordenateJ());
	}

	@Test
	public void TestMoveAdjGuard() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		Guard J =new Guard(1,3,'G',0);
		assertEquals(null,J.getClub());
		gameMap.setGuard(J);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		assertEquals(2,game.movement("d"));
	}

	@Test
	public void TestMoveTowardsDoorWhithioutKey() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("s");
		assertEquals(1,game.movement("a"));
	}

	@Test
	public void TestMoveTowardsLever() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("s");
		assertEquals(4,game.movement("s"));
		assertEquals('S',game.getExits().get(0).getSprite());
	}

	@Test
	public void TestMoveToExitWhithLever() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		gameMap.setGuard(G);
		gameMap.setHero(H);
		gameMap.setLever(k);
		Game game = new Game(gameMap);
		game.movement("s");
		game.movement("s");
		assertEquals(0,game.movement("a"));
	}

	@Test
	public void TestMoveAdjOgre() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setOgre(O);
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		int tmp = 0;
		do{
			int rand = (int) Math.floor(Math.random()*4);
			switch(rand){
			case 0:
				tmp = game.movement("a");
				break;
			case 1:
				tmp = game.movement("s");
				break;
			case 2:
				tmp = game.movement("w");
				break;
			case 3:
				tmp = game.movement("d");
				break;
			}
			
		}while(tmp!=2);
		
		assertEquals(2,tmp);
	}

	@Test
	public void TestMovetoKey() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.movement("s");
		game.movement("s");
		assertEquals('K',game.getHero().getSprite());
	}

	@Test
	public void TestMovetoExitWithoutKey() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setOgre(O);
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.movement("s");
		assertEquals(1,game.movement("a"));
	}

	@Test
	public void TestMovetoExitOpensDoor() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club());
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.movement("s");
		game.movement("s");
		game.movement("a");
		assertEquals('S',game.getExits().get(1).getSprite());
	}

	@Test
	public void TestMovetoExitWithKey() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.movement("s");
		game.movement("s");
		game.movement("a");
		assertEquals(0,game.movement("a"));
	}
	@Test
	public void TestNextMap() {
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		game.addMap(new Dungeon());
		game.movement("s");
		game.movement("s");
		game.movement("a");
		game.movement("a");
		game.movement("s");
		game.movement("w");
		assertEquals(1,game.movement("s"));
	}

	@Test
	public void TestConstructorDungeon(){
		GameMap dungeon = new Dungeon();
		fillmapdungeon();
		for(int i =0; i < 10; i++)
			for(int j=0; j < 10;j++)
				assertEquals(map_dungeon[i][j],dungeon.getMap()[i][j]);
		
		dungeon.setMap(map_dungeon);
		dungeon.setHero(H);
		dungeon.setGuard(G);
		dungeon.setClub(new Club());
		dungeon.setOgre(new Ogre());
		dungeon.setKey(new Key());
		dungeon.setLever(new Lever());
		dungeon.setHeroClub(new Club());
		dungeon.setExits(new Vector<Exit>());

		assertEquals(true,dungeon.hasLever());
		assertEquals(false,dungeon.hasOgre());
		assertEquals(false,dungeon.hasCLub());
		assertEquals(true,dungeon.hasGuard());
		assertEquals(false,dungeon.hasHeroClub());
		assertEquals(false,dungeon.hasKey());
		assertEquals(null,dungeon.getKey());
		assertEquals(null,dungeon.getOgres());
		assertEquals(null,dungeon.getClubs());
		assertEquals(null,dungeon.getHeroClub());
	}
	
	@Test
	public void TestConstructors2(){
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setHero(H);
		gameMap.setKey(K);
		gameMap.setGuard(G);
		Game game = new Game(gameMap);
		game.setExits(exits);
		game.setFullMap();
		game.setHero(H);
		assertEquals(game.getHero().getCoordenateI(),H.getCoordenateI());
	}
	
	@Test
	public void TestConstructorGame(){
		Guard Guuard123 = new Guard(1,3,'G',1);
		Game game = new Game(new Dungeon());
		game.setGuard(new Guard(1,8,'G',1));
		boolean yo = false;
		do{
			int rand = (int) Math.floor(Math.random()*4);
			switch(rand){
			case 0:
			    game.movement("a");
				break;
			case 1:
				game.movement("s");
				break;
			case 2:
				game.movement("w");
				break;
			case 3:
				game.movement("d");
				break;
			}
			for(int i =0; i < game.getEnemies().size();i++){
				if(game.getEnemies().get(i).getSprite() == 'g')
					yo=true;
			}
		}while(!yo);
		assertEquals(true, yo);
	}
	
	@Test
	public void TestConstructorGuard(){
		Guard Guuard123 = new Guard(1,3,'G',2);
		Game game = new Game(new Dungeon());
		game.setGuard(new Guard(1,8,'G',2));
		boolean yo = false;
		do{
			int rand = (int) Math.floor(Math.random()*4);
			switch(rand){
			case 0:
			    if(game.movement("a") == 1){
			    	continue;
			    }
				break;
			case 1:
				if(game.movement("s") == 1){
					continue;
				}
			case 2:
				if(game.movement("d") == 1){
					continue;
				}
			case 3:
				if(game.movement("w") == 1){
					continue;
				}
			}
			for(int i =0; i < game.getEnemies().size();i++){
				if(game.getEnemies().get(i).getCoordenateI() == 5 && game.getEnemies().get(i).getCoordenateJ() == 8)
					yo=true;
			}
		}while(!yo);
		assertEquals(true, yo);
	}
	
	@Test
	public void TestConstructorOgreMovement(){
		GameMap gameMap = new GameMapTest();
		gameMap.setMap(map);
		gameMap.setExits(exits);
		O.setClub(new Club(-3,-3,'*'));
		gameMap.setOgre(O);
		gameMap.setHero(H);
		gameMap.setKey(K);
		Game game = new Game(gameMap);
		boolean yo = false;
		do{
			int rand = (int) Math.floor(Math.random()*4);
			switch(rand){
			case 0:
			    if(game.movement("a") == 1){
			    	continue;
			    }
				break;
			case 1:
				if(game.movement("s") == 1){
					continue;
				}
			case 2:
				if(game.movement("d") == 1){
					continue;
				}
			case 3:
				if(game.movement("w") == 1){
					continue;
				}
			}
			for(int i =0; i < game.getEnemies().size();i++){
				if(game.getEnemies().get(i).getCoordenateI() == 2 && game.getEnemies().get(i).getCoordenateJ() == 3 ||game.getEnemies().get(i).getCoordenateI() == 1 && game.getEnemies().get(i).getCoordenateJ() == 2)
					yo=true;
			}
		}while(!yo);
		assertEquals(true, yo);
	}

	@Test
	public void TestConstructorKeep(){
		GameMap keep = new Keep();
		fillmapkeep();
		for(int i =0; i < 9; i++)
			for(int j=0; j < 9;j++)
				assertEquals(map_keep[i][j],keep.getMap()[i][j]);
		
		keep.setMap(map_keep);
		keep.setHero(H);
		keep.setGuard(G);
		keep.setClub(new Club());
		keep.setOgre(new Ogre());
		keep.setKey(new Key());
		keep.setLever(new Lever());
		keep.setHeroClub(new Club());
		keep.setExits(new Vector<Exit>());

		assertEquals(false,keep.hasLever());
		assertEquals(true,keep.hasOgre());
		assertEquals(true,keep.hasCLub());
		assertEquals(false,keep.hasGuard());
		assertEquals(true,keep.hasHeroClub());
		assertEquals(true,keep.hasKey());
		
		Vector<Ogre>ogres = new Vector<Ogre>();
		ogres.add(new Ogre(1,4,'O',7));
		ogres.add(new Ogre(2,5,'O',7));
		assertEquals(1,keep.getOgres().get(0).getCoordenateI());
		assertEquals(7,keep.getHero().getCoordenateI());
		assertEquals(null,keep.getGuard());
		assertEquals(null,keep.getLever());
		assertEquals(6,keep.getHeroClub().getCoordenateI());
		assertEquals( 1,keep.getKey().getCoordenateI());
	}
	
	@Test
	public void TestConstructorEditedMap(){
		fillmapkeep();
		int[] heroCor = new int[2];
		heroCor[0] = 7;
		heroCor[1] = 1;
		int[] ogreCor = new int[2];
		ogreCor[0] = 1;
		ogreCor[1] = 4;
		GameMap edited_map = new EditedMap(this.map_keep,1,heroCor,ogreCor);
		for(int i =0; i < 9; i++)
			for(int j=0; j < 9;j++)
				assertEquals(map_keep[i][j],edited_map.getMap()[i][j]);
		
		edited_map.setMap(map_keep);
		edited_map.setHero(H);
		edited_map.setGuard(G);
		edited_map.setClub(new Club());
		edited_map.setOgre(new Ogre());
		edited_map.setKey(new Key());
		edited_map.setLever(new Lever());
		edited_map.setHeroClub(new Club());
		edited_map.setExits(new Vector<Exit>());

		assertEquals(false,edited_map.hasLever());
		assertEquals(true,edited_map.hasOgre());
		assertEquals(true,edited_map.hasCLub());
		assertEquals(false,edited_map.hasGuard());
		assertEquals(true,edited_map.hasHeroClub());
		assertEquals(true,edited_map.hasKey());
		
		Vector<Ogre>ogres = new Vector<Ogre>();
		ogres.add(new Ogre(1,4,'O',7));
		assertEquals(1,edited_map.getOgres().get(0).getCoordenateI());
		assertEquals(7,edited_map.getHero().getCoordenateI());
		assertEquals(null,edited_map.getGuard());
		assertEquals(null,edited_map.getLever());
		assertEquals(null,edited_map.getHeroClub());
	}
	
	@Test
	public void TestConstructorGame2(){
	  Game g = new Game(1);
	  g.setFullMap();
	  assertEquals(1,g.getHero().getCoordenateI());
	}
	
	@Test
	public void TestConstructors(){
		Lever lever_teste =new Lever();
		lever_teste.setCoordenateI(1);
		lever_teste.setCoordenateJ(1);
		lever_teste.setSprite('k');
		lever_teste.move();
		lever_teste.setStun(1);

		assertEquals(1,lever_teste.getCoordenateI());
		assertEquals(1,lever_teste.getCoordenateJ());
		assertEquals('k',lever_teste.getSprite());
		assertEquals(false,lever_teste.hasClub());
		assertEquals(null,lever_teste.getClub());
		assertEquals(0,lever_teste.getStun());
		
		//////
		Key k_teste = new Key();
		k_teste.setCoordenateI(1);
		k_teste.setCoordenateJ(1);
		k_teste.setSprite('k');
		k_teste.move();
		k_teste.setStun(1);
		
		assertEquals(1,k_teste.getCoordenateI());
		assertEquals(1,k_teste.getCoordenateJ());
		assertEquals('k',k_teste.getSprite());
		assertEquals(false,k_teste.hasClub());
		assertEquals(null,k_teste.getClub());
		assertEquals(0,k_teste.getStun());
		
		/////
		Hero H_teste = new Hero();
		H_teste.setCoordenateI(1);
		H_teste.setCoordenateJ(1);
		H_teste.setSprite('k');
		H_teste.move();
		
		assertEquals(1,H_teste.getCoordenateI());
		assertEquals(1,H_teste.getCoordenateJ());
		assertEquals('k',H_teste.getSprite());
		assertEquals(0,H_teste.getStun());
		
		////
		Exit e_teste = new Exit(); 
		e_teste.setCoordenateI(1);
		e_teste.setCoordenateJ(1);
		e_teste.setSprite('I');
		e_teste.move();
		e_teste.setStun(1);
		
		assertEquals(1,e_teste.getCoordenateI());
		assertEquals(1,e_teste.getCoordenateJ());
		assertEquals('I',e_teste.getSprite());
		assertEquals(false,e_teste.hasClub());
		assertEquals(null,e_teste.getClub());
		assertEquals(0,e_teste.getStun());
		
		/////
		Guard g_teste = new Guard(); 
		g_teste.setCoordenateI(1);
		g_teste.setCoordenateJ(1);
		g_teste.setSprite('I');
		
		assertEquals(1,g_teste.getCoordenateI());
		assertEquals(1,g_teste.getCoordenateJ());
		assertEquals('I',g_teste.getSprite());
		assertEquals(0,g_teste.getStun());
		
		/////
		Club c_teste = new Club();
		c_teste.setCoordenateI(1);
		c_teste.setCoordenateJ(1);
		c_teste.setSprite('I');
		c_teste.move();
		assertEquals(1,c_teste.getCoordenateI());
		assertEquals(1,c_teste.getCoordenateJ());
		assertEquals('I',c_teste.getSprite());
		assertEquals(false,c_teste.hasClub());
		assertEquals(null,c_teste.getClub());
		assertEquals(0,c_teste.getStun());

	}
	
	@Test
	public void TestRealGame(){
		fillmapdungeon();
		Game g = new Game();
		for(int i =0; i < 10; i++)
			for(int j=0; j < 10;j++)
				assertEquals(map_dungeon[i][j],g.getMap().getMap()[i][j]);
		
		g.movement("d");
		g.movement("d");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("s");
		g.movement("w");
		g.movement("w");
		g.movement("d");
		g.movement("d");
		g.movement("d");
		g.movement("d");
		g.movement("d");
		g.movement("s");
		g.movement("s");
		g.movement("a");
		g.movement("d");
		g.movement("w");
		g.movement("w");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		g.movement("a");
		
		fillmapkeep();
		for(int i =0; i < 9; i++)
			for(int j=0; j < 9;j++)
				assertEquals(map_keep[i][j],g.getMap().getMap()[i][j]);
		
	}


}
