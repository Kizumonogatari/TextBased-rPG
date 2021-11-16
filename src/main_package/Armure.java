package main_package;

public class Armure extends Equipement {
    private Integer defense;

    public Armure(Integer objetId, String nom, String description, Integer forceCondition, Integer intelligenceCondition, Emplacement emplacement, Integer defense) {
        super(objetId, nom, description, forceCondition, intelligenceCondition, emplacement);
        this.defense = defense;
    }

    public Integer getDefense() {
        return this.defense;
    }
}
