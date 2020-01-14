import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
//Inherited from pieces.
public class Tercel extends Pieces
{
    public Tercel(int placement, String color)
    {
        super(placement,color);
    }

    
    public ImageIcon imageGreen()
    {
        ImageIcon TercelGreen = new ImageIcon("TercelGreen.png");
        return TercelGreen;   
    }
    
        public ImageIcon imageOrange()
    {
        ImageIcon TercelOrange = new ImageIcon("TercelOrange.png");
        return TercelOrange;   
    }
}