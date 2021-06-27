package panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DetailPanel extends JPanel{
    public DetailPanel(){
        super();
        setName("DetailPanel");
        setSize(210, 170);
        setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLocation(750, 470);
        setBackground(new Color(255, 246, 143, 200));

        //setPreferredSize(new Dimension(200, 100));
        setComponents("init");
        setVisible(true);
    }
    public JButton getButton(String text){
        JButton startbtn = new JButton(text);
        startbtn.setBackground(Color.gray);
        startbtn.setForeground(Color.black);
        startbtn.setPreferredSize(new Dimension(100, 30));
        startbtn.setSize(100, 30);
        startbtn.setBorder(BorderFactory.createLineBorder(Color.pink, 3));
        startbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return startbtn;
    }
    public void setComponents(String type){
        for(Component c: getComponents()){
            remove(c);
        }
        if(type.equals("during game")) {
            JButton pausebtn = getButton("     pause     ");
            pausebtn.addMouseListener(new MyMouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("clicked");
                }
            });
            add(pausebtn);
        }
        else if(type.equals("pause")) {
                JButton continuebtn = getButton("     continue     ");
                continuebtn.addMouseListener(new MyMouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("clicked");
                    }
                });
        }
        else if(type.equals("end")){
            JButton restartbtn = getButton("     restart     ");
            restartbtn.addMouseListener(new MyMouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("clicked");
                }
            });
        }
        else{
            setLayout(new GridLayout(3, 1));
            JLabel t = Utils.newLabelString("123", 15);
//            t.setBorder(new LineBorder(new Color(0,0,0,0), 10));
            t.setHorizontalAlignment(SwingConstants.CENTER);
            add(t);
        }
        revalidate();
        repaint();
    }
    public abstract class MyMouseAdapter extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e) {
            e.getComponent().setBackground(Color.darkGray);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            e.getComponent().setBackground(Color.gray);
        }
    }
}
