
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
    public JButton size_btn;
    public int Drawing_space_x=500,Drawing_space_y=500;
    Main_Drawing_space Main_Drawing_space ;
    Messgebar  Messgebar ;
    ToolbarBTN toolbarBTN; 
    JScrollPane JSC;
    Main_Frame THIS;
    int x;
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
                        if(x<20){
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
        JSC = new JScrollPane();
        JSC.setBounds(0, 0,this.getWidth()-ToolbarBTN.Panel_Button.getWidth()-16,this.getHeight()-this.Messgebar.getHeight()-38);
        Panel_size = new JPanel();
        Panel_size.setLayout(null);  
        Panel_size.setPreferredSize(new Dimension(Drawing_space_x+17, Drawing_space_y+17));
        Main_Drawing_space.setBounds(0,0, Drawing_space_x, Drawing_space_y);
        size_btn = new JButton();
        size_btn.setBounds(Drawing_space_x,Drawing_space_y, 15, 15);
        Panel_size.add(Main_Drawing_space);
        Panel_size.add(size_btn);
        JSC.getViewport().add(Panel_size);
        JSC.validate();
        Panel_Main.add(JSC);
        
        
        JSC.getVerticalScrollBar().addAdjustmentListener(
            new AdjustmentListener() {
                public void adjustmentValueChanged(AdjustmentEvent e) {
                  
                }
            });
        
        size_btn.addMouseMotionListener(
                new MouseAdapter()
                {                                    
                    public void mouseDragged(MouseEvent e)
                    {   
                        Main_Drawing_space.repaint();
                        Drawing_space_x = (int)MouseInfo.getPointerInfo().getLocation().x - Panel_Main.getLocationOnScreen().x - ToolbarBTN.Panel_Button.getWidth();
                        Drawing_space_y = (int)MouseInfo.getPointerInfo().getLocation().y - Panel_Main.getLocationOnScreen().y ;
                        if(Drawing_space_x <0){Drawing_space_x=0;}
                        if(Drawing_space_y <0){Drawing_space_y=0;}
                        size_btn.setBounds( Drawing_space_x, Drawing_space_y, 15, 15);
                        Main_Drawing_space.setBounds(0,0,  Drawing_space_x,  Drawing_space_y); 
                        
                        Panel_size.setPreferredSize(new Dimension(Drawing_space_x+17, Drawing_space_y+17));     
                        
                        Panel_Main.updateUI();
                    }    
                }    
        );
    }
}
