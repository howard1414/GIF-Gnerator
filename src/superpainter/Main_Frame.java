
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
    JPanel Panel_Main = new JPanel(); 
     Main_Frame(String APPVERSION,String Title) {
        
           
        this.setSize(600,600);
        this.setLocation(200, 200);
        this.add(Panel_Main);
        Panel_Main.setLayout(new BorderLayout());
        this.setTitle(Title + " - " + APPVERSION);
        Add_Object();
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
    
    void Add_Object(){
        //將其餘版面新增至程式面板上
        Main_Drawing_space Main_Drawing_space = new Main_Drawing_space();
        Panel_Main.add(Main_Drawing_space,BorderLayout.CENTER);
    }
}
