import java.awt.*;
import java.awt.image.BufferedImage;

public class Tiles {
    private static final int No_Tiles = 100;
    public static final int TileWidth_1 = 100; // rat, sq, t
    public static final int TileHeight_1 = 100;

    public static final int TileWidth_2 = 40; // sc
    public static final int TileHeight_2 = 40;

    public static final int TileWidth_3 = 168; //st
    public static final int TileHeight_3 = 20;

    public static final int TileWidth_4 = 20;//harness
    public static final int TileHeight_4 = 29;

    public static final int TileWidth_5 = 39; // sb
    public static final int TileHeight_5 = 200;

    public static final int TileWidth_6 = 45; // sf
    public static final int TileHeight_6 = 125;
/*
    public static final int TileWidth_7 = 50; // b
    public static final int TileHeight_7 = 50;

 */
    public static Tiles[] tiles = new Tiles[No_Tiles];
    public static Tiles right_angle_triangle_wood = new WoodTiles(11);
    public static Tiles triangle_wood = new WoodTiles(12);
    public static Tiles square_wood = new WoodTiles(13);
    public static Tiles stick_wood_medium = new WoodTiles(14);


    protected BufferedImage image;
    protected int id;

    public Tiles(BufferedImage image, int id)
    {
        this.image = image;
        this.id = id;

        tiles[id] = this;
    }

    public void Draw(Graphics g , int x, int y)
    {
        g.drawImage(image, x, y, TileWidth_1/2, TileHeight_1/2, null);
        //g.drawImage(image, x, y, TileWidth_2, TileHeight_2, null);
        //g.drawImage(image, x, y, TileWidth_3, TileHeight_3, null);
        //g.drawImage(image, x, y, TileWidth_4, TileHeight_4, null);
       // g.drawImage(image, x, y, TileWidth_5, TileHeight_5, null);
        //g.drawImage(image, x, y, TileWidth_6, TileHeight_6, null);

    }

    public int GetId(){return id;}

}
