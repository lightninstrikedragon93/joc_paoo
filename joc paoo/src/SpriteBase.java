import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static javax.swing.JLayeredPane.getLayer;

public abstract class SpriteBase extends Item{
    protected int DEFAULT_HEALTH = 1;
    protected int DEFAULT_DAMAGE = 0;
    protected double DEFAULT_SPEED = 10;
    protected int health;
    protected int damage;
    protected double speed;
    protected double XMove;
    protected double YMove;
    protected double dx, dy, dr;
    protected double radius;
    protected boolean movable = true, removable = false, moving = false;
    public BufferedImage image;
    private Graphics2D g;

    public SpriteBase () {
        super();
    }
    public  void init(RefLinks refLinks, BufferedImage image, double x, double y, double radius, int width, int height, int health, int damage, double speed){
        super.init(refLinks, x, y, width, height);
        health = DEFAULT_HEALTH;
        damage = DEFAULT_DAMAGE;
        speed = DEFAULT_SPEED;
        this.radius = radius;
        this.image = image;
        XMove = 0;
        YMove = 0;
        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;
        dr = 0;

    }

   public double GetDx(){return dx;}
    public double GetDy(){return dy;}
    public double GetDr(){return dr;}
    public void SetDx(double dx){this.dx = dx;}
    public void SetDy(double dy){this.dy = dy;}
    public void SetDr(double dr){this.dr = dr;}

    public void SetSpeed(double speed){this.speed = speed;}
    public double GetSpeed(){return speed;}
    public void SetHealth(int health){this.health = health;}
    public int GetHealth(){return health;}
    public void SetRadius(double radius){this.radius = radius;}
    public double GetRadius(){return radius;}

    public void SetDamage(int damage){this.damage = damage;}
    public int GetDamage(){return damage;}
    public boolean isRemovable(){return removable;}
    public void SetRemovable(boolean removable){this.removable = removable;}
    public boolean IsMovable() {return movable;}
    public void SetMovable(boolean movable){this.movable = movable;}
    public boolean IsMoving(){return moving;}
    public void SetMoving(boolean moving){this.moving = moving;}

    public boolean IsAlive(){return Double.compare(health, 0) > 0;}

    public void MoveTo(double x, double y){SetX(x); SetY(y);}
    public void Move(){x += GetDx(); y += GetDy();}

    public boolean CollideWith(SpriteBase other){
        return Math.hypot(this.GetX() - other.GetX(), this.GetY() - other.GetY()) <= 2 * this.GetRadius();
    }

    public void GetDamagedBy(SpriteBase sprite)
    {
        health -= sprite.GetDamage();
        if(GetHealth() <= 0) Kill();
    }

    public void Kill()
    {
        SetHealth(0);
        Remove();
    }
    public void Remove()
    {
        SetRemovable(true);
    }

    public boolean OutOfBounds(){return false;}

    public BufferedImage GetImage(){ return image;}

    public void SetImage(BufferedImage image){this.image = image;}

    public void processInput(Input input){}

    public void Update(){
        JPanel panel = new JPanel();
        //g.drawImage(image, x, y, null);
        panel.setLocation((int) x, (int) y);
    }

    public void RemoveImage(){
        g = image.createGraphics();
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }

    /*public AddImage()
    {
        g = image.createGraphics();
        g.drawImage()
    }

     */

}