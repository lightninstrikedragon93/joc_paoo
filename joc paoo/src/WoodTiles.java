
public class WoodTiles extends Tiles{
    public WoodTiles(int id)
    {
        super(Assets.woodFrames[id / 10 - 1][id % 10 - 1], id);
    }

}

