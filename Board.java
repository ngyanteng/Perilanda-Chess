import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;


public class Board extends JFrame
{   
    public boolean playerTurn;//Value false means its green's turn,while value true means its orange's turn.
    public int prevPlacement = 0;//Determines the old position of chosen piece.
    //private Container contents;
    public boolean pieceClick = false;//Checks if the player has chosen a piece.
    public JButton[] tiles = new JButton[49];//Makes the tiles.
    public int reset = 0;//Checks if game is reset.
	public int greenTurns = 0;
	public int orangeTurns = 0;
    public boolean[] occupied = new boolean[49];//Checks if the specified tile is occupied.
	JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    
    JFrame frame = new JFrame();
	JPanel menu = new JPanel();//Menu GUI.
	JPanel contents = new JPanel();//Tile GUI.
    
    private Color white = Color.WHITE; //Make white tiles.
    
    
    Pieces[] piece = new Pieces[49]; //Call pieces class.
    
    
    public Board()
    {
        super("Perilanda Chess Program");
        
        //contents = getContentPane();
        contents.setLayout(new GridLayout (7,7));
		
		menu.setLayout(new BorderLayout());
		menu.add(save, BorderLayout.NORTH);
        menu.add(load, BorderLayout.CENTER);
		
		save.addActionListener(saveGame);
		load.addActionListener(loadGame);
        
        ButtonHandler buttonHandler = new ButtonHandler();
        
        //Making the tiles.
        for (int i = 0; i < tiles.length; i++)
        {
            tiles[i] = new JButton();
            tiles[i].setBackground(white);
            tiles[i].setBorder(BorderFactory.createLineBorder(Color.black));//Makes the black borders.
            contents.add(tiles[i]);
            tiles[i].addActionListener(buttonHandler);            
        }
        
        callPieces();
		
		//Combining the two GUI.
		this.setLayout(new BorderLayout());
        this.add(menu,BorderLayout.NORTH);
        this.add(contents,BorderLayout.CENTER);
            
        //Window settings.
        setSize(800,800);
        setResizable(true);
        setLocationRelativeTo(null); // centers window
        setVisible(true);
    }
	
	public Board(String chosenLoad)
	{
		super("Perilanda Chess Program");
        
        contents.setLayout(new GridLayout (7,7));
		
		menu.setLayout(new BorderLayout());
		menu.add(save, BorderLayout.NORTH);
        menu.add(load, BorderLayout.CENTER);
		
		save.addActionListener(saveGame);
		load.addActionListener(loadGame);
        
        ButtonHandler buttonHandler = new ButtonHandler();
		
		  //Making the tiles.
        for (int i = 0; i < tiles.length; i++)
        {
            tiles[i] = new JButton();
            tiles[i].setBackground(white);
            tiles[i].setBorder(BorderFactory.createLineBorder(Color.black));//Makes the black borders.
            contents.add(tiles[i]);
            tiles[i].addActionListener(buttonHandler);            
        }
		
		String[] str = chosenLoad.split("\\s+");
		int[] numbers = new int[str.length];
		
		for(int i = 0;i < str.length ; i++)
		{
			numbers[i] = Integer.parseInt(str[i]);
		}
		
		if(numbers[0] == 0)
		{
			playerTurn = false;
		}
		else
		{
			playerTurn = true;
		}

		for(int i = 1; i < numbers.length-2 ; i = i + 3)
		{
			if(numbers[i+2] == 0)
			{
				if(numbers[i] == 0)
				{
					piece[numbers[i+1]] = new Advancer(numbers[i+1],"green");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageGreen());
					occupied[numbers[i+1]] = true;
					
				}
				if(numbers[i] == 1)
				{
					piece[numbers[i+1]] = new Tercel(numbers[i+1],"green");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageGreen());
					occupied[numbers[i+1]] = true;
				}
				if(numbers[i] == 2)
				{
					piece[numbers[i+1]] = new Excel(numbers[i+1],"green");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageGreen());
					occupied[numbers[i+1]] = true;
				}
				if(numbers[i] == 3)
				{
					piece[numbers[i+1]] = new Trident(numbers[i+1],"green");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageGreen());
					occupied[numbers[i+1]] = true;
				}
				if(numbers[i] == 4)
				{
					piece[numbers[i+1]] = new Chief(numbers[i+1],"green");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageGreen());
					occupied[numbers[i+1]] = true;
				}
			}
			else if(numbers[i+2] == 1)
			{
				if(numbers[i] == 0)
				{
					piece[numbers[i+1]] = new Advancer(numbers[i+1],"orange");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageOrange());
					occupied[numbers[i+1]] = true;
				}
				if(numbers[i] == 1)
				{
					piece[numbers[i+1]] = new Tercel(numbers[i+1],"orange");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageOrange());
					occupied[numbers[i+1]] = true;
				}
				if(numbers[i] == 2)
				{
					piece[numbers[i+1]] = new Excel(numbers[i+1],"orange");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageOrange());
					occupied[numbers[i+1]] = true;
				}
				if(numbers[i] == 3)
				{
					piece[numbers[i+1]] = new Trident(numbers[i+1],"orange");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageOrange());
					occupied[numbers[i+1]] = true;
				}
				if(numbers[i] == 4)
				{
					piece[numbers[i+1]] = new Chief(numbers[i+1],"orange");
					tiles[numbers[i+1]].setIcon(piece[numbers[i+1]].imageOrange());
					occupied[numbers[i+1]] = true;
				}
			}
		}	

		//Combining the two GUI.
		this.setLayout(new BorderLayout());
        this.add(menu,BorderLayout.NORTH);
        this.add(contents,BorderLayout.CENTER);
            
        //Window settings.
        setSize(800,800);
        setResizable(true);
        setLocationRelativeTo(null); // centers window
        setVisible(true);
	}
    
    //Calling the pieces in their default position.(Adam)
    public void callPieces()
    {
        for(int i = 35; i < 42; i++)
        {
            piece[i] = new Advancer(i,"green");
        }
            for(int i = 7; i < 14; i++)
        {
            piece[i] = new Advancer(i,"orange");
        }
        piece[0] = new Tercel(0, "orange");
        piece[6] = new Tercel(6, "orange");
        piece[42] = new Tercel(42, "green");
        piece[48] = new Tercel(48, "green");
        piece[1] = new Excel(1, "orange");
        piece[5] = new Excel(5, "orange");
        piece[43] = new Excel(43, "green");
        piece[47] = new Excel(47, "green"); 
        piece[2] = new Trident(2, "orange"); 
        piece[4] = new Trident(4, "orange");
        piece[44] = new Trident(44, "green"); 
        piece[46] = new Trident(46, "green");
        piece[3] = new Chief(3, "orange"); 
        piece[45] = new Chief(45, "green");
        
        //Putting the chess pieces in their original postion in the board.
        for (int i = 0; i < 14; i++)
        {
            tiles[i].setIcon(piece[i].imageOrange());
            occupied[i] = true;
        }
        for (int i = 35; i < 49; i++)
        {            
            tiles[i].setIcon(piece[i].imageGreen());
            occupied[i] = true;
        }
    }
    
    //Movement methods.(Adam)
    public void advancerMovement(int i)
    {
        if (playerTurn == false)
        {
            if (i == prevPlacement - 7 || i == prevPlacement - 14)
            {
                if(i == prevPlacement - 14)//Checks if this pice is skipping over other pieces.
                {
                    if (occupied[prevPlacement - 7] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                tiles[prevPlacement].setIcon(null);//Removes icon on previous tile.
                tiles[i].setIcon(piece[prevPlacement].imageGreen());
                occupied[prevPlacement] = false;//Changes the occupied state.
                occupied[i] = true;
                winCondition(i);//Checks if player has won.
                if (reset == 0)//If the game has not ended, continue with next turn.
                {
                    piece[i] = piece[prevPlacement];//Copies the value into another piece and removes the old one.
                    piece[prevPlacement] = null;
					greenTurns = greenTurns + 1;
					if (greenTurns == 3)
					{
						transformPiece();
					}
                    playerTurn = !playerTurn;
                    return;
                }
            }
            else
            {
                //The player has chosen invalid move and so his turn resets.
                JOptionPane.showMessageDialog(frame, "Invalid move from Green.");
            }     
        }  
        else if (playerTurn == true)
        {
            if (i == prevPlacement + 7 || i == prevPlacement + 14)
            {
                if(i == prevPlacement + 14)//Checks if this pice is skipping over other pieces.
                {
                    if (occupied[prevPlacement + 7] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                tiles[prevPlacement].setIcon(null);//Removes icon on previous tile.
                tiles[i].setIcon(piece[prevPlacement].imageOrange());
                occupied[prevPlacement] = false;//Changes the occupied state.
                occupied[i] = true;
                winCondition(i);//Checks if player has won.
                if (reset == 0)//If the game has not ended, continue with next turn.
                {
                    piece[i] = piece[prevPlacement];//Copies the value into another piece and removes the old one.
                    piece[prevPlacement] = null;
					orangeTurns = orangeTurns + 1;
					if (orangeTurns == 3)
					{
						transformPiece();
					}
                    playerTurn = !playerTurn;
                    return;
                }
            }
            else
            {
                //The player has chosen invalid move and so his turn resets.
                JOptionPane.showMessageDialog(frame, "Invalid move from Orange.");
            }     
        } 
    }
    
    public void tercelMovement(int i)
    {
        if (i == prevPlacement + 1 || i == prevPlacement -1 || i == prevPlacement + 2 || i == prevPlacement - 2 || i == prevPlacement + 3 || i == prevPlacement -3 || i == prevPlacement + 4 || i == prevPlacement - 4 ||i == prevPlacement + 5 || i == prevPlacement - 5 || i == prevPlacement + 6 || i == prevPlacement - 6 ||i == prevPlacement + 7 || i == prevPlacement - 7 || i == prevPlacement + 14 || i == prevPlacement - 14 ||i == prevPlacement + 21 || i == prevPlacement - 21 || i == prevPlacement + 28 || i == prevPlacement - 28 ||i == prevPlacement + 35 || i == prevPlacement - 35 || i == prevPlacement + 42 || i == prevPlacement - 42 )
        {
            if (i == prevPlacement + 2)//Checks if this pice is skipping over other pieces.
            {
                if (occupied[prevPlacement + 1] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 3)
            {
                if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 4)
            {
                if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 5)
            {
                if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true || occupied[prevPlacement + 4] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 6)
            {
                if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true || occupied[prevPlacement + 4] == true || occupied[prevPlacement + 5] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 2)//Checks if this pice is skipping over other pieces.
            {
                if (occupied[prevPlacement - 1] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 3)
            {
                if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 4)
            {
                if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 5)
            {
                if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true || occupied[prevPlacement - 4] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 6)
            {
                if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true || occupied[prevPlacement - 4] == true || occupied[prevPlacement - 5] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
	    else if (i == prevPlacement + 14)
            {
                if (occupied[prevPlacement + 7] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 21)
            {
                if (occupied[prevPlacement + 7] == true || occupied[prevPlacement + 14] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 28)
            {
                if (occupied[prevPlacement + 7] == true || occupied[prevPlacement + 14] == true || occupied[prevPlacement + 21] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 35)
            {
                if (occupied[prevPlacement + 7] == true || occupied[prevPlacement + 14] == true || occupied[prevPlacement + 21] == true || occupied[prevPlacement + 28] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 42)
            {
                if (occupied[prevPlacement + 7] == true || occupied[prevPlacement + 14] == true || occupied[prevPlacement + 21] == true || occupied[prevPlacement + 28] == true || occupied[prevPlacement + 35] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 49)
            {
                if (occupied[prevPlacement + 7] == true || occupied[prevPlacement + 14] == true || occupied[prevPlacement + 21] == true || occupied[prevPlacement + 28] == true || occupied[prevPlacement + 35] == true || occupied[prevPlacement + 49] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
	    else if (i == prevPlacement - 14)
            {
                if (occupied[prevPlacement - 7] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 21)
            {
                if (occupied[prevPlacement - 7] == true || occupied[prevPlacement - 14] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 28)
            {
                if (occupied[prevPlacement - 7] == true || occupied[prevPlacement - 14] == true || occupied[prevPlacement - 21] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 35)
            {
                if (occupied[prevPlacement - 7] == true || occupied[prevPlacement - 14] == true || occupied[prevPlacement - 21] == true || occupied[prevPlacement - 28] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 42)
            {
                if (occupied[prevPlacement - 7] == true || occupied[prevPlacement - 14] == true || occupied[prevPlacement - 21] == true || occupied[prevPlacement - 28] == true || occupied[prevPlacement - 35] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 49)
            {
                if (occupied[prevPlacement - 7] == true || occupied[prevPlacement - 14] == true || occupied[prevPlacement - 21] == true || occupied[prevPlacement - 28] == true || occupied[prevPlacement - 35] == true || occupied[prevPlacement - 49] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            tiles[prevPlacement].setIcon(null);//Removes icon on previous tile.
            if (playerTurn == false)
            {
                tiles[i].setIcon(piece[prevPlacement].imageGreen());
            }
            else
            {
                tiles[i].setIcon(piece[prevPlacement].imageOrange());
            }
            occupied[prevPlacement] = false;//Changes the occupied state.
            occupied[i] = true;
            winCondition(i);//Checks if player has won.
            if (reset == 0)//If the game has not ended, continue with next turn.
            {
                piece[i] = piece[prevPlacement];//Copies the value into another piece and removes the old one.
                piece[prevPlacement] = null;
				if (playerTurn == false)
				{
					greenTurns = greenTurns + 1;
					if (greenTurns == 3)
					{
						transformPiece();
					}
				}
				else
				{
					orangeTurns = orangeTurns + 1;
					if (orangeTurns == 3)
					{
						transformPiece();
					}
				}
                playerTurn = !playerTurn;
            }
        }
        else
        {
            //The player has chosen invalid move and so his turn resets.
            if (playerTurn == false)
            {
                JOptionPane.showMessageDialog(frame, "Invalid move from Green.");
            }
            else 
            {
                JOptionPane.showMessageDialog(frame, "Invalid move from Orange.");
            }  
        }     
    }
    
    public void excelMovement(int i)
    {
        if (i == prevPlacement + 6 || i == prevPlacement - 6 || i == prevPlacement + 8 || i == prevPlacement - 8 || i == prevPlacement + 12 || i == prevPlacement - 12 || i == prevPlacement + 16 || i == prevPlacement - 16 || i == prevPlacement + 18 || i == prevPlacement - 18 || i == prevPlacement + 24 || i == prevPlacement - 24 || i == prevPlacement + 30 || i == prevPlacement - 30 || i == prevPlacement + 32 || i == prevPlacement - 32 || i == prevPlacement + 36 || i == prevPlacement - 36 || i == prevPlacement + 40 || i == prevPlacement - 40 || i == prevPlacement + 48 || i == prevPlacement - 48)
        {   
            if (i == prevPlacement + 12)//Checks if this pice is skipping over other pieces.
            {
                if (occupied[prevPlacement + 6] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 18)
            {
                if (occupied[prevPlacement + 6] == true || occupied[prevPlacement + 12] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 24)
            {
                if (occupied[prevPlacement + 6] == true || occupied[prevPlacement + 12] == true || occupied[prevPlacement + 18] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 30)
            {
                if (occupied[prevPlacement + 6] == true || occupied[prevPlacement + 12] == true || occupied[prevPlacement + 18] == true || occupied[prevPlacement + 24] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 36)
            {
                if (occupied[prevPlacement + 6] == true || occupied[prevPlacement + 12] == true || occupied[prevPlacement + 18] == true || occupied[prevPlacement + 24] == true || occupied[prevPlacement + 30] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 12)//Checks if this pice is skipping over other pieces.
            {
                if (occupied[prevPlacement - 6] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 18)
            {
                if (occupied[prevPlacement - 6] == true || occupied[prevPlacement - 12] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 24)
            {
                if (occupied[prevPlacement - 6] == true || occupied[prevPlacement - 12] == true || occupied[prevPlacement - 18] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 30)
            {
                if (occupied[prevPlacement - 6] == true || occupied[prevPlacement - 12] == true || occupied[prevPlacement - 18] == true || occupied[prevPlacement - 24] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 36)
            {
                if (occupied[prevPlacement - 6] == true || occupied[prevPlacement - 12] == true || occupied[prevPlacement - 18] == true || occupied[prevPlacement - 24] == true || occupied[prevPlacement - 30] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
	    else if (i == prevPlacement + 16)
            {
                if (occupied[prevPlacement + 8] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 24)
            {
                if (occupied[prevPlacement + 8] == true || occupied[prevPlacement + 16] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 32)
            {
                if (occupied[prevPlacement + 8] == true || occupied[prevPlacement + 16] == true || occupied[prevPlacement + 24] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 40)
            {
                if (occupied[prevPlacement + 8] == true || occupied[prevPlacement + 16] == true || occupied[prevPlacement + 24] == true || occupied[prevPlacement + 32] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement + 48)
            {
                if (occupied[prevPlacement + 8] == true || occupied[prevPlacement + 16] == true || occupied[prevPlacement + 24] == true || occupied[prevPlacement + 32] == true || occupied[prevPlacement + 40] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
	    else if (i == prevPlacement - 16)
            {
                if (occupied[prevPlacement - 8] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 24)
            {
                if (occupied[prevPlacement - 8] == true || occupied[prevPlacement - 16] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 32)
            {
                if (occupied[prevPlacement - 8] == true || occupied[prevPlacement - 16] == true || occupied[prevPlacement - 24] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 40)
            {
                if (occupied[prevPlacement - 8] == true || occupied[prevPlacement - 16] == true || occupied[prevPlacement - 24] == true || occupied[prevPlacement - 32] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            else if (i == prevPlacement - 48)
            {
                if (occupied[prevPlacement - 8] == true || occupied[prevPlacement - 16] == true || occupied[prevPlacement - 24] == true || occupied[prevPlacement - 32] == true || occupied[prevPlacement - 40] == true)
                {
                    JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                    return;
                }
            }
            tiles[prevPlacement].setIcon(null);//Removes icon on previous tile.
            if (playerTurn == false)
            {
                tiles[i].setIcon(piece[prevPlacement].imageGreen());
            }
            else
            {
                tiles[i].setIcon(piece[prevPlacement].imageOrange());
            }
            occupied[prevPlacement] = false;//Changes the occupied state.
            occupied[i] = true;
            winCondition(i);//Checks if player has won.
            if (reset == 0)//If the game has not ended, continue with next turn.
            {
                piece[i] = piece[prevPlacement];//Copies the value into another piece and removes the old one.
                piece[prevPlacement] = null;
				if (playerTurn == false)
				{
					greenTurns = greenTurns + 1;
					if (greenTurns == 3)
					{
						transformPiece();
					}
				}
				else
				{
					orangeTurns = orangeTurns + 1;
					if (orangeTurns == 3)
					{
						transformPiece();
					}
				}
                playerTurn = !playerTurn;
            }
        }
        else
        {
            //The player has chosen invalid move and so his turn resets.
            if (playerTurn == false)
            {
                JOptionPane.showMessageDialog(frame, "Invalid move from Green.");
            }
            else 
            {
                JOptionPane.showMessageDialog(frame, "Invalid move from Orange.");
            }  
        }     
    }
    
    public void tridentMovement(int i)
    {
        if (playerTurn == false)
        {
            if (i == prevPlacement + 1 || i == prevPlacement -1 || i == prevPlacement + 2 || i == prevPlacement - 2 || i == prevPlacement + 3 || i == prevPlacement -3 || i == prevPlacement + 4 || i == prevPlacement - 4 ||i == prevPlacement + 5 || i == prevPlacement - 5 || i == prevPlacement + 6 || i == prevPlacement - 6 || i == prevPlacement - 7 )
            {
                if (i == prevPlacement + 2)//Checks if this pice is skipping over other pieces.
                {
                    if (occupied[prevPlacement + 1] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement + 3)
                {
                    if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement + 4)
                {
                    if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement + 5)
                {
                    if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true || occupied[prevPlacement + 4] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement + 6)
                {
                    if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true || occupied[prevPlacement + 4] == true || occupied[prevPlacement + 5] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 2)//Checks if this pice is skipping over other pieces.
                {
                    if (occupied[prevPlacement - 1] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 3)
                {
                    if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 4)
                {
                    if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 5)
                {
                    if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true || occupied[prevPlacement - 4] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 6)
                {
                    if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true || occupied[prevPlacement - 4] == true || occupied[prevPlacement - 5] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                tiles[prevPlacement].setIcon(null);//Removes icon on previous tile.
                tiles[i].setIcon(piece[prevPlacement].imageGreen());
                occupied[prevPlacement] = false;//Changes the occupied state.
                occupied[i] = true;
                winCondition(i);//Checks if player has won.
                if (reset == 0)//If the game has not ended, continue with next turn.
                {
                    piece[i] = piece[prevPlacement];//Copies the value into another piece and removes the old one.
                    piece[prevPlacement] = null;
					greenTurns = greenTurns + 1;
					if (greenTurns == 3)
					{
						transformPiece();
					}
                    playerTurn = !playerTurn;
                    return;
                }
            }
            else
            {
                //The player has chosen invalid move and so his turn resets.
                JOptionPane.showMessageDialog(frame, "Invalid move from Green.");
            }     
        }  
        else if (playerTurn == true)
        {
            if (i == prevPlacement + 1 || i == prevPlacement -1 || i == prevPlacement + 2 || i == prevPlacement - 2 || i == prevPlacement + 3 || i == prevPlacement -3 || i == prevPlacement + 4 || i == prevPlacement - 4 ||i == prevPlacement + 5 || i == prevPlacement - 5 || i == prevPlacement + 6 || i == prevPlacement - 6 || i == prevPlacement + 7 )
            {
                if (i == prevPlacement + 2)//Checks if this pice is skipping over other pieces.
                {
                    if (occupied[prevPlacement + 1] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement + 3)
                {
                    if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement + 4)
                {
                    if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement + 5)
                {
                    if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true || occupied[prevPlacement + 4] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement + 6)
                {
                    if (occupied[prevPlacement + 1] == true || occupied[prevPlacement + 2] == true || occupied[prevPlacement + 3] == true || occupied[prevPlacement + 4] == true || occupied[prevPlacement + 5] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 2)//Checks if this pice is skipping over other pieces.
                {
                    if (occupied[prevPlacement - 1] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 3)
                {
                    if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 4)
                {
                    if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 5)
                {
                    if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true || occupied[prevPlacement - 4] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                else if (i == prevPlacement - 6)
                {
                    if (occupied[prevPlacement - 1] == true || occupied[prevPlacement - 2] == true || occupied[prevPlacement - 3] == true || occupied[prevPlacement - 4] == true || occupied[prevPlacement - 5] == true)
                    {
                        JOptionPane.showMessageDialog(frame, "Cannot skip over another piece.");
                        return;
                    }
                }
                tiles[prevPlacement].setIcon(null);//Removes icon on previous tile.
                tiles[i].setIcon(piece[prevPlacement].imageOrange());
                occupied[prevPlacement] = false;//Changes the occupied state.
                occupied[i] = true;
                winCondition(i);//Checks if player has won.
                if (reset == 0)//If the game has not ended, continue with next turn.
                {
                    piece[i] = piece[prevPlacement];//Copies the value into another piece and removes the old one.
                    piece[prevPlacement] = null;
					orangeTurns = orangeTurns + 1;
					if (orangeTurns == 3)
					{
						transformPiece();
					}
                    playerTurn = !playerTurn;
                    return;
                }
            }
            else
            {
                //The player has chosen invalid move and so his turn resets.
                JOptionPane.showMessageDialog(frame, "Invalid move from Orange.");
            }     
        } 
    }
    
    public void chiefMovement(int i)
    {
        if (i == prevPlacement + 1 || i == prevPlacement - 1 || i == prevPlacement + 7 || i == prevPlacement - 7 || i == prevPlacement + 8 || i == prevPlacement - 8 || i == prevPlacement + 6 || i == prevPlacement - 6)
        {
            tiles[prevPlacement].setIcon(null);//Removes icon on previous tile.
            if (playerTurn == false)
            {
                tiles[i].setIcon(piece[prevPlacement].imageGreen());
            }
            else
            {
                tiles[i].setIcon(piece[prevPlacement].imageOrange());
            }
            occupied[prevPlacement] = false;//Changes the occupied state.
            occupied[i] = true;
            winCondition(i);//Checks if player has won.
            if (reset == 0)//If the game has not ended, continue with next turn.
            {
                piece[i] = piece[prevPlacement];//Copies the value into another piece and removes the old one.
                piece[prevPlacement] = null;

                playerTurn = !playerTurn;
            }
        }
        else
        {
            //The player has chosen invalid move and so his turn resets.
            if (playerTurn == false)
            {
                JOptionPane.showMessageDialog(frame, "Invalid move from Green.");
            }
            else 
            {
                JOptionPane.showMessageDialog(frame, "Invalid move from Orange.");
            }  
        }     
    }
    
    //Winning condition method.(Adam)
    public void winCondition(int i)
    {   
        if (playerTurn == false)
        {
            if (piece[i] == null)
            {
                return;
            }
            else if (piece[i].getPlacement() == 3)
            {
                JOptionPane.showMessageDialog(frame, "Green wins!");
                resetGame();
            }
        }
        else if (playerTurn == true)
        {
            if (piece[i] == null)
            {
                return;
            }
            else if (piece[i].getPlacement() == 45)
            {
                JOptionPane.showMessageDialog(frame, "Orange wins!");
                resetGame();
            }
        }
    }
    
    //Reset the game after match ends.(Adam)
    public void resetGame()
    {
        reset = 1;
        playerTurn = false;
        for (int i = 0; i < 49 ;i++)
        {
            tiles[i].setIcon(null);
            piece[i] = null;
            occupied[i] = false;
        }
        callPieces();
    }
	
	//Transform the piece.(Adam)
	public void transformPiece()
	{
		if (greenTurns ==3)
		{
			for (int i = 0; i < 49 ;i++)
			{
				if (piece[i] != null)
				{
					if (piece[i].getPlacement() == 42 || piece[i].getPlacement() == 48)//For Tercel.
					{
						tiles[i].setIcon(null);
						piece[i] = null;
						piece[i] = new Excel(43,"green");
						tiles[i].setIcon(piece[i].imageGreen());
					}
					else if (piece[i].getPlacement() == 43 || piece[i].getPlacement() == 47)//For Excel.
					{
						tiles[i].setIcon(null);
						piece[i] = null;
						piece[i] = new Tercel(42,"green");
						tiles[i].setIcon(piece[i].imageGreen());
					}
				}
			}
			greenTurns = 0;
		}
		else if (orangeTurns ==3)
		{
			for (int i = 0; i < 49 ;i++)
			{
				if (piece[i] != null)
				{
					if (piece[i].getPlacement() == 0 || piece[i].getPlacement() == 6)//For Tercel.
					{
						tiles[i].setIcon(null);
						piece[i] = null;
						piece[i] = new Excel(1,"orange");
						tiles[i].setIcon(piece[i].imageOrange());
					}
					else if (piece[i].getPlacement() == 1 || piece[i].getPlacement() == 5)//For Excel.
					{
						tiles[i].setIcon(null);
						piece[i] = null;
						piece[i] = new Tercel(0,"orange");
						tiles[i].setIcon(piece[i].imageOrange());
					}
				}
			}
			orangeTurns = 0;
		}
	}
  
    
    //What all the clicking does.
    public class ButtonHandler implements ActionListener
    { 
        public void actionPerformed(ActionEvent e)
        {
            Object source = e.getSource();
            reset = 0;
            for (int i = 0; i < 49; i++)
            {
                if (playerTurn == false)//Green players clicking mechanics.
                {
                    if (source == tiles [i] && pieceClick == false)
                    {
                        if (piece[i] == null)
                        {
                            JOptionPane.showMessageDialog(frame, "Please select a piece first!");//If the player did not select a piece.
							return;
                        }
                        if (piece[i].getColor() == "orange")
                        {
                            JOptionPane.showMessageDialog(frame, "Select a green piece.");//If the player selects another players pieces first.
                        }
                        else
                        {
                            pieceClick = true;
                            prevPlacement = i;
                        }
                        return;
                    }
                    if (source == tiles [i] && pieceClick == true)
                    {
                        if (occupied[i] == true && piece[i].getColor() == "green")
                        {
                            JOptionPane.showMessageDialog(frame, "Location occupied.");//If the player hasn't selected a piece.
                            pieceClick = false;
                        }
                        else
                        {
                            pieceClick = false;
                            //If statements to check what movement the piece uses based on its type..
                            if (piece[prevPlacement].getPlacement() == 35 || piece[prevPlacement].getPlacement() == 36 || piece[prevPlacement].getPlacement() == 37 || piece[prevPlacement].getPlacement() == 38 || piece[prevPlacement].getPlacement() == 39 || piece[prevPlacement].getPlacement() == 40 || piece[prevPlacement].getPlacement() == 41) 
                            {
                                advancerMovement(i);    
                            }
                            else if (piece[prevPlacement].getPlacement() == 42 || piece[prevPlacement].getPlacement() == 48)
                            {
                                tercelMovement(i);
                            }
                            else if (piece[prevPlacement].getPlacement() == 43 || piece[prevPlacement].getPlacement() == 47)
                            {
                                excelMovement(i);
                            }
                            else if (piece[prevPlacement].getPlacement() == 44 || piece[prevPlacement].getPlacement() == 46)
                            {
                                tridentMovement(i);
                            }
                            else if (piece[prevPlacement].getPlacement() == 45)
                            {
                                chiefMovement(i);
                            }
                        }
                        return;
                    }
                }
                else//Orange players clicking mechanics.
                {
                    if (source == tiles [i] && pieceClick == false)
                    {
                        if (piece[i] == null)
                        {
                            JOptionPane.showMessageDialog(frame, "Please select a piece first!");//If the player did not select a piece.
							return;
                        }
                        if (piece[i].getColor() == "green")
                        {
                            JOptionPane.showMessageDialog(frame, "Select an orange piece.");//If the player selects another players pieces first.
                        }
                        else
                        {
                            pieceClick = true;
                            prevPlacement = i;
                        }
                        return;
                    }
                    if (source == tiles [i] && pieceClick == true)
                    {
                        if (occupied[i] == true && piece[i].getColor() == "orange")
                        {
                            JOptionPane.showMessageDialog(frame, "Location occupied.");//If the player hasn't selected a piece.
                            pieceClick = false;
                        }
                        else
                        {
                            pieceClick = false;
                            //If statements to check what type of piece it is based on its original location.
                            if (piece[prevPlacement].getPlacement() == 7 || piece[prevPlacement].getPlacement() == 8 || piece[prevPlacement].getPlacement() == 9 || piece[prevPlacement].getPlacement() == 10 || piece[prevPlacement].getPlacement() == 11 || piece[prevPlacement].getPlacement() == 12 || piece[prevPlacement].getPlacement() == 13)
                            {
                                advancerMovement(i);
                            }
                            else if (piece[prevPlacement].getPlacement() == 0 || piece[prevPlacement].getPlacement() == 6)
                            {
                                tercelMovement(i);
                            }
                            else if (piece[prevPlacement].getPlacement() == 1 || piece[prevPlacement].getPlacement() == 5)
                            {
                                excelMovement(i);
                            }
                            else if (piece[prevPlacement].getPlacement() == 2 || piece[prevPlacement].getPlacement() == 4)
                            {
                                tridentMovement(i);
                            }
                            else if (piece[prevPlacement].getPlacement() == 3)
                            {
                                chiefMovement(i);
                            }
                        }
                        return;
                    }
                }
            }
        }   
    }   
	
	ActionListener saveGame = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			try{
			BufferedWriter writeSave = new BufferedWriter(new FileWriter("save.txt", true));
			
			if (playerTurn == false)
			{
				writeSave.write(0+" ");
			}
			else
			{
				writeSave.write(1+" ");
			}
			
				for (int i = 0; i < 49; i++)
				{
					if (occupied[i] == true && piece[i].getColor() == "green")
					{
						if (piece[i].getPlacement() == 35 || piece[i].getPlacement() == 36 || piece[i].getPlacement() == 37 || piece[i].getPlacement() == 38 || piece[i].getPlacement() == 39 || piece[i].getPlacement() == 40 || piece[i].getPlacement() == 41) 
						{
							//advancer
							writeSave.write(0+" "+i+" "+0+" ");
						}
						else if (piece[i].getPlacement() == 42 || piece[i].getPlacement() == 48)
						{
							//tercel
							writeSave.write(1+" "+i+" "+0+" ");
						}
						else if (piece[i].getPlacement() == 43 || piece[i].getPlacement() == 47)
						{
							//excel
							writeSave.write(2+" "+i+" "+0+" ");
						}
						else if (piece[i].getPlacement() == 44 || piece[i].getPlacement() == 46)
						{
							//trident
							writeSave.write(3+" "+i+" "+0+" ");
						}
						else if (piece[i].getPlacement() == 45)
						{
							//chief
							writeSave.write(4+" "+i+" "+0+" ");
						}
					}  
					else if (occupied[i] == true && piece[i].getColor() == "orange")
					{
						if (piece[i].getPlacement() == 7 || piece[i].getPlacement() == 8 || piece[i].getPlacement() == 9 || piece[i].getPlacement() == 10 || piece[i].getPlacement() == 11 || piece[i].getPlacement() == 12 || piece[i].getPlacement() == 13)
						{
							//advancer
							writeSave.write(0+" "+i+" "+1+" ");
						}
						else if (piece[i].getPlacement() == 0 || piece[i].getPlacement() == 6)
						{
							//tercel
							writeSave.write(1+" "+i+" "+1+" ");
						}
						else if (piece[i].getPlacement() == 1 || piece[i].getPlacement() == 5)
						{
							//excel
							writeSave.write(2+" "+i+" "+1+" ");
						}
						else if (piece[i].getPlacement() == 2 || piece[i].getPlacement() == 4)
						{
							//trident
							writeSave.write(3+" "+i+" "+1+" ");
						}
						else if (piece[i].getPlacement() == 3)
						{
							//chief
							writeSave.write(4+" "+i+" "+1+" ");
						}
					}
				}
				writeSave.newLine();
				writeSave.close();
			}
			catch(IOException f)
			{
				System.out.println(f);
			}
		}
	};
	
	ActionListener loadGame = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Load loadGui = new Load();
		}
		
	};
}

