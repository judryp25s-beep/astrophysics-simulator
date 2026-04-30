public class Main {

    // Constantes partagées
    static double MSoleil = 1.0;

    public static void main(String[] args) {
        Espace espace = new Espace("Simulations Spatiales");

        // --- DECOMMENTEZ LE SYSTEME A TESTER ---
        // creerSystemeSolaire(espace);
        // creerSystemeBistellaire(espace);
        creerSystemeTristellaire(espace);
        // creerCasVitesses(espace);

        new Fenetre(espace);
    }

    /**
     * Système classique : La vitesse est perpendiculaire au rayon (Axe X si pos sur Z)
     */
    public static void creerSystemeSolaire(Espace e) {
        // Le Soleil au centre (0,0,0) sans vitesse
        e.ajouterAstre("Soleil", "Yellow", "Star", MSoleil, 35, 35, 0, 0, 0, 0, 0, 0, 0);
        
        double[] distances = {70, 110, 160, 210, 320, 450};
        String[] noms = {"Mercure", "Vénus", "Terre", "Mars", "Jupiter", "Saturne"};
        
        for (int i = 0; i < distances.length; i++) {
            // Formule : v = sqrt(G * M / r)
            double v = Math.sqrt(Astre.G * MSoleil / distances[i]);
            // Position sur Z, donc vitesse sur X pour être perpendiculaire
            e.ajouterAstre(noms[i], "White", "Planet", 0.001, 8, 8, 0, 0, 0, v, 0, 0, distances[i]);
        }
    }

    /**
     * Deux étoiles tournant autour d'un centre de masse commun
     */
    public static void creerSystemeBistellaire(Espace e) {
        double distAuCentre = 100; 
        // Vitesse pour système binaire : v = sqrt(G * M_total / (4 * r))
        double v = Math.sqrt(Astre.G * (MSoleil + MSoleil) / (4 * distAuCentre));

        // Étoile A à +100 sur Z, vitesse +X
        e.ajouterAstre("Soleil A", "Yellow", "Star", MSoleil, 30, 30, 0, 0, 0, v, 0, 0, distAuCentre);
        // Étoile B à -100 sur Z, vitesse -X
        e.ajouterAstre("Soleil B", "Orange", "Star", MSoleil, 30, 30, 0, 0, 0, -v, 0, 0, -distAuCentre);
    }

    /**
     * Trois étoiles en équilibre (Triangle équilatéral parfait)
     */
    public static void creerSystemeTristellaire(Espace e) {

        // Étoile 1 : Haut (Angle 90°)
        // Pos : (0, R) | Vel : (v, 0)
        e.ajouterAstre("Alpha", "Cyan", "WhiteHole", 30, 40, 40, 0, 0, 0, 50, 0, 0, 0);

        // Étoile 2 : Bas Gauche (Angle 210°)
        e.ajouterAstre("Beta", "White", "BlackHole", 70, 70, 70, 0, 0, 0, 25, 25, 0, 100);
        // Note: j'ai supposé que ton ajouterAstre prend (..., vx, vy, vz, z) basé sur ton code initial

        // Étoile 3 : Bas Droite (Angle 330°)
        e.ajouterAstre("Gamma", "Red", "Planet", 15, 25 , 25, 0, 0, 0, 0, 10, 100, 0);
    }

    /**
     * Comparaison visuelle des types de trajectoires
     */
    public static void creerCasVitesses(Espace e) {
        e.ajouterAstre("Centre", "Yellow", "Star", MSoleil, 40, 40, 0, 0, 0, 0, 0, 0, 0);

        double d = 180;
        double vOrbite = Math.sqrt(Astre.G * MSoleil / d); 
        double vLibre = vOrbite * Math.sqrt(2); // Vitesse d'échappement

        // 1. CRASH (Vitesse 40% de l'orbitale)
        e.ajouterAstre("Crash", "Red", "Star", 0.001, 6, 6, 0, 0, 0, vOrbite * 0.4, 0, 0, d);

        // 2. ORBITE CIRCULAIRE
        e.ajouterAstre("Orbite", "Green", "Star", 0.001, 6, 6, 0, 0, 0, vOrbite, 0, 0, d + 40);

        // 3. LIBÉRATION (Vitesse 110% de la libération)
        e.ajouterAstre("Evasion", "Blue", "Star", 0.001, 6, 6, 0, 0, 0, vLibre * 1.1, 0, 0, d + 80);
    }
}