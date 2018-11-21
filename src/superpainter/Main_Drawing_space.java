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
import java.util.*;
/**
 *
 * @author lv379
 */
public class Main_Drawing_space extends Panel{
    Random ran = new Random();  
    JPanel Drawing_space = new JPanel();
    int x=0,y=0;
    Rectangle windowSize; 
    Point fp,lp;                     //存取座標點
    Status status;                   //狀態儲存
    Vector<SaveLine> lines=null;     //繪布紀錄儲存
    Color color;                     //色彩更改
    Stack re;                        //存取繪畫紀錄堆疊
    int pencilem = 0;                //復原用
    int drawline =0 ;                //判斷是否為第一次畫線用
    
    Main_Drawing_space(){
        super();
        this.add(Drawing_space);
        this.setBackground(new Color( 50 , 50  ,50));     
        this.setLayout(null);
        this.setVisible(true);
        re = new Stack();
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
                        //鉛筆功能(按下滑鼠左鍵)
                       if(Main_Drawing_space.this.status==Status.drawpencil)        
                       {                
                        fp=e.getPoint();
                        pencilem = lines.size();
                       }
                       //畫線功能(按下滑鼠左鍵)
                       else if(Main_Drawing_space.this.status==Status.drawline)     
                       {
                         fp=e.getPoint();
                       }
                    }
                    public void mouseReleased(MouseEvent e)
                    {   
                        //鉛筆功能(放開滑鼠左鍵)
                        if(Main_Drawing_space.this.status == Status.drawpencil)
                        {
                            re.push(lines.size()-pencilem);
                            lp=null;
                            fp=null;
                            
                        }
                        //直線功能(放開滑鼠左鍵)
                        else if(Main_Drawing_space.this.status == Status.drawline)
                        {
                            drawline=0;
                            lp=null;
                            fp=null;
                            re.push(1);
                            repaint();
                        }    
                    }
                }
        );
        this.addMouseMotionListener(new MouseAdapter()
            {
                public void mouseDragged(MouseEvent e)
                {  
                   //鉛筆功能(拖曳滑鼠)
                   if(Main_Drawing_space.this.status == Status.drawpencil)
                   {    
                       lp=e.getPoint();
                       Graphics g=  Main_Drawing_space.this.getGraphics();
                       g.setColor(color);
                       g.drawLine(fp.x, fp.y, lp.x, lp.y);
                       lines.add(new SaveLine(fp,lp));
                       fp=lp;     
                   }
                   //畫線功能(拖曳滑鼠)
                   else if(Main_Drawing_space.this.status == Status.drawline)
                   {  
                           if(drawline !=0){
                           int temp = lines.size();
                           lines.removeElementAt(temp-1);
                           repaint();
                       }
                       lp=e.getPoint(); 
                       Graphics g=  Main_Drawing_space.this.getGraphics();
                       g.setColor(color);
                       g.drawLine(fp.x, fp.y, lp.x, lp.y);
                       lines.add(new SaveLine(fp,lp)); 
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
    
    //復原功能實作
    public int recovery(){
        if(re.empty() == false){
            if((int)re.peek() != 1){
                int count = (int)re.peek();
                while(count!=0){
                    int temp = lines.size();
                    lines.removeElementAt(temp-1);
                    count--;
                }
                re.pop();
            }
            else
            {   
                int temp = lines.size();
                lines.removeElementAt(temp-1);
                re.pop();
            }
            repaint();            
            return 1;
        }
        else
        {
            return 0;
        }
    } 
 
    
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
