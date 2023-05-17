/**
 * Classe admin qui rajoute des méthodes d'administration à la classe Utilisateur
 */
public class Admin extends Utilisateur{

    public Admin(String prenom, String nom, int age, String pseudo, String mdp) {
        super(prenom, nom, age, pseudo, mdp, true);
    }

    public void suspendre(Utilisateur user){

    }

    public void supprimerCompte(Utilisateur user){

    }

}
