import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileScan {
    private String path; // L'adresse du fichier texte
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    /**
     * Classe permettant de traiter des données sortantes d'un fichier texte (lecture...)
     * @param path
     */
    public FileScan(String path){
        this.path = path;
        try {
            // Cette ligne peut causer une erreur de type IO si le fichier n'est pas trouvé
            this.fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print ligne par ligne le contenu du fichier ciblé
     */
    public void printLineByLine(){
        try{
            String line = bufferedReader.readLine();
            while (line != null){
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
