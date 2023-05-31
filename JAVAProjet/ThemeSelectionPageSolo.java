import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ThemeSelectionPageSolo implements ActionListener {
    private final JFrame frame;

    private ArrayList<Question> list;
    
    private final JCheckBox sportbutton;
    private final JCheckBox cinemabutton;
    private final JCheckBox musiquebutton;

    private final JComboBox<Integer> questionBox;

    private final JButton jouerbutton;
    private final ButtonGroup groupe;

    private final JRadioButton facilebutton;
    private final JRadioButton normalbutton;
    private final JRadioButton hardbutton;
    private final Utilisateur user;
    private final Utilisateurs users;
    public ThemeSelectionPageSolo(Utilisateur user,Utilisateurs users) throws IOException {
        this.user = user;
        this.users = users;
        frame = new JFrame("GridLayout");
        frame.setSize(600,200);
        frame.setLocationRelativeTo(null);
        frame.setTitle("page de sélection Solo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel jpanelAll = new JPanel();
        jpanelAll.setLayout(new GridLayout(4,1));

        JLabel messageLabel = new JLabel("choisissez vos thèmes et le nombre de question.");
        jpanelAll.add(messageLabel);

        //Selection des thèmes
        JPanel themeJpanel = new JPanel();
        themeJpanel.setLayout(new GridLayout(1,3));
        sportbutton = new JCheckBox("Sport");
        cinemabutton = new JCheckBox("Cinéma");
        musiquebutton = new JCheckBox("Musique");
        themeJpanel.add(sportbutton);
        themeJpanel.add(cinemabutton);
        themeJpanel.add(musiquebutton);
        jpanelAll.add(themeJpanel);

        //Selection du nombre de question
        JPanel QuestionJpanel = new JPanel();
        QuestionJpanel.setLayout(new GridLayout(1,2));
        JLabel questionLabel = new JLabel("Veuillez choisir le nombre de question");
        QuestionJpanel.add(questionLabel);
        questionBox = new JComboBox<>();
        questionBox.addItem(5);
        questionBox.addItem(10);
        questionBox.addItem(15);
        questionBox.addItem(20);
        QuestionJpanel.add(questionBox);
        jpanelAll.add(QuestionJpanel);

        JPanel jouerJpanel = new JPanel();
        jouerbutton = new JButton("Jouer");
        jouerJpanel.add(jouerbutton);
        frame.add(jouerJpanel,BorderLayout.SOUTH);

        JPanel difficultejpanel = new JPanel();
        difficultejpanel.setLayout(new GridLayout(1,3));
        groupe= new ButtonGroup();
        facilebutton = new JRadioButton("Facile");
        facilebutton.setSelected(true); // On le met initialement à Selected pour éviter des abus d'UI
        difficultejpanel.add(facilebutton);
        groupe.add(facilebutton);
        normalbutton = new JRadioButton("Normal");
        difficultejpanel.add(normalbutton);
        groupe.add(normalbutton);
        hardbutton = new JRadioButton("Hard");
        difficultejpanel.add(hardbutton);
        groupe.add(hardbutton);
        jpanelAll.add(difficultejpanel);


        jouerbutton.addActionListener(this);
        frame.add(jpanelAll,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void actionPerformed (ActionEvent event){
        if(event.getSource() == jouerbutton){
            try {
                 list = genererQuestions();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        new PageQuiz(list, user,users);

        frame.dispose();
    }

    public ArrayList<Question> genererQuestions() throws IOException {

        if((!sportbutton.isSelected()) && (!musiquebutton.isSelected()) && (!cinemabutton.isSelected())){
            JOptionPane.showMessageDialog(frame, "Veuilllez sélectionner au moins 1 thème");
            return null;
        } else {
            ArrayList<String> themes = new ArrayList<>();
            if (sportbutton.isSelected()) themes.add("Sport");
            if (musiquebutton.isSelected()) themes.add("Musique");
            if (cinemabutton.isSelected()) themes.add("Cinéma");

            int difficulte = 0;
            if (facilebutton.isSelected()) difficulte = 1;
            else if (normalbutton.isSelected()) difficulte = 2;
            else if (hardbutton.isSelected()) difficulte = 3;

            int nbQuestion = questionBox.getItemAt(questionBox.getSelectedIndex());

            Questions questions = new Questions();
            questions.deserialize("./src/more/question.csv");
            ArrayList<Question> dataBase = questions.getQuestions();

            ArrayList<Question> listeQuestions = new ArrayList<>();

        /* On enlève toutes les questions de la dataBase dont les difficultés sont supérieurs à celle sélectionnée
           et celles dont le thème n'est pas sélectionné  */
            for (int i = 0; i < dataBase.size(); i++) {
                if ((dataBase.get(i).getPoints() > difficulte) || !themes.contains(dataBase.get(i).getTheme())) {
                    dataBase.remove(i);
                    i--;
                }
            }
            // On mélange la liste
            Collections.shuffle(dataBase);
            // On prend les x premières questions avec x le nb de questions sélectionné par l'utilisateur
            for (int j = 0; j < nbQuestion; j++) listeQuestions.add(dataBase.get(j));
            // On trie les questions par ordre croissant de difficulte
            listeQuestions.sort(new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    return Integer.compare(o1.getPoints(), o2.getPoints());
                }
            });

            return listeQuestions;
        }
    }
}