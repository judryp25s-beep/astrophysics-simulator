import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AstrePanel extends JPanel {
    private Vector astres;
    private int pause;

    AstrePanel(Vector astres){
        this.astres = astres;
        this.pause = 20;
    }

    public void setAstres(Vector astres) {
        this.astres = astres;
    }
    public Vector getAstres() {
        return astres;
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);

        for (int i = 0; i < astres.size(); i++){
            Astre astre = (Astre) astres.get(i);
            int posX = (int) astre.getPosition().getX();
            int posY = (int) astre.getPosition().getY();

            int dx = (int) astre.getDiametre().getX();
            int dy = (int) astre.getDiametre().getY();
            g.drawOval(posX, posY, dx, dy);
            astre.update(this.pause*0.001);
        }


        try {
            Thread.sleep(this.pause);
        } catch (Exception e) {
            // TODO: handle exception
        }
       repaint();
    }
}
