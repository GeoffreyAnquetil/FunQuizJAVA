import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe permettant de stocker les utilisateurs inscrits au quiz dans un fichier texte
 */
public class Utilisateurs implements Serializable {
    private HashMap<String, Personne> users;

    public Utilisateurs(){}

    public Utilisateurs(HashMap<String, Personne> users) {
        this.users = users;
    }

    /**
     * Sérialise l'objet dans un fichier donné
     * @param pathToFile chemin d'accès au fichier dans lequel sérialiser l'objet
     */
    public void serialize(String pathToFile){
        try{
            FileOutputStream fichier = new FileOutputStream(pathToFile);
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(this.users);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Désérialise un objet contenu dans un fichier
     * @param pathToFile chemin d'accès au fichier où se trouve l'objet sérialisé
     */
    public void deserialize(String pathToFile){
        HashMap<String, Personne> utilisateursDes = null;
        try{
            FileInputStream fichier = new FileInputStream(pathToFile);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            utilisateursDes = (HashMap<String, Personne>)ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        } this.users = utilisateursDes;
    }

    public HashMap<String, Personne> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, Personne> users) {
        this.users = users;
    }
}
