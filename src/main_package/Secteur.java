package main_package;

import java.util.ArrayList;
import java.util.List;

public class Secteur implements Comparable<Secteur>
{
    private Position position;

    private String nom;

    private String description;

    private List<Entite> collEntite;

    private List<ActionsPossible> actionsPossible;

    public Secteur(Position position, String nom, String description)
    {
        this.position = position;
        this.nom = nom;
        this.description = description;

        this.collEntite = new ArrayList<>();
        this.actionsPossible = new ArrayList<>();
    }

    public void supprimerEntite(Entite e)
    {
        this.collEntite.remove(e);
    }

    public void ajouterEntite(Entite e)
    {
        if (!this.collEntite.contains(e))
        {
            this.collEntite.add(e);
            if (e instanceof Joueur)
            {
                ((Joueur) e).setPosition(this.position);
            }
            this.mettreAJourActionsPossible();
        }
    }

    public Position getPosition()
    {
        return this.position;
    }

    public List<ActionsPossible> getActionsPossible() {
        return this.actionsPossible;
    }

    public void mettreAJourActionsPossible() {
        this.actionsPossible.clear();
        //Check liste entite

        this.actionsPossible.add(ActionsPossible.DEPLACEMENT);

        for(Entite uneEntite : this.collEntite) {
            if(uneEntite instanceof Monstre && !this.actionsPossible.contains(ActionsPossible.COMBATTRE)) {
                this.actionsPossible.add(ActionsPossible.COMBATTRE);
            }
        }

        this.actionsPossible.add(ActionsPossible.INVENTAIRE);
        this.actionsPossible.add(ActionsPossible.INFORMATIONS_PERSONNAGE);
        this.actionsPossible.add(ActionsPossible.SAUVERGARDER);
        this.actionsPossible.add(ActionsPossible.QUITTER);
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
