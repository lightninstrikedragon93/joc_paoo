import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Egg extends SpriteBase{
    private int score = 0;
    public Egg(){super();}

    public void Update(){}

    public void init(RefLinks refLinks, BufferedImage eggImage, double radius, int health, int damage, int score, int width, int height, int speed){
        eggImage = Assets.egg;
        this.score = score;
        super.init(refLinks, eggImage, 0, 0, radius, width, height, health, damage, speed);
    }

    public int GetScore(){return score;}
    @Override
    public void MoveTo(double x, double y)
    {
        double finalWidth = 1200;
        double finalHeight = 800;

        x = x * finalWidth;
        y = finalHeight  - (y * finalHeight);
        super.MoveTo(x,y);
    }

    public void RemoveFromScreen(Container container)
    {
        JLabel explosionLabel = new JLabel(new ImageIcon(getClass().getResource("little-explosion-1.png")));
        container.add(explosionLabel);

        Timer explosionTimer = null;
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("little-explosion-2.png"));
            explosionLabel.setIcon(new ImageIcon(image));

            Timer finalExplosionTimer = explosionTimer;
            explosionTimer = new Timer(200, new ActionListener() {
                int frame = 2;
                @Override
                public void actionPerformed(ActionEvent e) {
                    String imagePath = "little-explosion-" + frame + ".png";
                    try {
                        BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagePath));
                        explosionLabel.setIcon(new ImageIcon(image));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    frame++;
                    if (frame > 3) {
                        container.remove(explosionLabel);
                        container.revalidate();
                        container.repaint();
                        finalExplosionTimer.stop();
                    }
                }
            });
            explosionTimer.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }
}
