import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class EcouteurBoutonPause implements MouseListener {

    private AstrePanel AP;

    public EcouteurBoutonPause(AstrePanel AP) {
        this.AP = AP;
    }

    public void mouseClicked(MouseEvent e){
        AP.toggleStop();
    }
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
