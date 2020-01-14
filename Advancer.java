import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
//Inherited from pieces.
public class Advancer extends Pieces
{
    public Advancer(int placement, String color)
    {
        super(placement,color);
    }
 
    public ImageIcon imageGreen()
    {
        ImageIcon AdvancerGreen = new ImageIcon("AdvancerGreen.png");
        return AdvancerGreen;   
    }
    
        public ImageIcon imageOrange()
    {
        ImageIcon AdvancerOrange = new ImageIcon("AdvancerOrange.png");
        return AdvancerOrange;   
    }
    
        

}