public class Main {
    public static void main(String[] args) {
        Espace espace = new Espace("Système solaire");
        espace.ajouterAstre("A1", "", 5, 10, 11, 30, 0, 0, 0, 0, 500, 500);
        espace.ajouterAstre("A2", "", 8, 14, 15, 30, 0, 0, 0, 0, 100, 270);
        //espace.ajouterAstre("A3", "", 9, 29, 30, 30, 0, 0, 0, 0, 150, 50);
        Fenetre ma_fenetre = new Fenetre(espace);
    }
}
