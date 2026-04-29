import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Espace {
    private String nom;
    private List<Astre> astres;

    public Espace(String nom) {
        this.nom = nom;
        this.astres = new CopyOnWriteArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List getAstres() {
        return astres;
    }

    public void setAstres(List astres) {
        this.astres = astres;
    }

    public void ajouterAstre(Astre astre) {
        astre.setEspace(this);
        this.astres.add(astre);
    }

    public void ajouterAstre(String nom, String desc, String type, double masse, double dx, double dy, double temperature, double ax, double ay, double vx, double vy, double px, double py) {
        Astre a;
        switch (type) {
            case "Planet":
            case "Planète":
                System.out.println("Planet");
                a = new Planet(nom, 0, desc, masse, temperature, dx, ax, ay, vx, vy, px, py, dy/dx);
                break; // Arrête l'exécution du switch
            case "Star":
            case "Étoile":
                System.out.println("Star");
                a = new Star(nom, 0, desc, masse, temperature, dx, ax, ay, vx, vy, px, py);
                break;
            case "WhiteHole":
            case "Trou blanc":
                System.out.println("Trou blanc");
                a = new WhiteHole(nom, 0, desc, masse, dy, dx, ax, ay, vx, vy, px, py);
                break;
            case "BlackHole":
            case "Trou noir": // Cas groupés
                System.out.println("Trou noir");
                a = new BlackHole(nom, 0, desc, masse, dy, dx, ax, ay, vx, vy, px, py);
                break;
            default: // Si aucun cas ne correspond
                System.out.println("Inconnu, alors Planet");
                a = new Planet(nom, 0, desc, masse, temperature, dx, ax, ay, vx, vy, px, py, dy/dx);
                break;
        }
        
        // Astre a = new Astre(nom, desc, masse, dx, dy, temperature, ax, ay, vx, vy, px, py);
        this.ajouterAstre(a);
    }

    public int retirerAstre(int i) {
        if (i >= this.astres.size() || i < 0) return 1;
        this.astres.remove(i);
        return 0;
    }

    public void retirerAstre(Astre a) {
        this.astres.remove(a);
        a = null;
    }

    public int retirerAstre(String nom) {
        for (int i = 0; i < this.astres.size(); i++) {
            Astre current_astre = (Astre) this.astres.get(i);
            if (current_astre.getNom() == nom) {
                this.astres.remove(i);
                return 0;
            }

        }
        return 1;
    }

    // Retourne uniquement les fontaines blanches (WhiteHole)
    public List<WhiteHole> getListeWhiteHole() {
        return astres.stream()
                     .filter(a -> a instanceof WhiteHole)
                     .map(a -> (WhiteHole) a)
                     .collect(Collectors.toList());
    }

    // Retourne uniquement les trous noirs (BlackHole)
    public List<BlackHole> getListeBlackHole() {
        return astres.stream()
                     .filter(a -> a instanceof BlackHole)
                     .map(a -> (BlackHole) a)
                     .collect(Collectors.toList());
    }

    // Retourne uniquement les planètes (Planet)
    public List<Planet> getListePlanet() {
        return astres.stream()
                     .filter(a -> a instanceof Planet)
                     .map(a -> (Planet) a)
                     .collect(Collectors.toList());
    }

    // Retourne uniquement les étoiles (Star)
    public List<Star> getListeStar() {
        return astres.stream()
                     .filter(a -> a instanceof Star)
                     .map(a -> (Star) a)
                     .collect(Collectors.toList());
    }
    // ... dans ta classe Espace

    public WhiteHole getRandomWhiteHole() {
        List<WhiteHole> whiteHoles = getListeWhiteHole();
        
        if (whiteHoles.isEmpty()) {
            return null; // Ou lancez une exception selon votre besoin
        }
        
        int randomIndex = ThreadLocalRandom.current().nextInt(whiteHoles.size());
        return whiteHoles.get(randomIndex);
    }
}