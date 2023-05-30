import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Themes {

    private ArrayList<String> themes;

    public Themes(){};

    public Themes(ArrayList<String> themes){
        this.themes = themes;
    }

    /**
     * Sérialise l'objet dans un fichier
     */
    public void serialize(String path) throws IOException {
        ArrayList<String[]> array = new ArrayList<>();
        String[] list = new String[themes.size()];
        for(int i=0; i<themes.size(); i++){
            list[i] = themes.get(i);
        }
        array.add(list);

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

        this.themes = new ArrayList<>();
        this.themes.addAll(Arrays.asList(array.get(0)));
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();
        for(String theme : themes){
            chaine.append(theme);
            chaine.append(";");
        }
        return chaine.toString();
    }

    public ArrayList<String> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<String> themes) {
        this.themes = themes;
    }
}
