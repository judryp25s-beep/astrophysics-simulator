import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AstrePanel extends JPanel {
    private Vector astres;
    private int pause;
    private double W_c, H_c;
    private double lensMoveX, lensMoveY;
    private double e;
    private boolean stop;
    private Astre focus;

    public AstrePanel(Vector astres){
        this.astres = astres;
        this.pause = 5;
        this.setBackground(Astre.DARK_BLUE);
        this.e = 10;
        this.W_c = this.getWidth()/2;
        this.H_c = this.getHeight()/2;
        this.lensMoveX = 0;
        this.lensMoveY = 0;
        this.stop = true;
        this.focus = null;
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
        g.setColor(Color.WHITE);
        g.drawOval((int) this.W_c, (int) this.H_c, 10, 10);
        g.drawOval((int) this.W_c, (int) this.H_c, (int) (10*this.e), (int) (10*this.e));

        g.drawOval(0, 0, 10, 10);
        g.drawOval(0, 0, (int) (10*this.e), (int) (10*this.e));
        g.setColor(Color.BLACK);

        for (int i = 0; i < astres.size(); i++){
            Astre astre = (Astre) astres.get(i);
            int posX = (int) astre.getPosition().getX();
            int posY = (int) astre.getPosition().getY();

            //int dx = (int) Math.log(10*astre.getDiametre().getX()*10);
            //int dy = (int) Math.log(10*astre.getDiametre().getY()*10);

            int dx = (int) astre.getDiametre().getX();
            int dy = (int) astre.getDiametre().getY();
            if (this.focus != null && this.focus == astre) g.setColor(Color.BLUE);
            g.drawOval(
                (int) (e*(posX + this.W_c)),
                (int) (e*(posY + this.H_c)),
                (int) e*dx,
                (int) e*dy
            );
            g.setColor(Astre.JUPITER);
            g.fillOval(
                (int) (e*(posX + this.W_c)),
                (int) (e*(posY + this.H_c)),
                (int) e*dx,
                (int) e*dy
            );
            if (!this.stop) astre.update(this.pause*0.005);
        }
        this.W_c += this.lensMoveX;
        this.H_c += this.lensMoveY;


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

    public void moveLens(double dx, double dy) {
        this.lensMoveX+=dx;
        this.lensMoveY+=dy; 
        if (dx == 0 && dy == 0) {
            this.lensMoveX = 0;
            this.lensMoveY = 0;
        }
        System.out.println("Move " + dx + " -> " + this.lensMoveX + ", " + dy + " -> " + this.lensMoveY);
    }

    public void zoomLens(double f) {
        if (this.e >= 0.001) this.e *= f;
        System.out.println("Zoom " + f + " -> " + this.e);

    }


    public Astre verifierFocus(double x, double y) {
        for (int i = 0; i < this.astres.size(); i++) {
            Astre a = (Astre) this.astres.get(i);
            int xp = (int) a.getPosition().getX();
            int yp = (int) a.getPosition().getY();
            if (
                x >= (xp - 0.5)* this.e
                && x <= (xp + 0.5)*this.e
                && y >= (yp - 0.5)*this.e
                && y <= (yp + 0.5)*this.e
            ) {
                this.focus = a;
                return a;
            }
        }
        this.focus = null;
        return null;
    }

}
