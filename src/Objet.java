public abstract class Objet {
    private Integer objetId;
    private String nom;
    private String description;
    private Integer quantite;

    public Objet(Integer objetId, String nom, String description, Integer quantite) {
        this.objetId = objetId;
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
    }

    public abstract void utiliser(Entite e);

    public Integer getObjetId() {
        return this.objetId;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getQuantite() {
        return this.quantite;
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
