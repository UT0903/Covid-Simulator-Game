package panels;

import javax.swing.*;
import java.awt.*;

public class DetailPanel extends JLayeredPane{
    public DetailPanel(){
        super();
        setSize(210, 170);
        setBackground(Color.red);
        //setPreferredSize(new Dimension(200, 100));
        //setComponents("init");
    }
    private void setComponents(String type){
        for(Component c: getComponents()){
            remove(c);
        }
        switch(type){
            case("init"):
                JButton startbtn = new JButton("     start game     ");
                startbtn.setBackground(Color.gray);
                startbtn.setBorder(BorderFactory.createLineBorder(Color.pink, 3));
                add(startbtn);
                System.out.println("init");
                break;
            case("during game"):
                break;
            case("pause"):
                break;
            case("itemDetail"):
                break;
        }

    }
}