package panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils {
    public static final String basePath = "../pic/";

    public static ImageIcon resizeImage(String path , int width, int height){
        /*ImageIcon img = new ImageIcon(path);
        img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return img;*/
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (IOException e) {}
        return null;
    }
    public static JLabel newLabelString(String s, int size){
        JLabel jl = new JLabel();
        jl.setText(s);
        jl.setFont(new Font("Courier", Font.ITALIC, size));
        jl.setOpaque(false);
        jl.setHorizontalTextPosition(SwingConstants.CENTER);
        return jl;
    }
}
