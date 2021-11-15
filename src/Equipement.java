public class Equipement extends Objet {
    private final Integer forceCondition;
    private final Integer intelligenceCondition;

    public Equipement(String nom, String description, Integer forceCondition, Integer intelligenceCondition) {
        super(nom, description);
        this.forceCondition = forceCondition;
        this.intelligenceCondition = intelligenceCondition;
    }

    public Integer getConditionForce() {
        return this.forceCondition;
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
