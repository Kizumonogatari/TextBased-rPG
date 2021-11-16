package main_package;

public enum Emplacement {
    MAIN_PRINCIPAL("Main principale"), TETE("Tête"), BUSTE("Buste"), JAMBES("Jambes");

    private String nom;

    Emplacement(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }
}
