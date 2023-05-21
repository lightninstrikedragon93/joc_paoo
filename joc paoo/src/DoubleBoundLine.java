public class DoubleBoundLine {
    private BoundLine boudLineBack;
    private BoundLine boudLineFront;

    public DoubleBoundLine()
    {
        SetBack(new BoundLine());
        SetFront(new BoundLine());
    }

    public BoundLine GetBack(){return boudLineBack;}
    public BoundLine GetFront(){return boudLineFront;}

    public void SetBack(BoundLine boudLineBack){this.boudLineBack = boudLineBack;}
    public void SetFront(BoundLine boudLineFront){this.boudLineFront = boudLineFront;}
}

