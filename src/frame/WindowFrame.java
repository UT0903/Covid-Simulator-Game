package frame;

import javax.swing.*;
import java.util.List;

public class WindowFrame extends JFrame {
    private JLayeredPane layeredPane;
    public WindowFrame(List<JLayeredPane> panels){
        super();
        setSize(960, 640);
        setTitle("Virus Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        layeredPane = getLayeredPane();
        for(JLayeredPane panel:panels){
            layeredPane.add(panel, 0);
        }
    }
}
