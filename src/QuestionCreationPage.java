import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionCreationPage extends JFrame implements ActionListener {

    private final JLabel labelChoisirTheme = new JLabel("Theme");
    private final JLabel labelIntitule = new JLabel("Intitule");
    private final JLabel labelOption1 = new JLabel("Option 1");
    private final JLabel labelOption2 = new JLabel("Option 2");
    private final JLabel labelOption3 = new JLabel("Option 3");
    private final JLabel labelOption4 = new JLabel("Option 4");
    private final JLabel labelReponse = new JLabel("Reponse");
    private final JLabel labelDifficulte = new JLabel("Difficulte");

    private final JComboBox<String> comboBoxTheme = new JComboBox<>();
    private final JComboBox<String> comboBoxDifficulte = new JComboBox<>();

    private final JButton buttonAjouterQuestion = new JButton("Ajouter");

    private final JTextField fieldIntitule = new JTextField();
    private final JTextField fieldOption1 = new JTextField();
    private final JTextField fieldOption2 = new JTextField();
    private final JTextField fieldOption3 = new JTextField();
    private final JTextField fieldOption4 = new JTextField();

    private final JRadioButton rb1 = new JRadioButton("option1");
    private final JRadioButton rb2 = new JRadioButton("option2");
    private final JRadioButton rb3 = new JRadioButton("option3");
    private final JRadioButton rb4 = new JRadioButton("option4");

    private final ButtonGroup group = new ButtonGroup();

    private final JPanel panel1 = new JPanel();
    private final JPanel panel2 = new JPanel();
    private final JPanel panel3 = new JPanel();
    private final JPanel panel4 = new JPanel();
    private final JPanel panel5 = new JPanel();
    private final JPanel panel6 = new JPanel();
    private final JPanel panel7 = new JPanel();
    private final JPanel panel8 = new JPanel();
    private final JPanel panel9 = new JPanel();

    public QuestionCreationPage() throws IOException {
        super("Création de question");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true); // On affiche la fenêtre
        this.setSize(600,400); // On fixe la taille de la fenêtre
        this.setLocationRelativeTo(null); // La fenêtre apparait au centre de l'écran

        // on instancie un Themes pour récupérer les themes en mémoire
        String[] themes = {"Musique", "Cinéma", "Sport"};
        String[] difficultes = {"facile", "moyen", "difficile"};

        // On setup les comboBox
        for(String difficulte : difficultes) comboBoxDifficulte.addItem(difficulte);
        for(String theme : themes) comboBoxTheme.addItem(theme);

        // On setup le boutons
        buttonAjouterQuestion.addActionListener(this);

        // On groupe les radioButton
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
        group.add(rb4);
        // On initialise le premier radioButton à coché pour qu'au moins 1 soit coché initialement
        rb1.doClick();

        // On crée des gridlayout pour la fenetre et les différents panel
        GridLayout grille91 = new GridLayout(9,1); // On crée une grille 9,1
        GridLayout grille12 = new GridLayout(1,2); // On crée une grille 1,2
        GridLayout grille13 = new GridLayout(1,3); // On crée une grille 1,3
        GridLayout grille15 = new GridLayout(1,5); // On crée une grille 1,5
        this.setLayout(grille91); // On set le layout de la frame à cette grille
        // On set le layout des 8 premiers panels
        panel1.setLayout(grille12);
        panel2.setLayout(grille12);
        panel3.setLayout(grille12);
        panel4.setLayout(grille12);
        panel5.setLayout(grille12);
        panel6.setLayout(grille12);
        panel7.setLayout(grille15);
        panel8.setLayout(grille12);

        // On ajoute les éléments aux panels
        panel1.add(labelChoisirTheme);
        panel1.add(comboBoxTheme);

        panel2.add(labelIntitule);
        panel2.add(fieldIntitule);

        panel3.add(labelOption1);
        panel3.add(fieldOption1);

        panel4.add(labelOption2);
        panel4.add(fieldOption2);

        panel5.add(labelOption3);
        panel5.add(fieldOption3);

        panel6.add(labelOption4);
        panel6.add(fieldOption4);

        panel7.add(labelReponse);
        panel7.add(rb1);
        panel7.add(rb2);
        panel7.add(rb3);
        panel7.add(rb4);

        panel8.add(labelDifficulte);
        panel8.add(comboBoxDifficulte);

        panel9.add(buttonAjouterQuestion);

        // On ajoute les panel à la fenetre
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel6);
        this.add(panel7);
        this.add(panel8);
        this.add(panel9);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonAjouterQuestion){
                Questions questions = new Questions();
            try {
                questions.deserialize("./src/questions/questions.csv");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            String difficulte = comboBoxDifficulte.getItemAt(comboBoxDifficulte.getSelectedIndex());
                String intitule = fieldIntitule.getText();
                String theme = comboBoxTheme.getItemAt(comboBoxTheme.getSelectedIndex());
                String option1 = fieldOption1.getText();
                String option2 = fieldOption2.getText();
                String option3 = fieldOption3.getText();
                String option4 = fieldOption4.getText();
                String reponse = "defaultValue";

                if(!intitule.equals("") &&
                        !option1.equals("") && !option2.equals("") && !option3.equals("") && !option4.equals("")) {
                    if (rb1.isSelected()) reponse = option1;
                    else if (rb2.isSelected()) reponse = option2;
                    else if (rb3.isSelected()) reponse = option3;
                    else if (rb4.isSelected()) reponse = option4;
                    int points = -1;
                    switch (difficulte) {
                        case "facile" -> points = 1;
                        case "moyen" -> points = 2;
                        case "difficile" -> points = 3;
                    }

                    Question question = new Question(difficulte, intitule, theme,
                            option1, option2, option3, option4,
                            reponse, points);

                    questions.addQuestion(question);
                    try {
                        questions.serialize("./src/questions/questions.csv");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(this, "Question bien ajoutée à la banque de données");
                } else {
                    JOptionPane.showMessageDialog(this, "Veuillez ne laisser aucun champs vide");
                }
        }
    }
}
