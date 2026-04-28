import javax.swing.*;
import java.awt.*;

public class CommandesPanel extends JPanel {

    private JTextField nom_tf, desc_tf, diamx_tf, diamy_tf, accx_tf, accy_tf, vitx_tf, vity_tf, posx_tf, posy_tf, temp_tf, mass_tf;

    private JButton action_button, zoomp_button, zoomn_button, movel_button, mover_button, movet_button, moved_button, pause_play_button;
    private Espace espace;
    private Fenetre fenetre;
    private double zoom = 2;
    private int move = 50;

    public CommandesPanel(Fenetre fenetre) {
        this.espace = fenetre.getEspace();
        this.fenetre = fenetre;
        System.out.println("Titre " + this.fenetre.getTitle());
        JPanel groupe_composants;

        this.setBackground(new Color(240, 240, 240));
        this.setLayout(new GridLayout(4, 4, 10, 10));

        int tf_width = 8;
        this.nom_tf = new JTextField(tf_width);
        this.desc_tf = new JTextField(tf_width);
        this.diamx_tf = new JTextField(tf_width);
        this.diamy_tf = new JTextField(tf_width);
        this.accx_tf = new JTextField(tf_width);
        this.accy_tf = new JTextField(tf_width);
        this.vitx_tf = new JTextField(tf_width);
        this.vity_tf = new JTextField(tf_width);
        this.posx_tf = new JTextField(tf_width);
        this.posy_tf = new JTextField(tf_width);
        this.temp_tf = new JTextField(tf_width);
        this.mass_tf = new JTextField(tf_width);
        this.action_button = new JButton();

        this.zoomp_button = new JButton();
        this.zoomp_button.setText("+");
        this.zoomp_button.addMouseListener(new EcouteurBoutonZoom(this.fenetre.getAstre_panel(), this.zoom));

        this.zoomn_button = new JButton();
        this.zoomn_button.setText("-");
        this.zoomn_button.addMouseListener(new EcouteurBoutonZoom(this.fenetre.getAstre_panel(), 1/this.zoom));

        this.movel_button = new JButton();
        this.movel_button.setText("←");
        this.movel_button.addMouseListener(new EcouteurBoutonMove(this.fenetre.getAstre_panel(),this.move, 0));
        
        this.mover_button = new JButton();
        this.mover_button.setText("→");
        this.mover_button.addMouseListener(new EcouteurBoutonMove(this.fenetre.getAstre_panel(),-this.move, 0));


        this.movet_button = new JButton();
        this.movet_button.setText("↑");
        this.movet_button.addMouseListener(new EcouteurBoutonMove(this.fenetre.getAstre_panel(),0, this.move));


        this.moved_button = new JButton();
        this.moved_button.setText("↓");
        this.moved_button.addMouseListener(new EcouteurBoutonMove(this.fenetre.getAstre_panel(),0, -this.move));

        this.pause_play_button = new JButton();
        this.pause_play_button.setText("▷||");
        this.pause_play_button.addMouseListener(new EcouteurBoutonPause(this.fenetre.getAstre_panel()));


        JLabel nom_l = new JLabel("Nom   ");
        JLabel desc_l = new JLabel("Desc  ");
        JLabel mass_l = new JLabel("Mass  ");
        JLabel diamx_l = new JLabel("Diamx ");
        JLabel diamy_l = new JLabel("Diamy ");
        JLabel accx_l = new JLabel("Accx  ");
        JLabel accy_l = new JLabel("Accy  ");
        JLabel vitx_l = new JLabel("Vitx  ");
        JLabel vity_l = new JLabel("Vity  ");
        JLabel posx_l = new JLabel("Posx  ");
        JLabel posy_l = new JLabel("Posy  ");
        JLabel temp_l = new JLabel("Temp  ");

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(nom_l);
        groupe_composants.add(this.nom_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(desc_l);
        groupe_composants.add(this.desc_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(temp_l);
        groupe_composants.add(this.temp_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(mass_l);
        groupe_composants.add(this.mass_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(diamx_l);
        groupe_composants.add(this.diamx_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(diamy_l);
        groupe_composants.add(this.diamy_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(accx_l);
        groupe_composants.add(this.accx_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(accy_l);
        groupe_composants.add(this.accy_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(vitx_l);
        groupe_composants.add(this.vitx_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(vity_l);
        groupe_composants.add(this.vity_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(posx_l);
        groupe_composants.add(this.posx_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(posy_l);
        groupe_composants.add(this.posy_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.movel_button);
        groupe_composants.add(this.mover_button);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.movet_button);
        groupe_composants.add(this.moved_button);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.zoomp_button);
        groupe_composants.add(this.zoomn_button);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.pause_play_button);
        groupe_composants.add(this.action_button);
        this.action_button.setText("Ajouter");
        this.action_button.addMouseListener(new EcouteurBoutonAjouter(this));
        this.add(groupe_composants);

    }

    public void ajouterAstre() {
        String nom = this.nom_tf.getText();
        String desc = this.desc_tf.getText();
        String mass = this.mass_tf.getText();
        String temp = this.temp_tf.getText();
        String diamx = this.diamx_tf.getText();
        String diamy = this.diamy_tf.getText();
        String posx = this.posx_tf.getText();
        String posy = this.posy_tf.getText();
        String accx = this.accx_tf.getText();
        String accy = this.accy_tf.getText();
        String vitx = this.vitx_tf.getText();
        String vity = this.vity_tf.getText();

        double mass_, temp_, vitx_, vity_, accx_, accy_, posx_, posy_, diamx_, diamy_;

        if (nom == null || nom.trim().isEmpty() || diamx == null || diamx.trim().isEmpty() || diamy == null || diamy.trim().isEmpty() || posx == null || posx.trim().isEmpty() || posy == null || posy.trim().isEmpty()) {
            System.out.println("Ajout impossible");
            return;
        }

        if (temp == null || temp.trim().isEmpty()) {
            temp_ = 0;
        } else {
            temp_ = Double.parseDouble(temp);
        }

        
        diamx_ = Double.parseDouble(diamx);
        diamy_ = Double.parseDouble(diamy);
        posx_ = Double.parseDouble(posx);
        posy_ = Double.parseDouble(posy);

        if (accx == null || accx.trim().isEmpty()) {
            accx_ = 0;
        } else {
            accx_ = Double.parseDouble(accx);
        }

        if (mass == null || mass.trim().isEmpty()) {
            mass_ = 0;
        } else {
            mass_ = Double.parseDouble(mass);
        }

        if (accy == null || accy.trim().isEmpty()) {
            accy_ = 0;
        } else {
            accy_ = Double.parseDouble(accy);
        }

        if (vitx == null || vitx.trim().isEmpty()) {
            vitx_ = 0;
        } else {
            vitx_ = Double.parseDouble(vitx);
        }

        if (vity == null || vity.trim().isEmpty()) {
            vity_ = 0;
        } else {
            vity_ = Double.parseDouble(vity);
        }

        this.espace.ajouterAstre(nom, desc, mass_, diamx_, diamy_, temp_, accx_, accy_, vitx_, vity_, posx_, posy_);
        
    }

}
