import javax.swing.*;
import java.awt.*;

public class CommandesPanel extends JPanel {

    private JTextField nom_tf, desc_tf, diamx_tf, diamy_tf, accx_tf, accy_tf, vitx_tf, vity_tf, posx_tf, posy_tf, temp_tf;

    private JButton action_button;

    public CommandesPanel() {
        JPanel groupe_composants;

        this.setBackground(new Color(240, 240, 240));
        this.setLayout(new GridLayout(3, 4, 10, 10));

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
        this.action_button = new JButton();

        JLabel nom_l = new JLabel("Nom ");
        JLabel desc_l = new JLabel("Desc ");
        JLabel diamx_l = new JLabel("Diamx ");
        JLabel diamy_l = new JLabel("Diamy ");
        JLabel accx_l = new JLabel("Accx ");
        JLabel accy_l = new JLabel("Accy ");
        JLabel vitx_l = new JLabel("Vitx ");
        JLabel vity_l = new JLabel("Vity ");
        JLabel posx_l = new JLabel("Posx ");
        JLabel posy_l = new JLabel("Posy ");
        JLabel temp_l = new JLabel("Temp ");

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.nom_tf);
        groupe_composants.add(nom_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.desc_tf);
        groupe_composants.add(desc_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.temp_tf);
        groupe_composants.add(temp_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.action_button);
        this.action_button.setText("Ajouter");
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.diamx_tf);
        groupe_composants.add(diamx_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.diamy_tf);
        groupe_composants.add(diamy_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.accx_tf);
        groupe_composants.add(accx_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.accy_tf);
        groupe_composants.add(accy_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.vitx_tf);
        groupe_composants.add(vitx_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.vity_tf);
        groupe_composants.add(vity_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.posx_tf);
        groupe_composants.add(posx_l);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.posy_tf);
        groupe_composants.add(posy_l);
        this.add(groupe_composants);

    }
}
