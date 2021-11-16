package main_package;

public class MonstreFactory {
    public Monstre creerMonstre(TypeMonstre typeMonstre) {
        Monstre tempMonstre = null;
        switch (typeMonstre) {
            case LOUP:
                return new Loup();
            case SQUELETTE:
            default:
                return null;
        }
    }
}
