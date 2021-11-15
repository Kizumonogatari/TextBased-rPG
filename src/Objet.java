public abstract class Objet {
    private String nom;
    private String description;

    public Objet(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();

        chaine.append(this.nom);
        chaine.append("\n\t");
        chaine.append(this.description);

        return chaine.toString();
    }
}
