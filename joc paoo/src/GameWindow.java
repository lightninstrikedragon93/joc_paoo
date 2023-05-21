import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class GameWindow
{
    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;
    private JFrame frame;
    private String Title;
    private Canvas canvas;
    private Input input;

    public GameWindow(String title, int WIDTH, int HEIGHT) {

        Title = title;
        SCREEN_HEIGHT = HEIGHT;
        SCREEN_WIDTH = WIDTH;
        frame = null;

    }

    public void BuildGameWindow()
    {
        if(frame != null)
            return;

        frame = new JFrame(Title);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        canvas.setMaximumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        canvas.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        input = new Input();
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);


        frame.getContentPane().add(canvas);

        frame.setVisible(true);
        frame.pack();


    }

    public int GetWindowWidth(){return SCREEN_WIDTH;}
    public int GetWindowHeight(){return SCREEN_HEIGHT;}
    public Canvas GetCanvas(){return canvas;}
    public JFrame GetFrame(){return frame;}

}
