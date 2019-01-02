/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Howard
 */
public class Output_setting extends Frame {
    JPanel JP;
    JPanel panel_Check_btns;
    JPanel panel_interval;
    JPanel panel_delay;
    JButton button_ok;
    JButton button_cancel;
    JButton button_add_interval;
    JButton button_sub_interval;
    JButton button_add_delay;
    JButton button_sub_delay;
    JTextArea interval_text;
    JTextArea delay_text;
    int delay;
    int interval;
    Main_Frame MF;
    Output_setting(Main_Frame parent){
    Point pos;
    MF = parent;
    pos = parent.toolbarBTN.output.getLocationOnScreen();
    Dimension windowSize = parent.toolbarBTN.output.getSize();
    int x= windowSize.width+pos.x;
    int y = pos.y;
    this.setSize(100,100);
    this.setLocation(x,y);
    setup();
    this.add(JP);
    this.setVisible(true);
    this.pack();
    this.addWindowListener( new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    setVisible(false);    
                    
                }
            }
        );
    }
    void setup(){
    
    JP = new JPanel();
    JPanel panel_label_interval = new JPanel();
    JPanel panel_label_delay = new JPanel();
    panel_interval = new JPanel();
    panel_delay = new JPanel();
    panel_Check_btns = new JPanel();
    JLabel label_interval = new JLabel("間隔(每幀步數)");
    JLabel label_delay = new JLabel("速度(ms)");
    button_ok = new JButton("確定");
    button_cancel = new JButton("取消");
    button_add_interval = new JButton("+");
    button_sub_interval = new JButton("-");
    button_add_delay = new JButton("+");
    button_sub_delay = new JButton("-");
    interval_text = new  JTextArea(1,3);
    interval_text.setText("1");
    delay_text = new  JTextArea(1,3);
    delay_text.setText("1");
    JP.setLayout(new GridLayout(5,0));
    panel_label_interval.add(label_interval);
    panel_label_delay.add(label_delay);
    panel_Check_btns.setLayout(new GridLayout(0,2));
    panel_Check_btns.add(button_ok);
    panel_Check_btns.add(button_cancel);
    panel_interval.setLayout(new GridLayout(0,3));
    panel_interval.add(button_add_interval);
    panel_interval.add(interval_text);
    panel_interval.add(button_sub_interval);
    panel_delay.setLayout(new GridLayout(0,3));
    panel_delay.add(button_add_delay);
    panel_delay.add(delay_text);
    panel_delay.add(button_sub_delay);
    JP.add(panel_label_interval);
    JP.add(panel_interval);
    JP.add(panel_label_delay);
    JP.add(panel_delay);
    JP.add(panel_Check_btns);
    button_add_interval.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                       
                        interval_text.setText(Integer.toString(Integer.parseInt(interval_text.getText())+1));
                    }
                         
                }
        );
    button_sub_interval.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {                          
                        interval_text.setText(Integer.toString(Integer.parseInt(interval_text.getText())-1)); 
                    }
                         
                }
        );
    button_add_delay.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {    
                        delay_text.setText(Integer.toString(Integer.parseInt(delay_text.getText())+1)); 
                    }
                         
                }
        );
    button_sub_delay.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {    
                        delay_text.setText(Integer.toString(Integer.parseInt(delay_text.getText())-1)); 
                    }
                         
                }
        );
    button_ok.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        
                        delay = Integer.parseInt(delay_text.getText());
                        interval = Integer.parseInt(interval_text.getText());
                        if(delay<1 || interval<1){
                        JOptionPane.showMessageDialog(null,"數值不得小於1，請再次確認!");
                        }else{
                        MF.toolbarBTN.output.send_setting(delay,interval);
                        setVisible(false);
                        }
                    }
                         
                }
        );
    button_cancel.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        
                        interval_text.setText("1");
                        delay_text.setText("1");
                        setVisible(false);
                    }
                         
                }
        );
    
    }
    
    
}
