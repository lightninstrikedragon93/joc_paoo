import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameCanvas extends JPanel{
    private Bird bird;
    private Image backgroundImage;
    public GameCanvas() {
        backgroundImage = new ImageIcon("background 1.png").getImage();
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }
    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);

        if(bird != null)
            bird.Draw(g);
    }
     */

}



