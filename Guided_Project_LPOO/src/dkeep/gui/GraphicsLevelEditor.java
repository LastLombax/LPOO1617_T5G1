package dkeep.gui;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsLevelEditor extends JPanel implements MouseListener{

	private int size = 100,  width, height;
	private BufferedImage wall, heroArmed, ogre, key,  door, square;
	LevelEditor lv = new LevelEditor();

	public GraphicsLevelEditor(int width, int height, LevelEditor lv) {	
		setCoord(width, height);
		setEditor(lv);		
		loadEditImages();
		this.repaint();
		this.addMouseListener(this);
	}

	public void loadEditImages(){
		try {			
			wall = ImageIO.read(new File("src/wall.png"));
			heroArmed = ImageIO.read(new File("src/HeroArmed.png"));
			ogre = ImageIO.read(new File("src/Ogre.png"));	
			key = ImageIO.read(new File("src/Key.png"));
			door = ImageIO.read(new File("src/door.png"));
			square = ImageIO.read(new File("src/square.jpg"));

		} catch (IOException e) {System.out.println("Image not found");	}		
	}

	@Override
	protected void paintComponent(Graphics g) {	
		super.paintComponent(g);	
		for (int i = 0; i <  lv.getCharMap().length; i++)
			for (int j = 0; j < lv.getCharMap()[i].length; j++){
				 if (lv.getCharMap()[i][j] == 'O')
					g.drawImage(ogre, i*size, j*size, size, size, null);			
				else if (lv.getCharMap()[i][j] == 'A')
					g.drawImage(heroArmed, i*size, j*size, size, size, null);	
				 checkStaticElements(i,j, g);			
			}
	}

	public void checkStaticElements(int i, int j, Graphics g){
		if (lv.getCharMap()[i][j] == ' ')					
			g.drawImage(square, i*size, j*size, size, size, null);	
		else if (lv.getCharMap()[i][j] == 'X')
			g.drawImage(wall, i*size, j*size, size, size, null);
		else if (lv.getCharMap()[i][j] == 'k')
			g.drawImage(key,  i*size, j*size, size, size, null);		
		else if (lv.getCharMap()[i][j] == 'I')
			g.drawImage(door,  i*size, j*size, size, size, null);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

		double posX = this.getMousePosition().getX()/size;
		double posY = this.getMousePosition().getY()/size;

		for (int i = 1; i <= width;i++)
			for (int j = 1 ; j <= height; j++){
				if (posX >= (i-1) && posX < i && posY >=(j-1) && posY < j)
				{
					if (lv.getCharMap()[i-1][j-1] == ' ' && lv.getSelected() != ' ') //if cell is empty
					{
						lv.getCharMap()[i-1][j-1] = lv.getSelected();
						checkElement(i-1,j-1, 1);
					}
					else if (lv.getCharMap()[i-1][j-1]  == lv.getSelected()) // if you select the wrong cell
					{
						checkElement(i-1,j-1, -1);
						lv.getCharMap()[i-1][j-1] = ' ';

					}
					else if (lv.getCharMap()[i-1][j-1]  != ' ') //if you want to replace a cell
					{
						checkElement(i-1,j-1, -1);
						lv.getCharMap()[i-1][j-1] = lv.getSelected();	
						checkElement(i-1,j-1,1);
					}
				}
			}
		repaint();
	}


	public void checkElement(int x, int y, int inc){
			if (lv.getCharMap()[x][y] == 'O')
				lv.nOgresPlaced += inc;
			else if (lv.getCharMap()[x][y] == 'A')
				lv.nHeroesPlaced+= inc;
			checkStaticElements(x,y,inc);
	}
	
	public void checkStaticElements(int x, int y, int inc){
		if (lv.getCharMap()[x][y] == 'k')
			lv.nKeysPlaced+= inc;
		else if (lv.getCharMap()[x][y] == 'X')
			lv.nWallsPlaced+= inc;
		else if(lv.getCharMap()[x][y] == 'I')
			lv.nDoorsPlaced+= inc;
	}


	public void setCoord(int x, int y){	this.width = x;	this.height = y;}

	public void setEditor(LevelEditor lv){this.lv = lv;}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

