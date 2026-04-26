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
    private double G = 6.674;

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
        for (int i = 0; i < this.espace.getAstres().size(); i++) {
            Astre a = (Astre) this.espace.getAstres().get(i);
            if (a == this) continue;
            double dx = a.getPosition().getX() - this.position.getX();
            double dy = a.getPosition().getY() - this.position.getY();
            double d = this.calculerDistance(a);
            double da = this.accelerationGAvec(a);
            dax += dx*da/d;
            day += dy*da/d;
        }
        this.acceleration.setX(this.acceleration.getX() + dax);
        this.acceleration.setY(this.acceleration.getY() + day);
    }

    public double accelerationGAvec(Astre a) {
        double d = this.calculerDistance(a);
        return a.getMasse()*this.G/d*d;
    }

    // distance entre deux astres
    // voir si deux astres sont en colision (diamètre contre centre)

    public void update(double dt) {
        this.updatePosition(dt);
        this.updateVitesse(dt);
        this.updateAcceleration(dt);
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

}