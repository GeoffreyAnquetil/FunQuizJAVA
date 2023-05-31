import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PageQuizMultiA implements ActionListener {
    private final JFrame frame;
    private final JLabel questionLabel;
    private final JButton optionButton1;
    private final JButton optionButton2;
    private final JButton optionButton3;
    private final JButton optionButton4;
    private final Question q;
    private final Score scoreA;
    private final Score scoreB;
    private final JLabel scoreLabel;
    private final JLabel timerLabel;
    private ExecutorService service;
    private Future<?> future;
    private int Arange=0;
    private int Brange=-1;
    private int currentTeam=1;
    private final int tailleA;
    private final int tailleB;
    private final ArrayList<Question> qListA;
    private final ArrayList<Question> qListB;
    private final Utilisateur user;
    private final Utilisateurs users;
    public PageQuizMultiA(ArrayList<Question> questionA,ArrayList<Question> questionB, Utilisateur user,Utilisateurs users) {
        this.user = user;
        this.users = users;
        //initialise taille(nombre de question), la liste
        tailleA= questionA.size();
        tailleB= questionB.size();
        qListA = questionA;
        qListB = questionB;

        //initialise la Question actuel
        q = new Question();
        q.setReponse(qListA.get(Arange).getReponse());
        q.setIntitule(qListA.get(Arange).getIntitule());
        q.setOption1(qListA.get(Arange).getOption1());
        q.setOption2(qListA.get(Arange).getOption2());
        q.setOption3(qListA.get(Arange).getOption3());
        q.setOption4(qListA.get(Arange).getOption4());
        q.setPoints(qListA.get(Arange).getPoints());

        //début de la page
        frame = new JFrame("Quiz Page Multi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        //Intitule question
        JPanel questionPanel = new JPanel();
        questionLabel = new JLabel(q.getIntitule()); //ici, on met l'intitule de la question
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionPanel.add(questionLabel);
        frame.add(questionPanel, BorderLayout.NORTH);

        //TIMER
        JPanel timerPanel = new JPanel();
        timerLabel = new JLabel("Timer "+"00:00:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerPanel.add(timerLabel);
        frame.add(timerPanel, BorderLayout.CENTER);

        //Score
        scoreA = new Score(0);
        scoreB = new Score(0);
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Equipe A.Votre score : " + scoreA.getScore());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scorePanel.add(scoreLabel);
        frame.add(scorePanel, BorderLayout.WEST);

        //Proposition
        JPanel optionsPanel = new JPanel();
        optionButton1 = new JButton(q.getOption1()); //Option 1
        optionButton2 = new JButton(q.getOption2()); //Option 2
        optionButton3 = new JButton(q.getOption3()); //Option 3
        optionButton4 = new JButton(q.getOption4()); //Option 4
        optionsPanel.add(optionButton1);
        optionsPanel.add(optionButton2);
        optionsPanel.add(optionButton3);
        optionsPanel.add(optionButton4);
        frame.add(optionsPanel, BorderLayout.SOUTH);

        optionButton1.addActionListener(this);
        optionButton2.addActionListener(this);
        optionButton3.addActionListener(this);
        optionButton4.addActionListener(this);
        frame.setVisible(true);

        //ON lance le timer
        startTimer(10); // Délai de 10 secondes
    }


    public void actionPerformed (ActionEvent event){
        JButton bouton = (JButton) event.getSource(); // bouton prend la source de l'évènement(elle prend le button qui a été appuyé)
        stopTimer();
        checkAnswer(bouton.getText(),q.getReponse());

    }

    //vérifie la réponse
    private void checkAnswer(String selectedOption,String correctOption) {

        if (selectedOption.equals("Trop tard") ){
            JOptionPane.showMessageDialog(frame, "Trop Tard ");
        }
        // Comparer la réponse sélectionnée avec la réponse correcte
        if (selectedOption.equals(correctOption) ) {
            JOptionPane.showMessageDialog(frame, "Bonne réponse !");
            //donne les points a la bonne équipe ( équipeA (0),equipeB (1) )
            if(currentTeam == 1) {
                scoreA.addScore(q.getPoints());
            }else{
                scoreB.addScore(q.getPoints());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Mauvaise réponse ! La réponse était "+q.getReponse());
            if (currentTeam == 1 && Brange<tailleB) {
                JOptionPane.showMessageDialog(frame, "Au tour de l'équipe B");
                currentTeam = 2;
            }else {
                if (currentTeam == 2 && Arange < tailleB) {
                    JOptionPane.showMessageDialog(frame, "Au tour de l'équipe A");
                    currentTeam = 1;
                }
            }
        }

        //question suivante
        changeQuestionMulti();
    }

    /**
     *  On instaure la prochaine question ou la fin de la partie
     */
    private void changeQuestionMulti() {
        if (currentTeam == 1) {
            Arange++;
            if (Arange < tailleA) { // si l'équipe A n'a plus de question, on fait jouer l'équipe B
                questionA();
            } else {
                Brange++;
                currentTeam = 2;
                if (Brange < tailleB) {
                    JOptionPane.showMessageDialog(frame, "Au tour de l'équipe B");
                    questionB();
                } else { // il n'y a plus aucune question
                    new PageFinMulti( scoreA.getScore(),scoreB.getScore(),user,users);
                    frame.dispose();
                }
            }
        } else {
            Brange++;
            if (Brange < tailleB) { // Si l'équipe B n'a plus de question, on fait jouer l'équipe A
                questionB();
            } else {
                Arange++;
                currentTeam = 1;
                if (Arange < tailleA) {
                    JOptionPane.showMessageDialog(frame, "Au tour de l'équipe A");
                    questionA();
                } else { // il n'y a plus aucune question
                    new PageFinMulti( scoreA.getScore(),scoreB.getScore(),user,users);
                    frame.dispose();
                }
            }
        }
    }

    /**
     * Change la question pour les question de la liste A
     */
    public void questionA(){
        q.setReponse(qListA.get(Arange).getReponse());
        q.setIntitule(qListA.get(Arange).getIntitule());
        q.setOption1(qListA.get(Arange).getOption1());
        q.setOption2(qListA.get(Arange).getOption2());
        q.setOption3(qListA.get(Arange).getOption3());
        q.setOption4(qListA.get(Arange).getOption4());
        q.setPoints(qListA.get(Arange).getPoints());

        questionLabel.setText(q.getIntitule());
        optionButton1.setText(q.getOption1());
        optionButton2.setText(q.getOption2());
        optionButton3.setText(q.getOption3());
        optionButton4.setText(q.getOption4());
        scoreLabel.setText("Equipe A.Votre score : " + scoreA.getScore());
        service.shutdownNow();
        startTimer(20); // Délai de 10 secondes
    }

    /**
     * Change les questions pour la liste de l'équipe B
     */
    public void questionB(){
        q.setReponse(qListB.get(Brange).getReponse());
        q.setIntitule(qListB.get(Brange).getIntitule());
        q.setOption1(qListB.get(Brange).getOption1());
        q.setOption2(qListB.get(Brange).getOption2());
        q.setOption3(qListB.get(Brange).getOption3());
        q.setOption4(qListB.get(Brange).getOption4());
        q.setPoints(qListB.get(Brange).getPoints());

        questionLabel.setText(q.getIntitule());
        optionButton1.setText(q.getOption1());
        optionButton2.setText(q.getOption2());
        optionButton3.setText(q.getOption3());
        optionButton4.setText(q.getOption4());
        scoreLabel.setText("Equipe B.Votre score : " + scoreB.getScore());
        service.shutdownNow();
        startTimer(18); // Délai de 10 secondes
    }

    /**
     * Cette procédure sert à lancer un timer en thread
     * @param delay : Chronometre
     */
    private void startTimer(int delay) {
        service = Executors.newSingleThreadExecutor();
        try {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    int secondsPassed = 0;
                    boolean timeout=false;
                    while (!Thread.currentThread().isInterrupted() && !timeout) {
                        try {
                            Thread.sleep(1000); // 1000 millisec = 1sec
                            secondsPassed++;
                            updateTimerLabel(secondsPassed, delay);
                            if (secondsPassed >= delay) {
                                timeout=true;
                                stopTimer();
                                checkAnswer("Trop tard",q.getReponse());
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            };

            future = service.submit(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Arrete le timer
     */
    private void stopTimer() {
        if (future != null && !future.isDone()) {
            future.cancel(true);
            service.shutdownNow();
        }
    }

    /**
     * Modifie l'affichage
     * @param secondsPassed : temps passé en seconde
     * @param delai : chronometre
     */
    private void updateTimerLabel(int secondsPassed, int delai) {
        long hours = TimeUnit.SECONDS.toHours(secondsPassed);
        long minutes = TimeUnit.SECONDS.toMinutes(secondsPassed) % 60;
        long seconds = delai - (secondsPassed % 60);

        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText("Timer "+time);
    }
}




