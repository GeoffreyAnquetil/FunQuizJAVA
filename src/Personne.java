import java.io.Serial;
import java.io.Serializable;

public class Personne implements Serializable {

    private String prenom;
    private String nom;
    private int age;

    // Bugfix qui permet de sérialiser correctement
    @Serial
    private static final long serialVersionUID = 2977695184832216321L;

    public Personne(){}

    @Override
    public String toString(){
        return prenom + "," + nom + "," + age;
    }

    public Personne(String prenom, String nom, int age) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
