public class Arme extends Equipement {

    private Integer degat;

    public Arme(String nom, String description, Integer forceCondition, Integer intelligenceCondition, Integer degat) {
        super(nom, description, forceCondition, intelligenceCondition);
        this.degat = degat;
    }

    public Integer getDegat() {
        return this.degat;
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();

        chaine.append(super.toString());
        chaine.append("\n\tDégat(s): ");
        chaine.append(this.degat);

        return chaine.toString();
    }
}
