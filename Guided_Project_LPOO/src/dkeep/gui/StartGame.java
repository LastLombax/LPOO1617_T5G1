package dkeep.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dkeep.logic.Game;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;

import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dkeep.logic.Game;
import java.awt.Color;

public class StartGame {

	private JFrame GameWindow = new JFrame("Dungeon Escape");
	private JButton ButtonExit = new JButton("Exit");
	private JButton ButtonUp = new JButton("Up");
	private JButton ButtonLeft = new JButton("Left");
	private JButton ButtonRight = new JButton("Right");
	private JButton ButtonDown = new JButton("Down");
	private JButton ButtonSave = new JButton("Save");
	private JLabel GameStatus = new JLabel("Game Status");
	private JPanel Console;
	private Game g;

	public void initialise() {

		GameWindow.setResizable(false);
		GameWindow.setBounds(600, 300, 620, 500);
		GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameWindow.getContentPane().setLayout(null);
		
		
		Console = new GraphicsGui(g);
		Console.setBackground(Color.LIGHT_GRAY);
		Console.setBounds(0, 0, 400, 400);
		
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

		
		GameStatus.setBounds(0, 425, 184, 16);
		GameStatus.setText("You can play the game");				
				
		ButtonUp.setBounds(470, 125, 80, 20);
		ButtonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = g.movement("w");
				verify(m);
			}
		});
		
		ButtonLeft.setBounds(420, 163,80, 20);
		ButtonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = g.movement("a");
				verify(m);
			}
		});
		
		ButtonRight.setBounds(520, 163, 80, 20);
		ButtonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int m = g.movement("d");
				verify(m);
			}
		});
		
		ButtonDown.setBounds(470, 200, 80, 20);
		ButtonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = g.movement("s");
				verify(m);
			}
		});

		ButtonExit.setBounds(470, 420, 80, 20);
		ButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindow.setVisible(false);
				MainMenu m = new MainMenu();
				m.getMainMenu().setVisible(true);
				GameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		ButtonSave.setBounds(470, 297, 90, 25);
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
	
	public JFrame getGameWindow() {return GameWindow;}
	public void setGameWindow(JFrame gameWindow) {GameWindow = gameWindow; }
	public Game getGame() {return g;}
	public void setGame(Game g) {this.g = g;}
}
