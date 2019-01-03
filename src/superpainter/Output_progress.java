/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Howard
 */
public class Output_progress extends Frame {
    JProgressBar progressBar;
    int vals=0;
    Main_Frame MF;
    Output_progress(int size,Main_Frame parent){
    Point pos;
    pos = parent.toolbarBTN.output.getLocationOnScreen();
    Dimension windowSize = parent.toolbarBTN.output.getSize();
    int x= windowSize.width+pos.x;
    int y = pos.y;
    progressBar = new JProgressBar(0, size);
     progressBar.setValue(0);
    JLabel LB = new JLabel("進度:");
    JPanel JP = new JPanel();
    
    JP.setLayout(new GridLayout(0,2));
    JP.add(LB);
    JP.add(progressBar);
    this.add(JP);
    this.setSize(300,100);
    this.setLocation(x,y);
    this.setVisible(true);
    }
    public void add_progress(int adds){
     vals = progressBar.getValue()+adds;   
     progressBar.setValue(vals);
    }
}
