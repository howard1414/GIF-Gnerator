
package superpainter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
/*
主視窗介面
-- 設定視窗大小 
-- 設定視窗監聽元件
-- 新增panel物件   
*/
public class Main_Frame extends Frame {
    SuperPainter parent;
     Main_Frame(String APPVERSION,String Title) {
        
        JPanel Panel_Main = new JPanel();     
        this.setSize(600,600);
        this.setLocation(200, 200);
        this.add(Panel_Main);  
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
        
    }
}
