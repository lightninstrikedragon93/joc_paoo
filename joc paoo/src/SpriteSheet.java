import java.awt.image.BufferedImage;
public class SpriteSheet {
    private BufferedImage spriteSheet;
    public static final int TileWidth_1 = 80; // rat, sq, t
    public static final int TileHeight_1 = 80;

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

    public static final int TileWidth_7 = 100; // b
    public static final int TileHeight_7 = 100;

    public SpriteSheet(BufferedImage buffImg)
    {
        spriteSheet = buffImg;
    }

     public BufferedImage crop (int x, int y, int w, int h)
    {
        return spriteSheet.getSubimage(x * w, y * h, w, h);
    }
}
