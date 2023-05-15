public class Admin extends Utilisateur{

    public Admin(String prenom, String nom, int age, String pseudo, String mdp) {
        super(prenom, nom, age, pseudo, mdp, true);
    }

}
