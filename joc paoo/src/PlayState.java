
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayState extends State
{
    private Bird currentBird;
    private List<Bird> birds = new ArrayList<>(3);
    private List<Egg> eggs = new ArrayList<>(3);
    private Factory factory;
    private Slingshot slingshot;
    private Input input;
    private int score = 0;
    private JLabel scoreText;

    private Map map;

    public PlayState(RefLinks refLink) throws IOException {

        super(refLink);

        map = new Map(refLink);
        input = new Input();
        refLink.SetMap(map);
        factory = new Factory();
        factory.setRefLinks(refLink);


        CreateBird("bean");
        CreateBird("purple");
        CreateBird("bean");
        CreateBird("purple");


        CreateEggs();
        CreateSlingshot();
        loadNextBird();

        SpriteBase bird = CurrentBird();
        bird.processInput(input);
        slingshot.ProcessInput(input);
        bird.Move();
        CheckCollision();
        bird.Update();
        //RemoveSprite();
        //UpdateScore();

    }

    private void UpdateScore() {
        scoreText = new JLabel("Score : " + score);
        scoreText.setText("Score:" + score);
    }

    @Override
    public void Update()
    {
        map.Update();
    }

    @Override
    public void Draw(Graphics g) throws IOException {

        g.drawImage(Assets.background, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
        map.Draw(g);
        int i = 0;
        for (Bird b : birds) {
            g.drawImage(b.GetImage(), i, 610, 50, 50, null);
            i += 50;
        }
        i = 0;

        for(Egg e: eggs){
            g.drawImage(e.image, 0, 0, e.GetWidth(), e.GetHeight(), null);
        }

        g.drawImage(Assets.slingshot_front, 229, 560, 18,60, null);
        g.drawImage(Assets.slingshot_back, 241, 560, 20, 100, null);
    }


    public void CreateBird(String type) throws IOException {

        if(type.equals("purple")) {
            Bird bird = (Bird) factory.GetSprite("bird", "purple");
            birds.add(bird);
        }
        else if(type.equals("bean")){
            Bird bird = (Bird) factory.GetSprite("bird", "bean");
            birds.add(bird);
        }
    }

    public void CreateEggs() throws IOException {
        for(int i = 0; i <= 3; i ++)
        {
            double eggX = 556,
                    eggY = 218;
            Egg egg = (Egg) factory.GetSprite("egg", null);
            egg.MoveTo(eggX, eggY);
            eggs.add(egg);
        }
    }

    public void CreateSlingshot()throws IOException{
        slingshot = new Slingshot();
        slingshot.init();
    }

    private boolean GameOver () {
        return (birds.isEmpty() || (eggs.isEmpty() && !CurrentBird().IsMoving()));
    }

    private void RemoveSprite()
    {
        int InitialBirdsNr = birds.size();

        birds.stream().filter(bird -> bird.isRemovable() && bird.OutOfBounds()).forEach(SpriteBase::RemoveImage);;
        eggs.stream().filter(SpriteBase::isRemovable).forEach(SpriteBase::RemoveImage);

        birds = birds.stream().filter(bird -> !bird.isRemovable() || !bird.OutOfBounds()).collect(Collectors.toList());
        eggs = eggs.stream().filter(pig -> !pig.isRemovable()).collect(Collectors.toList());

        int newBirdsNbr = birds.size();

        if(newBirdsNbr < InitialBirdsNr && newBirdsNbr != 0) {
            loadNextBird();
        }
    }
    private void loadNextBird() {
        double birdOnSlingshotX = 10,
                birdOnSlingshotY = 15;

        slingshot.SetMovable(true);
        CurrentBird().MoveTo(birdOnSlingshotX, birdOnSlingshotY);

        input = new Input();

        slingshot.GetDoubleBoundLine().GetFront();
        slingshot.GetHarness();
        slingshot.GetFront();
    }
    
    private Bird CurrentBird()
    {
        currentBird = birds.get(0);
        return currentBird;
    }

    private void CheckCollision() {
        for(Egg egg: eggs)
            if(egg.CollideWith(CurrentBird())) {
                System.out.println("Collision");

                score += egg.GetScore();

                egg.GetDamagedBy(CurrentBird());
                CurrentBird().GetDamagedBy(egg);
            }
    }
}
