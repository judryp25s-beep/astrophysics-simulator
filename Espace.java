import java.util.Vector;

public class Espace {
    private String nom;
    private Vector astres;

    public Espace(String nom) {
        this.nom = nom;
        this.astres = new Vector();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Vector getAstres() {
        return astres;
    }

    public void setAstres(Vector astres) {
        this.astres = astres;
    }

    public void ajouterAstre(Astre astre) {
        this.astres.add(astre);
    }

    public void ajouterAstre(String nom, String desc, double masse, double dx, double dy, double temperature, double ax, double ay, double vx, double vy, double px, double py) {
        Astre a = new Astre(nom, desc, masse, dx, dy, temperature, ax, ay, vx, vy, px, py);
        this.astres.add(a);
    }

    public int retirerAstre(int i) {
        if (i >= this.astres.size() || i < 0) return 1;
        this.astres.remove(i);
        return 0;
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
}