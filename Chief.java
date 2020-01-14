import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
//Inherited from pieces.
public class Chief extends Pieces
{
    public Chief(int placement, String color)
    {
        super(placement,color);
    }

    
    public ImageIcon imageGreen()
    {
        ImageIcon ChiefGreen = new ImageIcon("ChiefGreen.png");
        return ChiefGreen;   
    }
    
        public ImageIcon imageOrange()
    {
        ImageIcon ChiefOrange = new ImageIcon("ChiefOrange.png");
        return ChiefOrange;   
    }
}