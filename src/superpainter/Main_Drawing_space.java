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
    Point fp,lp;
    Status status;
    Vector<SaveLine> lines=null;
    Main_Drawing_space(){
        super();
        this.add(Drawing_space);
        this.setBackground(new Color( 50 , 50  ,50));     
        this.setLayout(null);
        this.setVisible(true);
         //mouse event blocks
        this.addMouseMotionListener(
          new MouseAdapter()
                {
                    public void mouseMoved(MouseEvent e)
                    {
                        x=e.getX();                     
                        if(x<20){
                        ToolbarBTN.Panel_Button.setVisible(true);                        
                        }                 
                    }
                }
         );
        this.addMouseListener(
          new MouseAdapter()      
                {
                    public void mousePressed(MouseEvent e)
                    {
                       if(Main_Drawing_space.this.status==Status.drawpaint) 
                       {
                        System.out.print("鼠標點下\n");                       
                        Main_Drawing_space.this.status = Status.drawingpencil;
                        fp=e.getPoint();
                       }
                    }
                    public void mouseReleased(MouseEvent e)
                    {   
                        if(Main_Drawing_space.this.status == Status.drawingpencil)
                        {
                            Main_Drawing_space.this.status=Status.active;
                            lp=null;
                            fp=null;
                            System.out.print("鼠標放開\n");
                        }    
                    }
                }
        );
        this.addMouseMotionListener(new MouseAdapter()
            {
                public void mouseDragged(MouseEvent e)
                {   
                   if( Main_Drawing_space.this.status == Status.drawingpencil)
                   {    
                       lp=e.getPoint();
                       Graphics g=  Main_Drawing_space.this.getGraphics();
                       g.setColor(Color.red);
                       g.drawLine(fp.x, fp.y, lp.x, lp.y);
                       Point savefp;
                       savefp = fp;
                       fp=lp;
                       System.out.print("鼠標拖動\n");
                       lines.add(new SaveLine(savefp,lp));
                   } 
                 }
            }
        );
       
         /*this.addMouseListener(
                 new MouseAdapter(){
                     public void mousePressed(MouseEvent e)
                    {                 
                        System.out.println("color event" + e.toString());
                        change_color();
                    }              
                 }        
         );*/    
    }
   /* void change_color(){
         this.setBackground(new Color( ran.nextInt(256) , ran.nextInt(256)  ,ran.nextInt(256)));
    }*/
    public void paint(Graphics g)
    {
        g.setColor(Color.red);
        if(lines!=null)
        {   
            for(SaveLine l : lines)
            {
                System.out.print("drawLine");
                 g.drawLine(l.fristpoint.x, l.fristpoint.y, l.lastpoint.x, l.lastpoint.y);  
            }
        }
    }
}
