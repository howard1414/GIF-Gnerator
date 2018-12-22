/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

/**
 *
 * @author HALUNA
 */
import java.awt.*;
import javax.swing.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

public class Out_line  {
   
    Out_line(Point fp,Point lp,Main_Drawing_space MDS){
     
        MDS.addMouseListener(
          new MouseAdapter()      
                {
                    public void mousePressed(MouseEvent e)
                    {
                        
                    }  
                    
                     public void mouseReleased(MouseEvent e)
                    {
                        
                    }   
                });
        MDS.addMouseMotionListener(new MouseAdapter()
            {
                public void mouseDragged(MouseEvent e)
                {
                    
                }
            });         

 
    }
}
