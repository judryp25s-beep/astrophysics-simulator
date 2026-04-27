import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AstrePanel extends JPanel {
    private Vector astres;
    private int pause;
    public static final Color DARK_BLUE = new Color(10, 10, 25);

    // Planètes
    public static final Color MERCURY = new Color(165, 165, 165);
    public static final Color VENUS = new Color(227, 220, 175);
    public static final Color MARS = new Color(193, 68, 14);
    public static final Color JUPITER = new Color(216, 202, 157);
    public static final Color SATURN = new Color(191, 155, 95);
    public static final Color URANUS = new Color(209, 231, 231);
    public static final Color NEPTUNE = new Color(63, 130, 242);

    // Étoiles
    public static final Color RED_DWARF = new Color(255, 82, 0);
    public static final Color SUN_WHITE = new Color(255, 255, 240);
    public static final Color BLUE_GIANT = new Color(155, 176, 255);
    public static final Color WHITE_DWARF = new Color(248, 247, 255);

    // Trous Noirs et Blancs
    public static final Color BLACK_HOLE_DISK = new Color(255, 140, 0);
    public static final Color WHITE_HOLE = new Color(255, 255, 255);

    // Espace Profond
    public static final Color NEBULA_PINK = new Color(255, 0, 102);
    public static final Color COSMIC_LATTE = new Color(255, 248, 231);

    AstrePanel(Vector astres){
        this.astres = astres;
        this.pause = 5;
        //this.setBackground();
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

            int dx = (int) Math.log(astre.getDiametre().getX()*10);
            int dy = (int) Math.log(astre.getDiametre().getY()*10);
            g.drawOval(posX, posY, dx, dy);
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
