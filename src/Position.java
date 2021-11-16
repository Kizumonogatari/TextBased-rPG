
public class Position
{
    private Integer x;

    private Integer y;

    public Position()
    {
        x = 0;
        y = 0;
    }

    public Position(Integer x, Integer y)
    {
        this.x = x;
        this.y = y;
    }

    public Integer getX()
    {
        return this.x;
    }

    public Integer getY()
    {
        return this.y;
    }

    @Override
    public String toString()
    {
        StringBuilder chaine = new StringBuilder();

        chaine.append(this.x);
        chaine.append(",");
        chaine.append(this.y);

        return chaine.toString();
    }
}
