import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class EcouteurBoutonEffacer implements MouseListener {

    private CommandesPanel commandes;

    public EcouteurBoutonEffacer(CommandesPanel cmd) {
        this.commandes = cmd;
    }

    public void mouseClicked(MouseEvent e){
        commandes.effacerAstre();
    }
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
