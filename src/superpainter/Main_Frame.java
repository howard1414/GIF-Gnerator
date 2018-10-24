
package superpainter;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author lv379
 */
public class Main_Frame extends Frame {
    
    Main_Frame() {
        this.setSize(600,600);
        this.setLocation(200, 200);
        this.setVisible(true);
        this.addWindowListener( new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                        System.exit(0);
                }
            }
        );
}
}
