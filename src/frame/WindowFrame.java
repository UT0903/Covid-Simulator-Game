package frame;

import javax.swing.*;
import java.util.List;

public class WindowFrame extends JFrame {
    public WindowFrame(){
        super("Virus Game");
        setSize(1080, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
//        for(JLayeredPane panel:panels){
//            layeredPane.add(panel, 0);
//        }
    }
}
