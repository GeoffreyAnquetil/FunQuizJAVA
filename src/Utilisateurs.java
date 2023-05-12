import java.io.*;
import java.util.HashMap;


/**
 * Classe permettant de stocker les utilisateurs inscrits au quiz dans un fichier texte
 */
public class Utilisateurs implements Serializable {
    private HashMap<String, Utilisateur> users;

    // Chemin vers le fichier où sont stockés les utilisateurs enregistrés
    private final String path = "./src/users/usersData.txt";

    public Utilisateurs(){}

    public Utilisateurs(HashMap<String, Utilisateur> users) {
        this.users = users;
    }

    /**
     * Sérialise l'objet dans un fichier
     */
    public void serialize(){
        try{
            FileOutputStream fichier = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(this.users);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Désérialise un objet contenu dans un fichier
     */
    public void deserialize() {
        HashMap<String, Utilisateur> u = new HashMap<String, Utilisateur>();
        try {
            FileInputStream fichier = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            u = (HashMap<String, Utilisateur>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        } users = u;
    }

    public HashMap<String, Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, Utilisateur> users) {
        this.users = users;
    }
}
