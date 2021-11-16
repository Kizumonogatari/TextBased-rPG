public class Joueur extends Entite
{

    private Position position;

    public Joueur(String nom, Integer vie, Integer mana, Integer force, Integer intelligence, Integer initiative, Integer defense,
        Integer niveau)
    {
        super(nom, vie, mana, force, intelligence, initiative, defense, niveau);
        position = new Position(2, 2);
    }

    public Position getPosition()
    {
        return this.position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    // TODO: Refaire la logique éventuellement
    public Equipement equiperEquipement(Emplacement emplacement, Equipement equipement)
    {
        if (this.getForce() >= equipement.getConditionForce() && this.getIntelligence() >= equipement.getConditionIntelligence())
        {
            Equipement tempEquipement = this.collEquipements.get(emplacement);
            if (tempEquipement != null)
            {
                this.getInventaire().ajouterObjet(equipement);
            }
            this.getInventaire().retirerObjet(equipement.getObjetId());
            return this.collEquipements.put(emplacement, equipement);
        }
        else
        {
            return null;
        }
    }
}
