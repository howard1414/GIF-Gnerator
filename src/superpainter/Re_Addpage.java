/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author HALUNA
 */
public class Re_Addpage extends JFrame {
    Re_Addpage(Main_Frame MF){
        super();
        this.setSize(180,110);
        this.setLocation(300, 300);
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("新建頁面");
        JButton check = new JButton("確定");
        JButton fail = new JButton("取消");
        JLabel LB1 = new JLabel("是否要放棄當前繪畫進度?");
        check.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        MF.Main_Drawing_space.readdpage();
                        dispose();
                    }
                }
        );
        fail.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        dispose();
                    }
                }
        );
        
        this.add(check);
        this.add(fail);
        this.add(LB1);
        LB1.setBounds(13,-30, 200, 100);
        check.setBounds(10, 40, 60, 20);
        fail.setBounds(90, 40, 60, 20);
        
        
    }
}
