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
import javax.swing.JPanel;
/**
 *
 * @author lv379
 */
public class Main_Drawing_space extends Panel{
    Random ran = new Random();  
    JPanel Drawing_space = new JPanel();
    int x=0,y=0;
    Rectangle windowSize; 
    Main_Drawing_space(){
        super();
        this.add(Drawing_space);
        this.setBackground(new Color( 50 , 50  ,50));     
        this.setLayout(null);
        this.setVisible(true);
        this.addMouseMotionListener(
          new MouseAdapter()
                {
                    public void mouseMoved(MouseEvent e)
                    {
                        x=e.getX();                     
                        if(x<20){
                        toolbarBTN.Panel_Button.setVisible(true);                        
                        }                 
                    }
                }
         );
        //mouse event blocks
         this.addMouseListener(
                 new MouseAdapter(){
                     public void mousePressed(MouseEvent e)
                    {                 
                        System.out.println("color event" + e.toString());
                        change_color();
                    }              
                 }        
         );    
    }
    void change_color(){
         this.setBackground(new Color( ran.nextInt(256) , ran.nextInt(256)  ,ran.nextInt(256)));
    }
}
