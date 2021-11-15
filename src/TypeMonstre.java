public enum TypeMonstre {

    SQUELETTE("Squelette", "Revenu d'entre les morts, on peut entendre leurs claquettis au loin."),
    LOUP("Loup", "Bête féroce et sauvage.");

    private String nom;
    private String description;

    TypeMonstre(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return this.description;
    }
}
