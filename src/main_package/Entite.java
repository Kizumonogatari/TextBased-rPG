package main_package;

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
        // TODO: Faire une méthode qui renvois le total de défense via armure

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
        //TODO: Amélioration des stats
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

    public void setForce(Integer force) {
        this.force = force;
    }

    public Integer getIntelligence()
    {
        return this.intelligence;
    }

    public void setIntelligence(Integer intelligence)
    {
        this.intelligence = intelligence;
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

    public String afficherStatistiques() {
        StringBuilder chaine = new StringBuilder();

        chaine.append("[Force: ");
        chaine.append(this.force);
        chaine.append("\tIntelligence: ");
        chaine.append(this.intelligence);
        chaine.append("\tInitiative: ");
        chaine.append(this.initiative);
        chaine.append("]\n");

        return chaine.toString();
    }

    public String afficherEquipement() {
        StringBuilder chaine = new StringBuilder();

        for(Map.Entry<Emplacement, Equipement> unEquipement : this.collEquipements.entrySet()) {
            chaine.append("\t[");
            chaine.append(unEquipement.getKey().getNom());
            chaine.append(": ");
            if(unEquipement.getValue() != null) {
                chaine.append(unEquipement.getValue().getNom());
                if(unEquipement.getValue() instanceof Arme) {
                    chaine.append("\tDégats: ");
                    chaine.append(((Arme) unEquipement.getValue()).getDegat());
                } else if(unEquipement.getValue() instanceof Armure) {
                    chaine.append("\tDéfense: ");
                    chaine.append(((Armure) unEquipement.getValue()).getDefense());
                }
            } else {
                chaine.append("Rien");
            }
            chaine.append("]\n");
        }

        return chaine.toString();
    }

    public String afficherCombat()
    {
        StringBuilder chaine = new StringBuilder();

        chaine.append(this.nom);
        chaine.append("\t\tPdV: ");
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
        chaine.append("[Nom: ");
        chaine.append(this.nom);

        chaine.append("\tVie:");
        chaine.append(this.vie);
        chaine.append("/");
        chaine.append(this.vieMax);

        chaine.append("\tMana: ");
        chaine.append(this.mana);
        chaine.append("/");
        chaine.append(this.manaMax);

        chaine.append("\t Nv.: ");
        chaine.append(this.niveau);
        chaine.append(" XP: ");
        chaine.append(this.experience);
        chaine.append("/");
        chaine.append(this.experienceProchainNiveau);
        chaine.append("]\n");

        return chaine.toString();
    }
}
