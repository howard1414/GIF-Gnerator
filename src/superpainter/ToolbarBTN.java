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
    JButton saveBTN,loadBTN,optBTN,lineBTN,pencilBTN,circleBTN,closeBTN,chang3d,recoveryBTN,rectBTN,addnewPG,palette;
    JButton pencilall,fileall,patternall,backup;
    Button_Status BS;
    Boolean toolbarVisible=true;
    public final static JPanel Panel_Button = new JPanel();
    ToolbarBTN(Main_Frame MF){
        super();
        BS = BS.initial;
        //畫筆類
        pencilall = new JButton("  鉛筆工具  ");
        pencilBTN = new JButton("     畫筆     ");
        lineBTN = new JButton("   直線   ");
        //檔案類
        fileall = new JButton("  檔案存取  ");
        saveBTN = new JButton("     存檔     ");
        loadBTN = new JButton("   讀檔   ");
        optBTN = new JButton("   輸出   ");
        //圖形類
        patternall = new JButton("  圖形工具  ");
        rectBTN = new JButton("     矩形     ");
        circleBTN = new JButton("      圓      ");
        //調色盤
        palette = new JButton(" 調色盤 ");
        //功能類
        backup = new JButton(" <= ");
        addnewPG = new JButton("  新建頁面  ");
        recoveryBTN = new JButton("   復原   ");
        chang3d = new JButton(" 3D切換 ");       
        closeBTN = new JButton("  <<  ");
       
        //設定外觀
        set_buttonUI(backup);
        set_buttonUI(palette);
        set_buttonUI(patternall);
        set_buttonUI(fileall);
        set_buttonUI(pencilall);
        set_buttonUI(addnewPG);
        set_buttonUI(chang3d);
        set_buttonUI(rectBTN);
        set_buttonUI(lineBTN);
        set_buttonUI(loadBTN);
        set_buttonUI(saveBTN);
        set_buttonUI(optBTN);
        set_buttonUI(circleBTN);
        set_buttonUI(closeBTN);
        set_buttonUI(pencilBTN);
        set_buttonUI(recoveryBTN);
        //設定pannel layout
        Panel_Button.setLayout(new GridLayout(10,1,10,10));
        Panel_Button.setBackground(new Color(0x8e8e8e));
        //新增到panel上(初始化
        chang_button();
        //按鍵動作*/
        palette.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        Palette_Frame pf = new Palette_Frame(MF);
                    }
                }
        );
        addnewPG.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {    
                        BS = BS.painting;
                        chang_button();
                        if(MF.Main_Drawing_space!=null){
                             Re_Addpage RA = new Re_Addpage(MF);
                        }
                        else
                        {   
                            MF.addpage();
                            MF.add_Panel_Main();
                            MF.Messgebar.setLB("新頁面以建立");
                        }
                    }
                }
        );
        pencilall.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        BS = BS.brush;
                        chang_button();
                    }
                }
        );
        fileall.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                       BS = BS.file;
                       chang_button();
                    }
                }
        );
        patternall.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                        BS= BS.pattern;
                        chang_button();
                    }
                }
        );
        backup.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        BS = BS.painting;
                        chang_button();
                    }
                }
        );
        closeBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        set_pannel_visible(false);
                        if(MF.Main_Drawing_space!=null){
                          MF.JSC.setBounds(0, 0,MF.getWidth()-16,MF.getHeight()-MF.Messgebar.getHeight()-38);
                          MF.Panel_Main.updateUI();
                        }
                    }
                }
        );
        loadBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {                       
                        MF.Messgebar.setLB("讀取檔案");
                        try{
                        MF.Main_Drawing_space.loadimage();
                        }catch(Exception ex){
                             System.out.println("Load image Failed!");
                        }
                    }
                }
        );
        saveBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                         MF.Messgebar.setLB("儲存檔案");;
                    }
                }
        );
        optBTN.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {    
                         MF.Messgebar.setLB("輸出");
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
    }
    public void set_pannel_visible(boolean state){
        Panel_Button.setVisible(state);
        toolbarVisible = state;
    }
    public void chang_button(){
        switch(BS){
            case initial:
                Panel_Button.removeAll();
                Panel_Button.add(addnewPG);
                Panel_Button.add(fileall);
                Panel_Button.add(closeBTN);
                Panel_Button.updateUI();
            break;
            case painting:
                Panel_Button.removeAll();
                Panel_Button.add(addnewPG);
                Panel_Button.add(fileall);
                Panel_Button.add(pencilall);
                Panel_Button.add(patternall);
                Panel_Button.add(palette);          
                Panel_Button.add(recoveryBTN);
                Panel_Button.add(closeBTN);
                Panel_Button.updateUI();
            break;
            case file:
                 Panel_Button.removeAll();
                 Panel_Button.add(loadBTN);
                 Panel_Button.add(saveBTN);
                 Panel_Button.add(optBTN);
                 Panel_Button.add(backup);
                 Panel_Button.updateUI();
            break;
            case pattern:
                Panel_Button.removeAll();
                Panel_Button.add(circleBTN); 
                Panel_Button.add(rectBTN); 
                Panel_Button.updateUI();
                Panel_Button.add(backup);
            break;
            case brush:
                Panel_Button.removeAll();
                Panel_Button.add(pencilBTN);
                Panel_Button.add(lineBTN);
                Panel_Button.updateUI();
                Panel_Button.add(backup);
            break;
        }
    }
    public void set_buttonUI(JButton btn){
        btn.setFont(new Font("新細明體", Font.BOLD, 15));
        btn.setBackground(new Color(0xFFBB00));
        btn.setForeground(Color.white);
        btn.setUI(new UI_Template());        
    }

    
}