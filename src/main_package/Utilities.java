package main_package;

import java.util.Scanner;

public class Utilities
{
    public static String dialogueBordure(String chaine)
    {
        Integer chaineLaPlusLongue = 0;
        StringBuilder chaineBordure = new StringBuilder();

        String[] tabChaine = chaine.split("\n");
        for (String uneChaine : tabChaine)
        {
            if (uneChaine.length() > chaineLaPlusLongue)
            {
                chaineLaPlusLongue = uneChaine.length();
            }
            chaineBordure.append("* ");
            chaineBordure.append(uneChaine);
            chaineBordure.append("\n");
        }

        StringBuilder chaineBordureFinal = new StringBuilder();
        chaineBordureFinal.append("<-");
        for (int i = 0; i <= chaineLaPlusLongue; i++)
        {
            chaineBordureFinal.append('-');
        }
        chaineBordureFinal.append("->\n");
        chaineBordureFinal.append(chaineBordure);
        for (int i = 0; i <= chaineLaPlusLongue; i++)
        {
            chaineBordureFinal.append('-');
        }
        chaineBordure.append("\n");
        return chaineBordureFinal.toString();
    }

    public static String saisieChaineUtilisateur(String message)
    {
        System.out.print(message);
        Scanner monScanneur = new Scanner(System.in);
        return monScanneur.nextLine();
    }

    public static Integer saisieNumeriqueUtilisateur(String message)
    {
        Integer saisie = 0;

        System.out.print(message);
        Scanner monScanneur = new Scanner(System.in);
        try
        {
            saisie = monScanneur.nextInt();
        }
        catch (Exception ignored)
        {

        }
        return saisie;
    }

    public static Integer calculPourcentage(Integer total, Integer partie)
    {
        if (total > 0)
        {
            return (partie * 100) / total;
        }
        else
        {
            return 0;
        }
    }

    public static void afficherTextColor()
    {
        String hello = "Hello world!";

        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW = "\u001B[33m";

        System.out.println(ANSI_YELLOW + " " + hello + ANSI_RESET);
    }
}
