/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;

/**
 *
 * @author lv379
 */
public class BTN_Option extends JFrame{
    JPanel Panel_option = new JPanel();
    String colors_name_zhtw[]={"白色","深灰色","淺灰色","淡黃色","淡藍色","淡紫色"};
    String colors_name_code[]={"FFFFFF","666666","AAAAAA","FFEE99","CCEEFF","E8CCFF"};
    
    BTN_Option(){
        super();
        JLabel TEXT_Label = new JLabel("文字顏色");
        JLabel BG_Label = new JLabel("背景顏色");
        JLabel BTN_Label = new JLabel("按鈕顏色");
        JButton Yes_BTN = new JButton("確定");
        JButton NO_BTN = new JButton("取消");
        JComboBox Text_Color= new JComboBox(colors_name_zhtw);
        JComboBox BG_Color= new JComboBox(colors_name_zhtw);
        JComboBox BTN_Color= new JComboBox(colors_name_zhtw);
        TEXT_Label.setFont(new Font("新細明體", Font.BOLD, 15));
        BG_Label.setFont(new Font("新細明體", Font.BOLD, 15));
        BTN_Label.setFont(new Font("新細明體", Font.BOLD, 15));
        Text_Color.setFont(new Font("新細明體", Font.BOLD, 15));
        BG_Color.setFont(new Font("新細明體", Font.BOLD, 15));
        BTN_Color.setFont(new Font("新細明體", Font.BOLD, 15));
        Yes_BTN.setFont(new Font("新細明體", Font.BOLD, 15));
        NO_BTN.setFont(new Font("新細明體", Font.BOLD, 15));
        TEXT_Label.setBackground(new Color(0xFFBB00));
        TEXT_Label.setForeground(Color.white);
        BG_Label.setBackground(new Color(0xFFBB00));
        BG_Label.setForeground(Color.white);
        BTN_Label.setBackground(new Color(0xFFBB00));
        BTN_Label.setForeground(Color.white);
        Text_Color.setBackground(new Color(0xFFBB00));
        Text_Color.setForeground(Color.white);
        BG_Color.setBackground(new Color(0xFFBB00));
        BG_Color.setForeground(Color.white);
        BTN_Color.setBackground(new Color(0xFFBB00));
        BTN_Color.setForeground(Color.white);
        Yes_BTN.setUI(new UI_Template());
        NO_BTN.setUI(new UI_Template());
        Panel_option.setLayout(new GridLayout(3,5));     
        Panel_option.add(TEXT_Label);
        Panel_option.add(BG_Label);
        Panel_option.add(BTN_Label);
        Panel_option.add(Text_Color);
        Panel_option.add(BG_Color);
        Panel_option.add(BTN_Color);
        Panel_option.add(Yes_BTN);
        Panel_option.add(NO_BTN);
        this.setSize(1000,1000);
        this.setLocation(200, 200);
        this.add(Panel_option);
        this.pack();
        System.out.println("build...");
        this.setVisible(false);      
    }

    
}
