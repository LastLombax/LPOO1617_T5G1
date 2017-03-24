package dkeep.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
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
		

		lblEditStatus.setText("Bananas");
		lblEditStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditStatus.setBounds(142, 834, 367, 29);		

	
		for (int i = 0; i <  charMap.length; i++)
			for (int j = 0; j < charMap[i].length; j++)
				charMap[i][j] = ' '; //it's a white square


		Map = new GraphicsLevelEditor(width, height, this);
		Map.setBounds(50, 50, width*100, height*100);
		

		


		ButtonWall.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {selected = 'W';}
		});

		ButtonWall.setBounds(844, 266, 97, 25);
		


		ButtonExitDoor.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonExitDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'D';
			}
		});
		
		ButtonExitDoor.setBounds(844, 332, 97, 25);
		


		ButtonKey.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		ButtonKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 'K';
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