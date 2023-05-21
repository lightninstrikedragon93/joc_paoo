import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Slingshot {
    private DoubleBoundLine doubleBoundLine;
    private BufferedImage backImage, frontImage, harnessImage;

    private double dx = 0, dy = 0;
    private boolean movable = true;

    public Slingshot() {
    }

    public void init() throws IOException {
        double finalHeight = 1200;
        double finalWidth = 800;

        BufferedImage slingshotBack = Assets.slingshot_back;
        BufferedImage slingshotFront = Assets.slingshot_front;
        BufferedImage harness = Assets.harness;

        double slingshotBackX = 0.17,
                slingshotFrontX = 0.151,
                slingshotBackY = 0.36,
                slingshotFrontY = 0.366;

        this.doubleBoundLine = new DoubleBoundLine();
        this.backImage = slingshotBack;
        this.frontImage = slingshotFront;
        this.harnessImage = harness;

        slingshotBackX = slingshotBackX * finalWidth;
        slingshotBackY = finalHeight - (slingshotBackY * finalHeight);
        slingshotFrontX = slingshotFrontX * finalWidth;
        slingshotFrontY = finalHeight - (slingshotFrontY * finalHeight);
        int combWidth = backImage.getWidth() + frontImage.getWidth();
        int combHeight = backImage.getHeight() + frontImage.getHeight();

        BufferedImage comb = new BufferedImage(combWidth, combHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = comb.createGraphics();
        g.drawImage((Image) backImage, (int) slingshotBackX, (int) slingshotBackY, null);
        g.drawImage((Image) frontImage, (int) slingshotFrontX, (int) slingshotFrontY, null);

        ImageIO.write(comb, "png", new File("res/comb.png"));

        doubleBoundLine.GetBack().SetAllProperty(backImage, 30, 25);
        doubleBoundLine.GetFront().SetAllProperty(frontImage, 18, 30);

       // harness = null;


    }

    public void ProcessInput(Input input) {
        double x, y, radius = 70;
        if (input.IsOutOfRange(radius)) {
            x = input.GetMaxRangeX(radius) + input.GetDragStartX();
            y = input.GetMaxRangeY(radius) + input.GetDragStartY();
        } else {
            x = input.GetSceneX() + input.GetDragStartX();
            y = input.GetSceneY() + input.GetDragStartY();
        }

        if (input.IsMouseDragged()) {
            Graphics2D g = harnessImage.createGraphics();
            g.drawImage(harnessImage, (int) x, (int) y + 15, null);

            GetDoubleBoundLine().GetBack().SetEndProperty(x + 10, y + 35);
            GetDoubleBoundLine().GetFront().SetEndProperty(x + 10, y + 35);

        }
        if (input.IsMouseReleased() && movable) {
            if (eq(x + 10 + dx, input.GetImageStartX()) && eq(y + 35 + dy, input.GetImageStartY())) {
                movable = false;
                harnessImage = null;

                // doubleBoundLine.GetBack().SetEndProperty(frontImage.);
                dx = 0;
                dy = 0;
            } else {
                dx = dx + ((input.GetImageStartX() - input.GetSceneX()) / 12);
                dy = dy + ((input.GetImageStartY() - input.GetSceneY()) / 12);

                GetDoubleBoundLine().GetBack().SetEndProperty(x + 10 + dx, y + 35 + dy);
                GetDoubleBoundLine().GetFront().SetEndProperty(x + 10 + dx, y + 35 + dy);

            }

        }
    }

    public DoubleBoundLine GetDoubleBoundLine() {
        return doubleBoundLine;
    }

    public static boolean eq(double x, double y){
        return Math.abs(x - y) < 40;
    }

    public void SetMovable(boolean movable)
    {
        this.movable = movable;
    }

    public BufferedImage GetHarness() {
        return harnessImage;
    }

    public BufferedImage GetFront() {
        return frontImage;
    }
}
