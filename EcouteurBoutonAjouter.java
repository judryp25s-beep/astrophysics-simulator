import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class EcouteurBoutonAjouter implements MouseListener {

    private CommandesPanel commandes;

    public EcouteurBoutonAjouter(CommandesPanel cmd) {
        this.commandes = cmd;
    }

    public void mouseClicked(MouseEvent e){
        commandes.ajouterAstre();
    }
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
