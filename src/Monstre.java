public class Monstre extends Entite {

    public Monstre(String nom, Integer vie, Integer mana, Integer force, Integer intelligence, Integer initiative, Integer defense, Integer niveau) {
        super(nom, vie, mana, force, intelligence, initiative, defense, niveau);
    }

    public void combatAI(Entite e) {
        super.attack(e);
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();

        chaine.append(super.toString());

        return chaine.toString();
    }

}
