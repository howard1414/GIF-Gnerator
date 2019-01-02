/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import java.util.Random;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import java.math.*;
/**
 *
 * @author lv379
 */
public class Main_Drawing_space extends Canvas{
    
    //Image Img;
    JPanel Drawing_space ;
    JFileChooser file_choose ;
    File file_path;
    int x=0,y=0;
    Rectangle windowSize; 
    Point fp,lp,p1,p2;                     //存取座標點
    Status status = Status.active,SaveStatus;      //狀態儲存
    Vector<Line> lines=null;     //繪布紀錄儲存(線)
    ArrayList linecount;
    Stack re;                        //存取繪畫紀錄堆疊
    int pencilem = 0;                //復原用
    int drawfirst =0 ;                //判斷是否為第一次畫用
    private BufferedImage img;
    Main_Frame parent;
    Color color;                    //色彩更改
    Color BGD = Color.white;
    Image ImageBuffer;
    
    float BS;        //線條粗細
    Main_Drawing_space(Main_Frame MF){
        super();
        color = Color.red;
        BS= 1.0f;
        parent = MF;
        file_choose = new JFileChooser();
        Drawing_space = new JPanel();  
        this.setBackground(BGD);
        this.setVisible(true);
        re = new Stack();
        lines = new Vector<Line>();
        linecount = new ArrayList();
        System.out.println(linecount.size());
        this.addMouseMotionListener(
          new MouseAdapter()
                {
                    public void mouseMoved(MouseEvent e)
                    {
                        x=e.getX();                     
                        if(x<20 && MF.toolbarBTN.toolbarVisible == false){
                            MF.toolbarBTN.set_pannel_visible(true);
                        }
                        if(Main_Drawing_space.this.status==Status.select)
                        {
                         fp=e.getPoint(); 
                         if(fp.x >= p1.x-5 &&fp.y >= p1.y-5 &&fp.x <= p2.x+5 && fp.y <= p2.y+5 )
                         {
                            if(fp.x >= p1.x-5 && fp.y >= p1.y-5 && fp.x <=p1.x && fp.y <= p1.y)
                            {
                                Main_Drawing_space.this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                            }
                            else if(fp.x >= (p1.x+p2.x)/2 && fp.y >= p1.y-5 && fp.x <=(p1.x+p2.x)/2+5 && fp.y <= p1.y)
                            {
                                Main_Drawing_space.this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                            }
                            else if(fp.x >= p2.x && fp.y >= p1.y-5 && fp.x <=p2.x+5 && fp.y <= p1.y)
                            {
                                Main_Drawing_space.this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                            }
                            else if(fp.x >= p1.x-5 && fp.y >= (p1.y+p2.y)/2 && fp.x <=p1.x && fp.y <= (p1.y+p2.y)/2+5)
                            {
                                Main_Drawing_space.this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                            }
                            else if(fp.x >= p2.x && fp.y >= (p1.y+p2.y)/2 && fp.x <=p2.x+5 && fp.y <= (p1.y+p2.y)/2+5)
                            {
                               Main_Drawing_space.this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                            }
                            else if(fp.x >= p1.x-5 && fp.y >= p2.y && fp.x <=p1.x && fp.y <= p2.y+5)
                            {
                               Main_Drawing_space.this.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                            }
                            else if(fp.x >= (p1.x+p2.x)/2 && fp.y >= p2.y && fp.x <=(p1.x+p2.x)/2+5 && fp.y <= p2.y+5)
                            {
                                Main_Drawing_space.this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                            }
                            else if(fp.x >= p2.x && fp.y >= p2.y && fp.x <=p2.x+5 && fp.y <= p2.y+5)
                            {
                              Main_Drawing_space.this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                            }
                            else
                            {
                                Main_Drawing_space.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                            }
                        }
                        else
                        {
                           Main_Drawing_space.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        }
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
                        //System.out.println("MOUSE PRESSED!!");
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
                       //選取狀態及8個點
                       else if(Main_Drawing_space.this.status==Status.select)
                       { 
                         fp=e.getPoint(); 
                         if(fp.x >= p1.x-5 &&fp.y >= p1.y-5 &&fp.x <= p2.x+5 && fp.y <= p2.y+5 )
                         {
                            if(fp.x >= p1.x-5 && fp.y >= p1.y-5 && fp.x <=p1.x && fp.y <= p1.y)
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.P1resize;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);                               
                            }
                            else if(fp.x >= (p1.x+p2.x)/2 && fp.y >= p1.y-5 && fp.x <=(p1.x+p2.x)/2+5 && fp.y <= p1.y)
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.P2resize;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                            }
                            else if(fp.x >= p2.x && fp.y >= p1.y-5 && fp.x <=p2.x+5 && fp.y <= p1.y)
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.P3resize;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                            }
                            else if(fp.x >= p1.x-5 && fp.y >= (p1.y+p2.y)/2 && fp.x <=p1.x && fp.y <= (p1.y+p2.y)/2+5)
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.P4resize;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                            }
                            else if(fp.x >= p2.x && fp.y >= (p1.y+p2.y)/2 && fp.x <=p2.x+5 && fp.y <= (p1.y+p2.y)/2+5)
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.P5resize;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                            }
                            else if(fp.x >= p1.x-5 && fp.y >= p2.y && fp.x <=p1.x && fp.y <= p2.y+5)
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.P6resize;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                            }
                            else if(fp.x >= (p1.x+p2.x)/2 && fp.y >= p2.y && fp.x <=(p1.x+p2.x)/2+5 && fp.y <= p2.y+5)
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.P7resize;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                            }
                            else if(fp.x >= p2.x && fp.y >= p2.y && fp.x <=p2.x+5 && fp.y <= p2.y+5)
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.P8resize;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                            }
                            else
                            {
                                System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                                Main_Drawing_space.this.status = Status.move;
                                System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                                fp = e.getPoint();
                            }
                        }
                        else
                        {
                            System.out.println("離開狀態" + MF.Main_Drawing_space.status);
                            if(SaveStatus == Status.drawimage){
                                Main_Drawing_space.this.status = Status.active;
                            }
                            else
                            {
                                Main_Drawing_space.this.status = SaveStatus;
                            }
                            System.out.println("當前狀態" + MF.Main_Drawing_space.status);
                            fp = e.getPoint();                   
                            repaint();
                        }
                      }
                    }
                    public void mouseReleased(MouseEvent e)
                    {   
                        //鉛筆功能(放開滑鼠左鍵)
                        if(lp!=null){
                            if(Main_Drawing_space.this.status == Status.drawpencil)
                            {   
                                linecount.add(lines.size()-pencilem);
                                re.push(lines.size()-pencilem);
                                lp=null;
                                fp=null; 
                                System.out.println(linecount.size());
                            }
                            //直線功能(放開滑鼠左鍵)
                            else if(Main_Drawing_space.this.status == Status.drawline)
                            { 
                                drawfirst=0;
                                lp=null;
                                fp=null;
                                linecount.add(1);
                            re.push(1);
                            repaint();
                            System.out.println(linecount.size());
                        }
                        //畫圓功能(放開滑鼠左鍵)
                        else if(Main_Drawing_space.this.status==Status.drawOval)
                       {  
                           if(lp!=null && fp!=lp)
                              draw_out_line(p1,p2);
                            drawfirst=0;
                            lp=null;
                            linecount.add(1);
                            re.push(1);
                            System.out.println(linecount.size());
                       }
                        //矩形功能(放開滑鼠左鍵)
                        else if(Main_Drawing_space.this.status==Status.drawRect)
                       {    
                            if(lp!=null && fp!=lp)
                              draw_out_line(p1,p2);
                            drawfirst=0;
                            lp=null;
                            fp=null;
                            linecount.add(1);
                            re.push(1);
                            System.out.println(linecount.size());
                        }
                        else if(Main_Drawing_space.this.status==Status.active)
                        {

                        }
                        else 
                        {
                           draw_out_line(p1,p2);
                        }
                     }
                   }
                }
        );
        this.addMouseMotionListener(new MouseAdapter()
            {
                public void mouseDragged(MouseEvent e)
                {   
                    lp=e.getPoint();
                    Graphics g=  Main_Drawing_space.this.getGraphics();
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setColor(color);
                    g2d.setStroke(new BasicStroke(BS));
                     //鉛筆功能(拖曳滑鼠)
                   if(fp != lp && fp!=null)
                   {
                        if(Main_Drawing_space.this.status == Status.drawpencil)
                        {    
                            lp=e.getPoint();
                            g2d.drawLine(fp.x, fp.y, lp.x, lp.y);
                             lines.add(new Line(fp,lp,Pattern.Pencil,color,BS,linecount.size()));
                            fp=lp;     
                        }
                   //畫線功能(拖曳滑鼠)
                        else if(Main_Drawing_space.this.status == Status.drawline)
                        {   
                            lp=e.getPoint();
                            if(drawfirst !=0){
                                int temp = lines.size();
                                lines.removeElementAt(temp-1);
                                repaint();
                            }
                            g2d.drawLine(fp.x, fp.y, lp.x, lp.y);
                            lines.add(new Line(fp,lp,Pattern.Line,color,BS,linecount.size())); 
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
                            Judge_Quadrant();
                            g2d.drawOval(p1.x, p1.y, p2.x-p1.x,p2.y-p1.y);
                            lines.add(new Line(fp,lp,Pattern.Ovil,color,BS,linecount.size())); 
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
                        Judge_Quadrant();
                        g2d.drawRect(p1.x, p1.y, p2.x-p1.x,p2.y-p1.y);
                        lines.add(new Line(fp,lp,Pattern.Rect,color,BS,linecount.size())); 
                        drawfirst=1;
                    }
                    else if(Main_Drawing_space.this.status == Status.P1resize)                    
                    {
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int x,y;
                       x = lp.x;
                       y = lp.y;
                       if(x>=p2.x) x =p2.x;
                       if(y>=p2.y) y =p2.y;
                       p1 = new Point(x,y);
                       if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                   }
                   else if(Main_Drawing_space.this.status == Status.P2resize)                    
                   {
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int y;
                       y = lp.y;
                       if(y>=p2.y) y =p2.y;
                       p1 = new Point(p1.x,y);
                       if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                   }
                   else if(Main_Drawing_space.this.status == Status.P3resize)                    
                   {
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int x,y;
                       x = lp.x;
                       y = lp.y;
                       if(x<=p1.x) x =p1.x;
                       if(y>=p2.y) y =p2.y;
                       p1 = new Point(p1.x,y);
                       p2 = new Point(x,p2.y);
                        if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                   }
                   else if(Main_Drawing_space.this.status == Status.P4resize)                    
                   {
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int x;
                       x = lp.x;
                       if(x>=p2.x) x =p2.x;
                       p1 = new Point(x,p1.y);
                        if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                   }
                   else if(Main_Drawing_space.this.status == Status.P5resize)                    
                   {
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int x;
                       x = lp.x;
                       if(x<=p1.x) x=p1.x;
                       p2 = new Point(x,p2.y);
                        if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                   }
                   else if(Main_Drawing_space.this.status == Status.P6resize)                    
                   {
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int x,y;
                       x = lp.x;
                       y = lp.y;
                       if(x>=p2.x) x =p2.x;
                       if(y<=p1.y) y =p1.y;
                       p1 = new Point(x,p1.y);
                       p2 = new Point(p2.x,y);
                       if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                   }
                   else if(Main_Drawing_space.this.status == Status.P7resize)                    
                   {
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int y;
                       y = lp.y;
                       if(y<=p1.y) y =p1.y;
                       p2 = new Point(p2.x,y);
                       if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                   }
                   else if(Main_Drawing_space.this.status == Status.P8resize)                    
                   {
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int x,y;
                       x = lp.x;
                       y = lp.y;
                       if(x<=p1.x) x =p1.x;
                       if(y<=p1.y) y =p1.y;
                       p2 = new Point(x,y);
                       if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                   }
                   else if(Main_Drawing_space.this.status == Status.move)
                   {   
                       lp=e.getPoint();
                       int temp = lines.size();
                       lines.removeElementAt(temp-1);
                       repaint();
                       int savewidth =0 , saveheight=0;
                       savewidth = lp.x-fp.x;
                       saveheight = lp.y-fp.y;
                       p1= new Point(p1.x+savewidth,p1.y+saveheight);
                       p2= new Point(p2.x+savewidth,p2.y+saveheight);
                       
                       if(SaveStatus ==Status.drawRect){
                           g2d.drawRect(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Rect,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawOval)
                       {
                           g2d.drawOval(p1.x,p1.y ,p2.x-p1.x,p2.y-p1.y);
                           lines.add(new Line(p1,p2,Pattern.Ovil,color,BS,linecount.size()));
                       }
                       else if(SaveStatus == Status.drawimage)
                       {   
                           g2d.drawImage(img,p1.x,p1.y,p2.x-p1.x,p2.y-p1.y, Main_Drawing_space.this);
                           lines.add(new Line(p1,p2,Pattern.Image,img,linecount.size()));
                       }
                       fp = lp;
                    }
                }
            }
           }
        );
    }
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
            linecount.remove(linecount.size()-1);
            repaint();            
            return 1;
        }
        else
        {
            return 0;
        }
    } 
    //歷史重放
    public void history_replay(int step){
         int num = 0;
          ImageBuffer = null;
          Graphics GraImage = null;        
          ImageBuffer = createImage(this.getWidth(), this.getHeight());
          GraImage = ImageBuffer.getGraphics();
          Graphics2D g2d = (Graphics2D)GraImage;
          boolean pencilfist = false;
          int pencilcount =0;
          if(lines!=null)
           {   
            for(Line l : lines)
            {  
               if(num == step) break;
               if(l.Pattern == Pattern.Image){
                  g2d.drawImage(l.Image, 0, 0, 500 * l.Image.getHeight() / l.Image.getWidth(), 500 * l.Image.getHeight() / l.Image.getWidth(), this);
                  num++;
               }
               else
               {
                    g2d.setColor(l.Color);
                    g2d.setStroke(new BasicStroke(l.BasicStroke));
                    if(l.Pattern == Pattern.Line){
                        g2d.drawLine(l.firstpoint.x, l.firstpoint.y, l.lastpoint.x, l.lastpoint.y);  
                        num++;
                    }
                    else  if(l.Pattern == Pattern.Pencil){
                        if(pencilfist==false) pencilfist=true;
                        g2d.drawLine(l.firstpoint.x, l.firstpoint.y, l.lastpoint.x, l.lastpoint.y);
                        if(pencilfist==true) pencilcount++;
                        if(pencilcount == (int)linecount.get(num))
                        {  
                            pencilfist = false;
                            pencilcount =0;
                        num++;
                        }         
                    }
                    else if(l.Pattern ==Pattern.Ovil)
                    {
                        //第一象限
                        if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y<l.firstpoint.y){
                            g2d.drawOval(l.lastpoint.x, l.lastpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.firstpoint.y-l.lastpoint.y));
                        }
                        //第二象限
                        else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y<=l.firstpoint.y){
                            g2d.drawOval(l.firstpoint.x, l.lastpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.firstpoint.y-l.lastpoint.y));
                        }
                        //第三象限
                        else if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y>l.firstpoint.y){
                            g2d.drawOval(l.lastpoint.x, l.firstpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.lastpoint.y-l.firstpoint.y));
                        }
                        //第四象限
                        else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y>=l.firstpoint.y){
                            g2d.drawOval(l.firstpoint.x, l.firstpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.lastpoint.y-l.firstpoint.y));
                        }
                        num++;
                    }
                    else if(l.Pattern ==Pattern.Rect)
                    {
                        //第一象限
                        if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y<l.firstpoint.y){
                            g2d.drawRect(l.lastpoint.x, l.lastpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.firstpoint.y-l.lastpoint.y));
                 
                        }
                        //第二象限
                        else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y<=l.firstpoint.y){
                            g2d.drawRect(l.firstpoint.x, l.lastpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.firstpoint.y-l.lastpoint.y));
            
                        }
                        //第三象限
                        else if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y>l.firstpoint.y){
                            g2d.drawRect(l.lastpoint.x, l.firstpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.lastpoint.y-l.firstpoint.y));
                  
                        }
                        //第四象限
                        else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y>=l.firstpoint.y){
                            g2d.drawRect(l.firstpoint.x, l.firstpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.lastpoint.y-l.firstpoint.y));
                        
                        }
                        num++;
                    }
                }
            }
        }
        Graphics g = Main_Drawing_space.this.getGraphics();
        g.drawImage(ImageBuffer, 0, 0,null);
    }    
    //double buffering
    public void update(Graphics g){
        ImageBuffer = null;
        Graphics GraImage = null;
        ImageBuffer = createImage(this.getWidth(), this.getHeight());
        GraImage = ImageBuffer.getGraphics();
        paint(GraImage);
        GraImage.dispose();
        g.drawImage(ImageBuffer, 0, 0,null);
    }
    //繪圖
    @Override
    public void paint(Graphics g)
    {   
        System.out.println("Paint");
        ImageBuffer = null;
        Graphics GraImage = null;
        ImageBuffer = createImage(this.getWidth(), this.getHeight());
        GraImage = ImageBuffer.getGraphics();
        Graphics2D g2d = (Graphics2D) GraImage;
        if(lines!=null)
        {   
            for(Line l : lines)
            {   
                if(l.Pattern == Pattern.Image)
                {   
                    System.out.println("LODINGE");
                   
                    g2d.drawImage(l.Image,l.firstpoint.x, l.firstpoint.y, l.lastpoint.x-l.firstpoint.x, l.lastpoint.y-l.firstpoint.y, this);
                }
                else
                {
                    g2d.setColor(l.Color);
                    g2d.setStroke(new BasicStroke(l.BasicStroke));
                    if(l.Pattern == Pattern.Line){
                        g2d.drawLine(l.firstpoint.x, l.firstpoint.y, l.lastpoint.x, l.lastpoint.y);  
                    }
                    else if(l.Pattern ==Pattern.Pencil){
                        g2d.drawLine(l.firstpoint.x, l.firstpoint.y, l.lastpoint.x, l.lastpoint.y);  
                    }
                    else if(l.Pattern ==Pattern.Ovil)
                    { 
                        {    
                            //第一象限
                            if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y<l.firstpoint.y){
                                g2d.drawOval(l.lastpoint.x, l.lastpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.firstpoint.y-l.lastpoint.y));
                            }
                            //第二象限
                            else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y<=l.firstpoint.y){
                                g2d.drawOval(l.firstpoint.x, l.lastpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.firstpoint.y-l.lastpoint.y));
                            }
                            //第三象限
                            else if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y>l.firstpoint.y){
                                g2d.drawOval(l.lastpoint.x, l.firstpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.lastpoint.y-l.firstpoint.y));
                            }
                            //第四象限
                            else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y>=l.firstpoint.y){
                                g2d.drawOval(l.firstpoint.x, l.firstpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.lastpoint.y-l.firstpoint.y));
                            }
                        }
                    }
                    else if(l.Pattern ==Pattern.Rect)
                    {
                        {    
                            //第一象限
                            if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y<l.firstpoint.y){
                                g2d.drawRect(l.lastpoint.x, l.lastpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.firstpoint.y-l.lastpoint.y));
                            }
                            //第二象限
                            else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y<=l.firstpoint.y){
                                g2d.drawRect(l.firstpoint.x, l.lastpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.firstpoint.y-l.lastpoint.y));
                            }
                            //第三象限
                            else if(l.lastpoint.x<=l.firstpoint.x && l.lastpoint.y>l.firstpoint.y){
                                g2d.drawRect(l.lastpoint.x, l.firstpoint.y, (l.firstpoint.x-l.lastpoint.x),(l.lastpoint.y-l.firstpoint.y));
                            }
                            //第四象限
                            else if(l.lastpoint.x>l.firstpoint.x && l.lastpoint.y>=l.firstpoint.y){
                                g2d.drawRect(l.firstpoint.x, l.firstpoint.y, (l.lastpoint.x-l.firstpoint.x),(l.lastpoint.y-l.firstpoint.y));
                            }
                        }
                    }
                }
            }
        }
        GraImage.dispose();
        g.drawImage(ImageBuffer, 0, 0,null);
    }       
    //重設頁面
    public void readdpage(){
        lines.removeAllElements();
        parent.Drawing_space_x=500;
        parent.Drawing_space_y=500;
        this.setBounds(0, 0, parent.Drawing_space_x, parent.Drawing_space_y);
        parent.size_btn.setBounds(parent.Drawing_space_x,parent.Drawing_space_y, 15, 15);
        re.removeAll(re);
        img = null;
        repaint();
        parent.Messgebar.setLB("已重新建立頁面");
    }
    //讀取圖片
    public void loadimage() throws FileNotFoundException{
        FileFilter filter = new FileNameExtensionFilter("Images Files", "jpg", "jpeg","gif","bmp");
        System.out.println("Load image...");
        file_choose.setFileFilter(filter);
            if(file_choose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                file_path = file_choose.getSelectedFile();
            }
        String path = String.valueOf(file_path);
       try
       {
            Graphics g = Main_Drawing_space.this.getGraphics();
            img = ImageIO.read(new File(path));
            Point tfp = new Point(0,0);
            Point tlp = new Point(30 + 500 * img.getHeight() / img.getWidth(),500 * img.getHeight() / img.getWidth()+30);
            g.drawImage(img, tfp.x, tfp.y, tlp.x-tfp.x, tlp.y-tfp.y,this);
            p1 =new Point(tfp);
            p2 =new Point(tlp);
            lines.add(new Line(tfp,tlp,Pattern.Image,img,linecount.size()));
            Main_Drawing_space.this.status = Status.drawimage;
            if((tlp.x-tfp.x)>=Main_Drawing_space.this.getWidth() || (tlp.y-tfp.y)>= Main_Drawing_space.this.getHeight() ){
                parent.size_btn.setBounds( tlp.x-tfp.x,tlp.y-tfp.y, 15, 15);
                parent.Panel_size.setPreferredSize(new Dimension((tlp.x-tfp.x)+17, (tlp.y-tfp.y)+17));
                Main_Drawing_space.this.setSize(tlp.x-tfp.x,  tlp.y-tfp.y); 
                parent.Panel_size.repaint();
                parent.Panel_size.updateUI();
                parent.SPW = Main_Drawing_space.this.getWidth();
                parent.SPH = Main_Drawing_space.this.getHeight();
            }
            draw_out_line(tfp,tlp);
            linecount.add(1);
            re.push(1);
       }catch(Exception ex){
            System.out.println("Printing image failed!");       
       }
       //repaint();
    }
    public Vector<Line> request_line(){
        return lines;
    }
    
    public Image request_Image(){
        return ImageBuffer;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(), img.getHeight());
    }
    //象限判斷
    void Judge_Quadrant(){
        if(lp.x<=fp.x && lp.y<fp.y){
            p1 = new Point(lp);
            p2 = new Point(fp);
        }
            else if(lp.x>fp.x && lp.y<=fp.y){
            p1 = new Point(fp.x,lp.y);
            p2 = new Point(lp.x,fp.y );
        }
        else if(lp.x<=fp.x && lp.y>fp.y){
            p1 = new Point(lp.x,fp.y);
            p2 = new Point(fp.x,lp.y );
        }
        else if(lp.x>fp.x && lp.y>=fp.y){
            p1 = new Point(fp);
            p2 = new Point(lp);
        }
    }
    
    
    void Judge_Quadrant2(Point nfp,Point nlp){
        if(nlp.x<=nfp.x && nlp.y<nfp.y){
            p1 = new Point(nlp);
            p2 = new Point(nfp);
        }
            else if(nlp.x>nfp.x && nlp.y<=nfp.y){
            p1 = new Point(nfp.x,nlp.y);
            p2 = new Point(nlp.x,nfp.y );
        }
        else if(nlp.x<=nfp.x && nlp.y>nfp.y){
            p1 = new Point(nlp.x,nfp.y);
            p2 = new Point(nfp.x,nlp.y);
        }
        else if(nlp.x>nfp.x && nlp.y>=nfp.y){
            p1 = new Point(nfp);
            p2 = new Point(nlp);
        }
    }
    void draw_out_line(Point fp,Point lp){ 
       
        if(status == Status.drawOval || status == Status.drawRect || status == Status.drawimage )
            SaveStatus =  Main_Drawing_space.this.status;
        System.out.println("離開狀態" + Main_Drawing_space.this.status);
        Main_Drawing_space.this.status = Status.select;
        System.out.println("當前狀態" + Main_Drawing_space.this.status);
        System.out.println("SavaStatus = " + SaveStatus);
        Graphics draw  = Main_Drawing_space.this.getGraphics();
        Graphics2D draw2D = (Graphics2D)draw;
        draw2D.setColor(Color.BLACK);
        //(1)
        draw2D.drawRect(fp.x-5       ,fp.y-5,         5,5);
        //(2)
        draw2D.drawRect((fp.x+lp.x)/2,fp.y-5,         5,5);
        //(3)
        draw2D.drawRect(lp.x          ,fp.y-5,        5,5);
        //(4)
        draw2D.drawRect(fp.x-5        ,(fp.y+lp.y)/2, 5,5);
        //(5)
        draw2D.drawRect(lp.x          ,(fp.y+lp.y)/2, 5,5);
        //(6)
        draw2D.drawRect(fp.x-5        ,lp.y,          5,5);
        //(7)
        draw2D.drawRect((fp.x+lp.x)/2 ,lp.y,          5,5);
        //(8)
        draw2D.drawRect(lp.x          ,lp.y,          5,5);
        //外虛線
        float[] dashPattern = {10F, 10F, 10F, 10F, 10F, 10F, 10F, 10F};
        Stroke dash = new BasicStroke(1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,3.5f,dashPattern,0f);
        draw2D.setStroke(dash);
        draw2D.drawRect(fp.x-3, fp.y-3, (lp.x - fp.x)+6 , (lp.y - fp.y)+6); 
  }
   
    Boolean temp = true;
    public void drawp(int x1, int y1, int x2, int y2,Line ls){
          if(temp == true){
           temp = false;
             ImageBuffer = createImage(this.getWidth(), this.getHeight());
           }
          Graphics GraImage = null;        
          Graphics g = Main_Drawing_space.this.getGraphics();
          GraImage = ImageBuffer.getGraphics();
          Graphics2D g2d = (Graphics2D)GraImage;
          g2d.setStroke(new BasicStroke(ls.BasicStroke));
          g2d.setColor(ls.Color);
          g2d.drawLine(x1, y1, x2, y2);
          System.out.println(x1 + " " + y1+" "+ x2 +" " + y2+ "\n");
          g.drawImage(ImageBuffer, 0, 0,null);
    }
    
    
    public void drawOvil(int x1, int y1, int x2, int y2,Line ll,int angle){
          if(temp == true){
           temp = false;
             ImageBuffer = createImage(this.getWidth(), this.getHeight());
           }
          Graphics GraImage = null;        
          GraImage = ImageBuffer.getGraphics();
          Graphics2D g2d = (Graphics2D)GraImage;
          g2d.setColor(ll.Color);
          g2d.setStroke(new BasicStroke(ll.BasicStroke));
          Graphics g = Main_Drawing_space.this.getGraphics();
          Point t1 = new Point(x1,y1);
          Point t2 = new Point(x2,y2);
          Judge_Quadrant2(t1,t2);
          g2d.drawArc(p1.x,p1.y,p2.x-p1.x,p2.y-p1.y,90,angle);
          g.drawImage(ImageBuffer, 0, 0,null);
    }
    /*public void drawRect(int x1, int y1, int x2, int y2,Line ll,int linecount){
          if(temp == true){
           temp = false;
             ImageBuffer = createImage(this.getWidth(), this.getHeight());
           }
          Graphics GraImage = null;        
          GraImage = ImageBuffer.getGraphics();
          Graphics2D g2d = (Graphics2D)GraImage;
          g2d.setColor(ll.Color);
          g2d.setStroke(new BasicStroke(ll.BasicStroke));
          Graphics g = Main_Drawing_space.this.getGraphics();
          switch(linecount){
              case 1:
                  break;
              case 2:
                  break;
              case 3:
                  break;
              case 4:
                  break;
                  
          }
          

    }*/

}
