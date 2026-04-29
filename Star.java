import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Star extends Astre {

    private int categorie;

    public Star() {
        super();
    }

    public Star(String nom, int categorie, String description, double masse, double temperature, double diametre, double ax,
            double ay, double vx, double vy, double px, double py) {
        super(nom, description, masse, diametre, diametre, temperature, ax, ay, vx, vy, px, py);
        this.categorie = categorie;
    }

    @Override
    public void paint(Graphics2D g2d, int centerX, int centerY, double offsetX, double offsetY, double zoom,
                    double vScale, double aScale, boolean isFocused) {

        // 1. Projection des coordonnées (Monde -> Écran)
        double viewX = (this.getPosition().getX() + offsetX) * zoom + centerX;
        double viewY = (this.getPosition().getY() + offsetY) * zoom + centerY;
        
        // 2. Mise à l'échelle de la taille de l'astre
        double viewDiameter = this.getDiametre().getX() * zoom;
        double viewRadius = viewDiameter / 2.0;

        // Sécurité : Si l'étoile est trop petite pour être calculée
        if (viewDiameter < 1) viewDiameter = 1;

        Color coreColor = Astre.SUN_WHITE;
        Point2D center = new Point2D.Double(viewX, viewY);
        
        // 3. Définition du dégradé radial basé sur les coordonnées de VUE
        float[] dist = {0.0f, 0.2f, 1.0f};
        Color[] colors = {
            Color.WHITE, 
            coreColor, 
            new Color(coreColor.getRed(), coreColor.getGreen(), coreColor.getBlue(), 0) // Halo transparent
        };

        // Le rayon du gradient doit impérativement être le viewRadius
        RadialGradientPaint p = new RadialGradientPaint(
            center, 
            (float)Math.max(1, viewRadius), 
            dist, 
            colors
        );
        
        g2d.setPaint(p);

        // 4. Dessin de l'astre projeté
        g2d.fill(new Ellipse2D.Double(viewX - viewRadius, viewY - viewRadius, viewDiameter, viewDiameter));
        
        this.paintVectors(g2d, centerX, centerY, offsetX, offsetY, zoom, vScale, aScale);


        // Optionnel : Effet de focus (si l'astre est sélectionné)
        if (isFocused) {
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(1.0f));
            double markerSize = viewDiameter + 10;
            g2d.draw(new Ellipse2D.Double(viewX - markerSize/2, viewY - markerSize/2, markerSize, markerSize));
        }
    }

    @Override
    public Astre fusion(Astre autre) {
        if (autre instanceof BlackHole || autre instanceof WhiteHole) {
            return autre.fusion(this); 
        }
        // L'étoile absorbe l'autre (Planète ou autre étoile)
        this.absorber(autre);
        return this;
    }

}
