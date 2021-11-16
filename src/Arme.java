public class Arme extends Equipement {

    private Integer degat;

    public Arme(Integer objetId, String nom, String description, Integer forceCondition, Integer intelligenceCondition, Emplacement emplacement, Integer degat) {
        super(objetId, nom, description, forceCondition, intelligenceCondition, emplacement);
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
