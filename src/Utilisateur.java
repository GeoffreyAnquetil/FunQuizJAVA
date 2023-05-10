import java.io.Serializable;

public class Utilisateur extends Personne implements Serializable {

    private String pseudo;
    private String mdp;


    public Utilisateur(String prenom, String nom, int age, String pseudo, String mdp) {
        super(prenom, nom, age);
        this.pseudo = pseudo;
        this.mdp = mdp;
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
}
