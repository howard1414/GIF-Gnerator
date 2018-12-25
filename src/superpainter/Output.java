/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author lv379
 */
public class Output extends Frame {
    Vector<Line> line;
    Main_Frame MF;
    JPanel JP_Buttom;
    JPanel Panel_left;
    JScrollPane scrollPane;
    JCheckBox[] checkbox;
    String[] name;
    Stack ss ;
    Output(Main_Frame parent){
    MF = parent;
    ss = MF.Main_Drawing_space.re;
    Point pos;
    pos = MF.getLocationOnScreen();
    Dimension windowSize = MF.getSize();
    int x= windowSize.width+pos.x;
    int y = pos.y;
    setup_comp();
    //show_test();
    if(count_stack()>=0){
    name = new String[count_stack()];
    }
    for(int i=0;i<count_stack();i++){
    name[i] = ss.get(i).toString();
    }   
    gen_box(count_stack(),name);
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
     checkbox[i] = new JCheckBox("步驟"+i+" : "+content[i]);    
     Panel_left.add(checkbox[i]);
     setup_event(checkbox[i],i);
     }
     
     }
     void setup_event(JCheckBox JC,int ptr){
       JC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                MF.Main_Drawing_space.history_replay(ptr+1);
            }
        });
     }
     
    void show_step(int step){
    
    }
    void show_test(){
    int count=0;
    line = MF.Main_Drawing_space.request_line();
    for(Line ll : line){
    System.out.println(ll.lastpoint.x+" "+ll.lastpoint.y+"\n");
    count++;
    }
    System.out.println("size= "+count+"\n");
    
    Stack ss = MF.Main_Drawing_space.re ;
    for(Object s:ss){
    System.out.println("ss= "+s+"\n");
    }
    }
    int count_stack(){
    int count=0;
   
    for(Object s:ss){
    System.out.println("ss= "+s+"\n");
    count++;
    }
    return count;
    }
}
