public class Armure extends Equipement {
    private Integer defense;

    public Armure(String nom, String description, Integer forceCondition, Integer intelligenceCondition, Integer defense) {
        super(nom, description, forceCondition, intelligenceCondition);
        this.defense = defense;
    }

    public Integer getDefense() {
        return this.defense;
    }
}
