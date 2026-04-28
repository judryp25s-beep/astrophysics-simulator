import java.awt.*;

public class Astre {
    private String nom;
    private String description;
    private double masse;
    private X_Y diametre;
    private double temperature;
    private X_Y acceleration;
    private X_Y vitesse;
    private X_Y position;
    private Espace espace;
    static double G = 6.674;
    public static final Color DARK_BLUE = new Color(10, 10, 25);

    // Planètes
    public static final Color MERCURY = new Color(165, 165, 165);
    public static final Color VENUS = new Color(227, 220, 175);
    public static final Color MARS = new Color(193, 68, 14);
    public static final Color JUPITER = new Color(216, 202, 157);
    public static final Color SATURN = new Color(191, 155, 95);
    public static final Color URANUS = new Color(209, 231, 231);
    public static final Color NEPTUNE = new Color(63, 130, 242);

    // Étoiles
    public static final Color RED_DWARF = new Color(255, 82, 0);
    public static final Color SUN_WHITE = new Color(255, 255, 240);
    public static final Color BLUE_GIANT = new Color(155, 176, 255);
    public static final Color WHITE_DWARF = new Color(248, 247, 255);

    // Trous Noirs et Blancs
    public static final Color BLACK_HOLE_DISK = new Color(255, 140, 0);
    public static final Color WHITE_HOLE = new Color(255, 255, 255);

    // Espace Profond
    public static final Color NEBULA_PINK = new Color(255, 0, 102);
    public static final Color COSMIC_LATTE = new Color(255, 248, 231);


    public Astre(String nom, String description, double masse, X_Y diametre, double temperature, X_Y acceleration,
            X_Y vitesse, X_Y position) {
        this.nom = nom;
        this.description = description;
        this.masse = masse;
        this.diametre = diametre;
        this.temperature = temperature;
        this.acceleration = acceleration;
        this.vitesse = vitesse;
        this.position = position;
    }

    public Astre(String nom, String description, double masse, double dx, double dy, double temperature, double ax,
            double ay, double vx, double vy, double px, double py) {
        this.nom = nom;
        this.description = description;
        this.masse = masse;
        this.diametre = new X_Y(dx, dy);
        this.vitesse = new X_Y(vx, vy);
        this.acceleration = new X_Y(ax, ay);
        this.position = new X_Y(px, py);
        this.temperature = temperature;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMasse(double masse) {
        this.masse = masse;
    }

    public void setDiametre(X_Y diametre) {
        if (diametre.getX() > 0 && diametre.getY() > 0) {
            this.diametre = diametre;

        }
    }

    public void setVitesse(X_Y vitesse) {
        this.vitesse = vitesse;
    }

    public void setAcceleration(X_Y acceleration) {
        this.acceleration = acceleration;
    }

    public void setPosition(X_Y position) {
        if (position.getX() > 0 && position.getY() > 0) {
            this.position = position;

        }
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public double getMasse() {
        return masse;
    }

    public X_Y getDiametre() {
        return diametre;
    }

    public X_Y getVitesse() {
        return vitesse;
    }

    public X_Y getAcceleration() {
        return acceleration;
    }

    public X_Y getPosition() {
        return position;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setEspace(Espace e) {
        this.espace = e;
    }

    public void updatePosition(double dt) {
        double px = this.position.getX();
        double py = this.position.getY();

        double dx = 0.5*this.acceleration.getX()*dt*dt + this.vitesse.getX() * dt;
        double dy = 0.5*this.acceleration.getY()*dt*dt + this.vitesse.getY() * dt;

        this.position.setX(px+dx);
        this.position.setY(py+dy);
        System.out.println("Déplacement de " + this.getNom() + " " + px + ", " + py + " de " + dx + ", " + dy);


    }

    public void updateVitesse(double dt) {
        double vx = this.vitesse.getX();
        double vy = this.vitesse.getY();

        double dx = this.acceleration.getX()*dt;
        double dy = this.acceleration.getY()*dt;

        this.vitesse.setX(vx+dx);
        this.vitesse.setY(vy+dy);

    }

    public void updateAcceleration(double dt) {
        double dax = 0, day = 0;
        if (this.masse == 0) return;
        for (int i = 0; i < this.espace.getAstres().size(); i++) {
            Astre a = (Astre) this.espace.getAstres().get(i);
            if (a == this || a.getMasse() == 0) continue;
            double dx = a.getPosition().getX() - this.position.getX();
            double dy = a.getPosition().getY() - this.position.getY();
            double d = this.calculerDistance(a);
            double da = this.accelerationGAvec(a);
            dax += dx*da/d;
            day += dy*da/d;
            System.out.println("Acceleration " + this.nom + " " + a.getNom() + " " + dax + " " + day + " " + this.acceleration.getX() + " " + this.acceleration.getY());
        }
        this.acceleration.setX(dax);
        this.acceleration.setY(day);
    }

    public double accelerationGAvec(Astre a) {
        double d = this.calculerDistance(a);
        return a.getMasse()*Astre.G/d*d;
    }

    // distance entre deux astres
    // voir si deux astres sont en colision (diamètre contre centre)

    public void updateCollision(double dt) {
        for (int i = 0; i < this.espace.getAstres().size(); i++) {
            Astre a = (Astre) this.espace.getAstres().get(i);
            if (a == this) continue;
            if (this.verifyCollision(a)) {
                Astre A = this.fusion(a);
                this.espace.ajouterAstre(A);
                this.espace.retirerAstre(a);
                this.espace.retirerAstre(this);
            }
        }
    }

    public void update(double dt) {
        this.updatePosition(dt);
        this.updateVitesse(dt);
        this.updateAcceleration(dt);
        this.updateCollision(dt);
    }

    public double calculerDistance(Astre a){
        double d = 0;
        double x1 = this.getPosition().getX();
        double y1 = this.getPosition().getY();

        double x2 = a.getPosition().getX();
        double y2 = a.getPosition().getY();
        
        d = Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1), 2));
        return d;
    }

    public boolean verifyCollision(Astre a) {
        double dx = this.diametre.getX();
        double dy = this.diametre.getY();
        double x = this.position.getX();
        double y = this.position.getY();
        double x_ = a.getPosition().getX();
        double y_ = a.getPosition().getY();
        if (
            (x - dx <= x_) &&
            (x + dx >= x_) && 
            (y - dy <= y_) && 
            (y +  dy >= y_)
        ) return true;
        return false;
    }

    public Astre fusion(Astre a) {
        String N = this.nom + a.getNom();
        String D = this.description + " fusionné avec " + a.getDescription();
        double M = this.masse + a.getMasse();
        double dx = this.diametre.getX() + a.getDiametre().getX();
        double dy = this.diametre.getY() + a.getDiametre().getY();
        double px = (this.position.getX() + a.getPosition().getX())/2;
        double py = (this.position.getY() + a.getPosition().getY())/2;
        double vx = (this.masse * this.vitesse.getX() + a.getMasse() * a.getVitesse().getX()) / (this.masse + a.getMasse());
        double vy = (this.masse * this.vitesse.getY() + a.getMasse() * a.getVitesse().getY()) / (this.masse + a.getMasse());
        double ax = (this.masse * this.acceleration.getX() + a.getMasse() * a.getAcceleration().getX()) / (this.masse + a.getMasse());
        double ay = (this.masse * this.acceleration.getY() + a.getMasse() * a.getAcceleration().getY()) / (this.masse + a.getMasse());
        double T = (this.masse * this.temperature + a.getMasse() * a.getTemperature()) / (this.masse + a.getMasse());

        Astre A = new Astre(N, D, M, dx, dy, T, ax, ay, vx, vy, px, py);
        return A;
    }

}