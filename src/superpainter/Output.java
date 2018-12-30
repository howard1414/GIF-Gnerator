/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpainter;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author lv379
 */
public class Output extends Frame {
    Vector<Line> line;
    Main_Frame MF;
    JPanel JP_Buttom;
    JPanel Panel_left;
    JPanel Panel_Right;
    JScrollPane scrollPane;
    JButton button_save;
    JButton button_setting;
    JButton button_exit;
    JCheckBox[] checkbox;
    String[] name;
    Stack ss ;
    int check_arr[];
    Image img_buffers[];
    Output(Main_Frame parent){
    
    MF = parent;
    ss = MF.Main_Drawing_space.re;
    Point pos;
    pos = MF.getLocationOnScreen();
    Dimension windowSize = MF.getSize();
    int x= windowSize.width+pos.x;
    int y = pos.y;
    setup_comp();
    show_test();
    if(count_stack()>=0){
    name = new String[count_stack()];
    }
    for(int i=0;i<count_stack();i++){
    name[i] = ss.get(i).toString();
    }   
    gen_box(count_stack(),name);
    this.add(JP_Buttom);
    this.setSize(300,300);
    this.setLocation(x,y);
    this.pack();
    this.setTitle("輸出");
    this.setVisible(true);
    this.addWindowListener( new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    setVisible(false);    
                    MF.toolbarBTN.output = null;
                }
            }
        );
    
   new Thread(new Runnable(){
   @Override
        public void run(){
          test_drawing_speed();  
        }
    }).start();
     
        
    
    }
    void setup_comp(){
    JP_Buttom = new JPanel(); 
    Panel_left = new JPanel();
    Panel_Right = new JPanel();
    button_save = new JButton("輸出");
    button_setting= new JButton("設定");
    button_exit= new JButton("取消");
    set_buttonUI(button_save);
    set_buttonUI(button_setting);
    set_buttonUI(button_exit);
    scrollPane = new JScrollPane(Panel_left);
    Panel_Right.setLayout(new GridLayout(3, 1));
    Panel_left.setLayout(new BoxLayout(Panel_left, BoxLayout.PAGE_AXIS));
    scrollPane.setPreferredSize(new Dimension(200, 300));
    //scrollPane.setBounds(0, 0, this.getWidth(), this.getHeight());
    JP_Buttom.setLayout(new BorderLayout());
    Panel_Right.add(button_save);
    Panel_Right.add(button_setting);
    Panel_Right.add(button_exit);
    JP_Buttom.add(scrollPane,BorderLayout.WEST);
    JP_Buttom.add(Panel_Right,BorderLayout.EAST);
    
    button_save.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                       gen_pic();                      
                    }    
                }    
        );
    button_setting.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                                          
                    }    
                }    
        );
    button_exit.addMouseListener(
                new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {   
                    setVisible(false);    
                    MF.toolbarBTN.output = null;                
                    }    
                }    
        );
    }
     void gen_box(int amount,String[] content){
     checkbox = new JCheckBox[amount];  
     for(int i=0;i<amount;i++){
         if(content[i].equals("1")){
             content[i]="畫線";
         }else{
             content[i]="線段";
         }
     checkbox[i] = new JCheckBox("步驟"+(i+1)+" : "+content[i]);    
     Panel_left.add(checkbox[i]);
     setup_event(checkbox[i],i);
     }
     
     }
     void setup_event(JCheckBox JC,int ptr){
       JC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                MF.Main_Drawing_space.history_replay(ptr+1);
                //gen_pic(ptr);
            }
        });
     }
   void gen_pic(){
   new Thread(new Runnable(){
   @Override
        public void run(){
            int count_imgs=0;
            for(int i=0;i<count_stack();i++){
                if(checkbox[i].isSelected()){
                    gen_pic_count(i);
                    count_imgs++;
                }
            }
            gen_gif(count_imgs);
        }
    }).start();
    } 
    void gen_pic_count(int step){
    MF.Main_Drawing_space.history_replay(step+1);
    Image ibf = MF.Main_Drawing_space.request_Image();
    try{
        String filename = "c:\\TEST\\pic"+(step+1)+".PNG";
        ImageIO.write((RenderedImage) ibf, "PNG", new File(filename));
    }catch(Exception ex){
    
    }
    
    }
    void gen_gif(int img_count){
     System.out.println(img_count);   
    try{
    BufferedImage firstImage = ImageIO.read(new File("c:\\TEST\\pic1.PNG"));
    ImageOutputStream output = new FileImageOutputStream(new File("c:\\TEST\\opt.gif"));
    GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), 1000, true);
    writer.writeToSequence(firstImage);
    for(int i=1; i<img_count; i++) {
    BufferedImage nextImage = ImageIO.read(new File("c:\\TEST\\pic"+(i+1)+".PNG"));
    System.out.println(i);
    writer.writeToSequence(nextImage);
    }
    writer.close();
    output.close();
    }catch(Exception ex){
    
    }
    }
    void geneate_gif_buffer(Image imgbfr[]){
        check_directory();
        int count=0;
        for (Image img : imgbfr) {
           if(count ==0){
           gen_pic(img,count);
           }else if(count>=1){
           
           }
           count++;
        }
    }
    void gen_pic(Image ibf,int num){
    try{
        String filename = "\\temp"+(num+1)+".PNG";
        ImageIO.write((RenderedImage) ibf, "PNG", new File(filename));
    }catch(IOException ex){
    
    }
    
    }
    void check_directory(){
    File dir= new File("\\temp");
    if(!dir.exists()){
    try{
        dir.mkdir();
    }catch(SecurityException se){
        
    }        
    }
    }
    void check_to_buffer(){
        int amount=0;
        for(int i=0;i<count_stack();i++){
                if(checkbox[i].isSelected()){
                    amount++;               
                }
        }
        check_arr = new int[amount];
        img_buffers = new Image[amount];
        int j=0;
        for(int i=0;i<count_stack();i++){
                if(checkbox[i].isSelected()){
                    check_arr[j] = i;
                    MF.Main_Drawing_space.history_replay(i+1);
                    img_buffers[j] = MF.Main_Drawing_space.request_Image();
                    j++;
                }
        }
        
    }
    
    void show_test(){
    int count=0;
    line = MF.Main_Drawing_space.request_line();
    for(Line ll : line){
    System.out.println(ll.Pattern+" "+ll.stack+"\n");
    count++;
    }
    System.out.println("size= "+count+"\n");
    
    Stack ss = MF.Main_Drawing_space.re ;
    for(Object s:ss){
    System.out.println("ss= "+s+"\n");
    }
    }
    void test_drawing_speed(){
    line = MF.Main_Drawing_space.request_line();
    try{
    for(Line ll : line){
    MF.Main_Drawing_space.drawp(ll.firstpoint.x,ll.firstpoint.y,ll.lastpoint.x,ll.lastpoint.y);
    
    Thread.sleep(10);
    }
    }catch(InterruptedException exx){
    
    }
    
    
    //MF.Main_Drawing_space.history_replay(base+1);
    //MF.Main_Drawing_space.drawp(d_line.firstpoint.x,d_line.firstpoint.y,d_line.lastpoint.x,d_line.lastpoint.y);
    MF.Main_Drawing_space.temp = true;
    }
    int count_stack(){
    int count=0;
   
    for(Object s:ss){
    System.out.println("ss= "+s+"\n");
    count++;
    }
    return count;
    }
    public void set_buttonUI(JButton btn){
        btn.setPreferredSize(new Dimension(80, 50));
        btn.setFont(new Font("新細明體", Font.BOLD, 15));
        btn.setBackground(new Color(0xFFBB00));
        btn.setForeground(Color.white);
        btn.setUI(new UI_Template());        
    }
}


