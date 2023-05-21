import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Assets {
    public static BufferedImage play_button;
    public static BufferedImage stop_button;
    public static BufferedImage exit_button;

    public static BufferedImage slingshot_front;
    public static BufferedImage slingshot_back;

    public static BufferedImage harness;
    public static BufferedImage background;
    public static BufferedImage egg;


    //linii = nr frame, coloane = tip material
    public static BufferedImage[][] woodFrames = new BufferedImage[3][5];
    //public static BufferedImage[][] glassFrames = new BufferedImage[3][5];
    //public static BufferedImage[][] metalFrames = new BufferedImage[3][5];
    public static BufferedImage[][] birdsFrames = new BufferedImage[2][4];
    public static void init()  {

        SpriteSheet sheetbird = new SpriteSheet(ImageLoader.LoadImage("res/bird_1_1_100.png"));
        SpriteSheet sheetwood = new SpriteSheet(ImageLoader.LoadImage("res/wood_1_1.png"));

        woodFrames[0][0] = sheetwood.crop(0, 0, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        woodFrames[0][1] = sheetwood.crop(1, 0, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        woodFrames[0][2] = sheetwood.crop(2, 0, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        //woodFrames[0][3] = sheet.crop(1, 0, SpriteSheet.TileWidth_2, SpriteSheet.TileHeight_2);
        //woodFrames[0][4] = sheet.crop(1, 1, SpriteSheet.TileWidth_3, SpriteSheet.TileHeight_3);
        woodFrames[1][0] = sheetwood.crop(0, 1, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        woodFrames[1][1] = sheetwood.crop(1, 1, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        woodFrames[1][2] = sheetwood.crop(2, 1, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        //woodFrames[1][3] = sheet.crop(2, 0, SpriteSheet.TileWidth_2, SpriteSheet.TileHeight_2);
        //woodFrames[1][4] = sheet.crop(2, 1, SpriteSheet.TileWidth_3, SpriteSheet.TileHeight_3);
        woodFrames[2][0] = sheetwood.crop(0, 2, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        woodFrames[2][1] = sheetwood.crop(1, 2, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        woodFrames[2][2] = sheetwood.crop(2, 2, SpriteSheet.TileWidth_1, SpriteSheet.TileHeight_1);
        //woodFrames[2][3] = sheet.crop(3, 0, SpriteSheet.TileWidth_2, SpriteSheet.TileHeight_2);
        //woodFrames[2][4] = sheet.crop(3, 1, SpriteSheet.TileWidth_3, SpriteSheet.TileHeight_3);


        birdsFrames[0][0] = sheetbird.crop(0, 0, SpriteSheet.TileWidth_7, SpriteSheet.TileHeight_7);
        birdsFrames[1][0] = sheetbird.crop(0, 1, SpriteSheet.TileWidth_7, SpriteSheet.TileHeight_7);
        birdsFrames[0][1] = sheetbird.crop(1, 0, SpriteSheet.TileWidth_7, SpriteSheet.TileHeight_7);
        birdsFrames[1][1] = sheetbird.crop(1, 1, SpriteSheet.TileWidth_7, SpriteSheet.TileHeight_7);
        birdsFrames[0][2] = sheetbird.crop(2, 0, SpriteSheet.TileWidth_7, SpriteSheet.TileHeight_7);
        birdsFrames[1][2] = sheetbird.crop(2, 1, SpriteSheet.TileWidth_7, SpriteSheet.TileHeight_7);
        birdsFrames[0][3] = sheetbird.crop(3, 0, SpriteSheet.TileWidth_7, SpriteSheet.TileHeight_7);
        birdsFrames[1][3] = sheetbird.crop(3, 1, SpriteSheet.TileWidth_7, SpriteSheet.TileHeight_7);


        slingshot_back = ImageLoader.LoadImage("res/slingshot_back.png");
        slingshot_front = ImageLoader.LoadImage("res/slingshot_front.png");
        harness = ImageLoader.LoadImage("res/harness.png");

        background = ImageLoader.LoadImage("res/background_1.png");
        egg = ImageLoader.LoadImage("res/egg.png");

    }
}
