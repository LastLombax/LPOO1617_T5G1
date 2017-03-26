package dkeep.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSeparatorUI;

import dkeep.logic.Game;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
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
		lblEditStatus.setBounds(142, 834, 367, 29);		


		for (int i = 0; i <  charMap.length; i++)
			for (int j = 0; j < charMap[i].length; j++)
				charMap[i][j] = ' '; //it's a white square


		Map = new GraphicsLevelEditor(width, height, this);
		Map.setBounds(50, 50, width*100, height*100);





		ButtonWall.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'X';
			}
		});

		ButtonWall.setBounds(844, 266, 97, 25);



		ButtonExitDoor.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonExitDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'I';
			}
		});

		ButtonExitDoor.setBounds(844, 332, 97, 25);



		ButtonKey.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'k';
			}
		});

		ButtonKey.setBounds(844, 397, 97, 25);



		ButtonHero.setFont(new Font("Tahoma", Font.PLAIN, 15));	
		ButtonHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'A';
			}
		});

		ButtonHero.setBounds(844, 458, 97, 25);




		ButtonOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'O';
			}
		});

		ButtonOgre.setBounds(844, 204, 97, 25);


		ButtonSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkElements()){
					
					if (!checkBorders())
						lblEditStatus.setText("The borders can't be empty cells!");
					
					addFile();

					lblEditStatus.setText("Map has been saved in the file Map1!");

				}
			}
		});

		ButtonSaveMap.setBounds(619, 912, 114, 27);


		ButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editor.setVisible(false);
				MainMenu m = new MainMenu();
				m.getMainMenu().setVisible(true);
				getEditor().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		ButtonBack.setBounds(66, 911, 114, 29);

		addContent();		

	}

	public void addFile(){
		try {
			FileOutputStream fileOut = new FileOutputStream("src/Map1");						
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

	public boolean checkBorders(){

		//upper border

		for(int i = 0; i < width;i++)
			if(charMap[i][0] == ' ' || charMap[i][0] != 'X' || charMap[i][0] != 'I')
				return false;

		//lower border
		for(int i = 0; i < width;i++)
			if(charMap[i][height-1] == ' ')
				return false;

		//left border
		for(int j = 0; j < height;j++)
			if(charMap[0][j] == ' ')
				return false;

		//right border
		for(int j = 0; j < height;j++)
			if(charMap[width-1][j] == ' ')
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
	}

	public char[][] getCharMap(){return charMap;}

	public JFrame getEditor() {return Editor;}

	public char getSelected(){return selected;}

}