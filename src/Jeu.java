public class Jeu
{
    private Zone zoneActive;

    private static final Jeu instanceJeu = new Jeu();

    Joueur leJoueur;

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
        System.out.println("Message bienvenue blablabla");
        nom = Utilities.saisieChaineUtilisateur("Veuillez entrer le nom du héros: ");
        leJoueur = new Joueur(nom, 10, 5, 2, 2, 110, 0, 1);

        zoneActive = new Zone("Zone #1", "Première zone du jeu");

        Secteur monSecteur = zoneActive.getSecteur(this.leJoueur.getPosition().getX(), this.leJoueur.getPosition().getY());
        monSecteur.ajouterEntite(leJoueur);

        // TODO: Texte d'intro
        System.out.println("Vous êtes " + nom);
    }

    public void lancerLaBoucleDeJeu()
    {
        System.out.println();
        System.out.println(zoneActive.afficherZone());
    }
}
