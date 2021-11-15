public enum TypeConsommable {

    VIE("Vie"), MANA("Mana"), EXP("EXP"), DEGAT("Dégats");

    private String type;

    TypeConsommable(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
