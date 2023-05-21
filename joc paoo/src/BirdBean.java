import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BirdBean extends Bird{

    public BirdBean(RefLinks refLinks) {
        double radius = 9,
                birdOnSlingshotX = 0.150f,
                birdOnSlingshotY = 0.350f,
                birdWaintingX = 0.100f,
                birdWaintingY = 0.180f,
                maxY = 0.188f,
                maxX = 0;
        int height = 50,
                width = 50,
                speed = 10,
                health = 1,
                damage = 0;
        image = Assets.birdsFrames[0][1];
        super.init(refLinks, image, birdWaintingX, birdWaintingY, 0, 0, maxX, maxY, radius, health, damage, width, height, speed);
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    public void Update()
    {
        GetInput();

        Move();
        if(refLinks.GetInput().IsMouseDragged() || refLinks.GetInput().IsMouseEntered()) {
            image = Assets.birdsFrames[0][1];
        }

        if(refLinks.GetInput().IsMouseReleased() == true) {
            image = Assets.birdsFrames[1][1];
        }
    }

    private void GetInput()
    {
        XMove = 0;
        YMove = 0;
        if(refLinks.GetInput().IsMouseReleased() == true) {
            XMove = speed;
            YMove = speed;
        }

    }
}
