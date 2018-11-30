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
public class Main_Drawing_space extends Canvas{
    Random ran = new Random();  
    JPanel Drawing_space = new JPanel();
    //painting_space sp ; 
    int x=0,y=0;
    Rectangle windowSize; 
    Point fp,lp;                     //存取座標點
    Status status;                   //狀態儲存
    Vector<SaveLine> lines=null;     //繪布紀錄儲存(線)
    Color color;                     //色彩更改
    Stack re;                        //存取繪畫紀錄堆疊
    int pencilem = 0;                //復原用
    int drawfirst =0 ;                //判斷是否為第一次畫用
    Pattern Pattern;
    Main_Drawing_space(Main_Frame MF){
        super();
        this.setBackground(Color.WHITE);     
        this.setVisible(true);
        re = new Stack();
        lines = new Vector<SaveLine>();
        color = Color.red;
        this.addMouseMotionListener(
          new MouseAdapter()
                {
                    public void mouseMoved(MouseEvent e)
                    {
                        x=e.getX();                     
                        if(x<20){
                        ToolbarBTN.Panel_Button.setVisible(true); 
                        MF.toolbarBTN.close_event=false;
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
                       //畫圓功能(按下滑鼠左鍵)
                       else if(Main_Drawing_space.this.status==Status.drawOval)
                       {
                         fp=e.getPoint();
                       }
                       //矩形功能(按下滑鼠左鍵)
                       else if(Main_Drawing_space.this.status==Status.drawRect)
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
                            drawfirst=0;
                            lp=null;
                            fp=null;
                            re.push(1);
                            repaint();
                        }
                        //畫圓功能(放開滑鼠左鍵)
                        else if(Main_Drawing_space.this.status==Status.drawOval)
                       {
                            drawfirst=0;
                            lp=null;
                            fp=null;
                            re.push(1);
                            repaint();
                       }
                        //矩形功能(放開滑鼠左鍵)
                        else if(Main_Drawing_space.this.status==Status.drawRect)
                       {
                            drawfirst=0;
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
                       lines.add(new SaveLine(fp,lp,Pattern.Line));
                       fp=lp;     
                   }
                   //畫線功能(拖曳滑鼠)
                   else if(Main_Drawing_space.this.status == Status.drawline)
                   {  
                       if(drawfirst !=0){
                           int temp = lines.size();
                           lines.removeElementAt(temp-1);
                           repaint();
                       }
                       lp=e.getPoint(); 
                       Graphics g=  Main_Drawing_space.this.getGraphics();
                       g.setColor(color);
                       g.drawLine(fp.x, fp.y, lp.x, lp.y);
                       lines.add(new SaveLine(fp,lp,Pattern.Line)); 
                       drawfirst=1;     
                   }
                   //畫圓功能(拖曳滑鼠)
                   else if(Main_Drawing_space.this.status == Status.drawOval)
                   {
                       if(drawfirst !=0){
                           int temp = lines.size();
                           lines.removeElementAt(temp-1);
                           repaint();
                       }
                       lp=e.getPoint();
                       Graphics g=  Main_Drawing_space.this.getGraphics();
                       g.setColor(color);
                       {    
                            //第二象限
                            if(lp.x<=fp.x && lp.y<fp.y){
                                g.drawOval(lp.x, lp.y, (fp.x-lp.x),(fp.y-lp.y));
                            }
                            //第二象限
                            else if(lp.x>fp.x && lp.y<=fp.y){
                                g.drawOval(fp.x, lp.y, (lp.x-fp.x),(fp.y-lp.y));
                            }
                            //第三象限
                            else if(lp.x<=fp.x && lp.y>fp.y){
                                g.drawOval(lp.x, fp.y, (fp.x-lp.x),(lp.y-fp.y));
                            }
                            //第四象限
                            else if(lp.x>fp.x && lp.y>=fp.y){
                                g.drawOval(fp.x, fp.y, (lp.x-fp.x),(lp.y-fp.y));
                            }
                       }
                       lines.add(new SaveLine(fp,lp,Pattern.Ovil)); 
                       drawfirst=1;
                   }
                   //矩形功能
                   else if(Main_Drawing_space.this.status == Status.drawRect)
                   {
                       if(drawfirst !=0){
                           int temp = lines.size();
                           lines.removeElementAt(temp-1);
                           repaint();
                       }
                       lp=e.getPoint();
                       Graphics g=  Main_Drawing_space.this.getGraphics();
                       g.setColor(color);
                       {    
                            //第二象限
                            if(lp.x<=fp.x && lp.y<fp.y){
                                g.drawRect(lp.x, lp.y, (fp.x-lp.x),(fp.y-lp.y));
                            }
                            //第二象限
                            else if(lp.x>fp.x && lp.y<=fp.y){
                                g.drawRect(fp.x, lp.y, (lp.x-fp.x),(fp.y-lp.y));
                            }
                            //第三象限
                            else if(lp.x<=fp.x && lp.y>fp.y){
                                g.drawRect(lp.x, fp.y, (fp.x-lp.x),(lp.y-fp.y));
                            }
                            //第四象限
                            else if(lp.x>fp.x && lp.y>=fp.y){
                                g.drawRect(fp.x, fp.y, (lp.x-fp.x),(lp.y-fp.y));
                            }
                       }
                       lines.add(new SaveLine(fp,lp,Pattern.Rect)); 
                       drawfirst=1;
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
    //double buffering
    public void update(Graphics g){
        Image ImageBuffer = null;
        Graphics GraImage = null;
        ImageBuffer = createImage(this.getWidth(), this.getHeight());
        GraImage = ImageBuffer.getGraphics();
        paint(GraImage);
        GraImage.dispose(); 
        g.drawImage(ImageBuffer, 0, 0, this);
    }
    public void paint(Graphics g)
    {
        g.setColor(color);
        if(lines!=null)
        {   
            for(SaveLine l : lines)
            {    
                 if(l.Pattern == Pattern.Line){
                     g.drawLine(l.firstpoint.x, l.firstpoint.y, l.lastpoint.x, l.lastpoint.y);  
                 }
                 else if(l.Pattern ==Pattern.Ovil)
                 {
                    {    
                        //第一象限
                        if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y<l.firstpoint.y){
                            g.drawOval(l.lastpoint.x, l.lastpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.firstpoint.y-l.lastpoint.y));
                        }
                        //第二象限
                        else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y<=l.firstpoint.y){
                            g.drawOval(l.firstpoint.x, l.lastpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.firstpoint.y-l.lastpoint.y));
                        }
                        //第三象限
                        else if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y>l.firstpoint.y){
                            g.drawOval(l.lastpoint.x, l.firstpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.lastpoint.y-l.firstpoint.y));
                        }
                        //第四象限
                        else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y>=l.firstpoint.y){
                            g.drawOval(l.firstpoint.x, l.firstpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.lastpoint.y-l.firstpoint.y));
                        }
                    }
                 }
                 else if(l.Pattern ==Pattern.Rect)
                 {
                    {    
                        //第一象限
                        if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y<l.firstpoint.y){
                            g.drawRect(l.lastpoint.x, l.lastpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.firstpoint.y-l.lastpoint.y));
                        }
                        //第二象限
                        else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y<=l.firstpoint.y){
                            g.drawRect(l.firstpoint.x, l.lastpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.firstpoint.y-l.lastpoint.y));
                        }
                        //第三象限
                        else if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y>l.firstpoint.y){
                            g.drawRect(l.lastpoint.x, l.firstpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.lastpoint.y-l.firstpoint.y));
                        }
                        //第四象限
                        else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y>=l.firstpoint.y){
                            g.drawRect(l.firstpoint.x, l.firstpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.lastpoint.y-l.firstpoint.y));
                        }
                    }
                 }
                 
            }
        }
    }
}
