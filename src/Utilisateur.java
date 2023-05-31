import java.io.Serial;
import java.io.Serializable;

public class Utilisateur extends Personne implements Serializable {

    private String pseudo;
    private String mdp;
    private boolean isAdmin = false;
    private boolean suspendu = false;

    @Serial
    private static final long  serialVersionUID = 3975315987111895119L;

    public Utilisateur(){}

    public Utilisateur(String prenom, String nom, int age, String pseudo, String mdp, boolean isAdmin, boolean suspendu) {
        super(prenom, nom, age);
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.isAdmin = isAdmin;
        this.suspendu = suspendu;
    }

    @Override
    public String toString(){
        return this.getPrenom() + ";" + this.getNom() + ";" + this.getAge() + ";"
                + pseudo + ";" + mdp + ";" + isAdmin + ";" + suspendu;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public boolean isSuspendu() {
        return suspendu;
    }

    public void setSuspendu(boolean suspendu) {
        this.suspendu = suspendu;
    }
}
