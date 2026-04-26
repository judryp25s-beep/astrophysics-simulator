import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
   private CommandesPanel commandes; 
   private AstrePanel astre_panel; 
   private Espace espace;

    public Fenetre(Espace espace){
        this.espace = espace;
        this.commandes = new CommandesPanel();
        this.astre_panel = new AstrePanel(espace.getAstres());
        this.setName(espace.getNom());
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());
        this.add(astre_panel, BorderLayout.NORTH);
        this.add(commandes, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
