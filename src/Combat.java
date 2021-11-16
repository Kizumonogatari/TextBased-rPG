import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Combat
{
    private Integer pointExperiencesGagne;

    private ChoixCombat choix;

    private Integer tour;

    private List<Entite> collEntite;

    public Combat(List<Entite> entite)
    {
        this.tour = 0;
        this.pointExperiencesGagne = 0;
        this.collEntite = new ArrayList<>(entite);
    }

    public void lancerBoucleDeCombat()
    {
        Collections.sort(this.collEntite, Comparator.comparingInt(Entite::getInitiative).reversed());
        while (this.compterOccuranceDeMonstre() > 0)
        {
            System.out.println("Tour numéro: " + this.tour);
            for (Iterator<Entite> iterator = this.collEntite.iterator(); iterator.hasNext();)
            {
                StringBuilder chaine = new StringBuilder();
                Entite uneEntite = iterator.next();

                if (uneEntite instanceof Joueur)
                {
                    if (!uneEntite.estVivant())
                    {
                        // TODO: Fin de partie
                        // Dans les plans: Retour à un point de sauvegarde (Auberge?)
                    }

                    // TODO: Faire une machine à étaaaaaat, gestion parfaite
                    // Pourquoi ? Gestion du retour en arrière (Exemple: choix -> attaque -> retour -> choix consommable...)
                    do
                    {
                        choix = this.saisieChoixCombat();
                    }
                    while (choix == null);

                    switch (choix)
                    {
                        case ATTAQUE:
                            Monstre tempMonstre = this.saisieChoixMonstre(this.collEntite);
                            chaine.append("Vous infligez ");
                            chaine.append(uneEntite.attack(tempMonstre));
                            chaine.append(" point(s) de dégats à: ");
                            chaine.append(tempMonstre.getNom());
                            chaine.append("\n");
                            System.out.println(chaine.toString());
                            System.out.println(tempMonstre.afficherCombat());
                            break;
                        case OBJETS:
                            Consommable leConsommable = this.saisieChoixConsommable((Joueur) uneEntite);
                            leConsommable.utiliser(uneEntite);
                            break;
                        case FUITE:
                            // TODO: Fuite du combat
                            break;
                        default:
                            System.out.println("Grosse erreur de logique!");
                            break;
                    }

                    System.out.println("Fin de votre tour");
                }
                else
                {
                    if (!uneEntite.estVivant())
                    {
                        iterator.remove();
                    }
                    else
                    {
                        uneEntite.attack(this.getJoueurFromCollection(this.collEntite));
                        System.out.println("Le monstre attaque");
                    }
                }
            }
            this.tour++;
        }
        // TODO: Résultat de fin de combat (EXP, LOOT?)
        System.out.println("Fin du combat");
    }

    private Integer compterOccuranceDeMonstre()
    {
        Integer compteur = 0;
        for (Entite uneEntite : this.collEntite)
        {
            if (uneEntite instanceof Monstre)
            {
                compteur++;
            }
        }
        return compteur;
    }

    private Joueur getJoueurFromCollection(List<Entite> listeEntite)
    {
        for (Entite uneEntite : listeEntite)
        {
            if (uneEntite instanceof Joueur)
            {
                return (Joueur) uneEntite;
            }
        }
        return null;
    }

    private ChoixCombat saisieChoixCombat()
    {
        if (ChoixCombat.values().length > 0)
        {
            StringBuilder chaine = new StringBuilder();
            chaine.append("Plusieurs possibilités s'offre à vous\n");
            for (ChoixCombat unChoixDeCombat : ChoixCombat.values())
            {
                chaine.append("[");
                chaine.append(unChoixDeCombat.getNumero());
                chaine.append(" - ");
                chaine.append(unChoixDeCombat.getNom());
                chaine.append("] ");
            }

            System.out.println(chaine);
            try
            {
                Integer saisie = Utilities.saisieNumeriqueUtilisateur("Choisir une action: ");
                switch (saisie)
                {
                    case 1:
                        return ChoixCombat.ATTAQUE;
                    case 2:
                        return ChoixCombat.OBJETS;
                    case 3:
                        return ChoixCombat.FUITE;
                    default:
                        return null;
                }
            }
            catch (Exception ignored)
            {
                return null;
            }
        }
        else
        {
            // TODO: Exception si y'a rien dans l'enum ChoixCombat
            return null;
        }
    }

    public Monstre saisieChoixMonstre(List<Entite> listeEntite)
    {
        Integer i = 1;
        Monstre[] listeMonstre = new Monstre[listeEntite.size() - 1];
        StringBuilder chaine = new StringBuilder();
        chaine.append("Ennemi(s): \n");
        for (Entite uneEntite : listeEntite)
        {
            if (uneEntite instanceof Monstre)
            {
                listeMonstre[i - 1] = (Monstre) uneEntite;
                chaine.append("[");
                chaine.append(i);
                chaine.append(" - ");
                chaine.append(uneEntite.getNom());
                chaine.append("] ");
                i++;
            }
        }
        System.out.println(chaine);

        do
        {
            i = Utilities.saisieNumeriqueUtilisateur("Quel ennemi souhaitez-vous attaquer? ");
        }
        while (i <= 0 || i > listeMonstre.length);

        return listeMonstre[i - 1];
    }

    public Consommable saisieChoixConsommable(Joueur joueur)
    {
        StringBuilder chaine = new StringBuilder();
        List<Consommable> listeConsommable = new ArrayList<>();
        Integer i = 1;

        // TODO: Déplacer ce bloc dans la classe inventaire directement
        chaine.append("Consommable disponible:\n");
        for (Objet unObjet : joueur.getInventaire().getCollObjets().values())
        {
            if (unObjet instanceof Consommable)
            {
                listeConsommable.add((Consommable) unObjet);
                chaine.append(i);
                chaine.append(" - ");
                chaine.append(unObjet);
                chaine.append("\n");
            }
        }
        System.out.print(chaine);
        do
        {
            i = Utilities.saisieNumeriqueUtilisateur("Quel objet utiliser? ");
        }
        while (i <= 0 || i > listeConsommable.size());

        return listeConsommable.get(i - 1);
    }

}
