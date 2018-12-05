/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

/**
 *
 * @author User
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Palette_Frame extends JFrame {
    int r,g,b ;
    
    Palette_Frame(Main_Frame MF){
        super();
        JPanel PF = new JPanel();
        this.setLocation(200, 150);
        this.setSize(300, 200);
        //this.setLayout(null);
        this.setBackground(new Color(50,50,50));
        this.setTitle("調色盤");
        this.add(PF);
        PF.setLayout(null);
        PF.setBackground(new Color(50,50,50));
        JButton check = new JButton("確認");
        JButton fail = new JButton("取消");
        check.setBounds(150,130 , 60,20);
        fail.setBounds(220, 130, 60, 20);
        r = MF.Main_Drawing_space.color.getRed();
        g =  MF.Main_Drawing_space.color.getGreen();
        b = MF.Main_Drawing_space.color.getBlue();
        
        JScrollBar redSCB = new JScrollBar(Scrollbar.HORIZONTAL);
        JScrollBar greenSCB = new JScrollBar(Scrollbar.HORIZONTAL);
        JScrollBar blueSCB = new JScrollBar(Scrollbar.HORIZONTAL);
        redSCB.setBounds(60, 15, 200, 20);
        greenSCB.setBounds(60, 45, 200, 20);
        blueSCB.setBounds(60, 75, 200, 20);
    
        
        redSCB.setMaximum(265);
        redSCB.setMinimum(0);
        redSCB.setValue(r);
        greenSCB.setMaximum(265);
        greenSCB.setMinimum(0);
        greenSCB.setValue(g);
        blueSCB.setMaximum(265);
        blueSCB.setMinimum(0);
        blueSCB.setValue(b);
        
        JLabel redLB = new JLabel("紅色");
        JLabel greenLB = new JLabel("綠色");
        JLabel blueLB = new JLabel("藍色");
        JLabel colorLB = new JLabel("  ");
        
        redLB.setForeground(Color.red);
        redLB.setBounds(20, 15, 40, 20);
        greenLB.setForeground(Color.green);
        greenLB.setBounds(20, 45, 40, 20);
        blueLB.setForeground(new Color(100,100,255));
        blueLB.setBounds(20, 75, 40, 20);
        
        colorLB.setOpaque(true);
        colorLB.setBounds(20, 110, 120, 40);
        colorLB.setBackground(MF.Main_Drawing_space.color);
        
        //卷軸滾動事件
        redSCB.addAdjustmentListener(
                new AdjustmentListener(){
                    public void adjustmentValueChanged(AdjustmentEvent e){
                        r = redSCB.getValue();
                        g = greenSCB.getValue();
                        b = blueSCB.getValue();
                        colorLB.setBackground(new Color(r,g,b));
                        colorLB.updateUI();
            }
        });
        greenSCB.addAdjustmentListener(
                new AdjustmentListener(){
                    public void adjustmentValueChanged(AdjustmentEvent e){
                        r = redSCB.getValue();
                        g = greenSCB.getValue();
                        b = blueSCB.getValue();
                        colorLB.setBackground(new Color(r,g,b));
                        colorLB.updateUI();
            }
        });
        blueSCB.addAdjustmentListener(
                new AdjustmentListener(){
                    public void adjustmentValueChanged(AdjustmentEvent e){
                        r = redSCB.getValue();
                        g = greenSCB.getValue();
                        b = blueSCB.getValue();
                        colorLB.setBackground(new Color(r,g,b));
                        colorLB.updateUI();
            }
        });
        //按鈕事件
        check.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        MF.Main_Drawing_space.color = new Color(r,g,b);
                        dispose();
                    }
                }
        );
        check.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        dispose();
                    }
                }
        );
        
        PF.add(colorLB);
        PF.add(greenLB);
        PF.add(blueLB);
        PF.add(redLB);
        PF.add(check);
        PF.add(fail);
        PF.add(redSCB);
        PF.add(greenSCB);
        PF.add(blueSCB);
        
        
        this.setVisible(true);
    }
}
