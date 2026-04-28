import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class EcouteurBoutonTime implements MouseListener {

    private AstrePanel AP;
    private double time;

    public EcouteurBoutonTime(AstrePanel AP, double time) {
        this.AP = AP;
        this.time = time;
    }

    public void mouseClicked(MouseEvent e){
        AP.time_update(this.time);
    }
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
