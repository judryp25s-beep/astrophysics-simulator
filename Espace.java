import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

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

    public void ajouterAstre(String nom, String desc, double masse, double dx, double dy, double temperature, double ax, double ay, double vx, double vy, double px, double py) {
        Astre a = new Astre(nom, desc, masse, dx, dy, temperature, ax, ay, vx, vy, px, py);
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
}