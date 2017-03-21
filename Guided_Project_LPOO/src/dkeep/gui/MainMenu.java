package dkeep.gui;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class MainMenu {
  
	private JFrame MainMenu = new JFrame("Main Menu");
	private JButton ButtonNewGame = new JButton("New Game");
	private JButton ButtonOptions = new JButton("Options");
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

	private void initialize() {	
		getMainMenu().setResizable(false);
		getMainMenu().setBounds(600, 300, 400, 500);
		getMainMenu().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainMenu().getContentPane().setLayout(null);				
		
		ButtonNewGame.setBounds(140, 75, 115, 40);		
		ButtonNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getMainMenu().setVisible(false);
				NewGame ng = new NewGame();				
				ng.getGameWindowOptions().setVisible(true);					
				getMainMenu().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});		
		
		ButtonOptions.setBounds(140, 150, 115, 40);
		ButtonOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	
		ButtonLoadGame.setBounds(140, 225, 115, 40);
		ButtonLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		
		ButtonExitGame.setBounds(140, 300, 115, 40);
		ButtonExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		getMainMenu().getContentPane().add(ButtonNewGame);
		getMainMenu().getContentPane().add(ButtonOptions);
		getMainMenu().getContentPane().add(ButtonLoadGame);
		getMainMenu().getContentPane().add(ButtonExitGame);
	}
	
	public MainMenu() {initialize();}
	public JFrame getMainMenu() {return MainMenu;}
	public void setMainMenu(JFrame mainMenu) {MainMenu = mainMenu;}
	
}
