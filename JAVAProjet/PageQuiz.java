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

public class PageQuiz implements ActionListener {
    private final JFrame frame;
    private final JLabel questionLabel; // intitulé de la question
    private final JButton optionButton1; // option de réponse 1
    private final JButton optionButton2; // option de réponse 2
    private final JButton optionButton3; // option de réponse 3
    private final JButton optionButton4; //option de réponse 4
    private final Question q; // Question actuel
    private final Score score; // score du joueur
    private final JLabel scoreLabel;
    private final JLabel timerLabel;
    private ExecutorService service;
    private Future<?> future;
    private int j=0;
    private final int taille;
    private final ArrayList<Question> qList; // liste de Question
    private final Utilisateur user;
    private final Utilisateurs users;

    /**
     * Page d'affichage du jeu en mode solo
     * @param question : la liste de question que l'utilisateur va avoir.
     */
    public PageQuiz(ArrayList<Question> question, Utilisateur user, Utilisateurs users) {
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

        //initialise la taille(nombre de question), la liste

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
        score = new Score(0);
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Votre score : " + Integer.toString(score.getScore()));
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

    /**
     * Si un bouton est déclenché le TImer s'arrete et une vérification est lancé
     * @param event the event to be processed
     */
    public void actionPerformed (ActionEvent event){
        JButton bouton = (JButton) event.getSource(); // bouton prend la source de l'évènement(elle prend le button qui a été appuyé)
        stopTimer();
        checkAnswer(bouton.getText(),q.getReponse());

    }

    /**
     *
     * @param selectedOption : La réponse choisi par l'utilisateur
     * @param correctOption : la bonne réponse
     */
    private void checkAnswer(String selectedOption,String correctOption) {
        //Message si le temps est dépassé
        if (selectedOption.equals("Trop tard") ){
            JOptionPane.showMessageDialog(frame, "Trop Tard ");
        }
        // Comparer la réponse sélectionnée avec la réponse correcte
        if (selectedOption.equals(correctOption) ) {
            JOptionPane.showMessageDialog(frame, "Bonne réponse !");
            score.addScore(q.getPoints());
        } else {
            JOptionPane.showMessageDialog(frame, "Mauvaise réponse ! La réponse était "+q.getReponse());
        }

        // Change la question
        changeQuestion();
    }

    /**
     * Procédure qui sert à changer la question ou mettre la page de fin
     */
    private void changeQuestion() {
        j++;
        //Nouvelle question
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
            scoreLabel.setText("Votre score : "+Integer.toString(score.getScore()));
            service.shutdownNow();

            startTimer(10); // Délai de 10 secondes
        } else { // Page de fin
            new PageFinSolo(score.getScore(),user,users);
            frame.dispose();
        }
    }

    /** Cette procédure sert à lancer un timer en thread
     *
     * @param delay : chronometre
     */
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

    /**
     * Procédure qui vérifie si l'objet future existe et si la tache assynchrone associée n'est pas encore terminée.
     */
    private void stopTimer() {
        if (future != null && !future.isDone()) {
            future.cancel(true);
            service.shutdownNow();
        }
        service.shutdownNow(); // au cas ou, sinon ça ne fait rien
    }

    /**
     * affichage d'un timer qui décrémente (pour les secondes)
     * @param secondsPassed : le temps passé au total en second
     * @param delai : le temps accordé pour répondre à la question
     */
    private void updateTimerLabel(int secondsPassed, int delai) {
        long hours = TimeUnit.SECONDS.toHours(secondsPassed);
        long minutes = TimeUnit.SECONDS.toMinutes(secondsPassed) % 60;
        long seconds = delai - (secondsPassed % 60);

        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText("Timer "+time);
    }
}
