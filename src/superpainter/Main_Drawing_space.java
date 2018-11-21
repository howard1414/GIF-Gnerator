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
    Color color;
    int drawline =0 ;
    Main_Drawing_space(){
        super();
        this.add(Drawing_space);
        this.setBackground(new Color( 50 , 50  ,50));     
        this.setLayout(null);
        this.setVisible(true);
        lines = new Vector<SaveLine>();
        color = Color.red;
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
        //功能實作
        this.addMouseListener(
          new MouseAdapter()      
                {
                    public void mousePressed(MouseEvent e)
                    {
                       if(Main_Drawing_space.this.status==Status.drawpencil) 
                       {                
                        Main_Drawing_space.this.status = Status.drawingpencil;
                        fp=e.getPoint();
                       }
                       else if(Main_Drawing_space.this.status==Status.drawline)
                       {
                         Main_Drawing_space.this.status = Status.drawingline;
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
                            
                        }
                        else if(Main_Drawing_space.this.status == Status.drawingline)
                        {
                            Main_Drawing_space.this.status=Status.active;
                            drawline=0;
                            lines.add(new SaveLine(fp,lp)); 
                            lp=null;
                            fp=null;
                            System.out.print(lines.size()+"\n");
                            repaint();
                        }    
                    }
                }
        );
        this.addMouseMotionListener(new MouseAdapter()
            {
                public void mouseDragged(MouseEvent e)
                {   
                   if(Main_Drawing_space.this.status == Status.drawingpencil)
                   {    
                       lp=e.getPoint();
                       Graphics g=  Main_Drawing_space.this.getGraphics();
                       g.setColor(color);
                       g.drawLine(fp.x, fp.y, lp.x, lp.y);
                       lines.add(new SaveLine(fp,lp));
                       fp=lp;     
                   }
                   else if(Main_Drawing_space.this.status == Status.drawingline)
                   {   
                       if(drawline !=0){
                           int temp = lines.size();
                           lines.removeElementAt(temp-1);
                           repaint();
                           System.out.print("repaint\n");
                       }
                       lp=e.getPoint(); 
                       Graphics g=  Main_Drawing_space.this.getGraphics();
                       g.setColor(color);
                       g.drawLine(fp.x, fp.y, lp.x, lp.y);
                       lines.add(new SaveLine(fp,lp)); 
                       System.out.print("set line \n");
                       System.out.print("lp.x=" + lp.x + ",lp.y=" +lp.y+"\n");
                       drawline=1;     
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
        g.setColor(color);
        if(lines!=null)
        {   
            for(SaveLine l : lines)
            {
                 g.drawLine(l.fristpoint.x, l.fristpoint.y, l.lastpoint.x, l.lastpoint.y);  
            }
        }
    }
}
