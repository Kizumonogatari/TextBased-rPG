import java.util.List;
import java.util.Scanner;

public class Utilities {
    public static String dialogueBordure(String chaine) {
        Integer chaineLaPlusLongue = 0;
        StringBuilder chaineBordure = new StringBuilder();

        String[] tabChaine = chaine.split("\n");
        for(String uneChaine : tabChaine) {
            if(uneChaine.length() > chaineLaPlusLongue) {
                chaineLaPlusLongue = uneChaine.length();
            }
            chaineBordure.append("* ");
            chaineBordure.append(uneChaine);
            chaineBordure.append("\n");
        }

        StringBuilder chaineBordureFinal = new StringBuilder();
        chaineBordureFinal.append("<-");
        for(int i = 0; i <= chaineLaPlusLongue; i++) {
            chaineBordureFinal.append('-');
        }
        chaineBordureFinal.append("->\n");
        chaineBordureFinal.append(chaineBordure);
        for(int i = 0; i <= chaineLaPlusLongue; i++) {
            chaineBordureFinal.append('-');
        }
        chaineBordure.append("\n");
        return chaineBordureFinal.toString();
    }

    public static ChoixCombat saisieChoixCombat() {
        if(ChoixCombat.values().length > 0) {
            StringBuilder chaine = new StringBuilder();
            chaine.append("Plusieurs possibilités s'offre à vous\n");
            for(ChoixCombat unChoixDeCombat : ChoixCombat.values()) {
                chaine.append(unChoixDeCombat.getNumero());
                chaine.append(": ");
                chaine.append(unChoixDeCombat.getNom());
                chaine.append("\n");
            }

            //TODO: Formattage du texte
            System.out.print(chaine);
            try {
                Integer saisie = saisieNumeriqueUtilisateur("Choisir une action: ");
                switch (saisie) {
                    case 1:
                        return ChoixCombat.ATTAQUE;
                    case 2:
                        return ChoixCombat.OBJETS;
                    case 3:
                        return ChoixCombat.FUITE;
                    default:
                        return null;
                }
            } catch (Exception ignored) {
                return null;
            }
        } else {
            //TODO: Exception si y'a rien dans l'enum ChoixCombat
            return null;
        }
    }

    public static Monstre saisieChoixMonstre(List<Entite> listeEntite) {
        Integer i = 1;
        Monstre[] listeMonstre = new Monstre[listeEntite.size()-1];
        StringBuilder chaine = new StringBuilder();
        chaine.append("Ennemi(s): \n");
        for(Entite uneEntite : listeEntite) {
            if(uneEntite instanceof Monstre) {
                listeMonstre[i-1] = (Monstre)uneEntite;
                chaine.append(i);
                chaine.append(" - ");
                chaine.append(uneEntite.getNom());
                chaine.append("\n");
                i++;
            }
        }
        System.out.print(chaine);

        do {
           i = saisieNumeriqueUtilisateur("Quel ennemi souhaitez-vous attaquer? ");
        } while(i <= 0 || i > listeMonstre.length);

        return listeMonstre[i-1];
    }

    public static Joueur getJoueurFromCollection(List<Entite> listeEntite) {
        for(Entite uneEntite : listeEntite) {
            if(uneEntite instanceof Joueur) {
                return (Joueur)uneEntite;
            }
        }
        return null;
    }

    //TODO: Faire la classe inventaire, pour une meilleure gestion des objets
    public static Consommable saisieChoixConsommable() {
        return null;
    }

    public static Integer compterOccurenceMonstre(List<Entite> listeEntite) {
        Integer compteur = 0;
        for(Entite uneEntite : listeEntite) {
            if(uneEntite instanceof Monstre) {
                compteur++;
            }
        }
        return compteur;
    }

    public static String saisieChaineUtilisateur(String message) {
        System.out.print(message);
        Scanner monScanneur = new Scanner(System.in);
        return monScanneur.nextLine();
    }

    public static Integer saisieNumeriqueUtilisateur(String message) {
        Integer saisie = 0;

        System.out.print(message);
        Scanner monScanneur = new Scanner(System.in);
        try {
            saisie = monScanneur.nextInt();
        } catch (Exception ignored) {

        }
        return saisie;
    }

    public static Integer calculPourcentage(Integer total, Integer partie) {
        if(total > 0) {
            return (partie * 100) / total;
        } else {
            return 0;
        }

    }
}
