public class Equipement extends Objet {
    private final Integer forceCondition;
    private final Integer intelligenceCondition;
    private final Emplacement emplacement;

    public Equipement(Integer objetId, String nom, String description, Integer forceCondition, Integer intelligenceCondition, Emplacement emplacement) {
        super(objetId, nom, description, 1);
        this.forceCondition = forceCondition;
        this.intelligenceCondition = intelligenceCondition;
        this.emplacement = emplacement;
    }

    public Integer getConditionForce() {
        return this.forceCondition;
    }

    public void utiliser(Entite e) {
        if(e instanceof Joueur) {
            ((Joueur) e).equiperEquipement(this.emplacement, this);
        }
    }

    public Integer getConditionIntelligence() {
        return this.intelligenceCondition;
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();

        chaine.append(super.toString());

        chaine.append("\n\tConditions nécéssaire:\n`\t\tForce: ");
        chaine.append(this.forceCondition);
        chaine.append(" | Intelligence: ");
        chaine.append(this.intelligenceCondition);

        return chaine.toString();
    }
}
