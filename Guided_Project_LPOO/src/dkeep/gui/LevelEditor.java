package dkeep.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class LevelEditor {

	private JFrame Editor = new JFrame("Level Editor");
	private JButton ButtonBack = new JButton("Back");
	private JButton ButtonWall = new JButton("Wall");
	private JButton ButtonExitDoor = new JButton("Exit Door");
	private JButton ButtonKey = new JButton("Key");
	private JButton ButtonHero = new JButton("Hero");
	private JButton ButtonSaveMap = new JButton("Save Map");
	private JLabel lblEditStatus = new JLabel();
	private JButton ButtonOgre = new JButton("Ogre");
	private JButton SaveSlot1 = new JButton("Save Map 1");
	private JButton SaveSlot2 = new JButton("Save Map 2");
	private JButton SaveSlot3 = new JButton("Save Map 3");
	private JButton SaveSlot4 = new JButton("Save Map 4");
	private	JPanel Map;
	private int width, height, nOgres;
	public int  nOgresPlaced, nHeroesPlaced, nWallsPlaced, nDoorsPlaced, nKeysPlaced;
	private char selected;
	public char[][] charMap;
	

	public LevelEditor(){}
	/**
	 * @param nOgres 
	 * @param height 
	 * @param width 
	 * @wbp.parser.entryPoint
	 */
	public LevelEditor(int width, int height, int  nOgres){
		this.width = width;
		this.height = height;
		this.nOgres = nOgres;
		this.charMap = new char[width][height];
		this.selected = ' ';
		initialise();
	}

	public void initialise(){

		Editor.setBounds(500, 10, 1000, 1000);
		Editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Editor.getContentPane().setLayout(null);


		lblEditStatus.setText("Choose an element and click on a cell");
		lblEditStatus.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		for (int i = 0; i <  charMap.length; i++)
			for (int j = 0; j < charMap[i].length; j++)
				charMap[i][j] = ' '; //it's a white square

		Map = new GraphicsLevelEditor(width, height, this);
		Map.setBounds(50, 50, width*100, height*100);

		ButtonWall.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblEditStatus.setText("Choose an element and click on a cell");
				selected = 'X';
			}
		});

		ButtonExitDoor.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonExitDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblEditStatus.setText("Choose an element and click on a cell");
				selected = 'I';
			}
		});

		ButtonKey.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblEditStatus.setText("Choose an element and click on a cell");
				selected = 'k';
			}
		});

		ButtonHero.setFont(new Font("Tahoma", Font.PLAIN, 15));	
		ButtonHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblEditStatus.setText("Choose an element and click on a cell");
				selected = 'A';
			}
		});

		ButtonOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblEditStatus.setText("Choose an element and click on a cell");
				selected = 'O';
			}
		});

		SaveSlot1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkElements()){

					if (!checkBorders()){
						lblEditStatus.setText("The borders must be walls only or walls and one door");
						return;
					}

					if (!checkCorners()){
						lblEditStatus.setText("You can't have a door in a corner");
						return;
					}

					addFile(1);

					lblEditStatus.setText("Map has been saved in the file Map1!");
				}
			}
		});


		SaveSlot2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkElements()){

					if (!checkBorders()){
						lblEditStatus.setText("The borders must be walls only or walls and one door");
						return;
					}

					if (!checkCorners()){
						lblEditStatus.setText("You can't have a door in a corner");
						return;
					}

					addFile(2);

					lblEditStatus.setText("Map has been saved in the file Map2!");
				}
			}
		});


		SaveSlot3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkElements()){

					if (!checkBorders()){
						lblEditStatus.setText("The borders must be walls only or walls and one door");
						return;
					}

					if (!checkCorners()){
						lblEditStatus.setText("You can't have a door in a corner");
						return;
					}

					addFile(3);

					lblEditStatus.setText("Map has been saved in the file Map3!");
				}

			}
		});



		SaveSlot4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkElements()){

					if (!checkBorders()){
						lblEditStatus.setText("The borders must be walls only or walls and one door");
						return;
					}

					if (!checkCorners()){
						lblEditStatus.setText("You can't have a door in a corner");
						return;
					}

					addFile(4);

					lblEditStatus.setText("Map has been saved in the file Map4!");
				}

			}
		});
	

		ButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editor.setVisible(false);
				MainMenu m = new MainMenu();
				m.getMainMenu().setVisible(true);
				getEditor().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		setBounds();

		addContent();		

	}

	public void addFile(int map){

		try {
			FileOutputStream fileOut = new FileOutputStream("src/Map" + map);						
			fileOut.write(width +'0');
			fileOut.write('\n');
			fileOut.write(height +'0');
			fileOut.write('\n');
			fileOut.write(nOgres +'0');
			fileOut.write('\n');
			for (int j = 0; j < height ;j++){						
				for (int i = 0; i < width; i++)		
					fileOut.write(charMap[i][j]);
				fileOut.write('\n');
			}
			fileOut.close();
		} catch (IOException e1) {
			lblEditStatus.setText("There was an error on saving the map");
			e1.printStackTrace();
		}
	}
	
	public boolean checkElements(){

		if ( nOgresPlaced == 0 || nDoorsPlaced == 0 || nHeroesPlaced == 0|| nWallsPlaced == 0 || nKeysPlaced == 0){
			lblEditStatus.setText("There's, at least, a missing element to the map!");
			return false;
		}

		if (nOgresPlaced > 1){
			lblEditStatus.setText("Place only ONE Ogre");
			return false;
		}	
		if (nHeroesPlaced > 1){
			lblEditStatus.setText("Place only ONE Hero");
			return false;
		}	

		if (nKeysPlaced > 1){
			lblEditStatus.setText("Place only ONE Key");
			return false;
		}		

		if (nDoorsPlaced > 1){
			lblEditStatus.setText("Place only ONE Door");
			return false;
		}		

		return true;
	}

	public boolean checkCorners(){
		if (charMap[0][0] == 'I' || charMap[width-1][0] == 'I' || charMap[0][height-1] == 'I' || charMap[width-1][height-1] == 'I')
			return false;
		return true;

	}

	public boolean checkBorders(){

		//upper border
		for(int i = 0; i < width;i++)
			if(charMap[i][0] == 'O'|| charMap[i][0] == 'A' || charMap[i][0] == 'k' ||charMap[i][0] == ' ')
				return false;			

		//lower border
		for(int i = 0; i < width;i++)
			if(charMap[i][height-1] == 'O'||charMap[i][height-1] == 'A' || charMap[i][height-1] == 'k' ||charMap[i][height-1] == ' ')
				return false;

		//left border
		for(int j = 0; j < height;j++)
			if(charMap[0][j] == 'O' || charMap[0][j] == 'A'  || charMap[0][j] == 'k' || charMap[0][j] == ' ')
				return false;			

		//right border
		for(int j = 0; j < height;j++)
			if(charMap[width-1][j] == 'O' || charMap[width-1][j] == 'A'  || charMap[width-1][j] == 'k' || charMap[width-1][j] == ' ')					
				return false;			

		return true;

	}

	
	public void addContent(){
		Editor.getContentPane().add(lblEditStatus);	
		Editor.getContentPane().add(Map);
		Editor.getContentPane().add(ButtonWall);
		Editor.getContentPane().add(ButtonHero);
		Editor.getContentPane().add(ButtonOgre);
		Editor.getContentPane().add(ButtonExitDoor);
		Editor.getContentPane().add(ButtonKey);
		Editor.getContentPane().add(ButtonSaveMap);
		Editor.getContentPane().add(ButtonBack);
		Editor.getContentPane().add(SaveSlot1);
		Editor.getContentPane().add(SaveSlot2);
		Editor.getContentPane().add(SaveSlot3);
		Editor.getContentPane().add(SaveSlot4);
	}

	public void setBounds(){		
		ButtonWall.setBounds(844, 266, 97, 25);
		lblEditStatus.setBounds(91, 885, 367, 29);		
		ButtonKey.setBounds(844, 397, 97, 25);
		ButtonExitDoor.setBounds(844, 332, 97, 25);
		ButtonHero.setBounds(844, 458, 97, 25);
		ButtonOgre.setBounds(844, 204, 97, 25);
		ButtonBack.setBounds(844, 788, 114, 29);
		SaveSlot1.setBounds(50, 790, 114, 29);
		SaveSlot2.setBounds(258, 790, 114, 29);
		SaveSlot3.setBounds(454, 788, 114, 29);
		SaveSlot4.setBounds(649, 790, 114, 29);
	}
	
	public char[][] getCharMap(){return charMap;}

	public JFrame getEditor() {return Editor;}

	public char getSelected(){return selected;}
}