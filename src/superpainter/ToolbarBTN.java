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
    JButton saveBTN,LoadBTN,OptBTN,lineBTN,pencilBTN,circleBTN,closeBTN,chang3d,recoveryBTN,rectBTN,AddnewPG;
    JButton pencilall,fileall,patternall;
    JFrame pen,file,pat;
    int fristtadd = 0;
    public boolean close_event = false;
    final static JPanel Panel_Button = new JPanel();
    ToolbarBTN(Main_Frame MF){
        super();
        // 統合按鈕
        pencilall = new JButton("  鉛筆工具  ");
        fileall = new JButton("  檔案存取  ");
        patternall = new JButton("  圖形工具  ");
        //
        AddnewPG = new JButton("  新建頁面  ");
        saveBTN = new JButton("  存檔  ");
        LoadBTN = new JButton("  讀檔  ");
        OptBTN = new JButton("  輸出  ");
        pencilBTN = new JButton("  畫筆  ");
        rectBTN = new JButton("  矩形  ");
        circleBTN = new JButton("  圓  ");
        lineBTN = new JButton("  直線  ");
        chang3d = new JButton(" 3D切換 ");       
        closeBTN = new JButton(" << ");
        recoveryBTN = new JButton(" 復原 ");
        //設定外觀
        patternall.setFont(new Font("新細明體", Font.BOLD, 15));
        patternall.setBackground(new Color(0xFFBB00));
        patternall.setForeground(Color.white);
        patternall.setUI(new UI_Template());
        fileall.setFont(new Font("新細明體", Font.BOLD, 15));
        fileall.setBackground(new Color(0xFFBB00));
        fileall.setForeground(Color.white);
        fileall.setUI(new UI_Template());
        pencilall.setFont(new Font("新細明體", Font.BOLD, 15));
        pencilall.setBackground(new Color(0xFFBB00));
        pencilall.setForeground(Color.white);
        pencilall.setUI(new UI_Template());
        AddnewPG.setFont(new Font("新細明體", Font.BOLD, 15));
        AddnewPG.setBackground(new Color(0xFFBB00));
        AddnewPG.setForeground(Color.white);
        AddnewPG.setUI(new UI_Template());
        chang3d.setFont(new Font("新細明體", Font.BOLD, 15));
        chang3d.setBackground(new Color(0xFFBB00));
        chang3d.setForeground(Color.white);
        chang3d.setUI(new UI_Template());
        rectBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        rectBTN.setBackground(new Color(0xFFBB00));
        rectBTN.setForeground(Color.white);
        rectBTN.setUI(new UI_Template());
        lineBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        lineBTN.setBackground(new Color(0xFFBB00));
        lineBTN.setForeground(Color.white);
        lineBTN.setUI(new UI_Template());
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
        pencilBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        pencilBTN.setBackground(new Color(0xFFBB00));
        pencilBTN.setForeground(Color.white);
        pencilBTN.setUI(new UI_Template());
        recoveryBTN.setFont(new Font("新細明體", Font.BOLD, 15));
        recoveryBTN.setBackground(new Color(0xFFBB00));
        recoveryBTN.setForeground(Color.white);
        recoveryBTN.setUI(new UI_Template());
        
        //設定pannel layout
        Panel_Button.setLayout(new GridLayout(10,1,10,10));
        Panel_Button.setBackground(new Color(0x8e8e8e));
        //新增到panel上
        Panel_Button.add(AddnewPG);
        Panel_Button.add(fileall);
        Panel_Button.add(pencilall);
        Panel_Button.add(patternall);
        Panel_Button.add(recoveryBTN);
        Panel_Button.add(closeBTN);
        //統整按鈕視窗設計
         //檔案
        file = new JFrame();
        file.setLayout(new GridLayout(1,3,10,10));
        file.setLocation(208,100);
        file.setSize(330,100);
        file.setTitle("檔案存取");
        file.setBackground(new Color( 50 , 50  ,50));
        file.add(LoadBTN);
        file.add(saveBTN);
        file.add(OptBTN);
         //鉛筆工具
        pen = new JFrame();
        pen.setLayout(new GridLayout(1,2,10,10));
        pen.setLocation(208,150);
        pen.setSize(220,100);
        pen.setTitle("鉛筆工具");
        pen.setBackground(new Color( 50 , 50  ,50));
        pen.add(pencilBTN);
        pen.add(lineBTN);
         //圖形
        pat = new JFrame();
        pat.setLayout(new GridLayout(1,2,10,10));
        pat.setLocation(208,200);
        pat.setSize(220,100);
        pat.setTitle("檔案存取");
        pat.setBackground(new Color( 50 , 50  ,50));
        pat.add(circleBTN);
        pat.add(rectBTN);
        //按鍵動作
        AddnewPG.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        if(fristtadd != 1)
                        {
                        MF.add_Panel_Main();
                        MF.Messgebar.setLB("新頁面以建立");
                        fristtadd = 1;
                        }
                        else
                        {
                        MF.Messgebar.setLB("已存在葉面");
                        }
                    }
                }
        );
        pencilall.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        pen.setVisible(true);
                        file.setVisible(false);
                        pat.setVisible(false);
                    }
                }
        );
        fileall.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        file.setVisible(true);
                        pen.setVisible(false);
                        pat.setVisible(false);
                    }
                }
        );
        patternall.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        pat.setVisible(true);
                        file.setVisible(false);
                        pen.setVisible(false);
                    }
                }
        );
        closeBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        set_pannel_visible(false);
                        close_event = true;                     
                    }
                }
        );
        LoadBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        MF.Messgebar.setLB("讀取檔案");
                        file.setVisible(false);
                    }
                }
        );
        saveBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         MF.Messgebar.setLB("儲存檔案");;
                         file.setVisible(false);
                    }
                }
        );
        OptBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {    
                         MF.Messgebar.setLB("輸出");
                         file.setVisible(false);
                    }
                }
        );
        pencilBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        MF.Messgebar.setLB("畫筆工具啟用中");
                        MF.Main_Drawing_space.status=Status.drawpencil;
                        pen.setVisible(false);
                    }
                }
        );
        lineBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         MF.Messgebar.setLB("畫線工具啟用中");
                         MF.Main_Drawing_space.status=Status.drawline;
                         pen.setVisible(false);
                    }
                }
        );
        circleBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         MF.Messgebar.setLB("畫圓工具啟用中");
                         MF.Main_Drawing_space.status=Status.drawOval;
                         pat.setVisible(false);
                    }
                }
        );
        rectBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         MF.Messgebar.setLB("矩形工具啟用中");
                         MF.Main_Drawing_space.status=Status.drawRect;
                         pat.setVisible(false);
                    }
                }
        );
        recoveryBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        int temp ;
                        temp = MF.Main_Drawing_space.recovery();
                        if(temp == 1 ){
                            MF.Messgebar.setLB("返回上一步驟");
                        }
                        else
                        {
                            MF.Messgebar.setLB("已是最原始步驟");
                        }
                        
                    }    
                }    
        );
         //Panel_Button.pack();
    }
    public void set_pannel_visible(boolean state){
        Panel_Button.setVisible(state);
    }
}