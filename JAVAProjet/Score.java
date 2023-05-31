public class Score {
    private int score;
    public Score(int val){
        this.score = val;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void addScore(int val){
        this.score = this.score+val;
    }
    public void minusScore(int val){
        this.score -= val;
    }

}
