package superpainter;
import java.util.*;
import java.text.*;
/**
 SuperPainter -- JAVA 軟體工程實作
 */


public class SuperPainter {
    
    
    public static void main(String[] args) {
       Date Date = new Date( );
       SimpleDateFormat date = new SimpleDateFormat ("yyyyMMdd");
       SimpleDateFormat time = new SimpleDateFormat ("hhmm");
       String APPVERSION= date.format(Date) + "build" + time.format(Date);
       String Title = "SuperPrinter";
       Main_Frame MF = new Main_Frame(APPVERSION,Title);
       
    }
    
    
    
}
