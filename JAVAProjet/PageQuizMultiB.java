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

public class PageQuizMultiB implements ActionListener {
    private final JFrame frame;
    private final JLabel questionLabel;
    private final JButton optionButton1;
    private final JButton optionButton2;
    private final JButton optionButton3;
    private final JButton optionButton4;
    private final Question q;
    private Score scoreA;
    private Score scoreB;
    private final JLabel scoreLabelA;
    private final JLabel scoreLabelB;
    private final JLabel timerLabel;
    private ExecutorService service;
    private Future<?> future;
    private int j=0;
    private final int taille;
    private final ArrayList<Question> qList;
    private int currentTeam=1;
    private final Utilisateur user;
    private final Utilisateurs users;

    /**
     * Affichage de la page de jeu pour 2 equipes dans la version B
     * @param question : la liste de Question utilisée pour les 2 equipes
     */
    public PageQuizMultiB(ArrayList<Question> question, Utilisateur user, Utilisateurs users) {
        this.user = user;
        this.users = users;

        //initialise la Question actuel
        q = new Question();
        q.setReponse(question.get(j).getReponse());
        q.setIntitule(question.get(j).getIntitule());
        q.setOption1(question.get(j).getOption1());
        q.setOption2(question.get(j).getOption2());
        q.setOption3(question.get(j).getOption3());
        q.setOption4(question.get(j).getOption4());
        q.setPoints(question.get(j).getPoints());

        //initialise taille(nombre de question), la liste

        taille= question.size();
        qList = question;

        //début de la page
        frame = new JFrame("Quiz Page");
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
        scoreLabelA = new JLabel("EquipeA : " + scoreA.getScore());
        scoreLabelA.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabelB = new JLabel("EquipeB : " + scoreB.getScore());
        scoreLabelB.setFont(new Font("Arial", Font.BOLD, 20));
        scorePanel.add(scoreLabelA);
        scorePanel.add(scoreLabelB);
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

        if (selectedOption.equals("Trop tard")) {
            JOptionPane.showMessageDialog(frame, "Trop Tard");
        }
        // Comparer la réponse sélectionnée avec la réponse correcte
        if (selectedOption.equals(correctOption)) {
            JOptionPane.showMessageDialog(frame, "Bonne réponse !");
            //donne les points a la bonne équipe ( équipeA (0),equipeB (1) )
            if (currentTeam == 1) {
                scoreA.addScore(q.getPoints());
            } else {
                scoreB.addScore(q.getPoints());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Mauvaise réponse ! La réponse était " + q.getReponse());
            if (currentTeam == 1 && j<taille-1) {
                JOptionPane.showMessageDialog(frame, "Au tour de l'équipe B");
                currentTeam = 2;
            }else {
                if (currentTeam == 2 && j < taille-1) {
                    JOptionPane.showMessageDialog(frame, "Au tour de l'équipe A");
                    currentTeam = 1;
                }
            }
        }
        changeQuestion();
    }

    /**
     *  On instaure la prochaine question ou la fin de la partie
     */
    private void changeQuestion() {
        j++;
        if(j<taille){
            q.setReponse(qList.get(j).getReponse());
            q.setIntitule(qList.get(j).getIntitule());
            q.setOption1(qList.get(j).getOption1());
            q.setOption2(qList.get(j).getOption2());
            q.setOption3(qList.get(j).getOption3());
            q.setOption4(qList.get(j).getOption4());
            q.setPoints(qList.get(j).getPoints());

            questionLabel.setText(q.getIntitule());
            optionButton1.setText(q.getOption1());
            optionButton2.setText(q.getOption2());
            optionButton3.setText(q.getOption3());
            optionButton4.setText(q.getOption4());
            scoreLabelA.setText("EquipeA : "+ scoreA.getScore());
            scoreLabelB.setText("EquipeB : "+ scoreB.getScore());
            service.shutdownNow();

            startTimer(10); // Délai de 10 secondes
        } else {
            new PageFinMulti( scoreA.getScore(),scoreB.getScore(),user,users);
            frame.dispose();
        }
    }


    private void startTimer(int delay) {
        service = Executors.newSingleThreadExecutor();
        try {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    int secondsPassed = 0;
                    boolean timeout = false; // ceci empeche une boucle crée par checkAnswer (JOptionPane)
                    while (!Thread.currentThread().isInterrupted() && !timeout) {
                        try {
                            Thread.sleep(1000); // 1000 millisec = 1sec
                            secondsPassed++;
                            updateTimerLabel(secondsPassed, delay);
                            if (secondsPassed >= delay) {
                                timeout = true;
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

    private void stopTimer() {
        if (future != null && !future.isDone()) {
            future.cancel(true);
            service.shutdownNow();
        }
        service.shutdownNow();
    }

    private void updateTimerLabel(int secondsPassed, int delai) {
        long hours = TimeUnit.SECONDS.toHours(secondsPassed);
        long minutes = TimeUnit.SECONDS.toMinutes(secondsPassed) % 60;
        long seconds = delai - (secondsPassed % 60);

        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText("Timer "+time);
    }
}
