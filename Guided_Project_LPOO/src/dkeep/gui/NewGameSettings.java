package dkeep.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dkeep.logic.Game;
import dkeep.logic.Guard;

public class NewGameSettings {

	private JFrame GameWindowOptions = new JFrame("Game Settings");
	private JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
	private JTextField fldOgres = new JTextField();
	private JLabel lblGuardBehaviour = new JLabel("Guard's Personality");
	private JComboBox fldBehaviour = new JComboBox();
	private JButton ButtonStart = new JButton("Start Game");
	private JButton ButtonBack = new JButton("Back");
	private JLabel GameStatus = new JLabel(" ");
	protected Game g;
	
	
	public void windowConf(){

		GameWindowOptions.setResizable(false);
		GameWindowOptions.setBounds(600, 300, 325, 250);
		GameWindowOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameWindowOptions.getContentPane().setLayout(null);	
		
		lblNumberOfOgres.setBounds(28, 16, 98, 16);	
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.LEFT);
		
	}
	
	public void fldOgresConf(){
		fldOgres.setBounds(186, 13, 61, 22);
		fldOgres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fldOgres.setBackground(Color.WHITE);
		fldOgres.setColumns(5);
		

		lblGuardBehaviour.setBounds(28, 66, 116, 16);
		

		fldBehaviour.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunk", "Suspicious"}));
		fldBehaviour.setEditable(true);
		fldBehaviour.setBounds(186, 63, 89, 22);
	}
	
	public void buttonStart(){
		ButtonStart.setBounds(186, 149, 112, 33);
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
				GameWindowOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
				
		});
	}
	
	public void initialise(){	

		windowConf();
		fldOgresConf();	
		buttonStart();

		ButtonBack.setBounds(28, 151, 114, 29);
		ButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindowOptions.setVisible(false);
				MainMenu m = new MainMenu();
				m.getMainMenu().setVisible(true);
				GameWindowOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		addContent();
	}
	
	public void addContent(){
		GameWindowOptions.getContentPane().add(ButtonBack);
		GameWindowOptions.getContentPane().add(ButtonStart);
		GameWindowOptions.getContentPane().add(fldBehaviour);
		GameWindowOptions.getContentPane().add(lblGuardBehaviour);
		GameWindowOptions.getContentPane().add(fldOgres);
		GameWindowOptions.getContentPane().add(lblNumberOfOgres);
	}
	
	
	
	public NewGameSettings(){initialise();}
	
	public JFrame getGameWindowOptions() {return GameWindowOptions;}

	public void setGameWindowOptions(JFrame gameWindowOptions) {GameWindowOptions = gameWindowOptions;}

	
}
