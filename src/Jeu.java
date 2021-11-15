import javax.rmi.CORBA.Util;
import java.util.*;

public class Jeu {

    private static final Jeu instanceJeu = new Jeu();

    Joueur leJoueur;

    private Jeu() {
        leJoueur = null;
    }

    public static Jeu getInstance() {
        return instanceJeu;
    }

    public void initialisation() {
        String nom;

        System.out.println("Message bienvenue blablabla");
        nom = Utilities.saisieChaineUtilisateur("Veuillez entrer le nom du héros: ");
        leJoueur = new Joueur(nom, 10, 5, 2,2, 110,0,1);

        //TODO: Texte d'intro
        System.out.println("Vous êtes " + nom);
        lancerLaBoucleDeJeu();
    }

    public void lancerLaBoucleDeJeu() {
        //TODO: boucle de jeu
        System.out.println("Pas encore fait.");

        List<Entite> listEntite = new ArrayList<>();

        listEntite.add(leJoueur);
        listEntite.add(new Monstre("Loup", 7, 3, 1, 0,75,0, 1));

        this.engagerCombat(listEntite);

    }

    //TODO: En faire un objet? Trèèèèèèèèèèèès probablement
    private void engagerCombat(List<Entite> combattants) {
        ChoixCombat choix;

        Collections.sort(combattants, Comparator.comparingInt(Entite::getInitiative).reversed());

        while(Utilities.compterOccurenceMonstre(combattants) != 0) {
            for(Iterator<Entite> iterator = combattants.iterator(); iterator.hasNext();) {
                StringBuilder chaine = new StringBuilder();
                Entite uneEntite = iterator.next();

                if(uneEntite instanceof Joueur) {
                    if(!uneEntite.estVivant()) {
                        //TODO: gameover();
                    }
                    System.out.println(uneEntite.afficherCombat());
                    do {
                        choix = Utilities.saisieChoixCombat();
                    } while(choix == null);

                    switch (choix) {
                        case ATTAQUE:
                            Monstre tempMonstre = Utilities.saisieChoixMonstre(combattants);
                            chaine.append("Vous infligez ");
                            chaine.append(uneEntite.attack(tempMonstre));
                            chaine.append(" point(s) de dégats à: ");
                            chaine.append(tempMonstre.getNom());
                            chaine.append("\n");
                            System.out.println(chaine.toString());
                            System.out.println(tempMonstre.afficherCombat());
                            break;
                        case OBJETS:
                            Consommable leConsommable = Utilities.saisieChoixConsommable((Joueur) uneEntite);
                            leConsommable.utiliser(uneEntite);
                            break;
                        case FUITE:
                            //TODO: Fuite du combat
                            break;
                        default:
                            System.out.println("Grosse erreur de logique!");
                    }
                    chaine.append("--- Fin de votre tour ---");
                } else {
                    //TODO: Implement AI
                    if(!uneEntite.estVivant()) {
                        //TODO: mort du monstre, gain xp, etc...
                        iterator.remove();
                    } else {
                        uneEntite.attack(Utilities.getJoueurFromCollection(combattants));
                        System.out.println("Le monstre attaque");
                    }

                }
            }
        }
        //TODO: Résultat de fin de combat (EXP, LOOT?)
        System.out.println("Fin du combat");
    }
}
