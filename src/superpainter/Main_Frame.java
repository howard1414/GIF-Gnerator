
package superpainter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
/*
主視窗介面
-- 設定視窗大小 
-- 設定視窗監聽元件
-- 新增panel物件   
*/
public class Main_Frame extends Frame {
    public JPanel Panel_Main = new JPanel();
    public JPanel Panel_size;
    private JButton size_btn;
    private int Drawing_space_x=500,Drawing_space_y=500;
    Main_Drawing_space Main_Drawing_space ;
    Messgebar  Messgebar ;
    ToolbarBTN toolbarBTN;
    int x;
     Main_Frame(String APPVERSION,String Title) {
        Main_Drawing_space = new Main_Drawing_space(this);
        
        Messgebar = new Messgebar();
        this.setSize(700,700);
        this.setLocation(100, 20);
        this.add(Panel_Main);
        Panel_Main.setLayout(new BorderLayout());
        Panel_Main.setBackground(new Color( 50 , 50  ,50));
        //add_Panel_Main();
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
                        if(x<20){
                        ToolbarBTN.Panel_Button.setVisible(true); 
                        toolbarBTN.close_event=false;
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
    
    void add_Panel_Main(){
        Panel_size = new JPanel();   
        Panel_size.setLayout(null);
        Main_Drawing_space.setBounds(0,0, Drawing_space_x, Drawing_space_y);
        size_btn = new JButton();
        size_btn.setBounds(Drawing_space_x,Drawing_space_y, 15, 15);
        Panel_size.add(Main_Drawing_space);
        Panel_size.add(size_btn);
        Panel_Main.add(Panel_size);
        size_btn.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseReleased(MouseEvent e)
                    {   
                        if(toolbarBTN.close_event=false){
                        Drawing_space_x = (int)MouseInfo.getPointerInfo().getLocation().x- Panel_Main.getLocationOnScreen().x-100;    
                        }else{
                        Drawing_space_x = (int)MouseInfo.getPointerInfo().getLocation().x- Panel_Main.getLocationOnScreen().x;
                        }
                        
                        Drawing_space_y = (int)MouseInfo.getPointerInfo().getLocation().y- Panel_Main.getLocationOnScreen().y;
                        if(Drawing_space_x <0){Drawing_space_x=0;}
                        if(Drawing_space_y <0){Drawing_space_y=0;}
                        size_btn.setBounds( Drawing_space_x, Drawing_space_y, 15, 15);
                        Main_Drawing_space.setBounds(0,0,  Drawing_space_x,  Drawing_space_y);         
                    }    
                }    
        );
        

    }
    
}
