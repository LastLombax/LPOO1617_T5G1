package dkeep.gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;


public class LevelEditorSettings {

	private JFrame Editor = new JFrame("Level Editor Settings");
	private JButton ButtonContinue = new JButton("Continue");
	private JButton ButtonBack = new JButton("Back");
	private JComboBox Width = new JComboBox();
	private JLabel lblWidth = new JLabel("Width");
	private JLabel lblHeight = new JLabel("Height");
	private JLabel lblOgres = new JLabel("Ogres");
	private JComboBox Height = new JComboBox();		
	private JTextField fldOgres = new JTextField();

	
	public void initialise(){

		Editor.setBounds(550, 250, 399, 295);
		Editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Editor.getContentPane().setLayout(null);	


		Width.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6", "7", "8", "9", "10"}));
		Width.setBounds(189, 13, 64, 29);
		
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 16));


		lblWidth.setBounds(112, 16, 55, 23);
		
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 16));


		lblHeight.setBounds(112, 63, 55, 17);
		


		Height.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6", "7", "8", "9", "10"}));
		Height.setBounds(189, 58, 64, 29);
		




		ButtonBack.setBounds(32, 177, 114, 29);
		ButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editor.setVisible(false);
				MainMenu m = new MainMenu();
				m.getMainMenu().setVisible(true);
				getSettings().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		

		
		ButtonContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fldOgres.getText().charAt(0) < 49 || fldOgres.getText().charAt(0) > 53)
					return;
				
				Editor.setVisible(false);
				LevelEditor l = new LevelEditor(Width.getSelectedIndex()+4, Height.getSelectedIndex()+4, Integer.parseInt(fldOgres.getText()));
				l.getEditor().setVisible(true);
				getSettings().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
			}
		});
		ButtonContinue.setBounds(240, 177, 114, 29);
		

		
		lblOgres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOgres.setBounds(112, 105, 55, 23);
		

		fldOgres.setBounds(189, 106, 64, 22);
		
		fldOgres.setColumns(10);

		addContent();
	}
	
	public void addContent(){
		Editor.getContentPane().add(Width);
		Editor.getContentPane().add(lblWidth);
		Editor.getContentPane().add(lblHeight);
		Editor.getContentPane().add(Height);
		Editor.getContentPane().add(ButtonBack);
		Editor.getContentPane().add(ButtonContinue);
		Editor.getContentPane().add(lblOgres);
		Editor.getContentPane().add(fldOgres);
	}


	/**
	 * @wbp.parser.entryPoint
	 */
	public LevelEditorSettings(){initialise();}

	public JFrame getSettings() {return Editor;}
}
