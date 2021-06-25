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
}