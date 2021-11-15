public enum ChoixCombat {
    ATTAQUE(1, "Attaquer"), OBJETS(2, "Objets"), FUITE(3, "Fuite");

    private Integer numero;
    private String nom;

    ChoixCombat(Integer numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public String getNom() {
        return this.nom;
    }
}
