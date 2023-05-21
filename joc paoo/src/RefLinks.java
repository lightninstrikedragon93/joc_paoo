public class RefLinks {

    private Game game;
    private Map map;

    public RefLinks(Game game)
    {
        this.game = game;
    }

    public Input GetInput()
    {
        return game.GetInput();
    }


    public int GetWidth()
    {
        return game.GetWidth();
    }

    public int GetHeight()
    {
        return game.GetHeight();
    }

    public Game GetGame()
    {
        return game;
    }

    public void SetGame(Game game)
    {
        this.game = game;
    }

    public Map GetMap()
    {
        return map;
    }

    public void SetMap(Map map)
    {
        this.map = map;
    }
}
