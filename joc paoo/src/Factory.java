import java.io.IOException;

public class Factory {
    private RefLinks refLinks;
    public Factory(){
    }

    public void setRefLinks(RefLinks refLinks) {
        this.refLinks = refLinks;
    }
    public SpriteBase GetSprite(String type, String subtype) throws IOException {

        if(type.equals("bird"))
        {
            if(subtype.equals("purple"))
                return new BirdPurple(refLinks);
            else
                if(subtype.equals("bean"))
                    return new BirdBean(refLinks);
        }
        else if(type.equals("egg"))
            return new Egg();
        return null;
    }
}
