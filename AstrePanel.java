import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AstrePanel extends JPanel {
    private Vector astres;
    private int pause;
    private int W_c, H_c;
    private double e;
    private boolean stop;

    AstrePanel(Vector astres){
        this.astres = astres;
        this.pause = 5;
        this.setBackground(Astre.DARK_BLUE);
        this.e = 10;
        this.W_c = (int) this.getWidth()/2;
        this.H_c = (int) this.getHeight()/2;
        this.stop = true;
    }

    public void setAstres(Vector astres) {
        this.astres = astres;
    }
    public Vector getAstres() {
        return astres;
    }

    public void setW_c(int w_c) {
        W_c = w_c;
    }

    public void setH_c(int h_c) {
        H_c = h_c;
    }

    public void setE(double e) {
        this.e = e;
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);

        for (int i = 0; i < astres.size(); i++){
            Astre astre = (Astre) astres.get(i);
            int posX = (int) astre.getPosition().getX();
            int posY = (int) astre.getPosition().getY();

            //int dx = (int) Math.log(10*astre.getDiametre().getX()*10);
            //int dy = (int) Math.log(10*astre.getDiametre().getY()*10);

            int dx = (int) astre.getDiametre().getX();
            int dy = (int) astre.getDiametre().getY();
            g.drawOval(
                (int) e*(posX + this.W_c),
                (int) e*(posY + this.H_c),
                (int) e*dx,
                (int) e*dy
            );
            g.setColor(Astre.JUPITER);
            g.fillOval(
                (int) e*(posX + this.W_c),
                (int) e*(posY + this.H_c),
                (int) e*dx,
                (int) e*dy
            );
            if (!stop) astre.update(this.pause*0.005);
        }


        try {
            Thread.sleep(this.pause);
        } catch (Exception e) {
            // TODO: handle exception
        }
        repaint();
    }

    public void toggleStop() {
        this.stop = !this.stop;
        System.out.println("Toggle " + this.stop);
    }

    public void moveLens(int dx, int dy) {
        this.W_c += dx;
        this.H_c += dy; 
        System.out.println("Move " + dx + " -> " + this.W_c + ", " + dy + " -> " + this.H_c);
    }

    public void zoomLens(double f) {
        this.e *= f;
        System.out.println("Zoom " + f + " -> " + this.e);

    }

}
