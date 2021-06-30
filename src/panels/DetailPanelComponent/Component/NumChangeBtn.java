package panels.DetailPanelComponent.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class NumChangeBtn extends JButton{
    public int itemId;
    public NumChangeBtn(String text, int itemId, MouseAdapter adapter){
        super(text);
        setBackground(Color.gray);
        setForeground(Color.black);
        this.itemId = itemId;
        //decBtn.setPreferredSize(new Dimension(15, 15));
        setMargin( new Insets(10, 10, 10, 10) );
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }

}
