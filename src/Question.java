import java.io.Serializable;

public class Question implements Serializable {
    private String difficulte;
    private String intitule;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String reponse;
    private int points;
    private boolean DejaChoisi;

    public Question(String difficulte, String intitule,
                    String option1, String option2, String option3, String option4,
                    String reponse, int points, boolean dejaChoisi) {
        this.difficulte = difficulte;
        this.intitule = intitule;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.reponse = reponse;
        this.points = points;
        DejaChoisi = dejaChoisi;
    }

    public Question(){}

    public String toString(){
        return difficulte + "," + intitule + "," +
                option1 + "," + option2 + "," + option3 + "," + option4 + "," +
                reponse + "," + points + "," + DejaChoisi;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public String getIntitule(){
        return intitule;
    }

    public int getPoints() {
        return points;
    }

    public String getReponse() {
        return reponse;
    }

    public boolean getDejaChoisi(){ return DejaChoisi;}

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public boolean vérifie(String elt){
        return (this.reponse==elt);
    }

    public void setDejaChoisi(boolean dejaChoisi) {
        DejaChoisi = dejaChoisi;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }
}
