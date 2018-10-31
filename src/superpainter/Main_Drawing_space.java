/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Vector;
/**
 *
 * @author lv379
 */
public class Main_Drawing_space extends Panel{
    Random ran = new Random();
    Main_Drawing_space(){
        
        this.setBackground(new Color( ran.nextInt(256)+1 , ran.nextInt(256)+1  ,ran.nextInt(256)+1));
        this.setLayout(null);
        this.setVisible(true);
        
        //mouse event blocks
         this.addMouseListener(
                 new MouseAdapter(){
                     public void mousePressed(MouseEvent e)
                    {
                        System.out.println("color event" + e.toString());
                        changecolor();
                    }
                     
                    
                 }
         
         
         
         
         );
        
    }
    void changecolor(){
         this.setBackground(new Color( ran.nextInt(256)+1 , ran.nextInt(256)+1  ,ran.nextInt(256)+1));
    }
}
