package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dkeep.logic.Game;
import java.awt.Color;

import java.io.*;
import java.awt.Font;

public class StartGame {

	private JFrame GameWindow = new JFrame("Dungeon Escape");
	private JButton ButtonExit = new JButton("Exit");
	private JButton ButtonUp = new JButton("Up");
	private JButton ButtonLeft = new JButton("Left");
	private JButton ButtonRight = new JButton("Right");
	private JButton ButtonDown = new JButton("Down");
	private JButton ButtonSave = new JButton("Save");
	private JLabel GameStatus = new JLabel("Game Status");
	protected JPanel Console;
	private Game g;

public void keys(){
	//keyboard keys
			Console.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent e){
					if (e.getKeyCode() == KeyEvent.VK_UP)
					{			
						int m = getGame().movement("w");
						verify(m);
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN)
					{
						int m = getGame().movement("s");
						verify(m);
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT)
					{
						int m = getGame().movement("a");
						verify(m);
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					{
						int m = getGame().movement("d");
						verify(m);
					}
				}

				@Override
				public void keyReleased(KeyEvent arg0) {}
				@Override
				public void keyTyped(KeyEvent arg0) {}
			});
}
	

public void buttonUp(){
	ButtonUp.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	ButtonUp.setBounds(662, 164, 80, 20);
	ButtonUp.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int m = g.movement("w");
			verify(m);
		}
	});
}

public void buttonLeft(){
	ButtonLeft.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	ButtonLeft.setBounds(612, 202,80, 20);
	ButtonLeft.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int m = g.movement("a");
			verify(m);
		}
	});
}

public void buttonDown(){
	ButtonDown.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	ButtonDown.setBounds(662, 239, 80, 20);
	ButtonDown.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int m = g.movement("s");
			verify(m);
		}
	});
}

public void buttonRight(){
	ButtonRight.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	ButtonRight.setBounds(712, 202, 80, 20);
	ButtonRight.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			int m = g.movement("d");
			verify(m);
		}
	});

}

public void buttonExit(){
	ButtonExit.setFont(new Font("Tahoma", Font.PLAIN, 14));

	ButtonExit.setBounds(662, 670, 90, 25);
	ButtonExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			GameWindow.setVisible(false);
			MainMenu m = new MainMenu();
			m.getMainMenu().setVisible(true);
			GameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	});
}

public void buttonSave(){
	ButtonSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
	ButtonSave.setBounds(662, 443, 90, 25);
	ButtonSave.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			try {
				FileOutputStream fileOut = new FileOutputStream("src/save.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(g);
				out.close();
				fileOut.close();
				//System.out.printf("Serialized data is saved in /src/save.txt");
			}catch(IOException i) {
				i.printStackTrace();
			}
		}
	});
}

public void GameWindowConf(){
	GameWindow.setResizable(false);
	GameWindow.setBounds(600, 50, 810, 810);
	GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	GameWindow.getContentPane().setLayout(null);
}
	
	public void initialise() {

		GameWindowConf();		
		Console = new GraphicsGui(g);
		Console.setBackground(Color.LIGHT_GRAY);
		Console.setBounds(0, 0, 600, 600);
		
		keys();
		GameStatus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GameStatus.setBounds(12, 676, 291, 19);
		GameStatus.setText("You can play the game");				
		buttonUp();
		buttonLeft();
		buttonDown();
		buttonRight();
		buttonExit();
		
		addContent();
	}
	
	public void addContent(){
		GameWindow.getContentPane().add(ButtonUp);
		GameWindow.getContentPane().add(ButtonLeft);
		GameWindow.getContentPane().add(ButtonRight);
		GameWindow.getContentPane().add(ButtonDown);
		GameWindow.getContentPane().add(ButtonExit);	
		GameWindow.getContentPane().add(Console);
		GameWindow.getContentPane().add(GameStatus);
		GameWindow.getContentPane().add(ButtonSave);
	}

	public void enableMovementButtons(boolean enable){
		ButtonUp.setEnabled(enable);
		ButtonDown.setEnabled(enable);
		ButtonLeft.setEnabled(enable); 
		ButtonRight.setEnabled(enable);	
	}


	public void verify(int valid){
		if (valid == 1) //facing a wall
			GameStatus.setText("You're facing a wall!");
		else if (valid == 2) //loses
		{
			GameStatus.setText("You lost!");
			enableMovementButtons(false);		
			Console.setEnabled(false);
		}
		else if(valid==0) //wins
		{
			GameStatus.setText("You Won!");
			enableMovementButtons(false);
			Console.setEnabled(false);
		}
		else
			GameStatus.setText("Be Careful!");
	}
	
	public StartGame(Game g) {
		this.setGame(g);
		initialise();
	}
	
	public StartGame() {
		// TODO Auto-generated constructor stub
	}

	public JFrame getGameWindow() {return GameWindow;}
	public void setGameWindow(JFrame gameWindow) {GameWindow = gameWindow; }
	public Game getGame() {return g;}
	public void setGame(Game g) {this.g = g;}
}
