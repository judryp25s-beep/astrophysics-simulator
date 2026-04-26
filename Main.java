public class Main {
    public static void main(String[] args) {
        Espace espace = new Espace("Système solaire");
        espace.ajouterAstre("A1", "", 100, 100, 15, 30, 1000, 1000, 10, 20, 50, 50);
        espace.ajouterAstre("A2", "", 100, 20, 15, 30, 20, 20, 0, 0, 100, 70);
        espace.ajouterAstre("A3", "", 100, 25, 30, 30, 50, 50, 10, 10, 150, 50);
        Fenetre ma_fenetre = new Fenetre(espace);
    }
}
