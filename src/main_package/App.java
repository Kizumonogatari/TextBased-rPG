package main_package;

public class App
{
    public static void main(String[] args)
    {
        Jeu monJeu = Jeu.getInstance();
        monJeu.initialisation();
        monJeu.lancerLaBoucleDeJeu();
        System.exit(0);
    }
}
