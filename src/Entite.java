import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Entite
{
    private String nom;

    private Integer vie;

    private Integer vieMax;

    private Integer mana;

    private Integer manaMax;

    private Integer force;

    private Integer intelligence;

    private Integer initiative;

    private Integer niveau;

    private Integer experience;

    private Integer experienceProchainNiveau;

    private Integer defense;

    private Inventaire inventaire;

    protected Map<Emplacement, Equipement> collEquipements;

    public Entite(String nom, Integer vie, Integer mana, Integer force, Integer intelligence, Integer initiative, Integer defense,
        Integer niveau)
    {
        this.nom = nom;
        this.vie = vie;
        this.vieMax = vie;
        this.mana = mana;
        this.manaMax = mana;
        this.force = force;
        this.intelligence = intelligence;
        this.initiative = initiative;
        this.niveau = niveau;
        this.experience = 0;
        this.experienceProchainNiveau = 100;
        this.defense = defense;

        // Initialisation
        inventaire = new Inventaire();
        this.collEquipements = new HashMap<>();

        // Mise en place du systeme d'equipement
        for (Emplacement unEmplacement : Emplacement.values())
        {
            this.collEquipements.put(unEmplacement, null);
        }
    }

    public Integer attack(Entite e)
    {
        Integer degatsInfliges = 0;

        // TODO: Formule de dégat pour les entités
        Arme arme = (Arme) this.collEquipements.get(Emplacement.MAIN_PRINCIPAL);
        if (arme != null)
        {
            degatsInfliges += arme.getDegat();
        }
        else
        {
            degatsInfliges += 1;
        }

        // TODO: Formule de défense pour encaisser les dégats
        degatsInfliges -= e.defense;

        // Attaque de l'entite adverse
        e.receiveHit(degatsInfliges);

        return degatsInfliges;
    }

    public void receiveHit(Integer hit)
    {
        if (vie - hit < 0)
        {
            this.vie = 0;
        }
        else
        {
            this.vie -= hit;
        }
    }

    public void setVieMax(Integer vieMax)
    {
        this.vieMax = vieMax;
    }

    public void setManaMax(Integer manaMax)
    {
        this.manaMax = manaMax;
    }

    public void appliquerSoin(Integer soin)
    {
        if (soin + this.vie > this.vieMax)
        {
            this.vie = this.vieMax;
        }
        else
        {
            this.vie += soin;
        }
    }

    public void appliquerRechargeMana(Integer mana)
    {
        if (mana + this.mana > this.manaMax)
        {
            this.mana = this.manaMax;
        }
        else
        {
            this.mana += mana;
        }
    }

    public void gainExperience(Integer pointsExperience)
    {
        if (pointsExperience + this.experience > this.experienceProchainNiveau)
        {
            this.niveauSuperieur(pointsExperience + this.experience - experienceProchainNiveau);
        }
        else
        {
            this.experience += pointsExperience;
        }
    }

    private void niveauSuperieur(Integer experienceRestant)
    {
        this.niveau += 1;
        this.experience = 0;
        this.experienceProchainNiveau += 100;
        if (experienceRestant > 0)
        {
            this.gainExperience(experienceRestant);
        }
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getNom()
    {
        return this.nom;
    }

    public Integer getForce()
    {
        return this.force;
    }

    public Integer getIntelligence()
    {
        return this.intelligence;
    }

    public Integer getInitiative()
    {
        return this.initiative;
    }

    public boolean estVivant()
    {
        return this.vie > 0;
    }

    public Inventaire getInventaire()
    {
        return this.inventaire;
    }

    public String afficherCombat()
    {
        StringBuilder chaine = new StringBuilder();

        chaine.append(this.nom);
        chaine.append("\tPdV: ");
        chaine.append(this.vie);
        chaine.append("/");
        chaine.append(this.vieMax);
        chaine.append("\tMana: ");
        chaine.append(this.mana);
        chaine.append("/");
        chaine.append(this.manaMax);
        chaine.append("\t\tNv.:");
        chaine.append(this.niveau);

        chaine.append("\n[");
        chaine.append(String.join("", Collections.nCopies(Utilities.calculPourcentage(this.vieMax, this.vie), ":")));
        chaine.append(String.join("", Collections.nCopies(100 - Utilities.calculPourcentage(this.vieMax, this.vie), ".")));
        chaine.append("]");

        return chaine.toString();
    }

    @Override
    public String toString()
    {
        StringBuilder chaine = new StringBuilder();
        chaine.append("Nom: ");
        chaine.append(this.nom);

        chaine.append("\n\t\tVie:");
        chaine.append(this.vie);
        chaine.append("/");
        chaine.append(this.vieMax);

        chaine.append(" - ");
        chaine.append("Mana: ");
        chaine.append(this.mana);
        chaine.append("/");
        chaine.append(this.manaMax);

        for (Equipement unEquipement : this.collEquipements.values())
        {
            if (unEquipement != null)
            {
                chaine.append("\n");
                chaine.append(unEquipement);
            }
        }

        return chaine.toString();
    }
}
