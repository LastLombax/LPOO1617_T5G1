package dkeep.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dkeep.logic.Game;
import dkeep.logic.Guard;

public class NewGame {

	private JFrame GameWindowOptions = new JFrame("Game Settings");
	private JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
	private JTextField fldOgres = new JTextField();
	private JLabel lblGuardBehaviour = new JLabel("Guard's Personality");
	private JComboBox fldBehaviour = new JComboBox();
	private JButton ButtonStart = new JButton("Start Game");
	private JButton ButtonBack = new JButton("Back");
	private JLabel GameStatus = new JLabel(" ");

	protected Game g;


	public NewGame(){
		initializeOptions();
	}

	public void initializeOptions(){	

		getGameWindowOptions().setResizable(false);
		getGameWindowOptions().setBounds(600, 300, 325, 250);
		getGameWindowOptions().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getGameWindowOptions().getContentPane().setLayout(null);	
		
		
		lblNumberOfOgres.setBounds(7, 16, 98, 16);	
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.LEFT);
		getGameWindowOptions().getContentPane().add(lblNumberOfOgres);


		fldOgres.setBounds(165, 13, 61, 22);
		fldOgres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fldOgres.setBackground(Color.WHITE);
		fldOgres.setColumns(5);
		getGameWindowOptions().getContentPane().add(fldOgres);

		lblGuardBehaviour.setBounds(7, 66, 116, 16);
		getGameWindowOptions().getContentPane().add(lblGuardBehaviour);

		fldBehaviour.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunk", "Suspicious"}));
		fldBehaviour.setEditable(true);
		fldBehaviour.setBounds(165, 63, 89, 22);
		getGameWindowOptions().getContentPane().add(fldBehaviour);

		ButtonStart.setBounds(24, 146, 112, 33);
		ButtonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fldOgres.getText().charAt(0) < 49 || fldOgres.getText().charAt(0) > 53){
					GameStatus.setText("Invalid number of Ogres");
					return;
				}

				g = new Game(Integer.parseInt(fldOgres.getText()));
				Guard G = g.getGuard();
				g.setGuard( new Guard(G.getCoordenateI(),G.getCoordenateJ(),G.getSprite(),fldBehaviour.getSelectedIndex()));

				GameWindowOptions.setVisible(false);				
				StartGame sg = new StartGame(g);	
				sg.getGameWindow().setVisible(true);				
				getGameWindowOptions().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
				
		});

		getGameWindowOptions().getContentPane().add(ButtonStart);

		ButtonBack.setBounds(184, 146, 112, 33);
		ButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindowOptions.setVisible(false);
				MainMenu m = new MainMenu();
				m.getMainMenu().setVisible(true);
				getGameWindowOptions().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		getGameWindowOptions().getContentPane().add(ButtonBack);
	}
	
	public JFrame getGameWindowOptions() {return GameWindowOptions;}

	public void setGameWindowOptions(JFrame gameWindowOptions) {GameWindowOptions = gameWindowOptions;}

	
}
