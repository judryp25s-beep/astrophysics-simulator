import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Fenetre extends JFrame {
   private CommandesPanel commandes; 
   private AstrePanel astre_panel; 
   private Espace espace;

    public Fenetre(Espace espace){
        this.espace = espace;
        this.astre_panel = new AstrePanel(espace.getAstres());
        this.commandes = new CommandesPanel(this);
        this.setTitle(espace.getNom());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
        this.add(astre_panel, BorderLayout.CENTER);
        this.add(commandes, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public AstrePanel getAstre_panel() {
        return astre_panel;
    }

    public CommandesPanel getCommandes() {
        return commandes;
    }

    public Espace getEspace() {
        return espace;
    }
}
