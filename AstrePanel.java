import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AstrePanel extends JPanel {
    private Vector astres;

    AstrePanel(Vector astres){
        this.astres = astres;
    }

    public void setAstres(Vector astres) {
        this.astres = astres;
    }
    public Vector getAstres() {
        return astres;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (int i = 0; i < astres.size(); i++){
            Astre astre = (Astre) astres.get(i);
            int posX = (int) astre.getPosition().getX();
            int posY = (int) astre.getPosition().getY();

            int dx = (int) astre.getDiametre().getX();
            int dy = (int) astre.getDiametre().getX();
            g.drawOval(posX, posY, dx, dy);

        }
       // repaint();
    }
}
