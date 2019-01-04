package superpainter;
import java.util.*;
import java.text.*;
/**
 SuperPainter -- JAVA 軟體工程實作
 */


public class SuperPainter {
    
    //自動日期版本設定
    static boolean Auto_date_State = false;
    //設定標題及手動版本號
    static String  APPVERSION_static = "20190104 build 2037";
    static String Title = "SuperPrinter";
    //
    
    public static void main(String[] args) {
       
       
       if(Auto_date_State == true){       
       Date Date = new Date( );
       SimpleDateFormat date = new SimpleDateFormat ("yyyyMMdd");
       SimpleDateFormat time = new SimpleDateFormat ("hhmm");
       String APPVERSION= date.format(Date) + "build" + time.format(Date);
       new Main_Frame(APPVERSION,Title);     
       }else if(Auto_date_State == false){          
       new Main_Frame(APPVERSION_static,Title);      
       } 
       
    }
    
    
    
}
