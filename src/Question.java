import java.io.Serializable;

/**
 * Une question constituée d'un intitulé, de propositions, d'un thème, d'une réponse et de points définis
 */
public class Question implements Serializable {
    private String difficulte;
    private String intitule;
    private String theme;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String reponse;
    private int points;

    public Question(String difficulte, String intitule, String theme,
                    String option1, String option2, String option3, String option4,
                    String reponse, int points) {
        this.difficulte = difficulte;
        this.intitule = intitule;
        this.theme = theme;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.reponse = reponse;
        this.points = points;
    }

    public Question(){}

    /**
     * Convertie une question en string
     * @return chaque attribut séparé du précédent par ";"
     */
    public String toString(){
        return difficulte + ";" + intitule + ";" + theme + ";" +
                option1 + ";" + option2 + ";" + option3 + ";" + option4 + ";" +
                reponse + ";" + points;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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
