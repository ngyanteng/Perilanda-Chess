import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
//Inherited from pieces.
public class Excel extends Pieces
{
    public Excel(int placement, String color)
    {
        super(placement,color);
    }

    
    public ImageIcon imageGreen()
    {
        ImageIcon ExcelGreen = new ImageIcon("ExcelGreen.png");
        return ExcelGreen;   
    }
    
        public ImageIcon imageOrange()
    {
        ImageIcon ExcelOrange = new ImageIcon("ExcelOrange.png");
        return ExcelOrange;   
    }
}
