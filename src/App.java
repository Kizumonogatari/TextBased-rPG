public class App
{
    public static void main(String[] args)
    {
        Jeu monJeu = Jeu.getInstance();
        monJeu.initialisation();
        monJeu.lancerLaBoucleDeJeu();
    }
}
