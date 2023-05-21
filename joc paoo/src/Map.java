import java.awt.*;

public class Map {

    private RefLinks refLinks;
    private int width;
    private int height;
    private int [][] tiles;

    public Map(RefLinks refLinks)
    {
        this.refLinks = refLinks;
        LoadWorld();
    }

    public void Update(){}

    public void Draw(Graphics g)
    {
        for(int y = 0; y < width; y++)
        {
            for(int x = 0; x < height; x++)
            {
                GetTile(x, y).Draw(g, (int)x * Tiles.TileHeight_1/2 + 930, (int)y * Tiles.TileWidth_1/2 + 460);
            }
        }

        /*
        for(int y = 0; y < refLinks.GetGame().GetHeight()/Tiles.TileHeight_2; y++)
        {
            for(int x = 0; x < refLinks.GetGame().GetWidth()/Tiles.TileWidth_2; x++)
            {
                GetTile(x, y).Draw(g, (int)x * Tiles.TileHeight_2, (int)y * Tiles.TileWidth_2);
            }
        }

        for(int y = 0; y < refLinks.GetGame().GetHeight()/Tiles.TileHeight_3; y++)
        {
            for(int x = 0; x < refLinks.GetGame().GetWidth()/Tiles.TileWidth_3; x++)
            {
                GetTile(x, y).Draw(g, (int)x * Tiles.TileHeight_3, (int)y * Tiles.TileWidth_3);
            }
        }

         */


    }
    public Tiles GetTile(int x, int y)
    {
        /*
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tiles.right_angle_triangle_wood;
        }
        */

        Tiles t = Tiles.tiles[tiles[x][y]];

        /*
        if(t == null)
        {
            return Tiles.right_angle_triangle_wood;
        }

         */
        return t;
    }
    private void LoadWorld()
    {
        width = 4;
        height = 4;
        tiles = new int[width][height];

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                tiles[x][y] = FirstMap(y, x);
            }
        }
    }

    private int FirstMap(int x, int y)
    {
        final int map[][] = {
                {12, 11, 11, 12},
                {12, 13, 13, 12},
                {13, 12, 12, 13},
                {13, 12, 12, 11}

        };

        if (x < 0 || x > map.length || y < 0 || y > map.length) {
            throw new IllegalArgumentException("Invalid x or y value");
        }

        return map[x][y];
    }

}
