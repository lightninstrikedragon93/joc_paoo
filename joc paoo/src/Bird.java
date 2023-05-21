import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TimerTask;


public abstract class Bird extends SpriteBase{
    private double minX, minY, maxX, maxY, finalWidth, finalHeight;
    public Bird()
    {
        super();
    }
    public void init(RefLinks refLinks, BufferedImage birdImage, double x, double y, double minX, double minY, double maxX,double maxY, double radius, int health, int damage, int width, int height, int speed) {
        super.init(refLinks, birdImage, x, y, radius, width, height, health, damage, speed);

        x *= finalWidth;
        y = finalHeight -( y * finalHeight);
        this.minX = minX;
        this.minY = minY;
        this.maxX = finalWidth - maxX;
        this.maxY = finalHeight - (maxY * finalHeight);

        normalBounds.x = 0;
        normalBounds.y = 572;
        normalBounds.width = 100;
        normalBounds.height = 100;

        attackBounds.width = 100;
        attackBounds.height = 100;

    }

    @Override
    public void Move(){
        if(!OutOfBounds())
        {
            super.Move();
            if(IsMoving())
                SetDy( (GetDy() + 10));
        }
    }

    @Override
    public void MoveTo(double x, double y){
        super.MoveTo(x, y);
        x = x * finalWidth;
        y = finalHeight - (y * finalHeight);

    }

    @Override
    public void GetDamagedBy(SpriteBase spritebase){
        super.GetDamagedBy(spritebase);
        SetImage(image);
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

    public boolean OutOfBounds()
    {
        if(Double.compare(GetX(), minX) < 0 || Double.compare(GetX(), maxX - 50) > 0)
            return true;
        if(Double.compare(GetX(), maxY) > 0){
            if(BounceIsTooWeak())
                return true;
            SetRemovable(true);
            SetY(maxY - 1);
            SetDy(-GetDy() /2);
            SetDx(GetDx() / 2);
        }
        return false;
    }
    public void processInput(Input input) {
        double radius = 70;
        image = GetImage();


        if(input.IsMouseEntered() && IsMovable())
        {
            if(image == null) {
                System.out.println("null");
                System.exit(0);
            }

            JLabel label = new JLabel(new ImageIcon(image));
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            label.setCursor(cursor);

        }

        if(input.IsMousePressed() && IsMovable()) {

            input.SetDragStartX(image.getMinX());
            input.SetDragStartY(image.getMinY());

            JLabel label = new JLabel(new ImageIcon(image));
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            label.setCursor(cursor);
        }

        if(input.IsMouseDragged() && IsMovable()) {
            if(input.IsOutOfRange(radius)){
                SetX( (input.GetMaxRangeX(radius) + input.GetDragStartX()));
                SetY( (input.GetMaxRangeY(radius) + input.GetDragStartY()));
            }
            else {
                SetX( (input.GetSceneX() + input.GetDragStartX()));
                SetY(( (input.GetSceneY() + input.GetDragStartY())));
            }
        }

        if(input.IsMouseReleased() && IsMovable()) {
            SetMoving(true);
            SetMovable(false);
            SetRemovable(true);

            JLabel label = new JLabel(new ImageIcon(image));
            Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
            label.setCursor(cursor);

            SetImage(image);

            if(input.IsOutOfRange(radius)){
                SetDx( ((input.GetImageStartX() - input.GetMaxRangeX(radius)) / 10));
                SetDy( ((input.GetImageStartY() - input.GetMaxRangeY(radius)) / 10));
            }
            else {
                SetDx( ((input.GetImageStartX() - input.GetSceneX()) / 10));
                SetDy( ((input.GetImageStartY() - input.GetSceneY()) / 10));
            }
        }
    }

    private boolean BounceIsTooWeak() {
        return Double.compare(Math.abs(GetDx()), 1) < 0 || Double.compare(Math.abs(GetDy()), 1) < 0;
    }
}
