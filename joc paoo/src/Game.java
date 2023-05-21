import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Game implements Runnable{

    private GameWindow window;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;
    private Graphics g;
    private State playState;
    private Input input;
    private RefLinks refLinks;
    //private Tiles tile;

    public Game(String title, int width, int height)
    {
        window = new GameWindow(title, width, height);
        runState = false;
        input  = new Input();
    }

    public void InitGame() throws IOException {
        window.BuildGameWindow();
        window.GetFrame().addMouseListener(input);
        window.GetFrame().addMouseMotionListener(input);
        Assets.init();

        refLinks = new RefLinks(this);
        playState = new PlayState(refLinks);

        State.SetState(playState);
    }

    public void run()
    {
        try {
            InitGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long oldTime = System.nanoTime();
        long currentTime;
        final int framePerSecond = 60;
        final double timeFrame = 1000000000/ framePerSecond;

        while(runState == true)
        {
            currentTime = System.nanoTime();
            if((currentTime - oldTime) > timeFrame)
            {
                Update();
                try {
                    Draw();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                oldTime = currentTime;
            }
        }
    }

    public synchronized void StartGame()
    {
        if(runState == false)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        else {return;}
    }
    public synchronized void StopGame()
    {
        if(runState == true)
        {

            runState = false;
            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        else {return;}
    }

    private void Update()
    {
        input.Update();
       
        if(State.GetState() != null)
        {
            State.GetState().Update();
        }
    }

    private void Draw() throws IOException {

        bs = window.GetCanvas().getBufferStrategy();
       if(bs == null)
        {
            try
            {
                window.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, window.GetWindowWidth(), window.GetWindowHeight());

        if(State.GetState() != null)
        {
            State.GetState().Draw(g);
        }

        bs.show();
        g.dispose();
    }


    public int GetWidth()
    {
        return window.GetWindowWidth();
    }

    public int GetHeight()
    {
        return window.GetWindowHeight();
    }

    public Input GetInput(){return input;}
}
