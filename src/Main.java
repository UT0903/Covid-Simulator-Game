import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainGame frame = new MainGame(); //create new JFrame (window for the game)
        GameBody gameBody = new GameBody();
        frame.add(gameBody, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
