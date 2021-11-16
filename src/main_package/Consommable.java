package main_package;

public class Consommable extends Objet
{
    private TypeConsommable typeConsommable;

    private Integer points;

    public Consommable(Integer objetId, String nom, String description, Integer quantite, TypeConsommable typeConsommable, Integer points)
    {
        super(objetId, nom, description, quantite);
        this.typeConsommable = typeConsommable;
        this.points = points;
    }

    @Override
    public String toString()
    {
        StringBuilder chaine = new StringBuilder();

        chaine.append(super.toString());
        if (typeConsommable == TypeConsommable.DEGAT)
        {
            chaine.append("\nInflige ");
        }
        else
        {
            chaine.append("\nRestaure ");
        }
        chaine.append(this.points);
        chaine.append(" point(s) de ");
        chaine.append(this.typeConsommable.name().toLowerCase());

        return chaine.toString();
    }

    public void utiliser(Entite e)
    {
        switch (typeConsommable)
        {
            case VIE:
                e.appliquerSoin(this.points);
                break;
            case MANA:
                e.appliquerRechargeMana(this.points);
                break;
            case EXPERIENCE:
                e.gainExperience(this.points);
                break;
            case DEGAT:
                e.receiveHit(this.points);
                break;
            default:
                break;
        }
    }

}
