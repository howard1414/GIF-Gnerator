/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author lv379
 */
public class Output extends Frame {
    Main_Frame MF;
    JPanel JP_Buttom;
    JPanel Panel_left;
    JScrollPane scrollPane;
    JCheckBox[] checkbox;
    Output(Main_Frame parent){
    MF = parent;
    Point pos;
    pos = MF.getLocationOnScreen();
    Dimension windowSize = MF.getSize();
    int x= windowSize.width+pos.x;
    int y = pos.y;
    setup_comp();
    String[] name = {"AAA","BBB","CCC","DDD","EEE"};
    gen_box(5,name);
    this.add(JP_Buttom);
    this.setSize(300,300);
    this.setLocation(x,y);
    this.setVisible(true);
    this.addWindowListener( new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    setVisible(false);    
                    MF.toolbarBTN.output = null;
                }
            }
        );
    }
    void setup_comp(){
    JP_Buttom = new JPanel(); 
    Panel_left = new JPanel(); 
    scrollPane = new JScrollPane(Panel_left);
    Panel_left.setLayout(new BoxLayout( Panel_left, BoxLayout.PAGE_AXIS));
    scrollPane.setPreferredSize(new Dimension(250, 300));
    //scrollPane.setBounds(0, 0, this.getWidth(), this.getHeight());
    JP_Buttom.setLayout(new BorderLayout());
    JP_Buttom.add(Panel_left,BorderLayout.WEST);
    }
     void gen_box(int amount,String[] content){
     checkbox = new JCheckBox[amount];
     
     for(int i=0;i<amount;i++){
     checkbox[i] = new JCheckBox(content[i]);    
     //checkbox[i].setText(content[i]);
     Panel_left.add(checkbox[i]);
     
     }
     
     }
    void showoption(){
    
    }
}
