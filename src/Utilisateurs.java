import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Classe permettant de stocker les utilisateurs inscrits au quiz dans un fichier texte
 */
public class Utilisateurs implements Serializable {
    private HashMap<String, Utilisateur> users;

    public Utilisateurs(){}

    public Utilisateurs(HashMap<String, Utilisateur> users) {
        this.users = users;
    }

    @Override
    public String toString(){
        StringBuilder chaine = new StringBuilder();
        for(String key : users.keySet()){
            chaine.append(users.get(key).toString());
            chaine.append("/");
        }
        return chaine.toString();
    }

    /**
     * Sérialise l'objet dans un fichier
     */
    public void serialize(String path) throws IOException {
        ArrayList<String[]> array = new ArrayList<>();
        // On sépare chaque utilisateur dans des String différentes
        String[] utilisateurs = this.toString().split("/");

        //Pour chaque String d'utilisateur
        for(String utilisateur : utilisateurs){
            //On split les données de l'utilisateur par "," et on les ajoute à array
            array.add(utilisateur.split(","));
        }

        //On instancie un CSVFileIO et on écrit l'array dans un fichier destination au format CSV
        CSVFileIO csvFileIO = new CSVFileIO();
        csvFileIO.write(array, path);
    }

    /**
     * Désérialise un objet contenu dans un fichier
     */
    public void deserialize(String path) throws IOException {
        CSVFileIO csvFileIO = new CSVFileIO();

        ArrayList<String[]> array = csvFileIO.readCSV(path);

        for(String[] utilisateur : array){
            Utilisateur user = new Utilisateur(utilisateur[0],
                                                utilisateur[1],
                                                Integer.parseInt(utilisateur[2]),
                                                utilisateur[3],
                                                utilisateur[4],
                                                Boolean.parseBoolean(utilisateur[5]));
            users.put(user.getPseudo(), user);
        }
    }

    public HashMap<String, Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, Utilisateur> users) {
        this.users = users;
    }
}
