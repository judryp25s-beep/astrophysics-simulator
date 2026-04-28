import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.List;

public class AstrePanel extends JPanel {
    private List<Astre> astres; // Use Generics
    private int pauseTime = 16;   // ~60 FPS
    private double offsetX, offsetY;
    private double zoom = 1.0;
    private boolean isPaused = true;
    private Astre focusBody = null;
    private double lensMoveX, lensMoveY, time_update;

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

    private void updateSimulation() {
        if (isPaused) return;

        for (Astre a : astres) {
            // Update physics (assuming update takes dt)
            a.update(this.time_update); 
        }

        // If focusing on a body, center the camera on it
        if (focusBody != null) {
            this.offsetX = -focusBody.getPosition().getX();
            this.offsetY = -focusBody.getPosition().getY();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        double vScale = 10.0; 
        double aScale = 50.0;

        // On synchronise sur la liste pour éviter le crash pendant le dessin
        synchronized(astres) {
            for (Astre a : astres) {
                a.paint(
                    g2d, 
                    centerX, centerY, 
                    offsetX, offsetY, 
                    zoom, 
                    vScale, aScale, 
                    (a == focusBody) // Le booléen isFocused
                );
            }
        }
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
                return a;
            }
        }
        this.focusBody = null;
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
        this.time_update *= time;
    }
}