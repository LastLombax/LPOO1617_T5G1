package dkeep.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import dkeep.logic.Game;

public class GraphicsGui extends JPanel{

	BufferedImage wall;
	BufferedImage hero, heroArmed, heroKey;
	BufferedImage ogre, ogreStunned, club;
	BufferedImage guard, guardSleeping;
	BufferedImage key, lever, coverKey;
	BufferedImage door, doorOpened;
	char[][] map;
	private Game game;

	public GraphicsGui(Game g) {		
		setGame(g);
		updateGame();		
		try {			
			wall = ImageIO.read(new File("src/wall.png"));
			hero = ImageIO.read(new File("src/CloudR.png"));
			heroArmed = ImageIO.read(new File("src/CloudArmed.png"));
			heroKey = ImageIO.read(new File("src/heroKey.png"));
			ogre = ImageIO.read(new File("src/Sephiroth.png"));
			ogreStunned = ImageIO.read(new File("src/SephirothStunned.png"));
			club = ImageIO.read(new File("src/Masamune.png"));			
			guard = ImageIO.read(new File("src/Guard.png"));
			guardSleeping = ImageIO.read(new File("src/GuardStopped.png"));
			key = ImageIO.read(new File("src/Key.png"));
			lever = ImageIO.read(new File("src/Lever.png"));
			coverKey = ImageIO.read(new File("src/coverKey.png"));
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
		int size = 40;
		this.map = getGame().getFullMap();
		
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map.length; j++){
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
					g.setColor(Color.WHITE);
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

