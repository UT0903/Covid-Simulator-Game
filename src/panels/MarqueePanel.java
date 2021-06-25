package panels;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MarqueePanel extends JPanel implements ActionListener {
    private static final int RATE = 20;
    private final Timer timer = new Timer(1000 / RATE, this);
    private final JLabel mes = new JLabel();
    private List<String> messages;
    private String message;
    private int numChars;
    private int index;

    public MarqueePanel(int n) {
        this.index = 0;
        this.numChars = n;
        this.messages = new ArrayList<>();
        mes.setFont(new Font("Serif", Font.ITALIC, 20));
        this.add(mes);
    }

    public void start() { timer.start(); }
    public void stop() { timer.stop(); }

    public void generateString() {
        if (this.messages.size() < 1)
            this.message = "";
        else {
            StringBuilder messageBuilder = new StringBuilder();
            StringBuilder spaces = new StringBuilder();
            String intervals = "                ";
            for (int i = 0; i < this.numChars; i++)
                spaces.append(' ');
            messageBuilder.append(spaces);
            messageBuilder.append(this.messages.get(0));
            for (int i = 1; i < this.messages.size(); i++) {
                messageBuilder.append(intervals);
                messageBuilder.append(this.messages.get(i));
            }
            messageBuilder.append(spaces);
            this.message = messageBuilder.toString();
        }
    }

    public void setNumChars(int n) {
        this.numChars = n;
        this.generateString();
    }

    public void addString(String str) {
        this.messages.add(str);
        this.generateString();
    }

    public void removeString(String str) {
        this.messages.remove(str);
        this.generateString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.index++;
        if (this.index > this.message.length() - this.numChars)
            this.index = 0;
        this.mes.setText(this.message.substring(this.index, this.index + this.numChars));
    }
}
