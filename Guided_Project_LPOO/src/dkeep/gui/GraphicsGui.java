package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import dkeep.logic.Game;

public class GraphicsGui extends JPanel{

	private BufferedImage wall, hero, heroArmed, heroKey, ogre, ogreStunned, club, guard, guardSleeping, key, lever, coverKey, door, doorOpened;
	private char[][] map;
	private Game game;	

	public GraphicsGui(Game g) {		
		setGame(g);
		updateGame();		
		loadCharacterImages();		
		loadMapImages();

	}

	public void loadCharacterImages(){
		try {			
			hero = ImageIO.read(new File("src/Hero.png"));
			heroArmed = ImageIO.read(new File("src/HeroArmed.png"));
			heroKey = ImageIO.read(new File("src/HeroKey.png"));
			ogre = ImageIO.read(new File("src/Ogre.png"));
			ogreStunned = ImageIO.read(new File("src/OgreStunned.png"));
			club = ImageIO.read(new File("src/Club.png"));			
			guard = ImageIO.read(new File("src/Guard.png"));
			guardSleeping = ImageIO.read(new File("src/GuardSleeping.png"));
			coverKey = ImageIO.read(new File("src/OgreOnKey.png"));			
		} catch (IOException e) {System.out.println("Image not found");}
	}

	public void loadMapImages(){
		try {			
			wall = ImageIO.read(new File("src/wall.png"));			
			key = ImageIO.read(new File("src/Key.png"));
			lever = ImageIO.read(new File("src/Lever.png"));
			door = ImageIO.read(new File("src/door.png"));
			doorOpened = ImageIO.read(new File("src/doorOpened.png"));			

		} catch (IOException e) {
			System.out.println("Image not found");
			e.printStackTrace();
		}		
	}

	@Override
	protected void paintComponent(Graphics g) {		
		super.paintComponent(g);
		this.map = getGame().getFullMap();
		int size = 60;
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				if (map[i][j] == 'X')
					g.drawImage(wall, j*size, i*size, size, size, null);
				else if (map[i][j] == 'H')
					g.drawImage(hero, j*size, i*size, size, size, null);
				else if (map[i][j] == 'K')
					g.drawImage(heroKey, j*size, i*size, size, size, null);
				else if (map[i][j] == 'I')
					g.drawImage(door, j*size, i*size, size, size, null);
				else if (map[i][j] == 'S')
					g.drawImage(doorOpened, j*size, i*size, size, size, null);
				else if (map[i][j] == 'G')
					g.drawImage(guard, j*size, i*size, size, size, null);
				else if (map[i][j] == 'g')
					g.drawImage(guardSleeping, j*size, i*size, size, size, null);
				else if (map[i][j] == 'O')
					g.drawImage(ogre, j*size, i*size, size, size, null);
				else if (map[i][j] == '8')
					g.drawImage(ogreStunned, j*size, i*size, size, size, null);
				else if (map[i][j] == '*')
					g.drawImage(club, j*size, i*size, size, size, null);
				else if (map[i][j] == 'k' && game.getMap().hasLever())
					g.drawImage(lever, j*size, i*size, size, size, null);				
				else if (map[i][j] == 'k' && game.getMap().hasKey())
					g.drawImage(key, j*size, i*size, size, size, null);	
				else if (map[i][j] == 'A')
					g.drawImage(heroArmed, j*size, i*size, size, size, null);				
				else if (map[i][j] == '$')
					g.drawImage(coverKey, j*size, i*size, size, size, null);
				else 					
					g.setColor(Color.LIGHT_GRAY);
			}
		}
		updateGame();
	}

	public void updateGame(){
		this.game.setFullMap();
		this.repaint(); 
		this.requestFocusInWindow();
	}

	public Game getGame() {return game;}

	public void setGame(Game g) {this.game = g;}
}

