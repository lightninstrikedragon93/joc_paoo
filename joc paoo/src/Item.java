import java.awt.*;
public abstract class Item {

    protected double x;
    protected double y;
    protected int width;
    protected int height;

    protected Rectangle bounds;
    protected Rectangle normalBounds;
    protected Rectangle attackBounds;

    protected RefLinks refLinks;
    public Item(){}
    public void init(RefLinks refLinks, double x, double y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.refLinks = refLinks;
        normalBounds = new Rectangle(0, 0,width,height);
        attackBounds = new Rectangle(0,0, width, height);
        bounds = normalBounds;
    }
    public abstract void Update();
   public abstract void Draw(Graphics g);

    public double GetX(){return x;}
    public double GetY(){return y;}
    public int GetWidth(){return width;}
    public int GetHeight(){return height;}

    public void SetX(double x){this.x = x;}
    public void SetY(double y){this.y = y;}

    public void SetWidth(int width){this.width = width;}
    public void SetHeight(int height){this.height = height;}
    public void SetNormalMode(){bounds = normalBounds;}
    public void SetAttackMode(){bounds  = attackBounds;}

}
