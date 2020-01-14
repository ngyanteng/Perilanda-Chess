import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Load
{	
	public Load()
	{
		BufferedReader loadGame;
		ArrayList<String> gameList = new ArrayList<String>();
		
		try 
		{
			FileInputStream fstream = new FileInputStream("save.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			
			while ((line = br.readLine()) != null) 
			{
				gameList.add(line);
			}
			fstream.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		String picklist[] = new String[gameList.size()];
		for (int i = 0; i < gameList.size(); i++)
		{
			picklist[i] = (i + 1)+"";
		}
		
		String input = (String)JOptionPane.showInputDialog(null, "Select a game", "Load Game", JOptionPane.QUESTION_MESSAGE,
        null, picklist, picklist[0]);
		
		if (input != null)
		{
			int gameNumber = Integer.parseInt(input) - 1;
			Board gui = new Board(gameList.get(gameNumber));
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
}