import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class EcouteurBoutonMove implements MouseListener {

    private AstrePanel AP;
    private int mx, my;

    public EcouteurBoutonMove(AstrePanel AP, int mx, int my) {
        this.AP = AP;
        this.mx = mx;
        this.my = my;
    }

    public void mouseClicked(MouseEvent e){
        AP.moveLens(mx, my);
    }
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
