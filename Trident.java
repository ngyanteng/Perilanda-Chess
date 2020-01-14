import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
//Inherited from pieces.
public class Trident extends Pieces
{
    public Trident(int placement, String color)
    {
        super(placement,color);
    }

    
    public ImageIcon imageGreen()
    {
        ImageIcon TridentGreen = new ImageIcon("TridentGreen.png");
        return TridentGreen;   
    }
    
        public ImageIcon imageOrange()
    {
        ImageIcon TridentOrange = new ImageIcon("TridentOrange.png");
        return TridentOrange;   
    }
}