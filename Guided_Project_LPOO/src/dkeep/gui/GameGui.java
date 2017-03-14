package dkeep.gui;
import dkeep.logic.*;
import dkeep.logic.Game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameGui {

	private JFrame GameWindow = new JFrame("Dungeon Escape");
	private JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
	private JTextField fldOgres = new JTextField();
	private JLabel lblGuardBehaviour = new JLabel("Guard's Personality");
	private JTextArea Console = new JTextArea();
	private JComboBox fldBehaviour = new JComboBox();
	private JButton ButtonNewGame = new JButton("New Game");
	private JButton ButtonExit = new JButton("Exit");
	private JButton ButtonUp = new JButton("Up");
	private JButton ButtonLeft = new JButton("Left");
	private JButton ButtonRight = new JButton("Right");
	private JButton ButtonDown = new JButton("Down");
	private JLabel GameStatus = new JLabel("Game Status");
	private Game g;

	/**
	 * Launch the application.
	 */ 

	public void updateGame(){
		String s = new String();
		g.setFullMap();
		char[][] m = g.getFullMap();
		for (int i = 0; i < g.getFullMap().length; i++){
			for (int j = 0; j <  g.getFullMap()[i].length; j++){
				if (g.getFullMap()[i][j] == '\0')
					s+= "  ";
				else
					s+= "" +  g.getFullMap()[i][j] + " ";		
			}
			s+= "\n";
		}	
		Console.setText(s);
	}

	public void verify(int valid){

		if (valid == 1) //facing a wall
			GameStatus.setText("You're facing a wall!");
		else if (valid == 2) //loses
		{
			GameStatus.setText("You lost!");
			enableMovementButtons(false);
		}
		if(valid==0)
		{
			GameStatus.setText("You Won!");
			enableMovementButtons(false);
		}

	}
	
	public void enableMovementButtons(boolean enable){
		ButtonUp.setEnabled(enable);
		ButtonDown.setEnabled(enable);
		ButtonLeft.setEnabled(enable);
		ButtonRight.setEnabled(enable);
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGui window = new GameGui();
					window.GameWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public GameGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		GameWindow.setResizable(false);
		GameWindow.setBounds(600, 300, 430, 400);
		GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameWindow.getContentPane().setLayout(null);


		lblNumberOfOgres.setBounds(7, 10, 98, 16);	
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.LEFT);
		GameWindow.getContentPane().add(lblNumberOfOgres);


		fldOgres.setBounds(130, 7, 61, 22);
		fldOgres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fldOgres.setBackground(Color.WHITE);
		GameWindow.getContentPane().add(fldOgres);
		fldOgres.setColumns(5);


		lblGuardBehaviour.setBounds(7, 39, 116, 16);
		GameWindow.getContentPane().add(lblGuardBehaviour);


		fldBehaviour.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunk", "Suspicious"}));
		fldBehaviour.setEditable(true);
		fldBehaviour.setBounds(130, 38, 89, 22);
		GameWindow.getContentPane().add(fldBehaviour);


		Console.setFont(new Font("Courier New", Font.PLAIN, 20));
		Console.setBounds(7, 68, 265, 243);
		GameWindow.getContentPane().add(Console);


		ButtonNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/*	GameStatus.setText(fldOgres.getText());
				if ( fldOgres.getText() != "1"  || fldOgres.getText() != "2" || fldOgres.getText() != "3" || fldOgres.getText() != "4" || fldOgres.getText() != "5")
				{
					GameStatus.setText("Try again");
					return;
				}*/
				
				g = new Game(Integer.parseInt(fldOgres.getText()));
				
				Guard G = g.getGuard();
				g.setGuard( new Guard(G.getCoordenateI(),G.getCoordenateJ(),G.getSprite(),fldBehaviour.getSelectedIndex()));
				
				updateGame();
				enableMovementButtons(true);
				GameStatus.setText("You can play the game");
			}
		});
		ButtonNewGame.setBounds(303, 74, 97, 25);
		GameWindow.getContentPane().add(ButtonNewGame);


		ButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ButtonExit.setBounds(303, 286, 97, 25);
		GameWindow.getContentPane().add(ButtonExit);



		ButtonUp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ButtonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = g.movement("w");
				updateGame();
				verify(m);
			}
		});
		ButtonUp.setBounds(315, 143, 61, 25);
		GameWindow.getContentPane().add(ButtonUp);



		ButtonLeft.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ButtonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = g.movement("a");
				updateGame();
				verify(m);
			}
		});
		ButtonLeft.setBounds(284, 181, 61, 25);
		GameWindow.getContentPane().add(ButtonLeft);


		ButtonRight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ButtonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int m = g.movement("d");
				updateGame();
				verify(m);
			}
		});
		ButtonRight.setBounds(356, 181, 61, 25);
		GameWindow.getContentPane().add(ButtonRight);


		ButtonDown.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ButtonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = g.movement("s");
				updateGame();
				verify(m);
			}
		});
		ButtonDown.setBounds(315, 219, 62, 25);
		GameWindow.getContentPane().add(ButtonDown);


		GameStatus.setBounds(7, 337, 184, 16);
		GameWindow.getContentPane().add(GameStatus);
	}
}
