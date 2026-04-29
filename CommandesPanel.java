import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class CommandesPanel extends JPanel {

    private JTextField nom_tf, desc_tf, diam_tf, vitx_tf, vity_tf, posx_tf, posy_tf, temp_tf, mass_tf;

    private JButton action_button, zoomp_button, zoomn_button, movel_button, mover_button, movet_button, moved_button, pause_play_button, moveR_button, time_sp, time_sl, edit_button, remove_button;
    private JScrollPane astre_type_panel;
    private JList<String> astre_liste;
    
    private Espace espace;
    private Fenetre fenetre;
    private double zoom = 1.1;
    private double move = 1;

    public CommandesPanel(Fenetre fenetre) {
        this.espace = fenetre.getEspace();
        this.fenetre = fenetre;
        JPanel groupe_composants;

        this.setBackground(new Color(240, 240, 240));
        this.setLayout(new GridLayout(4, 4, 10, 10));

        int tf_width = 15;
        this.nom_tf = new JTextField(tf_width);
        this.desc_tf = new JTextField(tf_width);
        this.diam_tf = new JTextField(tf_width);
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

        this.time_sp = new JButton();
        this.time_sp.setText(">>");
        this.time_sp.addMouseListener(new EcouteurBoutonTime(this.fenetre.getAstre_panel(), 1.25));

        this.time_sl = new JButton();
        this.time_sl.setText("<<");
        this.time_sl.addMouseListener(new EcouteurBoutonTime(this.fenetre.getAstre_panel(), 0.25));

        this.moveR_button = new JButton();
        this.moveR_button.setText("↻");
        this.moveR_button.addMouseListener(new EcouteurBoutonMove(this.fenetre.getAstre_panel(),0,0));

        String[] data = {"Planète", "Étoile", "Trou noir", "Trou blanc"};
        this.astre_liste = new JList<>(data);
        this.astre_type_panel = new JScrollPane(this.astre_liste);
        this.astre_liste.setVisibleRowCount(1); // N'affiche qu'une ligne
        this.astre_liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Masquer la barre horizontale, laisser la verticale si besoin (ou l'inverse)
        this.astre_type_panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.astre_type_panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        

        this.edit_button = new JButton();
        this.edit_button.setText("✎");
        this.edit_button.addMouseListener(new EcouteurBoutonEditer(this));

        this.remove_button = new JButton();
        this.remove_button.setText("x");
        this.remove_button.addMouseListener(new EcouteurBoutonEffacer(this));


        JLabel nom_l = new JLabel("Nom   ");
        JLabel desc_l = new JLabel("Desc  ");
        JLabel mass_l = new JLabel("Mass  ");
        JLabel diam_l = new JLabel("Diam ");
        JLabel vitx_l = new JLabel("Vx  ");
        JLabel vity_l = new JLabel("Vy  ");
        JLabel posx_l = new JLabel("x  ");
        JLabel posy_l = new JLabel("y  ");
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
        groupe_composants.add(mass_l);
        groupe_composants.add(this.mass_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(diam_l);
        groupe_composants.add(this.diam_tf);
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
        groupe_composants.add(temp_l);
        groupe_composants.add(this.temp_tf);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.astre_type_panel);
        this.add(groupe_composants);
        
        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.action_button);
        this.action_button.setText("Ajouter");
        this.action_button.addMouseListener(new EcouteurBoutonAjouter(this));
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.zoomp_button);
        groupe_composants.add(this.zoomn_button);
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
        groupe_composants.add(this.moveR_button);
        this.add(groupe_composants);
        
        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.time_sl);
        groupe_composants.add(this.pause_play_button);
        groupe_composants.add(this.time_sp);
        this.add(groupe_composants);

        groupe_composants = new JPanel();
        groupe_composants.setLayout(new FlowLayout());
        groupe_composants.add(this.remove_button);
        groupe_composants.add(this.edit_button);
        this.add(groupe_composants);
    }

    public void update() {
        if (this.fenetre.getAstre_panel().getFocusBody() == null) {
            this.nom_tf.setText("");
            this.desc_tf.setText("");
            this.diam_tf.setText("");
            this.temp_tf.setText("");
            this.mass_tf.setText("");
            this.vitx_tf.setText("");
            this.vity_tf.setText("");
            this.posx_tf.setText("");
            this.posy_tf.setText("");
            return;
        };
        Astre focus = this.fenetre.getAstre_panel().getFocusBody();
        this.nom_tf.setText(focus.getNom());
        this.desc_tf.setText(focus.getDescription());
        this.diam_tf.setText(String.format(Locale.US, "%.2E", focus.getDiametre().getX()));
        this.temp_tf.setText(String.format(Locale.US, "%.2E", focus.getTemperature()));
        this.mass_tf.setText(String.format(Locale.US, "%.2E", focus.getMasse()));
        this.vitx_tf.setText(String.format(Locale.US, "%.2E", focus.getVitesse().getX()));
        this.vity_tf.setText(String.format(Locale.US, "%.2E", focus.getVitesse().getY()));
        this.posx_tf.setText(String.format(Locale.US, "%.2E", focus.getPosition().getX()));
        this.posy_tf.setText(String.format(Locale.US, "%.2E", focus.getPosition().getY()));
    }
    
    public void ajouterAstre() {
        // Récupération et nettoyage des textes de base
        String nom = this.nom_tf.getText().trim();
        String desc = this.desc_tf.getText().trim();
        String type = this.astre_liste.getSelectedValue();

        // Validation stricte des champs obligatoires pour la création
        if (nom.isEmpty() || this.diam_tf.getText().trim().isEmpty() || 
            this.posx_tf.getText().trim().isEmpty() || this.posy_tf.getText().trim().isEmpty()) {
            System.err.println("Ajout impossible : Nom, Diamètre et Positions sont obligatoires.");
            return;
        }

        // Parsing de toutes les valeurs (les obligatoires et les optionnelles)
        double diam_ = parseDoubleSafe(this.diam_tf.getText());
        double posx_ = parseDoubleSafe(this.posx_tf.getText());
        double posy_ = parseDoubleSafe(this.posy_tf.getText());
        double mass_ = parseDoubleSafe(this.mass_tf.getText());
        double temp_ = parseDoubleSafe(this.temp_tf.getText());
        double vitx_ = parseDoubleSafe(this.vitx_tf.getText());
        double vity_ = parseDoubleSafe(this.vity_tf.getText());

        // Envoi à la logique métier
        if (type == null) {
            System.out.println("Aucune selection");
            return;
        }

        this.espace.ajouterAstre(nom, desc, type, mass_, diam_, diam_, temp_, 0, 0, vitx_, vity_, posx_, posy_);
        System.out.println("Astre ajouté avec succès : " + nom);
    }

    public void editerAstre() {
        Astre focus = this.fenetre.getAstre_panel().getFocusBody();
        if (focus == null) return;

        // --- 1. CHAMPS TEXTES ---
        String nom = this.nom_tf.getText().trim();
        if (!nom.isEmpty()) focus.setNom(nom);

        String desc = this.desc_tf.getText().trim();
        if (!desc.isEmpty()) focus.setDescription(desc);

        // --- 2. CHAMPS NUMÉRIQUES (Mise à jour sélective) ---
        
        // Masse
        if (!this.mass_tf.getText().trim().isEmpty()) {
            focus.setMasse(parseDoubleSafe(this.mass_tf.getText()));
        }

        // Diamètre
        if (!this.diam_tf.getText().trim().isEmpty()) {
            focus.setDiametre(parseDoubleSafe(this.diam_tf.getText()));
        }

        // Température
        if (!this.temp_tf.getText().trim().isEmpty()) {
            focus.setTemperature(parseDoubleSafe(this.temp_tf.getText()));
        }

        // --- 3. VECTEURS (Position et Vitesse) ---
        // On vérifie chaque axe indépendamment pour permettre de ne changer que X ou Y
        
        double newPosX = focus.getPosition().getX();
        double newPosY = focus.getPosition().getY();
        boolean posChanged = false;

        if (!this.posx_tf.getText().trim().isEmpty()) {
            newPosX = parseDoubleSafe(this.posx_tf.getText());
            posChanged = true;
        }
        if (!this.posy_tf.getText().trim().isEmpty()) {
            newPosY = parseDoubleSafe(this.posy_tf.getText());
            posChanged = true;
        }
        if (posChanged) focus.setPosition(newPosX, newPosY);

        double newVitX = focus.getVitesse().getX();
        double newVitY = focus.getVitesse().getY();
        boolean vitChanged = false;

        if (!this.vitx_tf.getText().trim().isEmpty()) {
            newVitX = parseDoubleSafe(this.vitx_tf.getText());
            vitChanged = true;
        }
        if (!this.vity_tf.getText().trim().isEmpty()) {
            newVitY = parseDoubleSafe(this.vity_tf.getText());
            vitChanged = true;
        }
        if (vitChanged) focus.setVitesse(newVitX, newVitY);
    }
    public void effacerAstre() {
        if (this.fenetre.getAstre_panel().getFocusBody() != null)
        this.espace.retirerAstre(this.fenetre.getAstre_panel().getFocusBody());
    }

    /**
     * Tente de convertir une String en double. 
     * Retourne 0 si le champ est vide, nul ou mal formaté.
     */
    
    
    private double parseDoubleSafe(String text) {
    if (text == null || text.trim().isEmpty()) {
        return 0.0;
    }
    try {
        return Double.parseDouble(text.trim().replace(',', '.')); // Gère aussi les virgules
    } catch (NumberFormatException e) {
        System.err.println("Erreur de format numérique pour : " + text);
        return 0.0;
    }
}
}
