public class Main {

    // Constantes partagées
    static double MSoleil = 1;

    public static void main(String[] args) {
        Espace espace = new Espace("Simulations Spatiales");

        // --- CHOISISSEZ LE SYSTÈME À AFFICHER ---
        // creerSystemeSolaire(espace);
        // creerSystemeBistellaire(espace);
        //creerSystemeTristellaire(espace);
        // creerCasVitesses(espace);

        new Fenetre(espace);
    }

    /**
     * Système classique avec alignement vertical
     */
    public static void creerSystemeSolaire(Espace e) {
        e.ajouterAstre("Soleil", "Yellow", MSoleil, 35, 35, 0, 0, 0, 0, 0, 0, 0);
        
        double[] distances = {70, 110, 160, 210, 320, 450};
        String[] noms = {"Mercure", "Vénus", "Terre", "Mars", "Jupiter", "Saturne"};
        
        for (int i = 0; i < distances.length; i++) {
            double v = Math.sqrt(Astre.G * MSoleil / distances[i]);
            e.ajouterAstre(noms[i], "White", 100, 8, 8, 0, 0, 0, v, 0, 0, distances[i]);
        }
    }

    /**
     * Deux soleils qui gravitent l'un autour de l'autre
     */
    public static void creerSystemeBistellaire(Espace e) {
        double dist = 5;
        // Pour un système binaire stable, la vitesse est v = sqrt(Astre.G * M_total / (4 * dist))
        double v = 10;

        e.ajouterAstre("Soleil A", "Yellow", MSoleil, 30, 30, 0, 0, 0, v, 0, 0, dist);
        e.ajouterAstre("Soleil B", "Orange", MSoleil, 30, 30, 0, 0, 0, -v, 0, 0, dist);
    }

    /**
     * Trois étoiles en équilibre (Configuration en triangle équilatéral)
     */
    public static void creerSystemeTristellaire(Espace e) {
        double rayon = 150;
        double v = 100;

        // Étoile 1 (Haut)
        e.ajouterAstre("Alpha", "Cyan", MSoleil, 25, 25, 0, 0, 0, v, 0, 0, rayon);
        // Étoile 2 (Bas Astre.Gauche)
        e.ajouterAstre("Beta", "White", MSoleil, 25, 25, 0, 0, 0, -v/2, v, 130, 75);
        // Étoile 3 (Bas Droite)
        e.ajouterAstre("Astre.Gamma", "Red", MSoleil, 25, 25, 0, 0, 0, 0, -v, 130, 75);
    }

    /**
     * Démonstration des trois types de trajectoires
     */
    public static void creerCasVitesses(Espace e) {
        e.ajouterAstre("Centre", "Yellow", MSoleil, 40, 40, 0, 0, 0, 0, 0, 0, 0);

        double d = 150;
        double vOrbite = Math.sqrt(Astre.G * MSoleil / d); // Vitesse circulaire
        double vLibre = vOrbite * Math.sqrt(2);      // Vitesse de libération

        // 1. CRASH : Vitesse trop faible (tombe sur l'étoile)
        e.ajouterAstre("Crash", "Red", 10, 6, 6, 0, 0, 0, vOrbite * 0.4, 0, 0, d);

        // 2. ORBITE : Vitesse parfaite (cercle)
        e.ajouterAstre("Orbite", "Astre.Green", 10, 6, 6, 0, 0, 0, vOrbite, 0, 0, d);

        // 3. LIBÉRATION : Vitesse trop haute (s'échappe en parabole)
        e.ajouterAstre("Evasion", "Blue", 10, 6, 6, 0, 0, 0, vLibre * 1.1, 0, 0, d);
    }
}