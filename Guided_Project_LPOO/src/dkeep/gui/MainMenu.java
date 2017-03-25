package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.*;
import dkeep.gui.*;
import dkeep.logic.Game;


public class MainMenu {

	private JFrame MainMenu = new JFrame("Main Menu");
	private JButton ButtonNewGame = new JButton("New Game");
	private JButton ButtonLevelEditor = new JButton("Level Editor");
	private JButton ButtonLoadGame = new JButton("Load Game");
	private JButton ButtonExitGame = new JButton("Exit");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.getMainMenu().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialise() {	
		MainMenu.setResizable(false);
		MainMenu.setBounds(600, 300, 400, 500);
		MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenu.getContentPane().setLayout(null);				

		ButtonNewGame.setBounds(140, 75, 115, 40);		
		ButtonNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu.setVisible(false);
				NewGameSettings ng = new NewGameSettings();				
				ng.getGameWindowOptions().setVisible(true);					
				MainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});		

		ButtonLevelEditor.setBounds(140, 225, 115, 40);
		ButtonLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMainMenu().setVisible(false);
				LevelEditorSettings lv = new LevelEditorSettings();
				lv.getSettings().setVisible(true);
				getMainMenu().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);				
			}
		});

		ButtonLoadGame.setBounds(140, 150, 115, 40);
		ButtonLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game g = null;
				if (!readFile(g))
					return;
				StartGame sg = new StartGame(g);	
				sg.getGameWindow().setVisible(true);
			}			
		});	

		ButtonExitGame.setBounds(140, 300, 115, 40);
		ButtonExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		addContent();

	}
	
	public boolean readFile(Game g){
		try {
			FileInputStream fileIn = new FileInputStream("src/save.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			g = (Game) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			i.printStackTrace();
			return false;
		}catch(ClassNotFoundException c) {
			System.out.println("Game class not found");
			c.printStackTrace();
			return false;
		}
		return true;
	}

	public void addContent(){
		MainMenu.getContentPane().add(ButtonNewGame);
		MainMenu.getContentPane().add(ButtonLevelEditor);
		MainMenu.getContentPane().add(ButtonLoadGame);
		MainMenu.getContentPane().add(ButtonExitGame);
	}


	public MainMenu() {initialise();}
	public JFrame getMainMenu() {return MainMenu;}
	public void setMainMenu(JFrame mainMenu) {MainMenu = mainMenu;}

}
