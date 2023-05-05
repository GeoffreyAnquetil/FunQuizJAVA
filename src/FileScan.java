import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileScan {
    private String path; // L'adresse du fichier texte
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    /**
     * Classe permettant de traiter des données entrantes et sortantes d'un fichier texte (lecture, écriture...)
     * @param path
     */
    public FileScan(String path){
        this.path = path;
    }

    /**
     *
     * @return Un tableau à deux dimensions avec en ligne les lignes du fichier text séparées en
     *  colonnes par les ; dans le fichier texte
     *
     */
    public ArrayList<String[]> toTab(){
        try {
            // Cette ligne peut causer une erreur de type IO si le fichier n'est pas trouvé
            this.fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    public void printLineByLine() {
        try {
            // Cette ligne peut causer une erreur de type IO si le fichier n'est pas trouvé
            this.fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
