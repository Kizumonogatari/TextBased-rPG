package main_package;

import java.util.Map;
import java.util.HashMap;

public class Inventaire {

    private Map<Integer, Objet> collObjets;

    public Inventaire() {
        this.collObjets = new HashMap<>();
    }

    public void ajouterObjet(Integer objetId, String nom, String description, Integer forceCondition, Integer intelligenceCondition, Emplacement emplacement) {
        Objet tempObjet = this.getObjet(objetId);
        if(tempObjet != null) {
            tempObjet.setQuantite(tempObjet.getQuantite() + 1);
        } else {
            this.collObjets.put(objetId, new Equipement(objetId, nom, description, forceCondition, intelligenceCondition, emplacement));
        }

    }

    public void ajouterObjet(Integer objetId, String nom, String description, Integer quantite, TypeConsommable typeConsommable, Integer points) {
        Objet tempObjet = this.getObjet(objetId);
        if(tempObjet != null) {
            tempObjet.setQuantite(tempObjet.getQuantite() + quantite);
        } else {
            this.collObjets.put(objetId, new Consommable(objetId, nom, description, quantite, typeConsommable, points));
        }
    }

    public void ajouterObjet(Equipement equipement) {
        this.collObjets.put(equipement.getObjetId(), equipement);
    }

    public Map<Integer, Objet> getCollObjets() {
        return this.collObjets;
    }

    public void retirerObjet(Integer objectId) {
        Objet unObjet = getObjet(objectId);
        if(unObjet != null) {
            if(unObjet.getQuantite() >= 1) {
                unObjet.setQuantite(unObjet.getQuantite() - 1);
            }

            if(unObjet.getQuantite() == 0) {
                this.collObjets.remove(objectId);
            }
        }
    }

    private Objet getObjet(Integer objetId) {
        return this.collObjets.get(objetId);
    }

    private TypeObjet controlerTypeObjet(String typeObjet) throws TypeObjetException {
        if(typeObjet != null && !typeObjet.trim().isEmpty()) {
            for(TypeObjet unTypeObjet : TypeObjet.values()) {
                if(unTypeObjet.name().toLowerCase().compareTo(typeObjet.toLowerCase()) == 0) {
                    return unTypeObjet;
                }
            }
        }
        throw new TypeObjetException("Le type objet `" + typeObjet + "` n'existe pas.");
    }

    public String afficherObjets(String typeObjet) {
        StringBuilder chaine = new StringBuilder();
        try {
            for(Objet unObjet : this.collObjets.values()) {
                if(typeObjet != null && !typeObjet.trim().isEmpty()) {
                    controlerTypeObjet(typeObjet);
                    if(unObjet.getClass().getSimpleName().toLowerCase().compareTo(typeObjet.toLowerCase()) == 0)
                    {
                        chaine.append(unObjet);
                        chaine.append("\n");
                    }
                } else {
                    chaine.append(unObjet);
                    chaine.append("\n");
                }
            }
        } catch (TypeObjetException e) {
            chaine.append("Erreur: " + e.getMessage());
        }
        return chaine.toString();
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();

        return chaine.toString();
    }
}
