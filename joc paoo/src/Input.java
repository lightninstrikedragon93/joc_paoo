import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public  class Input implements MouseListener, MouseMotionListener {
    private double DragStartX, DragStartY, ImageStartX, ImageStartY, SceneX, SceneY;
    private boolean MouseDragged = false,
        MouseEntered = false,
        MousePressed = false,
        MouseReleased = false,
        MouseClicked = false;
    private  JLabel label = new JLabel();


    public Input (){

        MouseEntered = true;
        MousePressed = false;

    }

    public void Update()
    {
        MousePressed = true;
    }

    public boolean IsOutOfRange(double radius) {
        return Math.hypot(GetSceneX() - GetImageStartX(), GetSceneY() - GetImageStartY()) >= 2 * radius; // sqrt(x^2 + y^2)
    }

    public double GetMaxRangeX(double radius) {
        double vX = GetSceneX() - GetImageStartX();
        double vY = GetSceneY() - GetImageStartY();
        double magV = Math.sqrt(vX*vX + vY*vY);

        return GetImageStartX() + vX / magV * 2 * radius;
    }

    public double GetMaxRangeY(double radius) {
        double vX = GetSceneX() - GetImageStartX();
        double vY = GetSceneY() - GetImageStartY();
        double magV = Math.sqrt(vX*vX + vY*vY);

        return GetImageStartY() + vY / magV * 2 * radius;
    }

    public void SetDragStartX(double DragStartX) {
        this.DragStartX =  (DragStartX - GetSceneX());
    }

    public void SetDragStartY(double DragStartY) {
        this.DragStartY =  (DragStartY - GetSceneY());
    }

    public double GetDragStartX() {
        return DragStartX;
    }

    public double GetDragStartY() {
        return DragStartY;
    }

    public double GetSceneX() {
        return SceneX;
    }

    public double GetSceneY() {
        return SceneY;
    }

    public double GetImageStartX() {
        return ImageStartX;
    }

    public double GetImageStartY() {
        return ImageStartY;
    }

    public boolean IsMouseEntered() {
        return MouseEntered;
    }

    public boolean IsMousePressed() {
        return MousePressed;
    }

    public boolean IsMouseReleased() {
        return MouseReleased;
    }

    public boolean IsMouseDragged() {
        return MouseDragged;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MouseClicked = true;
        System.out.println("MouseClicked");

    }

    @Override
    public void mousePressed(MouseEvent event) {
        MousePressed = true;
        //MouseEntered = false;

        ImageStartX = event.getX();
        ImageStartY = event.getY();

        SceneX = event.getXOnScreen();
        SceneY = event.getYOnScreen();

        System.out.println("MousePressed");

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        MouseDragged = false;
        MouseReleased = true;

        SceneX = event.getX() - ImageStartX;
        SceneY = event.getY() - ImageStartY;
        System.out.println("MouseReleased");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MouseEntered = true;
        System.out.println("MouseEntered");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        MouseEntered = false;
        System.out.println("MouseExited");

    }

    @Override
    public void mouseDragged(MouseEvent event) {
        MousePressed = false;
        MouseDragged = true;

        SceneX = event.getXOnScreen();
        SceneY = event.getYOnScreen();
        System.out.println("MouseDragged");

    }


    @Override
    public void mouseMoved(MouseEvent event) {

        label.setLocation(event.getX(), event.getY());
    }
}
