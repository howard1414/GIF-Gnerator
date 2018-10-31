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



public class ToolbarBTN extends JPanel{
    JButton saveBTN,LoadBTN,OptBTN,lineBTN,circleBTN,closeBTN,setBTN;
    final static JPanel Panel_Button = new JPanel();
    BTN_Option BTN_Option = new BTN_Option();
    ToolbarBTN(Messgebar Messgebar){
        super();
        saveBTN = new JButton("  存檔  ");
        LoadBTN = new JButton("  讀檔  ");
        OptBTN = new JButton("  輸出  ");
        circleBTN = new JButton("  圓  ");
        lineBTN = new JButton("  線  ");
        closeBTN = new JButton("<<");
        setBTN = new JButton("  選項  ");
        //設定外觀
        lineBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        lineBTN.setBackground(new Color(0xFFBB00));
        lineBTN.setForeground(Color.white);
        lineBTN.setUI(new UI_Template());
        setBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        setBTN.setBackground(new Color(0xFFBB00));
        setBTN.setForeground(Color.white);
        setBTN.setUI(new UI_Template());
        LoadBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        LoadBTN.setBackground(new Color(0xFFBB00));
        LoadBTN.setForeground(Color.white);
        LoadBTN.setUI(new UI_Template());
        saveBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        saveBTN.setBackground(new Color(0xFFBB00));
        saveBTN.setForeground(Color.white);
        saveBTN.setUI(new UI_Template());
        OptBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        OptBTN.setBackground(new Color(0xFFBB00));
        OptBTN.setForeground(Color.white);
        OptBTN.setUI(new UI_Template());
        circleBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        circleBTN.setBackground(new Color(0xFFBB00));
        circleBTN.setForeground(Color.white);
        circleBTN.setUI(new UI_Template());
        closeBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        closeBTN.setBackground(new Color(0xFFBB00));
        closeBTN.setForeground(Color.white);
        closeBTN.setUI(new UI_Template());
        //設定pannel layout
        Panel_Button.setLayout(new GridLayout(10,1,10,10));
        Panel_Button.setBackground(new Color(0x8e8e8e));
        //新增到panel上
        Panel_Button.add(LoadBTN);
        Panel_Button.add(saveBTN);
        Panel_Button.add(OptBTN);
        Panel_Button.add(lineBTN);
        Panel_Button.add(circleBTN); 
        Panel_Button.add(setBTN);
        Panel_Button.add(closeBTN);
        //設定建置
        
        //按鍵動作
        closeBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        set_pannel_visible(false);
                    }
                }
        );
        setBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        setup();
                        Messgebar.setLB("設置");
                    }
                }
        );    
        

        LoadBTN.addMouseListener(

                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        Messgebar.setLB("讀取檔案");
                        
                    }
                }
        );
        


        saveBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         Messgebar.setLB("儲存檔案");
                    }
                }
        );
        OptBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         Messgebar.setLB("輸出");
                    }
                }
        );
        lineBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         Messgebar.setLB("畫線工具啟用中");
                    }
                }
        );
        circleBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         Messgebar.setLB("畫圓工具啟用中");
                    }
                }
        );
         //Panel_Button.pack();

    }
    public void set_pannel_visible(boolean state){
        Panel_Button.setVisible(state);
    }
    public void setup(){
        BTN_Option.setVisible(true);
    }
}