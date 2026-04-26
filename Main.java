public class Main {
    public static void main(String[] args) {
        Espace espace = new Espace("Système solaire");
        espace.ajouterAstre("A1", "", 100, 10, 15, 30, 0, 0, 0, 0, 50, 50);
        espace.ajouterAstre("A2", "", 100, 20, 15, 30, 0, 0, 0, 0, 100, 70);
        espace.ajouterAstre("A1", "", 100, 25, 30, 30, 0, 0, 0, 0, 150, 50);
        Fenetre ma_fenetre = new Fenetre(espace);
    }
}
