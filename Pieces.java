import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
//Super class of all the pieces.
public abstract class Pieces
{
    private int placement;
    private String color;
    
    public Pieces(int placement, String color)
    {
        this.placement = placement;
        this.color = color;
    }
    
    public int getPlacement()
    {
        return placement;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public abstract ImageIcon imageGreen();
    public abstract ImageIcon imageOrange();
    
}