import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.MultipleGradientPaint.CycleMethod;

public class Planet extends Astre {

    private int categorie;

    public Planet() {
        super();
    }

    public Planet(String nom, int categorie, String description, double masse, double temperature, double diametre, double ax,
        double ay, double vx, double vy, double px, double py, double excentricity) {
            super(nom, description, masse, diametre, diametre*excentricity, temperature, ax, ay, vx, vy, px, py);
        if (excentricity <= 0 || excentricity > 1)
            this.getDiametre().setY(diametre);
        this.categorie = categorie;
    }

    @Override
    public void paint(Graphics2D g2d, int centerX, int centerY, double offsetX, double offsetY, double zoom,
                    double vScale, double aScale, boolean isFocused) {
        
        // 1. Calcul de la position projetée sur l'écran
        double viewX = (this.getPosition().getX() + offsetX) * zoom + centerX;
        double viewY = (this.getPosition().getY() + offsetY) * zoom + centerY;
        
        // 2. Mise à l'échelle du diamètre (on zoome aussi sur la taille de l'astre)
        double viewDiameter = this.getDiametre().getX() * zoom;
        double viewRadius = viewDiameter / 2.0;

        Color color = Astre.NEPTUNE;

        // 3. Configuration du Gradient avec les coordonnées de VUE (écran)
        Point2D center = new Point2D.Double(viewX, viewY);
        // Le focus doit aussi être proportionnel au rayon de vue
        Point2D focus = new Point2D.Double(viewX - viewRadius * 0.3, viewY - viewRadius * 0.3);
        
        float[] dist = {0.0f, 0.8f, 1.0f};
        Color[] colors = {color.brighter(), color, Color.BLACK};

        // 4. Dessin final
        RadialGradientPaint p = new RadialGradientPaint(
            center, 
            (float)Math.max(1, viewRadius), // Sécurité pour éviter un rayon de 0
            focus, 
            dist, 
            colors, 
            CycleMethod.NO_CYCLE
        );
        
        g2d.setPaint(p);
        
        // On dessine l'ellipse aux coordonnées calculées
        g2d.fill(new Ellipse2D.Double(viewX - viewRadius, viewY - viewRadius, viewDiameter, viewDiameter));

        this.paintVectors(g2d, centerX, centerY, offsetX, offsetY, zoom, vScale, aScale);

    }

    @Override
    public Astre fusion(Astre a) {
        if (a instanceof Star || a instanceof BlackHole) {
            return a.fusion(this); // La planète se fait manger
        }
        // Planet + Planet = Planet
        this.absorber(a);
        return this;
    }

}
