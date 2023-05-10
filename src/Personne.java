import java.io.Serializable;

/**
 * Classe représentant une personne
 */
public class Personne implements Serializable {
    //Pseudo;Password;IsAdmin;Image
    private String pseudo; // pseudo de la personne
    private String password; // mot de passe de la personne
    private boolean isAdmin; // booléen indiquant si la personne est admin
    private String Image; // chemin vers l'image de la personne

    /**
     * @param pseudo pseudo de la personne
     * @param password mot de passe de la personne
     * @param isAdmin booléen indiquant si la personne est admin
     * @param image chemin vers l'image de la personne
     */
    public Personne(String pseudo, String password, boolean isAdmin, String image) {
        this.pseudo = pseudo;
        this.password = password;
        this.isAdmin = isAdmin;
        Image = image;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
