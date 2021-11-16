package main_package;

import java.util.Arrays;
import java.util.List;

public class Jeu
{
    private Zone zoneActive;

    private static final Jeu instanceJeu = new Jeu();

    private Joueur leJoueur;

    private Jeu()
    {
        leJoueur = null;
        zoneActive = null;
    }

    public static Jeu getInstance()
    {
        return instanceJeu;
    }

    public void initialisation()
    {
        String nom;

        //TODO: Texte de bienvenue
        System.out.println("Message bienvenue blablabla");

        nom = Utilities.saisieChaineUtilisateur("Veuillez entrer le nom du héros: ");
        leJoueur = new Joueur(nom, 10, 5, 2, 2, 110, 0, 1);
        zoneActive = new Zone("Plateau du prélude", "Une plaine et quelques forêts éparses trône sur cette région\nQuelques animaux sauvages y résident.");

        Secteur monSecteur = zoneActive.getSecteur(this.leJoueur.getPosition().getX(), this.leJoueur.getPosition().getY());
        monSecteur.ajouterEntite(leJoueur);

        // TODO: Texte d'intro
        System.out.println("Vous êtes " + nom);
    }

    public void lancerLaBoucleDeJeu()
    {
        boolean estEnAttenteDeShutdown = false;

        while(!estEnAttenteDeShutdown) {
            ActionsPossible actionsPossible = null;

            System.out.println(zoneActive.afficherZone());

            do {
                actionsPossible = saisieChoixGeneral();
            } while(actionsPossible == null);

            switch (actionsPossible) {
                case DEPLACEMENT:
                    leJoueur.seDeplacer(zoneActive, deplacerJoueur());
                    break;
                case COMBATTRE:
                    //TODO: Liste d'entite (leJoueur + liste entite Monstre du Secteur)
                    //Combat unCombat = new Combat();
                    break;
                case INVENTAIRE:
                    //TODO: Faire une fonction avec demande du type d'objet à afficher (Consommable, arme, armure, etc...)
                    //saisieDeQuelqueChose()
                    System.out.println(leJoueur.getInventaire().afficherObjets(""));
                    break;
                case INFORMATIONS_PERSONNAGE:
                    StringBuilder chaine = new StringBuilder();
                    chaine.append("Informations:\n");
                    chaine.append(leJoueur.toString());
                    chaine.append(leJoueur.afficherStatistiques());
                    chaine.append(leJoueur.afficherEquipement());
                    System.out.println(chaine);
                    break;
                case SAUVERGARDER:
                    //TODO: Implémentation de la sauvegarde
                    break;
                case QUITTER:
                    //TODO: Quitter le jeu (Demanade de confirmation de sauvegarde?)
                    estEnAttenteDeShutdown = true;
                    break;
                default:
                    System.err.println("Porblème de logique dans la Class Jeu.java, lancerLaBoucleDeJeu, switch actionPossible");
                    break;
            }
        }
        System.out.println("Merci d'avoir joué!");
    }

    private ActionsPossible saisieChoixGeneral() {
        List<ActionsPossible> listeActionsPossibles = zoneActive.getSecteur(this.leJoueur.getPosition()).getActionsPossible();
        StringBuilder chaine = new StringBuilder();
        Integer index = 1;

        for(ActionsPossible uneAction : listeActionsPossibles) {
            chaine.append("[");
            chaine.append(index);
            chaine.append(" - ");
            chaine.append(uneAction.getLibelle());
            chaine.append("] ");
            index++;
        }
        System.out.println(chaine);

        do {
            index = Utilities.saisieNumeriqueUtilisateur("Votre choix: ");
        } while (index <= 0 || index > listeActionsPossibles.size());

        return listeActionsPossibles.get(index-1);
    }

    private Direction deplacerJoueur() {
        //TODO: Vérifier que se déplacer dans une direction est possible (faire une foncion qui teste chaque direction et renvois si c'est possible ou pas)
        StringBuilder chaine = new StringBuilder();
        Integer index = 1;

        for(Direction uneDirection : Direction.values()) {
            chaine.append("[");
            chaine.append(index);
            chaine.append(" - ");
            chaine.append(uneDirection.name());
            chaine.append("] ");
            index++;
        }
        System.out.println(chaine);

        do {
            index = Utilities.saisieNumeriqueUtilisateur("Votre choix: ");
        } while(index <= 0 || index > Direction.values().length);

        return Arrays.asList(Direction.values()).get(index-1);
    }
}
