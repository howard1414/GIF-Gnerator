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
import javax.swing.JPanel;
public class toolbartBTN extends Panel{
    JButton lineBTN,circleBTN;
    toolbartBTN(){
        super();
        lineBTN = new JButton("線");
        circleBTN = new JButton("圓");
        this.add(lineBTN);
        this.add(circleBTN);        
    }
    
}
