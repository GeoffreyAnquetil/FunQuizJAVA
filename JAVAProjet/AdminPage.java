import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class AdminPage extends JFrame implements ActionListener {

    private Utilisateurs users;

    private final JPanel panel1 = new JPanel();
    private final JPanel panel2 = new JPanel();
    private final JPanel panel3 = new JPanel();
    private final JPanel panel4 = new JPanel();

    private final JLabel labelPseudo = new JLabel();
    private final JLabel labelChamps = new JLabel();
    private final JLabel labelModification = new JLabel();

    private final JComboBox<String> comboBoxPseudo = new JComboBox<>();
    private final JComboBox<String> comboBoxChamps = new JComboBox<>();

    private final JButton buttonAffichage = new JButton();
    private final JButton buttonSuppression = new JButton();
    private final JButton buttonConfirmer1 = new JButton();
    private final JButton buttonConfirmer2 = new JButton();
    private final JButton buttonAnnuler = new JButton();
    private final JButton buttonQuestion = new JButton();

    private final JTextField textField = new JTextField();

    public AdminPage(Utilisateurs users){
        super("Administration");
        this.users = users;

        this.setSize(700,250); // On initialise la taille de la fenêtre
        this.setLocationRelativeTo(null); // La fenêtre apparait au centre de l'écran
        this.setDefaultCloseOperation(HIDE_ON_CLOSE); // Le processus termine quand la fenêtre est fermée
        this.setVisible(true); // On affiche la fenêtre

        GridLayout grillePrincipale = new GridLayout(5,1); // Grille principale de la fenêtre
        GridLayout grillePanel1 = new GridLayout(1,2); // Grille pour le panel1
        GridLayout grillePanel2Et3 = new GridLayout(1,3); // Grille pour le panel2 et le panel3
        GridLayout grillePanel4 = new GridLayout(1,4); // Grille pour le panel4

        // Initialisation des layout pour la fenêtre et les panel
        this.setLayout(grillePrincipale);
        panel1.setLayout(grillePanel1);
        panel2.setLayout(grillePanel2Et3);
        panel3.setLayout(grillePanel2Et3);
        panel4.setLayout(grillePanel4);

        // Initialisation des comboBox
        Set<String> pseudos = users.getUsers().keySet();
        for(String pseudo : pseudos) comboBoxPseudo.addItem(pseudo);
        String[] champs = {"prenom", "nom", "age", "pseudo", "MdP", "admin", "suspendu"};
        for(String champ : champs) comboBoxChamps.addItem(champ);

        // Initialisation des label
        labelPseudo.setText("Utilisateur à modifier :");
        labelPseudo.setHorizontalAlignment(SwingConstants.CENTER);
        labelChamps.setText("Champs à modifier :");
        labelChamps.setHorizontalAlignment(SwingConstants.CENTER);
        labelModification.setVisible(false);

        // Initialisation des boutons
        buttonAffichage.setText("Afficher Utilisateurs");
        buttonAffichage.addActionListener(this);
        buttonSuppression.setText("Supprimer Utilisateur");
        buttonSuppression.addActionListener(this);
        buttonConfirmer1.setText("Confirmer");
        buttonConfirmer1.addActionListener(this);
        buttonConfirmer2.setText("Confirmer");
        buttonConfirmer2.addActionListener(this);
        buttonConfirmer2.setVisible(false);
        buttonAnnuler.setText("Annuler");
        buttonAnnuler.addActionListener(this);
        buttonAnnuler.setVisible(false);
        buttonQuestion.setText("Ajouter une question");
        buttonQuestion.addActionListener(this);

        // On rend invisible le textfield
        textField.setVisible(false);

        // On remplie le panel1
        panel1.add(buttonAffichage);
        panel1.add(buttonSuppression);
        // On remplie le panel2
        panel2.add(labelPseudo);
        panel2.add(comboBoxPseudo);
        panel2.add(new JLabel(""));
        // On remplie le panel3
        panel3.add(labelChamps);
        panel3.add(comboBoxChamps);
        panel3.add(buttonConfirmer1);
        // On remplie le panel4
        panel4.add(labelModification);
        panel4.add(textField);
        panel4.add(buttonConfirmer2);
        panel4.add(buttonAnnuler);

        // On ajoute les panel et le dernier bouton à la fenêtre
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel1);
        this.add(buttonQuestion);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection1 = comboBoxPseudo.getItemAt(comboBoxPseudo.getSelectedIndex());
        String selection2 = comboBoxChamps.getItemAt(comboBoxChamps.getSelectedIndex());
        String text = textField.getText();

        if(e.getSource() == buttonConfirmer1){ // <=> appuie sur le premier bouton "Confirmer"
            // On affiche la dernière ligne de bouton/label etc
            labelModification.setVisible(true);
            textField.setVisible(true);
            buttonConfirmer2.setVisible(true);
            buttonAnnuler.setVisible(true);

            // On initialise le label3
            labelModification.setText("<html><span>" +
                            "Nouvelle valeur de " + selection2 + " pour " + selection1 + " :"
                            + "</html></span>");
            labelModification.setHorizontalAlignment(SwingConstants.CENTER);

            buttonConfirmer1.setVisible(false); // On arrête d'afficher le bouton 1
            buttonConfirmer1.removeActionListener(this); // On enlève son ActionListener
            // On désactive les combobox
            comboBoxPseudo.setEnabled(false);
            comboBoxChamps.setEnabled(false);
            panel3.revalidate(); // On actualise le panel3

            panel4.revalidate(); // On actualise le panel4

            // On actualise la fenêtre
            this.revalidate();
            this.repaint();

        } else if(e.getSource() == buttonAnnuler){ // <=> appuie sur le bouton "Annuler"
            // On ré affiche le bouton1 et on ré active les combobox
            buttonConfirmer1.setVisible(true);
            buttonConfirmer1.addActionListener(this);
            comboBoxPseudo.setEnabled(true);
            comboBoxChamps.setEnabled(true);
            panel3.revalidate();

            // On dé affiche la troisième ligne
            labelModification.setVisible(false);
            textField.setVisible(false);
            buttonConfirmer2.setVisible(false);
            buttonAnnuler.setVisible(false);
            panel4.revalidate();

            // On actualise la fenêtre
            this.revalidate();
            this.repaint();

        } else if(e.getSource() == buttonConfirmer2){ // <=> appuie sur le deuxième bouton "Confirmer"
            // Si la comboBox est sur Suspendu ou Admin, on vérifie que l'utilisateur rentre bien "true" ou "false"
            if((selection2.equals("suspendu") || selection2.equals("admin"))
                    && (!(text.equals("true")) && !(text.equals("false")))){
                JOptionPane.showMessageDialog(this, "Veuillez saisir true ou false");
                textField.setText("");
            // Si la comboBox est sur "pseudo" on vérifie que le pseudo saisi est bien unique
            } else if((selection2.equals("pseudo"))
                    && (users.getUsers().containsKey(text))) {
                JOptionPane.showMessageDialog(this, "Pseudo déjà existant");
                textField.setText("");
            } else if(text.equals("")){
                JOptionPane.showMessageDialog(this, "Veuillez saisir une valeur de : " + selection2);
            } else {
                // On affiche un popup pour prévenir l'utilisateur que l'action est importante
                String[] options = {"Oui", "Non"};
                int choix = JOptionPane.showOptionDialog(null,
                        "Êtes-vous sûr de vouloir changer le " + selection2
                                + " de " + selection1 + " en : " + text,
                        "Confirmation", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (choix == JOptionPane.YES_OPTION) { // Si l'utilisateur clique sur "oui"
                    HashMap<String, Utilisateur> hm = users.getUsers(); // On récupère la hashmap de users
                    Utilisateur user = hm.get(selection1); // On récupère l'utilisateur sélectionné
                    hm.remove(selection1); // On l'enlève de la hashmap
                    switch (selection2){ // Selon le choix fait, on modifie le user
                        case "prenom" -> user.setPrenom(textField.getText());
                        case "nom" -> user.setNom(textField.getText());
                        case "age" -> user.setAge(Integer.parseInt(textField.getText()));
                        case "pseudo" -> user.setPseudo(textField.getText());
                        case "MdP" -> user.setMdp(textField.getText());
                        case "admin" -> user.setAdmin(Boolean.parseBoolean(textField.getText()));
                        case "suspendu" -> user.setSuspendu(Boolean.parseBoolean(textField.getText()));
                    }
                    hm.put(user.getPseudo(), user); // On le rajoute à la hashmap
                    users.setUsers(hm); // On modifie la hashmap de users
                    try {
                        users.serialize("./src/users/usersData.csv"); // On sérialise
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.dispose(); // On ferme la page
                    new AdminPage(users); // On en ouvre une nouvelle avec les users modifiés
                } else { // S'il clique sur "non" ou ferme la fenêtre
                    textField.setText("");
                }
            }
        } else if (e.getSource() == buttonAffichage){
            try {
                new UsersTab();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == buttonSuppression){
            new UserSuppressionPage(users);
        } else if (e.getSource() == buttonQuestion){
            try {
                new QuestionCreationPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
