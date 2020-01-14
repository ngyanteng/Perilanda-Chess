import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Menu extends JFrame{
	
	private JFrame viewMenu = new JFrame();
	private JButton newGameBtn = new JButton("New Game");
	private JButton loadGameBtn = new JButton("Load Game");
	private JButton quitBtn = new JButton("Quit");
	private JLabel title = new JLabel("Perilanda Chess Game");
	
	public Menu(){
		newGameBtn.addActionListener(actionListener);
		loadGameBtn.addActionListener(actionListener);
		quitBtn.addActionListener(actionListener);
		mainMenu();
	}
	
	public void mainMenu(){		
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
		  
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		
		newGameBtn.setBackground(Color.WHITE);
		newGameBtn.setSize(300, 300);
		
		loadGameBtn.setBackground(Color.WHITE);
		loadGameBtn.setSize(300, 300);
		
		quitBtn.setBackground(Color.WHITE);
		quitBtn.setSize(300, 300);
		setPreferredSize(getSize());
		
		gbc.gridy = 0;
		panel.add(title, gbc);
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL; 
		panel.add(newGameBtn, gbc);
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL; 
		panel.add(loadGameBtn, gbc);
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL; 
		panel.add(quitBtn, gbc);

		viewMenu.add(panel);
		setResizable(true);
        setLocationRelativeTo(null);
		viewMenu.setSize(800, 800);
		viewMenu.setVisible(true);
	}
	
	void newGame(){
		Board gui = new Board();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	void loadGame(){
		Load loadGui = new Load();
	}
	
	void close(JFrame f){
		WindowEvent winClosingEvent = new WindowEvent(f, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	ActionListener actionListener = new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == newGameBtn){
				close(viewMenu);
				newGame();
			}
			else if(e.getSource() == loadGameBtn){
				loadGame();
			}
			else if(e.getSource() == quitBtn){	
				viewMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				close(viewMenu);
			}
		}
	};
	
	
}