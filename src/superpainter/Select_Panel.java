/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.*;

/**
 *
 * @author HALUNA
 */
public class Select_Panel extends JPanel{
    JLabel stateLB,heightLB,widthLB;
    JTextField TFH,TFW;
    Main_Frame MF;
    Boolean close_event=false;
    Status SST = Status.active;
    Select_Panel(Main_Frame MFS){
        super();
        MF =MFS;
        this.setLayout(null);
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(100,50));
        
        stateLB = new JLabel("畫布大小");
        heightLB = new JLabel("長");
        widthLB = new JLabel("寬");
        TFH = new JTextField();
        TFW = new JTextField();
        stateLB.setBounds(25, 50, 80, 30);
        heightLB.setBounds(10, 80, 80, 30);
        TFH.setBounds(10, 110, 80, 30);
        widthLB.setBounds(10, 160, 80, 30);
        TFW.setBounds(10, 190, 80,30);
        JButton JB1 = new JButton("更改");
        JButton close = new JButton(">");
        close.setBounds(0,25,25,25);
        close.setMargin(new Insets(0,0,0,0));
        close.setFont(new Font("新細明體", Font.BOLD,20));
        JB1.setBounds(10, 250, 80, 30);
        TFW.setText(Integer.toString(MF.Main_Drawing_space.getWidth()));
        TFH.setText(Integer.toString(MF.Main_Drawing_space.getHeight()));
        this.add(close);                
        this.add(TFH);
        this.add(TFW);
        this.add(heightLB);
        this.add(widthLB);
        this.add(stateLB);
        this.add(JB1);
        
        /****按鈕事件****/
        close.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                         if(close_event == false){
                            close_event = true;
                            Select_Panel.this.removeAll();
                            Select_Panel.this.add(close);
                            Select_Panel.this.setPreferredSize(new Dimension(25,25));
                            close.setText("<");
                            Select_Panel.this.updateUI();
                         }
                         else
                         {
                            close_event = false;
                            Select_Panel.this.removeAll();
                            Select_Panel.this.add(close);                
                            Select_Panel.this.add(TFH);
                            Select_Panel.this.add(TFW);
                            Select_Panel.this.add(heightLB);
                            Select_Panel.this.add(widthLB);
                            Select_Panel.this.add(stateLB);
                            Select_Panel.this.add(JB1);
                            Select_Panel.this.setPreferredSize(new Dimension(100,50));
                            close.setText(">");
                            Select_Panel.this.updateUI();
                         }
                    }    
                }    
        );
        JB1.addMouseListener(
            new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        Line l;
                        if(Select_Panel.this.Numornot() == true){
                                 System.out.println("drawimage TRUE");
                                int h = Integer.parseInt(TFH.getText());
                                int w = Integer.parseInt(TFW.getText());     
                                Graphics g=  MF.Main_Drawing_space.getGraphics();
                                Graphics2D g2d = (Graphics2D)g;
                                g2d.setColor(MF.Main_Drawing_space.color);
                                g2d.setStroke(new BasicStroke(MF.Main_Drawing_space.BS));
                                if(SST == Status.drawimage){
                                    l = MF.Main_Drawing_space.lines.get(MF.Main_Drawing_space.lines.size()-1);
                                    Point fp = new Point(l.firstpoint);
                                    Point lp = new Point(l.firstpoint.x+w,l.firstpoint.y+h);
                                    MF.Main_Drawing_space.p2 = new Point(lp);
                                    MF.Main_Drawing_space.lines.removeElementAt(MF.Main_Drawing_space.lines.size()-1);
                                    MF.Main_Drawing_space.repaint();
                                    g2d.drawImage(MF.Main_Drawing_space.img,fp.x,fp.y,w,h, MF.Main_Drawing_space);
                                    MF.Main_Drawing_space.lines.add(new Line(fp,lp,Pattern.Image,MF.Main_Drawing_space.img,MF.Main_Drawing_space.linecount.size()-1));
                                    MF.Main_Drawing_space.draw_out_line(fp,lp);
                      
                                }
                                else
                                {
                                    if(SST == Status.active || SST == Status.drawline || SST == Status.drawpencil){
                                        System.out.println("drawPanel");
                                        MF.size_btn.setBounds(w, h, 15, 15);
                                        MF.Panel_size.setPreferredSize(new Dimension(w+17, h+17));
                                        MF.Main_Drawing_space.setSize(w, h); 
                                        MF.Panel_size.repaint();
                                        MF.Panel_size.updateUI();
                                        MF.SPW = w;
                                        MF.SPH = h;
                                        change();
                                    }   
                                    else if(SST == Status.drawOval)
                                    {  
                                        l = MF.Main_Drawing_space.lines.get(MF.Main_Drawing_space.lines.size()-1);
                                        Point fp = new Point(l.firstpoint);
                                        Point lp = new Point(l.firstpoint.x+w,l.firstpoint.y+h);
                                        MF.Main_Drawing_space.p2 = new Point(lp);
                                        MF.Main_Drawing_space.lines.removeElementAt(MF.Main_Drawing_space.lines.size()-1);
                                        MF.Main_Drawing_space.repaint();
                                        g2d.drawOval(fp.x, fp.y, w, h);
                                        MF.Main_Drawing_space.lines.add(new Line(fp,lp,Pattern.Ovil,MF.Main_Drawing_space.color,MF.Main_Drawing_space.BS,MF.Main_Drawing_space.linecount.size()-1));
                                        System.out.println("drawOval");
                                        MF.Main_Drawing_space.draw_out_line(fp,lp);
                                    }
                                    else if(SST == Status.drawRect){
                                        l = MF.Main_Drawing_space.lines.get(MF.Main_Drawing_space.lines.size()-1);
                                        Point fp = new Point(l.firstpoint);
                                        Point lp = new Point(l.firstpoint.x+w,l.firstpoint.y+h);
                                        MF.Main_Drawing_space.p2 = new Point(lp);
                                        MF.Main_Drawing_space.lines.removeElementAt(MF.Main_Drawing_space.lines.size()-1);
                                        MF.Main_Drawing_space.repaint();
                                        g2d.drawRect(fp.x, fp.y, w, h);
                                        MF.Main_Drawing_space.lines.add(new Line(fp,lp,Pattern.Rect,MF.Main_Drawing_space.color,MF.Main_Drawing_space.BS,MF.Main_Drawing_space.linecount.size()-1));
                                        System.out.println("drawRect");
                                        MF.Main_Drawing_space.draw_out_line(fp,lp);
                                    }
                                }                    
                        }
                        else
                        {   
                            change();
                            System.out.println("drawimage FALSE");
                        }   
                    }         
                }
        );
    }
    
    void TF_update(){
        TFW.setText(Integer.toString(MF.Main_Drawing_space.getWidth()));
        TFH.setText(Integer.toString(MF.Main_Drawing_space.getHeight()));
    }
    public boolean Numornot(){
        try{
            Integer.parseInt(TFH.getText());
            Integer.parseInt(TFW.getText());
               return true;
              }catch(Exception e){
               return false;
              }
    }
    
    void change(){
        Status temp = MF.Main_Drawing_space.status;
        int w=0,h=0;
        Line l;
        if(temp == Status.select){
            Status savetemp = MF.Main_Drawing_space.SaveStatus;
            switch(savetemp){
                case drawRect:
                    stateLB.setText("矩形大小");
                    l = MF.Main_Drawing_space.lines.get(MF.Main_Drawing_space.lines.size()-1);
                    w =Math.abs( l.lastpoint.x - l.firstpoint.x);
                    h =Math.abs(l.lastpoint.y - l.firstpoint.y);
                    TFW.setText(Integer.toString(w));
                    TFH.setText(Integer.toString(h));
                    break;
                case drawOval:
                    stateLB.setText("圓形大小");
                    l = MF.Main_Drawing_space.lines.get(MF.Main_Drawing_space.lines.size()-1);
                    w =Math.abs( l.lastpoint.x - l.firstpoint.x);
                    h =Math.abs(l.lastpoint.y - l.firstpoint.y);
                    TFW.setText(Integer.toString(w));
                    TFH.setText(Integer.toString(h));
                    break;
                case drawimage:
                    stateLB.setText("圖片大小");
                    l = MF.Main_Drawing_space.lines.get(MF.Main_Drawing_space.lines.size()-1);
                    w =Math.abs( l.lastpoint.x - l.firstpoint.x);
                    h =Math.abs(l.lastpoint.y - l.firstpoint.y);
                    TFW.setText(Integer.toString(w));
                    TFH.setText(Integer.toString(h));
                    break;
            }
            SST = savetemp;
        }
        else
        {
            switch(temp){
                case active:
                case drawline:
                case drawpencil:
                    stateLB.setText("畫布大小");
                    TFW.setText(Integer.toString(MF.Main_Drawing_space.getWidth()));
                    TFH.setText(Integer.toString(MF.Main_Drawing_space.getHeight()));
                    break;  
            }
            SST = temp;
        }    
    }
}
