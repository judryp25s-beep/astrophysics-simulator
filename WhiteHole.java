import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class WhiteHole extends Astre {

    private int categorie;

    public WhiteHole() {
        super();
    }

    public WhiteHole(String nom, int categorie, String description, double masse, double horizon, double diametre, double ax,
            double ay, double vx, double vy, double px, double py) {
        super(nom, description, masse, diametre, diametre, 0, ax, ay, vx, vy, px, py);
        this.categorie = categorie;
    }

    @Override
    public void paint(Graphics2D g2d, int centerX, int centerY, double offsetX, double offsetY, double zoom,
                    double vScale, double aScale, boolean isFocused) {

        // 1. Projection de la position (Monde -> Écran)
        // On ajoute l'offset à la position réelle, puis on applique le zoom et le centrage
        double viewX = (this.getPosition().getX() + offsetX) * zoom + centerX;
        double viewY = (this.getPosition().getY() + offsetY) * zoom + centerY;
        
        // 2. Mise à l'échelle du diamètre
        double viewDiameter = this.getDiametre().getX() * zoom;
        double viewRadius = viewDiameter / 2.0;

        // Sécurité visuelle pour les zooms lointains
        if (viewDiameter < 1) viewDiameter = 1;

        // 3. Configuration du dégradé radial (Glow électrique)
        Point2D center = new Point2D.Double(viewX, viewY);
        
        float[] dist = {0.0f, 0.1f, 0.6f, 1.0f};
        Color[] colors = {
            Color.WHITE, 
            new Color(200, 230, 255),       // Cœur bleuté intense
            new Color(100, 150, 255, 150),  // Halo intermédiaire semi-transparent
            new Color(100, 100, 255, 0)     // Extinction totale
        };

        // Utilisation de viewRadius pour que le gradient suive la taille à l'écran
        RadialGradientPaint p = new RadialGradientPaint(
            center, 
            (float)Math.max(1, viewRadius), 
            dist, 
            colors
        );
        
        g2d.setPaint(p);

        // 4. Dessin de la forme projetée
        g2d.fill(new Ellipse2D.Double(viewX - viewRadius, viewY - viewRadius, viewDiameter, viewDiameter));
        
        // 5. Indicateur de focus (optionnel)
        if (isFocused) {
            g2d.setColor(new Color(200, 230, 255, 180));
            g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            double selectionSize = viewDiameter + 20;
            g2d.draw(new Ellipse2D.Double(viewX - selectionSize/2, viewY - selectionSize/2, selectionSize, selectionSize));
        }
        this.paintVectors(g2d, centerX, centerY, offsetX, offsetY, zoom, vScale, aScale);

    }

    public Astre fusion(Astre a) {
        // Le Trou Blanc ne fusionne jamais, il expulse.
        this.appliquerExpulsion(a);
        
        // On retourne 'this' pour que la boucle updateCollision sache 
        // que l'astre principal survit, mais on ne retire pas 'a' !
        // Cependant, pour coller à ta logique de boucle, on va retourner null
        // pour signifier qu'aucune fusion (destruction) n'a eu lieu.
        return null; 
    }

    private void appliquerExpulsion(Astre a) {
        // 1. Calcul du vecteur de direction entre le centre du trou blanc et l'astre
        double dx = a.getPosition().getX() - this.getPosition().getX();
        double dy = a.getPosition().getY() - this.getPosition().getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Éviter la division par zéro
        if (distance == 0) { dx = 1; dy = 1; distance = 1.41; }

        // 2. Normalisation et intensité de la force d'expulsion
        // On peut baser la force sur la vitesse actuelle pour un effet de "rebond"
        double intensite = 2.5; 
        double forceX = (dx / distance) * intensite;
        double forceY = (dy / distance) * intensite;

        // 3. Application brutale de la nouvelle vitesse
        // On inverse la vitesse de l'astre 'a' et on ajoute l'impulsion du trou blanc
        a.getVitesse().setX(forceX * Math.abs(a.getVitesse().getX() + 1));
        a.getVitesse().setY(forceY * Math.abs(a.getVitesse().getY() + 1));

        // 4. "Kick" de position pour sortir de la zone de collision
        // Cela évite que l'astre reste bloqué "à l'intérieur" du trou blanc
        a.getPosition().setX(a.getPosition().getX() + a.getVitesse().getX());
        a.getPosition().setY(a.getPosition().getY() + a.getVitesse().getY());
        
        
        System.out.println("EXPULSION : " + a.getNom() + " a été rejeté par le Trou Blanc !");
    }

}
