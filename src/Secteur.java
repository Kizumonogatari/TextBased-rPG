import java.util.ArrayList;
import java.util.List;

public class Secteur implements Comparable<Secteur>
{
    private Position position;

    private String nom;

    private String description;

    private List<Entite> collEntite;

    public Secteur(Position position, String nom, String description)
    {
        this.position = position;
        this.nom = nom;
        this.description = description;

        this.collEntite = new ArrayList<>();
    }

    public void supprimerEntite(Entite e)
    {
        this.collEntite.remove(this.collEntite.indexOf(e));
    }

    public void ajouterEntite(Entite e)
    {
        if (this.collEntite.indexOf(e) == -1)
        {
            this.collEntite.add(e);
            if (e instanceof Joueur)
            {
                ((Joueur) e).setPosition(this.position);
            }
        }
    }

    public Position getPosition()
    {
        return this.position;
    }

    @Override
    public int compareTo(Secteur s)
    {
        int ret = this.position.getX() - s.position.getX();
        if (ret == 0)
        {
            ret = this.position.getY() - s.position.getY();
        }
        return ret;
    }

    public String afficherCaractere()
    {
        for (Entite uneEntite : this.collEntite)
        {
            if (uneEntite instanceof Joueur)
            {
                return "J";
            }
        }
        return " ";
    }

    @Override
    public String toString()
    {
        StringBuilder chaine = new StringBuilder();

        chaine.append("[");
        chaine.append(this.nom);
        if (!this.description.trim().isEmpty())
        {
            chaine.append(": ");
            chaine.append(this.description);
        }
        chaine.append(" ");
        chaine.append("Pos: ");
        chaine.append(this.position);
        chaine.append("]");

        return chaine.toString();
    }
}
