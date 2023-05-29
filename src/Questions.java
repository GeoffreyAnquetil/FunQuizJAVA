import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Questions implements Serializable {

    private ArrayList<Question> questions = new ArrayList<>();

    public Questions(){};

    public Questions(ArrayList<Question> questions){
        this.questions = questions;
    }

    @Override
    public String toString(){
        StringBuilder chaine = new StringBuilder();
        for(Question question : questions){
            chaine.append(question.toString());
            chaine.append("/");
        }
        return chaine.toString();
    }

    /**
     * Sérialise l'objet dans un fichier
     */
    public void serialize(String path) throws IOException {
        ArrayList<String[]> array = new ArrayList<>();
        // On sépare chaque question dans des String différentes
        String[] qs = this.toString().split("/");

        //Pour chaque String de questions
        for(String q : qs){
            //On split les données des questions par "," et on les ajoute à array
            array.add(q.split(","));
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

        for(String[] q : array){
            Question question = new Question(q[0],
                    q[1],
                    q[2],
                    q[3],
                    q[4],
                    q[5],
                    q[6],
                    Integer.parseInt(q[7]),
                    Boolean.parseBoolean(q[8]));
            this.questions.add(question);
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
