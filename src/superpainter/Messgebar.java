/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

/**
 *
 *訊息欄位設計
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
public class Messgebar extends JPanel{
    JLabel LB1;
    Messgebar(){
        super();
        LB1 = new JLabel("待機中....");
        LB1.setForeground(Color.WHITE);
        LB1.setHorizontalAlignment(SwingConstants.RIGHT);
        this.setBackground(new Color(0x778899));
        this.add(LB1,BorderLayout.EAST);
    }
    public void setLB(String s){
    LB1.setText(s);
    } 
    
}
