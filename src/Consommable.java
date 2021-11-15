public class Consommable extends Objet {

    private Integer id;
    private TypeConsommable typeConsommable;
    private Integer points;

    public Consommable(String nom, String description, Integer id, TypeConsommable typeConsommable, Integer points) {
        super(nom, description);
        this.id = id;
        this.typeConsommable = typeConsommable;
        this.points = points;
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();

        chaine.append(super.toString());
        if(typeConsommable == TypeConsommable.DEGAT) {
            chaine.append("\nInflige ");
        } else {
            chaine.append("\nRestaure ");
        }
        chaine.append(this.points);
        chaine.append(" point(s) de ");
        chaine.append(this.typeConsommable.getType());

        return chaine.toString();
    }

    public Consommable utiliser(Entite e) {
        switch (typeConsommable) {
            case VIE:
                e.appliquerSoin(this.points);
                break;
            case MANA:
                e.appliquerRechargeMana(this.points);
                break;
            case EXP:
                e.gainExperience(this.points);
                break;
            default:
                break;
        }
        return this;
    }

}
