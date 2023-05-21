import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class BoundLine extends Line2D {

    private double startX, startY, endX, endY;
    public BoundLine()
    {
        Container cp = new Container();
        cp.add(new Component() {
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke (10));
                g2.draw(new Line2D.Float(30, 20, 80, 90));
            }
        });
    }

    @Override
    public double getX1() {
        return 0;
    }

    @Override
    public double getY1() {
        return 0;
    }

    @Override
    public Point2D getP1() {
        return null;
    }

    @Override
    public double getX2() {
        return 0;
    }

    @Override
    public double getY2() {
        return 0;
    }

    @Override
    public Point2D getP2() {
        return null;
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {

    }

    public void SetAllProperty(Image image, double x, double y)
    {
        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);

        label.setBounds((int) x, (int) y, icon.getIconWidth(), icon.getIconHeight());

        double lineX = label.getX();
        double lineY = label.getY();

        SetAllProperty(lineX, lineY);
    }

    public void SetAllProperty(double x, double y)
    {
        SetStartProperty(x, y);
        SetEndProperty(x, y);
    }

    public void SetStartProperty(double startX, double startY)
    {
        this.startX = startX;
        this.startY = startY;
    }
    public void SetEndProperty(double endX, double endY)
    {
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }
}
