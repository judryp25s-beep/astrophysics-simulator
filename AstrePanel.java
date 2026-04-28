import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AstrePanel extends JPanel {
    private Vector astres;
    private int pause;

    AstrePanel(Vector astres){
        this.astres = astres;
        this.pause = 5;
        this.setBackground(Astre.DARK_BLUE);
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

            int dx = (int) Math.log(10*astre.getDiametre().getX()*10);
            int dy = (int) Math.log(10*astre.getDiametre().getY()*10);
            g.drawOval(posX, posY, dx, dy);
            g.setColor(Astre.JUPITER);
            g.fillOval(posX, posY, dx, dy);
            astre.update(this.pause*0.005);
        }


        try {
            Thread.sleep(this.pause);
        } catch (Exception e) {
            // TODO: handle exception
        }
       repaint();
    }

}
