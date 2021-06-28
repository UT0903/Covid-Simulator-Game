package panels;

import Game.GameState;
import Game.GameStateListener;
import Game.StateManager;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MessagePanel extends JPanel implements ActionListener, GameStateListener {
    private final Timer timer = new Timer(50,  this);
    private final JLabel mes = new JLabel();
    private List<String> messages;
    private String message;
    private int numChars;
    private int index;
    private int delayCount;

    public MessagePanel(int n) {
        this.index = 0;
        this.numChars = n;
        this.messages = new ArrayList<>();
        this.delayCount = 0;
        setPreferredSize(new Dimension(750, 40));
        setBackground(Color.green);
        setLocation(0, 0);
        setLayout(new GridLayout());
        this.mes.setFont(new Font("Courier", Font.ITALIC, 18));
        this.mes.setHorizontalAlignment(SwingConstants.LEFT);
        this.mes.setVerticalAlignment(SwingConstants.CENTER);
        this.add(mes);
        StateManager.addGameStateListener(this);
    }

    public void generateString() {
        if (this.messages.size() < 1)
            this.message = "";
        else {
            StringBuilder messageBuilder = new StringBuilder();
            StringBuilder spaces = new StringBuilder();
            String intervals;
            for (int i = 0; i < this.numChars; i++)
                spaces.append(' ');
            messageBuilder.append(spaces);
            messageBuilder.append(this.messages.get(0));
            for (int i = 1; i < this.messages.size(); i++) {
                intervals = " ".repeat(this.numChars - this.messages.get(i - 1).length());
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
        if (str.length() <= this.numChars)
            this.messages.add(str);
        else {
            int start = 0;
            while (start < str.length()) {
                if (start + this.numChars <= str.length()) {
                    int end = str.substring(start, start + this.numChars).lastIndexOf(' ');
                    this.messages.add(str.substring(start, start + end));
                    start += end + 1;
                }
                else {
                    this.messages.add(str.substring(start));
                    start += this.numChars;
                }
            }
        }
        this.generateString();
    }

    public void removeString(String str) {
        if (str.length() <= this.numChars)
            this.messages.remove(str);
        else {
            int start = 0;
            while (start < str.length()) {
                if (start + this.numChars <= str.length()) {
                    int end = str.substring(start, start + this.numChars).lastIndexOf(' ');
                    this.messages.remove(str.substring(start, start + end));
                    start += end + 1;
                }
                else {
                    this.messages.remove(str.substring(start));
                    start += this.numChars;
                }
            }
        }
        this.generateString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.message.equals("")) {
            this.mes.setText(this.message);
            this.index = 0;
        }
        else {
            if (this.index != 0 && this.index % this.numChars == 0 && this.delayCount < 20) {
                this.delayCount++;
            }
            else {
                this.index++;
                this.delayCount = 0;
                if (this.index > this.message.length() - this.numChars)
                    this.index = 0;
                this.mes.setText(this.message.substring(this.index, this.index + this.numChars));
            }
        }
    }

    @Override
    public void onGameStateChanged(GameState prevState, GameState curState) {
        if (prevState == GameState.INIT && curState == GameState.INGAME)
            this.timer.start();
        else if (prevState == GameState.INGAME && curState == GameState.PAUSE) {
            this.timer.stop();
            this.mes.setText("Game paused.");
        }
        else if (prevState == GameState.PAUSE && curState == GameState.INGAME)
            this.timer.start();
    }
}
