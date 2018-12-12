/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

/**
 *
 * @author HALUNA
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Line_width_Frame extends JFrame{
    int lineW=15;
    float temp;
   
    Line_width_Frame(Main_Frame MF){
        super();
        this.setLocation(200, 150);
        this.setSize(250, 180);
        this.setTitle("線條粗細");
        JPanel PF = new JPanel();
        JButton check = new JButton("確認");
        JButton fail = new JButton("取消");
        JScrollBar lw = new JScrollBar(Scrollbar.HORIZONTAL);
        check.setBounds(90,110 , 60,20);
        fail.setBounds(160, 110, 60, 20);
        PF.setLayout(null);
        PF.setBackground(new Color(50,50,50));
        test linebox = new test(MF);
        linebox.setBounds(20, 20, 200, 50);
        lw.setValue((int)MF.Main_Drawing_space.BasicStroke);
        lw.setBounds(20, 80, 200,20);
        lw.setMaximum(50);
        lw.setMinimum(1);
        lw.addAdjustmentListener(
                new AdjustmentListener(){
                    @Override
                    public void adjustmentValueChanged(AdjustmentEvent e)
                    {  
                        temp =lw.getValue();
                        linebox.lsize = temp;
                        linebox.repaint();
                    }
        });
        check.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                       MF.Main_Drawing_space.BasicStroke = temp;
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
        PF.add(fail);
        PF.add(check);
        PF.add(linebox);
        PF.add(lw);
        this.add(PF);
        this.setVisible(true);
    }
}
class test extends Panel
{   int w,h;
    float lsize;
    Color color;
    Main_Frame MF;
    test(Main_Frame MF){
        super();
        MF = MF;
        lsize = MF.Main_Drawing_space.BasicStroke;
        color = MF.Main_Drawing_space.color;
        this.setBackground(Color.white);
    }
     @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(lsize));
        g2d.drawLine(0,this.getHeight()/2 ,this.getWidth() , this.getHeight()/2);
    }
}