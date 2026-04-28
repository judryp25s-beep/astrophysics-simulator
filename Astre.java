import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Astre {
    private String nom;
    private String description;
    private double masse;
    private X_Y diametre;
    private double temperature;
    private X_Y acceleration;
    private X_Y vitesse;
    private X_Y position;
    private Espace espace;
    static double G = 6674;
    
    private Color couleur = SUN_WHITE;

    // --- CONSTANTES DE COULEURS ---
    public static final Color SUN_WHITE = new Color(255, 255, 240);
    // ... (garde tes autres constantes ici)

    public Astre(String nom, String description, double masse, double dx, double dy, double temperature, double ax,
            double ay, double vx, double vy, double px, double py) {
        this.nom = nom;
        this.description = description;
        this.masse = masse;
        this.diametre = new X_Y(dx, dy);
        this.vitesse = new X_Y(vx, vy);
        this.acceleration = new X_Y(ax, ay);
        this.position = new X_Y(px, py);
        this.temperature = temperature;
    }

    // --- PHYSIQUE ---

    public void update(double dt) {
        // 1. On calcule l'accélération APRES avoir bougé pour le tour suivant
        // ou AVANT pour ce tour. L'important est la cohérence.
        this.updateAcceleration(dt);
        this.updateVitesse(dt);
        this.updatePosition(dt);
        this.updateCollision(dt);
    }

    public void updateAcceleration(double dt) {
        double dax = 0, day = 0;
        if (this.masse <= 0 || this.espace == null) return;

        for (Object obj : this.espace.getAstres()) {
            Astre a = (Astre) obj;
            if (a == this || a.getMasse() <= 0) continue;

            double d = this.calculerDistance(a);
            
            // Sécurité : si les centres sont trop proches, on limite d 
            // pour éviter que l'accélération tende vers l'infini
            double dMin = this.diametre.getX() / 2;
            double effectiveD = Math.max(d, dMin);

            double forceA = (Astre.G * a.getMasse()) / (effectiveD * effectiveD);
            
            double dx = a.getPosition().getX() - this.position.getX();
            double dy = a.getPosition().getY() - this.position.getY();
            
            dax += (dx / effectiveD) * forceA;
            day += (dy / effectiveD) * forceA;
        }
        this.acceleration.setX(dax);
        this.acceleration.setY(day);
    }

    public void updateVitesse(double dt) {
        this.vitesse.setX(this.vitesse.getX() + this.acceleration.getX() * dt);
        this.vitesse.setY(this.vitesse.getY() + this.acceleration.getY() * dt);
    }

    public void updatePosition(double dt) {
        // Formule complète : x = x0 + v*dt + 0.5*a*dt^2
        double dx = this.vitesse.getX() * dt + 0.5 * this.acceleration.getX() * dt * dt;
        double dy = this.vitesse.getY() * dt + 0.5 * this.acceleration.getY() * dt * dt;

        this.position.setX(this.position.getX() + dx);
        this.position.setY(this.position.getY() + dy);
    }

    // --- COLLISION & FUSION ---

    /**
     * Vérifie si le contour de 'this' touche le centre de 'a'
     */
    public boolean verifyCollision(Astre a) {
        double distance = this.calculerDistance(a);
        double rayonThis = this.diametre.getX() / 2;
        return distance <= rayonThis;
    }

    public void updateCollision(double dt) {
        // Pour éviter ConcurrentModificationException, on ne retire pas 
        // directement dans la boucle si on utilise un itérateur ailleurs.
        for (int i = 0; i < this.espace.getAstres().size(); i++) {
            Astre a = (Astre) this.espace.getAstres().get(i);
            if (a == this) continue;

            if (this.verifyCollision(a)) {
                Astre fusionne = this.fusion(a);
                this.espace.ajouterAstre(fusionne);
                this.espace.retirerAstre(a);
                this.espace.retirerAstre(this);
                break; // On arrête pour cet astre car il n'existe plus
            }
        }
    }

    public Astre fusion(Astre a) {
        double masseTotale = this.masse + a.getMasse();
        
        // Conservation de la quantité de mouvement (Vitesse résultante)
        double vx = (this.masse * this.vitesse.getX() + a.getMasse() * a.getVitesse().getX()) / masseTotale;
        double vy = (this.masse * this.vitesse.getY() + a.getMasse() * a.getVitesse().getY()) / masseTotale;
        
        // Nouvelle position (moyenne pondérée ou centre de this)
        double px = this.position.getX();
        double py = this.position.getY();

        // Nouveau diamètre (somme des surfaces pour être réaliste, ou simple addition)
        double nouveauDiam = Math.sqrt(Math.pow(this.diametre.getX(), 2) + Math.pow(a.getDiametre().getX(), 2));

        return new Astre(
            this.nom + "+" + a.getNom(),
            "Fusion de " + this.nom + " et " + a.getNom(),
            masseTotale,
            nouveauDiam, nouveauDiam,
            (this.temperature + a.getTemperature()) / 2,
            0, 0, vx, vy, px, py
        );
    }

    // --- UTILITAIRES ---

    public double calculerDistance(Astre a) {
        double dx = a.getPosition().getX() - this.position.getX();
        double dy = a.getPosition().getY() - this.position.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void paint(Graphics2D g2d, int centerX, int centerY, double offsetX, double offsetY, double zoom, double vScale, double aScale, boolean isFocused) {
        // 1. Calcul de la position à l'écran (basé sur le centre de l'astre)
        // On suppose que getPosition() renvoie le centre
        int drawX = (int) (centerX + (this.getPosition().getX() + offsetX) * zoom);
        int drawY = (int) (centerY + (this.getPosition().getY() + offsetY) * zoom);
        
        int sizeW = (int) (this.getDiametre().getX() * zoom);
        int sizeH = (int) (this.getDiametre().getY() * zoom);

        // Pour que (drawX, drawY) soit le centre, on décale le dessin du cercle
        int topLeftX = drawX - sizeW / 2;
        int topLeftY = drawY - sizeH / 2;

        // 2. Dessin de l'Astre (Focus + Corps)
        if (isFocused) {
            g2d.setColor(Color.CYAN);
            g2d.drawOval(topLeftX - 2, topLeftY - 2, sizeW + 4, sizeH + 4);
        }
        
        g2d.setColor(Color.WHITE); 
        g2d.fillOval(topLeftX, topLeftY, sizeW, sizeH);

        // 3. Traçage du vecteur Vitesse (Vert)
        g2d.setColor(Color.GREEN);
        int vEndX = (int) (drawX + this.getVitesse().getX() * vScale * zoom);
        int vEndY = (int) (drawY + this.getVitesse().getY() * vScale * zoom);
        g2d.drawLine(drawX, drawY, vEndX, vEndY);

        // 4. Traçage du vecteur Accélération (Rouge)
        g2d.setColor(Color.RED);
        int aEndX = (int) (drawX + this.getAcceleration().getX() * aScale * zoom);
        int aEndY = (int) (drawY + this.getAcceleration().getY() * aScale * zoom);
        g2d.drawLine(drawX, drawY, aEndX, aEndY);
    }

    // --- GETTERS / SETTERS ---
    public X_Y getPosition() { return position; }
    public X_Y getDiametre() { return diametre; }
    public double getMasse() { return masse; }
    public double getTemperature() {
        return temperature;
    }
    public X_Y getVitesse() {
        return vitesse;
    }
    public X_Y getAcceleration() {
        return acceleration;
    }
    public String getDescription() {
        return description;
    }
    public void setEspace(Espace e) { this.espace = e; }
    public String getNom() { return nom; }
}