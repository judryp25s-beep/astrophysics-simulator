import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.List;

public class BlackHole extends Astre {

    private int categorie;

    public BlackHole() {
        super();
    }

    public BlackHole(String nom, int categorie, String description, double masse, double horizon, double diametre, double ax,
            double ay, double vx, double vy, double px, double py) {
        super(nom, description, masse, diametre, diametre, 0, ax, ay, vx, vy, px, py);
        this.categorie = categorie;
    }

    @Override
    public void paint(Graphics2D g2d, int centerX, int centerY, double offsetX, double offsetY, double zoom,
                    double vScale, double aScale, boolean isFocused) {

        // 1. Projection des coordonnées (Monde -> Écran)
        double viewX = (this.getPosition().getX() + offsetX) * zoom + centerX;
        double viewY = (this.getPosition().getY() + offsetY) * zoom + centerY;
        
        // 2. Mise à l'échelle des diamètres et rayons
        double viewHorizonDiam = this.getDiametre().getX() * zoom;
        double viewHorizonRad = viewHorizonDiam / 2.0;
        // Le disque d'accrétion suit la même règle de zoom
        double viewAccretionRad = viewHorizonRad * 1.2; 

        // Sécurité : même un trou noir doit être repérable
        if (viewHorizonDiam < 1) viewHorizonDiam = 1;

        Point2D center = new Point2D.Double(viewX, viewY);

        // 3. Dessin du Disque d'accrétion (Glow)
        float[] dist = {0.4f, 0.5f, 1.0f};
        Color[] colors = {
            Color.BLACK, 
            new Color(255, 150, 0, 200), 
            new Color(255, 50, 0, 0)
        };
        
        // Utilisation de viewAccretionRad pour le dégradé
        g2d.setPaint(new RadialGradientPaint(center, (float)Math.max(1, viewAccretionRad), dist, colors));
        g2d.fill(new Ellipse2D.Double(viewX - viewAccretionRad, viewY - viewAccretionRad, 
                                    viewAccretionRad * 2, viewAccretionRad * 2));

        // 4. Horizon des événements (Le "vide" central)
        g2d.setColor(Color.BLACK);
        g2d.fill(new Ellipse2D.Double(viewX - viewHorizonRad, viewY - viewHorizonRad, 
                                    viewHorizonDiam, viewHorizonDiam));
        
        // 5. Lentille gravitationnelle (Liseré lumineux)
        // On adapte l'épaisseur du trait (Stroke) au zoom pour éviter qu'il ne disparaisse
        float strokeThickness = (float)Math.max(1.0, 2.0 * zoom); 
        g2d.setStroke(new BasicStroke(strokeThickness));
        g2d.setColor(new Color(255, 255, 255, 100));

        this.paintVectors(g2d, centerX, centerY, offsetX, offsetY, zoom, vScale, aScale);

        
        g2d.draw(new Arc2D.Double(viewX - viewHorizonRad, viewY - viewHorizonRad, 
                                viewHorizonDiam, viewHorizonDiam, 0, 160, Arc2D.OPEN));

        // 6. Si l'astre est sélectionné
        if (isFocused) {
            g2d.setStroke(new BasicStroke(1.0f));
            g2d.setColor(Color.ORANGE);
            double padding = 15;
            g2d.draw(new Ellipse2D.Double(viewX - viewAccretionRad - padding, viewY - viewAccretionRad - padding, 
                                        (viewAccretionRad + padding) * 2, (viewAccretionRad + padding) * 2));
        }
    }

    @Override
    public Astre fusion(Astre a) {
        if (a instanceof WhiteHole) return a.fusion(this);
        WhiteHole hole = this.getEspace().getRandomWhiteHole();
        if (Math.random() < 0.33 && hole != null) {
            a.setPosition(
                hole.getPosition().getX(),
                hole.getPosition().getY()
            );
            return null; 
        }
        // Le trou noir absorbe la masse sans forcément grossir autant qu'une étoile
        this.absorber(a);
        return this;
    }

}
