import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.List;
import java.util.Locale;

public class AstrePanel extends JPanel {
    private List<Astre> astres; // Use Generics
    private int pauseTime = 16;   // ~60 FPS
    private double offsetX, offsetY;
    private double zoom = 1.0;
    private boolean isPaused = true;
    private Astre focusBody = null;
    private double lensMoveX, lensMoveY, time_update;
    private double last_clickedx, last_clickedy;
    private CommandesPanel cmd;

    public AstrePanel(List<Astre> astres) {
        this.astres = astres;
        this.setBackground(Color.decode("#000022")); // Dark Blue
        this.time_update = 0.025;
        
        // Use a Swing Timer for the animation loop instead of Thread.sleep
        Timer timer = new Timer(pauseTime, e -> {
            updateSimulation();
            repaint();
        });
        timer.start();
    }

    public void setCmd(CommandesPanel cmd) {
        this.cmd = cmd;
    }

    private void updateSimulation() {

        this.offsetX += this.lensMoveX;
        this.offsetY += this.lensMoveY;

        // If focusing on a body, center the camera on it
        if (focusBody != null) {
            this.offsetX = -focusBody.getPosition().getX();
            this.offsetY = -focusBody.getPosition().getY();
        }

        if (isPaused) return;

        for (Astre a : astres) {
            // Update physics (assuming update takes dt)
            a.update(this.time_update); 
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //g2d.drawLine((int) this.offsetX, (int) -this.offsetY, (int) -(100000*this.zoom), (int) -this.offsetY);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
        g2d.setColor(Color.decode("#515151"));
        g2d.drawLine(
            (int) -(centerX + this.offsetX),
            (int) -(centerY + this.offsetY),
            (int) -(centerX + this.offsetX),
            (int) -(centerY + this.offsetY + 10000)
        );

        double vScale = 10.0; 
        double aScale = 50.0;

        // On synchronise sur la liste pour éviter le crash pendant le dessin
        synchronized(astres) {
            for (Astre a : astres) {
                a.paint(
                    g2d, 
                    centerX, centerY, 
                    this.offsetX, this.offsetY, 
                    this.zoom, 
                    vScale, aScale, 
                    (a == this.focusBody)
                );
            }
        }

        this.rendreTextsAstre(g2d);
        this.rendreStatsGlobales(g2d);
    }
        
    public void toggleStop() {
        this.isPaused = !this.isPaused;
    }

    public void zoomLens(double factor) {
        this.zoom *= factor;
    }

    public Astre verifierFocus(double mouseX, double mouseY) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (Astre a : astres) {
            // Reverse the drawing math to find the body under the mouse
            double screenX = centerX + (a.getPosition().getX() + offsetX) * zoom;
            double screenY = centerY + (a.getPosition().getY() + offsetY) * zoom;
            double sizeW = a.getDiametre().getX() * zoom;

            // Simple distance check or bounding box
            if (Math.abs(mouseX - screenX) < sizeW && Math.abs(mouseY - screenY) < sizeW) {
                this.focusBody = a;
                this.cmd.update();
                return a;
            }
        }
        this.last_clickedx = (mouseX - centerX)/this.zoom - this.offsetX;
        this.last_clickedy = (mouseY - centerY)/this.zoom - this.offsetY;
        this.focusBody = null;
        this.cmd.update();
        return null;
    }

    public void moveLens(double dx, double dy) {
        this.lensMoveX+=dx;
        this.lensMoveY+=dy; 
        if (dx == 0 && dy == 0) {
            this.lensMoveX = 0;
            this.lensMoveY = 0;
        }
        System.out.println("Move " + dx + " -> " + this.lensMoveX + ", " + dy + " -> " + this.lensMoveY);
    }

    public void time_update(double time) {
        if (this.time_update * time > 0)
        this.time_update *= time;
    }

    public Astre getFocusBody() {
        return this.focusBody;
    }

    public int getPauseTime() {
        return pauseTime;
    }

    public void rendreTextsAstre(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (focusBody != null) {
            // 1. Préparation des données
            String[] lignes = {
                "Nom: " + focusBody.getNom(),
                "Description: " + focusBody.getDescription(),
                "Diamètre: " + String.format(Locale.US, "%.2E", focusBody.getDiametre().getX()),
                "Température: " + String.format(Locale.US, "%.2E", focusBody.getTemperature()),
                "Masse: " + String.format(Locale.US, "%.2E", focusBody.getMasse()),
                "Acc X: " + String.format(Locale.US, "%.2E", focusBody.getAcceleration().getX()),
                "Acc Y: " + String.format(Locale.US, "%.2E", focusBody.getAcceleration().getY()) + " (" +
                    String.format(Locale.US, "%.2E", focusBody.getGlobalAcceleration()) + ")",
                "Vit X: " + String.format(Locale.US, "%.2E", focusBody.getVitesse().getX()),
                "Vit Y: " + String.format(Locale.US, "%.2E", focusBody.getVitesse().getY()) + " (" +
                    String.format(Locale.US, "%.2E", focusBody.getGlobalVitesse()) +")",
                "Pos X: " + String.format(Locale.US, "%.2E", focusBody.getPosition().getX()),
                "Pos Y: " + String.format(Locale.US, "%.2E", focusBody.getPosition().getY())
            };

            // 2. Paramètres du bloc de texte
            g2d.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 12)); // Monospaced pour un alignement parfait
            int x = 15;                       // Marge gauche
            int hauteurLigne = 18;
            int padding = 10;                 // Espace intérieur du rectangle
            int largeurRectangle = 400;
            int hauteurRectangle = (lignes.length * hauteurLigne) + padding;
            int yDeDepart = getHeight() - hauteurRectangle - 15; // Calcul du coin bas gauche

            // 3. Dessiner le fond semi-transparent
            g2d.setColor(new Color(0, 0, 0, 160)); // Noir, opacité 160/255
            g2d.fillRoundRect(x - padding, yDeDepart - padding, largeurRectangle, hauteurRectangle, 15, 15);

            // 4. Dessiner le texte
            g2d.setColor(Color.WHITE);
            for (int i = 0; i < lignes.length; i++) {
                g2d.drawString(lignes[i], x, yDeDepart + (i * hauteurLigne) + 5);
            }
        } else {
            String[] lignes = {
                "X : " + String.format(Locale.US, "%.1f", this.last_clickedx),
                "Y : " + String.format(Locale.US, "%.1f", this.last_clickedy)
            };

            // 2. Paramètres du bloc de texte
            g2d.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 12)); // Monospaced pour un alignement parfait
            int x = 15;                       // Marge gauche
            int hauteurLigne = 18;
            int padding = 10;                 // Espace intérieur du rectangle
            int largeurRectangle = 400;
            int hauteurRectangle = (lignes.length * hauteurLigne) + padding;
            int yDeDepart = getHeight() - hauteurRectangle - 15; // Calcul du coin bas gauche

            // 3. Dessiner le fond semi-transparent
            g2d.setColor(new Color(0, 0, 0, 160)); // Noir, opacité 160/255
            g2d.fillRoundRect(x - padding, yDeDepart - padding, largeurRectangle, hauteurRectangle, 15, 15);

            // 4. Dessiner le texte
            g2d.setColor(Color.WHITE);
            for (int i = 0; i < lignes.length; i++) {
                g2d.drawString(lignes[i], x, yDeDepart + (i * hauteurLigne) + 5);
            }
        }
    }

    public void rendreStatsGlobales(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // 1. Préparation des données (on formate les doubles pour la propreté)
        String[] stats = {
            "Zoom: " + String.format(Locale.US, "%.2f", zoom),
            "Offset X: " + String.format(Locale.US, "%.0f", offsetX),
            "Offset Y: " + String.format(Locale.US, "%.0f", offsetY),
            "Pause: " + (isPaused ? "OUI" : "NON"),
            "Lens X: " + String.format(Locale.US, "%.1f", lensMoveX),
            "Lens Y: " + String.format(Locale.US, "%.1f", lensMoveY),
            "Time Update: " + String.format(Locale.US, "%.3f s", time_update)
        };

        // 2. Paramètres du bloc
        g2d.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 12));
        int padding = 10;
        int hauteurLigne = 18;
        int largeurRectangle = 200; // Un peu plus étroit que l'autre
        int hauteurRectangle = (stats.length * hauteurLigne) + padding;

        // 3. Calcul de la position (Coin bas droit)
        // getWidth() - largeur - marge de 15
        int xDeDepart = getWidth() - largeurRectangle - 15; 
        int yDeDepart = getHeight() - hauteurRectangle - 15;

        // 4. Dessiner le fond (Noir semi-transparent)
        g2d.setColor(new Color(0, 0, 0, 160));
        g2d.fillRoundRect(xDeDepart - padding, yDeDepart - padding, largeurRectangle, hauteurRectangle, 15, 15);

        // 5. Dessiner le texte
        g2d.setColor(new Color(200, 255, 200)); // Une couleur légèrement verte pour différencier du focus
        for (int i = 0; i < stats.length; i++) {
            g2d.drawString(stats[i], xDeDepart, yDeDepart + (i * hauteurLigne) + 5);
        }
    }
}