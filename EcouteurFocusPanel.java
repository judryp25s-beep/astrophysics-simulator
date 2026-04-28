import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class EcouteurFocusPanel implements MouseListener {

    private AstrePanel AP;

    public EcouteurFocusPanel(AstrePanel AP) {
        this.AP = AP;
    }

    public void mouseClicked(MouseEvent e){
        AP.verifierFocus(e.getX(), e.getY());
    }
    
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
