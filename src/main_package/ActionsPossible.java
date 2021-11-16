package main_package;

public enum ActionsPossible {
    DEPLACEMENT("Se déplacer"), COMBATTRE("Engager un combat"), INVENTAIRE("Inventaire"), INFORMATIONS_PERSONNAGE("Informations du personnage"), SAUVERGARDER("Sauvegarder"), QUITTER("Quitter");

    private String libelle;

    ActionsPossible(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return this.libelle;
    }
}
