import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class EcouteurBoutonZoom implements MouseListener {

    private AstrePanel AP;
    private double f;

    public EcouteurBoutonZoom(AstrePanel AP, double f) {
        this.AP = AP;
        this.f = f;
    }

    public void mouseClicked(MouseEvent e){
        AP.zoomLens(f);
    }
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
