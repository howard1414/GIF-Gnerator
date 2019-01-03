
package superpainter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.geom.*;

/*
主視窗介面
-- 設定視窗大小 
-- 設定視窗監聽元件
-- 新增panel物件   
*/
public class Main_Frame extends Frame {
    public JPanel Panel_Main = new JPanel();
    public JPanel Panel_size;
    public JButton size_btn;
    public int Drawing_space_x=500,Drawing_space_y=500;
    Main_Drawing_space Main_Drawing_space ;
    Messgebar  Messgebar ;
    ToolbarBTN toolbarBTN; 
    ScrollPane JSC;
    Main_Frame THIS;
    int PageWidth = 500;
    int PageHeight = 500;
    int SPW = 500;
    int SPH = 500;
    int x;
    int fpx;
    int fpy;
    boolean firstin = true;
    int count=0;
    
     Main_Frame(String APPVERSION,String Title){
        Messgebar = new Messgebar();
        THIS = this;
        this.setSize(700,700);
        this.setLocation(100, 20);
        this.add(Panel_Main);
        Panel_Main.setLayout(new BorderLayout());
        Panel_Main.setBackground(new Color( 50 , 50  ,50));
        this.setTitle(Title + " - " + APPVERSION);
        Add_Object(this);
        this.setVisible(true);
    
        this.addWindowListener( new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                        System.exit(0);
                }
            }
        );
        this.addMouseMotionListener(
          new MouseAdapter()
                {
                    public void mouseMoved(MouseEvent e)
                    {
                        x=e.getX();                     
                        if(x<20 && toolbarBTN.toolbarVisible==false){
                            toolbarBTN.set_pannel_visible(true);
                        }                 
                    }
                }
         );
}

    void Add_Object(Main_Frame MF){
        //將其餘版面新增至程式面板上
        toolbarBTN = new ToolbarBTN(MF);
        Panel_Main.add(Messgebar,BorderLayout.SOUTH);       
        Panel_Main.add(toolbarBTN.Panel_Button,BorderLayout.WEST);
    }
    void addpage(){
        Main_Drawing_space = new Main_Drawing_space(this);
    }
    void add_Panel_Main(){
        JSC = new ScrollPane();
        JSC.setBounds(0, 0,this.getWidth()-ToolbarBTN.Panel_Button.getWidth()-16,this.getHeight()-this.Messgebar.getHeight()-38);
        Panel_size = new JPanel();
        Panel_size.setLayout(null); 
        Panel_size.setBackground(new Color(50,50,50));
        Panel_size.setPreferredSize(new Dimension(Drawing_space_x+17, Drawing_space_y+17));
        Main_Drawing_space.setBounds(0,0, Drawing_space_x, Drawing_space_y);
        size_btn = new JButton();
        size_btn.setBounds(Drawing_space_x,Drawing_space_y, 15, 15);
        
        Panel_size.add(Main_Drawing_space);
        Panel_size.add(size_btn);
        
        JSC.add(Panel_size);
        JSC.validate();
        Panel_Main.add(JSC);
        

        size_btn.addMouseMotionListener(
                new MouseAdapter()
                {                                    
                    public void mouseDragged(MouseEvent e)
                    {   
                        if(count %4 == 0){
                            Main_Drawing_space.repaint();
                            Panel_size.repaint();
                        }
                        
                        Drawing_space_x = (int)MouseInfo.getPointerInfo().getLocation().x - Panel_Main.getLocationOnScreen().x ;
                        Drawing_space_y = (int)MouseInfo.getPointerInfo().getLocation().y - Panel_Main.getLocationOnScreen().y ;
                        
                        if(toolbarBTN.toolbarVisible==true && SPW <= JSC.getWidth())
                        {
                            Drawing_space_x -= ToolbarBTN.Panel_Button.getWidth();
                        }
                        if(SPW >= JSC.getWidth()){   
                            Drawing_space_x =  SPW + (Drawing_space_x-fpx);
                        }
                        if(SPH >= JSC.getHeight()){    
                            Drawing_space_y  = SPH + (Drawing_space_y-fpy);
                        }
                        
                        if(Drawing_space_x <0){Drawing_space_x=0;}
                        if(Drawing_space_y <0){Drawing_space_y=0;}
                      
                        size_btn.setBounds( Drawing_space_x, Drawing_space_y, 15, 15);
                        Panel_size.setPreferredSize(new Dimension(Drawing_space_x+17, Drawing_space_y+17));
                        draw_outline();
                        count++;
                    }
                }    
        );
        size_btn.addMouseListener(
          new MouseAdapter()      
                {
                    public void mouseReleased(MouseEvent e){
                       count=0;
                       Main_Drawing_space.setSize(Drawing_space_x,  Drawing_space_y); 
                       Main_Frame.this.Panel_size.repaint();
                       firstin = true;
                       Panel_size.updateUI();
                       SPW = Main_Drawing_space.getWidth();
                       SPH = Main_Drawing_space.getHeight();
                    }
                    public void mousePressed(MouseEvent e){
                        fpx = (int)MouseInfo.getPointerInfo().getLocation().x - Panel_Main.getLocationOnScreen().x;
                        fpy = (int)MouseInfo.getPointerInfo().getLocation().y - Panel_Main.getLocationOnScreen().y;
                    }
                }
        );        
    }
    void draw_outline(){
        Graphics g = Main_Frame.this.Panel_size.getGraphics();
        Graphics g2 = Main_Frame.this.Main_Drawing_space.getGraphics();
        Graphics2D g2d = (Graphics2D)g;
        Graphics2D g2d2 = (Graphics2D)g2;
        float[] dashPattern = {10F, 20F, 10F, 20F, 10F, 20F, 20F, 10F};
        Stroke dash = new BasicStroke(2.5f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,3.5f,dashPattern,0f);
        g2d.setColor(Color.red);
        g2d.setStroke(dash);
        g2d2.setColor(Color.red);
        g2d2.setStroke(dash);
        if(Drawing_space_x>Main_Drawing_space.getWidth() && Drawing_space_y > Main_Drawing_space.getHeight())
        {    
            g2d.drawLine(Drawing_space_x, 0,Drawing_space_x,Drawing_space_y);
            g2d.drawLine(0, Drawing_space_y, Drawing_space_x, Drawing_space_y);
            
        }
        else if(Drawing_space_x < Main_Drawing_space.getWidth() && Drawing_space_y > Main_Drawing_space.getHeight())
        {   
            g2d2.drawLine(Drawing_space_x, 0, Drawing_space_x, Main_Drawing_space.getHeight());
            g2d.drawLine(Drawing_space_x,(Drawing_space_y - Main_Drawing_space.getHeight()), Drawing_space_x, Drawing_space_y);
            g2d.drawLine(0, Drawing_space_y, Drawing_space_x, Drawing_space_y);
        }
        else if(Drawing_space_x > Main_Drawing_space.getWidth() && Drawing_space_y < Main_Drawing_space.getHeight())
        {
            g2d2.drawLine(0, Drawing_space_y, Main_Drawing_space.getWidth(), Drawing_space_y);
            g2d.drawLine(Drawing_space_x,0, Drawing_space_x, Drawing_space_y);
            g2d.drawLine((Drawing_space_x-Main_Drawing_space.getWidth()), Drawing_space_y, Drawing_space_x, Drawing_space_y);
        }
        else if(Drawing_space_x < Main_Drawing_space.getWidth() && Drawing_space_y < Main_Drawing_space.getHeight())
        {
            g2d2.drawLine( Drawing_space_x, 0, Drawing_space_x, Drawing_space_y);
            g2d2.drawLine(0, Drawing_space_y, Drawing_space_x, Drawing_space_y);
            
        }
 
    }
}
