import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGame extends JFrame {
    public static final int windowWidth = 960;
    public static final int windowHeight = 640;

    public MainGame(){
        super();
        setSize(windowWidth, windowHeight);
        setLayout(new BorderLayout());
        setTitle("Virus Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        setVisible(true);
    }
    public static void main(String[] args) {
        MainGame frame = new MainGame(); //create new JFrame (window for the game)
        GameBody gameBody = new GameBody();
        frame.add(gameBody, BorderLayout.CENTER);
        frame.setVisible(true);

    }
    public static class GameBody extends JPanel {
        public GameBody() {
            setLayout(new FlowLayout());
            ImageIcon backgroundPicture = new ImageIcon("../pic/map.png");
            JLabel picLabel = new JLabel(backgroundPicture);
            add(picLabel);
        }
    }

}