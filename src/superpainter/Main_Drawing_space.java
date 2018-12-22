/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Vector;
import javax.swing.JPanel;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author lv379
 */
public class Main_Drawing_space extends Canvas{
    
    Image Img;
    JPanel Drawing_space ;
    JFileChooser file_choose ;
    File file_path;
    int x=0,y=0;
    Rectangle windowSize; 
    Point fp,lp;                     //存取座標點
    Status status;                   //狀態儲存
    Vector<Line> lines=null;     //繪布紀錄儲存(線)
    Vector<test_image_class> imagesaved = null;  //繪布記錄存儲(圖)
    Out_line Ol;
    Stack re;                        //存取繪畫紀錄堆疊
    int pencilem = 0;                //復原用
    int drawfirst =0 ;                //判斷是否為第一次畫用
    Pattern Pattern;
    private BufferedImage img;
    Main_Frame parent;
    Color color;                    //色彩更改
    Color BGD = Color.white;
    float BasicStroke;        //線條粗細
    
    Main_Drawing_space(Main_Frame MF){
        super();
        color = Color.red;
        BasicStroke = 1.0f;
        parent = MF;
        
        
        file_choose = new JFileChooser();
        Drawing_space = new JPanel();
        this.setBackground(BGD);
        this.setVisible(true);
        re = new Stack();
        lines = new Vector<Line>();
        this.addMouseMotionListener(
          new MouseAdapter()
                {
                    public void mouseMoved(MouseEvent e)
                    {
                        x=e.getX();                     
                        if(x<20 && MF.toolbarBTN.toolbarVisible == false){
                            MF.toolbarBTN.set_pannel_visible(true);
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
                        System.out.println("MOUSE PRESSED!!");
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
                        System.out.println("MOUSE RELEASED!!");
                        if(Main_Drawing_space.this.status == Status.drawpencil)
                        {  
                            
                            re.push(lines.size()-pencilem);
                            lp=null;
                            fp=null;
                            
                        }
                        //直線功能(放開滑鼠左鍵)
                        else if(Main_Drawing_space.this.status == Status.drawline)
                        {
                            //Image ImageBuffer =createImage(MF.Main_Drawing_space.getWidth(),MF.Main_Drawing_space.getHeight());
                            //imagesaved.add(new test_image_class(ImageBuffer));
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
                    System.out.println("MOUSE DRAGGED!!");
                    lp=e.getPoint();
                    Graphics g=  Main_Drawing_space.this.getGraphics();
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setColor(color);
                    g2d.setStroke(new BasicStroke(BasicStroke));
                     //鉛筆功能(拖曳滑鼠)
                   if(Main_Drawing_space.this.status == Status.drawpencil)
                   {    
                       g2d.drawLine(fp.x, fp.y, lp.x, lp.y);
                       lines.add(new Line(fp,lp,Pattern.Line,color,BasicStroke));
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
                       g2d.drawLine(fp.x, fp.y, lp.x, lp.y);
                       lines.add(new Line(fp,lp,Pattern.Line,color,BasicStroke)); 
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
                       {    
                            //第二象限
                            if(lp.x<=fp.x && lp.y<fp.y){
                                g2d.drawOval(lp.x, lp.y, (fp.x-lp.x),(fp.y-lp.y));
                            }
                            //第二象限
                            else if(lp.x>fp.x && lp.y<=fp.y){
                                g2d.drawOval(fp.x, lp.y, (lp.x-fp.x),(fp.y-lp.y));
                            }
                            //第三象限
                            else if(lp.x<=fp.x && lp.y>fp.y){
                                g2d.drawOval(lp.x, fp.y, (fp.x-lp.x),(lp.y-fp.y));
                            }
                            //第四象限
                            else if(lp.x>fp.x && lp.y>=fp.y){
                                g2d.drawOval(fp.x, fp.y, (lp.x-fp.x),(lp.y-fp.y));
                            }
                       }
                       lines.add(new Line(fp,lp,Pattern.Ovil,color,BasicStroke)); 
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
                       {    
                            //第二象限
                            if(lp.x<=fp.x && lp.y<fp.y){
                                g2d.drawRect(lp.x, lp.y, (fp.x-lp.x),(fp.y-lp.y));
                            }
                            //第二象限
                            else if(lp.x>fp.x && lp.y<=fp.y){
                                g2d.drawRect(fp.x, lp.y, (lp.x-fp.x),(fp.y-lp.y));
                            }
                            //第三象限
                            else if(lp.x<=fp.x && lp.y>fp.y){
                                g2d.drawRect(lp.x, fp.y, (fp.x-lp.x),(lp.y-fp.y));
                            }
                            //第四象限
                            else if(lp.x>fp.x && lp.y>=fp.y){
                                g2d.drawRect(fp.x, fp.y, (lp.x-fp.x),(lp.y-fp.y));
                            }
                       }
                       lines.add(new Line(fp,lp,Pattern.Rect,color,BasicStroke)); 
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
        
        g.drawImage(ImageBuffer, 0, 0,null);
        /*if (img != null) 
        {             
            int img_x = (getWidth() - img.getWidth()) / 2;
            int img_y = (getHeight() - img.getHeight()) / 2;
            g.drawImage(img, 0, 0, 500 * img.getHeight() / img.getWidth(), 500 * img.getHeight() / img.getWidth(), this);           
        }     */
    }
    @Override
    
    public void paint(Graphics g)
    {   
        Graphics2D g2d = (Graphics2D)g;
        if(lines!=null)
        {   
            for(Line l : lines)
            {   
                g2d.setColor(l.Color);
                g2d.setStroke(new BasicStroke(l.BasicStroke));
                 if(l.Pattern == Pattern.Line){
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

      if (img != null) {              
                int img_x = (getWidth() - img.getWidth()) / 2;
                int img_y = (getHeight() - img.getHeight()) / 2;
                //g.drawImage(img, img_x, img_y, this);
                g.drawImage(img, 0, 0, 500 * img.getHeight() / img.getWidth(), 500 * img.getHeight() / img.getWidth(), this);
               /*this.setBounds(0, 0, img.getHeight(), img.getWidth());
                parent.size_btn.setBounds(img.getHeight(), img.getWidth(), 15, 15);*/
            }
    }       
    public void readdpage(){
        lines.removeAllElements();
        parent.Drawing_space_x=500;
        parent.Drawing_space_y=500;
        this.setBounds(0, 0, parent.Drawing_space_x, parent.Drawing_space_y);
        parent.size_btn.setBounds(parent.Drawing_space_x,parent.Drawing_space_y, 15, 15);
        img = null;
        repaint();
        parent.Messgebar.setLB("已重新建立頁面");
    }
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
         img = ImageIO.read(new File(path));
       }catch(Exception ex){
            System.out.println("Printing image failed!");       
       }
       repaint();
    }
        @Override
        public Dimension getPreferredSize() {
            return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(), img.getHeight());
        }


    
        
}
