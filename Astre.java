public class Astre {
    private String nom;
    private String description;
    private double masse;
    private X_Y diametre;
    private double temperature;
    private X_Y acceleration;
    private X_Y vitesse;
    private X_Y position;

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

    public void updatePosition(double dt) {
        double px = this.position.getX();
        double py = this.position.getY();

        double dx = 0.5*this.acceleration.getX()*dt*dt + this.vitesse.getX() * dt;
        double dy = 0.5*this.acceleration.getY()*dt*dt + this.vitesse.getY() * dt;

        this.position.setX(px+dx);
        this.position.setX(py+dy);

    }

    public void updateVitesse(double dt) {
        double vx = this.vitesse.getX();
        double vy = this.vitesse.getY();

        double dx = this.acceleration.getX()*dt;
        double dy = this.acceleration.getY()*dt;

        this.position.setX(vx+dx);
        this.position.setX(vy+dy);

    }

    public void update(double dt) {
        this.updatePosition(dt);
        this.updateVitesse(dt);
    }

}