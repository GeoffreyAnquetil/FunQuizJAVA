import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class GameSignUp extends JFrame implements ActionListener {

    private Utilisateurs users;

    private JLabel prenomLabel = new JLabel("Prenom :");
    private JLabel nomLabel = new JLabel("Nom :");
    private JLabel ageLabel = new JLabel("Age :");
    private JLabel pseudoLabel = new JLabel("Pseudo :");
    private JLabel mdpLabel = new JLabel("Mot de passe :");

    private JTextField prenomField = new JTextField();
    private JTextField nomField = new JTextField();
    private JTextField ageField = new JTextField();
    private JTextField pseudoField = new JTextField();
    private JPasswordField mdpField = new JPasswordField();

    private JButton retourButton = new JButton("Retour");
    private JButton signupButton = new JButton("S'inscrire");

    public GameSignUp(Utilisateurs users){
        super("Inscription"); // On set le titre à Connexion

        this.users = users;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Le processus termine quand la fenêtre est fermée
        this.setVisible(true); // On affiche la fenêtre
        this.setSize(300,300); // On fixe la taille de la fenêtre
        this.setLocationRelativeTo(null); // La fenêtre apparait au centre de l'écran

        GridLayout grille = new GridLayout(6,2); // On crée une grille 6,1
        this.setLayout(grille); // On set le layout de la frame à cette grille

        prenomLabel.setHorizontalAlignment(SwingConstants.CENTER); // On centre le Label
        nomLabel.setHorizontalAlignment(SwingConstants.CENTER); // On centre le Label
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER); // On centre le Label
        pseudoLabel.setHorizontalAlignment(SwingConstants.CENTER); // On centre le Label
        mdpLabel.setHorizontalAlignment(SwingConstants.CENTER); // On centre le Label

        signupButton.addActionListener(this);
        retourButton.addActionListener(this);

        this.add(prenomLabel);
        this.add(prenomField);
        this.add(nomLabel);
        this.add(nomField);
        this.add(ageLabel);
        this.add(ageField);
        this.add(pseudoLabel);
        this.add(pseudoField);
        this.add(mdpLabel);
        this.add(mdpField);
        this.add(retourButton);
        this.add(signupButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == retourButton){
            try {
                this.dispose();
                new GameLogin(users);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if(e.getSource() == signupButton){
            String prenom = prenomField.getText();
            String nom = nomField.getText();
            try {
                String pseudo = pseudoField.getText();
                String mdp = new String(mdpField.getPassword());
                int age = Integer.parseInt(ageField.getText());
                if(prenom == "") {
                    JOptionPane.showMessageDialog(this, "veuillez entrer un prenom");
                    prenomField.setText("");
                } else if(nom == "") {
                    JOptionPane.showMessageDialog(this, "veuillez entrer un nom");
                    prenomField.setText("");
                } else if(users.getUsers().keySet().contains(pseudo)) {
                    JOptionPane.showMessageDialog(this, "Pseudo déjà pris, veuillez en saisir un nouveau");
                    prenomField.setText("");
                } else if(mdp.length() < 4){
                    JOptionPane.showMessageDialog(this, "Votre mot de passe doit faire minimum 4 caractères");
                    prenomField.setText("");
                } else {
                    Utilisateur user = new Utilisateur(prenom, nom, age, pseudo, mdp, false, false);
                    HashMap<String, Utilisateur> hashMap = users.getUsers();
                    hashMap.put(user.getPseudo(), user);
                    users.setUsers(hashMap);
                    try {
                        users.serialize("./src/users/usersData.csv");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    this.dispose();
                    new GameHomePage(user, users);
                }
            } catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(this, "Veuillez entrer un age correct");
                ageField.setText("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
