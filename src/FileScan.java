import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileScan {
    private final String path; // L'adresse du fichier texte

    /**
     * Classe qui permet de gérer la lecture dans un fichier
     * @param path chemin vers le fichier à lire
     */
    public FileScan(String path){
        this.path = path;
    }

    /**
     * Récupère une donnée d'un champ et d'un id donné dans un fichier au format requis.
     * @param field le champ qui nous intéresse
     * @param id l'id qui nous intéresse
     * @return
     */
    public String getData(String field, String id) throws IOException {
        boolean fieldExists = false;
        ArrayList<String[]> tab = this.toTab();
        for (int i = 0; i < tab.size(); i++) {
            for (int j = 0; j < tab.get(i).length; j++) {
                if((tab.get(0)[j].equals(field)) && (tab.get(i)[0].equals(id))){
                    return tab.get(i)[j];
                }
            }
        } return "Err";
    }

    /**
     * Renvoie Un tableau à deux dimensions avec en ligne les lignes du fichier text séparées en
     *         colonnes par les ; dans le fichier texte
     * @return Un tableau à deux dimensions avec en ligne les lignes du fichier text séparées en
     *  colonnes par les ; dans le fichier texte
     *
     */
    public ArrayList<String[]> toTab() throws IOException{
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String[]> res = new ArrayList<String[]>();
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] ligne = line.split(";");
                res.add(ligne);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } return res;
    }

    /**
     * Print ligne par ligne le contenu du fichier ciblé
     */
    public void printLineByLine() throws IOException{
        //Déclarations
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
