public class Joueur extends Entite {

    public Joueur(String nom, Integer vie, Integer mana, Integer force, Integer intelligence, Integer initiative, Integer defense, Integer niveau) {
        super(nom, vie, mana, force, intelligence, initiative, defense, niveau);
        initJoueur();
    }

    private void initJoueur() {
        //this.collEquipements.put(Emplacement.MAIN_PRINCIPAL, new Arme("Petite épée", "Une simple épée toute fine.", 0, 0, 3));
        //this.collEquipements.put(Emplacement.BUSTE, new Armure("Cotes de mailles", "Basique mais fonctionnelle.",0, 0, 2));
    }

    //TODO: Refaire la logique éventuellement
    public Equipement equiperEquipement(Emplacement emplacement, Equipement equipement) {
        if(this.getForce() >= equipement.getConditionForce() && this.getIntelligence() >= equipement.getConditionIntelligence()) {
            Equipement tempEquipement = this.collEquipements.get(emplacement);
            if(tempEquipement != null) {
                this.getInventaire().ajouterObjet(equipement);
            }
            this.getInventaire().retirerObjet(equipement.getObjetId());
            return this.collEquipements.put(emplacement, equipement);
        } else {
            return null;
        }
    }
}
