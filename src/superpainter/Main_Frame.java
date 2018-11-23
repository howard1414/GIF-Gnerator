
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
    Main_Drawing_space Main_Drawing_space ;
    Messgebar  Messgebar ;
     Main_Frame(String APPVERSION,String Title) {
         Main_Drawing_space = new Main_Drawing_space(this);
         Messgebar = new Messgebar();
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
}

    void Add_Object(Main_Frame MF){
        //將其餘版面新增至程式面板上
        ToolbarBTN toolbarBTN = new ToolbarBTN(MF);
        Panel_Main.add(Messgebar,BorderLayout.SOUTH);       
        Panel_Main.add(toolbarBTN.Panel_Button,BorderLayout.WEST);
    }
    
    void add_Panel_Main(){
         Panel_Main.add(Main_Drawing_space);
    }
    
}
