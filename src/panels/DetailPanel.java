package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DetailPanel extends JPanel{
    public DetailPanel(){
        super();
        setName("DetailPanel");
        //setSize(210, 170);
        //setLocation(750, 470);
        setBackground(Color.red);
        setVisible(true);
        //setPreferredSize(new Dimension(200, 100));
        setComponents("init");
    }
    public void setComponents(String type){
        for(Component c: getComponents()){
            remove(c);
        }
        switch(type) {
            case ("init"):
                JButton startbtn = new JButton("     start game     ");
                startbtn.setBackground(Color.gray);
                startbtn.setForeground(Color.black);
                startbtn.setPreferredSize(new Dimension(100, 30));
                startbtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("clicked");
                    }

                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        startbtn.setBackground(Color.darkGray);
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        System.out.println("out");
                        startbtn.setBackground(Color.gray);
                    }
                });
                startbtn.setBorder(BorderFactory.createLineBorder(Color.pink, 3));
                add(startbtn);
                revalidate();
                repaint();
                //System.out.println("init");
                break;
            case ("during game"):
                break;
            case ("pause"):
                break;
            default:
                JTextArea t = new JTextArea();
                t.setText(type);
                add(t);
                revalidate();
                repaint();
                break;
       }
    }
}
